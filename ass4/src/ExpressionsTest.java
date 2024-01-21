import java.util.Map;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Expressions test.
 */
public class ExpressionsTest {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        // Create the expression (2x) + (sin(4y)) + (e^x).
        Expression e1 = new Plus(new Plus(new Mult(new Num(2), new Var("x")),
                new Sin(new Mult(new Num(4), new Var("y")))), new Pow(new Var("e"), new Var("x")));
        // Print the expression.
        System.out.println(e1);
        //Print the value of the expression with (x=2,y=0.25,e=2.71)
        System.out.println(e1.evaluate(Map.of("x", 2.0, "y", 0.25, "e", 2.71)));
        //Print the differentiated expression according to x.
        System.out.println(e1.differentiate("x"));
        //Print the value of the differentiated expression according to x with the assignment above.
        System.out.println(e1.differentiate("x").evaluate(Map.of("x",
                2.0, "y", 0.25, "e", 2.71)));
        //Print the simplified differentiated expression.
        System.out.println(e1.differentiate("x").simplify());


    }
}
