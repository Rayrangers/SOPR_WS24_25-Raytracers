package rayrangers.raytracer.algorithm;

import java.awt.Color;
import java.util.Map;
import java.util.UUID;

import rayrangers.raytracer.world.LightSource;
import rayrangers.raytracer.world.Scene;

/**
 * Represents a Blinn-Phong Shader.
 */
public class Shader {

    /**
     * Hash map of all light sources of the scene.
     */
    private Map<UUID, LightSource> lightSources;

    /**
     * Class constructor specifying the scene to be rendered.
     * 
     * @param scene scene to be rendered
     */
    public Shader(Scene scene) {
        lightSources = scene.getLightSources();
    }

    /**
     * Calculates the color of the associated pixel.
     * 
     * @param record hit record of the intersection between ray and hit object
     * @return calculated color of the pixel
     */
    public Color calculatePixelColor(HitRecord record) {
        // TODO: Implement, green for testing
        for (LightSource lightSource : lightSources.values()) {

        }
        return Color.GREEN;
    }

}
