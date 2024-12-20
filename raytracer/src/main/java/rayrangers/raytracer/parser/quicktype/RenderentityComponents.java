package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.*;

public class RenderentityComponents {
    private RenderComponent renderComponent;

    @JsonProperty("RenderComponent")
    public RenderComponent getRenderComponent() { return renderComponent; }
    @JsonProperty("RenderComponent")
    public void setRenderComponent(RenderComponent value) { this.renderComponent = value; }
}
