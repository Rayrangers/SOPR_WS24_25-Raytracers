package rayrangers.raytracer;

/**
 * Represents some kind of material described by a Wavefront MTL file.
 */
public class Material {

    /**
     * Material name.
     * OBJ file:
     * usemtl <material-name>
     */
    private String name;

    /**
     * Class constructor specifying the material name.
     * @param name  material name 
     */
    public Material(String name) {
        this.name = name;
    }

    /**
     * Returns the material name.
     * @return  material name
     */
    private String getName() {
        return name;
    }

    /**
     * Sets the material name.
     * @param name  material name
     */
    private void setName(String name) {
        this.name = name;
    }
}