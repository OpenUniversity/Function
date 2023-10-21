import function.Constant;
import function.Function;
import function.FunctionVector;
import function.arithmetics.Compose;
import function.arithmetics.Product;
import function.elementary.PowerFunction;
import function.trigonometric.Sine;
import utilities.vector.MapVector;
import utilities.vector.Vector;

public class App {
    public static void main(String[] args) throws Exception {
        functionTest();
    }

    public static void vectorTest() {
        Vector<Character> vec1 = new MapVector<>();
        System.out.println(vec1);
        vec1.add('a', 1);
        System.out.println(vec1);
        vec1.add('b', 2);
        System.out.println(vec1);
        vec1.add('a', 2);
        System.out.println(vec1);

        Vector<Character> vec2 = new MapVector<>();
        vec2.add('a', -3);
        System.out.println(vec2);
        vec2.append(vec1, 1);
        System.out.println(vec2);
    }

    public static void functionTest() {
        System.out.println("\n\n\n f=x^2+5");
        Function f = new PowerFunction(2).plus(Constant.of(5));
        System.out.println(f);
        System.out.println(f.derive());
        System.out.println(f.derive().derive());
        System.out.println(f.derive().derive().derive());

        System.out.println("\n\n\n g=x+3");
        Function g = new PowerFunction(1).plus(Constant.of(3));
        System.out.println(g);
        System.out.println(g.derive());
        System.out.println(g.derive().derive());

        System.out.println("\n\n\n fg");
        Function fg = f.times(g);
        System.out.println(fg);
        System.out.println(fg.derive());
        System.out.println(fg.derive().derive());
        System.out.println(fg.derive().derive().derive());

        System.out.println("\n\n\n Unsimplified fg");
        Function fg2 = new Product(f, g);
        System.out.println(fg2);
        System.out.println(fg2.derive());

        System.out.println("\n\n\n fg2f");
        Function fg2f = fg2.times(f);
        System.out.println(fg2f);
        System.out.println(fg2f.derive());

        System.out.println("\n\n\n f compose g unsimplified");
        Function fofg = new Compose(f, g);
        System.out.println(fofg);
        System.out.println(fofg.derive());

        System.out.println("\n\n\n g compose f unsimplified");
        Function goff = new Compose(g, f);
        System.out.println(goff);
        System.out.println(goff.derive());

        System.out.println("\n\n\n g compose f");
        Function goff2 = g.compose(f);
        System.out.println(goff2);
        System.out.println(goff2.derive());

        System.out.println("\n\n\n f/g");
        Function foverg = f.div(g);
        System.out.println(foverg);
        System.out.println(foverg.derive());

        System.out.println("\n\n\n sin(-x^2)");
        Function sineOfNegF = new Sine().compose(FunctionVector.scale(PowerFunction.of(2), -1));
        System.out.println(sineOfNegF);
        System.out.println(sineOfNegF.derive());
    }
}
