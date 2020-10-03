import dsa.LinkedQueue;
import stdlib.StdOut;

public class Josephus {
    // Entry point.
    public static void main(String[] args) {
        // Accept n (int) and m (int) as command-line arguments.
        int n = Integer.parseInt(args[0]), m = Integer.parseInt(args[1]);

        // Create a queue q and enqueue integers 1, 2, ..., n.
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();
        for (int i = 1; i != n + 1; ++i)
            q.enqueue(i);

        // Set i to 0. As long as q is not empty: increment i; dequeue an element (say pos); if m
        // divides i, write pos to standard output, otherwise enqueue pos to q.
        while (!q.isEmpty()) {
            for (int i = 0; i != m - 1; ++i)
                q.enqueue(q.dequeue());
            StdOut.println(q.dequeue());
        }
    }
}
