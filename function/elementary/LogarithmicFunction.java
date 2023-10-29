package function.elementary;

import function.FunctionVector;
import function.NaturalLogFunction;

/**
 * Represents the function log_base(x).
 * Since log_a(x) = ln(x) / ln(a), we extend the FunctionVector class
 */
public class LogarithmicFunction extends FunctionVector {

    private double base;

    public LogarithmicFunction(double base) {
        super();
        this.base = base;
        if (base <= 0 || base == 1)
            throw new ArithmeticException("Can't compute log of non-positive base or base 1");
        add(new NaturalLogFunction(), 1.0 / Math.log(base));
    }

    public double getBase() {
        return base;
    }

    @Override
    public String substitute(String x) {
        return "log_" + base + " " + x;
    }

}
