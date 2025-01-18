package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class RenderComponent {
    private long objUUID;
    private long matUUID;

    @JsonProperty("objUUID")
    public long getObjUUID() { return objUUID; }
    @JsonProperty("objUUID")
    public void setObjUUID(long value) { this.objUUID = value; }

    @JsonProperty("matUUID")
    public long getMatUUID() { return matUUID; }
    @JsonProperty("matUUID")
    public void setMatUUID(long value) { this.matUUID = value; }
}
