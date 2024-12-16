package rayrangers.raytracer.parser.quicktype;

/**
 * Unit test for TestQuicktype class.
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class SampleQuicktypeTest {

    @Test
    void testJsonDeserializationFromFile() throws Exception {
        // Path to the JSON file
        String filePath = "src/test/java/rayrangers/raytracer/parser/quicktype/sample_quicktype.json";
        File jsonFile = new File(filePath);

        // Jackson ObjectMapper for deserialization
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize the JSON file into a SampleQuicktype object
        SampleQuicktype sample = objectMapper.readValue(jsonFile, SampleQuicktype.class);

        // Assertions: Verify the fields of the object
        assertNotNull(sample, "The SampleQuicktype object should not be null.");

        // Adjust the expected value to match the actual greeting in the JSON
        assertEquals("Welcome to quicktype!", sample.getGreeting(), "The greeting is incorrect.");

        // Expected instructions array
        String[] expectedInstructions = {
                "Type or paste JSON here",
                "Or choose a sample above",
                "Quicktype will generate code in your",
                "chosen language to parse the sample data"
        };

        // Compare the expected and actual instructions, ignoring case
        for (int i = 0; i < expectedInstructions.length; i++) {
            assertEquals(expectedInstructions[i].toLowerCase(), sample.getInstructions()[i].toLowerCase(),
                    "The instructions do not match (case-insensitive).");
        }

        // Print out the JSON content if the test is successful
        if (sample != null) {
            String jsonContent = new String(Files.readAllBytes(jsonFile.toPath()), StandardCharsets.UTF_8);
            System.out.println("JSON content from file:\n" + jsonContent);
        }

    }
}