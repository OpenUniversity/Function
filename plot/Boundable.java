package plot;

/**
 * Represents a rectangle subset of the two dimentional plane
 */
public interface Boundable {

    public void calculateBounds();

    public double getStartX();

    public double getEndX();

    public double getStartY();

    public double getEndY();

}
