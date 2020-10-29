import java.util.Arrays;
import java.util.List;

public class Quadrilateral implements Positionable, TwoDShape {

    private final TwoDPoint[] vertices = new TwoDPoint[4];

    public Quadrilateral(double... vertices) { 
        this(TwoDPoint.ofDoubles(vertices));
    }

    public Quadrilateral(List<TwoDPoint> vertices) {
        int n = 0;
        for (TwoDPoint p : vertices) this.vertices[n++] = p;
        if (!isMember(vertices))
            throw new IllegalArgumentException(String.format("Invalid set of vertices specified for %s",
                                                             this.getClass().getCanonicalName()));
    }

    /**
     * Given a list of four points, adds them as the four vertices of this quadrilateral in the order provided in the
     * list. This is expected to be a counterclockwise order of the four corners.
     *
     * @param points the specified list of points.
     * @throws IllegalStateException if the number of vertices provided as input is not equal to four.
     * @throws IllegalArgumentException if points is not list of TwoDPoints
     */
    @Override
    public void setPosition(List<? extends Point> points) throws IllegalStateException, IllegalArgumentException {
        // TODO
        if (points.size() != 4) {
            throw new IllegalStateException("Quadrilateral must have 4 vertices");
        } else if (points.get(0).getClass() != TwoDPoint.class) {
            throw new IllegalArgumentException("Circle requires 2d center point.");
        } else {
            int n = 0;
            for (Point p: points) this.vertices[n++] = (TwoDPoint) p;
        }
    }

    @Override
    public List<TwoDPoint> getPosition() {
        return Arrays.asList(vertices);
    }

    /**
     * @return the lengths of the four sides of the quadrilateral. Since the setter {@link Quadrilateral#setPosition(List)}
     *         expected the corners to be provided in a counterclockwise order, the side lengths are expected to be in
     *         that same order.
     */
    protected double[] getSideLengths() {
        // TODO
        double[] lengths = new double[4];
        for (int i = 0; i < 3; ++i) {
            double x = vertices[i+1].coordinates()[0] - vertices[i].coordinates()[0];
            double y = vertices[i+1].coordinates()[1] - vertices[i].coordinates()[1];
            lengths[i] = Math.sqrt(x*x + y*y);
        }
        double x = vertices[0].coordinates()[0] - vertices[3].coordinates()[0];
        double y = vertices[0].coordinates()[1] - vertices[3].coordinates()[1];
        lengths[3] = Math.sqrt(x*x + y*y);
        return lengths;

    }

    @Override
    public int numSides() { return 4; }

    @Override
    public boolean isMember(List<? extends Point> vertices) { return vertices.size() == 4; }

    @Override
    public Point center() {
        // TODO
        List<TwoDPoint> l = this.getPosition();
        double sumX = 0, sumY = 0;
        for (int i = 0; i < l.size(); ++i) {
            sumX += l.get(i).coordinates()[0];
            sumY += l.get(i).coordinates()[1];
        }
        return new TwoDPoint(sumX/4, sumY/4);
    }

}
