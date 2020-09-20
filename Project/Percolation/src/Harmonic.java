import stdlib.StdOut;

public class Harmonic {
    // Entry point.
    public static void main(String[] args) {
        // Accept n (int) as command-line argument.
        int n = Integer.parseInt(args[0]);

        // Set total to the rational number 0.
        double total = 0;
        int den = 1;
        int gcd = 1;

        // For each 1 <= i <= n, add the rational term 1 / i to total.
        for (int i = 1; i <= n; ++i) {
            total += (double) 1/i;
            den *= i;
        }
        // Write total to standard output.
        int num = (int) Math.round(total * 100 * den) / 100;
        for (int i = 1; i <= den && i <= num; ++i)
            if (den % i == 0 && num % i == 0)
                gcd = i;
        StdOut.println(num / gcd + "/" + den / gcd);
    }
}
