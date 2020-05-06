import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ShopOrSwapTest {

    /**
     * Automated tests for ShopOrSwap constructor methods
     */
    @Test
    void constructorsTest(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        assertNotNull(testShopOrSwap.getAccountFactory());
        assertNotNull(testShopOrSwap.getProductFactory());
        assertNotNull(testShopOrSwap.getMessageFactory());
        assertNotNull(testShopOrSwap.getStorefrontFactory());
        assertNotNull(testShopOrSwap.getAccountCollection());
        assertEquals(0, testShopOrSwap.getAccountCollection().size());
    }

    /**
     * Automated tests for ShopOrSwap mutator methods
     */
    @Test
    void mutatorsTests(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        assertNotNull(testShopOrSwap.getAccountFactory());
        assertNotNull(testShopOrSwap.getProductFactory());
        assertNotNull(testShopOrSwap.getMessageFactory());
        assertNotNull(testShopOrSwap.getStorefrontFactory());
        assertNotNull(testShopOrSwap.getAccountCollection());

        AccountFactory testAccountFactory = new AccountFactory();
        testShopOrSwap.setAccountFactory(testAccountFactory);
        assertEquals(testAccountFactory, testShopOrSwap.getAccountFactory());

        AbstractProductFactory testAbstractProductFactory = new AbstractProductFactory();
        testShopOrSwap.setProductFactory(testAbstractProductFactory);
        assertEquals(testAbstractProductFactory, testShopOrSwap.getProductFactory());

        AbstractMessageFactory testAbstractMessageFactory = new AbstractMessageFactory();
        testShopOrSwap.setMessageFactory(testAbstractMessageFactory);
        assertEquals(testAbstractMessageFactory, testShopOrSwap.getMessageFactory());

        StorefrontFactory testStorefrontFactory = new StorefrontFactory();
        testShopOrSwap.setStorefrontFactory(testStorefrontFactory);
        assertEquals(testStorefrontFactory, testShopOrSwap.getStorefrontFactory());

        Map<String, Account> testAccountCollection = new HashMap<String, Account>();
        testAccountCollection.put("test1", new Client("test1", "pass1"));
        testShopOrSwap.setAccountCollection(testAccountCollection);
        assertEquals(1, testShopOrSwap.getAccountCollection());
    }

    /**
     * Automated tests for ShopOrSwap.addAccount method
     */
    @Test
    void addAccountTest(){

        ShopOrSwap testShopOrSwap = new ShopOrSwap();

        Client testClient1, testClient2, testClient3;
        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test1", "pass1");
        testClient3 = new Client("test2", "pass2");

        testShopOrSwap.addAccount(testClient1);
        assertEquals(1, testShopOrSwap.getAccountCollection());
        assertTrue(testShopOrSwap.getAccountCollection().containsKey(testClient1.getAccountName()));

        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addAccount(testClient2));

        testShopOrSwap.addAccount(testClient3);
        assertEquals(2, testShopOrSwap.getAccountCollection());
        assertTrue(testShopOrSwap.getAccountCollection().containsKey(testClient1.getAccountName()));
        assertTrue(testShopOrSwap.getAccountCollection().containsKey(testClient3.getAccountName()));

    }

    /**
     * Automated tests for ShopOrSwap.findAccount method
     */
    @Test
    void findAccountTest(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();

        Client testClient1, testClient2, testClient3;
        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test2", "pass2");
        testClient3 = new Client("test3", "pass3");

        testShopOrSwap.addAccount(testClient1);
        assertEquals(testClient1, testShopOrSwap.findAccount(testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient2));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient3));

        testShopOrSwap.addAccount(testClient2);
        assertEquals(testClient1, testShopOrSwap.findAccount(testClient1));
        assertEquals(testClient1, testShopOrSwap.findAccount(testClient2));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient3));

        testShopOrSwap.addAccount(testClient3);
        assertEquals(testClient1, testShopOrSwap.findAccount(testClient1));
        assertEquals(testClient1, testShopOrSwap.findAccount(testClient2));
        assertEquals(testClient1, testShopOrSwap.findAccount(testClient3));

    }

    /**
     * Automated tests for ShopOrSwap.removeAccount method
     */
    @Test
    void removeAccountTest(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();

        Client testClient1, testClient2;
        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test2", "pass2");

        testShopOrSwap.addAccount(testClient1);
        assertEquals(1, testShopOrSwap.getAccountCollection().size());
        testShopOrSwap.removeAccount(testClient1);
        assertEquals(0, testShopOrSwap.getAccountCollection().size());
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient2));

        testShopOrSwap.addAccount(testClient1);
        testShopOrSwap.addAccount(testClient2);
        assertEquals(2, testShopOrSwap.getAccountCollection().size());
        testShopOrSwap.removeAccount(testClient1);
        assertEquals(1, testShopOrSwap.getAccountCollection().size());
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient1));
        testShopOrSwap.removeAccount(testClient2);
        assertEquals(0, testShopOrSwap.getAccountCollection().size());
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient2));

        testShopOrSwap.addAccount(testClient1);
        testShopOrSwap.addAccount(testClient2);
        assertEquals(2, testShopOrSwap.getAccountCollection().size());
        testShopOrSwap.removeAccount(testClient2);
        assertEquals(1, testShopOrSwap.getAccountCollection().size());
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient2));
        testShopOrSwap.removeAccount(testClient1);
        assertEquals(0, testShopOrSwap.getAccountCollection().size());
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient2));
    }

    /**
     * Automated tests for ShopOrSwap.addStorefront method
     */
    @Test
    void addStorefrontTest(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.addStorefront("sell", "test1", testClient1));
        Storefront testSellStorefront1 = testShopOrSwap.addStorefront("sell","test1", testClient1);
        assertEquals("test1", testSellStorefront1.getStorefrontName());
        assertEquals(1, testClient1.getMyStorefronts());
        assertEquals(testSellStorefront1, testClient1.getMyStorefronts().get("test1"));
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addStorefront("sell", "test1", testClient1));
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addStorefront("swap", "test1", testClient1));
        Storefront testSellStorefront2 = testShopOrSwap.addStorefront("swap","test2", testClient1);
        assertEquals("test2", testSellStorefront2.getStorefrontName());
        assertEquals(2, testClient1.getMyStorefronts());
        assertEquals(testSellStorefront2, testClient1.getMyStorefronts().get("test2"));
    }

    /**
     * Automated tests for ShopOrSwap.findStorefront method
     */
    @Test
    void findStorefrontTest(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findStorefront("test1", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findStorefront("test2", testClient1));

        Storefront testSellStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        assertEquals(testSellStorefront1, testShopOrSwap.findStorefront("test1", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findStorefront("test2", testClient1));

        Storefront testSwapStorefront1 = testShopOrSwap.addStorefront("swap", "test2", testClient1);
        assertEquals(testSwapStorefront1, testShopOrSwap.findStorefront("test2", testClient1));
    }

    /**
     * Automated tests for ShopOrSwap.removeStorefront method
     */
    @Test
    void removeStorefrontTest(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test1", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test2", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test3", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test4", testClient1));

        Storefront testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        assertEquals(testStorefront1, testShopOrSwap.removeStorefront("test1", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test2", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test3", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test4", testClient1));

        testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        Storefront testStorefront2 = testShopOrSwap.addStorefront("sell", "test2", testClient1);
        assertEquals(testStorefront1, testShopOrSwap.removeStorefront("test1", testClient1));
        assertEquals(testStorefront2, testShopOrSwap.removeStorefront("test1", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test3", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test4", testClient1));

        testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        testStorefront2 = testShopOrSwap.addStorefront("sell", "test2", testClient1);
        Storefront testStorefront3 = testShopOrSwap.addStorefront("swap", "test3", testClient1);
        assertEquals(testStorefront1, testShopOrSwap.removeStorefront("test1", testClient1));
        assertEquals(testStorefront2, testShopOrSwap.removeStorefront("test1", testClient1));
        assertEquals(testStorefront3, testShopOrSwap.removeStorefront("test1", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test4", testClient1));

        testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        testStorefront2 = testShopOrSwap.addStorefront("sell", "test2", testClient1);
        testStorefront3 = testShopOrSwap.addStorefront("swap", "test3", testClient1);
        Storefront testStorefront4 = testShopOrSwap.addStorefront("swap", "test4", testClient1);
        assertEquals(testStorefront1, testShopOrSwap.removeStorefront("test1", testClient1));
        assertEquals(testStorefront2, testShopOrSwap.removeStorefront("test1", testClient1));
        assertEquals(testStorefront3, testShopOrSwap.removeStorefront("test1", testClient1));
        assertEquals(testStorefront4, testShopOrSwap.removeStorefront("test1", testClient1));

    }

    /**
     * Automated tests for ShopOrSwap.addToStorefront methods
     */
    @Test
    void addToStorefrontTests(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        Storefront testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        Storefront testStorefront2 = testShopOrSwap.addStorefront("sell", "test2", testClient1);
        Storefront testStorefront3 = testShopOrSwap.addStorefront("swap", "test3", testClient1);
        Storefront testStorefront4 = testShopOrSwap.addStorefront("swap", "test4", testClient1);

        SellProduct testProduct1 = new SellProduct("test1", "description1", 50, testClient1);
        SwapProduct testProduct2 = new SwapProduct("test3", "description3", 50, testClient1);

        testShopOrSwap.addToStorefront(testProduct1, testStorefront1);
        assertEquals(testProduct1, ((SellStorefront) testShopOrSwap.findStorefront("test1", testClient1)).findProduct(testProduct1));
        testShopOrSwap.addToStorefront(testProduct1, testStorefront2);
        assertEquals(testProduct1, ((SellStorefront) testShopOrSwap.findStorefront("test2", testClient1)).findProduct(testProduct1));
        testShopOrSwap.addToStorefront(testProduct2, testStorefront3);
        assertEquals(testProduct2, ((SwapStorefront) testShopOrSwap.findStorefront("test3", testClient1)).findProduct(testProduct2));
        testShopOrSwap.addToStorefront(testProduct2, testStorefront4);
        assertEquals(testProduct2, ((SwapStorefront) testShopOrSwap.findStorefront("test4", testClient1)).findProduct(testProduct2));

        testProduct1 = (SellProduct) testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);
        assertEquals(testProduct1, ((SellStorefront) testShopOrSwap.findStorefront("test1", testClient1)).findProduct(testProduct1));

        testProduct1 = (SellProduct) testShopOrSwap.addToStorefront("test2", "description3", 50, testStorefront2);
        assertEquals(testProduct1, ((SellStorefront) testShopOrSwap.findStorefront("test2", testClient1)).findProduct(testProduct1));

        testProduct2 = (SwapProduct) testShopOrSwap.addToStorefront("test3", "description3", 50, testStorefront3);
        assertEquals(testProduct2, ((SwapStorefront) testShopOrSwap.findStorefront("test3", testClient1)).findProduct(testProduct2));

        testProduct2 = (SwapProduct) testShopOrSwap.addToStorefront("test4", "description4", 50, testStorefront4);
        assertEquals(testProduct2, ((SwapStorefront) testShopOrSwap.findStorefront("test4", testClient1)).findProduct(testProduct2));
    }

    /**
     * Automated tests for ShopOrSwap.findInStorefront method
     */
    @Test
    void findInStorefrontTests(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront(new SellProduct(), new SellStorefront()));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront(new SwapProduct(), new SwapStorefront()));

        Storefront testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        Storefront testStorefront2 = testShopOrSwap.addStorefront("swap", "test2", testClient1);

        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront(new SellProduct(), testStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront(new SwapProduct(), testStorefront2));

        AbstractProduct testProduct1 = testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);
        assertEquals(testProduct1, testShopOrSwap.findInStorefront("test1", testStorefront1));
        assertEquals(testProduct1, testShopOrSwap.findInStorefront(testProduct1, testStorefront1));
        AbstractProduct testProduct2 = testShopOrSwap.addToStorefront("test2", "description2", 50, testStorefront1);
        assertEquals(testProduct1, testShopOrSwap.findInStorefront("test1", testStorefront1));
        assertEquals(testProduct1, testShopOrSwap.findInStorefront(testProduct1, testStorefront1));
        assertEquals(testProduct2, testShopOrSwap.findInStorefront("test2", testStorefront1));
        assertEquals(testProduct2, testShopOrSwap.findInStorefront(testProduct2, testStorefront1));

        AbstractProduct testProduct3 = testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront2);
        assertEquals(testProduct3, testShopOrSwap.findInStorefront("test1", testStorefront1));
        assertEquals(testProduct3, testShopOrSwap.findInStorefront(testProduct3, testStorefront2));
        AbstractProduct testProduct4 = testShopOrSwap.addToStorefront("test2", "description2", 50, testStorefront2);
        assertEquals(testProduct3, testShopOrSwap.findInStorefront("test1", testStorefront2));
        assertEquals(testProduct3, testShopOrSwap.findInStorefront(testProduct3, testStorefront2));
        assertEquals(testProduct4, testShopOrSwap.findInStorefront("test2", testStorefront2));
        assertEquals(testProduct4, testShopOrSwap.findInStorefront(testProduct4, testStorefront2));

    }

    /**
     * Automated tests for ShopOrSwap.removeFromStorefront method
     */
    @Test
    void removeFromStorefrontTests(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeFromStorefront(new SellProduct(), new SellStorefront()));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeFromStorefront(new SwapProduct(), new SwapStorefront()));

        Storefront testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        Storefront testStorefront2 = testShopOrSwap.addStorefront("swap", "test2", testClient1);

        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeFromStorefront(new SellProduct(), testStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeFromStorefront(new SwapProduct(), testStorefront2));

        AbstractProduct testProduct1 = testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);
        assertEquals(testProduct1, testShopOrSwap.removeFromStorefront("test1", testStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeFromStorefront("test2", testStorefront1));

        testProduct1 = testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);
        assertEquals(testProduct1, testShopOrSwap.removeFromStorefront(testProduct1, testStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeFromStorefront("test2", testStorefront1));

        testProduct1 = testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);
        AbstractProduct testProduct2 = testShopOrSwap.addToStorefront("test2", "description2", 50, testStorefront1);
        assertEquals(testProduct1, testShopOrSwap.removeFromStorefront("test1", testStorefront1));
        assertEquals(testProduct2, testShopOrSwap.removeFromStorefront("test2", testStorefront1));

        testProduct1 = testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);
        testProduct2 = testShopOrSwap.addToStorefront("test2", "description2", 50, testStorefront1);
        assertEquals(testProduct1, testShopOrSwap.removeFromStorefront(testProduct1, testStorefront1));
        assertEquals(testProduct2, testShopOrSwap.removeFromStorefront(testProduct2, testStorefront1));

    }

    /**
     * Automated tests for ShopOrSwap.buyProduct method
     */
    @Test
    void buyProductTest(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        Client testClient2 = (Client) testShopOrSwap.addAccount("client", "test2", "pass2");

        Storefront testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);

        SellProduct testProduct1 = (SellProduct) testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);

        testShopOrSwap.buyProduct(testProduct1, testClient2);
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront("test1", testStorefront1));
        assertEquals(1, ((Client) testShopOrSwap.findAccount("test1")).getMyOwnedProductList().size());
        assertTrue(((Client) testShopOrSwap.findAccount("test1")).getMyOwnedProductList().contains(testProduct1));
    }

    /**
     * Automated tests for ShopOrSwap.swapProducts method
     */
    @Test
    void swapProductsTest(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        Client testClient2 = (Client) testShopOrSwap.addAccount("client", "test2", "pass2");

        SwapStorefront testStorefront1 = (SwapStorefront) testShopOrSwap.addStorefront("swap", "test1", testClient1);
        SwapStorefront testStorefront2 = (SwapStorefront) testShopOrSwap.addStorefront("swap", "test1", testClient2);

        SwapProduct testProduct1 = (SwapProduct) testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);
        SwapProduct testProduct2 = (SwapProduct) testShopOrSwap.addToStorefront("test2", "description1", 50, testStorefront2);

        testShopOrSwap.swapProducts(testProduct1, testProduct2);
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront("test1", testStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront("test2", testStorefront2));
        assertEquals(1, ((Client) testShopOrSwap.findAccount("test1")).getMyOwnedProductList().size());
        assertEquals(1, ((Client) testShopOrSwap.findAccount("test2")).getMyOwnedProductList().size());
        assertTrue(((Client) testShopOrSwap.findAccount("test1")).getMyOwnedProductList().contains(testProduct2));
        assertTrue(((Client) testShopOrSwap.findAccount("test2")).getMyOwnedProductList().contains(testProduct1));
    }

    /**
     * Automated tests for ShopOrSwap.sendMessage method
     */
    @Test
    void sendMessageTest(){
        assertTrue(false);
    }
}
