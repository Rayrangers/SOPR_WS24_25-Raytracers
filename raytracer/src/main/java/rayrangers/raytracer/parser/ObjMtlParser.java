package rayrangers.raytracer.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjMtlParser {
    // TODO: change form void to Shape when PR-3 is merged
    public static void parseObjFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        while (line != null) {
            // TODO: Shape parsedShape = ...;
            line = br.readLine();
            if (line != null) {
                line = line.split("#", 2)[0]; // Sanitize any comments (also at end of line)
                if (line.length() > 0) { // Full line comments will result in empty String
                    String[] data = line.split("\\s+"); // Split by any number of whitespace chars
                    switch (data[0]) { // analyze first identifier value
                        case "mtllib": // Reference to external material definition
                            parseMaterial(data[1]);
                            break;
                        case "v":
                            parseVertex(data);
                            break;
                        case "vn":
                            parseVertexNormals(data);
                            break;
                        case "vt":
                            parseVertexTexture(data);
                            break;
                        case "f": // Face
                            parseFace(data);
                            break;
                        default: // Ignore unused params, e.g.: groups ('g')
                            System.out.println("Ignored: " + Arrays.toString(data));
                            break;
                    }
                }
            }
        }
        br.close();
    }

    private static void parseVertexTexture(String[] data) {
        // TODO: Implement
        System.out.println(Arrays.toString(data));
    }

    private static void parseVertexNormals(String[] data) {
        // TODO: Implement
        System.out.println(Arrays.toString(data));
    }

    private static void parseMaterial(String materialName) {
        // TODO: Implement
        System.out.println(materialName);
    }

    private static void parseVertex(String[] data) {
        // TODO: Implement
        System.out.println(Arrays.toString(data));
    }

    private static void parseFace(String[] data) {
        System.out.println(Arrays.toString(data));
        List<String[]> parsedFaceVertices = new ArrayList<>();
        for (int i = 1; i < data.length; i++) {
            String currCoord = data[i];
            if (currCoord.length() == currCoord.replace("/", "").length()) { // Only vertex coordinates
                // TODO: Add only vertices
                System.out.println("Only vertices");
            } else {
                parsedFaceVertices.add(currCoord.split("/"));
            }
        }
        for (String[] s : parsedFaceVertices) {
            System.out.println("    " + Arrays.toString(s));
        }
    }

    // TODO: Remove main method
    public static void main(String[] args) {
        try {
            parseObjFile(new File("examples/3d-cubes/cube-tex.obj"));
            parseObjFile(new File("examples/3d-cubes/cube.obj"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
