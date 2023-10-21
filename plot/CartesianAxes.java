package plot;

import javafx.scene.canvas.GraphicsContext;

public class CartesianAxes implements Drawable {

    public static final int STEP_SIZE_PX = 30;
    public static final int MARKING_SIZE_PX = 10;

    private double canvasWidth, canvasHeight;
    private double xAxisLocation; // the y coordinate of the x axis in the canvas
    private double yAxisLocation; // the x coordinate of the y axis in the canvas
    private int xScale; // the scale of the x markings. i.e - how many units will each step contain
    private int yScale; // the scale of the y marking, i.e - how many units will each step contain
    private double minX, maxX, minY, maxY;

    public CartesianAxes(double minX, double maxX, double minY, double maxY, double canvasWidth, double canvasHeight) {
        this.minX = Math.min(minX, 0);
        this.maxX = Math.max(maxX, 0);
        this.minY = Math.min(minY, 0);
        this.maxY = Math.max(maxY, 0);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        yAxisLocation = getAxisLocation(this.minX, this.maxX, canvasWidth); // the x coord of the y axis
        xAxisLocation = canvasHeight - getAxisLocation(this.minY, this.maxY, canvasHeight); // the y coord of the x axis
        xScale = getScale(this.minX, this.maxX, canvasWidth);
        yScale = getScale(this.minY, this.maxY, canvasHeight);
    }

    private double getAxisLocation(double minValue, double maxValue, double dimensionSize) {
        double negativePercentage = (-minValue) / (maxValue - minValue);
        return dimensionSize * negativePercentage;
    }

    private int getScale(double minValue, double maxValue, double dimSize) {
        double steps = dimSize / STEP_SIZE_PX;
        double units = (maxValue - minValue);
        return (int) Math.ceil(steps / units);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // draw axes
        gc.strokeLine(0, xAxisLocation, canvasWidth, xAxisLocation);
        gc.strokeLine(yAxisLocation, 0, yAxisLocation, canvasHeight);

        // draw x markings
        for (int currentStep = xScale; currentStep <= maxX; currentStep += xScale) {
            drawXMarking(gc, currentStep);
        }
        for (int currentX = -xScale; currentX >= minX; currentX -= xScale) {
            drawXMarking(gc, currentX);
        }

        // drawY markings
        for (int currentY = yScale; currentY <= maxY; currentY += yScale) {
            drawYMarking(gc, currentY);
        }
        for (int currentY = -yScale; currentY >= minY; currentY -= yScale) {
            drawYMarking(gc, currentY);
        }
    }

    private void drawXMarking(GraphicsContext gc, int x) {
        double xLocation = mapXCoordinate(x / xScale);
        gc.strokeLine(xLocation, xAxisLocation + MARKING_SIZE_PX / 2, xLocation, xAxisLocation - MARKING_SIZE_PX / 2);
        gc.strokeText(String.valueOf(x), xLocation, xAxisLocation + MARKING_SIZE_PX * 3 / 2);
    }

    private void drawYMarking(GraphicsContext gc, int y) {
        double yLocation = mapYCoordinate(y / yScale);
        gc.strokeLine(yAxisLocation + MARKING_SIZE_PX / 2, yLocation, yAxisLocation - MARKING_SIZE_PX / 2, yLocation);
        gc.strokeText(String.valueOf(y), yAxisLocation - MARKING_SIZE_PX * 2, yLocation);
    }

    public double mapXCoordinate(double x) {
        double pxPerUnit = canvasWidth / (maxX - minX);
        return yAxisLocation + x * xScale * pxPerUnit;
    }

    public double mapYCoordinate(double y) {
        double pxPerUnit = canvasHeight / (maxY - minY);
        return xAxisLocation - y * yScale * pxPerUnit;
    }

}
