/**
 * The type Neg.
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * Constructor of the expression.
     *
     * @param expression as the expression
     */
    public Neg(Expression expression) {
        super(expression);
    }

    @Override
    public double evaluate() throws Exception {
        return -1 * getExpression().evaluate();
    }

    @Override
    public double evaluate(java.util.Map<String, Double> assignment) throws Exception {
        return -1 * getExpression().evaluate(assignment);
    }

    @Override
    public String toString() {
        return "(-" + getExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(getExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // (-f(x))' = -f'(x)
        return new Neg(getExpression().differentiate(var));
    }

    @Override
    public Expression simplify() throws Exception {
        Expression e = getExpression().simplify();
        // neg (a) = neg (a)
        if (e.getVariables().isEmpty()) {
            try {
                return new Num(new Neg(e).evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return new Neg(e);
    }
}
