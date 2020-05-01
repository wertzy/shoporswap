import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    /**
     * Automated tests for Client constructor methods:
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
        assertEquals(0, testClient1.getMyProductList().size());

        testClient2 = new Client("test1", "pass1");
        assertEquals("test1", testClient2.getAccountName());
        assertEquals("pass1", testClient2.getAccountPassword());
        assertFalse(testClient2.getIsFrozen());
        assertEquals(0, testClient2.getMyProductList().size());

        Account testClient3, testClient4;

        testClient3 = new Client();
        assertEquals("DefaultClient", testClient3.getAccountName());
        assertEquals("DefaultPassword", testClient3.getAccountPassword());
        assertFalse(testClient3.getIsFrozen());
        assertEquals(0, ((Client) testClient3).getMyProductList().size());

        testClient4 = new Client("test1", "pass1");
        assertEquals("test1", testClient4.getAccountName());
        assertEquals("pass1", testClient4.getAccountPassword());
        assertFalse(testClient4.getIsFrozen());
        assertEquals(0, ((Client) testClient4).getMyProductList().size());

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
     * Automated tests for Client mutator methods:
     * - Client.getMyProductList
     * - Client.setMyProductList
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
        testClient1.setMyProductList(testProductList1);
        assertEquals(testProductList1, testClient1.getMyProductList());

        List<AbstractProduct> testProductList2 = Arrays.asList(
                new SellProduct("product1", "description1", 50, testClient2),
                new SwapProduct("product2", "description2", 50, testClient1)
        );
        testClient2.setMyProductList(testProductList2);
        assertEquals(testProductList2, testClient2.getMyProductList());

    }

    /**
     * Automated tests for Client.addSellProduct method
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
        assertEquals(1, testClient1.getMyProductList().size());
        assertEquals(testSellProductList1.get(0), testClient1.getMyProductList().get(0));
        assertEquals(testClient1, testClient1.getMyProductList().get(0).getProductMerchant());

        testClient1.addSellProduct(testSellProductList1.get(1));
        assertEquals(2, testClient1.getMyProductList().size());
        assertEquals(testSellProductList1.get(1), testClient1.getMyProductList().get(1));
        assertEquals(testClient2, testClient1.getMyProductList().get(1).getProductMerchant());
    }

    /**
     * Automated tests for Client.addSwapProduct method
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
        assertEquals(1, testClient1.getMyProductList().size());
        assertEquals(testSwapProductList1.get(0), testClient1.getMyProductList().get(0));
        assertEquals(testClient1, testClient1.getMyProductList().get(0).getProductMerchant());

        testClient1.addSwapProduct(testSwapProductList1.get(1));
        assertEquals(2, testClient1.getMyProductList().size());
        assertEquals(testSwapProductList1.get(1), testClient1.getMyProductList().get(1));
        assertEquals(testClient2, testClient1.getMyProductList().get(1).getProductMerchant());
    }

    /**
     * Automated tests for Client.findSellProduct method
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
     * Automated tests for Client.findSwapProduct method
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
     * Automated tests for Client.removeSellProduct method
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
     * Automated tests for Client.removeSwapProduct method
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

}
