package function.elementary;

import function.Function;
import function.ValueNotInDomainException;
import function.arithmetics.Scale;

/**
 * Represents a function f(x)=1
 * DO NOT USE DIRECTLY! Always surround with a scale()
 */
public class Constant extends Function {

    public static Function of(double scalar) {
        return new Scale(scalar, new Constant());
    }

    // to prevent usage outside of this class
    private Constant() {
    }

    @Override
    public double evaluate(double x) throws ValueNotInDomainException {
        return 1;
    }

    @Override
    public Function derive() {
        return Constant.of(0);
    }

    @Override
    public String substitute(String x) {
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Constant))
            return false;
        return true;
    }

}
