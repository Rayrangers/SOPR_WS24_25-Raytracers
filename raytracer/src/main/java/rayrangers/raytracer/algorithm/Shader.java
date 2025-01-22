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
     * Ambient color used if scene background color is completely black.
     */
    private static final Color AMBIENT_COLOR = new Color(50, 50, 50);

    /**
     * Hash map of all light sources of the scene.
     */
    private Map<UUID, LightSource> lightSources;

    /**
     * Background color of the scene.
     */
    private Color backgroundColor;

    /**
     * Class constructor specifying the scene to be rendered.
     * 
     * @param scene scene to be rendered
     */
    public Shader(Scene scene) {
        lightSources = scene.getLightSources();
        if (scene.getBackgroundColor().equals(Color.BLACK)) {
            backgroundColor = AMBIENT_COLOR;
        } else {
            backgroundColor = scene.getBackgroundColor();
        }
    }

    /**
     * Calculates the color of the associated pixel.
     * 
     * @param record hit record of the intersection between ray and hit object
     * @param scene scene to be rendered
     * @return calculated color of the pixel
     */
    public Color calculatePixelColor(HitRecord record, Scene scene) {

        // Get material, normal vector, view ray direction and hit point from hit record
        Material material = record.getMaterial();
        Vertex3D hitPoint = record.getHitPoint();

        // Get all vertices from the current hit record
        Vertex3D[] vertices = record.getAllVert();
        Vector3D normal0 = vertices[0].getlocationVector().normalize();
        Vector3D normal1 = vertices[1].getlocationVector().normalize();
        Vector3D normal2 = vertices[2].getlocationVector().normalize();

        double[] barycentric = calculateBarycentric(hitPoint, vertices[0], vertices[1], vertices[2]);
        Vector3D interpolatedNormal = normal0.mult(barycentric[0]).add(normal1.mult(barycentric[1]))
                .add(normal2.mult(barycentric[2])).normalize();

        // Get ambient coefficients from material
        Color ambientCoefficients = material.getAmbient();
        // Calculate ambient color
        Color ambientColor = new Color((int) (backgroundColor.getRed() * (ambientCoefficients.getRed() / 255.0)),
                (int) (backgroundColor.getGreen() * (ambientCoefficients.getGreen() / 255.0)),
                (int) (backgroundColor.getBlue() * (ambientCoefficients.getBlue() / 255.0)));

        Color finalColor = new Color(0, 0, 0);
        Color color = new Color(ambientColor.getRed(), ambientColor.getGreen(), ambientColor.getBlue());



        // Iterate over all light sources
        for (LightSource lightSource : lightSources.values()) {

            // Get light source position, color and intensity
            Color lightColor = lightSource.getColor();

            // Calculate light vector
            Vector3D lightVector = lightSource.getPosition().getlocationVector().sub(hitPoint.getlocationVector())
                    .normalize();
                
                  
            // Shadow ray from hit point towards light source
            Ray shadowRay = new Ray(hitPoint, lightVector);

            // Check if shadow ray intersects with any object
            HitRecord shadowRecord =  new HitRecord();
            boolean inShadow = scene.hit(shadowRay, 0.1, Double.POSITIVE_INFINITY, shadowRecord);

            // Get coefficients from material
            Color diffuseColor = material.getDiffuse();
            Color specularColor = material.getSpecular();
            double specularExponent = material.getSpecularExp();
            double dissolve = material.getTransparency();

            if (!inShadow) {
            // Calculate diffuse color
            double diffuseIntensity = Math.max(0, interpolatedNormal.scalar(lightVector));
            Color diffuse = new Color((int) (lightColor.getRed() * (diffuseColor.getRed() / 255.0) * diffuseIntensity),
                    (int) (lightColor.getGreen() * (diffuseColor.getGreen() / 255.0) * diffuseIntensity),
                    (int) (lightColor.getBlue() * (diffuseColor.getBlue() / 255.0) * diffuseIntensity));
            color = new Color(
                    Math.min(255, color.getRed() + diffuse.getRed()),
                    Math.min(255, color.getGreen() + diffuse.getGreen()),
                    Math.min(255, color.getBlue() + diffuse.getBlue()));

            // Calculate specular color using Blinn-Phong shading
            Vector3D viewVector = record.getViewRayDirection().mult(-1).normalize();
            Vector3D halfwayVector = lightVector.add(viewVector).normalize();
            double specularIntensity = specularExponent > 0
                ? Math.pow(Math.max(0, interpolatedNormal.scalar(halfwayVector)), specularExponent)
                : 0.0;
            Color specular = new Color(
                (int) (lightColor.getRed() * (specularColor.getRed() / 255.0) * specularIntensity),
                (int) (lightColor.getGreen() * (specularColor.getGreen() / 255.0) * specularIntensity),
                (int) (lightColor.getBlue() * (specularColor.getBlue() / 255.0) * specularIntensity));

            color = new Color(
                Math.min(255, color.getRed() + specular.getRed()),
                Math.min(255, color.getGreen() + specular.getGreen()),
                Math.min(255, color.getBlue() + specular.getBlue()));

            // Dissolve - if entity is see-through
            if (dissolve < 1) {
                color = mixColors(color, backgroundColor, dissolve);
                }
            }
            finalColor = new Color(
                    Math.min(255, ambientColor.getRed() + color.getRed()),
                    Math.min(255, ambientColor.getGreen() + color.getGreen()),
                    Math.min(255, ambientColor.getBlue() + color.getBlue()));
        
    }
        return finalColor;
    }

    /**
     * Mixes two colors based on a dissolve factor.
     * 
     * @param color1 first color
     * @param color2 second color
     * @param dissolve dissolve factor
     * @return mixed color
     */
    private Color mixColors(Color color1, Color color2, double dissolve) {
        int red = (int) (color1.getRed() * dissolve + color2.getRed() * (1 - dissolve));
        int green = (int) (color1.getGreen() * dissolve + color2.getGreen() * (1 - dissolve));
        int blue = (int) (color1.getBlue() * dissolve + color2.getBlue() * (1 - dissolve));
        return new Color(red, green, blue);
    }

    /**
     * Calculates the barycentric coordinates of a point in a triangle.
     * 
     * @param p point
     * @param v0 vertex 0
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return barycentric coordinates
     */
    private double[] calculateBarycentric(Vertex3D p, Vertex3D v0, Vertex3D v1, Vertex3D v2) {
        Vector3D v0v1 = v1.getlocationVector().sub(v0.getlocationVector());
        Vector3D v0v2 = v2.getlocationVector().sub(v0.getlocationVector());
        Vector3D v0p = p.getlocationVector().sub(v0.getlocationVector());

        double d00 = v0v1.scalar(v0v1);
        double d01 = v0v1.scalar(v0v2);
        double d11 = v0v2.scalar(v0v2);
        double d20 = v0p.scalar(v0v1);
        double d21 = v0p.scalar(v0v2);

        double denom = d00 * d11 - d01 * d01;
        double v = (d11 * d20 - d01 * d21) / denom;
        double w = (d00 * d21 - d01 * d20) / denom;
        double u = 1.0 - v - w;

        return new double[] { u, v, w };
    }

}