package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;

public class LightentityTranslation {
    private Position position;

    @JsonProperty("position")
    public Position getPosition() { return position; }
    @JsonProperty("position")
    public void setPosition(Position value) { this.position = value; }
}
