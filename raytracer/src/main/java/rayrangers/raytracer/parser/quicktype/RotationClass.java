package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class RotationClass {
    private double x;
    private Long y;
    private Long z;

    @JsonProperty("x")
    public double getX() { return x; }
    @JsonProperty("x")
    public void setX(double value) { this.x = value; }

    @JsonProperty("y")
    public Long getY() { return y; }
    @JsonProperty("y")
    public void setY(Long value) { this.y = value; }

    @JsonProperty("z")
    public Long getZ() { return z; }
    @JsonProperty("z")
    public void setZ(Long value) { this.z = value; }
}
