package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;
import java.util.UUID;

public class Resource {
    private UUID uuid;
    private String type;
    private String path;

    @JsonProperty("uuid")
    public UUID getUUID() { return uuid; }
    @JsonProperty("uuid")
    public void setUUID(UUID value) { this.uuid = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("path")
    public String getPath() { return path; }
    @JsonProperty("path")
    public void setPath(String value) { this.path = value; }
}
