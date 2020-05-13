package stacksqueues;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() { return N == 0; }

    // return number of items in the queue
    public int size() {return N;}

    // add an item to the queue
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int i = randomizedPosition();
        swap(i, N-1);
        Item item = a[--N];
        if (N < a.length / 4) resize(a.length / 2);
        return item;
    }

    // return a random item
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return a[randomizedPosition()];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() { return i < N; }
        public Item next() { 
            if (i >= N) throw new java.util.NoSuchElementException();
            i++;
            return sample(); 
        }
        public void remove() { throw new UnsupportedOperationException();}
    }

    // resize the underlying array to a specified capacity
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < a.length; i++) copy[i] = a[i];
        a = copy;
    }

    // Swap items stored at indices i and j
    private void swap(int i, int j) {
        Item temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Generate a random int between 0 inclusive and N exclusive.
    private int randomizedPosition() {
        return StdRandom.uniform(N);
    }

    public static void main(String[] args) {

        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        /** Empty queue tests */
        if (queue.isEmpty()) System.out.println("isEmpty() empty queue test passed");
        else System.out.println("isEmpty() empty queue test failed");

        try {
            queue.enqueue(null);
            System.out.println("equeue null test failed");
        } catch (Exception e) {
            System.out.println("equeue null test passed");
        }

        try {
            int i = queue.dequeue();
            System.out.println("dequeue empty test failed");
        } catch (Exception e) {
            System.out.println("dequeue empty test passed");
        }

        try {
            for (int i : queue) System.out.println(i);
            System.out.println("iterator empty test failed");
        } catch (Exception e) {
            System.out.println("iterator empty test passed");
        }
        
        /* Enqueue tests */
        // Create a list of ints to add to the queue
        int[] list = {1, 2, 3, 4, 5};
        for (int i : list) queue.enqueue(i);
        if (queue.size() == list.length) 
            System.out.println("size test passed");
        else System.out.println("size test failed");

        if (queue.isEmpty()) System.out.println("isEmpty() filled queue test failed");
        else System.out.println("isEmpty() filled queue test passed");
        
        System.out.println("Randomised iterator test 1");
        for (int i : queue) System.out.println(i); 

        System.out.println("Randomised iterator test 2 - decoupled from test 1");
        for (int i : queue) System.out.println(i); 

        /* dequeue tests */
        System.out.println("Dequeue test");
        int size = queue.size();
        int item = queue.dequeue();

        System.out.printf("Dequeued %d \n", item);

        System.out.printf("Size before/after: %d, %d \n", size, queue.size());
        if (size == queue.size() + 1) System.out.println("Dequeue size test passed");
        else System.out.println("Dequeue size test failed");

        System.out.println("Sample test");
        for(int i = 0; i < list.length * 5; i++) System.out.println(queue.sample());

        /** Iterator tests */
        System.out.println("iterator test");
        Iterator<Integer> it = queue.iterator();
        System.out.println(it.hasNext());
        System.out.println(it.next());
        try {
            it.remove();
            System.out.println("Iterator remove unsupported test failed");
        } catch (Exception e) {
            System.out.println("Iterator remove unsupported test passed");

        }
    }
}