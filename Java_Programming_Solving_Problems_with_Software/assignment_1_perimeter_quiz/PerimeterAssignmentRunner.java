import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {

    // Provided function with assignment.
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    /*
     * This function calculates the total number of points in the shape.
     */
    public int getNumPoints (Shape s) {
        int count = 0;

        for(Point p: s.getPoints()){
            count += 1;
        }
        return count;
    }

    /*
     * This function calculates the average length of the shape.
     */
    public double getAverageLength(Shape s) {
        return getPerimeter(s) / getNumPoints(s);
    }

    /*
     * This function calculates the largest side of the shape.
     */
    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            largestSide = Math.max(largestSide, currDist);
            prevPt = currPt;
        }
        return largestSide;
    }

    /*
     * This function calculates the largest X in the shape.
     */
    public double getLargestX(Shape s) {
        double largestX = 0.0;
        for(Point p: s.getPoints())
            largestX = Math.max(largestX, p.getX());
        return largestX;
    }

    /*
     * This function calculates the largest perimeter among multiple files.
     */
    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;

        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            largestPerimeter = Math.max(largestPerimeter, getPerimeter(s));
        }
        return largestPerimeter;
    }

    /*
     * This function calculates the file name with the largest perimeter.
     */
    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        File largestFile = null;
        double largestPerimeter = 0.0;

        for(File f: dr.selectedFiles()) {
            Shape s = new Shape(new FileResource(f));
            if (getPerimeter(s) > largestPerimeter) {
                largestPerimeter = Math.max(largestPerimeter, getPerimeter(s));
                largestFile = f;
            }
        }
        return largestFile.getName();
    }

    /*
     *                   UNIT TEST FUNCTIONS
     */
    /*
     *   This function tests the perimeter.
     */
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points = "+ getNumPoints(s));
        System.out.println("Average of sides = "+ getAverageLength(s));
        System.out.println("Longest side = "+getLargestSide(s));
        System.out.println("Largest x = "+getLargestX(s));
    }

    /*
     * This function tests the perimeter among multiple files.
     */
    public void testPerimeterMultipleFiles() {
        System.out.println("Largest perimeter among files = " +
                            getLargestPerimeterMultipleFiles());
    }

    /*
     * This function tests the file name with the largest perimeter.
     */
    public void testFileWithLargestPerimeter() {
        System.out.println("Largest File with perimeter " +
                            getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        System.out.println("------------------");
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
        System.out.println("------------------");
    }
}
