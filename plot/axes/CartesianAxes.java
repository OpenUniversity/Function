package plot.axes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import plot.Drawable;
import plot.bounded.Boundable;

public class CartesianAxes implements Drawable {

    private XAxis xAxis;
    private YAxis yAxis;

    public CartesianAxes(double minX, double maxX, double minY, double maxY, double width, double height) {
        this.xAxis = new XAxis(width, minX, maxX);
        this.yAxis = new YAxis(height, minY, maxY);
        this.xAxis.setLocation(yAxis.getOriginLocationPx());
        this.yAxis.setLocation(xAxis.getOriginLocationPx());
    }

    public CartesianAxes(Boundable bounds, double width, double height) {
        this(bounds.getStartX(), bounds.getEndX(), bounds.getStartY(), bounds.getEndY(), width, height);
    }

    @Override
    public void draw(GraphicsContext gc) {
        this.xAxis.draw(gc);
        this.yAxis.draw(gc);
    }

    public Point2D unitsToPx(Point2D point) {
        double x = xAxis.unitsToPx(point.getX());
        double y = yAxis.unitsToPx(point.getY());
        return new Point2D(x, y);
    }

}
