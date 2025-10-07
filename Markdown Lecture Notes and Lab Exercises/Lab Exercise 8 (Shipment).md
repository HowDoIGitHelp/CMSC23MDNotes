# Lab Exercise 8 (Shipment)

## Task

#### Online Marketplace Delivery

Consider you're developing the product delivery side of an online  marketplace app (think Amazon/Shopee). Your app is on its early stage so their is only one delivery option,  standard nationwide delivery that takes a minimum of 7 days. 

What you have is a `Shipment` class that contains a `StandardDelivery` class. Inside the shipment class is the `shipmentDetails()` method which builds a string representing the details of the shipment, this  includes the delivery details (which requires access to the composed `StandardDelivery` instance stored in the attribute `delivery`). 

When constructing instances of `Shipment` you don't pass a `StandardDelivery` instance to be stored in the attribute `delivery`. Instead, you pass a `location` string which will be used to construct a `StandardDelivery` instance.

![online marketplace](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/ShipmentNoFactory.svg)

This system does work. It works but it is still inelegant. As soon as your app grows, you will incorporate new delivery options like express delivery, pickups, etc. Every time you need to add a new delivery method you will need to perform surgery in `Shipment` since the `StandardDelivery` instance is created inside the constructor of `Shipment`. `Shipment`'s code is too coupled with `StandardDelivery`.

To solve this you decide to implement the factory method pattern.  Right now shipment is a factory since it constructs its own instance of `StandardDelivery`. To refactor this into elegant code, you will first need to so create an abstraction called `Delivery`  to support polymorphism. Inside `Shipment`, instead of creating an instance of `Delivery` using a constructor, you invoke a factory method that encapsulates the instantiation of `Delivery`. In this case, we name this method `newDelivery()`. All it does is return an instance of `StandardDelivery` using its constructor.

![online marketplace](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/Shipment.svg))

In this new architecture, whenever there are new delivery methods a  shipment could have, all you have to do is to create a realization of  that delivery method. For example, the new delivery method is `ExpressDelivery` which delivers the next day but is twice as expensive. And instead of changing `Shipment` (violates Open/Closed Principle), you make an extension to `Shipment`. This extension is the specialization to shipment called `ExpressShipment` (a shipment that uses express delivery). In this specialization, you only need to override the factory method  delivery, so that every instance of delivery construction creates `ExpressDelivery`. 

The difference between `ExpressDelivery` and `Delivery` is that **`ExpressDelivery` has a delivery fee of 1000 and the estimated delivery date is one day after the processing date**.

**Complete the system using the factory method pattern.**

## Assessment Criteria

- Correct naming of definitions - 10
- Correct `ExpressShipment` behavior - 10
- Correct class structure and relationships - 10
- Class architecture supports extensibility - 10
