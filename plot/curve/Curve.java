package plot.curve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import range.Bounded;

/**
 * Represents something that can be drawn
 */
public abstract class Curve extends Bounded implements Iterable<Point2D> {
    protected List<Point2D> points;
    private Paint paint;

    public Curve(Paint paint) {
        this.points = new ArrayList<>();
        this.paint = paint;
    }

    public Curve() {
        this(Color.BLACK);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    @Override
    public Iterator<Point2D> iterator() {
        return points.iterator();
    }

}
