# Lab Exercise 15 (Weather Notifier)

## Task

You are creating a push notification system that works for multiple platforms. You want to distribute information about the current weather and news headlines. This system will be potentially used on many platforms so you have to think about the maintainability issues for adding new platform support.

To implement this, you have to apply the observer pattern. Your subject would be `Weather` data and `Headline` data (which are their own classes). These subjects should be encapsulated into a single publisher class (which will be called `PushNotifier`). 

Any platform, that is interested in the changes to the subject should realize a `Subscriber` abstraction (Observer), which contains the abstract method update().

![observer example](https://raw.githubusercontent.com/HowDoIGitHelp/CMSC23MDNotes/master/Markdown%20Lecture%20Notes%20and%20Lab%20Exercises/uml/umlOutputs/WeatherNotifier.svg)

**Complete the system using the observer pattern.**

## Assessment Criteria

- Completeness of the pattern - 40