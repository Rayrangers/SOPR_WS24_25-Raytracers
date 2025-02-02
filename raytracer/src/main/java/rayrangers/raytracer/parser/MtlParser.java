package rayrangers.raytracer.parser;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import rayrangers.raytracer.world.Material;

/**
 * Parser for a Wavefront MTL file.
 * 
 * Inspired by
 * @see <a href="https://github.com/UtkuOktay/Ray-Tracer/blob/main/src/main/java/com/utils/MTLParser.java">UtkuOktay</a>
 */
public class MtlParser {

    /**
     * Parses a Wavefront MTL file at the given location.
     * 
     * @param mtlPath relative path to the MTL file
     * @return map with the parsed material name and material object
     * @throws FileNotFoundException if the MTL file referenced in the OBJ file cannot be found
     * @throws IOException if there is any error while reading the file
     */
    public static Map<String, Material> parseMaterialFile(String mtlPath) throws FileNotFoundException, IOException {
        // Temporary parsing data structures
        Map<String, Material> materials = new HashMap<>();
        String currentMaterialName = null;
        double ns = Double.MIN_VALUE, d = Double.MIN_VALUE;
        int illum = Integer.MIN_VALUE;
        Color ka = null, kd = null, ks = null;

        File file = new File(mtlPath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            line = line.split("#", 2)[0]; // Sanitize any comments (also at end of line)
            if (line.length() > 0) { // Full line comments will result in empty string
                String[] data = line.split("\\s+"); // Split by any number of whitespace chars
                switch (data[0]) {
                    case "newmtl":
                        if (currentMaterialName != null) {
                            materials.put(currentMaterialName,
                                    new Material(currentMaterialName, ka, kd, ks, ns, d, illum));
                            // Reset all values for new material
                            ns = Double.MIN_VALUE;
                            d = Double.MIN_VALUE;
                            illum = Integer.MIN_VALUE;
                            ka = null;
                            kd = null;
                            ks = null;
                        }
                        currentMaterialName = data[1];
                        break;
                    case "Ka": // Ambient color
                        ka = parseColor(data);
                        break;
                    case "Kd": // Diffuse color
                        kd = parseColor(data);
                        break;
                    case "Ks": // Specular color
                        ks = parseColor(data);
                        break;
                    case "d": // Transparency coefficient
                        d = Double.parseDouble(data[1]);
                        break;
                    case "Ns": // Specular exponent
                        ns = Double.parseDouble(data[1]);
                        break;
                    case "illum": // Illumination model index
                        illum = Integer.parseInt(data[1]);
                        break;
                    default: // Ignore unused params
                        break;
                }
            }
            line = br.readLine(); // Read next line in file
        }
        materials.put(currentMaterialName, new Material(currentMaterialName, ka, kd, ks, ns, d, illum));
        br.close();
        return materials;
    }

    /**
     * Parses the colors associated with Ka (ambient color), Kd (diffuse color) and Ks (specular color).
     * The corresponding lines in the MTL file contain four components: the color name and all three RGB values.
     * Each line in the MTL file is passed as an array of strings containing these components.
     * 
     * @param data array of strings containing the color name and the RGB values
     * @return parsed color
     */
    private static Color parseColor(String[] data) {
        // Optional fourth alpha channel value is ignored
        return new Color(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3]));
    }
}
