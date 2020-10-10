import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a random queue, implemented using a resizing array as the underlying
// data structure.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private Item q[];
    private int n;

    // Constructs an empty random queue.
    public ResizingArrayRandomQueue() {
        q = (Item[]) new Object[2];
        n = 0;
    }

    // Returns true if this queue is empty, and false otherwise.
    public boolean isEmpty() {
        return n == 0;
    }

    // Returns the number of items in this queue.
    public int size() {
        return n;
    }

    // Adds item to the end of this queue.
    public void enqueue(Item item) {
        if (q.length == n)
            resize(n * 2);
        q[n] = item;
        ++n;
    }

    // Returns a random item from this queue.
    public Item sample() {
        return q[StdRandom.uniform(0, n)];
    }

    // Removes and returns a random item from this queue.
    public Item dequeue() {
        int i = StdRandom.uniform(0, n);
        Item item = q[i];
        q[i] = q[n - 1];
        q[n - 1] = null;
        --n;
        return item;
    }

    // Returns an independent iterator to iterate over the items in this queue in random order.
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] itr;
        private int index;

        // Constructs an iterator.
        public RandomQueueIterator() {
            itr = q;
            index = 0;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return index != n;
        }

        // Returns the next item.
        public Item next() {
            return itr[index++];
        }

        // Unsupported method.
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }

    // Resizes the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q = new ResizingArrayRandomQueue<Integer>();
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            int r = StdRandom.uniform(10000);
            q.enqueue(r);
            sum += r;
        }
        int iterSumQ = 0;
        for (int x : q) {
            iterSumQ += x;
        }
        int dequeSumQ = 0;
        while (q.size() > 0) {
            dequeSumQ += q.dequeue();
        }
        StdOut.println("sum       = " + sum);
        StdOut.println("iterSumQ  = " + iterSumQ);
        StdOut.println("dequeSumQ = " + dequeSumQ);
        StdOut.println("iterSumQ + dequeSumQ == 2 * sum? " + (iterSumQ + dequeSumQ == 2 * sum));
    }
}
