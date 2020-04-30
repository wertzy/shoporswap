import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SellProductTest {

    /**
     * Automated tests for SellProduct constructor methods:
     * - default constructor
     * - constructor with 4 parameters
     * - constructor with 5 parameters
     */
    @Test
    void constructorsTest(){
        String text0c = ""; // string with 0 characters, used repeatedly during testing
        String text20c = "thisistencthisistenc"; // string with 20 characters, used repeatedly during testing
        String text60c = "thisistencthisistencthisistencthisistencthisistencthisistenc"; // string with 51 characters, used repeatedly during testing
        String text500c =
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc"; // string with 500 characters, used repeatedly during testing
        String text600c =
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc"; // string with 600 characters, used repeatedly during testing
        double invalidPrice = -100; // invalid price, used repeatedly during testing
        double validPrice = 20.2; // valid price, used repeatedly during testing

        assertThrows(IllegalArgumentException.class, ()-> new SellProduct(text0c, text0c, validPrice, null)); // Equivalence class: name and description must be valid (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> new SellProduct(text20c, text600c, validPrice, null)); // Equivalence class: name and description must be valid (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> new SellProduct(text60c, text500c, validPrice, null)); // Equivalence class: name and description must be valid (invalid case, border case)

        assertThrows(IllegalArgumentException.class, ()-> new SellProduct(text0c, text0c, invalidPrice, null)); // Equivalence class: name and description must be valid (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> new SellProduct(text20c, text600c, validPrice,null)); // Equivalence class: name and description must be valid (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> new SellProduct(text60c, text500c, validPrice,null)); // Equivalence class: name and description must be valid (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> new SellProduct(text20c, text500c, invalidPrice,null)); // Equivalence class: name and description must be valid (invalid case, border case)

        SellProduct testSellProduct1, testSellProduct2, testSellProduct3, testSellProduct4, testSellProduct5;

        testSellProduct1 = new SellProduct("1", text500c, 0, null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testSellProduct1.getProductName());
        assertEquals(text500c, testSellProduct1.getProductDescription());
        assertEquals(0, testSellProduct1.getProductValue());
        assertNull(testSellProduct1.getProductMerchant());
        assertEquals(0, testSellProduct1.getProductTags().size());

        testSellProduct2 = new SellProduct(text20c, text500c, 0.01,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testSellProduct2.getProductName());
        assertEquals(text500c, testSellProduct2.getProductDescription());
        assertEquals(0.01, testSellProduct2.getProductValue());
        assertNull(testSellProduct2.getProductMerchant());
        assertEquals(0, testSellProduct2.getProductTags().size());

        testSellProduct3 = new SellProduct(text20c, "1", validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testSellProduct3.getProductName());
        assertEquals("1", testSellProduct3.getProductDescription());
        assertEquals(validPrice, testSellProduct3.getProductValue());
        assertNull(testSellProduct3.getProductMerchant());
        assertEquals(0, testSellProduct3.getProductTags().size());

        testSellProduct4 = new SellProduct("1", text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testSellProduct4.getProductName());
        assertEquals(text500c, testSellProduct4.getProductDescription());
        assertEquals(validPrice, testSellProduct4.getProductValue());
        assertNull(testSellProduct4.getProductMerchant());
        assertEquals(0, testSellProduct4.getProductTags().size());

        testSellProduct5 = new SellProduct(text20c, text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, middle case)
        assertEquals(text20c, testSellProduct5.getProductName());
        assertEquals(text500c, testSellProduct5.getProductDescription());
        assertEquals(validPrice, testSellProduct5.getProductValue());
        assertNull(testSellProduct5.getProductMerchant());
        assertEquals(0, testSellProduct5.getProductTags().size());

        AbstractProduct testAbstractSellProduct1, testAbstractSellProduct2, testAbstractSellProduct3, testAbstractSellProduct4, testAbstractSellProduct5;

        testAbstractSellProduct1 = new SellProduct("1", text500c, 0, null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testAbstractSellProduct1.getProductName());
        assertEquals(text500c, testAbstractSellProduct1.getProductDescription());
        assertEquals(0, testAbstractSellProduct1.getProductValue());
        assertNull(testAbstractSellProduct1.getProductMerchant());
        assertEquals(0, testAbstractSellProduct1.getProductTags().size());

        testAbstractSellProduct2 = new SellProduct(text20c, text500c, 0.01,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testAbstractSellProduct2.getProductName());
        assertEquals(text500c, testAbstractSellProduct2.getProductDescription());
        assertEquals(0.01, testAbstractSellProduct2.getProductValue());
        assertNull(testAbstractSellProduct2.getProductMerchant());
        assertEquals(0, testAbstractSellProduct2.getProductTags().size());

        testAbstractSellProduct3 = new SellProduct(text20c, "1", validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testAbstractSellProduct3.getProductName());
        assertEquals("1", testAbstractSellProduct3.getProductDescription());
        assertEquals(validPrice, testAbstractSellProduct3.getProductValue());
        assertNull(testAbstractSellProduct3.getProductMerchant());
        assertEquals(0, testAbstractSellProduct3.getProductTags().size());

        testAbstractSellProduct4 = new SellProduct("1", text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testAbstractSellProduct4.getProductName());
        assertEquals(text500c, testAbstractSellProduct4.getProductDescription());
        assertEquals(validPrice, testAbstractSellProduct4.getProductValue());
        assertNull(testAbstractSellProduct4.getProductMerchant());
        assertEquals(0, testAbstractSellProduct4.getProductTags().size());

        testAbstractSellProduct5 = new SellProduct(text20c, text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, middle case)
        assertEquals(text20c, testAbstractSellProduct5.getProductName());
        assertEquals(text500c, testAbstractSellProduct5.getProductDescription());
        assertEquals(validPrice, testAbstractSellProduct5.getProductValue());
        assertNull(testAbstractSellProduct5.getProductMerchant());
        assertEquals(0, testAbstractSellProduct5.getProductTags().size());
    }
}
