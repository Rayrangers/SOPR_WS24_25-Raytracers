package rayrangers.raytracer.world;

import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.view.ViewPane;

/**
 * Represents the camera.
 */
public class Camera {

    /**
     * Position of the camera in the world coordinate system.
     */
    private Vertex3D worldPosition;

    /**
     * Distance of the camera to the viewpane (=focal length).
     */
    private double paneDistance;

    /**
     * Viewpane of the camera.
     */
    private ViewPane viewPane;

    /**
     * Base vector in righthand-direction in the view coordinate system,
     * relative to the world.
     */
    private Vector3D u;

    /**
     * Base vector in up-direction in the view coordinate system,
     * relative to the world.
     */
    private Vector3D v;

    /**
     * Base vector in forward-direction in the view coordinate system,
     * relative to the world.
     */
    private Vector3D w;
}
