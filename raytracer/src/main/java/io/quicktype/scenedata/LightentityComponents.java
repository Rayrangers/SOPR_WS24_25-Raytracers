package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;

public class LightentityComponents {
    private LightComponent lightComponent;

    @JsonProperty("LightComponent")
    public LightComponent getLightComponent() { return lightComponent; }
    @JsonProperty("LightComponent")
    public void setLightComponent(LightComponent value) { this.lightComponent = value; }
}
