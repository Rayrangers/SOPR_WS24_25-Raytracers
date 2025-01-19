package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;

public class SceneData {
    private Renderentity[] renderentity;
    private Lightentity[] lightentity;
    private Cameraentity[] cameraentity;
    private Resource[] resources;
    private Metadata metadata;

    @JsonProperty("renderentity")
    public Renderentity[] getRenderentity() { return renderentity; }
    @JsonProperty("renderentity")
    public void setRenderentity(Renderentity[] value) { this.renderentity = value; }

    @JsonProperty("lightentity")
    public Lightentity[] getLightentity() { return lightentity; }
    @JsonProperty("lightentity")
    public void setLightentity(Lightentity[] value) { this.lightentity = value; }

    @JsonProperty("cameraentity")
    public Cameraentity[] getCameraentity() { return cameraentity; }
    @JsonProperty("cameraentity")
    public void setCameraentity(Cameraentity[] value) { this.cameraentity = value; }

    @JsonProperty("resources")
    public Resource[] getResources() { return resources; }
    @JsonProperty("resources")
    public void setResources(Resource[] value) { this.resources = value; }

    @JsonProperty("metadata")
    public Metadata getMetadata() { return metadata; }
    @JsonProperty("metadata")
    public void setMetadata(Metadata value) { this.metadata = value; }
}
