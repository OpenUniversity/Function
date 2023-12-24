package function.arithmetics;

import function.Function;

/**
 * Represents the function int_0^x of an inner function
 * 
 * @author Itay Schechner
 */
public class Integral extends Function {

    public static final double DT = 0.01;

    private Function inner;

    /**
     * Generates a new integral function
     * 
     * @param inner
     */
    public Integral(Function inner) {
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) throws ArithmeticException {
        if (x == 0)
            return 0;
        double start = Math.min(0, x);
        double end = Math.max(0, x);
        double result = 0;
        for (double t = start; t <= end; t += DT) {
            result += inner.evaluate(t) * DT;
        }
        if (x < 0)
            return -result;
        return result;
    }

    @Override
    public Function derive() {
        return inner;
    }

    @Override
    public String substitute(String x) {
        return "∫₀ˣ" + inner.substitute("t") + "dt";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Function) && ((Function) obj).derive().equals(derive());
    }
}