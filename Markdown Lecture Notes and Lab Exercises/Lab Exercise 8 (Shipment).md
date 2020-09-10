# Lab Exercise 8 (Shipment)

## Task

#### Online Marketplace Delivery

Consider you're developing the product delivery side of an online  marketplace app (think Amazon/Lazada). Your app is on its early stage so their is only one delivery option,  standard nationwide delivery that takes a minimum of 7 days. 

What you have is `Shipment` class that contains a `StandardDelivery` class. Inside the shipment class is the `shipmentDetails()` builder  which builds a string representing the details of the shipment, this  includes the delivery details (which requires access to the composed `StandardDelivery` instance). Inside the constructor of `Shipment` an instance of `StandardDelivery` is created so that every `Shipment` is set to be delivered using standard delivery.

![online marketplace](C:/Users/rrabe/Google Drive/Lecture-Notes-And-Resources/CMSC 23/uml/nonfactorymethodExample.png)

```python
from datetime import date,timedelta

class Order:
    def __init__(self,productName:str, productPrice:float):
        self.__productName = productName
        self.__productPrice = productPrice
    def orderString(self) -> str:
        return "%s P%.2f" % (self.__productName,self.__productPrice)
    def price(self) -> float:
        return self.__productPrice

class StandardDelivery:
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
        self._delivery = StandardDelivery(location)

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

o = [Order("Surface Pro 7",40000),Order("Zzzquil",900)]
s = Shipment(o,date(2019,11,1),"Cebu City")
print(s.shipmentDetails())

```

This system does work. It works but it is still inelegant. As soon as your app grows, you will incorporate new delivery options like express delivery, or pickups or whatever. Every time you need to add a new delivery method you will need to perform surgery in `Shipment` since the `StandardDelivery` instance is created inside the constructor of `Shipment`. `Shipment`'s code is too coupled with `StandardDelivery`.

To solve this you need to implement the factory method pattern.  Right now shipment is a factory since it constructs its own instance of `StandardDelivery`. To refactor this into elegant code, you need to so create an abstraction called`Delivery` first to support polymorphism. Inside `Shipment` instead of creating instances of `Delivery`'s using a constructor, you invoke a factory method that encapsulates the instantiation of `Delivery`. In this case we name this method `newDelivery()`. All it does is return an instance of `StandardDelivery` using its constructor.

![online marketplace](C:/Users/rrabe/Google Drive/Lecture-Notes-And-Resources/CMSC 23/uml/factorymethodExample.png)

In this new architecture, whenever there are new delivery methods a  shipment could have, all you have to do is to create a realization of  that delivery method. In this case the new delivery method is `ExpressDelivery` which delivers for two days but is twice as expensive. And instead of changing `Shipment` (violates Open/Closed Principle), you make an extension to `Shipment`. This extension is the specialization to shipment called `ExpressShipment` (a shipment that uses express delivery). In this specialization, you only need to override the factory method  delivery, so that every instance of delivery construction creates `ExpressDelivery`. The difference between `ExpressDelivery` and `Delivery` is that `ExpressDelivery` has a delivery fee of 1000 and the estimated delivery date is one day after the processing date.

**Complete the system using the factory method pattern.**

## Assessment Criteria

- Completeness of the pattern - 40
- Elegance of method and attribute naming - 10

**Deadline November 30, 2020**