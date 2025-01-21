package rayrangers.raytracer.world;

import java.awt.Color;
import java.util.UUID;

import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents a light source in the scene.
 */
public class LightSource {

    /**
     * Randomly generated UUID of the light source.
     */
    private UUID uuid;

    /**
     * Intensity of the light source.
     */
    private double intensity;

    /**
     * Position of the light source in the world coordinate system.
     */
    private Vertex3D position;

    /**
     * Color of the light source.
     */
    private Color color;

    /**
     * Constructs a light source with the specified intensity, position and color.
     * The light source is identified with the given UUID.
     * 
     * @param uuid UUID of the light source
     * @param intensity intensity of the light source
     * @param position position of the light source
     * @param color color of the light source
     */
    public LightSource(UUID uuid, double intensity, Vertex3D position, Color color) {
        this.uuid = uuid;
        this.intensity = intensity;
        this.position = position;
        this.color = color;
    }

    /**
     * Constructs a light source with the specified intensity, position and color,
     * but without a given UUID. Generates a random UUID for the light source instead.
     * 
     * @param intensity intensity of the light source
     * @param position position of the light source
     * @param color color of the light source
     */
    public LightSource(double intensity, Vertex3D position, Color color) {
        this(UUID.randomUUID(), intensity, position, color);
    }

    /**
     * Returns the UUID of the light source.
     * 
     * @return UUID
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Returns the intensity of the light source.
     * 
     * @return intensity
     */
    public double getIntensity() {
        return intensity;
    }

    /**
     * Sets the intensity of the light source.
     * 
     * @param intensity intensity
     */
    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the position of the light source in the world coordinate system.
     * 
     * @return position
     */
    public Vertex3D getPosition() {
        return position;
    }

    /**
     * Sets the position of the light source in the world coordinate system.
     * 
     * @param position position
     */
    public void setPosition(Vertex3D position) {
        this.position = position;
    }

    /**
     * Returns the color of the light source.
     * 
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the light source.
     * 
     * @param color color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
