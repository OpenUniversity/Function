package plot.axes;

import javafx.scene.canvas.GraphicsContext;
import plot.Drawable;

public class CartesianAxes implements Drawable {

    private XAxis xAxis;
    private YAxis yAxis;

    public CartesianAxes(double minX, double maxX, double minY, double maxY, double width, double height) {
        this.xAxis = new XAxis(width, minX, maxX);
        this.yAxis = new YAxis(height, minY, maxY);
        this.xAxis.setLocation(yAxis.getOriginLocationPx());
        this.yAxis.setLocation(xAxis.getOriginLocationPx());
    }

    @Override
    public void draw(GraphicsContext gc) {
        this.xAxis.draw(gc);
        this.yAxis.draw(gc);
    }

}
