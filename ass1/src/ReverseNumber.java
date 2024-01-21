/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * @version 1.0
 * @since 2023-03-23
 * The type Reverse number.
 * This class calculates the reverse number.
 */
public class ReverseNumber {
    private static final int ONE_DIGIT = 10;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //get the number from the command line
        int number = Integer.parseInt(args[0]);
        //print the reverse number
        System.out.println("reverse number: " + reverseNum(number));
    }

    /**
     * Reverse number int.
     *
     * @param number the number
     * @return the reverse number
     */
    public static int reverseNum(int number) {
        long revers = 0;
        //calculate the reverse number
        while (number != 0) {
            int dig = number % ONE_DIGIT;
            revers = dig + revers * ONE_DIGIT;
            number = number / ONE_DIGIT;
        }
        //check if the reverse number is out of range of int
        if (revers > Integer.MAX_VALUE || revers < Integer.MIN_VALUE) {
            return 0;
        }
        //return the reverse number
        return (int) revers;
    }
    //end of class
}
