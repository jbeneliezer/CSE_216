import java.util.List;

public class Rectangle extends Quadrilateral implements SymmetricTwoDShape {

    private static double distance(TwoDPoint a, TwoDPoint b) {
        double x = b.coordinates()[0] - a.coordinates()[0];
        double y = b.coordinates()[1] - a.coordinates()[1];
        return Math.sqrt(x*x + y*y);
    }

    public Rectangle(List<TwoDPoint> vertices) {
        super(vertices);
    }

    /**
     * The center of a rectangle is calculated to be the point of intersection of its diagonals.
     *
     * @return the center of this rectangle.
     */
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

    @Override
    public boolean isMember(List<? extends Point> vertices) {
        // TODO
        return (vertices.size() == 4);
    }

    @Override
    public double area() {
        // TODO
        List<TwoDPoint> l = this.getPosition();
        double x = distance(l.get(0), l.get(1));
        double y = distance(l.get(0), l.get(3));
        return x * y;
    }
}
