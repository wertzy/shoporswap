import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void constructorsTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
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

        assertThrows(IllegalArgumentException.class, ()-> new Product(text0c, text0c, null)); // Equivalence class: name and description must be valid (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> new Product(text20c, text600c, null)); // Equivalence class: name and description must be valid (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> new Product(text60c, text500c, null)); // Equivalence class: name and description must be valid (invalid case, border case)

        assertThrows(IllegalArgumentException.class, ()-> new Product(text0c, text0c, invalidPrice, null)); // Equivalence class: name and description must be valid (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> new Product(text20c, text600c, validPrice,null)); // Equivalence class: name and description must be valid (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> new Product(text60c, text500c, validPrice,null)); // Equivalence class: name and description must be valid (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> new Product(text20c, text500c, invalidPrice,null)); // Equivalence class: name and description must be valid (invalid case, border case)

        Product testProduct1 = new Product("1", text500c, null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testProduct1.getName());
        assertEquals(text500c, testProduct1.getDescription());
        assertEquals(0, testProduct1.getPrice());
        assertNull(testProduct1.getMerchant());
        assertNull(testProduct1.getConsumer());
        assertEquals(0, testProduct1.getTags().size());

        Product testProduct2 = new Product(text20c, text500c, 0.01,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testProduct2.getName());
        assertEquals(text500c, testProduct2.getDescription());
        assertEquals(0.01, testProduct2.getPrice());
        assertNull(testProduct2.getMerchant());
        assertNull(testProduct2.getConsumer());
        assertEquals(0, testProduct2.getTags().size());

        Product testProduct3 = new Product(text20c, "1", validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals(text20c, testProduct3.getName());
        assertEquals("1", testProduct3.getDescription());
        assertEquals(validPrice, testProduct3.getPrice());
        assertNull(testProduct3.getMerchant());
        assertNull(testProduct3.getConsumer());
        assertEquals(0, testProduct3.getTags().size());

        Product testProduct4 = new Product("1", text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, border case)
        assertEquals("1", testProduct4.getName());
        assertEquals(text500c, testProduct4.getDescription());
        assertEquals(validPrice, testProduct4.getPrice());
        assertNull(testProduct4.getMerchant());
        assertNull(testProduct4.getConsumer());
        assertEquals(0, testProduct4.getTags().size());

        Product testProduct5 = new Product(text20c, text500c, validPrice,null); // Equivalence class: name and description must be valid (valid case, middle case)
        assertEquals(text20c, testProduct5.getName());
        assertEquals(text500c, testProduct5.getDescription());
        assertEquals(validPrice, testProduct5.getPrice());
        assertNull(testProduct5.getMerchant());
        assertNull(testProduct5.getConsumer());
        assertEquals(0, testProduct5.getTags().size());


        User testUser1 = new User(); // Equivalence class: name and description must be valid, merchant must be valid (valid case, middle case)
        Product testProduct6 = new Product(text20c, text500c, validPrice, testUser1);
        assertEquals(text20c, testProduct6.getName());
        assertEquals(text500c, testProduct6.getDescription());
        assertEquals(validPrice, testProduct6.getPrice());
        assertEquals("accountname", testProduct6.getMerchant().getAccountName());
        assertEquals("password", testProduct6.getMerchant().getPassword());
        assertEquals(0, testProduct6.getMerchant().getRating());
        assertEquals(0, testProduct6.getMerchant().getTransactionHistory().size());
        assertNull(testProduct6.getConsumer());
        assertEquals(0, testProduct6.getTags().size());

        User testUser2 = new User("accountname1", "password1"); // Equivalence class: name and description must be valid, merchant must be valid (valid case, middle case)
        Product testProduct7 = new Product(text20c, text500c, validPrice, testUser2);
        assertEquals(text20c, testProduct7.getName());
        assertEquals(text500c, testProduct7.getDescription());
        assertEquals(validPrice, testProduct7.getPrice());
        assertEquals("accountname1", testProduct7.getMerchant().getAccountName());
        assertEquals("password1", testProduct7.getMerchant().getPassword());
        assertEquals(0, testProduct7.getMerchant().getRating());
        assertEquals(0, testProduct7.getMerchant().getTransactionHistory().size());
        assertNull(testProduct7.getConsumer());
        assertEquals(0, testProduct7.getTags().size());
    }

    @Test
    void isValidNameTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
        assertFalse(Product.isValidName("")); // Equivalence class: number of characters must be nonzero and at most 50 (invalid case, border case)
        assertFalse(Product.isValidName("123456789 123456789 123456789 123456789 123456789 1")); // Equivalence class: number of characters must be nonzero and at most 50 (invalid case, border case)
        assertFalse(Product.isValidName("123456789 123456789 123456789 123456789 123456789 123456789 ")); // Equivalence class: number of characters must be nonzero and at most 50 (invalid case, middle case)
        assertTrue(Product.isValidName("a")); // Equivalence class: number of characters must be nonzero and at most 50 (valid case, border case)
        assertTrue(Product.isValidName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx")); // Equivalence class: number of characters must be nonzero and at most 50 (valid case, border case)
        assertTrue(Product.isValidName("standard")); // Equivalence class: number of characters must be nonzero and at most 50 (valid case, middle case)

        assertFalse(Product.isValidName(" 123456789")); // Equivalence class: name cannot begin or end with a space (invalid case, border case)
        assertFalse(Product.isValidName("123456789 ")); // Equivalence class: name cannot begin or end with a space (invalid case, border case)
        assertFalse(Product.isValidName(" 123456789 ")); // Equivalence class: name cannot begin or end with a space (invalid case, middle case)
        assertTrue(Product.isValidName("a name")); // Equivalence class: name cannot begin or end with a space (valid case, border case)
        assertTrue(Product.isValidName("product c")); // Equivalence class: name cannot begin or end with a space (valid case, border case)
        assertTrue(Product.isValidName("name standard")); // Equivalence class: name cannot begin or end with a space (valid case, middle case)

        assertFalse(Product.isValidName("a@bcd")); // Equivalence class: name cannot contain any special characters (invalid case, border case)
        assertFalse(Product.isValidName("@#$%&!*?/\\")); // Equivalence class: name cannot contain any special characters (invalid case, border case)
        assertFalse(Product.isValidName("a@b#c$d%")); // Equivalence class: name cannot contain any special characters (invalid case, middle case)
        assertTrue(Product.isValidName("possibility")); // Equivalence class: name cannot contain any special characters (valid case, middle case)
    }

    @Test
    void isValidDescriptionTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
        assertFalse(Product.isValidDescription("")); // Equivalence class: number of characters must be nonzero and at most 500 (invalid case, border case)
        assertFalse(Product.isValidDescription(
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "t")); // Equivalence class: number of characters must be nonzero and at most 500 (invalid case, border case)
        assertFalse(Product.isValidDescription(
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc")); // Equivalence class: number of characters must be nonzero and at most 500 (invalid case, middle case)
        assertTrue(Product.isValidDescription("t")); // Equivalence class: number of characters must me nonzero and at most 500 (valid case, border case)
        assertTrue(Product.isValidDescription(
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc")); // Equivalence class: number of characters must me nonzero and at most 500 (valid case, border case)
        assertTrue(Product.isValidDescription("thisistencthisistencthisistencthisistencthisistenc")); // Equivalence class: number of characters must me nonzero and at most 500 (valid case, middle case)

        assertFalse(Product.isValidDescription(" description")); // Equivalence class: description cannot begin or end with a space (invalid case, border case)
        assertFalse(Product.isValidDescription("description ")); // Equivalence class: description cannot begin or end with a space (invalid case, border case)
        assertFalse(Product.isValidDescription(" description ")); // Equivalence class: description cannot begin or end with a space (invalid case, middle case)
        assertTrue(Product.isValidDescription("a description")); // Equivalence class: description cannot begin or end with a space (valid case, border case)
        assertTrue(Product.isValidDescription("description d")); // Equivalence class: description cannot begin or end with a space (valid case, border case)
        assertTrue(Product.isValidDescription("product description")); // Equivalence class: description cannot begin or end with a space (valid case, middle case)
    }

    @Test
    void isValidPriceTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
        assertFalse(Product.isValidPrice(-100)); // Equivalence class: price must be nonnegative (invalid case, middle case)
        assertFalse(Product.isValidPrice(-0.01)); // Equivalence class: price must be nonnegative (invalid case, border case)
        assertTrue(Product.isValidPrice(0)); // Equivalence class: price must be nonnegative (valid case, border case)
        assertTrue(Product.isValidPrice(100)); // Equivalence class: price must be nonnegative (valid case, middle case)

        assertFalse(Product.isValidPrice(0.012)); // Equivalence class: price must have no more than 2 decimal places (invalid case, border case)
        assertFalse(Product.isValidPrice(0.0123456789)); // Equivalence class: price must have no more than 2 decimal places (invalid case, middle case)
        assertTrue(Product.isValidPrice(10)); // Equivalence class: price must have no more than 2 decimal places (valid case, border case)
        assertTrue(Product.isValidPrice(10.01)); // Equivalence class: price must have no more than 2 decimal places (valid case, border case)
        assertTrue(Product.isValidPrice(10.1)); // Equivalence class: price must have no more than 2 decimal places (valid case, middle case)
    }

    @Test
    void getNameTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
    }

    @Test
    void getDescriptionTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
    }

    @Test
    void getPriceTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
    }

    @Test
    void getTagsTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
    }

    @Test
    void getMerchantTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
    }

    @Test
    void getConsumerTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
    }

    @Test
    void addTagTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
    }

    @Test
    void removeTagTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
    }

}
