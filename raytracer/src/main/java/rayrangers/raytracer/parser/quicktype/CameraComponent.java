package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class CameraComponent {
    private long fov;
    private double aspectRatio;
    private double nearClip;
    private long farClip;

    @JsonProperty("fov")
    public long getFov() { return fov; }
    @JsonProperty("fov")
    public void setFov(long value) { this.fov = value; }

    @JsonProperty("aspectRatio")
    public double getAspectRatio() { return aspectRatio; }
    @JsonProperty("aspectRatio")
    public void setAspectRatio(double value) { this.aspectRatio = value; }

    @JsonProperty("nearClip")
    public double getNearClip() { return nearClip; }
    @JsonProperty("nearClip")
    public void setNearClip(double value) { this.nearClip = value; }

    @JsonProperty("farClip")
    public long getFarClip() { return farClip; }
    @JsonProperty("farClip")
    public void setFarClip(long value) { this.farClip = value; }
}
