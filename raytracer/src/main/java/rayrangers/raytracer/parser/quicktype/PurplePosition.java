package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class PurplePosition {
    private long y;

    @JsonProperty("y")
    public long getY() { return y; }
    @JsonProperty("y")
    public void setY(long value) { this.y = value; }
}
