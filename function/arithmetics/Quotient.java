package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;
import function.elementary.PowerFunction;

/**
 * Represents a ratio of two functions
 */
public class Quotient extends FunctionArithmetic {

    /**
     * Creates a new Quotient function
     * 
     * @param numerator   the numerator
     * @param denominator the denominator
     */
    public Quotient(Function numerator, Function denominator) {
        super(numerator, denominator);
    }

    @Override
    public double unresolvedEvaluate(double x) throws ValueNotInDomainException {
        double denominatorValue = right.evaluate(x);
        if (denominatorValue == 0)
            throw new ValueNotInDomainException(this, x);
        return left.evaluate(x) / denominatorValue;
    }

    @Override
    public Function unresolvedDerive() {
        // we can derive f/g by deriving the product of f and 1/g
        Function inverseDenominator = new Compose(new PowerFunction(-1), right);
        Function quotient = new Product(left, inverseDenominator);
        return quotient.derive();
    }

    @Override
    public String unresolvedSubstitute(String x) {
        return left.substitute(x) + " / " + right.substitute(x);
    }

}
