package game;

/**
 * The type Counter.
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     */
    Counter() {
        this.counter = 0;
    }

    /**
     * Increase.
     *
     * @param number the number of increase
     */
// add number to current count.
    void increase(int number) {
        this.counter += number;
    }

    /**
     * Decrease.
     *
     * @param number the number of decrease
     */
// subtract number from current count.
    void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Get value int.
     *
     * @return the value of the counter
     */
// get current count.
    public int getValue() {
        return this.counter;
    }
}