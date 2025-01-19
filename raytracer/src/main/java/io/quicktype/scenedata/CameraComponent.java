package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;

public class CameraComponent {
    private double paneDistance;
    private double aspectRatio;
    private double paneWidth;
    private long resX;
    private long resY;

    @JsonProperty("paneDistance")
    public double getPaneDistance() { return paneDistance; }
    @JsonProperty("paneDistance")
    public void setPaneDistance(double value) { this.paneDistance = value; }

    @JsonProperty("aspectRatio")
    public double getAspectRatio() { return aspectRatio; }
    @JsonProperty("aspectRatio")
    public void setAspectRatio(double value) { this.aspectRatio = value; }

    @JsonProperty("paneWidth")
    public double getPaneWidth() { return paneWidth; }
    @JsonProperty("paneWidth")
    public void setPaneWidth(double value) { this.paneWidth = value; }

    @JsonProperty("resX")
    public long getResX() { return resX; }
    @JsonProperty("resX")
    public void setResX(long value) { this.resX = value; }

    @JsonProperty("resY")
    public long getResY() { return resY; }
    @JsonProperty("resY")
    public void setResY(long value) { this.resY = value; }
}
