package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class Cameraentity {
    private String name;
    private long uuid;
    private CameraentityTranslation translation;
    private CameraentityComponents components;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("uuid")
    public long getUUID() { return uuid; }
    @JsonProperty("uuid")
    public void setUUID(long value) { this.uuid = value; }

    @JsonProperty("Translation")
    public CameraentityTranslation getTranslation() { return translation; }
    @JsonProperty("Translation")
    public void setTranslation(CameraentityTranslation value) { this.translation = value; }

    @JsonProperty("components")
    public CameraentityComponents getComponents() { return components; }
    @JsonProperty("components")
    public void setComponents(CameraentityComponents value) { this.components = value; }
}
