package function.elementary;

import function.Function;
import function.LinearCombination;

/**
 * Represents a function in the form of x^power, where power is a real number
 */
public class PowerFunction extends Function {

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
        return LinearCombination.Scale(new PowerFunction(power - 1), power); // [x^n]'=nx^(n-1)
    }

    @Override
    public String substitute(String x, boolean parenthesize) {
        if (power < 0) {
            Function denominator = new PowerFunction(Math.abs(power));
            return "1 / " + denominator.substitute(x, parenthesize);
        }
        if (power == 1)
            return x;
        return x + "^" + power;
    }

    @Override
    public boolean equals(Function other) {
        if (!(other instanceof PowerFunction))
            return false;
        return this.power == ((PowerFunction) other).power;
    }

    @Override
    public Function times(Function other) {
        if (other instanceof Constant)
            return this;

        if (other instanceof PowerFunction) {
            double otherPower = ((PowerFunction) other).power;
            return new PowerFunction(power + otherPower);
        }

        return super.times(other);
    }

}
