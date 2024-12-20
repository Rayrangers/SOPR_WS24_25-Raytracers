package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class Renderentity {
    private String name;
    private long uuid;
    private RenderentityTranslation translation;
    private RenderentityComponents components;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("uuid")
    public long getUUID() { return uuid; }
    @JsonProperty("uuid")
    public void setUUID(long value) { this.uuid = value; }

    @JsonProperty("Translation")
    public RenderentityTranslation getTranslation() { return translation; }
    @JsonProperty("Translation")
    public void setTranslation(RenderentityTranslation value) { this.translation = value; }

    @JsonProperty("components")
    public RenderentityComponents getComponents() { return components; }
    @JsonProperty("components")
    public void setComponents(RenderentityComponents value) { this.components = value; }
}
