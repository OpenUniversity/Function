package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;

/**
 * Represents a function composed in another function, such as (3x+8)^6,
 * ln(sinx), and so on
 */
public class Compose extends Function {

    private Function outer;
    private Function inner;

    /**
     * Generate a new composed function in the form of outer(inner(x))
     * 
     * @param outer the outer function
     * @param inner the inner function
     */
    public Compose(Function outer, Function inner) {
        this.outer = outer;
        this.inner = inner;
    }

    @Override
    public double evaluate(double x) throws ValueNotInDomainException {
        return outer.evaluate(inner.evaluate(x));
    }

    @Override
    public Function derive() {
        return new Product(new Compose(outer.derive(), inner), inner.derive()); // f'(g) * g'
    }

    @Override
    public String substitute(String x) {
        String innerX = "(" + inner.substitute(x) + ")";
        return outer.substitute(innerX);
    }

}
