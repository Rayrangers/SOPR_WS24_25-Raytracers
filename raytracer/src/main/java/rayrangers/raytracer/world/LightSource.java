package rayrangers.raytracer.world;

import java.awt.Color;
import java.util.UUID;

import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents a lightsource in the scene.
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
     * Class constructor specifying the uuid, intensity, position and color of the
     * light source.
     * 
     * @param uuid      UUID of the light source
     * @param intensity intensity of the light source
     * @param position  position of the light source
     * @param color     color of the light source
     */
    public LightSource(UUID uuid, double intensity, Vertex3D position, Color color) {
        this.uuid = uuid;
        this.intensity = intensity;
        this.position = position;
        this.color = color;
    }

    /**
     * Class constructor specifying the intensity, position and color of the light
     * source.
     * 
     * @param intensity intensity of the light source
     * @param position  position of the light source
     * @param color     color of the light source
     */
    public LightSource(double intensity, Vertex3D position, Color color) {
        this(UUID.randomUUID(), intensity, position, color);
    }

    /**
     * Returns the UUID of the light source.
     * 
     * @return UUID of the light source
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Returns the intensity of the light source.
     * 
     * @return intensity of the light source
     */
    public double getIntensity() {
        return intensity;
    }

    /**
     * Sets the intensity of the light source.
     * 
     * @param intensity intensity of the light source
     */
    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the position of the light source in the world coordinate system.
     * 
     * @return position of the light source
     */
    public Vertex3D getPosition() {
        return position;
    }

    /**
     * Sets the position of the light source in the world coordinate system.
     * 
     * @param position position of the light source
     */
    public void setPosition(Vertex3D position) {
        this.position = position;
    }

    /**
     * Returns the color of the light source.
     * 
     * @return color of the light source
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the light source.
     * 
     * @param color color of the light source
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
