/**
 * The type Binary expression.
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructor of the expression.
     *
     * @param left  as the first expression
     * @param right as the second expression
     */
    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Gets left.
     *
     * @return the left
     */
    protected Expression getLeft() {
        return left;
    }

    /**
     * Gets right.
     *
     * @return the right
     */
    protected Expression getRight() {
        return right;
    }

    @Override
    public java.util.List<String> getVariables() {
        java.util.List<String> list =
                new java.util.ArrayList<>(getLeft().getVariables());
        list.addAll(getRight().getVariables());
        return list;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return null;
    }

}
