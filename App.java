import function.Constant;
import function.Function;
import function.elementary.PowerFunction;

public class App {
    public static void main(String[] args) throws Exception {
        Function f = new PowerFunction(2);
        System.out.println(f);
        System.out.println(f.derive());

        Function f1 = f.plus(Constant.of(5));
        System.out.println(f1);
        System.out.println(f1.derive());
        System.out.println(f1.derive().derive());
        System.out.println(f1.derive().derive().derive());

        Function g = new PowerFunction(1).plus(Constant.of(3));
        System.out.println(g);
        System.out.println(g.derive());
        System.out.println(g.derive().derive());

        Function fg = f1.times(g);
        System.out.println(fg);
        System.out.println(fg.derive());
        System.out.println(fg.derive().derive());
        System.out.println(fg.derive().derive().derive());
    }
}
