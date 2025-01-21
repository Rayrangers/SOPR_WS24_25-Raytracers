package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;
import java.util.UUID;

public class Lightentity {
    private String name;
    private UUID uuid;
    private LightentityTranslation translation;
    private LightentityComponents components;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("uuid")
    public UUID getUUID() { return uuid; }
    @JsonProperty("uuid")
    public void setUUID(UUID value) { this.uuid = value; }

    @JsonProperty("Translation")
    public LightentityTranslation getTranslation() { return translation; }
    @JsonProperty("Translation")
    public void setTranslation(LightentityTranslation value) { this.translation = value; }

    @JsonProperty("components")
    public LightentityComponents getComponents() { return components; }
    @JsonProperty("components")
    public void setComponents(LightentityComponents value) { this.components = value; }
}
