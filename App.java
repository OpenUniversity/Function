import function.Constant;
import function.Function;
import function.arithmetics.Compose;
import function.arithmetics.Product;
import function.elementary.PowerFunction;
import utilities.vector.ArrayListVector;
import utilities.vector.Vector;

public class App {
    public static void main(String[] args) throws Exception {
        functionTest();
    }

    public static void vectorTest() {
        Vector<Character> vec1 = new ArrayListVector<>();
        System.out.println(vec1);
        vec1.add('a', 1);
        System.out.println(vec1);
        vec1.add('b', 2);
        System.out.println(vec1);
        vec1.add('a', 2);
        System.out.println(vec1);

        Vector<Character> vec2 = new ArrayListVector<>();
        vec2.add('a', 2);
        System.out.println(vec2);
        vec2.append(vec1, 1);
        System.out.println(vec2);
    }

    public static void functionTest() {
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
