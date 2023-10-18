package function.elementary;

import function.Function;
import function.ValueNotInDomainException;
import function.arithmetics.Scale;

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
    public double evaluate(double x) throws ValueNotInDomainException {
        return Math.pow(x, power);
    }

    @Override
    public Function derive() {
        if (power == 1)
            return Constant.of(1); // [x^1]' = 1
        return new Scale(power, new PowerFunction(power - 1));
    }

    @Override
    public String substitute(String x) {
        if (power < 0) {
            Function denominator = new PowerFunction(Math.abs(power));
            return "1 / " + denominator.substitute(x);
        }
        if (power == 0)
            return "1";
        if (power == 1)
            return x;
        return x + "^" + power;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PowerFunction))
            return false;
        return this.power == ((PowerFunction) obj).power;
    }

}
