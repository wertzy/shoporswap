import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SwapStorefrontTest {

    /**
     * Automated tests for constructors methods:
     * - SwapStorefront.SwapStorefront constructor with 0 parameters
     * - SwapStorefront.SwapStorefront constructor with 2 parameters
     * - SwapStorefront.SwapStorefront constructor with 3 parameters
     */
    @Test
    void constructorsSwapStorefrontTests(){

        String invalidName = " invalid#name ";
        String validName = "Valid Name";

        User testUser1 = new User("accountname1", "password1");
        User testUser2 = new User("accountname2", "password2");

        assertThrows(IllegalArgumentException.class, ()-> new SwapStorefront(invalidName, testUser1));
        assertThrows(IllegalArgumentException.class, ()-> new SwapStorefront(invalidName, testUser2));
        assertThrows(IllegalArgumentException.class, ()-> new SwapStorefront(invalidName, null));
        assertThrows(IllegalArgumentException.class, ()-> new SwapStorefront(validName, null));

        SwapStorefront testSwapStorefront1, testSwapStorefront2, testSwapStorefront3, testSwapStorefront4;

        testSwapStorefront1 = new SwapStorefront(validName, testUser1);
        assertEquals(validName, testSwapStorefront1.getStorefrontName());
        assertEquals(testUser1, testSwapStorefront1.getStorefrontOwner());
        assertEquals(0, testSwapStorefront1.getSwapProducts().size());
        testSwapStorefront2 = new SwapStorefront(
                validName,
                testUser2,
                Arrays.asList(
                        new SwapProduct("product1", "description1", 50, testUser1),
                        new SwapProduct("product2", "description2", 50, testUser1)
                )
        );
        assertEquals(validName, testSwapStorefront2.getStorefrontName());
        assertEquals(testUser2, testSwapStorefront2.getStorefrontOwner());
        assertEquals(2, testSwapStorefront2.getSwapProducts().size());

        testSwapStorefront3 = new SwapStorefront(
                "Valid Name 2",
                testUser2,
                Arrays.asList(
                        new SwapProduct("product1", "description1", 50, testUser1),
                        new SwapProduct("product2", "description2", 50, testUser2)
                )
        );
        assertEquals(validName, testSwapStorefront3.getStorefrontName());
        assertEquals(testUser2, testSwapStorefront3.getStorefrontOwner());
        assertEquals(2, testSwapStorefront3.getSwapProducts().size());

        testSwapStorefront4 = new SwapStorefront();
        assertEquals("DEFAULT NAME", testSwapStorefront4.getStorefrontName());
        assertEquals(null, testSwapStorefront4.getStorefrontOwner());
        assertEquals(0, testSwapStorefront4.getSwapProducts().size());
        
    }

    /**
     * Automated tests for SwapStorefront.addProduct method
     */
    @Test
    void addProductSwapStorefrontTest(){
        SwapStorefront testSwapStorefront1;

        User testUser1 = new User("accountname1", "password1");
        User testUser2 = new User("accountname2", "password2");

        List<SwapProduct> testProductsList1 = Arrays.asList(
                new SwapProduct("test1", "test product 1", 50, testUser1),
                new SwapProduct("test2", "test product 2", 50, testUser1),
                new SwapProduct("test3", "test product 3", 50, testUser2)
        );

        testSwapStorefront1 = new SwapStorefront("Valid name", testUser1);
        testSwapStorefront1.addProduct(testProductsList1.get(0));
        assertEquals(1, testSwapStorefront1.getSwapProducts().size());
        assertEquals(1, testSwapStorefront1.getStorefrontProducts().size());
        testSwapStorefront1.addProduct(testProductsList1.get(1));
        assertEquals(2, testSwapStorefront1.getSwapProducts().size());
        assertEquals(2, testSwapStorefront1.getStorefrontProducts().size());
        assertThrows(IllegalArgumentException.class, ()-> testSwapStorefront1.addProduct(testProductsList1.get(2)));
        assertEquals(2, testSwapStorefront1.getSwapProducts().size());
        assertEquals(2, testSwapStorefront1.getStorefrontProducts().size());
        testSwapStorefront1.addProduct(testProductsList1.get(0));
        assertEquals(3, testSwapStorefront1.getSwapProducts().size());
        assertEquals(3, testSwapStorefront1.getStorefrontProducts().size());
    }

    /**
     * Automated tests for SwapStorefront.findProduct method
     */
    @Test
    void findProductSwapStorefrontTest(){
        SwapStorefront testSwapStorefront1;

        User testUser1 = new User("accountname1", "password1");
        User testUser2 = new User("accountname2", "password2");

        List<SwapProduct> testProductsList1 = Arrays.asList(
                new SwapProduct("test1", "test product 1", 50, testUser1),
                new SwapProduct("test2", "test product 2", 50, testUser1),
                new SwapProduct("test3", "test product 3", 50, testUser2)
        );
        SwapProduct testAddedProduct1, testAddedProduct2;

        testSwapStorefront1 = new SwapStorefront("Valid name", testUser1);
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.findProduct(testProductsList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.findProduct(testProductsList1.get(1)));
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.findProduct(testProductsList1.get(2)));

        testAddedProduct1 = testSwapStorefront1.addProduct(testProductsList1.get(0));
        assertEquals(testAddedProduct1, testSwapStorefront1.findProduct(testAddedProduct1));
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.findProduct(testProductsList1.get(1)));
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.findProduct(testProductsList1.get(2)));

        testAddedProduct2 = testSwapStorefront1.addProduct(testProductsList1.get(1));
        assertEquals(testAddedProduct1, testSwapStorefront1.findProduct(testAddedProduct1));
        assertEquals(testAddedProduct2, testSwapStorefront1.findProduct(testAddedProduct2));
    }

    /**
     * Automated tests for SwapStorefront.removeProduct method
     */
    @Test
    void removeProductSwapStorefrontTest(){
        SwapStorefront testSwapStorefront1;

        User testUser1 = new User("accountname1", "password1");
        User testUser2 = new User("accountname2", "password2");

        List<SwapProduct> testProductsList1 = Arrays.asList(
                new SwapProduct("test1", "test product 1", 50, testUser1),
                new SwapProduct("test2", "test product 2", 50, testUser1),
                new SwapProduct("test3", "test product 3", 50, testUser2)
        );
        SwapProduct testAddedProduct1, testAddedProduct2;

        testSwapStorefront1 = new SwapStorefront("Valid name", testUser1);
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.removeProduct(testProductsList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.removeProduct(testProductsList1.get(1)));
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.removeProduct(testProductsList1.get(2)));

        testAddedProduct1 = testSwapStorefront1.addProduct(testProductsList1.get(0));
        assertEquals(testAddedProduct1, testSwapStorefront1.removeProduct(testAddedProduct1));
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.removeProduct(testProductsList1.get(1)));
        assertThrows(NoSuchElementException.class, ()-> testSwapStorefront1.removeProduct(testProductsList1.get(2)));

        testAddedProduct1 = testSwapStorefront1.addProduct(testProductsList1.get(0));
        testAddedProduct2 = testSwapStorefront1.addProduct(testProductsList1.get(1));
        assertEquals(testAddedProduct1, testSwapStorefront1.removeProduct(testAddedProduct1));
        assertEquals(testAddedProduct2, testSwapStorefront1.removeProduct(testAddedProduct2));
    }

    /**
     * Automated tests for SwapStorefront.completeTransaction method
     */
    @Test
    void completeTransactionSwapStorefrontTest(){}

    /**
     * Automated tests for mutator methods:
     * - SwapStorefront.getSwapProducts
     * - SwapStorefront.setSwapProducts
     */
    @Test
    void mutatorsSwapStorefrontTests(){}
    
}
