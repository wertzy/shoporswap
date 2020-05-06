package shoporswap;

import org.junit.jupiter.api.Test;
import shoporswap.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    /**
     * Automated tests for shoporswap.Client constructor methods:
     * - 0 parameters (default)
     * - 2 parameters
     */
    @Test
    void constructorsTests(){

        Client testClient1, testClient2;

        testClient1 = new Client();
        assertEquals("DefaultClient", testClient1.getAccountName());
        assertEquals("DefaultPassword", testClient1.getAccountPassword());
        assertFalse(testClient1.getIsFrozen());
        assertEquals(0, testClient1.getMyOwnedProductList().size());
        assertEquals(0, testClient1.getMyStorefronts().size());

        testClient2 = new Client("test1", "pass1");
        assertEquals("test1", testClient2.getAccountName());
        assertEquals("pass1", testClient2.getAccountPassword());
        assertFalse(testClient2.getIsFrozen());
        assertEquals(0, testClient2.getMyOwnedProductList().size());
        assertEquals(0, testClient2.getMyStorefronts().size());

        Account testClient3, testClient4;

        testClient3 = new Client();
        assertEquals("DefaultClient", testClient3.getAccountName());
        assertEquals("DefaultPassword", testClient3.getAccountPassword());
        assertFalse(testClient3.getIsFrozen());
        assertEquals(0, ((Client) testClient3).getMyOwnedProductList().size());
        assertEquals(0, ((Client) testClient3).getMyStorefronts().size());

        testClient4 = new Client("test1", "pass1");
        assertEquals("test1", testClient4.getAccountName());
        assertEquals("pass1", testClient4.getAccountPassword());
        assertFalse(testClient4.getIsFrozen());
        assertEquals(0, ((Client) testClient4).getMyOwnedProductList().size());
        assertEquals(0, ((Client) testClient4).getMyStorefronts().size());

        List<String> invalidAccountNames = Arrays.asList(
                "",
                "des mond",
                "a#b",
                "%&l-()d"
        );

        List<String> invalidAccountPasswords = Arrays.asList(
                "",
                "des mond"
        );

        for(String invalidName : invalidAccountNames){
            assertThrows(IllegalArgumentException.class, ()-> new Client(invalidName, "pass1"));
            for(String invalidPassword : invalidAccountPasswords){
                assertThrows(IllegalArgumentException.class, ()-> new Client(invalidName, invalidPassword));
                assertThrows(IllegalArgumentException.class, ()-> new Client("test1", invalidPassword));
            }
        }

    }

    /**
     * Automated tests for shoporswap.Client mutator methods:
     * - shoporswap.Client.getMyProductList
     * - shoporswap.Client.setMyProductList
     */
    @Test
    void mutatorsTests(){

        Client testClient1, testClient2;

        testClient1 = new Client();
        assertEquals("DefaultClient", testClient1.getAccountName());
        assertEquals("DefaultPassword", testClient1.getAccountPassword());
        assertFalse(testClient1.getIsFrozen());
        testClient1.setAccountName("test1");
        testClient1.setAccountPassword("pass1");
        testClient1.setIsFrozen(true);
        assertEquals("test1", testClient1.getAccountName());
        assertEquals("pass1", testClient1.getAccountPassword());
        assertTrue(testClient1.getIsFrozen());

        testClient2 = new Client();
        assertEquals("DefaultClient", testClient2.getAccountName());
        assertEquals("DefaultPassword", testClient2.getAccountPassword());
        assertFalse(testClient2.getIsFrozen());
        testClient2.setAccountName("test2");
        testClient2.setAccountPassword("pass2");
        testClient2.setIsFrozen(true);
        assertEquals("test2", testClient2.getAccountName());
        assertEquals("pass2", testClient2.getAccountPassword());
        assertTrue(testClient2.getIsFrozen());

        List<AbstractProduct> testProductList1 = Arrays.asList(
                new SellProduct("product1", "description1", 50, testClient1),
                new SwapProduct("product2", "description2", 50, testClient2)
        );
        testClient1.setMyOwnedProductList(testProductList1);
        assertEquals(testProductList1, testClient1.getMyOwnedProductList());
        assertEquals(0, testClient1.getMyStorefronts().size());

        List<AbstractProduct> testProductList2 = Arrays.asList(
                new SellProduct("product1", "description1", 50, testClient2),
                new SwapProduct("product2", "description2", 50, testClient1)
        );
        testClient2.setMyOwnedProductList(testProductList2);
        assertEquals(testProductList2, testClient2.getMyOwnedProductList());
        assertEquals(0, testClient2.getMyStorefronts().size());

    }

    /**
     * Automated tests for shoporswap.Client.addSellProduct method
     */
    @Test
    void addSellProductTest(){
        Client testClient1, testClient2;
        List<SellProduct> testSellProductList1;

        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test2", "pass2");

        testSellProductList1 = Arrays.asList(
                new SellProduct("product1", "description1", 50, testClient1),
                new SellProduct("product2", "description2", 50, testClient2)
        );

        testClient1.addSellProduct(testSellProductList1.get(0));
        assertEquals(1, testClient1.getMyOwnedProductList().size());
        assertEquals(testSellProductList1.get(0), testClient1.getMyOwnedProductList().get(0));
        assertEquals(testClient1, testClient1.getMyOwnedProductList().get(0).getProductMerchant());

        testClient1.addSellProduct(testSellProductList1.get(1));
        assertEquals(2, testClient1.getMyOwnedProductList().size());
        assertEquals(testSellProductList1.get(1), testClient1.getMyOwnedProductList().get(1));
        assertEquals(testClient2, testClient1.getMyOwnedProductList().get(1).getProductMerchant());
    }

    /**
     * Automated tests for shoporswap.Client.addSwapProduct method
     */
    @Test
    void addSwapProductTest(){
        Client testClient1, testClient2;
        List<SwapProduct> testSwapProductList1;

        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test2", "pass2");

        testSwapProductList1 = Arrays.asList(
                new SwapProduct("product1", "description1", 50, testClient1),
                new SwapProduct("product2", "description2", 50, testClient2)
        );

        testClient1.addSwapProduct(testSwapProductList1.get(0));
        assertEquals(1, testClient1.getMyOwnedProductList().size());
        assertEquals(testSwapProductList1.get(0), testClient1.getMyOwnedProductList().get(0));
        assertEquals(testClient1, testClient1.getMyOwnedProductList().get(0).getProductMerchant());

        testClient1.addSwapProduct(testSwapProductList1.get(1));
        assertEquals(2, testClient1.getMyOwnedProductList().size());
        assertEquals(testSwapProductList1.get(1), testClient1.getMyOwnedProductList().get(1));
        assertEquals(testClient2, testClient1.getMyOwnedProductList().get(1).getProductMerchant());
    }

    /**
     * Automated tests for shoporswap.Client.findSellProduct method
     */
    @Test
    void findSellProductTest(){
        Client testClient1, testClient2;
        List<SellProduct> testSellProductList1;

        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test2", "pass2");

        testSellProductList1 = Arrays.asList(
                new SellProduct("product1", "description1", 50, testClient1),
                new SellProduct("product2", "description2", 50, testClient2)
        );

        assertThrows(NoSuchElementException.class, ()-> testClient1.findSellProduct(testSellProductList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findSellProduct(testSellProductList1.get(1)));
        testClient1.addSellProduct(testSellProductList1.get(0));
        assertEquals(testSellProductList1.get(0), testClient1.findSellProduct(testSellProductList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findSellProduct(testSellProductList1.get(1)));
        testClient1.addSellProduct(testSellProductList1.get(1));
        assertEquals(testSellProductList1.get(0), testClient1.findSellProduct(testSellProductList1.get(0)));
        assertEquals(testSellProductList1.get(1), testClient1.findSellProduct(testSellProductList1.get(1)));
    }

    /**
     * Automated tests for shoporswap.Client.findSwapProduct method
     */
    @Test
    void findSwapProductTest(){
        Client testClient1, testClient2;
        List<SwapProduct> testSwapProductList1;

        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test2", "pass2");

        testSwapProductList1 = Arrays.asList(
                new SwapProduct("product1", "description1", 50, testClient1),
                new SwapProduct("product2", "description2", 50, testClient2)
        );

        assertThrows(NoSuchElementException.class, ()-> testClient1.findSwapProduct(testSwapProductList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findSwapProduct(testSwapProductList1.get(1)));
        testClient1.addSwapProduct(testSwapProductList1.get(0));
        assertEquals(testSwapProductList1.get(0), testClient1.findSwapProduct(testSwapProductList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findSwapProduct(testSwapProductList1.get(1)));
        testClient1.addSwapProduct(testSwapProductList1.get(1));
        assertEquals(testSwapProductList1.get(0), testClient1.findSwapProduct(testSwapProductList1.get(0)));
        assertEquals(testSwapProductList1.get(1), testClient1.findSwapProduct(testSwapProductList1.get(1)));
    }

    /**
     * Automated tests for shoporswap.Client.removeSellProduct method
     */
    @Test
    void removeSellProductTest(){
        Client testClient1, testClient2;
        List<SellProduct> testSellProductList1;

        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test2", "pass2");

        testSellProductList1 = Arrays.asList(
                new SellProduct("product1", "description1", 50, testClient1),
                new SellProduct("product2", "description2", 50, testClient2)
        );

        assertThrows(NoSuchElementException.class, ()-> testClient1.findSellProduct(testSellProductList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findSellProduct(testSellProductList1.get(1)));
        testClient1.addSellProduct(testSellProductList1.get(0));
        assertEquals(testSellProductList1.get(0), testClient1.removeSellProduct(testSellProductList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findSellProduct(testSellProductList1.get(1)));
        testClient1.addSellProduct(testSellProductList1.get(0));
        testClient1.addSellProduct(testSellProductList1.get(1));
        assertEquals(testSellProductList1.get(0), testClient1.removeSellProduct(testSellProductList1.get(0)));
        assertEquals(testSellProductList1.get(1), testClient1.removeSellProduct(testSellProductList1.get(1)));
    }

    /**
     * Automated tests for shoporswap.Client.removeSwapProduct method
     */
    @Test
    void removeSwapProductTest(){
        Client testClient1, testClient2;
        List<SwapProduct> testSwapProductList1;

        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test2", "pass2");

        testSwapProductList1 = Arrays.asList(
                new SwapProduct("product1", "description1", 50, testClient1),
                new SwapProduct("product2", "description2", 50, testClient2)
        );

        assertThrows(NoSuchElementException.class, ()-> testClient1.findSwapProduct(testSwapProductList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findSwapProduct(testSwapProductList1.get(1)));
        testClient1.addSwapProduct(testSwapProductList1.get(0));
        assertEquals(testSwapProductList1.get(0), testClient1.removeSwapProduct(testSwapProductList1.get(0)));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findSwapProduct(testSwapProductList1.get(1)));
        testClient1.addSwapProduct(testSwapProductList1.get(0));
        testClient1.addSwapProduct(testSwapProductList1.get(1));
        assertEquals(testSwapProductList1.get(0), testClient1.removeSwapProduct(testSwapProductList1.get(0)));
        assertEquals(testSwapProductList1.get(1), testClient1.removeSwapProduct(testSwapProductList1.get(1)));
    }

    /**
     * Automated tests for shoporswap.Client.addStorefront method
     */
    @Test
    void addStorefrontTest(){

        Client testClient1 = new Client("test1", "pass1");

        Storefront testSellStorefront1 = new SellStorefront("name1", testClient1);
        Storefront testSellStorefront2 = new SellStorefront("name2", testClient1);
        Storefront testSellStorefront3 = new SellStorefront("name3", testClient1);
        Storefront testSwapStorefront1 = new SwapStorefront("name3", testClient1);
        Storefront testSwapStorefront2 = new SwapStorefront("name4", testClient1);
        Storefront testSwapStorefront3 = new SwapStorefront("name5", testClient1);

        testClient1.addStorefront(testSellStorefront1);
        assertEquals(1, testClient1.getMyStorefronts().size());
        assertTrue(testClient1.getMyStorefronts().containsKey(testSellStorefront1.getStorefrontName()));

        testClient1.addStorefront(testSellStorefront2);
        assertEquals(2, testClient1.getMyStorefronts().size());
        assertTrue(testClient1.getMyStorefronts().containsKey(testSellStorefront2.getStorefrontName()));

        testClient1.addStorefront(testSellStorefront3);
        assertEquals(3, testClient1.getMyStorefronts().size());
        assertTrue(testClient1.getMyStorefronts().containsKey(testSellStorefront3.getStorefrontName()));

        assertThrows(IllegalArgumentException.class, ()-> testClient1.addStorefront(testSwapStorefront1));
        assertEquals(3, testClient1.getMyStorefronts().size());
        assertTrue(testClient1.getMyStorefronts().containsKey(testSwapStorefront1.getStorefrontName()));
        assertNotEquals(testSwapStorefront1, testClient1.getMyStorefronts().get(testSwapStorefront1.getStorefrontName()));

        testClient1.addStorefront(testSwapStorefront2);
        assertEquals(4, testClient1.getMyStorefronts().size());
        assertTrue(testClient1.getMyStorefronts().containsKey(testSwapStorefront2.getStorefrontName()));

        testClient1.addStorefront(testSwapStorefront3);
        assertEquals(5, testClient1.getMyStorefronts().size());
        assertTrue(testClient1.getMyStorefronts().containsKey(testSwapStorefront3.getStorefrontName()));
    }

    /**
     * Automated tests for shoporswap.Client.findStorefront method
     */
    @Test
    void findStorefrontTest(){

        Client testClient1 = new Client("test1", "pass1");

        Storefront testSellStorefront1 = new SellStorefront("name1", testClient1);
        Storefront testSellStorefront2 = new SellStorefront("name2", testClient1);
        Storefront testSellStorefront3 = new SellStorefront("name3", testClient1);
        Storefront testSwapStorefront1 = new SwapStorefront("name3", testClient1);
        Storefront testSwapStorefront2 = new SwapStorefront("name4", testClient1);
        Storefront testSwapStorefront3 = new SwapStorefront("name5", testClient1);

        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSellStorefront1));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSellStorefront2));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSellStorefront3));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSwapStorefront1));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSwapStorefront2));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSwapStorefront3));
        assertEquals(0, testClient1.getMyStorefronts().size());

        testClient1.addStorefront(testSellStorefront1);
        assertEquals(testSellStorefront1, testClient1.findStorefront(testSellStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSellStorefront2));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSellStorefront3));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSwapStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSwapStorefront2));
        assertThrows(NoSuchElementException.class, ()-> testClient1.findStorefront(testSwapStorefront3));

        testClient1.addStorefront(testSellStorefront2);
        testClient1.addStorefront(testSellStorefront3);
        testClient1.addStorefront(testSwapStorefront2);
        testClient1.addStorefront(testSwapStorefront3);
        assertEquals(5, testClient1.getMyStorefronts().size());
        assertEquals(testSellStorefront1, testClient1.findStorefront(testSellStorefront1));
        assertEquals(testSellStorefront2, testClient1.findStorefront(testSellStorefront2));
        assertEquals(testSellStorefront3, testClient1.findStorefront(testSellStorefront3));
        assertEquals(testSellStorefront3, testClient1.findStorefront(testSwapStorefront1));
        assertNotEquals(testSwapStorefront1, testClient1.findStorefront(testSwapStorefront1));
        assertEquals(testSwapStorefront2, testClient1.findStorefront(testSwapStorefront2));
        assertEquals(testSwapStorefront3, testClient1.findStorefront(testSwapStorefront3));
        
    }

    /**
     * Automated tests for shoporswap.Client.removeStorefront method
     */
    @Test
    void removeStorefrontTest(){

        Client testClient1 = new Client("test1", "pass1");

        Storefront testSellStorefront1 = new SellStorefront("name1", testClient1);
        Storefront testSellStorefront2 = new SellStorefront("name2", testClient1);
        Storefront testSellStorefront3 = new SellStorefront("name3", testClient1);
        Storefront testSwapStorefront1 = new SwapStorefront("name3", testClient1);
        Storefront testSwapStorefront2 = new SwapStorefront("name4", testClient1);
        Storefront testSwapStorefront3 = new SwapStorefront("name5", testClient1);

        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSellStorefront1));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSellStorefront2));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSellStorefront3));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSwapStorefront1));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSwapStorefront2));
        assertEquals(0, testClient1.getMyStorefronts().size());
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSwapStorefront3));
        assertEquals(0, testClient1.getMyStorefronts().size());

        testClient1.addStorefront(testSellStorefront1);
        assertEquals(testSellStorefront1, testClient1.removeStorefront(testSellStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSellStorefront2));
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSellStorefront3));
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSwapStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSwapStorefront2));
        assertThrows(NoSuchElementException.class, ()-> testClient1.removeStorefront(testSwapStorefront3));

        testClient1.addStorefront(testSellStorefront1);
        testClient1.addStorefront(testSellStorefront2);
        testClient1.addStorefront(testSellStorefront3);
        testClient1.addStorefront(testSwapStorefront2);
        testClient1.addStorefront(testSwapStorefront3);
        assertEquals(5, testClient1.getMyStorefronts().size());
        assertEquals(testSellStorefront1, testClient1.removeStorefront(testSellStorefront1));
        assertEquals(testSellStorefront2, testClient1.removeStorefront(testSellStorefront2));
        assertEquals(testSellStorefront3, testClient1.removeStorefront(testSellStorefront3));
        testClient1.addStorefront(testSellStorefront3);
        assertEquals(testSellStorefront3, testClient1.removeStorefront(testSwapStorefront1));
        testClient1.addStorefront(testSellStorefront3);
        assertNotEquals(testSwapStorefront1, testClient1.removeStorefront(testSwapStorefront1));
        assertEquals(testSwapStorefront2, testClient1.removeStorefront(testSwapStorefront2));
        assertEquals(testSwapStorefront3, testClient1.removeStorefront(testSwapStorefront3));
        
    }

}
