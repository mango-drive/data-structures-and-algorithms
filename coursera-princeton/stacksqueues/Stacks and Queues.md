# Stacks and Queues

## Project
- [x] Organise princeton folder:
    - [x] algs4 available to all modules

```
learning-datastructures-algorithms/
    princeton/
    algs4.jar
    part-I/
        percolation/
        stacks-queues/
```

- [x] git init and push

## Deque
### To Do
- [x] Change file to Deque
- [x] Reorder methods in API order
- [x] Double check code
- [x] Check exceptions
- [x] Design a test object:

``` java
class Test {
    public assertEquals (testValue, value, testname) {
        // calculate test name length to calc required spaces
        if (testValue == value) // print passed
        else // print failed
}
```
Example:

```java
Test test = new Test();
test.assertEquals(size(), 0, "size returns 0 when the queue is empty");

// Console output:
// [TEST] size returns 0 when queue is empty:              passed
```
- [x] Explain why you designed this (Princeton requirement)
- [] Test all public functions in main
    - addFirst, addLast, iterator, removeFirst
- [] Write 3 sentence project description and readme. Include requirements/insights

## Randomized Queue
### To Do
- use StdRandom
- Double check exceptions and code
- [] Write 3 sentence project description and readme. Include requirements/insights

## Submit
- Submit to Princeton
- git push when done

# Further work / fun side projects
- More challenges from the book
- Dijktra's calculator algorithm
