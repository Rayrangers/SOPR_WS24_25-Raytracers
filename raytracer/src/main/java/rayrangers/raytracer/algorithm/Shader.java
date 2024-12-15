package rayrangers.raytracer.algorithm;
import java.awt.Color;
import java.util.Map;
import java.util.UUID;
import rayrangers.raytracer.world.LightSource;
import rayrangers.raytracer.world.Scene;
import rayrangers.raytracer.world.Material;
import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents a Blinn-Phong Shader.
 */
public class Shader {

    /**
     * Ambient color of the scene.
     */
    private static final Color AMBIENT_COLOR = new Color(100,100,100);

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

        // get material, normal vector, view ray direction and hit point from hit record
        Material material = record.getMaterial();
        Vector3D normalVector = record.getNormalVector();
        Vector3D viewRayDirection = record.getViewRayDirection();
        Vertex3D hitPoint = record.getHitPoint();

        // get ambient coefficients from material
        Color ambientCoefficients = material.getAmbient();

        // calculate ambient color
        Color ambientColor = new Color((int) (AMBIENT_COLOR.getRed() * ambientCoefficients.getRed() / 255.0),
        (int) (AMBIENT_COLOR.getGreen() * ambientCoefficients.getGreen() / 255.0),
        (int) (AMBIENT_COLOR.getBlue() * ambientCoefficients.getBlue() / 255.0));

        Color finalColor = new Color(0,0,0);    






        for (LightSource lightSource : lightSources.values()) {
            //get light source position, color and intensity
            Color lightColor = lightSource.getColor();

            // calculate light vector
            Vector3D lightVector = lightSource.getPosition().getlocationVector().sub(hitPoint.getlocationVector());
            

            // get diffuse coefficients from material
            Color diffuseColor = material.getDiffuse();
            System.out.println(normalVector);
            //calculate diffuse color
            //double diffuseIntensity = Math.max(0, record.getNormalVector().scalar(lightVector));
            
            
            double diffuseIntensity = 0;
            if (normalVector != null) {
                diffuseIntensity = Math.max(0, normalVector.scalar(lightVector));
                System.out.println("test");
            }
            

            //calculate diffuse color
            Color diffuse = new Color((int) (lightColor.getRed() * diffuseColor.getRed() / 255.0 * diffuseIntensity),
                    (int) (lightColor.getGreen() * diffuseColor.getGreen() / 255.0 * diffuseIntensity),
                    (int) (lightColor.getBlue() * diffuseColor.getBlue() / 255.0 * diffuseIntensity));

            
    
            finalColor = new Color(
                Math.min(255, ambientColor.getRed() + diffuse.getRed()),
                Math.min(255, ambientColor.getGreen() + diffuse.getGreen()),
                Math.min(255, ambientColor.getBlue() + diffuse.getBlue())
            );
            
        } 
        //return ambientColor;
        return finalColor;
    }

}
