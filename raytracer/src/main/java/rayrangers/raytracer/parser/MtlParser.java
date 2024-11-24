package rayrangers.raytracer.parser;

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
 * https://github.com/UtkuOktay/Ray-Tracer/blob/main/src/main/java/com/utils/MTLParser.java
 */
public class MtlParser {

    /**
     * Parses a Wavefront MTL file at the given location.
     * 
     * @param mtlPath relative path to the MTL file
     * @return HashMap with material name and Material object
     * @throws FileNotFoundException Thrown if MTL file referenced in OBJ file is not present
     * @throws IOException Thrown if there is any error while reading the file
     */
    public static Map<String, Material> parseMaterialFile(String mtlPath) throws FileNotFoundException, IOException {
        Map<String, Material> materials = new HashMap<>();
        String currentMaterialName = null;

        double ns = Integer.MIN_VALUE;
        double d = Integer.MIN_VALUE;
        int illum = Integer.MIN_VALUE;

        File file = new File(mtlPath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            line = br.readLine();
            if (line != null) {
                line = line.split("#", 2)[0]; // Sanitize any comments (also at end of line)
                if (line.length() > 0) { // Full line comments will result in empty string
                    String[] data = line.split("\\s+"); // Split by any number of whitespace chars
                    switch (data[0]) {
                        case "newmtl":
                            if (currentMaterialName != null) {
                                materials.put(currentMaterialName,
                                        new Material(currentMaterialName, null, null, null, ns, d, illum));
                                // TODO: Reset all values for material
                            }
                            currentMaterialName = data[1];
                            break;
                        case "Ka": // Ambient color
                            // TODO
                            break;
                        case "Kd": // Diffuse color
                            // TODO
                            break;
                        case "Ks": // Specular color
                            // TODO
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
            }
            // TODO: Add color based values
            materials.put(currentMaterialName, new Material(currentMaterialName, null, null, null, ns, d, illum));
        }
        br.close();
        return materials;
    }
}
