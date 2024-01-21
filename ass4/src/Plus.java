/**
 * The type Plus.
 */
public class Plus extends BinaryExpression implements Expression {

    /**
     * Constructor of the expression.
     *
     * @param left  as the first expression
     * @param right as the second expression
     */
    public Plus(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() throws Exception {
        return getLeft().evaluate() + getRight().evaluate();
    }

    @Override
    public double evaluate(java.util.Map<String, Double> assignment) throws Exception {
        return getLeft().evaluate(assignment) + getRight().evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + getLeft().toString() + " + " + getRight().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (a+b)' = a' + b'
        return new Plus(getLeft().differentiate(var), getRight().differentiate(var));
    }

    @Override
    public Expression simplify() throws Exception {
        Expression left = getLeft().simplify();
        Expression right = getRight().simplify();
        // 1 + 2 = 3
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(new Plus(left, right).evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // 0 + x = x
        } else if (left.getVariables().isEmpty() && left.evaluate() == 0) {
            return right;
            // x + 0 = x
        } else if (right.getVariables().isEmpty() && right.evaluate() == 0) {
            return left;
        }

        return new Plus(left, right);
    }


}
