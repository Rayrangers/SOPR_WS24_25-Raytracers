package rayrangers.raytracer.parser.quicktype;

/**
 * First attempt of Quicktype Parsing
 **/

import com.fasterxml.jackson.annotation.*;

public class SampleQuicktype {
    private String greeting;
    private String[] instructions;

    @JsonProperty("greeting")
    public String getGreeting() { return greeting; }
    @JsonProperty("greeting")
    public void setGreeting(String value) { this.greeting = value; }

    @JsonProperty("instructions")
    public String[] getInstructions() { return instructions; }
    @JsonProperty("instructions")
    public void setInstructions(String[] value) { this.instructions = value; }
}
