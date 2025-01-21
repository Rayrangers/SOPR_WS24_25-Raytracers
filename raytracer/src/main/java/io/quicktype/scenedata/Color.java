package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;

public class Color {
    private long r;
    private long g;
    private long b;

    @JsonProperty("r")
    public long getR() { return r; }
    @JsonProperty("r")
    public void setR(long value) { this.r = value; }

    @JsonProperty("g")
    public long getG() { return g; }
    @JsonProperty("g")
    public void setG(long value) { this.g = value; }

    @JsonProperty("b")
    public long getB() { return b; }
    @JsonProperty("b")
    public void setB(long value) { this.b = value; }
}
