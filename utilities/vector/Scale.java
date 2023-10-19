package utilities.vector;

/**
 * Represents a vector scaled by a scalar
 */
public class Scale<VecType> {

    private double scalar;
    private VecType vector;

    public Scale(double scalar, VecType vector) {
        this.scalar = scalar;
        this.vector = vector;
    }

    public double getScalar() {
        return scalar;
    }

    public void setScalar(double scalar) {
        this.scalar = scalar;
    }

    public VecType getVector() {
        return vector;
    }

    @Override
    public String toString() {
        return scalar + "" + vector;
    }

}
