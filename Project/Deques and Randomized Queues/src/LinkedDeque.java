import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdOut;
import stdlib.StdRandom;

// A data type to represent a double-ended queue (aka deque), implemented using a doubly-linked
// list as the underlying data structure.
public class LinkedDeque<Item> implements Iterable<Item> {
    private Node head, tail;
    private int size = 0;

    // Constructs an empty deque.
    public LinkedDeque() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
    }

    // Returns true if this deque is empty, and false otherwise.
    public boolean isEmpty() {
        return head.next == tail && tail.prev == head;
    }

    // Returns the number of items in this deque.
    public int size() {
        return size;
    }

    // Adds item to the front of this deque.
    public void addFirst(Item item) {
        Node node = new Node(item);
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        ++size;
    }

    // Adds item to the back of this deque.
    public void addLast(Item item) {
        Node node = new Node(item);
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
        ++size;
    }

    // Returns the item at the front of this deque.
    public Item peekFirst() {
        return head.item;
    }

    // Removes and returns the item at the front of this deque.
    public Item removeFirst() {
        Node node = head.next;
        Item item = node.item;
        head.next = node.next;
        node.next.prev = head;
        node = null;
        --size;
        return item;
    }

    // Returns the item at the back of this deque.
    public Item peekLast() {
        return tail.prev.item;
    }

    // Removes and returns the item at the back of this deque.
    public Item removeLast() {
        Node node = tail.prev;
        Item item = node.item;
        tail.prev = node.prev;
        node.prev.next = tail;
        node = null;
        --size;
        return item;
    }

    // Returns an iterator to iterate over the items in this deque from front to back.
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // A deque iterator.
    private class DequeIterator implements Iterator<Item> {
        private Node cur;

        // Constructs an iterator.
        public DequeIterator() {
            cur = head.next;
        }

        // Returns true if there are more items to iterate, and false otherwise.
        public boolean hasNext() {
            return cur != tail;
        }

        // Returns the next item.
        public Item next() {
            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        // Unsupported method.
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }

    // A data type to represent a doubly-linked list. Each node in the list stores a generic item
    // and references to the next and previous nodes in the list.
    private class Node {
        private Item item;  // the item
        private Node next;  // the next node
        private Node prev;  // the previous node
        
        public Node(Item item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its several powers, having " +
                "been originally breathed into a few forms or into one; and that, whilst this " +
                "planet has gone cycling on according to the fixed law of gravity, from so simple" +
                " a beginning endless forms most beautiful and most wonderful have been, and are " +
                "being, evolved. ~ Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        StdOut.println("Filling the deque...");
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.printf("The deque (%d characters): ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        StdOut.println("Emptying the deque...");
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println("deque.isEmpty()? " + deque.isEmpty());
    }
}
