package shoporswap;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import util.JsonUtil;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShopOrSwapTest {

    /**
     * Automated tests for shoporswap.ShopOrSwap constructor methods
     */
    @Test
    void constructorsTest(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        assertNotNull(testShopOrSwap.accessAccountFactory());
        assertNotNull(testShopOrSwap.accessProductFactory());
        assertNotNull(testShopOrSwap.accessMessageFactory());
        assertNotNull(testShopOrSwap.accessStorefrontFactory());
        assertNotNull(testShopOrSwap.getAccountCollection());
        assertEquals(0, testShopOrSwap.getAccountCollection().size());
        assertEquals(0, testShopOrSwap.getSystemTags().size());
    }

    /**
     * Automated tests for shoporswap.ShopOrSwap mutator methods
     */
    @Test
    void mutatorsTests(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        assertNotNull(testShopOrSwap.accessAccountFactory());
        assertNotNull(testShopOrSwap.accessProductFactory());
        assertNotNull(testShopOrSwap.accessMessageFactory());
        assertNotNull(testShopOrSwap.accessStorefrontFactory());
        assertNotNull(testShopOrSwap.getAccountCollection());

        AccountFactory testAccountFactory = new AccountFactory();
        testShopOrSwap.establishAccountFactory(testAccountFactory);
        assertEquals(testAccountFactory, testShopOrSwap.accessAccountFactory());

        AbstractProductFactory testAbstractProductFactory = new AbstractProductFactory();
        testShopOrSwap.establishProductFactory(testAbstractProductFactory);
        assertEquals(testAbstractProductFactory, testShopOrSwap.accessProductFactory());

        AbstractMessageFactory testAbstractMessageFactory = new AbstractMessageFactory();
        testShopOrSwap.establishMessageFactory(testAbstractMessageFactory);
        assertEquals(testAbstractMessageFactory, testShopOrSwap.accessMessageFactory());

        StorefrontFactory testStorefrontFactory = new StorefrontFactory();
        testShopOrSwap.establishStorefrontFactory(testStorefrontFactory);
        assertEquals(testStorefrontFactory, testShopOrSwap.accessStorefrontFactory());

        Map<String, Account> testAccountCollection = new HashMap<String, Account>();
        testAccountCollection.put("test1", new Client("test1", "pass1"));
        testShopOrSwap.establishAccountCollection(testAccountCollection);
        assertEquals(1, testShopOrSwap.getAccountCollection().size());

        testShopOrSwap.setSystemMessages(Arrays.asList(new UserMessage(), new ReportMessage()));
        assertEquals(2, testShopOrSwap.getSystemMessages().size());

    }

    /**
     * Automated tests for shoporswap.ShopOrSwap.addAccount method
     */
    @Test
    void addAccountTest(){

        ShopOrSwap testShopOrSwap = new ShopOrSwap();

        Client testClient1, testClient2, testClient3;
        testClient1 = new Client("test1", "pass1");
        testClient2 = new Client("test1", "pass1");
        testClient3 = new Client("test2", "pass2");

        testShopOrSwap.addAccount(testClient1);
        assertEquals(1, testShopOrSwap.getAccountCollection().size());
        assertTrue(testShopOrSwap.getAccountCollection().containsKey(testClient1.getAccountName()));

        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addAccount(testClient2));

        testShopOrSwap.addAccount(testClient3);
        assertEquals(2, testShopOrSwap.getAccountCollection().size());
        assertTrue(testShopOrSwap.getAccountCollection().containsKey(testClient1.getAccountName()));
        assertTrue(testShopOrSwap.getAccountCollection().containsKey(testClient3.getAccountName()));

    }

    /**
     * Automated tests for shoporswap.ShopOrSwap.findAccount method
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
        assertEquals(testClient2, testShopOrSwap.findAccount(testClient2));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findAccount(testClient3));

        testShopOrSwap.addAccount(testClient3);
        assertEquals(testClient1, testShopOrSwap.findAccount(testClient1));
        assertEquals(testClient2, testShopOrSwap.findAccount(testClient2));
        assertEquals(testClient3, testShopOrSwap.findAccount(testClient3));

    }

    /**
     * Automated tests for shoporswap.ShopOrSwap.removeAccount method
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
     * Automated tests for shoporswap.ShopOrSwap.addStorefront method
     */
    @Test
    void addStorefrontTest(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();

        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.addStorefront("sell", "test1", testClient1));

        testShopOrSwap.addAccount(testClient1);
        Storefront testSellStorefront1 = testShopOrSwap.addStorefront("sell","test1", testClient1);
        assertEquals("test1", testSellStorefront1.getStorefrontName());
        assertEquals(1, testClient1.getMyStorefronts().size());
        assertEquals(testSellStorefront1, testClient1.getMyStorefronts().get("test1"));
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addStorefront("sell", "test1", testClient1));
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addStorefront("swap", "test1", testClient1));
        Storefront testSellStorefront2 = testShopOrSwap.addStorefront("swap","test2", testClient1);
        assertEquals("test2", testSellStorefront2.getStorefrontName());
        assertEquals(2, testClient1.getMyStorefronts().size());
        assertEquals(testSellStorefront2, testClient1.getMyStorefronts().get("test2"));
    }

    /**
     * Automated tests for shoporswap.ShopOrSwap.findStorefront method
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
     * Automated tests for shoporswap.ShopOrSwap.removeStorefront method
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
        assertEquals(testStorefront2, testShopOrSwap.removeStorefront("test2", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test3", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test4", testClient1));

        testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        testStorefront2 = testShopOrSwap.addStorefront("sell", "test2", testClient1);
        Storefront testStorefront3 = testShopOrSwap.addStorefront("swap", "test3", testClient1);
        assertEquals(testStorefront1, testShopOrSwap.removeStorefront("test1", testClient1));
        assertEquals(testStorefront2, testShopOrSwap.removeStorefront("test2", testClient1));
        assertEquals(testStorefront3, testShopOrSwap.removeStorefront("test3", testClient1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.removeStorefront("test4", testClient1));

        testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);
        testStorefront2 = testShopOrSwap.addStorefront("sell", "test2", testClient1);
        testStorefront3 = testShopOrSwap.addStorefront("swap", "test3", testClient1);
        Storefront testStorefront4 = testShopOrSwap.addStorefront("swap", "test4", testClient1);
        assertEquals(testStorefront1, testShopOrSwap.removeStorefront("test1", testClient1));
        assertEquals(testStorefront2, testShopOrSwap.removeStorefront("test2", testClient1));
        assertEquals(testStorefront3, testShopOrSwap.removeStorefront("test3", testClient1));
        assertEquals(testStorefront4, testShopOrSwap.removeStorefront("test4", testClient1));

    }

    /**
     * Automated tests for shoporswap.ShopOrSwap.addToStorefront methods
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
     * Automated tests for shoporswap.ShopOrSwap.findInStorefront method
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
        assertEquals(testProduct3, testShopOrSwap.findInStorefront("test1", testStorefront2));
        assertEquals(testProduct3, testShopOrSwap.findInStorefront(testProduct3, testStorefront2));
        AbstractProduct testProduct4 = testShopOrSwap.addToStorefront("test2", "description2", 50, testStorefront2);
        assertEquals(testProduct3, testShopOrSwap.findInStorefront("test1", testStorefront2));
        assertEquals(testProduct3, testShopOrSwap.findInStorefront(testProduct3, testStorefront2));
        assertEquals(testProduct4, testShopOrSwap.findInStorefront("test2", testStorefront2));
        assertEquals(testProduct4, testShopOrSwap.findInStorefront(testProduct4, testStorefront2));

    }

    /**
     * Automated tests for shoporswap.ShopOrSwap.removeFromStorefront method
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
     * Automated tests for shoporswap.ShopOrSwap.buyProduct method
     */
    @Test
    void buyProductTest(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        Client testClient2 = (Client) testShopOrSwap.addAccount("client", "test2", "pass2");

        Storefront testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);

        SellProduct testProduct1 = (SellProduct) testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);

        testShopOrSwap.buyProduct(testStorefront1, testProduct1, testClient2);
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront("test1", testStorefront1));
        assertEquals(1, ((Client) testShopOrSwap.findAccount("test2")).getMyOwnedProductList().size());
        assertTrue(((Client) testShopOrSwap.findAccount("test2")).getMyOwnedProductList().contains(testProduct1));
    }

    /**
     * Automated tests for shoporswap.ShopOrSwap.swapProducts method
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

        testShopOrSwap.swapProducts(testStorefront1, testProduct1, testStorefront2, testProduct2);
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront("test1", testStorefront1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.findInStorefront("test2", testStorefront2));
        assertEquals(1, ((Client) testShopOrSwap.findAccount("test1")).getMyOwnedProductList().size());
        assertEquals(1, ((Client) testShopOrSwap.findAccount("test2")).getMyOwnedProductList().size());
        assertTrue(((Client) testShopOrSwap.findAccount("test1")).getMyOwnedProductList().contains(testProduct2));
        assertTrue(((Client) testShopOrSwap.findAccount("test2")).getMyOwnedProductList().contains(testProduct1));
    }

    /**
     * Automated tests for shoporswap.ShopOrSwap.sendMessage method
     */
    @Test
    void sendMessageTest(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(new Client("testClient1", "pass1"));
        testShopOrSwap.addAccount(new Client("testClient2", "pass2"));
        testShopOrSwap.addAccount(new Admin("testAdmin1", "pass3"));
        testShopOrSwap.sendMessage("User", "testClient1", "testClient2", "subject1", "message1");
        assertEquals(1, testShopOrSwap.getSystemMessages().size());
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.sendMessage("Report", "testClient1", "", "subject1", "message1"));
        testShopOrSwap.sendMessage("Report", "testClient1","testClient2","subject2", "message2");
        assertEquals(2, testShopOrSwap.getSystemMessages().size());
    }

    /**
     * Automated tests for shoporswap.ShopOrSwap.findMessagesByRecipient and shoporswap.ShopOrSwap.findMessagesBySender methods
     */
    @Test
    void findMessagesTests(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(new Client("testClient1", "pass1"));
        testShopOrSwap.addAccount(new Client("testClient2", "pass2"));
        testShopOrSwap.addAccount(new Admin("testAdmin1", "pass3"));

        List<AbstractMessage> testMessageList1 = testShopOrSwap.findMessagesByRecipient(testShopOrSwap.findAccount("testAdmin1"));
        List<AbstractMessage> testMessageList2 = testShopOrSwap.findMessagesByRecipient(testShopOrSwap.findAccount("testClient2"));
        List<AbstractMessage> testMessageList3 = testShopOrSwap.findMessagesByRecipient(testShopOrSwap.findAccount("testClient1"));
        List<AbstractMessage> testMessageList4 = testShopOrSwap.findMessagesBySender(testShopOrSwap.findAccount("testAdmin1"));
        List<AbstractMessage> testMessageList5 = testShopOrSwap.findMessagesBySender(testShopOrSwap.findAccount("testClient2"));
        List<AbstractMessage> testMessageList6 = testShopOrSwap.findMessagesBySender(testShopOrSwap.findAccount("testClient1"));
        assertEquals(0, testMessageList1.size());
        assertEquals(0, testMessageList2.size());
        assertEquals(0, testMessageList3.size());
        assertEquals(0, testMessageList4.size());
        assertEquals(0, testMessageList5.size());
        assertEquals(0, testMessageList6.size());

        testShopOrSwap.sendMessage("User", "testClient1", "testClient2", "subject1", "message1");
        assertEquals(1, testShopOrSwap.getSystemMessages().size());
        testMessageList1 = testShopOrSwap.findMessagesByRecipient(testShopOrSwap.findAccount("testAdmin1"));
        testMessageList2 = testShopOrSwap.findMessagesByRecipient(testShopOrSwap.findAccount("testClient2"));
        testMessageList3 = testShopOrSwap.findMessagesByRecipient(testShopOrSwap.findAccount("testClient1"));
        testMessageList4 = testShopOrSwap.findMessagesBySender(testShopOrSwap.findAccount("testAdmin1"));
        testMessageList5 = testShopOrSwap.findMessagesBySender(testShopOrSwap.findAccount("testClient2"));
        testMessageList6 = testShopOrSwap.findMessagesBySender(testShopOrSwap.findAccount("testClient1"));
        assertEquals(0, testMessageList1.size());
        assertEquals(1, testMessageList2.size());
        assertEquals(0, testMessageList3.size());
        assertEquals(0, testMessageList4.size());
        assertEquals(0, testMessageList5.size());
        assertEquals(1, testMessageList6.size());

        testShopOrSwap.sendMessage("Report", "testClient1","testClient2","subject2", "message2");
        assertEquals(2, testShopOrSwap.getSystemMessages().size());
        testMessageList1 = testShopOrSwap.findMessagesByRecipient(testShopOrSwap.findAccount("testAdmin1"));
        testMessageList2 = testShopOrSwap.findMessagesByRecipient(testShopOrSwap.findAccount("testClient2"));
        testMessageList3 = testShopOrSwap.findMessagesByRecipient(testShopOrSwap.findAccount("testClient1"));
        testMessageList4 = testShopOrSwap.findMessagesBySender(testShopOrSwap.findAccount("testAdmin1"));
        testMessageList5 = testShopOrSwap.findMessagesBySender(testShopOrSwap.findAccount("testClient2"));
        testMessageList6 = testShopOrSwap.findMessagesBySender(testShopOrSwap.findAccount("testClient1"));
        assertEquals(1, testMessageList1.size());
        assertEquals(1, testMessageList2.size());
        assertEquals(0, testMessageList3.size());
        assertEquals(0, testMessageList4.size());
        assertEquals(0, testMessageList5.size());
        assertEquals(2, testMessageList6.size());

    }

    @Test
    void freezeAccountTest(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        Client testAccount1 = (Client) testShopOrSwap.addAccount(new Client("testClient1", "pass1"));
        Admin testAccount2 = (Admin) testShopOrSwap.addAccount(new Admin("testAdmin1", "pass3"));
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.freezeAccount(testAccount1, testAccount2));
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.freezeAccount(testAccount2, testAccount2));
        testShopOrSwap.freezeAccount(testAccount2, testAccount1);
        assertTrue(testAccount1.getIsFrozen());
    }

    @Test
    void unfreezeAccountTest(){
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        Client testAccount1 = (Client) testShopOrSwap.addAccount(new Client("testClient1", "pass1"));
        Admin testAccount2 = (Admin) testShopOrSwap.addAccount(new Admin("testAdmin1", "pass3"));
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.unfreezeAccount(testAccount1, testAccount2));
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.unfreezeAccount(testAccount2, testAccount2));
        testShopOrSwap.freezeAccount(testAccount2, testAccount1);
        assertTrue(testAccount1.getIsFrozen());
        testShopOrSwap.unfreezeAccount(testAccount2, testAccount1);
        assertFalse(testAccount1.getIsFrozen());
    }

    /**
     * Automated test for ShopOrSwap.exportAccounts method
     */
    @Test
    void exportAccountsTest() throws JsonProcessingException {
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        assertEquals(0, testShopOrSwap.exportAccounts().size());

        Account testAccount1 = testShopOrSwap.addAccount(new Client("testClient1", "pass1"));
        assertEquals(1, testShopOrSwap.exportAccounts().size());

        Account testAccount2 = testShopOrSwap.addAccount(new Admin("testAdmin1", "pass3"));
        assertEquals(2, testShopOrSwap.exportAccounts().size());
    }

    @Test
    void addTagToProductTest(){
        Client testClient1 = new Client("test1", "pass1");

        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        testShopOrSwap.addAccount(testClient1);

        Storefront testStorefront1 = testShopOrSwap.addStorefront("sell", "test1", testClient1);

        AbstractProduct testProduct1;

        testProduct1 = testShopOrSwap.addToStorefront("test1", "description1", 50, testStorefront1);
        testShopOrSwap.addTagToProduct("Tag1", testProduct1);
        assertEquals(1, testProduct1.getProductTags().size());
        assertEquals(1, testShopOrSwap.getSystemTags().size());
        assertEquals("Tag1", testShopOrSwap.getSystemTags().get("Tag1").getName());
        assertEquals(1, testShopOrSwap.getSystemTags().get("Tag1").accessProducts().size());
    }


}
