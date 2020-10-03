import dsa.LinkedStack;
import stdlib.StdOut;

// A data type to represent a text editor buffer.
public class Buffer {
    protected LinkedStack<Character> left;  // chars left of cursor
    protected LinkedStack<Character> right; // chars right of cursor

    // Creates an empty buffer.
    public Buffer() {
        this.left = new LinkedStack<Character>();
        this.right = new LinkedStack<Character>();
    }

    // Inserts c at the cursor position.
    public void insert(char c) {
        this.left.push(c);
    }

    // Deletes and returns the character immediately ahead of the cursor.
    public char delete() {
        return this.right.pop();
    }

    // Moves the cursor k positions to the left.
    public void left(int k) {
        for (int i = 0; i != k; ++i) {
            if (this.left.isEmpty())
                break;
            
            this.right.push(this.left.pop());
        }
    }

    // Moves the cursor k positions to the right.
    public void right(int k) {
        for (int i = 0; i != k; ++i) {
            if (this.right.isEmpty())
                break;
            
            this.left.push(this.right.pop());
        }
    }

    // Returns the number of characters in this buffer.
    public int size() {
        return this.left.size() + this.right.size();
    }

    // Returns a string representation of the buffer with the "|" character (not part of the buffer)
    // at the cursor position.
    public String toString() {
        String str = new String();

        // Push chars from left into a temporary stack.
        LinkedStack<Character> temp = new LinkedStack<Character>();
        while (!this.left.isEmpty())
            temp.push(this.left.pop());

        // Append chars from temporary stack to sb.
        while (!temp.isEmpty())
            str += temp.pop();

        // Append "|" to sb.
        str += "|";

        // Append chars from right to sb.
        while (!this.right.isEmpty())
            str += this.right.pop();

        // Return the string from sb.
        return str;
    }

    // Unit tests the data type (DO NOT EDIT).
    public static void main(String[] args) {
        Buffer buf = new Buffer();
        String s = "There is grandeur in this view of life, with its several powers, having been " +
                "originally breathed into a few forms or into one; and that, whilst this planet " +
                "has gone cycling on according to the fixed law of gravity, from so simple a " +
                "beginning endless forms most beautiful and most wonderful have been, and are " +
                "being, evolved. ~ Charles Darwin, The Origin of Species";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        buf.left(buf.size());
        buf.right(97);
        s = "by the Creator ";
        for (int i = 0; i < s.length(); i++) {
            buf.insert(s.charAt(i));
        }
        buf.right(228);
        buf.delete();
        buf.insert('-');
        buf.insert('-');
        buf.left(342);
        StdOut.println(buf);
    }
}
