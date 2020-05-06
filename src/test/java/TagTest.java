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

}
