/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * @version 1.0
 * @since 2023 -03-23
 * The type Triplet of zero.
 * This class finds a triplet of numbers that their sum is zero.
 */
public class TripletOfZero {

    /**
     * The entry point of application.
     *
     * @param args the input arguments - the order and the numbers
     */
    public static void main(String[] args) {
        //get the order from the command line
        String ord = args[0];
        //get the numbers from the command line
        String[] numbers = new String[args.length - 1];
        System.arraycopy(args, 1, numbers, 0, args.length - 1);
        int[] nums = stringsToArray(numbers);
        //use the method tripletOfZero
        int[] triplet = tripletOfZero(nums);
        //check if the triplet is empty
        if (triplet.length == 0) {
            //print -1
            System.out.println("the triplet is: -1");
        } else
            //print the triplet by the order
            if (isAsc(ord)) {
                ascTripletPrint(triplet);
            } else {
                descTripletPrint(triplet);
            }
    }

    /**
     * Strings to array int [ ].
     *
     * @param numbers the numbers as strings array
     * @return the int [ ] - the numbers as int array
     */
    public static int[] stringsToArray(String[] numbers) {
        //convert the strings to int
        int[] res = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            res[i] = Integer.parseInt(numbers[i]);
        }
        //return the array
        return res;
    }

    /**
     * Asc triplet print - print the triplet in ascending order.
     *
     * @param numbers the array of numbers
     */
    public static void ascTripletPrint(int[] numbers) {
        System.out.print("the triplet is: [");
        for (int i = 0; i < numbers.length - 1; i++) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.println(numbers[numbers.length - 1] + "]");
    }

    /**
     * Desc triplet print - print the triplet in descending order.
     *
     * @param numbers the array of numbers
     */
    public static void descTripletPrint(int[] numbers) {
        System.out.print("the triplet is: [");
        for (int i = numbers.length - 1; i > 0; i--) {
            System.out.print(numbers[i] + ", ");
        }
        System.out.println(numbers[0] + "]");
    }

    /**
     * Is asc boolean. - check if the order is ascending
     *
     * @param order the order asc or desc
     * @return the boolean - true if the order is ascending
     */
    public static boolean isAsc(String order) {
        return order.equals("asc");
    }

    /**
     * Triplet of zero int [ ] - find the first triplet of zero in the array.
     *
     * @param numbers array of numbers
     * @return the int [ ] - the triplet of zero
     */
    public static int[] tripletOfZero(int[] numbers) {
        //sort the array
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        //find the triplet
        for (int i = 0; i < numbers.length - 2; i++) {
            int invNum = -numbers[i];
            int sec = i + 1, third = numbers.length - 1;
            while (sec < third) {
                //check if the sum of the three numbers is zero
                if (numbers[sec] + numbers[third] == invNum) {
                    //return the triplet
                    return new int[]{numbers[i], numbers[sec], numbers[third]};
                } else if (numbers[sec] + numbers[third] < invNum) {
                    //skip the duplicates
                    while (sec < third && numbers[sec] == numbers[sec + 1]) {
                        sec++;
                    }
                    sec++;
                } else {
                    while (sec < third && numbers[third] == numbers[third - 1]) {
                        //skip the duplicates
                        third--;
                    }
                    third--;
                }
                //end of while
            }
            //end of for
        }
        //return -1 if there is no triplet of sum zero
        return new int[]{-1};
    }
//end of class
}
