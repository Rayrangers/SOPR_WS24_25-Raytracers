package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class CameraentityTranslation {
    private RotationClass position;
    private RotationClass rotation;

    @JsonProperty("position")
    public RotationClass getPosition() { return position; }
    @JsonProperty("position")
    public void setPosition(RotationClass value) { this.position = value; }

    @JsonProperty("rotation")
    public RotationClass getRotation() { return rotation; }
    @JsonProperty("rotation")
    public void setRotation(RotationClass value) { this.rotation = value; }
}
