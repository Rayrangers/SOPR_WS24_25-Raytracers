package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class CameraentityComponents {
    private CameraComponent cameraComponent;

    @JsonProperty("CameraComponent")
    public CameraComponent getCameraComponent() { return cameraComponent; }
    @JsonProperty("CameraComponent")
    public void setCameraComponent(CameraComponent value) { this.cameraComponent = value; }
}
