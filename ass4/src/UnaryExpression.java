import java.util.ArrayList;
import java.util.List;

/**
 * The type Unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression expression;

    /**
     * Instantiates a new Unary expression.
     *
     * @param expression the expression
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Gets expression.
     *
     * @return the expression
     */
    protected Expression getExpression() {
        return expression;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>(getExpression().getVariables());
    }

}

