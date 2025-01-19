package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;
import java.util.UUID;

public class Renderentity {
    private String name;
    private UUID uuid;
    private RenderentityTranslation translation;
    private RenderentityComponents components;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("uuid")
    public UUID getUUID() { return uuid; }
    @JsonProperty("uuid")
    public void setUUID(UUID value) { this.uuid = value; }

    @JsonProperty("Translation")
    public RenderentityTranslation getTranslation() { return translation; }
    @JsonProperty("Translation")
    public void setTranslation(RenderentityTranslation value) { this.translation = value; }

    @JsonProperty("components")
    public RenderentityComponents getComponents() { return components; }
    @JsonProperty("components")
    public void setComponents(RenderentityComponents value) { this.components = value; }
}
