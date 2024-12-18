package rayrangers.raytracer.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import rayrangers.raytracer.world.Scene;
import rayrangers.raytracer.parser.quicktype.*;

import java.io.File;
import java.io.IOException;

public class SceneParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parses a JSON file into a Scene object.
     *
     * @param filePath the path to the JSON file
     * @return a Scene object populated with data from the JSON file
     * @throws IOException if there is an issue reading or parsing the file
     */
    public static Scene parseSceneFromJson(String filePath) throws IOException {
        // Step 1: Read the JSON file into Quicktype-generated classes
        SceneData sceneData = objectMapper.readValue(new File(filePath), SceneData.class);

        // Step 2: Map the Quicktype classes to the custom Scene class
        return mapToScene(sceneData);
    }

    /**
     * Saves a Scene object to a JSON file.
     *
     * @param scene the Scene object to save
     * @param filePath the path to the JSON file
     * @throws IOException if there is an issue writing the file
     */
    public static void saveSceneToJson(Scene scene, String filePath) throws IOException {
        // Step 1: Map the custom Scene class to Quicktype-generated classes
        SceneData sceneData = mapToSceneData(scene);

        // Step 2: Write the Quicktype classes to a JSON file
        objectMapper.writeValue(new File(filePath), sceneData);
    }

    /**
     * Maps a SceneData object (from Quicktype) to a Scene object.
     *
     * @param sceneData the SceneData object to map
     * @return a Scene object populated with data from the SceneData object
     */
    private static Scene mapToScene(SceneData sceneData) {
        Scene scene = new Scene();
        // Map data from SceneData to the custom Scene class
        // Example (simplified): Set the camera from the first Cameraentity object
        scene.setCamera(mapToCamera(sceneData.getCameraentity()[0]));
        // Map additional attributes here...
        return scene;
    }

    /**
     * Maps a Scene object to a SceneData object (for JSON serialization).
     *
     * @param scene the Scene object to map
     * @return a SceneData object populated with data from the Scene object
     */
    private static SceneData mapToSceneData(Scene scene) {
        SceneData sceneData = new SceneData();
        // Map data from the custom Scene class to the Quicktype SceneData class
        // Example (simplified): Convert `Camera` to `Cameraentity` and set it
        sceneData.setCameraentity(new Cameraentity[]{ mapToCameraentity(scene.getCamera()) });
        // Map additional attributes here...
        return sceneData;
    }

    /**
     * Maps a custom Camera object to a Quicktype Cameraentity object.
     *
     * @param camera the custom Camera object to map
     * @return a Cameraentity object populated with data from the Camera object
     */
    private static Cameraentity mapToCamera(rayrangers.raytracer.world.Camera camera) {
        Cameraentity cameraEntity = new Cameraentity();
        cameraEntity.setName(camera.getName());
        cameraEntity.setUUID(camera.getUuid());
        // Map additional attributes here...
        return cameraEntity;
    }

    /**
     * Maps a Quicktype Cameraentity object to a custom Camera object.
     *
     * @param cameraEntity the Cameraentity object to map
     * @return a Camera object populated with data from the Cameraentity object
     */
    private static rayrangers.raytracer.world.Camera mapToCamera(Cameraentity cameraEntity) {
        rayrangers.raytracer.world.Camera camera = new rayrangers.raytracer.world.Camera();
        camera.setName(cameraEntity.getName());
        camera.setUuid(cameraEntity.getUUID());
        // Map additional attributes here...
        return camera;
    }
}
