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
     * Automated tests for ShopOrSwap.addProduct method
     */
    @Test
    void addProductTest(){
        assertTrue(false);
    }

    /**
     * Automated tests for ShopOrSwap.findProduct method
     */
    @Test
    void findProductTest(){
        assertTrue(false);
    }

    /**
     * Automated tests for ShopOrSwap.removeProduct method
     */
    @Test
    void removeProductTest(){
        assertTrue(false);
    }

    /**
     * Automated tests for ShopOrSwap.addStorefront method
     */
    @Test
    void addStorefrontTest(){
        assertTrue(false);
    }

    /**
     * Automated tests for ShopOrSwap.findStorefront method
     */
    @Test
    void findStorefrontTest(){
        assertTrue(false);
    }

    /**
     * Automated tests for ShopOrSwap.removeStorefront method
     */
    @Test
    void removeStorefrontTest(){
        assertTrue(false);
    }

    /**
     * Automated tests for ShopOrSwap.buyProduct method
     */
    @Test
    void buyProductTest(){
        assertTrue(false);
    }

    /**
     * Automated tests for ShopOrSwap.swapProducts method
     */
    @Test
    void swapProductsTest(){
        assertTrue(false);
    }

    /**
     * Automated tests for ShopOrSwap.sendMessage method
     */
    @Test
    void sendMessageTest(){
        assertTrue(false);
    }
}
