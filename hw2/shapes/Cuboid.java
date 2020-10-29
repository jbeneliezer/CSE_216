import java.util.List;

// TODO : a missing interface method must be implemented in this class to make it compile. This must be in terms of volume().
public class Cuboid implements ThreeDShape {

    private final ThreeDPoint[] vertices = new ThreeDPoint[8];

    /**
     * Finds the distance between two ThreeDPoints
     * @param a point 1
     * @param b point 2
     * @return distance as a double
     */

    private static double distance(ThreeDPoint a, ThreeDPoint b) {
        double x = b.coordinates()[0] - a.coordinates()[0];
        double y = b.coordinates()[1] - a.coordinates()[1];
        double z = b.coordinates()[2] - a.coordinates()[2];
        return Math.sqrt(x*x + y*y + z*z);
    }

    /**
     * Creates a cuboid out of the list of vertices. It is expected that the vertices are provided in
     * the order as shown in the figure given in the homework document (from v0 to v7).
     * 
     * @param vertices the specified list of vertices in three-dimensional space.
     */

    public Cuboid(List<ThreeDPoint> vertices) {
        if (vertices.size() != 8)
            throw new IllegalArgumentException(String.format("Invalid set of vertices specified for %s",
                                                             this.getClass().getName()));
        int n = 0;
        for (ThreeDPoint p : vertices) this.vertices[n++] = p;
    }

    @Override
    public double volume() {
        //TODO
        double l = distance(vertices[0], vertices[1]);
        double w = distance(vertices[0], vertices[5]);
        double h = distance(vertices[0], vertices[3]);
        return l * w * h;
    }

    @Override
    public double surfaceArea() {
        double l = distance(vertices[0], vertices[1]);
        double w = distance(vertices[0], vertices[5]);
        double h = distance(vertices[0], vertices[3]);
        return 2 * (l*w + l*h + w*h);
    }

    @Override
    public ThreeDPoint center() {
        // TODO
        double sumX = 0, sumY = 0, sumZ = 0;
        for (int i = 0; i < 8; ++i) {
            sumX += vertices[i].coordinates()[0];
            sumY += vertices[i].coordinates()[1];
            sumZ += vertices[i].coordinates()[2];
        }
        return new ThreeDPoint(sumX/8, sumY/8, sumZ/8);
    }

    @Override
    public int compareTo(ThreeDShape t) {
        return (this.volume() - t.volume() == 0) ? 0: ((this.volume() - t.volume() > 0) ? 1: -1);
    }
}
