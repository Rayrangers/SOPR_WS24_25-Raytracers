package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;
import java.util.UUID;

public class RenderComponent {
    private UUID objUUID;
    private UUID matUUID;

    @JsonProperty("objUUID")
    public UUID getObjUUID() { return objUUID; }
    @JsonProperty("objUUID")
    public void setObjUUID(UUID value) { this.objUUID = value; }

    @JsonProperty("matUUID")
    public UUID getMatUUID() { return matUUID; }
    @JsonProperty("matUUID")
    public void setMatUUID(UUID value) { this.matUUID = value; }
}
