/**
 * The type Div.
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * Constructor of the expression.
     *
     * @param left  as the first expression
     * @param right as the second expression
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() throws Exception {
        return getLeft().evaluate() / getRight().evaluate();
    }

    @Override
    public double evaluate(java.util.Map<String, Double> assignment) throws Exception {
        return getLeft().evaluate(assignment) / getRight().evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + getLeft().toString() + " / " + getRight().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (a/b)' = (a'*b - a*b') / b^2
        return new Div(new Minus(new Mult(getLeft().differentiate(var), getRight()),
                new Mult(getLeft(), getRight().differentiate(var))),
                new Pow(getRight(), new Num(2)));
    }

    @Override
    public Expression simplify() throws Exception {
        Expression left = getLeft().simplify();
        Expression right = getRight().simplify();
        // a / b = a / b
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(new Div(left, right).evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // 0 / x = 0
        if (left.getVariables().isEmpty() && left.evaluate() == 0) {
            return new Num(0);
        }
        // x / x = 1
        if (left.toString().equals(right.toString())) {
            return new Num(1);
        }
        // x / 1 = x
        if (right.getVariables().isEmpty() && right.evaluate() == 1) {
            return left;
        }

        //cannot simplify
        return new Div(left, right);
    }

}
