package stacksqueues;
import java.util.NoSuchElementException;
import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int N;

    // Generic node class to maintain items in a linked list
    private class Node {
        Item item;
        Node next, prev;

        public Node (Item value) {
            this.item = value;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return number of elements in the deque
    public int size() {
        return N;
    }

    // add item to the front
    public void addFirst (Item value) {
        Node node = new Node(value);
        if (isEmpty()) {
            last = node;
        } else {
            first.prev = node;
            node.next = first;
        }
        first = node;
        N++;
    }

    // add item to the back
    public void addLast (Item value) {
        Node node = new Node(value);
        if (N == 0) first = node;
        else {
            last.next = node;
            node.prev = last;
        }
        last = node;
        N++;
    }

    // remove and return the first item
    public Item removeFirst () {
        if (isEmpty()) { throw new NoSuchElementException(); }

        Node node = first;
        if (size() > 1) { 
            first.next.prev = null;
            first = first.next;
        } else if (size() == 1) {
            last = null;
            first = null;
        }
        node.next = null;
        N--;
        return node.item;
    }

    // remove and return the last item
    public Item removeLast() {
        if (isEmpty()) { throw new NoSuchElementException(); }

        Node node = last;
        if (size() > 1) {
            last.prev.next = null;
            last = last.prev;
        } else if (size() == 1) {
            last = null;
            first = null;
        }
        node.prev = null;
        N--;
        return node.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    /**
     * private inner class that defines the DequeIterator
     * and implements hasNext(), remove() and next()
     */
    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            this.current = first;
        }

        public boolean hasNext() { return current != null;                      }
        public void remove()     { throw new UnsupportedOperationException();   }

        public Item next() {
            if (!this.hasNext()) { throw new NoSuchElementException();          }
            else {
                Node node = current;
                current = current.next;
                return node.item;
            }
        }
    }

    
    public static void main(String[] args) {
        System.out.println("\nDEQUE TESTING \n");

        Deque<Integer> deque = new Deque<Integer>();

        TestPrinter<Integer> printerInt = new TestPrinter<Integer>();
        TestPrinter<Boolean> printerBool = new TestPrinter<Boolean>();

        printerBool.test(deque.isEmpty(), true, "isEmpty() returns true when empty");
        printerInt.test(deque.size(), 0, "size() returns 0 when empty");

        try {
            int item = deque.removeFirst();
        } catch (Exception e) {
            System.out.println("removeFirst() Exception when empty test     passed");
        }

        try {
            int i = deque.removeLast();
        } catch (Exception e) {
            System.out.println("removeLast() Exception when empty test      passed");
        }

        deque.addFirst(1);
        printerInt.test(deque.removeFirst(), 1, "addFirst, removeFirst ");
        deque.addLast(1);
        printerInt.test(deque.removeLast(), 1, "addLast, removeLast ");


        int[] a = {1, 2, 3, 4, 5};
        for (int i : a) deque.addLast(i);

        boolean sequenceEqual = true;
        int i = 0;
        for (int item : deque) {
            if (item != a[i]) sequenceEqual = false;
            i++;
        }
        printerBool.test(sequenceEqual, true, "iterator and deque sequence");

        Iterator<Integer> it = deque.iterator();
        printerInt.test(it.next(), deque.first.item, "it.next() starts from first ");

        boolean thrown = false;
        for (i = 0; i < deque.size() + 1; i++) {
            try {
                it.next();
            } catch (Exception e) {
                thrown = true;
            }
        }

        printerBool.test(it.hasNext(), false, "it.hasNext() false when done ");
        printerBool.test(thrown, true, "it.next() throws when !hasNext() ");

        System.out.println("\nDEQUE TESTING ENDED \n");
    }

}

final class TestPrinter<T> {
    private int totalStringLength = 50;
    
    public void test(T testValue, T expected, String testName) {
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append(testName);

        int numSpaces = totalStringLength - testName.length() - "passed".length();
        for (int i = 0; i < numSpaces; i++) sBuffer.append(" ");

        if (testValue == expected) sBuffer.append("passed");
        else {
            sBuffer.append(String.format("failed \n value: %d, expected: %d", testValue, expected));
        }

        System.out.println(sBuffer.toString());
    }
}