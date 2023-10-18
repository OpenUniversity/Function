import function.Function;
import function.elementary.PowerFunction;

public class App {
    public static void main(String[] args) throws Exception {
        Function f = new PowerFunction(2);
        System.out.println(f);
        f = f.derive();
        System.out.println(f);
        f = f.derive();
        System.out.println(f);
        f = f.derive();
        System.out.println(f);
    }
}
