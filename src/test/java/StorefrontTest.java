import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StorefrontTest {

    /**
     * Automated tests for mutator methods:
     * - Storefront.getStorefrontName
     * - Storefront.getStorefrontOwner
     * - Storefront.setStorefrontName
     * - Storefront.setStorefrontOwner
     */
    @Test
    void mutatorsStorefrontTests(){

        Client testUser1 = new Client("accountname1", "password1");
        Client testUser2 = new Client("accountname2", "password2");

        Storefront testStorefront1 = new SellStorefront("Name 1", testUser1);
        assertEquals("Name 1", testStorefront1.getStorefrontName());
        assertEquals(testUser1, testStorefront1.getStorefrontOwner());

        Storefront testStorefront2 = new SellStorefront();
        testStorefront2.setStorefrontName("Name 1");
        assertEquals("Name 1", testStorefront2.getStorefrontName());
        assertNull(testStorefront2.getStorefrontOwner());

        testStorefront2.setStorefrontOwner(testUser2);
        assertEquals(testUser2, testStorefront2.getStorefrontOwner());

    }

    /**
     * Automated tests for validator methods:
     * - Storefront.isValidStorefrontName
     */
    @Test
    void isValidStorefrontNameTest(){
        String testName1, testName2, testName3, testName4, testName5,
               testName6, testName7, testName8, testName9, testName10,
               testName11, testName12;

        testName1 = "";
        testName2 = " invalid name ";
        testName3 = "invalid name ";
        testName4 = " invalid name";
        testName5 = "&invalid name";
        testName6 = "invalid name%";
        testName7 = "invalid ^ name";
        testName8 = "v";
        testName9 = "valid name";
        testName10 = "valid-name";
        testName11 = "-invalid name";
        testName12 = "invalid name-";

        assertFalse(Storefront.isValidStorefrontName(testName1));
        assertFalse(Storefront.isValidStorefrontName(testName2));
        assertFalse(Storefront.isValidStorefrontName(testName3));
        assertFalse(Storefront.isValidStorefrontName(testName4));
        assertFalse(Storefront.isValidStorefrontName(testName5));
        assertFalse(Storefront.isValidStorefrontName(testName6));
        assertFalse(Storefront.isValidStorefrontName(testName7));
        assertTrue(Storefront.isValidStorefrontName(testName8));
        assertTrue(Storefront.isValidStorefrontName(testName9));
        assertTrue(Storefront.isValidStorefrontName(testName10));
        assertFalse(Storefront.isValidStorefrontName(testName11));
        assertFalse(Storefront.isValidStorefrontName(testName12));

    }

}
