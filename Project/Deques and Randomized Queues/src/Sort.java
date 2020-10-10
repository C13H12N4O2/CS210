import dsa.LinkedStack;

import stdlib.StdIn;
import stdlib.StdOut;

public class Sort {
    // Entry point.
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        LinkedStack carry = new LinkedStack();
        String str = StdIn.readLine();
        StdOut.println("<ctrl-d>");
        
        for (int i = 0; i != str.length(); ++i) {
            if (str.charAt(i) == ' ')
                continue;
            if (stack.isEmpty()) {
                stack.push(str.charAt(i));
            } else {
                while (!stack.isEmpty() && str.charAt(i) - 'A' > (char)stack.peek() - 'A')
                    carry.push(stack.pop());
                stack.push(str.charAt(i));
                
                while (!carry.isEmpty())
                    stack.push(carry.pop());
            }
        }
        
        while (!stack.isEmpty())
            StdOut.println(stack.pop());
    }

    // Returns true if v is less than w according to their lexicographic order, and false otherwise.
    private static boolean less(String v, String w) {
        return v.compareTo(w) < 0;
    }
}
