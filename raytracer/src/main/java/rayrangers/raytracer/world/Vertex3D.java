package rayrangers.raytracer.world;

import rayrangers.raytracer.math.Vector3D;

/**
 * Represents a vertex in 3D space.
 */
public class Vertex3D {

    /**
     * Location vector of the vertex.
     */
    private Vector3D locationVector;

    /**
     * Normal vector of the vertex.
     */
    private Vector3D normalVector;

    /**
     * Class constructor specifying the coordinates of all dimensions.
     * 
     * @param x1    coordinate of x1-dimension
     * @param x2    coordinate of x2-dimension
     * @param x3    coordinate of x3-dimension
     */
    public Vertex3D(double x1, double x2, double x3) {
        this(x1, x2, x3, null);
    }

    /**
     * Class constructor specifying the coordinates of all dimensions and the normal vector.
     * 
     * @param x1    coordinate of x1-dimension
     * @param x2    coordinate of x2-dimension
     * @param x3    coordinate of x3-dimension
     * @param vec   normal vector
     */
    public Vertex3D(double x1, double x2, double x3, Vector3D vec) {
        locationVector = new Vector3D(x1, x2, x3);
        normalVector = vec;
    }

    /**
     * Returns the Euclidean distance to a target vertex.
     * 
     * @param vert  target vertex
     * @return      distance
     */
    public double distance(Vertex3D vert) {
        double sum = 0.0;
        for(int i = 0; i <=2; i++)
            sum += Math.pow(locationVector.getCoordinates()[i]-locationVector.getCoordinates()[i],2);
        return Math.sqrt(sum);
    }

    /**
     * Returns the coordinates of the vertex in an array.
     * 
     * @return  coordinates array
     */
    public Vector3D getlocationVector() {
        return locationVector;
    }

    /**
     * Returns the coordinate of the specified dimension.
     * 
     * @param dim   dimension, integer value in [1,3]
     * @return      coordinate
     * @throws      IndexOutOfBoundsException if {@code dim < 1 || dim > 3}
     */
    public double getCoord(int dim) {
        if(dim < 1 || dim > 3)
            throw new IndexOutOfBoundsException("Specified dimension out of 3D space.");
        return locationVector.getCoordinates()[dim-1];
    }

    /**
     * Sets the coordinates of all dimensions.
     * 
     * @param x1    coordinate of x1-dimension
     * @param x2    coordinate of x2-dimension
     * @param x3    coordinate of x3-dimension
     */
    public void setAllCoord(double x1, double x2, double x3) {
        locationVector = new Vector3D(x1, x2, x3);
    }

    /**
     * Returns the normal vector of the vertex.
     * 
     * @return  normal vector
     */
    public Vector3D getNormalVector() {
        return normalVector;
    }

    /**
     * Sets the coordinate of a specified dimension.
     * 
     * @param dim   dimension, integer value in [1,3]
     * @param coord coordinate
     * @throws      IndexOutOfBoundsException if {@code dim < 1 || dim > 3}
     */
    public void setCoord(int dim, double coord) {
        if(dim < 1 || dim > 3)
            throw new IndexOutOfBoundsException("Specified dimension out of 3D space.");
        locationVector.getCoordinates()[dim-1] = coord;
    }

    /**
     * Sets the normal vector of the vertex.
     * 
     * @param normalVector  normal vector
     */
    public void setNormalVector(Vector3D normalVector) {
        this.normalVector = normalVector;
    }
}