import java.util.*;

public class Ordering {

    static class XLocationComparator implements Comparator<TwoDShape> {
        @Override public int compare(TwoDShape o1, TwoDShape o2) {
            double x1 = o1.center().coordinates()[0];
            double x2 = o1.center().coordinates()[0];
            return (x1 == x2) ? 0 : ((x1 > x2) ? 1: -1);
        }
    }

    static class AreaComparator implements Comparator<SymmetricTwoDShape> {
        @Override public int compare(SymmetricTwoDShape o1, SymmetricTwoDShape o2) {
            return (o1.area() == o2.area()) ? 0: ((o1.area() > o2.area()) ? 1: -1);
        }
    }

    static class SurfaceAreaComparator implements Comparator<ThreeDShape> {
        @Override public int compare(ThreeDShape o1, ThreeDShape o2) {
            return (o1.surfaceArea() == o2.surfaceArea()) ? 0: ((o1.surfaceArea() > o2.surfaceArea()) ? 1: -1);
        }
    }

    // TODO: there's a lot wrong with this method. correct it so that it can work properly with generics.

    static void checkClass(Object o, Class c) throws IllegalStateException {
        if (!o.getClass().isAssignableFrom(c)) throw new IllegalStateException("source must be subtype of destination");
    }

    static<T extends Object> void copy(Collection<? extends T> source, Collection<T> destination) throws IllegalStateException {
        source.forEach(x -> destination.forEach(y -> checkClass(x, y.getClass())));
        destination.addAll(source);
    }

    public static void main(String[] args) {
        List<TwoDShape>          shapes          = new ArrayList<>();
        List<SymmetricTwoDShape> symmetricshapes = new ArrayList<>();
        List<ThreeDShape>        threedshapes    = new ArrayList<>();

        /*
         * uncomment the following block and fill in the "..." constructors to create actual instances. If your
         * implementations are correct, then the code should compile and yield the expected results of the various
         * shapes being ordered by their smallest x-coordinate, area, volume, surface area, etc. */

        symmetricshapes.add(new Rectangle(TwoDPoint.ofDoubles(0, 0, 0, 1, 3, 1, 3, 0)));
        symmetricshapes.add(new Square(TwoDPoint.ofDoubles(0, 0, 0, 2, 2, 2, 2, 0)));
        symmetricshapes.add(new Circle(0,0,1));

        copy(symmetricshapes, shapes); // note-1 //
        shapes.add(new Quadrilateral(new ArrayList<>(TwoDPoint.ofDoubles(0, 0, 0, 1, 3, 1, 3, 0))));

        // sorting 2d shapes according to various criteria
        shapes.sort(new XLocationComparator());
        symmetricshapes.sort(new XLocationComparator());
        symmetricshapes.sort(new AreaComparator());

        // sorting 3d shapes according to various criteria
        Collections.sort(threedshapes);
        threedshapes.sort(new SurfaceAreaComparator());

        /*
         * if your changes to copy() are correct, uncommenting the following block will also work as expected note that
         * copy() should work for the line commented with 'note-1' while at the same time also working with the lines
         * commented with 'note-2' and 'note-3'. */


        List<Number> numbers = new ArrayList<>();
        List<Double> doubles = new ArrayList<>();
        Set<Square>        squares = new HashSet<>();
        Set<Quadrilateral> quads   = new LinkedHashSet<>();

        copy(doubles, numbers); // note-2 //
        copy(squares, quads);   // note-3 //

    }
}
