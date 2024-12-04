package rayrangers.raytracer.algorithm;

import java.awt.Color;

import rayrangers.raytracer.world.Material;

/**
 * 
 */
public class Shader {

    /**
     * 
     */
    public Color calculatePixelColor(HitRecord record) {
        // TODO: Implement, green for testing
        Material mt = record.getMaterial();
        if (mt != null) {
            return mt.getAmbient();
        }
        return Color.GREEN;
    }

}
