import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopperTest {

    @Test
    void constructorTest(){
        Shopper shopper1 = new Shopper();
        assertEquals("accountname", shopper1.getAccountName());
        assertEquals("password", shopper1.getPassword());
        assertEquals(0.0, shopper1.getRating());

        Shopper shopper2 = new Shopper("desmond", "desmond");
        assertEquals("desmond", shopper2.getAccountName());
        assertEquals("desmond", shopper2.getPassword());
        assertEquals(0.0, shopper2.getRating());

        User shopper3 = new Shopper();
        assertEquals("accountname", shopper3.getAccountName());
        assertEquals("password", shopper3.getPassword());
        assertEquals(0.0, shopper3.getRating());

        User shopper4 = new Shopper("desmond", "desmond");
        assertEquals("desmond", shopper4.getAccountName());
        assertEquals("desmond", shopper4.getPassword());
        assertEquals(0.0, shopper4.getRating());

        assertThrows(IllegalArgumentException.class, ()-> new Shopper("des mond", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new Shopper("", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new Shopper("desmond", ""));
        assertThrows(IllegalArgumentException.class, ()-> new Shopper("desmond", "des mond"));

    }

    @Test
    void buyTest(){

    }

}
