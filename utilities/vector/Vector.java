package utilities.vector;

/**
 * Represents a data structure for a sum of vectors.
 * We assume there is a set, finite or infinite, of vectors that span the space
 * we refer to.
 */
public abstract class Vector<VecType> implements Iterable<Scale<VecType>> {

    /**
     * Adds a spanning vector to the sum, a number of times specified
     * 
     * @param vec         the spanning vector to add
     * @param coefficient its coefficient
     */
    public abstract void add(VecType vec, double coefficient);

    /**
     * Adds two vectors together
     * 
     * @param vector
     * @param coefficient
     */
    public void append(Vector<VecType> vector, double coefficient) {
        for (Scale<VecType> scale : vector) {
            add(scale.getVector(), coefficient * scale.getScalar());
        }
    }

    /**
     * The number of dimensions in the sum
     * 
     * @return the number of dimensions
     */
    public abstract int dimensions();

    /**
     * Calculate the coefficient of spanningVec in the vector
     * 
     * @param spanningVec the vector we refer to
     * @return its coefficiet, or 0 if vector doesn't have any
     */
    public double projection(VecType vector) {
        for (Scale<VecType> scale : this) {
            if (scale.getVector().equals(vector))
                return scale.getScalar();
        }
        return 0;
    }

    /**
     * Compare two vectors
     */
    public boolean equals(Vector<VecType> vec) {
        if (dimensions() != vec.dimensions())
            return false;
        for (Scale<VecType> scale : vec) {
            if (scale.getScalar() != vec.projection(scale.getVector()))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "[";
        for (Scale<VecType> scale : this) {
            result += scale.getScalar() + "" + scale.getVector() + ",\t";
        }
        result += "]";
        return result;
    }

}
