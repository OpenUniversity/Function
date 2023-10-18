package function;

/**
 * Will throw when trying to evaluate a function with a value outside of its
 * domain
 */
public class ValueNotInDomainException extends Exception {
    public ValueNotInDomainException() {
        super();
    }

    public ValueNotInDomainException(Function func, double value) {
        super("Value " + value + " is not in the function " + func + " domain");
    }
}
