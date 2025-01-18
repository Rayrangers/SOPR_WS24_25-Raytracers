package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class Resource {
    private long uuid;
    private String type;
    private String path;

    @JsonProperty("uuid")
    public long getUUID() { return uuid; }
    @JsonProperty("uuid")
    public void setUUID(long value) { this.uuid = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("path")
    public String getPath() { return path; }
    @JsonProperty("path")
    public void setPath(String value) { this.path = value; }
}
