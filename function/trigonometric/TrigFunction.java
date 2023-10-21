package function.trigonometric;

import function.Function;

public abstract class TrigFunction extends Function {

    public abstract String getName();

    @Override
    public String substitute(String x) {
        return getName() + x;
    }

    @Override
    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }

}
