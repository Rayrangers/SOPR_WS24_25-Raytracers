package rayrangers.raytracer;

/**
 * Represents a vertex in 3D space.
 */
public class Vertex3D {

    /**
     * Array containing the coordinates of the vertex (x1/x2/x3-dimension).
     */
    private double[] coordinates;

    /**
     * Class constructor specifying the coordinates of all dimensions.
     * @param x1    coordinate of x1-dimension
     * @param x2    coordinate of x2-dimension
     * @param x3    coordinate of x3-dimension
     */
    public Vertex3D(double x1, double x2, double x3) {
        coordinates = new double[] {x1, x2, x3};
    }

    /**
     * Returns the Euclidean distance to a target vertex.
     * @param vert  target vertex
     * @return      distance
     */
    public double distance(Vertex3D vert) {
        double sum = 0.0;
        for(int i = 0; i <=2; i++)
            sum += Math.pow(vert.coordinates[i]-coordinates[i],2);
        return Math.sqrt(sum);
    }

    /**
     * Returns the coordinates of the vertex in an array.
     * @return  coordinates array
     */
    public double[] getAllCoord() {
        return coordinates;
    }

    /**
     * Returns the coordinate of the specified dimension.
     * @param dim   dimension, integer value in [1,3]
     * @return      coordinate
     * @throws      IndexOutOfBoundsException if {@code dim < 1 || dim > 3}
     */
    public double getCoord(int dim) {
        if(dim < 1 || dim > 3)
            throw new IndexOutOfBoundsException("Specified dimension out of 3D space.");
        return coordinates[dim-1];
    }

    /**
     * Sets the coordinates of all dimensions.
     * @param x1    coordinate of x1-dimension
     * @param x2    coordinate of x2-dimension
     * @param x3    coordinate of x3-dimension
     */
    public void setAllCoord(double x1, double x2, double x3) {
        coordinates = new double[] {x1, x2, x3};
    }

    /**
     * Sets the coordinate of a specified dimension.
     * @param dim   dimension, integer value in [1,3]
     * @param coord coordinate
     * @throws      IndexOutOfBoundsException if {@code dim < 1 || dim > 3}
     */
    public void setCoord(int dim, double coord) {
        if(dim < 1 || dim > 3)
            throw new IndexOutOfBoundsException("Specified dimension out of 3D space.");
        coordinates[dim-1] = coord;
    }
}