package plot;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents something that can be drawn on a canvas
 */
public interface Drawable {
    default void draw(GraphicsContext gc, Paint p) {
        gc.setStroke(p);
        draw(gc);
    }

    public void draw(GraphicsContext gc);
}
