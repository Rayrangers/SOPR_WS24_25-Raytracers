package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;

public class PurplePosition {
    private double y;

    @JsonProperty("y")
    public double getY() { return y; }
    @JsonProperty("y")
    public void setY(double value) { this.y = value; }
}
