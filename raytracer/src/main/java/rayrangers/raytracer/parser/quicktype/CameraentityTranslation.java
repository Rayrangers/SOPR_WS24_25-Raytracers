package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.world.Camera;

public class CameraentityTranslation {
    private Vertex3D position;
    private RotationClass rotation;

    @JsonProperty("position")
    public Vertex3D getPosition() {
        return position;
    }
    @JsonProperty("position")
    public void setPosition(Vertex3D position) {
        this.position = position;
    }

    @JsonProperty("rotation")
    public RotationClass getRotation() { return rotation; }
    @JsonProperty("rotation")
    public void setRotation(RotationClass value) { this.rotation = value; }
}
