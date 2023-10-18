package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;

/**
 * Represents a difference of two functions
 */
public class Difference extends Function {
    private Function func1;
    private Function func2;

    /**
     * Generates a new sum function
     * 
     * @param func1 one of the functions in the sum
     * @param func2 the second function in the sum
     */
    public Difference(Function func1, Function func2) {
        this.func1 = func1;
        this.func2 = func2;
    }

    @Override
    public double evaluate(double x) throws ValueNotInDomainException {
        return func1.evaluate(x) - func2.evaluate(x);
    }

    @Override
    public Function derive() {
        return new Difference(func1.derive(), func2.derive());
    }

    @Override
    public String substitute(String x) {
        return "(" + func1.substitute(x) + " - " + func2.substitute(x) + ")";
    }
}
