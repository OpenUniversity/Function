package function.elementary;

import function.Function;
import function.FunctionVector;

public class ExponentialFunction extends Function {

    private double base;

    public ExponentialFunction() {
        this(Math.E);
    }

    public ExponentialFunction(double base) {
        if (base < 0)
            throw new IllegalArgumentException("Can only pick a positive base");
        if (base == 1 || base == 0)
            throw new IllegalArgumentException("Pick a constant of " + base + " instead");
        this.base = base;
    }

    @Override
    public double evaluate(double x) throws ArithmeticException {
        return Math.pow(base, x);
    }

    @Override
    public Function derive() {
        return FunctionVector.scale(new ExponentialFunction(base), Math.log(base));
    }

    @Override
    public String substitute(String x) {
        String baseStr = String.valueOf(base);
        if (base == Math.E)
            baseStr = "e";
        return baseStr + "^" + x;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ExponentialFunction))
            return false;
        return base == ((ExponentialFunction) obj).base;
    }

}
