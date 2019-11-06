
# Testing

The assignment specification requires that every public method in ```Deque``` and ```RandomizedQueue``` classes should be called for testing in their respective ```main()``` methods.

While testing ```RandomizedQueue```, the code required to assert each test and print out a pass/fail statement made the main method clumbersome and repetitive. 

The specification also imposes a constraint on imports: the only external library that is permitted is algs4.jar (Princeton's algorithm library). Additionally, the only files that could be submitted are ```Deque```, ```RandomizedQueue``` and ```Permutation```. This meant that for testing, the use of a test suite such as JUnit was not permitted and any test code would have to be repeated in each class.

The problem is that we have to create a new TestPrinter for each type:
 
 ```java
TestPrinter<Integer> printerInt = new TestPrinter<Integer>();
TestPrinter<Boolean> printerBool = new TestPrinter<Boolean>();
```

In the next assignment, I will investigate the use of a Factory design pattern for this purpose.

Exceptions are still tested with a try/catch block.
