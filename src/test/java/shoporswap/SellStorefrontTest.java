package shoporswap;

import org.junit.jupiter.api.Test;
import shoporswap.Client;
import shoporswap.SellProduct;
import shoporswap.SellStorefront;
import shoporswap.Storefront;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class SellStorefrontTest {

    /**
     * Automated tests for constructors methods:
     * - shoporswap.SellStorefront.shoporswap.SellStorefront constructor with 0 parameters (default)
     * - shoporswap.SellStorefront.shoporswap.SellStorefront constructor with 2 parameters
     * - shoporswap.SellStorefront.shoporswap.SellStorefront constructor with 3 parameters
     */
    @Test
    void constructorsSellStorefrontTests(){

        String invalidName = " invalid#name ";
        String validName = "Valid Name";

        Client testUser1 = new Client("accountname1", "password1");
        Client testUser2 = new Client("accountname2", "password2");

        assertThrows(IllegalArgumentException.class, ()-> new SellStorefront(invalidName, testUser1));
        assertThrows(IllegalArgumentException.class, ()-> new SellStorefront(invalidName, testUser2));
        assertThrows(IllegalArgumentException.class, ()-> new SellStorefront(invalidName, null));

        SellStorefront testSellStorefront1, testSellStorefront2, testSellStorefront3, testSellStorefront4;

        testSellStorefront1 = new SellStorefront(validName, testUser1);
        assertEquals(validName, testSellStorefront1.getStorefrontName());
        assertEquals(testUser1, testSellStorefront1.retrieveStorefrontOwner());
        assertEquals(0, testSellStorefront1.getSellProducts().size());
        testSellStorefront2 = new SellStorefront(
                validName,
                testUser2,
                Arrays.asList(
                        new SellProduct("product1", "description1", 50, testUser1),
                        new SellProduct("product2", "description2", 50, testUser1)
                )
        );
        assertEquals(validName, testSellStorefront2.getStorefrontName());
        assertEquals(testUser2, testSellStorefront2.retrieveStorefrontOwner());
        assertEquals(2, testSellStorefront2.getSellProducts().size());

        testSellStorefront3 = new SellStorefront(
                "Valid Name 2",
                testUser2,
                Arrays.asList(
                        new SellProduct("product1", "description1", 50, testUser1),
                        new SellProduct("product2", "description2", 50, testUser2)
                )
        );
        assertEquals("Valid Name 2", testSellStorefront3.getStorefrontName());
        assertEquals(testUser2, testSellStorefront3.retrieveStorefrontOwner());
        assertEquals(2, testSellStorefront3.getSellProducts().size());

        testSellStorefront4 = new SellStorefront();
        assertEquals("DEFAULT NAME", testSellStorefront4.getStorefrontName());
        assertEquals(null, testSellStorefront4.retrieveStorefrontOwner());
        assertEquals(0, testSellStorefront4.getSellProducts().size());

        Storefront testSellStorefront5, testSellStorefront6, testSellStorefront7, testSellStorefront8;

        testSellStorefront5 = new SellStorefront(validName, testUser1);
        assertEquals(validName, testSellStorefront5.getStorefrontName());
        assertEquals(testUser1, testSellStorefront5.retrieveStorefrontOwner());
        assertEquals(0, testSellStorefront5.getStorefrontProducts().size());
        testSellStorefront6 = new SellStorefront(
                validName,
                testUser2,
                Arrays.asList(
                        new SellProduct("product1", "description1", 50, testUser1),
                        new SellProduct("product2", "description2", 50, testUser1)
                )
        );
        assertEquals(validName, testSellStorefront6.getStorefrontName());
        assertEquals(testUser2, testSellStorefront6.retrieveStorefrontOwner());
        assertEquals(2, testSellStorefront6.getStorefrontProducts().size());

        testSellStorefront7 = new SellStorefront(
                "Valid Name 2",
                testUser2,
                Arrays.asList(
                        new SellProduct("product1", "description1", 50, testUser1),
                        new SellProduct("product2", "description2", 50, testUser2)
                )
        );
        assertEquals("Valid Name 2", testSellStorefront7.getStorefrontName());
        assertEquals(testUser2, testSellStorefront7.retrieveStorefrontOwner());
        assertEquals(2, testSellStorefront7.getStorefrontProducts().size());

        testSellStorefront8 = new SellStorefront();
        assertEquals("DEFAULT NAME", testSellStorefront8.getStorefrontName());
        assertEquals(null, testSellStorefront8.retrieveStorefrontOwner());
        assertEquals(0, testSellStorefront8.getStorefrontProducts().size());

    }

    /**
     * Automated tests for shoporswap.SellStorefront.addProduct method
     */
    @Test
    void addProductSellStorefrontTest(){

        SellStorefront testSellStorefront1;

        Client testUser1 = new Client("accountname1", "password1");
        Client testUser2 = new Client("accountname2", "password2");

        List<SellProduct> testProductsList1 = Arrays.asList(
                new SellProduct("test1", "test product 1", 50, testUser1),
                new SellProduct("test2", "test product 2", 50, testUser1),
                new SellProduct("test3", "test product 3", 50, testUser2)
        );

        testSellStorefront1 = new SellStorefront("Valid name", testUser1);
        testSellStorefront1.addProduct(testProductsList1.get(0));
        assertEquals(1, testSellStorefront1.getSellProducts().size());
        assertEquals(1, testSellStorefront1.getStorefrontProducts().size());
        testSellStorefront1.addProduct(testProductsList1.get(1));
        assertEquals(2, testSellStorefront1.getSellProducts().size());
        assertEquals(2, testSellStorefront1.getStorefrontProducts().size());
        assertThrows(IllegalArgumentException.class, ()-> testSellStorefront1.addProduct(testProductsList1.get(2)));
        assertEquals(2, testSellStorefront1.getSellProducts().size());
        assertEquals(2, testSellStorefront1.getStorefrontProducts().size());
        testSellStorefront1.addProduct(testProductsList1.get(0));
        assertEquals(3, testSellStorefront1.getSellProducts().size());
        assertEquals(3, testSellStorefront1.getStorefrontProducts().size());
    }

    /**
     * Automated tests for shoporswap.SellStorefront.findProduct method
     */
    @Test
    void findProductSellStorefrontTest(){
        SellStorefront testSellStorefront1;

        Client testUser1 = new Client("accountname1", "password1");
        Client testUser2 = new Client("accountname2", "password2");

        List<SellProduct> testProductsList1 = Arrays.asList(
                new SellProduct("test1", "test product 1", 50, testUser1),
                new SellProduct("test2", "test product 2", 50, testUser1),
                new SellProduct("test3", "test product 3", 50, testUser2)
        );
        SellProduct testAddedProduct1, testAddedProduct2;

        testSellStorefront1 = new SellStorefront("Valid name", testUser1);
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.findProduct(testProductsList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.findProduct(testProductsList1.get(1)));
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.findProduct(testProductsList1.get(2)));

        testAddedProduct1 = testSellStorefront1.addProduct(testProductsList1.get(0));
        assertEquals(testAddedProduct1, testSellStorefront1.findProduct(testAddedProduct1));
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.findProduct(testProductsList1.get(1)));
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.findProduct(testProductsList1.get(2)));

        testAddedProduct2 = testSellStorefront1.addProduct(testProductsList1.get(1));
        assertEquals(testAddedProduct1, testSellStorefront1.findProduct(testAddedProduct1));
        assertEquals(testAddedProduct2, testSellStorefront1.findProduct(testAddedProduct2));
    }

    /**
     * Automated tests for shoporswap.SellStorefront.removeProduct method
     */
    @Test
    void removeProductSellStorefrontTest(){
        SellStorefront testSellStorefront1;

        Client testUser1 = new Client("accountname1", "password1");
        Client testUser2 = new Client("accountname2", "password2");

        List<SellProduct> testProductsList1 = Arrays.asList(
                new SellProduct("test1", "test product 1", 50, testUser1),
                new SellProduct("test2", "test product 2", 50, testUser1),
                new SellProduct("test3", "test product 3", 50, testUser2)
        );
        SellProduct testAddedProduct1, testAddedProduct2;

        testSellStorefront1 = new SellStorefront("Valid name", testUser1);
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.removeProduct(testProductsList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.removeProduct(testProductsList1.get(1)));
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.removeProduct(testProductsList1.get(2)));

        testAddedProduct1 = testSellStorefront1.addProduct(testProductsList1.get(0));
        assertEquals(testAddedProduct1, testSellStorefront1.removeProduct(testAddedProduct1));
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.removeProduct(testProductsList1.get(1)));
        assertThrows(NoSuchElementException.class, ()-> testSellStorefront1.removeProduct(testProductsList1.get(2)));

        testAddedProduct1 = testSellStorefront1.addProduct(testProductsList1.get(0));
        testAddedProduct2 = testSellStorefront1.addProduct(testProductsList1.get(1));
        assertEquals(testAddedProduct1, testSellStorefront1.removeProduct(testAddedProduct1));
        assertEquals(testAddedProduct2, testSellStorefront1.removeProduct(testAddedProduct2));
    }

    /**
     * Automated tests for shoporswap.SellStorefront.completeTransaction method
     */
    @Test
    void completeTransactionSellStorefrontTest(){

        SellStorefront testSellStorefront1, testSellStorefront2;

        Client testUser1 = new Client("accountname1", "password1");
        Client testUser2 = new Client("accountname2", "password2");

        List<SellProduct> testProductsList1 = Arrays.asList(
                new SellProduct("test1", "test product 1", 50, testUser1),
                new SellProduct("test2", "test product 2", 50, testUser1),
                new SellProduct("test3", "test product 3", 50, testUser2),
                new SellProduct("test4", "test product 4", 50, testUser2)
        );

        testSellStorefront1 = new SellStorefront("test storefront 1", testUser1);
        testSellStorefront2 = new SellStorefront("test storefront 2", testUser2);

        testSellStorefront1.addProduct(testProductsList1.get(0));
        testSellStorefront1.addProduct(testProductsList1.get(1));
        testSellStorefront2.addProduct(testProductsList1.get(2));
        testSellStorefront2.addProduct(testProductsList1.get(3));

        testSellStorefront1.completeTransaction(testProductsList1.get(0), testUser2);

    }

    /**
     * Automated tests for mutator methods:
     * - shoporswap.SellStorefront.getSellProducts
     * - shoporswap.SellStorefront.setSellProducts
     */
    @Test
    void mutatorsSellStorefrontTests(){

        String validName = "valid name";
        Client testUser1 = new Client("accountname1", "password1");
        Client testUser2 = new Client("accountname2", "password2");

        SellStorefront testSellStorefront1, testSellStorefront2, testSellStorefront3, testSellStorefront4;

        testSellStorefront1 = new SellStorefront();
        testSellStorefront1.setStorefrontName(validName);
        testSellStorefront1.setStorefrontOwner(testUser1);
        assertEquals(validName, testSellStorefront1.getStorefrontName());
        assertEquals(testUser1, testSellStorefront1.retrieveStorefrontOwner());
        assertEquals(0, testSellStorefront1.getSellProducts().size());

        testSellStorefront2 = new SellStorefront();
        testSellStorefront2.setStorefrontName(validName);
        testSellStorefront2.setStorefrontOwner(testUser2);
        testSellStorefront2.setSellProducts(Arrays.asList(
                new SellProduct("product1", "description1", 50, testUser1),
                new SellProduct("product2", "description2", 50, testUser1)
        ));
        assertEquals(validName, testSellStorefront2.getStorefrontName());
        assertEquals(testUser2, testSellStorefront2.retrieveStorefrontOwner());
        assertEquals(2, testSellStorefront2.getSellProducts().size());
    }

}
