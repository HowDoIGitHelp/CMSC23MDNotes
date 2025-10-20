# Lab Exercise 11 (Printable Shipment)

## Task

Looking back at our previous lab exercises, some of the example classes contain string representation but do not implement the `toString()` function. An example of this is `Shipment` back from the factory method example. It does contain a string representation builder called `shipmentDetails()`, but printing a shipment is quite tedious since you have to print, `s.shipmentDetails()`.  You can replace the name of `shipmentDetails()` to `toString()` but  will potentially affect other clients of shipment. You can add the `toString()` function which does exactly the same but this may introduce unwanted code duplication.

The best solution for this problem is to create an adapter for shipment called `PrintableShipment`. This adapter will realize some `Printable` abstraction, which only contains the abstract method `toString()`. 

![adapter example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/PrintableShipment.svg)

**Complete the system using the adapter pattern**

## Assessment Criteria

- Adapter functionality - 15
- Correct class structure - 10
- Pattern is open for extension - 15