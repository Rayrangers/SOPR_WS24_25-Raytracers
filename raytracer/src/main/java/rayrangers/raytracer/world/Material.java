package rayrangers.raytracer.world;

import java.awt.*;

/**
 * Represents some kind of material described by a Wavefront MTL file.
 */
public class Material {

    /**
     * Material name.
     * OBJ file:
     * usemtl material-name
     * MTL file:
     * newmtl material-name
     */
    private final String name;
    
    /**
     * Ambient color definition with RGB values.
     * MTL file:
     * Ka <double R> <double G> <double B>
     */
    private final Color ka;

    /**
     * Diffuse color definition with RGB values.
     * MTL file:
     * Kd <double R> <double G> <double B>
     */
    private final Color kd;

    /**
     * Specular color definition with RGB values.
     * MTL file:
     * Ks <double R> <double G> <double B>
     */
    private final Color ks;
    
    /**
     * Specular exponent.
     * MTL file:
     * Ns <double specular-exponent>
     */
    private final double ns;

    /**
     * Transparency (dissolve) coefficient.
     * MTL file:
     * d <double transparency-coefficient>
     */
    private final double d;

    /**
     * Illumination model index.
     * MTL file:
     * illum <integer model-index>
     */
    private final int illum;

    /**
     * Constructs a material with the specified name and characteristics.
     * 
     * @param name material name 
     * @param ka ambient color (RGB)
     * @param kd diffuse color (RGB)
     * @param ks specular color (RGB)
     * @param ns specular exponent
     * @param d transparency coefficient
     * @param illum illumination model index
     */
    public Material(String name, Color ka, Color kd, Color ks, double ns, double d, int illum) {
        this.name = name;
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.ns = ns;
        this.d = d;
        this.illum = illum;
    }

    /**
     * Returns the transparency (dissolve) coefficient.
     * 
     * @return transparency coefficient
     */
    public double getTransparency() {
        return d;
    }

    /**
     * Returns the index of the illumination model.
     * 
     * @return illumination model index
     */
    public int getIllum() {
        return illum;
    }

    /**
     * Returns the ambient color.
     * 
     * @return array of RGB values for ambient color
     */
    public Color getAmbient() {
        return ka;
    }

    /**
     * Returns the diffuse color.
     * 
     * @return array of RGB values for diffuse color
     */
    public Color getDiffuse() {
        return kd;
    }

    /**
     * Returns the specular color.
     * 
     * @return array of RGB values for specular color
     */
    public Color getSpecular() {
        return ks;
    }

    /**
     * Returns the material name.
     * 
     * @return material name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the specular exponent.
     * 
     * @return specular exponent
     */
    public double getSpecularExp() {
        return ns;
    }
}