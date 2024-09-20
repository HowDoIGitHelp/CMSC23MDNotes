# Lab Exercise 17 (Printable Shipment)

## Task

Looking back at our previous lab exercises, some of the example classes contain string representation but do not implement the `__str__()` function. An example of this is `Shipment` back from the factory method example. It does contain a string representation builder called `shipmentDetails()`, but printing a shipment is quite tedious since you have to print, `s.shipmentDetails()`.  You can replace the name of `shipmentDetails()` to `__str__()` but this will potentially affect other clients of shipment. You can add the `__str__()` function which does exactly the same but this may introduce unwanted code duplication.

```python
from abc import ABC,abstractmethod
from datetime import date,timedelta

class Delivery(ABC):
    @abstractmethod
    def deliveryDetails(self) -> str:
        pass
    @abstractmethod
    def deliveryFee(self) -> float:
        pass
    @abstractmethod
    def estimatedDeliveryDate(self,processDate:date) -> float:
        pass
    @abstractmethod
    def changeDeliveryStatus(self,newStatus:str):
        pass


class Order:
    def __init__(self,productName:str, productPrice:float):
        self.__productName = productName
        self.__productPrice = productPrice
    def orderString(self) -> str:
        return "%s P%.2f" % (self.__productName,self.__productPrice)
    def price(self) -> float:
        return self.__productPrice
class StandardDelivery(Delivery):
    def __init__(self,location:str):
        self.__location = location
        self.__deliveryStatus = "Processing"
    def deliveryDetails(self) -> str:
        r = "STANDARD DELIVERY\nDELIVER TO:%s\nDELIVERY STATUS: %s\nDELIVERY FEE: P%.2f" % (self.__location,self.__deliveryStatus,self.deliveryFee())
        return r
    def deliveryFee(self) -> float:
        return 500
    def estimatedDeliveryDate(self,processDate:date) -> float:
        return processDate + timedelta(days = 7)
    def changeDeliveryStatus(self,newStatus:str):
        self.__deliveryStatus = newStatus

class Shipment:
    def __init__(self, orderList:[Order], processDate: date, location):
        self._orderList = orderList
        self._processDate = processDate
        self._delivery = self.delivery(location)

    def delivery(self,location:str) -> Delivery:
        return StandardDelivery(location)

    def totalPrice(self) -> str:
        t = 0.0
        for order in self._orderList:
            t+=order.price()
        return t

    def shipmentDetails(self) -> str:
        r = "ORDERS:" + str(self._processDate) + "\n"
        for order in self._orderList:
            r += order.orderString() + "\n"
        r += "\n"
        r += "TOTAL PRICE OF ORDERS: P"  + str(self.totalPrice()) + "\n"
        r += self._delivery.deliveryDetails() + "\n\n"
        r += "PRICE WITH DELIVERY FEE : P" + str(self.totalPrice()+self._delivery.deliveryFee()) + "\n"
        r += "ESTIMATED DELIVERY DATE: " + str(self._delivery.estimatedDeliveryDate(self._processDate))
        return r


class ExpressDelivery(Delivery):
    def __init__(self,location:str):
        self.__location = location
        self.__deliveryStatus = "Processing"
    def deliveryDetails(self) -> str:
        r = "EXPRESS DELIVERY\nDELIVER TO:%s\nDELIVERY STATUS: %s\nDELIVERY FEE: P%.2f" % (self.__location,self.__deliveryStatus,self.deliveryFee())
        return r
    def deliveryFee(self) -> float:
        return 1000
    def estimatedDeliveryDate(self,processDate:date) -> float:
        return processDate + timedelta(days = 2)
    def changeDeliveryStatus(self,newStatus):
        self.__deliveryStatus = newStatus

class ExpressShipment(Shipment):
    def delivery(self,location) -> Delivery:
        return ExpressDelivery(location)

```

The best solution for this problem is to create an adapter for shipment called `PrintableShipment`. This adapter will realize some `Printable` abstraction, which only contains the abstract method `__str__()`. 

![adapter example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/adapterexample.svg)

**Complete the system using the adapter pattern**

## Assessment Criteria

- Completeness of the pattern - 40
- Elegance of method and attribute naming - 10