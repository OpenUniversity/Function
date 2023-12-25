package function.elementary;

import function.Constant;
import function.Function;
import function.Identity;
import function.arithmetics.Power;

/**
 * Represents the function e to the power of f(x)
 */
public class ExponentialFunction extends Power {

    public ExponentialFunction() {
        super(Constant.of(Math.E), new Identity());
    }

    @Override
    public String substitute(String x) {
        return "e^" + x;
    }

    @Override
    public Function integrate() {
        return this;
    }

}
