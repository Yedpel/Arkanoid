/**
 * The type Log.
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * Constructor of the expression.
     *
     * @param left  as the first expression
     * @param right as the second expression
     */
    public Log(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public double evaluate() throws Exception {
        // log(a,b) = log(b)/log(a)
        return Math.log(getRight().evaluate()) / Math.log(getLeft().evaluate());
    }

    @Override
    public double evaluate(java.util.Map<String, Double> assignment) throws Exception {
        // log(a,b) = log(b)/log(a)
        return Math.log(getRight().evaluate(assignment)) / Math.log(getLeft().evaluate(assignment));
    }

    @Override
    public String toString() {
        return "log(" + getLeft().toString() + ", " + getRight().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // log(a,b)' = (1/b) * (b'/b - a'/a)
        return new Div(new Minus(new Div(getRight().differentiate(var), getRight()),
                new Div(getLeft().differentiate(var), getLeft())), new Log(new Var("e"), getLeft()));
    }

    @Override
    public Expression simplify() throws Exception {
        Expression left = getLeft().simplify();
        Expression right = getRight().simplify();
        // log(a,b) = log(a,b)
        if (left.getVariables().isEmpty() && right.getVariables().isEmpty()) {
            try {
                return new Num(new Log(left, right).evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // log(x,x) = 1
        } else if (left.toString().equals(right.toString())) {
            return new Num(1);
            // log(1,a) = 0
        }


        // can't simplify
        return new Log(left, right);

    }


}
