import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import util.JsonUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TagTest {

    @Test
    void constructorsTest(){
        assertThrows(IllegalArgumentException.class, ()-> new Tag(""));
        assertThrows(IllegalArgumentException.class, ()-> new Tag(" "));
        assertThrows(IllegalArgumentException.class, ()-> new Tag(" t"));
        assertThrows(IllegalArgumentException.class, ()-> new Tag("t "));
        assertThrows(IllegalArgumentException.class, ()-> new Tag("t t"));
        assertThrows(IllegalArgumentException.class, ()-> new Tag("%"));

        Tag testTag1 = new Tag("tag1");
        assertEquals("tag1", testTag1.getName());
        assertEquals(0, testTag1.getProducts().size());
    }

    @Test
    void toJsonStringTest() throws JsonProcessingException {
        Tag testTag1 = new Tag("tag1");
        String testString1 = "{" + System.lineSeparator() +
                "  \"name\" : \"tag1\"," + System.lineSeparator() +
                "  \"products\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testString1, JsonUtil.toJsonString(testTag1));

        Tag testTag2 = new Tag("tag2");
        Product testProduct1 = new Product("test1", "description1", 50, new User("test1", "pass1"));
        testTag2.addProduct(testProduct1);
        String testString2 = "{" + System.lineSeparator() +
                "  \"name\" : \"tag2\"," + System.lineSeparator() +
                "  \"products\" : [ {" + System.lineSeparator() +
                "    \"name\" : \"test1\"," + System.lineSeparator() +
                "    \"description\" : \"description1\"," + System.lineSeparator() +
                "    \"price\" : 50.0," + System.lineSeparator() +
                "    \"tags\" : [ ]," + System.lineSeparator() +
                "    \"merchant\" : {" + System.lineSeparator() +
                "      \"accountName\" : \"test1\"," + System.lineSeparator() +
                "      \"password\" : \"pass1\"," + System.lineSeparator() +
                "      \"transactionHistory\" : [ ]," + System.lineSeparator() +
                "      \"messages\" : [ ]," + System.lineSeparator() +
                "      \"rating\" : 0.0" + System.lineSeparator() +
                "    }," + System.lineSeparator() +
                "    \"consumers\" : [ ]" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";
        assertEquals(testString2, JsonUtil.toJsonString(testTag2));
    }

    @Test
    void jsonFileTest() throws IOException {
        Tag testExportTag1 = new Tag("tag1");
        Tag testExportTag2 = new Tag("tag2");
        Product testProduct1 = new Product("test1", "description1", 50, new User("test1", "pass1"));
        testExportTag2.addProduct(testProduct1);

        JsonUtil.toJsonFile("src/test/resources/tagJsonFileTest_1.json", testExportTag1);
        JsonUtil.toJsonFile("src/test/resources/tagJsonFileTest_2.json", testExportTag2);

        Tag testImportTag1 = JsonUtil.fromJsonFile("src/test/resources/tagJsonFileTest_1.json", Tag.class);
        Tag testImportTag2 = JsonUtil.fromJsonFile("src/test/resources/tagJsonFileTest_2.json", Tag.class);

        assertEquals("tag1", testImportTag1.getName());
        assertEquals(0, testImportTag1.getProducts().size());

        assertEquals("tag2", testImportTag2.getName());
        assertEquals(1, testImportTag2.getProducts().size());
    }

    @Test
    void listFromJsonFileTest() throws IOException {
        Tag testExportTag1 = new Tag("tag1");
        Tag testExportTag2 = new Tag("tag2");
        Product testProduct1 = new Product("test1", "description1", 50, new User("test1", "pass1"));
        testExportTag2.addProduct(testProduct1);

        JsonUtil.toJsonFile("src/test/resources/tagJsonFileTest_3.json", Arrays.asList(testExportTag1, testExportTag2));

        List<Tag> testImportTagList1 = JsonUtil.listFromJsonFile("src/test/resources/tagJsonFileTest_3.json", Tag.class);
        assertEquals(2, testImportTagList1.size());
    }

}
