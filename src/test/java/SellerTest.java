import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    @Test
    void constructorTest(){
        Seller seller1 = new Seller();
        assertEquals("", seller1.getAccountName());
        assertEquals("", seller1.getPassword());
        assertEquals(0.0, seller1.getRating());

        Seller seller2 = new Seller("desmond", "desmond");
        assertEquals("desmond", seller2.getAccountName());
        assertEquals("desmond", seller2.getPassword());
        assertEquals(0.0, seller2.getRating());

        User seller3 = new Seller();
        assertEquals("", seller3.getAccountName());
        assertEquals("", seller3.getPassword());
        assertEquals(0.0, seller3.getRating());

        User seller4 = new Seller("desmond",  "desmond");
        assertEquals("desmond", seller4.getAccountName());
        assertEquals("desmond", seller4.getPassword());
        assertEquals(0.0, seller4.getRating());

        assertThrows(IllegalArgumentException.class, ()-> new Seller("des mond", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new Seller("", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new Seller("desmond", ""));
        assertThrows(IllegalArgumentException.class, ()-> new Seller("desmond", "des mond"));

    }

}
