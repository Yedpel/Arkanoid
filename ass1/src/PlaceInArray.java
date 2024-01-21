/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * @version 1.0
 * @since 2023 -03-23
 * The type Place in array.
 * This class finds the start and end index of a number in an array.
 */
public class PlaceInArray {


    private static final int OUT_OF_RANGE = -1;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //get the number to find from the command line
        int find = Integer.parseInt(args[args.length - 1]);
        //get the numbers from the command line
        int[] numbers = new int[args.length - 1];
        for (int j = 0; j < args.length - 1; j++) {
            numbers[j] = Integer.parseInt(args[j]);
        }
        //use the method placeInArray
        placeInArray(numbers, find);
    }

    /**
     * Place in array int [ ].
     *
     * @param numbers the numbers
     * @param find    - the number to find
     */
    public static void placeInArray(int[] numbers, int find) {
        int first = OUT_OF_RANGE;
        int last = OUT_OF_RANGE;
        for (int i = 0; i < numbers.length; i++) {
            //check if the number is in the array
            if (numbers[i] == find) {
                //check if it is the first time the number is in the array
                if (first == OUT_OF_RANGE) {
                    first = i;
                }
                //update the last index
                last = i;
            }
        }
        //print the start and end index of the number
        System.out.println(find + " start in " + first + " and end in " + last);
    }
    //end of class
}

