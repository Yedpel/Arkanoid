/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * @version 1.0
 * @since 2023-03-23
 * The type Pow.
 * This class calculates the power of a number.
 */
public class Pow {
    private static final int MIN_POW = 1;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // get the base and exponent from the command line
        long base = Integer.parseInt(args[0]);
        long power = Integer.parseInt(args[1]);
        // calculate x^n using a recursive definition
        long recCalc = powRecursive(base, power);
        System.out.println("recursive: " + recCalc);
        // calculate x^n using an iterative definition
        long iterCalc = powIter(base, power);
        System.out.println("iterative: " + iterCalc);
    }

    /**
     * Pow recursive long.
     *
     * @param base the base
     * @param exp  the exponent
     * @return the result of the power
     */
    public static long powRecursive(long base, long exp) {
        // base case
        if (exp == 0) {
            return MIN_POW;
        }
        // calculate x^(n-1) and then multiply by x
        return base * powRecursive(base, (exp - 1));
    }

    /**
     * Pow iter long.
     *
     * @param base the base
     * @param exp  the exponent
     * @return the result of the power
     */
    public static long powIter(long base, long exp) {
        // initialize the result
        long res = MIN_POW;
        // calculate x^n by multiplying x by itself n times
        for (int i = 0; i < exp; i++) {
            res = res * base;
        }
        // return the result
        return res;
    }
    //end of class
}
