/**
 * The type Sin.
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * Constructor of the expression.
     *
     * @param expression as the expression
     */
    public Sin(Expression expression) {
        super(expression);
    }

    @Override
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians(getExpression().evaluate()));
    }

    @Override
    public double evaluate(java.util.Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(getExpression().evaluate(assignment)));
    }

    @Override
    public String toString() {
        return "sin(" + getExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(getExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // sin(f(x))' = cos(f(x)) * f'(x)
        return new Mult(new Cos(getExpression()), getExpression().differentiate(var));
    }

    @Override
    public Expression simplify() throws Exception {
        Expression e = getExpression().simplify();
        // sin (a) = sin (a)
        if (e.getVariables().isEmpty()) {
            try {
                return new Num(new Sin(e).evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //can't simplify
        return new Sin(e);
    }

}
