import org.jetbrains.annotations.NotNull;

public class Sphere implements ThreeDShape {

    private ThreeDPoint center;
    private double radius;

    public Sphere(ThreeDPoint t, double r) {
        center = t;
        radius = r;
    }

    @Override
    public Point center() {
        return this.center;
    }

    @Override
    public double volume() {
        return (4/3) * Math.PI * radius*radius*radius;
    }

    @Override
    public double surfaceArea() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public int compareTo(ThreeDShape t) {
        return (this.volume() == t.volume()) ? 0: ((this.volume() > t.volume()) ? 1: -1);
    }
}
