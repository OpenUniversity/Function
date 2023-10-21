package function.trigonometric;

import function.Function;

public abstract class TrigFunction extends Function {

    public abstract String getName();

    @Override
    public String substitute(String x, boolean parenthesize) {
        String result = getName() + x;
        if (parenthesize)
            result = "(" + result + ")"; // for (sin x)^2, etc...
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }

}
