/**
 * The type Cos.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * Constructor of the expression.
     *
     * @param expression as the expression
     */
    public Cos(Expression expression) {
        super(expression);
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(getExpression().evaluate()));
    }

    @Override
    public double evaluate(java.util.Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(getExpression().evaluate(assignment)));
    }

    @Override
    public String toString() {
        return "cos(" + getExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(getExpression().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        // cos(f(x))' = -sin(f(x)) * f'(x)
        return new Mult(new Neg(new Sin(getExpression())), getExpression().differentiate(var));
    }

    @Override
    public Expression simplify() throws Exception {
        Expression e = getExpression().simplify();
        // cos (a) = cos (a)
        if (e.getVariables().isEmpty()) {
            try {
                return new Num(new Cos(e).evaluate());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //can't simplify
        return new Cos(e);
    }
}
