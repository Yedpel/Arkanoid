import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Var.
 */
public class Var implements Expression {
    private final String var;

    /**
     * Constructor for Var.
     *
     * @param var as var
     */
    public Var(String var) {
        this.var = var;
    }

    @Override
    // Evaluate the expression using the variable values provided
    // in the assignment, and return the result.  If the expression
    // contains a variable which is not in the assignment, an exception
    // is thrown.
    public double evaluate(Map<String, Double> assignment) throws Exception {
        Double value = assignment.get(var);
        if (value == null) {
            throw new Exception("Error! the value is not in the " + "assignment");
        } else {
            return value;
        }
    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception("Error! the value is not in the " + "assignment");
    }

    @Override
    public List<String> getVariables() {
        List<String> stringList = new ArrayList<>();
        stringList.add(var);
        return stringList;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    // Returns a new expression in which all occurrences of the variable
    // var are replaced with the provided expression (Does not modify the
    // current expression).
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.var)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression differentiate(String var) {
        if (var.equals(this.var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    @Override
    public Expression simplify() {
        return this;
    }


}
