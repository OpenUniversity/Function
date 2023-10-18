package function.arithmetics;

import function.Function;
import function.ValueNotInDomainException;

/**
 * Represents a function multiplied by a scalar
 */
public class Scale extends Function {

    private double scalar;
    private Function func;

    /**
     * Generate a new scaled function
     * 
     * @param scalar the number to scale the function by
     * @param func   the function to scale
     */
    public Scale(double scalar, Function func) {
        init(scalar, func);
    }

    private void init(double scalar, Function func) {
        if (func instanceof Scale) {
            Scale scale = (Scale) func;
            init(scalar * scale.scalar, scale.func);
        } else {
            this.scalar = scalar;
            this.func = func;
        }
    }

    @Override
    public double evaluate(double x) throws ValueNotInDomainException {
        return scalar * func.evaluate(x);
    }

    @Override
    public Function derive() {
        return new Scale(scalar, func.derive());
    }

    @Override
    public String substitute(String x) {
        if (scalar == 1)
            return func.substitute(x);
        if (scalar == 0)
            return "0";
        return scalar + func.substitute(x);
    }

    public double getScalar() {
        return scalar;
    }

}
