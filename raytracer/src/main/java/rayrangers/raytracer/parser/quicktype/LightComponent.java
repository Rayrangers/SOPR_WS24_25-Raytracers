package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class LightComponent {
    private double intensity;
    private Color color;

    @JsonProperty("intensity")
    public double getIntensity() { return intensity; }
    @JsonProperty("intensity")
    public void setIntensity(double value) { this.intensity = value; }

    @JsonProperty("color")
    public Color getColor() { return color; }
    @JsonProperty("color")
    public void setColor(Color value) { this.color = value; }
}
