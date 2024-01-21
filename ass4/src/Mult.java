/**
 * The type Mult.
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * Constructor of the expression.
     *
     * @param left  as the first expression
     * @param right as the second expression
     */
    public Mult(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() throws Exception {
        return getLeft().evaluate() * getRight().evaluate();
    }

    @Override
    public double evaluate(java.util.Map<String, Double> assignment) throws Exception {
        return getLeft().evaluate(assignment) * getRight().evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(" + getLeft().toString() + " * " + getRight().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (a*b)' = a' * b + a * b'
        return new Plus(new Mult(getLeft().differentiate(var), getRight()),
                new Mult(getLeft(), getRight().differentiate(var)));
    }

    @Override
    public Expression simplify() throws Exception {
        Expression left = getLeft().simplify();
        Expression right = getRight().simplify();
        // 1 * 2 = 2
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(new Mult(left, right).evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // 0 * x = 0 || x * 0 = 0
        } else if (left.getVariables().isEmpty() && left.evaluate() == 0
                || right.getVariables().isEmpty() && right.evaluate() == 0) {
            return new Num(0);
            // x * 1 = x || 1 * x = x
        } else if (left.getVariables().isEmpty() && left.evaluate() == 1.0) {
            return right;
        } else if (right.getVariables().isEmpty() && right.evaluate() == 1.0) {
            return left;
        }


        return new Mult(left, right);


    }

}

