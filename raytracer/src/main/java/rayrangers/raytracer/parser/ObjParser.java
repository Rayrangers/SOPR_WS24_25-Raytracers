package rayrangers.raytracer.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.world.Entity;
import rayrangers.raytracer.world.Face;
import rayrangers.raytracer.world.Material;
import rayrangers.raytracer.world.Triangle;

/**
 * Parser for a Wavefront OBJ file.
 * 
 * Inspired by
 * @see <a href="https://github.com/UtkuOktay/Ray-Tracer/blob/main/src/main/java/com/utils/OBJParser.java">UtkuOktay</a>
 */
public class ObjParser {

    // Temporary parsing data structures
    private static String entityName;
    private static Map<String, Material> materials;
    private static List<Vertex3D> vertices;
    private static List<Vector3D> normalVectors;
    private static List<Face> faces;
    private static Material currentMaterial;
    private static String currentSmoothingGroup;

    // TODO: Maybe default material?
    /**
     * Parses a Wavefront OBJ file at the given location.
     * 
     * @param filePath relative path to the OBJ file
     * @return parsed entity
     * @throws FileNotFoundException if the OBJ file cannot be found
     * @throws IOException if there is any error while reading the file
     */
    public static Entity parseObjFile(String filePath) throws FileNotFoundException, IOException {

        // Reset temporary parsing data structures
        entityName = null;
        materials = new HashMap<>();
        vertices = new ArrayList<>();
        normalVectors = new ArrayList<>();
        faces = new ArrayList<>();
        currentMaterial = null;
        currentSmoothingGroup = null;

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            line = line.split("#", 2)[0]; // Sanitize any comments (also at end of line)
            if (line.length() > 0) { // Full line comments will result in empty string
                String[] data = line.split("\\s+"); // Split by any number of whitespace chars
                switch (data[0]) { // Analyze first identifier value
                    case "o": // Given entity name (optional)
                        entityName = data[1];
                        break;
                    case "mtllib": // Reference to external material definition
                        String mtlFilePath = filePath.substring(0, filePath.lastIndexOf(File.separator) + 1)
                                + data[1]; // Build mtl path from obj path and mtl file name
                        materials = MtlParser.parseMaterialFile(mtlFilePath); // Call mtl parser
                        break;
                    case "usemtl": // Material name
                        currentMaterial = materials.get(data[1]);
                        break;
                    case "v": // Vertex
                        vertices.add(parseVertex(data));
                        break;
                    case "vn": // Normal vector
                        normalVectors.add(parseVertexNormals(data));
                        break;
                    case "s": // Smoothing group
                        if (data[1].equalsIgnoreCase("off")) {
                            currentSmoothingGroup = null;
                        }
                        currentSmoothingGroup = data[1];
                        break;
                    case "f": // Face
                        faces.add(parseFace(data));
                        break;
                    default: // Ignore unused params, e.g. groups ('g')
                        break;
                }
            }
            line = br.readLine(); // Read next line in file
        }
        br.close();
        return new Entity(entityName, faces, vertices);
    }

    private static Vertex3D parseVertex(String[] data) {
        return new Vertex3D(
                Double.parseDouble(data[1]),
                Double.parseDouble(data[2]),
                Double.parseDouble(data[3]));
    }

    private static Vector3D parseVertexNormals(String[] data) {
        return new Vector3D(
                Double.parseDouble(data[1]),
                Double.parseDouble(data[2]),
                Double.parseDouble(data[3]));
    }

    private static Face parseFace(String[] data) {
        if (data.length > 4) {
            // Not a triangle
            throw new UnsupportedOperationException("Polygons are not supported yet.");
        } else {
            if (data[1].length() == data[1].replace("/", "").length()) { // Check first data element
                                                                                            // for format f v1 v2 v3
                // "Shortcut": Only vertices in data element
                return new Triangle(
                        currentMaterial, currentSmoothingGroup,
                        vertices.get(Integer.parseInt(data[1]) - 1),
                        vertices.get(Integer.parseInt(data[2]) - 1),
                        vertices.get(Integer.parseInt(data[3]) - 1));
            }
            // Format is v/vt/vn OR v//vn
            List<Vertex3D> faceVertList = new ArrayList<>();
            for (int i = 1; i < data.length; i++) {
                String currCoord = data[i];
                List<String[]> parsedIndices = new ArrayList<>();
                parsedIndices.add(currCoord.split("/"));
                for (String[] idxEntry : parsedIndices) {
                    int vertIdx = Integer.parseInt(idxEntry[0]);
                    if (idxEntry.length == 3) { // Add normal vector for formats v/vt/vn OR v//vn
                        vertices.get(vertIdx - 1)
                                .setNormalVector(normalVectors.get(Integer.parseInt(idxEntry[2]) - 1));
                    }
                    faceVertList.add(vertices.get(vertIdx - 1));
                    // TODO: Check for vt presence if textures are implemented
                }
            }
            return new Triangle(currentMaterial, currentSmoothingGroup, faceVertList);
        }
    }
}