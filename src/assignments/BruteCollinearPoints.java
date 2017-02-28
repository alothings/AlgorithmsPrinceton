import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Program that examines 4 points at a time and checks whether they all
 * lie on the same line segment.
 */
public class BruteCollinearPoints {

    private LineSegment[] segments;

    /**
     * Finds all line segments containing 4 points
     * @param points: a number of points. I need to examine 4 at a time
     */
    public BruteCollinearPoints(Point[] points) {

        hasDuplicates(points);                          // Check for duplicates

        int len = points.length;
        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        Arrays.sort(pointsCopy);
//        StdOut.println(Arrays.toString(pointsCopy));

        for (int i = 0; i < len - 3; i++) { //n*n*n*n = 10*10*10*10
            for (int j = i + 1; j < len - 2; j++) {
                for (int k = j + 1; k < len - 1; k++) {
                    for (int l = k + 1; l < len; l++) {
                        if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[k]) &&
                                pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[l])) {
                            foundSegments.add(new LineSegment(pointsCopy[i], pointsCopy[l]));
                        }
                    }
                }
            }
        }
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    /**
     * The number of line segments
     * @return
     */
    public int numberOfSegments() {
        return segments.length;
    }

    /**
     * The line segments
     * @return
     */
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }

    private void hasDuplicates(Point[] points) {
        for (int i = 0; i < points.length; i++)
            for (int j = i+1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Has Duplicates..");
    }


    public static void main(String[] args) {


        /**
         * Create points
         */
        int n = StdIn.readInt();
        Point[] temp = new Point[n];

        temp[0] = new Point(0, 0);
        temp[1] = new Point(1, 1);
        temp[2] = new Point(2, 2);
        temp[3] = new Point(3, 3);
        temp[4] = new Point(1, 3);
        temp[5] = new Point(3, 1);
        temp[6] = new Point(4, 0);

        BruteCollinearPoints p = new BruteCollinearPoints(temp);


//        StdOut.println(p.numberOfSegments());
//        StdOut.println(p.segments().toString());
    }
}