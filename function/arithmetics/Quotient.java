package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;
import function.elementary.PowerFunction;

/**
 * Represents a ratio of two functions
 */
public class Quotient extends Function {

    private Function numerator;
    private Function denominator;

    /**
     * Creates a new Quotient function
     * 
     * @param numerator   the numerator
     * @param denominator the denominator
     */
    public Quotient(Function numerator, Function denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public double evaluate(double x) throws ValueNotInDomainException {
        double denominatorValue = denominator.evaluate(x);
        if (denominatorValue == 0)
            throw new ValueNotInDomainException(this, x);
        return numerator.evaluate(x) / denominatorValue;
    }

    @Override
    public Function derive() {
        // we can derive f/g by deriving the product of f and 1/g
        Function inverseDenominator = new Compose(new PowerFunction(-1), denominator);
        Function quotient = new Product(numerator, inverseDenominator);
        return quotient.derive();
    }

    @Override
    public String substitute(String x) {
        return numerator.substitute(x) + " / " + denominator.substitute(x);
    }

}
