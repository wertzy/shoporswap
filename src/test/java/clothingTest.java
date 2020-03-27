import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class clothingTest {
    @Test
    void constTest(){
        String[] tags = {"1", "2", "3"};
        Clothing clothing = new Clothing("a", "b", 0.01, tags);
        assertEquals(clothing.getName(), "a");
        assertEquals(clothing.getDescription(), "b");
        assertEquals(clothing.getPrice(), 0.01);
        assertEquals(clothing.getTags(), tags);
    }
}
