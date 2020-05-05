import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorefrontFactoryTest {

    /**
     * Automated tests for StorefrontFactory.getStorefrontFactory method
     */
    @Test
    void getStorefrontTest(){

        StorefrontFactory testStorefrontFactory = new StorefrontFactory();
        Storefront testSellStorefront1, testSellStorefront2, testSellStorefront3;
        Storefront testSwapStorefront1, testSwapStorefront2, testSwapStorefront3;

        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("SELLL"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("selll"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("sElLl"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("SEL"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("sel"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("sEl"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("ELLL"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("elll"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("ElLl"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("ELL"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("ell"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("ElL"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront(""));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("Swapp"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("SWAPP"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("SwApP"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("wapp"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("WAPP"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("wApP"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("Swa"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("SWP"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("SAp"));
        assertThrows(IllegalArgumentException.class, ()-> testStorefrontFactory.getStorefront("invalid type"));

        testSellStorefront1 = testStorefrontFactory.getStorefront("sell");
        assertEquals("DEFAULT NAME", testSellStorefront1.getStorefrontName());
        assertNull(testSellStorefront1.getStorefrontOwner());
        assertEquals(0, testSellStorefront1.getStorefrontProducts().size());
        testSellStorefront2 = testStorefrontFactory.getStorefront("SELL");
        assertEquals("DEFAULT NAME", testSellStorefront2.getStorefrontName());
        assertNull(testSellStorefront2.getStorefrontOwner());
        assertEquals(0, testSellStorefront2.getStorefrontProducts().size());
        testSellStorefront3 = testStorefrontFactory.getStorefront("SeLl");
        assertEquals("DEFAULT NAME", testSellStorefront3.getStorefrontName());
        assertNull(testSellStorefront3.getStorefrontOwner());
        assertEquals(0, testSellStorefront3.getStorefrontProducts().size());

        testSwapStorefront1 = testStorefrontFactory.getStorefront("Swap");
        assertEquals("DEFAULT NAME", testSwapStorefront1.getStorefrontName());
        assertNull(testSwapStorefront1.getStorefrontOwner());
        assertEquals(0, testSwapStorefront1.getStorefrontProducts().size());
        testSwapStorefront2 = testStorefrontFactory.getStorefront("SWAP");
        assertEquals("DEFAULT NAME", testSwapStorefront2.getStorefrontName());
        assertNull(testSwapStorefront2.getStorefrontOwner());
        assertEquals(0, testSwapStorefront2.getStorefrontProducts().size());
        testSwapStorefront3 = testStorefrontFactory.getStorefront("sWaP");
        assertEquals("DEFAULT NAME", testSwapStorefront3.getStorefrontName());
        assertNull(testSwapStorefront3.getStorefrontOwner());
        assertEquals(0, testSwapStorefront3.getStorefrontProducts().size());

    }

}
