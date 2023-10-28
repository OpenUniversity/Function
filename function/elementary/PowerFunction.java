package function.elementary;

import function.Constant;
import function.Function;
import function.FunctionVector;
import function.arithmetics.Product;
import function.arithmetics.Quotient;

/**
 * Represents a function in the form of x^power, where power is a real number
 */
public class PowerFunction extends Function {

    public static final Function IDENTITY = new PowerFunction(1);

    private double power;

    /**
     * Generate a new power function
     * 
     * @param power the exponent of the function
     */
    public PowerFunction(double power) {
        if (power == 0)
            throw new IllegalArgumentException("For power=0, use the constant function");
        this.power = power;
    }

    public static Function of(double power) {
        if (power == 0)
            return Constant.of(1);
        else
            return new PowerFunction(power);
    }

    @Override
    public double evaluate(double x) {
        if (power < 0 && x == 0)
            throw new ArithmeticException("Can't divide by 0");
        return Math.pow(x, power);
    }

    @Override
    public Function derive() {
        if (power == 1)
            return Constant.of(1); // [x^1]' = 1
        return FunctionVector.scale(new PowerFunction(power - 1), power); // [x^n]'=nx^(n-1)
    }

    @Override
    public String substitute(String x) {
        if (power < 0) {
            Function denominator = new PowerFunction(Math.abs(power));
            return "1 / " + denominator.substitute(x);
        }
        if (power == 1)
            return x;
        return x + "^" + power;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PowerFunction))
            return false;
        return this.power == ((PowerFunction) other).power;
    }

    @Override
    public Function times(Function other) {

        if (other instanceof PowerFunction) {
            double otherPower = ((PowerFunction) other).power;
            return PowerFunction.of(power + otherPower);
        }

        return super.times(other);
    }

    @Override
    public Function div(Function other) {

        if (other instanceof PowerFunction) {
            double otherPower = ((PowerFunction) other).power;
            return PowerFunction.of(power - otherPower);
        }

        return super.div(other);
    }

    @Override
    public Function compose(Function inner) {

        // The identity function composing any function will give us the identity
        // function
        if (power == 1)
            return inner;

        // apply (x^a)^b=x^(ab)
        if (inner instanceof PowerFunction) {
            double innerPower = ((PowerFunction) inner).power;
            return PowerFunction.of(power * innerPower);
        }

        // apply (af)^n=a^nf^n
        double scalar = FunctionVector.getScalar(inner);
        if (scalar != 1)
            return FunctionVector.scale(compose(FunctionVector.scale(inner, 1.0 / scalar)), Math.pow(scalar, power));

        // apply (a*b)^n=a^n*b^n
        if (inner instanceof Product) {
            Product prod = (Product) inner;
            return compose(prod.getLeft()).times(compose(prod.getRight()));
        }

        // apply (a/b)^n = a^n / b^n
        if (inner instanceof Quotient) {
            Quotient quo = (Quotient) inner;
            return compose(quo.getLeft()).div(compose(quo.getRight()));
        }

        return super.compose(inner);
    }

}
