package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;

/**
 * Represents a product of two functions
 */
public class Product extends Function {

    private Function func1;
    private Function func2;

    /**
     * Generates a new sum function
     * 
     * @param func1 one of the functions in the sum
     * @param func2 the second function in the sum
     */
    public Product(Function func1, Function func2) {
        this.func1 = func1;
        this.func2 = func2;
    }

    @Override
    public double evaluate(double x) throws ValueNotInDomainException {
        return func1.evaluate(x) * func2.evaluate(x);
    }

    @Override
    public Function derive() {
        return new Sum(new Product(func1.derive(), func2), new Product(func1, func2.derive())); // f'g + fg'
    }

    @Override
    public String substitute(String x) {
        return func1.substitute(x) + " * " + func2.substitute(x);
    }

}
