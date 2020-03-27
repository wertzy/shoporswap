import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapperTest {

    @Test
    void constructorTest(){
        Swapper swapper1 = new Swapper();
        assertEquals("accountname", swapper1.getAccountName());
        assertEquals("password", swapper1.getPassword());
        assertEquals(0.0, swapper1.getRating());

        Swapper swapper2 = new Swapper("desmond", "desmond");
        assertEquals("desmond", swapper2.getAccountName());
        assertEquals("desmond", swapper2.getPassword());
        assertEquals(0.0, swapper2.getRating());

        User swapper3 = new Swapper();
        assertEquals("accountname", swapper3.getAccountName());
        assertEquals("password", swapper3.getPassword());
        assertEquals(0.0, swapper3.getRating());

        User swapper4 = new Swapper("desmond", "desmond");
        assertEquals("desmond", swapper4.getAccountName());
        assertEquals("desmond", swapper4.getPassword());
        assertEquals(0.0, swapper4.getRating());

        assertThrows(IllegalArgumentException.class, ()-> new Swapper("des mond", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new Swapper("", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new Swapper("desmond", ""));
        assertThrows(IllegalArgumentException.class, ()-> new Swapper("desmond", "des mond"));
    }

}
