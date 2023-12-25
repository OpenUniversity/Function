package function;

/**
 * Represents the identity function f(x)=x
 */
public class Identity extends Function {

    @Override
    public double evaluate(double x) throws ArithmeticException {
        return x;
    }

    @Override
    public Function derive() {
        return Constant.of(1);
    }

    @Override
    public boolean shouldAddPatentheses() {
        return false;
    }

    @Override
    public String substitute(String x) {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Identity;
    }

    @Override
    public Function compose(Function inner) {
        return inner;
    }

    @Override
    public Function integrate() {
        return FunctionVector.scale(new Identity().squared(), 0.5);
    }

}
