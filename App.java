import function.Function;
import function.arithmetics.Quotient;
import function.arithmetics.Sum;
import function.elementary.Constant;
import function.elementary.PowerFunction;

public class App {
    public static void main(String[] args) throws Exception {
        Function f = new PowerFunction(2);
        System.out.println(f);
        System.out.println(f.derive());

        Function f1 = new Sum(f, Constant.of(5));
        System.out.println(f1);
        System.out.println(f1.derive());
        System.out.println(f1.derive().derive());
        System.out.println(f1.derive().derive().derive());

        Function g = new Sum(new PowerFunction(1), Constant.of(3));
        System.out.println(g);
        System.out.println(g.derive());
        System.out.println(g.derive().derive());

        Function h = new Quotient(f, g);
        System.out.println(h);
        System.out.println(h.derive());

        Function p = (new PowerFunction(2)).plus(f);
        System.out.println(p);
    }
}
