import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void constructorsTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
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
    }

    @Test
    void isValidPriceTest(){
        //TODO write automated tests before corresponding implementation (include comments regarding equivalence class, case type)
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
