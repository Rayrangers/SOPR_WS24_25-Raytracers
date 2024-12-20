package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class LightentityTranslation {
    private PurplePosition position;

    @JsonProperty("position")
    public PurplePosition getPosition() { return position; }
    @JsonProperty("position")
    public void setPosition(PurplePosition value) { this.position = value; }
}
