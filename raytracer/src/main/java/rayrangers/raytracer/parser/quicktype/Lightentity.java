package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class Lightentity {
    private String name;
    private long uuid;
    private LightentityTranslation translation;
    private LightentityComponents components;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("uuid")
    public long getUUID() { return uuid; }
    @JsonProperty("uuid")
    public void setUUID(long value) { this.uuid = value; }

    @JsonProperty("Translation")
    public LightentityTranslation getTranslation() { return translation; }
    @JsonProperty("Translation")
    public void setTranslation(LightentityTranslation value) { this.translation = value; }

    @JsonProperty("components")
    public LightentityComponents getComponents() { return components; }
    @JsonProperty("components")
    public void setComponents(LightentityComponents value) { this.components = value; }
}
