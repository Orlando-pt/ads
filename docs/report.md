# Report

# Functionalities Made Until Date
The main functionalities of the program implemented already without GUI are:
- Allow record of **places**
- Allow record of **dates**
    - Both **simple** and **interval** dates
- Allow record of **events**
    - For this iteration it can only record 2 types of events (Birth and Custom)
- Allow record of **people**

# Introduction
At this document it will be explained what was the process to develop this platform. This software system helps to support the work of historians and individual genealogy researchers by storing data related to individuals, their relations, and other important kinds of information.

# Goals

# The Design

## Solving Simple Dates Snd Intervals
We'll start by explaining how we solved the problem of having uncertain dates.

### Problem In Context
We have to save two different dates, **Interval** and **Simple** dates. The difference between them is that one just has a simple date (10-11-2021 00:00:00) and the other is an interval date (10-11-2021 00:00:00 - 24-12-2021 23:20:00). And this is not all, we also have to pay attention that dates may be incomplete.

### Design Problem To Solve
The problem with dates was that they could be **incomplete**, which meant that we would need to have a lot of different constructors for the various possibilities or send params with null on the constructor.

### The Pattern
For the problem that we are trying to solve, the **Builder Pattern** is the one that best applies since the specification of the problem is exactly what it solves. The *Builder Pattern* extracts the object construction code out of the class and moves it to separate objects called builders. This way it makes it possible to “build” a date without the need for complex constructors and in a more structured way. The pattern solves the problem of **two different types of dates** since we can have **different builders** for each of them.

### Implementation
For the implementation of the builder pattern, we created an interface **Ibuilder** that contains all the methods to be implemented by the concrete builders: **SimpleDateBuilder** and **IntervalDateBuilder**. We had to create two concrete classes for the dates being created by the builders: **SimpleDate** and **IntervalDate**.

<img src="images/class-Date.png" alt="Builder Pattern at Date Problem" style="height: 700px"/>

### Consequences
One of the consequences of the builder is that it doesn’t allow other objects to access the date while it’s being built and for this use case it doesn’t matter so we didn’t identify any other problem.

## Solving The Insertion Of Locations