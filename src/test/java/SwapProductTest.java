import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwapProductTest {

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

        assertThrows(IllegalArgumentException.class, ()-> new SwapProduct(text0c, text0c, validPrice, null)); // Equivalence class: name and description must be valid (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> new SwapProduct(text20c, text600c, validPrice, null)); // Equivalence class: name and description must be valid (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> new SwapProduct(text60c, text500c, validPrice, null)); // Equivalence class: name and description must be valid (invalid case, border case)

        assertThrows(IllegalArgumentException.class, ()-> new SwapProduct(text0c, text0c, invalidPrice, null)); // Equivalence class: name and description must be valid (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> new SwapProduct(text20c, text600c, validPrice,null)); // Equivalence class: name and description must be valid (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> new SwapProduct(text60c, text500c, validPrice,null)); // Equivalence class: name and description must be valid (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> new SwapProduct(text20c, text500c, invalidPrice,null)); // Equivalence class: name and description must be valid (invalid case, border case)

        SwapProduct testSwapProduct1, testSwapProduct2, testSwapProduct3, testSwapProduct4, testSwapProduct5;

        testSwapProduct1 = new SwapProduct("1", text500c, 0, null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testSwapProduct1.getProductName());
        assertEquals(text500c, testSwapProduct1.getProductDescription());
        assertEquals(0, testSwapProduct1.getProductValue());
        assertNull(testSwapProduct1.getProductMerchant());
        assertEquals(0, testSwapProduct1.getProductTags().size());

        testSwapProduct2 = new SwapProduct(text20c, text500c, 0.01,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testSwapProduct2.getProductName());
        assertEquals(text500c, testSwapProduct2.getProductDescription());
        assertEquals(0.01, testSwapProduct2.getProductValue());
        assertNull(testSwapProduct2.getProductMerchant());
        assertEquals(0, testSwapProduct2.getProductTags().size());

        testSwapProduct3 = new SwapProduct(text20c, "1", validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testSwapProduct3.getProductName());
        assertEquals("1", testSwapProduct3.getProductDescription());
        assertEquals(validPrice, testSwapProduct3.getProductValue());
        assertNull(testSwapProduct3.getProductMerchant());
        assertEquals(0, testSwapProduct3.getProductTags().size());

        testSwapProduct4 = new SwapProduct("1", text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testSwapProduct4.getProductName());
        assertEquals(text500c, testSwapProduct4.getProductDescription());
        assertEquals(validPrice, testSwapProduct4.getProductValue());
        assertNull(testSwapProduct4.getProductMerchant());
        assertEquals(0, testSwapProduct4.getProductTags().size());

        testSwapProduct5 = new SwapProduct(text20c, text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, middle case)
        assertEquals(text20c, testSwapProduct5.getProductName());
        assertEquals(text500c, testSwapProduct5.getProductDescription());
        assertEquals(validPrice, testSwapProduct5.getProductValue());
        assertNull(testSwapProduct5.getProductMerchant());
        assertEquals(0, testSwapProduct5.getProductTags().size());

        AbstractProduct testAbstractSwapProduct1, testAbstractSwapProduct2, testAbstractSwapProduct3, testAbstractSwapProduct4, testAbstractSwapProduct5;

        testAbstractSwapProduct1 = new SwapProduct("1", text500c, 0, null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testAbstractSwapProduct1.getProductName());
        assertEquals(text500c, testAbstractSwapProduct1.getProductDescription());
        assertEquals(0, testAbstractSwapProduct1.getProductValue());
        assertNull(testAbstractSwapProduct1.getProductMerchant());
        assertEquals(0, testAbstractSwapProduct1.getProductTags().size());

        testAbstractSwapProduct2 = new SwapProduct(text20c, text500c, 0.01,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testAbstractSwapProduct2.getProductName());
        assertEquals(text500c, testAbstractSwapProduct2.getProductDescription());
        assertEquals(0.01, testAbstractSwapProduct2.getProductValue());
        assertNull(testAbstractSwapProduct2.getProductMerchant());
        assertEquals(0, testAbstractSwapProduct2.getProductTags().size());

        testAbstractSwapProduct3 = new SwapProduct(text20c, "1", validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testAbstractSwapProduct3.getProductName());
        assertEquals("1", testAbstractSwapProduct3.getProductDescription());
        assertEquals(validPrice, testAbstractSwapProduct3.getProductValue());
        assertNull(testAbstractSwapProduct3.getProductMerchant());
        assertEquals(0, testAbstractSwapProduct3.getProductTags().size());

        testAbstractSwapProduct4 = new SwapProduct("1", text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testAbstractSwapProduct4.getProductName());
        assertEquals(text500c, testAbstractSwapProduct4.getProductDescription());
        assertEquals(validPrice, testAbstractSwapProduct4.getProductValue());
        assertNull(testAbstractSwapProduct4.getProductMerchant());
        assertEquals(0, testAbstractSwapProduct4.getProductTags().size());

        testAbstractSwapProduct5 = new SwapProduct(text20c, text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, middle case)
        assertEquals(text20c, testAbstractSwapProduct5.getProductName());
        assertEquals(text500c, testAbstractSwapProduct5.getProductDescription());
        assertEquals(validPrice, testAbstractSwapProduct5.getProductValue());
        assertNull(testAbstractSwapProduct5.getProductMerchant());
        assertEquals(0, testAbstractSwapProduct5.getProductTags().size());
    }

}
