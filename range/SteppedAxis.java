package range;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an axis with defined markings
 */
public class SteppedAxis extends Axis {

    public static final int MIN_PX_PER_STEP = 30;
    public static final int MARK_SIZE_PX = 10;
    public static final int STEP_MARGIN = 2;

    private int unitsPerStep;
    private double pxPerStep;

    public SteppedAxis(double pxSize) {
        super(pxSize);
        this.unitsPerStep = 0;
    }

    @Override
    public boolean expandTo(double x) {
        boolean modified = super.expandTo(x);
        if (!modified)
            return false;
        double maxNumOfSteps = pxSize / MIN_PX_PER_STEP;
        unitsPerStep = (int) Math.ceil(getLength() / (maxNumOfSteps - STEP_MARGIN)); // divide the number of units by
                                                                                     // the number of usable steps (not
        if (unitsPerStep < 0) // this will happen when the length is 0, or where the axis size is too small
            return false;
        double numOfSteps = getLength() / unitsPerStep + STEP_MARGIN;
        pxPerStep = pxSize / numOfSteps; // including the step margin)
        return true;
    }

    @Override
    public double getPixelsOfUnits(double units) {
        if (unitsPerStep < 0)
            return super.getPixelsOfUnits(units);
        double stepsFromOrigin = units / unitsPerStep;
        double pxFromOrigin = stepsFromOrigin * pxPerStep;
        return getOriginLocation() + pxFromOrigin;
    }

    public Iterable<Integer> getSteps() {
        List<Integer> steps = new ArrayList<>();
        if (start > 0 || end < 0) {
            // doesn't include 0. Start with the last integer before start, and move until
            // the next after end
            for (int units = (int) Math.floor(start); units < end + unitsPerStep; units += unitsPerStep)
                steps.add(units);
        } else {
            // range includes 0. Start from 0 towards the positives, and viceversa
            for (int units = unitsPerStep; units <= end; units += unitsPerStep)
                steps.add(units);
            for (int units = -unitsPerStep; units >= start; units -= unitsPerStep)
                steps.add(units);
        }
        return steps;
    }

}
