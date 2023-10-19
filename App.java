import function.Constant;
import function.Function;
import function.arithmetics.Compose;
import function.arithmetics.Product;
import function.elementary.PowerFunction;

public class App {
    public static void main(String[] args) throws Exception {
        Function f = new PowerFunction(2).plus(Constant.of(5));
        System.out.println(f);
        System.out.println(f.derive());
        System.out.println(f.derive().derive());
        System.out.println(f.derive().derive().derive());

        Function g = new PowerFunction(1).plus(Constant.of(3));
        System.out.println(g);
        System.out.println(g.derive());
        System.out.println(g.derive().derive());

        Function fg = f.times(g);
        System.out.println(fg);
        System.out.println(fg.derive());
        System.out.println(fg.derive().derive());
        System.out.println(fg.derive().derive().derive());

        Function fg2 = new Product(f, g);
        System.out.println(fg2);
        System.out.println(fg2.derive());

        Function fofg = new Compose(f, g);
        System.out.println(fofg);
        System.out.println(fofg.derive());

        Function foverg = f.div(g);
        System.out.println(foverg);
        System.out.println(foverg.derive());
    }
}
