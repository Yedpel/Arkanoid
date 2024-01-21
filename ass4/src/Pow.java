/**
 * The type Pow.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Constructor of the expression.
     *
     * @param left  as the first expression
     * @param right as the second expression
     */
    public Pow(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() throws Exception {
        return Math.pow(getLeft().evaluate(), getRight().evaluate());
    }

    @Override
    public double evaluate(java.util.Map<String, Double> assignment) throws Exception {
        return Math.pow(getLeft().evaluate(assignment), getRight().evaluate(assignment));
    }

    @Override
    public String toString() {
        return "(" + getLeft().toString() + "^" + getRight().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (a^b)' = a^b * (a' * b/a + b' * log(a))
        return new Mult(new Pow(getLeft(), getRight()), new Plus(new Mult(getLeft().differentiate(var),
                new Div(getRight(), getLeft())), new Mult(getRight().differentiate(var),
                new Log(new Var("e"), getLeft()))));
    }

    @Override
    public Expression simplify() throws Exception {
        Expression left = getLeft().simplify();
        Expression right = getRight().simplify();
        // a^b = a^b
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(new Pow(left, right).evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // 0^x = 0
        } else if (left.getVariables().isEmpty() && left.evaluate() == 0) {
            return new Num(0);
            // x^0 = 1
        } else if (right.getVariables().isEmpty() && right.evaluate() == 0) {
            return new Num(1);
            // x^1 = x
        } else if (right.getVariables().isEmpty() && right.evaluate() == 1) {
            return left;
        }

        // can't simplify
        return new Pow(left, right);

    }
}
