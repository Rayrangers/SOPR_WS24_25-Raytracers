
package rayrangers.raytracer.world;

import java.util.UUID;

import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.view.ViewPane;

/**
 * Represents the camera the scene is captured with.
 */
public class Camera {

    /**
     * Randomly generated UUID of the camera.
     */
    private UUID uuid;

    /**
     * Position e of the camera in the world coordinate system.
     */
    private Vertex3D worldPosition;

    /**
     * Distance d of the camera to the viewpane (= focal length).
     */
    private double paneDistance;

    /**
     * Viewpane of the camera.
     */
    private ViewPane viewPane;

    /**
     * Base vector in u-direction (righthand) in the view coordinate system,
     * relative to the world.
     * Initial value: Base vector (1, 0, 0)
     */
    private Vector3D u = new Vector3D(1, 0, 0);

    /**
     * Base vector in v-direction (up) in the view coordinate system,
     * relative to the world.
     * Initial value: Base vector (0, 1, 0)
     */
    private Vector3D v = new Vector3D(0, 1, 0);

    /**
     * Base vector in w-direction (forward) in the view coordinate system,
     * relative to the world.
     * Initial value: Base vector (0, 0, 1)
     */
    private Vector3D w = new Vector3D(0, 0, 1);

    /**
     * Constructs a camera with the specified position, orientation and resolution.
     * The camera is identified with the given UUID.
     * 
     * @param uuid UUID of the camera
     * @param worldPosition camera postion in the world coordinate system
     * @param angleX1 rotation angle in x1-direction
     * @param angleX2 rotation angle in x2-direction
     * @param angleX3 rotation angle in x3-direction
     * @param paneDistance distance of the camera to the viewpane
     * @param paneWidth width of the viewpane
     * @param resX resolution in x-direction
     * @param resY resolution in y-direction
     */
    public Camera(UUID uuid, Vertex3D worldPosition, double angleX1, double angleX2, double angleX3,
            double paneDistance, double paneWidth, int resX, int resY) {
        this.uuid = uuid;
        this.worldPosition = worldPosition;
        this.paneDistance = paneDistance;
        viewPane = new ViewPane(resX, resY, paneWidth);
        rotate(angleX1, angleX2, angleX3);
    }

    /**
     * Constructs a camera with the specified position, orientation and resolution,
     * but without a given UUID. Generates a random UUID for the camera instead.
     * 
     * @param worldPosition camera postion in the world coordinate system
     * @param angleX1 rotation angle in x1-direction
     * @param angleX2 rotation angle in x2-direction
     * @param angleX3 rotation angle in x3-direction
     * @param paneDistance distance of the camera to the viewpane
     * @param paneWidth width of the viewpane
     * @param resX resolution in x-direction
     * @param resY resolution in y-direction
     */
    public Camera(Vertex3D worldPosition, double angleX1, double angleX2, double angleX3,
            double paneDistance, double paneWidth, int resX, int resY) {
        this(UUID.randomUUID(), worldPosition, angleX1, angleX2, angleX3, paneDistance, paneWidth, resX, resY);
    }

    /**
     * Returns the UUID of the camera.
     * 
     * @return UUID
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Returns the current position of the camera as a vertex.
     * 
     * @return position vertex
     */
    public Vertex3D getWorldPosition() {
        return worldPosition;
    }

    /**
     * Returns the distance of the camera to the associated viewpane.
     * 
     * @return distance to the viewpane
     */
    public double getPaneDistance() {
        return paneDistance;
    }

    /**
     * Returns the viewpane associated with the camera.
     * 
     * @return viewpane
     */
    public ViewPane getViewPane() {
        return viewPane;
    }

    /**
     * Returns the base vector in u-direction (righthand) 
     * in the view coordinate system.
     * 
     * @return base vector u
     */
    public Vector3D getU() {
        return u;
    }

    /**
     * Returns the base vector in v-direction (up) 
     * in the view coordinate system.
     * 
     * @return base vector v
     */
    public Vector3D getV() {
        return v;
    }

    /**
     * Returns the base vector in w-direction (forward) 
     * in the view coordinate system.
     * 
     * @return base vector w
     */
    public Vector3D getW() {
        return w;
    }

    /**
     * Rotates the camera along the world coordinate axes.
     * 
     * @param angleX1 rotation angle along x1 axis
     * @param angleX2 rotation angle along x2 axis
     * @param angleX3 rotation angle along x3 axis
     */
    public void rotate(double angleX1, double angleX2, double angleX3) {
        if (angleX1 != 0) {
            u.rotateX(angleX1);
            v.rotateX(angleX1);
            w.rotateX(angleX1);
        }
        if (angleX2 != 0) {
            u.rotateY(angleX2);
            v.rotateY(angleX2);
            w.rotateY(angleX2);
        }
        if (angleX3 != 0) {
            u.rotateZ(angleX3);
            v.rotateZ(angleX3);
            w.rotateZ(angleX3);
        }
    }
}
