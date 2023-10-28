package function.elementary;

import function.Constant;
import function.Function;
import function.FunctionVector;
import function.Identity;
import function.arithmetics.Power;
import function.arithmetics.Product;
import function.arithmetics.Quotient;

/**
 * Represents the function ln(x)
 */
public class LogarithmicFunction extends Function {

    @Override
    public double evaluate(double x) throws ArithmeticException {
        if (x <= 0)
            throw new ArithmeticException("Cannot compute natural logarithm of non-positive numbers");
        return Math.log(x);
    }

    @Override
    public Function derive() {
        return Power.inverse(new Identity());
    }

    @Override
    public String substitute(String x) {
        return "ln" + x;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LogarithmicFunction;
    }

    @Override
    public Function compose(Function inner) {
        if (inner instanceof Constant)
            return Constant.of(0);
        double scalar = FunctionVector.getScalar(inner);
        if (scalar <= 0)
            throw new ArithmeticException("Cannot compose non-positive in logarithmic function");
        if (scalar != 1) {
            return Constant.of(Math.log(scalar)).plus(compose(FunctionVector.getScaledFunc(inner)));
        }
        if (inner instanceof Product) {
            Product prod = (Product) inner;
            return compose(prod.getLeft()).plus(compose(prod.getRight()));
        }
        if (inner instanceof Quotient) {
            Quotient prod = (Quotient) inner;
            return compose(prod.getLeft()).minus(compose(prod.getRight()));
        }

        return super.compose(this);
    }

}
