import java.util.List;
import java.util.Map;

/**
 * The interface Expression.
 */
public interface Expression {
    /**
     * Evaluate double.
     *
     * @param assignment the assignment
     * @return the double value
     * @throws Exception the exception
     */
// Evaluate the expression using the variable values provided
    // in the assignment, and return the result.  If the expression
    // contains a variable which is not in the assignment, an exception
    // is thrown.
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluate double.
     *
     * @return the double value
     * @throws Exception the exception
     */
// A convenience method. Like the `evaluate(assignment)` method above,
    // but uses an empty assignment.
    double evaluate() throws Exception;

    /**
     * Gets variables.
     *
     * @return the variables
     */
// Returns a list of the variables in the expression.
    List<String> getVariables();


    // Returns a nice string representation of the expression.
    /**
     * To string string.
     *
     * @return the string
     */
    String toString();

    /**
     * Assign expression.
     *
     * @param var        the var
     * @param expression the expression
     * @return the expression
     */
// Returns a new expression in which all occurrences of the variable
    // var are replaced with the provided expression (Does not modify the
    // current expression).
    Expression assign(String var, Expression expression);

    /**
     * Differentiate expression.
     *
     * @param var the var
     * @return the expression
     */
// Returns the expression tree resulting from differentiating
    // the current expression relative to variable `var`.
    Expression differentiate(String var);

    /**
     * Simplify expression.
     *
     * @return the expression
     * @throws Exception the exception
     */
// Returned a simplified version of the current expression.
    Expression simplify() throws Exception;

}
