package rayrangers.raytracer.parser;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import io.quicktype.scenedata.Cameraentity;
import io.quicktype.scenedata.CameraentityTranslation;
import io.quicktype.scenedata.Converter;
import io.quicktype.scenedata.Lightentity;
import io.quicktype.scenedata.Renderentity;
import io.quicktype.scenedata.Resource;
import io.quicktype.scenedata.SceneData;
import rayrangers.raytracer.math.TrafoMatrix;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.world.Entity;
import rayrangers.raytracer.world.LightSource;
import rayrangers.raytracer.world.Scene;

public class SceneParser {
    public static Scene parseScene(String jsonPath) throws Exception {
        Path path = Paths.get(jsonPath);
        String jsonString = Files.readString(path, StandardCharsets.UTF_8);
        SceneData data = Converter.fromJsonString(jsonString);
        long[] backgroundColorData = data.getMetadata().getBackgroundColor();
        Scene scene = new Scene(new java.awt.Color(Long.valueOf(backgroundColorData[0]).intValue(),
                Long.valueOf(backgroundColorData[1]).intValue(),
                Long.valueOf(backgroundColorData[2]).intValue()));

        for (Cameraentity ce : data.getCameraentity()) {
            CameraentityTranslation translation = ce.getTranslation();
            Vertex3D worldPosition = new Vertex3D(translation.getPosition().getX(), translation.getPosition().getY(),
                    translation.getPosition().getZ());

            Camera camera = new Camera(worldPosition, translation.getRotation().getX(),
                    translation.getRotation().getY(), translation.getRotation().getZ(),
                    ce.getComponents().getCameraComponent().getPaneDistance(),
                    ce.getComponents().getCameraComponent().getPaneWidth(),
                    (int) ce.getComponents().getCameraComponent().getResX(),
                    (int) ce.getComponents().getCameraComponent().getResY());
            scene.addCamera(camera);
        }

        for (Renderentity re : data.getRenderentity()) {
            UUID objUUID = re.getComponents().getRenderComponent().getObjUUID();
            for (Resource resource : data.getResources()) {
                if (resource.getUUID().equals(objUUID)) {
                    Entity entity = ObjParser.parseObjFile(resource.getPath());
                    TrafoMatrix tm = new TrafoMatrix(re.getTranslation().getPosition().getX(),
                            re.getTranslation().getPosition().getY(), re.getTranslation().getPosition().getZ(),
                            re.getTranslation().getRotation().getX(), re.getTranslation().getRotation().getY(),
                            re.getTranslation().getRotation().getZ(), re.getTranslation().getScale().getX(),
                            re.getTranslation().getScale().getY(), re.getTranslation().getScale().getZ());
                    entity.transform(tm);
                    scene.addEntity(entity);
                }
            }

            for (Lightentity le : data.getLightentity()) {
                UUID lightUUID = le.getUUID();
                Vertex3D lightPosition = new Vertex3D(le.getTranslation().getPosition().getX(),
                        le.getTranslation().getPosition().getY(), le.getTranslation().getPosition().getZ());
                java.awt.Color lightColor = new java.awt.Color(
                        Long.valueOf(le.getComponents().getLightComponent().getColor().getR()).intValue(),
                        Long.valueOf(le.getComponents().getLightComponent().getColor().getG()).intValue(),
                        Long.valueOf(le.getComponents().getLightComponent().getColor().getB()).intValue());
                LightSource lightSource = new LightSource(lightUUID,
                        le.getComponents().getLightComponent().getIntensity(), lightPosition, lightColor);
                scene.addLightSource(lightSource);
            }

        }
        return scene;
    }
}