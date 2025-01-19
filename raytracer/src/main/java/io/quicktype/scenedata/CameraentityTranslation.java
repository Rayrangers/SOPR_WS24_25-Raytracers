package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;

public class CameraentityTranslation {
    private Position position;
    private Position rotation;

    @JsonProperty("position")
    public Position getPosition() { return position; }
    @JsonProperty("position")
    public void setPosition(Position value) { this.position = value; }

    @JsonProperty("rotation")
    public Position getRotation() { return rotation; }
    @JsonProperty("rotation")
    public void setRotation(Position value) { this.rotation = value; }
}
