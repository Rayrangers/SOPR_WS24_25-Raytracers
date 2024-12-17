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
    private static final Color AMBIENT_COLOR = new Color(15,15,15);

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

        // get material, normal vector, view ray direction and hit point from hit record
        Material material = record.getMaterial();
        Vector3D normalVector = record.getNormalVector();
        Vector3D viewRayDirection = record.getViewRayDirection();
        Vertex3D hitPoint = record.getHitPoint();

        // get ambient coefficients from material
        Color ambientCoefficients = material.getAmbient();

        // calculate ambient color
        Color ambientColor = new Color((int) (AMBIENT_COLOR.getRed() * (ambientCoefficients.getRed() / 255.0)),
        (int) (AMBIENT_COLOR.getGreen() * (ambientCoefficients.getGreen() / 255.0)),
        (int) (AMBIENT_COLOR.getBlue() * (ambientCoefficients.getBlue() / 255.0)));

        Color finalColor = new Color(0,0,0); 


        //Start with ambient color if illum == 1  
        Color color = new Color(ambientColor.getRed(), ambientColor.getGreen(), ambientColor.getBlue());
         
        // iterate over all light sources
        for (LightSource lightSource : lightSources.values()) {

            //get light source position, color and intensity
            Color lightColor = lightSource.getColor();


            // calculate light vector
            Vector3D lightVector = lightSource.getPosition().getlocationVector().sub(hitPoint.getlocationVector()).normalize();


            // get diffuse/specular coefficients from material
            Color diffuseColor = material.getDiffuse();
            Color specularColor = material.getSpecular();
            double specularExponent = material.getSpecularExp();
            int illuminationModel = material.getIllum();
            double dissolve = material.getTransparency();


            if(illuminationModel >= 2){
            //calculate diffuse color
                double diffuseIntensity = Math.max(0, normalVector.scalar(lightVector));
                Color diffuse = new Color((int) (lightColor.getRed() * (diffuseColor.getRed() / 255.0) * diffuseIntensity),
                    (int) (lightColor.getGreen() * (diffuseColor.getGreen() / 255.0) * diffuseIntensity),
                    (int) (lightColor.getBlue() * (diffuseColor.getBlue() / 255.0) * diffuseIntensity));
                color = new Color(
                    Math.min(255, color.getRed() + diffuse.getRed()),
                    Math.min(255, color.getGreen() + diffuse.getGreen()),
                    Math.min(255, color.getBlue() + diffuse.getBlue())
            );
            }



            if(illuminationModel >= 3){
            //calculate specular color
                Vector3D reflectionVector = normalVector.mult(2 * lightVector.scalar(normalVector)).sub(lightVector).normalize();
                double specularIntensity = Math.pow(Math.max(0, reflectionVector.scalar(reflectionVector)), specularExponent);
                Color specular = new Color((int) (lightColor.getRed() * (specularColor.getRed() / 255.0) * specularIntensity),
                    (int) (lightColor.getGreen() * (specularColor.getGreen() / 255.0) * specularIntensity),
                    (int) (lightColor.getBlue() * (specularColor.getBlue() / 255.0) * specularIntensity));

                color = new Color(
                    Math.min(255, color.getRed() + specular.getRed()),
                    Math.min(255, color.getGreen() + specular.getGreen()),
                    Math.min(255, color.getBlue() + specular.getBlue())
                    );       
            }




           /*  finalColor = new Color(
                Math.min(255, ambientColor.getRed() + diffuse.getRed() + specular.getRed()),
                Math.min(255, ambientColor.getGreen() + diffuse.getGreen() + specular.getGreen()),
                Math.min(255, ambientColor.getBlue() + diffuse.getBlue() + specular.getBlue())
            ); */
            
            

            if(dissolve < 1){
                Color backgroundColor = new Color(0,0,0);
                color = mixColors(color, backgroundColor, dissolve);
            }

            finalColor = new Color(
                Math.min(255, ambientColor.getRed() + color.getRed()),
                Math.min(255, ambientColor.getGreen() + color.getGreen()),
                Math.min(255, ambientColor.getBlue() + color.getBlue())
            );
        }
        
            
        //return ambientColor;
        return finalColor;
    }


    private Color mixColors(Color color1, Color color2, double dissolve){
        int red = (int) (color1.getRed() * dissolve + color2.getRed() * (1 - dissolve));
        int green = (int) (color1.getGreen() * dissolve + color2.getGreen() * (1 - dissolve));
        int blue = (int) (color1.getBlue() * dissolve + color2.getBlue() * (1 - dissolve));
        return new Color(red, green, blue);
    }
}