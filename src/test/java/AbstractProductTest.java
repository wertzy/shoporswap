import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractProductTest {

    /**
     * Automated tests for AbstractProduct.isValidProductName method
     */
    @Test
    void isValidProductNameTest(){
        assertFalse(AbstractProduct.isValidProductName("")); // Equivalence class: number of characters must be nonzero and at most 50 (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductName("123456789 123456789 123456789 123456789 123456789 1")); // Equivalence class: number of characters must be nonzero and at most 50 (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductName("123456789 123456789 123456789 123456789 123456789 123456789 ")); // Equivalence class: number of characters must be nonzero and at most 50 (invalid case, middle case)
        assertTrue(AbstractProduct.isValidProductName("a")); // Equivalence class: number of characters must be nonzero and at most 50 (valid case, border case)
        assertTrue(AbstractProduct.isValidProductName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx")); // Equivalence class: number of characters must be nonzero and at most 50 (valid case, border case)
        assertTrue(AbstractProduct.isValidProductName("standard")); // Equivalence class: number of characters must be nonzero and at most 50 (valid case, middle case)

        assertFalse(AbstractProduct.isValidProductName(" 123456789")); // Equivalence class: name cannot begin or end with a space (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductName("123456789 ")); // Equivalence class: name cannot begin or end with a space (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductName(" 123456789 ")); // Equivalence class: name cannot begin or end with a space (invalid case, middle case)
        assertTrue(AbstractProduct.isValidProductName("a name")); // Equivalence class: name cannot begin or end with a space (valid case, border case)
        assertTrue(AbstractProduct.isValidProductName("product c")); // Equivalence class: name cannot begin or end with a space (valid case, border case)
        assertTrue(AbstractProduct.isValidProductName("name standard")); // Equivalence class: name cannot begin or end with a space (valid case, middle case)

        assertFalse(AbstractProduct.isValidProductName("a@bcd")); // Equivalence class: name cannot contain any special characters (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductName("@#$%&!*?/\\")); // Equivalence class: name cannot contain any special characters (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductName("a@b#c$d%")); // Equivalence class: name cannot contain any special characters (invalid case, middle case)
        assertTrue(AbstractProduct.isValidProductName("possibility")); // Equivalence class: name cannot contain any special characters (valid case, middle case)
    }

    /**
     * Automated tests for AbstractProduct.isValidProductDescription method
     */
    @Test
    void isValidProductDescriptionTest(){
        assertFalse(AbstractProduct.isValidProductDescription("")); // Equivalence class: number of characters must be nonzero and at most 500 (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductDescription(
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "t")); // Equivalence class: number of characters must be nonzero and at most 500 (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductDescription(
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc")); // Equivalence class: number of characters must be nonzero and at most 500 (invalid case, middle case)
        assertTrue(AbstractProduct.isValidProductDescription("t")); // Equivalence class: number of characters must me nonzero and at most 500 (valid case, border case)
        assertTrue(AbstractProduct.isValidProductDescription(
                "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistencthisistenc")); // Equivalence class: number of characters must me nonzero and at most 500 (valid case, border case)
        assertTrue(AbstractProduct.isValidProductDescription("thisistencthisistencthisistencthisistencthisistenc")); // Equivalence class: number of characters must me nonzero and at most 500 (valid case, middle case)

        assertFalse(AbstractProduct.isValidProductDescription(" description")); // Equivalence class: description cannot begin or end with a space (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductDescription("description ")); // Equivalence class: description cannot begin or end with a space (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductDescription(" description ")); // Equivalence class: description cannot begin or end with a space (invalid case, middle case)
        assertTrue(AbstractProduct.isValidProductDescription("a description")); // Equivalence class: description cannot begin or end with a space (valid case, border case)
        assertTrue(AbstractProduct.isValidProductDescription("description d")); // Equivalence class: description cannot begin or end with a space (valid case, border case)
        assertTrue(AbstractProduct.isValidProductDescription("product description")); // Equivalence class: description cannot begin or end with a space (valid case, middle case)

        assertFalse(AbstractProduct.isValidProductDescription("- product description")); // Equivalence class: description cannot begin or end with a hyphen "-" (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductDescription("product description-")); // Equivalence class: description cannot begin or end with a hyphen "-" (invalid case, border case)
        assertTrue(AbstractProduct.isValidProductDescription("p-1 description")); // Equivalence class: description cannot begin or end with a hyphen "-" (valid case, middle case)
        assertTrue(AbstractProduct.isValidProductDescription("product description-1")); // Equivalence class: description cannot begin or end with a hyphen "-" (valid case, middle case)
        assertTrue(AbstractProduct.isValidProductDescription("product-description")); // Equivalence class: description cannot begin or end with a hyphen "-" (valid case, middle case)
    }

    /**
     * Automated tests for AbstractProduct.isValidProductPrice method
     */
    @Test
    void isValidProductPriceTest(){
        assertFalse(AbstractProduct.isValidProductPrice(-100)); // Equivalence class: price must be nonnegative (invalid case, middle case)
        assertFalse(AbstractProduct.isValidProductPrice(-0.01)); // Equivalence class: price must be nonnegative (invalid case, border case)
        assertTrue(AbstractProduct.isValidProductPrice(0)); // Equivalence class: price must be nonnegative (valid case, border case)
        assertTrue(AbstractProduct.isValidProductPrice(100)); // Equivalence class: price must be nonnegative (valid case, middle case)

        assertFalse(AbstractProduct.isValidProductPrice(0.012)); // Equivalence class: price must have no more than 2 decimal places (invalid case, border case)
        assertFalse(AbstractProduct.isValidProductPrice(0.0123456789)); // Equivalence class: price must have no more than 2 decimal places (invalid case, middle case)
        assertTrue(AbstractProduct.isValidProductPrice(10)); // Equivalence class: price must have no more than 2 decimal places (valid case, border case)
        assertTrue(AbstractProduct.isValidProductPrice(10.01)); // Equivalence class: price must have no more than 2 decimal places (valid case, border case)
        assertTrue(AbstractProduct.isValidProductPrice(10.1)); // Equivalence class: price must have no more than 2 decimal places (valid case, middle case)

    }

    /**
     * Automated tests for AbstractProduct.addTag methods (one with Tag object argument, one with String argument)
     */
    @Test
    void addTagTestTagTests(){
        
        User testUser1 = new User();
        final Tag testTag1, testTag2;

        SellProduct testSellProduct1;

        testSellProduct1 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)


        testTag1 = new Tag("tag1");
        testTag2 = new Tag("tag2");
        
        testSellProduct1.addTag(testTag1); // Equivalence class: having 1 tag (valid case, border case)
        assertTrue(testSellProduct1.getProductTags().contains(testTag1));
        assertFalse(testSellProduct1.getProductTags().contains(testTag2));

        testSellProduct1.addTag(testTag2); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSellProduct1.getProductTags().contains(testTag1));
        assertTrue(testSellProduct1.getProductTags().contains(testTag2));

        AbstractProduct testSellProduct2;

        testSellProduct2 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        testSellProduct2.addTag(testTag1); // Equivalence class: having 1 tag (valid case, border case)
        assertTrue(testSellProduct2.getProductTags().contains(testTag1));
        assertFalse(testSellProduct1.getProductTags().contains(testTag2));

        testSellProduct2.addTag(testTag2); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSellProduct2.getProductTags().contains(testTag1));
        assertTrue(testSellProduct2.getProductTags().contains(testTag2));

        SwapProduct testSwapProduct3;

        testSwapProduct3 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        testSwapProduct3.addTag(testTag1); // Equivalence class: having 1 tag (valid case, border case)
        assertTrue(testSwapProduct3.getProductTags().contains(testTag1));
        assertFalse(testSellProduct1.getProductTags().contains(testTag2));

        testSwapProduct3.addTag(testTag2); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSwapProduct3.getProductTags().contains(testTag1));
        assertTrue(testSwapProduct3.getProductTags().contains(testTag2));

        AbstractProduct testSwapProduct4;

        testSwapProduct4 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        testSwapProduct4.addTag(testTag1); // Equivalence class: having 1 tag (valid case, border case)
        assertTrue(testSwapProduct4.getProductTags().contains(testTag1));
        assertFalse(testSellProduct1.getProductTags().contains(testTag2));

        testSwapProduct4.addTag(testTag2); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSwapProduct4.getProductTags().contains(testTag1));
        assertTrue(testSwapProduct4.getProductTags().contains(testTag2));
        
    }

    /**
     * Automated tests for AbstractProduct.addTag methods (one with Tag object argument, one with String argument)
     */
    @Test
    void addTagTestStringTests(){
        User testUser1 = new User();

        SellProduct testSellProduct1;

        testSellProduct1 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.addTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        testSellProduct1.addTag("tag1"); // Equivalence class: having 1 tag (valid case, border case)
        assertFalse(testSellProduct1.getProductTags().contains("tag2"));
        assertTrue(testSellProduct1.getProductTags().contains("tag1"));

        testSellProduct1.addTag("tag2"); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSellProduct1.getProductTags().contains("tag2"));
        assertTrue(testSellProduct1.getProductTags().contains("tag1"));

        AbstractProduct testSellProduct2;

        testSellProduct2 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.addTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        testSellProduct2.addTag("tag1"); // Equivalence class: having 1 tag (valid case, border case)
        assertFalse(testSellProduct2.getProductTags().contains("tag2"));
        assertTrue(testSellProduct2.getProductTags().contains("tag1"));

        testSellProduct2.addTag("tag2"); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSellProduct2.getProductTags().contains("tag2"));
        assertTrue(testSellProduct2.getProductTags().contains("tag1"));

        SwapProduct testSwapProduct3;

        testSwapProduct3 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.addTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        testSwapProduct3.addTag("tag1"); // Equivalence class: having 1 tag (valid case, border case)
        assertFalse(testSwapProduct3.getProductTags().contains("tag2"));
        assertTrue(testSwapProduct3.getProductTags().contains("tag1"));

        testSwapProduct3.addTag("tag2"); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSwapProduct3.getProductTags().contains("tag2"));
        assertTrue(testSwapProduct3.getProductTags().contains("tag1"));

        AbstractProduct testSwapProduct4;

        testSwapProduct4 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.addTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        testSwapProduct4.addTag("tag1"); // Equivalence class: having 1 tag (valid case, border case)
        assertFalse(testSwapProduct4.getProductTags().contains("tag2"));
        assertTrue(testSwapProduct4.getProductTags().contains("tag1"));

        testSwapProduct4.addTag("tag2"); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSwapProduct4.getProductTags().contains("tag2"));
        assertTrue(testSwapProduct4.getProductTags().contains("tag1"));
    }

    /**
     * Automated tests for AbstractProduct.findTag methods (one with Tag object argument, one with String argument)
     */
    @Test
    void findTagTestTagTests(){
        User testUser1 = new User();
        final Tag testTag1, testTag2;

        testTag1 = new Tag("tag1");
        testTag2 = new Tag("tag2");

        SellProduct testSellProduct1;

        testSellProduct1 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSellProduct1.findTag(new Tag("tag1"))); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        testSellProduct1.addTag(testTag1); // Equivalence class: having 1 tag (valid case, border case)
        assertThrows(NoSuchElementException.class, ()-> testSellProduct1.findTag(testTag2));
        assertTrue(testSellProduct1.getProductTags().contains(testTag1));

        testSellProduct1.addTag(testTag2); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSellProduct1.getProductTags().contains(testTag1));
        assertTrue(testSellProduct1.getProductTags().contains(testTag2));

        AbstractProduct testSellProduct2;

        testSellProduct2 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSellProduct2.findTag(new Tag("tag1"))); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        testSellProduct2.addTag(testTag1); // Equivalence class: having 1 tag (valid case, border case)
        assertThrows(NoSuchElementException.class, ()-> testSellProduct2.findTag(testTag2));
        assertTrue(testSellProduct2.getProductTags().contains(testTag1));

        testSellProduct2.addTag(testTag2); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSellProduct2.getProductTags().contains(testTag1));
        assertTrue(testSellProduct2.getProductTags().contains(testTag2));

        SwapProduct testSwapProduct3;

        testSwapProduct3 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSwapProduct3.findTag(new Tag("tag1"))); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        testSwapProduct3.addTag(testTag1); // Equivalence class: having 1 tag (valid case, border case)
        assertThrows(NoSuchElementException.class, ()-> testSwapProduct3.findTag(testTag2));
        assertTrue(testSwapProduct3.getProductTags().contains(testTag1));

        testSwapProduct3.addTag(testTag2); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSwapProduct3.getProductTags().contains(testTag1));
        assertTrue(testSwapProduct3.getProductTags().contains(testTag2));

        AbstractProduct testSwapProduct4;

        testSwapProduct4 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSwapProduct4.findTag(new Tag("tag1"))); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        testSwapProduct4.addTag(testTag1); // Equivalence class: having 1 tag (valid case, border case)
        assertThrows(NoSuchElementException.class, ()-> testSwapProduct4.findTag(testTag2));
        assertTrue(testSwapProduct4.getProductTags().contains(testTag1));

        testSwapProduct4.addTag(testTag2); // Equivalence class: having 2 tags (valid case, middle case)
        assertTrue(testSwapProduct4.getProductTags().contains(testTag1));
        assertTrue(testSwapProduct4.getProductTags().contains(testTag2));
    }

    /**
     * Automated tests for AbstractProduct.findTag methods (one with Tag object argument, one with String argument)
     */
    @Test
    void findTagTestStringTests(){

        User testUser1 = new User();

        SellProduct testSellProduct1;

        testSellProduct1 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.findTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSellProduct1.findTag("tag1")); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        testSellProduct1.addTag("tag1"); // Equivalence class: having 1 tag (valid case, border case)
        assertThrows(NoSuchElementException.class, ()-> testSellProduct1.findTag("tag2"));
        assertEquals("tag1", testSellProduct1.findTag("tag1").getName());

        testSellProduct1.addTag("tag2"); // Equivalence class: having 2 tags (valid case, middle case)
        assertEquals("tag1", testSellProduct1.findTag("tag1").getName());
        assertEquals("tag2", testSellProduct1.findTag("tag2").getName());

        AbstractProduct testSellProduct2;

        testSellProduct2 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.findTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSellProduct2.findTag("tag1")); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        testSellProduct2.addTag("tag1"); // Equivalence class: having 1 tag (valid case, border case)
        assertThrows(NoSuchElementException.class, ()-> testSellProduct2.findTag("tag2"));
        assertEquals("tag1", testSellProduct2.findTag("tag1").getName());

        testSellProduct2.addTag("tag2"); // Equivalence class: having 2 tags (valid case, middle case)
        assertEquals("tag1", testSellProduct2.findTag("tag1").getName());
        assertEquals("tag2", testSellProduct2.findTag("tag2").getName());

        SwapProduct testSwapProduct3;

        testSwapProduct3 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.findTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSwapProduct3.findTag("tag1")); // Equivalence class: tag must already be attached to SwapProduct (invalid case)

        testSwapProduct3.addTag("tag1"); // Equivalence class: having 1 tag (valid case, border case)
        assertThrows(NoSuchElementException.class, ()-> testSwapProduct3.findTag("tag2"));
        assertEquals("tag1", testSwapProduct3.findTag("tag1").getName());

        testSwapProduct3.addTag("tag2"); // Equivalence class: having 2 tags (valid case, middle case)
        assertEquals("tag1", testSwapProduct3.findTag("tag1").getName());
        assertEquals("tag2", testSwapProduct3.findTag("tag2").getName());

        AbstractProduct testSwapProduct4;

        testSwapProduct4 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.findTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSwapProduct4.findTag("tag1")); // Equivalence class: tag must already be attached to SwapProduct (invalid case)

        testSwapProduct4.addTag("tag1"); // Equivalence class: having 1 tag (valid case, border case)
        assertThrows(NoSuchElementException.class, ()-> testSwapProduct4.findTag("tag2"));
        assertEquals("tag1", testSwapProduct4.findTag("tag1").getName());

        testSwapProduct4.addTag("tag2"); // Equivalence class: having 2 tags (valid case, middle case)
        assertEquals("tag1", testSwapProduct4.findTag("tag1").getName());
        assertEquals("tag2", testSwapProduct4.findTag("tag2").getName());

    }

    /**
     * Automated tests for AbstractProduct.removeTag methods (one with Tag object argument, one with String argument)
     */
    @Test
    void removeTagTestsTagTest(){

        User testUser1 = new User();
        final Tag testTag1, testTag2;

        testTag1 = new Tag("tag1");
        testTag2 = new Tag("tag2");

        SwapProduct testSellProduct1;

        testSellProduct1 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSellProduct1.removeTag(new Tag("tag1"))); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        // Equivalence class: removing 1 tag (valid case, border case)
        testSellProduct1.addTag(testTag1);
        testSellProduct1.removeTag(testTag1);
        assertThrows(NoSuchElementException.class, ()-> testSellProduct1.removeTag(testTag2));
        assertFalse(testSellProduct1.getProductTags().contains(testTag1));
        assertFalse(testSellProduct1.getProductTags().contains(testTag2));

        // Equivalence class: removing 2 tags (valid case, middle case)
        testSellProduct1.addTag(testTag1);
        testSellProduct1.addTag(testTag2);
        testSellProduct1.removeTag(testTag1);
        assertTrue(testSellProduct1.getProductTags().contains(testTag2));
        assertFalse(testSellProduct1.getProductTags().contains(testTag1));
        testSellProduct1.removeTag(testTag2);
        assertFalse(testSellProduct1.getProductTags().contains(testTag1));
        assertFalse(testSellProduct1.getProductTags().contains(testTag2));

        AbstractProduct testSellProduct2;

        testSellProduct2 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSellProduct2.removeTag(new Tag("tag1"))); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        // Equivalence class: removing 1 tag (valid case, border case)
        testSellProduct2.addTag(testTag1);
        testSellProduct2.removeTag(testTag1);
        assertThrows(NoSuchElementException.class, ()-> testSellProduct2.removeTag(testTag2));
        assertFalse(testSellProduct2.getProductTags().contains(testTag1));
        assertFalse(testSellProduct2.getProductTags().contains(testTag2));

        // Equivalence class: removing 2 tags (valid case, middle case)
        testSellProduct2.addTag(testTag1);
        testSellProduct2.addTag(testTag2);
        testSellProduct2.removeTag(testTag1);
        assertTrue(testSellProduct2.getProductTags().contains(testTag2));
        assertFalse(testSellProduct2.getProductTags().contains(testTag1));
        testSellProduct2.removeTag(testTag2);
        assertFalse(testSellProduct2.getProductTags().contains(testTag1));
        assertFalse(testSellProduct2.getProductTags().contains(testTag2));

        SwapProduct testSwapProduct3;

        testSwapProduct3 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSwapProduct3.removeTag(new Tag("tag1"))); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        // Equivalence class: removing 1 tag (valid case, border case)
        testSwapProduct3.addTag(testTag1);
        testSwapProduct3.removeTag(testTag1);
        assertThrows(NoSuchElementException.class, ()-> testSwapProduct3.removeTag(testTag2));
        assertFalse(testSwapProduct3.getProductTags().contains(testTag1));
        assertFalse(testSwapProduct3.getProductTags().contains(testTag2));

        // Equivalence class: removing 2 tags (valid case, middle case)
        testSwapProduct3.addTag(testTag1);
        testSwapProduct3.addTag(testTag2);
        testSwapProduct3.removeTag(testTag1);
        assertTrue(testSwapProduct3.getProductTags().contains(testTag2));
        assertFalse(testSwapProduct3.getProductTags().contains(testTag1));
        testSwapProduct3.removeTag(testTag2);
        assertFalse(testSwapProduct3.getProductTags().contains(testTag1));
        assertFalse(testSwapProduct3.getProductTags().contains(testTag2));

        AbstractProduct testSwapProduct4;

        testSwapProduct4 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag(new Tag(""))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag(new Tag(" "))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag(new Tag("a b"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag(new Tag("#"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag(new Tag("#$%"))); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSwapProduct4.removeTag(new Tag("tag1"))); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        // Equivalence class: removing 1 tag (valid case, border case)
        testSwapProduct4.addTag(testTag1);
        testSwapProduct4.removeTag(testTag1);
        assertThrows(NoSuchElementException.class, ()-> testSwapProduct4.removeTag(testTag2));
        assertFalse(testSwapProduct4.getProductTags().contains(testTag1));
        assertFalse(testSwapProduct4.getProductTags().contains(testTag2));

        // Equivalence class: removing 2 tags (valid case, middle case)
        testSwapProduct4.addTag(testTag1);
        testSwapProduct4.addTag(testTag2);
        testSwapProduct4.removeTag(testTag1);
        assertTrue(testSwapProduct4.getProductTags().contains(testTag2));
        assertFalse(testSwapProduct4.getProductTags().contains(testTag1));
        testSwapProduct4.removeTag(testTag2);
        assertFalse(testSwapProduct4.getProductTags().contains(testTag1));
        assertFalse(testSwapProduct4.getProductTags().contains(testTag2));
    }

    /**
     * Automated tests for AbstractProduct.removeTag methods (one with Tag object argument, one with String argument)
     */
    @Test
    void removeTagTestsStringTest(){

        User testUser1 = new User();

        SellProduct testSellProduct1;

        testSellProduct1 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct1.removeTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSellProduct1.removeTag("tag1")); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        // Equivalence class: removing 1 tag (valid case, border case)
        testSellProduct1.addTag("tag1");
        testSellProduct1.removeTag("tag1");
        assertFalse(testSellProduct1.getProductTags().contains("tag2"));
        assertFalse(testSellProduct1.getProductTags().contains("tag1"));

        // Equivalence class: removing 2 tags (valid case, middle case)
        testSellProduct1.addTag("tag1");
        testSellProduct1.addTag("tag2");
        testSellProduct1.removeTag("tag1");
        assertTrue(testSellProduct1.getProductTags().contains("tag2"));
        assertFalse(testSellProduct1.getProductTags().contains("tag1"));
        testSellProduct1.removeTag("tag2");
        assertFalse(testSellProduct1.getProductTags().contains("tag1"));
        assertFalse(testSellProduct1.getProductTags().contains("tag2"));

        AbstractProduct testSellProduct2;

        testSellProduct2 = new SellProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSellProduct2.removeTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSellProduct2.removeTag("tag1")); // Equivalence class: tag must already be attached to SellProduct (invalid case)

        // Equivalence class: removing 1 tag (valid case, border case)
        testSellProduct2.addTag("tag1");
        testSellProduct2.removeTag("tag1");
        assertFalse(testSellProduct2.getProductTags().contains("tag2"));
        assertFalse(testSellProduct2.getProductTags().contains("tag1"));

        // Equivalence class: removing 2 tags (valid case, middle case)
        testSellProduct2.addTag("tag1");
        testSellProduct2.addTag("tag2");
        testSellProduct2.removeTag("tag1");
        assertTrue(testSellProduct2.getProductTags().contains("tag2"));
        assertFalse(testSellProduct2.getProductTags().contains("tag1"));
        testSellProduct2.removeTag("tag2");
        assertFalse(testSellProduct2.getProductTags().contains("tag1"));
        assertFalse(testSellProduct2.getProductTags().contains("tag2"));

        SwapProduct testSwapProduct3;

        testSwapProduct3 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct3.removeTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSwapProduct3.removeTag("tag1")); // Equivalence class: tag must already be attached to SwapProduct (invalid case)

        // Equivalence class: removing 1 tag (valid case, border case)
        testSwapProduct3.addTag("tag1");
        testSwapProduct3.removeTag("tag1");
        assertFalse(testSwapProduct3.getProductTags().contains("tag2"));
        assertFalse(testSwapProduct3.getProductTags().contains("tag1"));

        // Equivalence class: removing 2 tags (valid case, middle case)
        testSwapProduct3.addTag("tag1");
        testSwapProduct3.addTag("tag2");
        testSwapProduct3.removeTag("tag1");
        assertTrue(testSwapProduct3.getProductTags().contains("tag2"));
        assertFalse(testSwapProduct3.getProductTags().contains("tag1"));
        testSwapProduct3.removeTag("tag2");
        assertFalse(testSwapProduct3.getProductTags().contains("tag1"));
        assertFalse(testSwapProduct3.getProductTags().contains("tag2"));

        AbstractProduct testSwapProduct4;

        testSwapProduct4 = new SwapProduct("name", "description", 50.05, testUser1);

        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag("")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag(" ")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag("a b")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag("#")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testSwapProduct4.removeTag("#$%")); // Equivalence class: tag must have more than 0 characters, no spaces, and no special characters (invalid case, middle case)

        assertThrows(NoSuchElementException.class, ()-> testSwapProduct4.removeTag("tag1")); // Equivalence class: tag must already be attached to SwapProduct (invalid case)

        // Equivalence class: removing 1 tag (valid case, border case)
        testSwapProduct4.addTag("tag1");
        testSwapProduct4.removeTag("tag1");
        assertFalse(testSwapProduct4.getProductTags().contains("tag2"));
        assertFalse(testSwapProduct4.getProductTags().contains("tag1"));

        // Equivalence class: removing 2 tags (valid case, middle case)
        testSwapProduct4.addTag("tag1");
        testSwapProduct4.addTag("tag2");
        testSwapProduct4.removeTag("tag1");
        assertTrue(testSwapProduct4.getProductTags().contains("tag2"));
        assertFalse(testSwapProduct4.getProductTags().contains("tag1"));
        testSwapProduct4.removeTag("tag2");
        assertFalse(testSwapProduct4.getProductTags().contains("tag1"));
        assertFalse(testSwapProduct4.getProductTags().contains("tag2"));
    }

}
