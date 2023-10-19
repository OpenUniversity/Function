package function.arithmetics;

import function.Function;

/**
 * Represents a composition of functions, such as (sinx)^2,
 */
public class Compose extends FunctionArithmetic {

    public Compose(Function left, Function right) {
        super(left, right);
    }

    @Override
    public double evaluate(double x) throws ArithmeticException {
        return left.evaluate(right.evaluate(x));
    }

    @Override
    public Function derive() {
        // based on formula [f(g)]'=f'(g) * g'
        return left.derive().compose(right).times(right.derive());
    }

    @Override
    public String substitute(String x, boolean parenthesize) {
        String innerSub = right.substitute(x, true);
        return left.substitute(innerSub, parenthesize);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Compose))
            return false;
        Compose comp = (Compose) obj;
        return left.equals(comp.left) && right.equals(comp.right);
    }

}
