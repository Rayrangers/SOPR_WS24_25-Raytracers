package rayrangers.raytracer.parser.quicktype;

/**
 * Unit test for TestQuicktype class.
 **/

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SampleQuicktypeTest {

    @Test
    void testJsonDeserializationFromFile() throws Exception {
        // Pfad zur JSON-Datei
        String filePath = "src/test/java/rayrangers/raytracer/parser/quicktype/sample_quicktype.json";
        File jsonFile = new File(filePath);

        // Jackson ObjectMapper zur Deserialisierung
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialisiere JSON-Datei in SampleQuicktype-Objekt
        SampleQuicktype sample = objectMapper.readValue(jsonFile, SampleQuicktype.class);

        // Assertions: Überprüfe die Felder des Objekts
        assertNotNull(sample, "Das SampleQuicktype-Objekt sollte nicht null sein.");
        assertEquals("Welcome to quicktype!", sample.getGreeting(), "Die Begrüßung ist falsch.");

        String[] expectedInstructions = {
                "Type or paste JSON here",
                "Or choose a sample above",
                "Quicktype will generate code in your",
                "chosen language to parse the sample data"
        };

        assertArrayEquals(expectedInstructions, sample.getInstructions(), "Die Anweisungen stimmen nicht überein.");
    }
}

