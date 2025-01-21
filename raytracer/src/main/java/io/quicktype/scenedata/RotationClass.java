package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;

public class RotationClass {
    private double x;
    private Double y;
    private Double z;

    @JsonProperty("x")
    public double getX() { return x; }
    @JsonProperty("x")
    public void setX(double value) { this.x = value; }

    @JsonProperty("y")
    public Double getY() { return y; }
    @JsonProperty("y")
    public void setY(Double value) { this.y = value; }

    @JsonProperty("z")
    public Double getZ() { return z; }
    @JsonProperty("z")
    public void setZ(Double value) { this.z = value; }
}
