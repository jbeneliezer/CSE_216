/**
 * An unmodifiable point in the three-dimensional space. The coordinates are specified by exactly three doubles (its
 * <code>x</code>, <code>y</code>, and <code>z</code> values).
 */
public class ThreeDPoint implements Point {

    private final double x, y, z;

    public ThreeDPoint(double a, double b, double c) {
        // TODO
        this.x = a;
        this.y = b;
        this.z = c;
    }

    /**
     * @return the (x,y,z) coordinates of this point as a <code>double[]</code>.
     */
    @Override
    public double[] coordinates() {
        // TODO
        return new double[]{x, y, z};
    }
}
