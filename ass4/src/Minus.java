/**
 * The type Minus.
 */
public class Minus extends BinaryExpression implements Expression {

    /**
     * Constructor of the expression.
     *
     * @param left  as the first expression
     * @param right as the second expression
     */
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() throws Exception {
        return getLeft().evaluate() - getRight().evaluate();
    }

    @Override
    public double evaluate(java.util.Map<String, Double> assignment) throws Exception {
        return getLeft().evaluate(assignment) - getRight().evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + getLeft().toString() + " - " + getRight().toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        // (a-b)' = a' - b'
        return new Minus(getLeft().differentiate(var),
                getRight().differentiate(var));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(getLeft().assign(var, expression),
                getRight().assign(var, expression));
    }

    @Override
    public Expression simplify() throws Exception {
        Expression left = getLeft().simplify();
        Expression right = getRight().simplify();
        // a - b = a - b
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(new Minus(left, right).evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else
            // 0 - x = -x
            if (left.getVariables().isEmpty() && left.evaluate() == 0) {
                return new Neg(right);
                // x - 0 = x
            } else if (right.getVariables().isEmpty() && right.evaluate() == 0) {
                return left;
                // x - x = 0
            } else if (left.toString().equals(right.toString())) {
                return new Num(0);
            }
        // can't simplify
        return new Minus(left, right);


    }

}
