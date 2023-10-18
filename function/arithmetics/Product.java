package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;

/**
 * Represents a product of two functions
 */
public class Product extends FunctionArithmetic {

    /**
     * Generates a new sum function
     * 
     * @param left  one of the functions in the sum
     * @param right the second function in the sum
     */
    public Product(Function left, Function right) {
        super(left, right);
    }

    @Override
    public double unresolvedEvaluate(double x) throws ValueNotInDomainException {
        return left.evaluate(x) * right.evaluate(x);
    }

    @Override
    public Function unresolvedDerive() {
        return new Sum(new Product(left.derive(), right), new Product(left, right.derive())); // f'g + fg'
    }

    @Override
    public String unresolvedSubstitute(String x) {
        return left.substitute(x) + " * " + right.substitute(x);
    }

}
