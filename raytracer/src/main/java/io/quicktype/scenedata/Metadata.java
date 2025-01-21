package io.quicktype.scenedata;

import com.fasterxml.jackson.annotation.*;

public class Metadata {
    private long[] backgroundColor;
    private boolean globalIllumination;
    private String renderMode;
    private long maxDepth;
    private long samplesPerPixel;

    @JsonProperty("backgroundColor")
    public long[] getBackgroundColor() { return backgroundColor; }
    @JsonProperty("backgroundColor")
    public void setBackgroundColor(long[] value) { this.backgroundColor = value; }

    @JsonProperty("globalIllumination")
    public boolean getGlobalIllumination() { return globalIllumination; }
    @JsonProperty("globalIllumination")
    public void setGlobalIllumination(boolean value) { this.globalIllumination = value; }

    @JsonProperty("renderMode")
    public String getRenderMode() { return renderMode; }
    @JsonProperty("renderMode")
    public void setRenderMode(String value) { this.renderMode = value; }

    @JsonProperty("maxDepth")
    public long getMaxDepth() { return maxDepth; }
    @JsonProperty("maxDepth")
    public void setMaxDepth(long value) { this.maxDepth = value; }

    @JsonProperty("samplesPerPixel")
    public long getSamplesPerPixel() { return samplesPerPixel; }
    @JsonProperty("samplesPerPixel")
    public void setSamplesPerPixel(long value) { this.samplesPerPixel = value; }
}
