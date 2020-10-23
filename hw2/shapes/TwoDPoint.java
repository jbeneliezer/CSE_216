import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values.
 */
public class TwoDPoint implements Point {
    private final double x;
    private final double y;

    public TwoDPoint(double a, double b) {
        this.x = a;
        this.y = b;
    }

    /**
     * @return the coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        return new double[]{x, y}; // TODO
    }

    /**
     * Returns a list of <code>TwoDPoint</code>s based on the specified array of doubles. A valid argument must always
     * be an even number of doubles so that every pair can be used to form a single <code>TwoDPoint</code> to be added
     * to the returned list of points.
     *
     * @param coordinates the specified array of doubles.
     * @return a list of two-dimensional point objects.
     * @throws IllegalArgumentException if the input array has an odd number of doubles.
     */
    public static List<TwoDPoint> ofDoubles(double @NotNull ... coordinates) throws IllegalArgumentException {
        if (coordinates.length % 2 != 0) {
            throw new IllegalArgumentException("number of coordinates must be even.");
        } else if (coordinates.length == 0){
            return new ArrayList<>(0);
        } else {
            List<TwoDPoint> l = new ArrayList<>(coordinates.length);
            l.add(new TwoDPoint(coordinates[0], coordinates[1]));
            l.addAll(ofDoubles(Arrays.copyOfRange(coordinates,2, coordinates.length)));
            return l;
        }
    }
}
