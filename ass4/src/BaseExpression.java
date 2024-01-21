import java.util.Map;
import java.util.List;


/**
 * The type Base expression.
 */
public abstract class BaseExpression implements Expression {
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }
    @Override
    public double evaluate() throws Exception {
        return 0;
    }
    @Override
    public List<String> getVariables() {
        return null;
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return null;
    }

}
