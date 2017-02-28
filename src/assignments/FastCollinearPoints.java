import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Program that examines 4 points at a time and checks whether they all
 * lie on the same line segment.
 * The method segments() should include each maximal line segment
 * containing 4 (or more) points exactly once. For example, if 5 points
 * appear on a line segment in the order p→q→r→s→t, then do not include
 * the subsegments p→s or q→t.
 */
public class FastCollinearPoints {

    private LineSegment[] segments;
    private Point[] pointsCopy;

    /**
     * Finds all line segments containing 4 points
     * @param points: a number of points. I need to examine 4 at a time
     */
    public FastCollinearPoints(Point[] points) {

        hasDuplicates(points);
        pointsCopy = Arrays.copyOf(points, points.length);

        if (pointsCopy == null) throw new NullPointerException("Null");
        for (int i = 0; i < pointsCopy.length; i++) {
            if (pointsCopy[i] == null) throw new NullPointerException("Null element");
        }

        Point[] pair;
        ArrayList<Point[]> lineSegments = new ArrayList<>();
        boolean unique = true;
//        Map<Point, Point> lineSegments = new HashMap<>();
//        ArrayList<LineSegment> lineSegments = new ArrayList<>();
        int len = pointsCopy.length;

        Arrays.sort(pointsCopy);
//        StdOut.println(Arrays.toString(points));

        for (int i = 0; i < len - 3; i++) {// i = i + 1
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, i+1, len, pointsCopy[i].slopeOrder());
//            StdOut.println(Arrays.toString(points));
            int count = 0;
            for (int j = i + 1; j < len - 1; j++) {

                if (pointsCopy[i].slopeTo(pointsCopy[j]) == pointsCopy[i].slopeTo(pointsCopy[j+1])) {
                    count++;
//                    StdOut.println("Incrementing with " + points[i] + " " + points[j]);
//                    StdOut.println("\t and " + points[i] + " " + points[j+1]);
                    if (j + 1 == len - 1 && count >= 2) {

                        /** Add points to Arraylist lineSegments */
                        for (Point[] pts : lineSegments) {
//                            StdOut.println("HERE");
                            if (pointsCopy[j + 1] == pts[1] &&
                                    pointsCopy[i].slopeTo(pointsCopy[j+1]) == pts[0].slopeTo(pts[1])) {
//                                StdOut.println("HERE");
                                unique = false;
                                break;
                            }
                        }
                        if (unique) {
                            pair = new Point[2];
                            pair[0] = pointsCopy[i];
                            pair[1] = pointsCopy[j+1];
                            lineSegments.add(pair);
                        }
                        else {
                            unique = true;
                            break;
                        }
//                        StdOut.println("NOT. HERE");

//                        lineSegments.add(new LineSegment(points[i], points[j + 1]));
//                        i = j + 1;
                    }
                }
                else if (count >= 2) {

                    for (Point[] pts : lineSegments) {
                        if (pointsCopy[j] == pts[1] &&
                                pointsCopy[i].slopeTo(pointsCopy[j]) == pts[0].slopeTo(pts[1])) {
//                            StdOut.println("HERE");
                            unique = false;
                            break;
                        }
                    }

//                    StdOut.println("NOT HERE");
                    if (unique) {
                        pair = new Point[2];
                        pair[0] = pointsCopy[i];
                        pair[1] = pointsCopy[j];
                        lineSegments.add(pair);
                    }
                    else {
                        unique = true;
                        break;
                    }


//                    if (!lineSegments.containsValue(points[j]))
//                        lineSegments.put(points[i], points[j]);

//                    lineSegments.add(new LineSegment(points[i], points[j]));
//                    i = j;
                    count = 0;
                }
                else {
                    count = 0;
//                    StdOut.println("NOT Incrementing with " + points[i] + " " + points[j]);
//                    StdOut.println("\t and " + points[i] + " " + points[j+1]);

                }
            }
        }
        segments = new LineSegment[lineSegments.size()];
        int index = 0;
        for (Point[] pts : lineSegments) {
            Point p = pts[0];
            Point q = pts[1];
            segments[index] = new LineSegment(p, q);
            index++;
        }

//        segments = lineSegments.entrySet().toArray(new LineSegment[lineSegments.size()]);
//        segments = lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    private void hasDuplicates(Point[] points) {
        for (int i = 0; i < points.length; i++)
            for (int j = i+1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Has Duplicates..");
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
        return segments;
    }

    public static void main(String[] args) {


        /**
         * Create points
         */
//        int n = StdIn.readInt();
//        Point[] temp = new Point[n];
//
//        temp[0] = new Point(0, 0);
//        temp[1] = new Point(1, 1);
//        temp[2] = new Point(2, 2);
//        temp[3] = new Point(3, 3);
//        temp[4] = new Point(1, 3);
//        temp[5] = new Point(3, 1);
//        temp[6] = new Point(4, 0);
//
//        FastCollinearPoints p = new FastCollinearPoints(temp);
//
//
//        StdOut.println(p.numberOfSegments());

        /** Sample client from hw
         *
         */
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}