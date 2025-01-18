package rayrangers.raytracer.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.world.*;
import rayrangers.raytracer.parser.quicktype.*;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SceneParser {
    private final ObjectMapper objectMapper;

    public SceneParser() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Loads a Scene from a JSON file.
     *
     * @param filePath the path to the JSON file
     * @return the loaded Scene object
     * @throws IOException if the file cannot be read or parsed
     */
    public Scene loadSceneFromJson(String filePath) throws IOException {
        SceneData sceneData = this.objectMapper.readValue(new File(filePath), SceneData.class);
        return mapToScene(sceneData);
    }

    /**
     * Saves a Scene object to a JSON file.
     *
     * @param scene    the Scene object to save
     * @param filePath the path to the JSON file
     * @throws IOException if the file cannot be written
     */
    public void saveSceneToJson(Scene scene, String filePath) throws IOException {
        SceneData sceneData = mapToSceneData(scene);
        this.objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), sceneData);
    }

    /**
     * Maps SceneData to a Scene object.
     *
     * @param sceneData the SceneData to map
     * @return the mapped Scene object
     */
    private Scene mapToScene(SceneData sceneData) {
        // Create Scene with background color
        Color backgroundColor = Color.BLACK;
        if (sceneData.getMetadata() != null && sceneData.getMetadata().getBackgroundColor() != null) {
            double[] bgColor = sceneData.getMetadata().getBackgroundColor();
            backgroundColor = new Color(
                    (int) (bgColor[0] * 255),
                    (int) (bgColor[1] * 255),
                    (int) (bgColor[2] * 255)
            );
        }
        Scene scene = new Scene(backgroundColor);

        // Map Cameras
        if (sceneData.getCameraentity() != null) {
            for (Cameraentity cameraEntity : sceneData.getCameraentity()) {
                // Extract position
                Vertex3D position = new Vertex3D(
                        cameraEntity.getTranslation().getPosition().getX(),
                        cameraEntity.getTranslation().getPosition().getY(),
                        cameraEntity.getTranslation().getPosition().getZ()
                );

                // Extract rotation
                double angleX = cameraEntity.getTranslation().getRotation().getX();
                double angleY = cameraEntity.getTranslation().getRotation().getY();
                double angleZ = cameraEntity.getTranslation().getRotation().getZ();

                // Extract camera properties
                CameraComponent cameraComponent = cameraEntity.getComponents().getCameraComponent();
                double fov = cameraComponent.getFov();
                double aspectRatio = cameraComponent.getAspectRatio();
                double nearClip = cameraComponent.getNearClip();
                double farClip = cameraComponent.getFarClip();

                // Create Camera
                Camera camera = new Camera(
                        position, angleX, angleY, angleZ,
                        fov, fov / aspectRatio, (int) nearClip, (int) farClip
                );
                scene.addCamera(camera);
            }
        }

        // Map Entities
        if (sceneData.getRenderentity() != null) {
            for (Renderentity renderEntity : sceneData.getRenderentity()) {
                List<Vertex3D> vertices = new ArrayList<>();
                List<Face> faces = new ArrayList<>();

                Entity entity = new Entity(
                        new UUID(0, renderEntity.getUUID()),
                        renderEntity.getName(),
                        faces,
                        vertices
                );
                scene.addEntity(entity);
            }
        }

        // Map Light Sources
        if (sceneData.getLightentity() != null) {
            for (Lightentity lightEntity : sceneData.getLightentity()) {
                Vertex3D position = new Vertex3D(0.0, 0.0, 0.0);
                Color color = new Color(
                        lightEntity.getComponents().getLightComponent().getColor().getR(),
                        lightEntity.getComponents().getLightComponent().getColor().getG(),
                        lightEntity.getComponents().getLightComponent().getColor().getB()
                );
                LightSource lightSource = new LightSource(
                        lightEntity.getComponents().getLightComponent().getIntensity(),
                        position,
                        color
                );
                scene.addLightSource(lightSource);
            }
        }

        return scene;
    }

    /**
     * Maps a Scene object to SceneData for JSON serialization.
     *
     * @param scene the Scene object to map
     * @return the SceneData object
     */
    private SceneData mapToSceneData(Scene scene) {
        SceneData sceneData = new SceneData();

        // Map Background Color
        Color bgColor = scene.getBackgroundColor();
        double[] backgroundColor = {
                bgColor.getRed() / 255.0,
                bgColor.getGreen() / 255.0,
                bgColor.getBlue() / 255.0
        };
        Metadata metadata = new Metadata();
        metadata.setBackgroundColor(backgroundColor);
        sceneData.setMetadata(metadata);

        // Map Cameras
        List<Cameraentity> cameraEntities = new ArrayList<>();
        for (Camera camera : scene.getCameras()) {
            Cameraentity cameraEntity = new Cameraentity();

            // Translation mapping
            CameraentityTranslation translation = new CameraentityTranslation();
            translation.setPosition(camera.getWorldPosition());
            translation.setRotation(new RotationClass(
                    camera.getU().getX(),
                    camera.getU().getY(),
                    camera.getU().getZ()
            ));
            cameraEntity.setTranslation(translation);

            // Components mapping
            CameraentityComponents components = new CameraentityComponents();
            CameraComponent cameraComponent = new CameraComponent();
            cameraComponent.setFov(camera.getPaneDistance());
            cameraComponent.setAspectRatio(camera.getViewPane().getAspectRatio());
            components.setCameraComponent(cameraComponent);
            cameraEntity.setComponents(components);

            // Add to list
            cameraEntities.add(cameraEntity);
        }
        sceneData.setCameraentity(cameraEntities.toArray(new Cameraentity[0]));

        // Map Entities
        List<Renderentity> renderEntities = new ArrayList<>();
        for (Entity entity : scene.getEntities()) {
            Renderentity renderEntity = new Renderentity();
            renderEntity.setName(entity.getName());
            renderEntity.setUuid(entity.getUuid().getMostSignificantBits()); // Example UUID handling

            // Add to list
            renderEntities.add(renderEntity);
        }
        sceneData.setRenderentity(renderEntities.toArray(new Renderentity[0]));

        // Map Light Sources
        List<Lightentity> lightEntities = new ArrayList<>();
        for (LightSource light : scene.getLightSources()) {
            Lightentity lightEntity = new Lightentity();

            // Components mapping
            LightentityComponents components = new LightentityComponents();
            LightComponent lightComponent = new LightComponent();
            lightComponent.setIntensity(light.getIntensity());
            lightComponent.setColor(new rayrangers.raytracer.parser.quicktype.Color(
                    light.getColor().getRed(),
                    light.getColor().getGreen(),
                    light.getColor().getBlue()
            ));
            components.setLightComponent(lightComponent);
            lightEntity.setComponents(components);

            // Position mapping
            LightentityTranslation translation = new LightentityTranslation();
            translation.setPosition(light.getPosition());
            lightEntity.setTranslation(translation);

            // Add to list
            lightEntities.add(lightEntity);
        }
        sceneData.setLightentity(lightEntities.toArray(new Lightentity[0]));

        return sceneData;
    }

}
