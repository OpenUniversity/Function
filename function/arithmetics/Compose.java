package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;

/**
 * Represents a function composed in another function, such as (3x+8)^6,
 * ln(sinx), and so on
 */
public class Compose extends FunctionArithmetic {

    /**
     * Generate a new composed function in the form of outer(inner(x))
     * 
     * @param outer the outer function
     * @param inner the inner function
     */
    public Compose(Function outer, Function inner) {
        super(outer, inner);
    }

    @Override
    public double unresolvedEvaluate(double x) throws ValueNotInDomainException {
        return left.evaluate(right.evaluate(x));
    }

    @Override
    public Function unresolvedDerive() {
        return new Product(new Compose(left.derive(), right), right.derive()); // f'(g) * g'
    }

    @Override
    public String unresolvedSubstitute(String x) {
        return left.substitute(right.substitute(x));
    }

}
