package plot.axes;

public class Axis {

    public static final int MIN_PX_PER_STEP = 30;
    public static final int MARK_SIZE_PX = 10;
    public static final int STEP_MARGIN = 2;

    protected double pxSize;
    protected double minValue, maxValue;
    protected int unitsPerStep;
    protected double pxPerStep;
    protected double originLocationPx;

    public Axis(double pxSize, double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.pxSize = pxSize;
        minValue = Math.min(0, minValue);
        maxValue = Math.max(0, maxValue);
        double unitSpan = Math.ceil(maxValue - minValue); // this is actually this min value
        double negativeShare = (-minValue) / unitSpan;
        this.originLocationPx = pxSize * negativeShare;

        // define the step size: This should be an integer number of units per step,
        // such that an integer number of steps, multiplied by a number
        // of pixels, would be greater than the span value plus a number of steps
        double numOfSteps = pxSize / MIN_PX_PER_STEP - STEP_MARGIN; // this is actually the max num of steps
        this.unitsPerStep = (int) Math.ceil(unitSpan / numOfSteps);
        numOfSteps = unitSpan / unitsPerStep + STEP_MARGIN;
        unitSpan = numOfSteps * unitsPerStep;
        this.pxPerStep = pxSize / numOfSteps;
    }

    public double unitsToPx(double units) {
        double stepsFromOrigin = units / unitsPerStep;
        double pxFromOrigin = stepsFromOrigin * pxPerStep;
        return originLocationPx + pxFromOrigin;
    }

    public double getOriginLocationPx() {
        return originLocationPx;
    }

}
