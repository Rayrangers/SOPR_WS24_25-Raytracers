package rayrangers.raytracer.parser.quicktype;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SceneParserTest {

    private static String json;

    // Before all tests, read the JSON file into a string
    @BeforeAll
    static void setUp() throws IOException {
        // Load the JSON file from the resources directory
        json = new String(Files.readAllBytes(Paths.get("src/test/java/rayrangers/raytracer/parser/quicktype/sample_quicktype.json")));
    }

    @Test
    void testFromJsonString() throws IOException {
        // Call the fromJsonString method to parse the JSON
        SampleQuicktype sample = SceneParser.fromJsonString(json);

        // Assertions to verify that the data is parsed correctly
        assertNotNull(sample);
        assertEquals("Welcome to quicktype!", sample.getGreeting());
        assertNotNull(sample.getInstructions());
        assertEquals(4, sample.getInstructions().length);
        assertEquals("Type or paste JSON here", sample.getInstructions()[0]);
        assertEquals("quicktype will generate code in your", sample.getInstructions()[2]);
    }

    @Test
    void testToJsonString() throws Exception {
        // Create an object of SampleQuicktype
        SampleQuicktype sample = new SampleQuicktype();
        sample.setGreeting("Welcome to quicktype!");
        sample.setInstructions(new String[]{
                "Type or paste JSON here",
                "Or choose a sample above",
                "quicktype will generate code in your",
                "chosen language to parse the sample data"
        });

        // Convert the object to JSON string
        String jsonString = SceneParser.toJsonString(sample);

        // Assertions to verify that the JSON string is correct
        assertTrue(jsonString.contains("Welcome to quicktype!"));
        assertTrue(jsonString.contains("Type or paste JSON here"));
        assertTrue(jsonString.contains("quicktype will generate code in your"));
    }
}
