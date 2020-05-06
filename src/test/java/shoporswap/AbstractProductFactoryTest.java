package shoporswap;

import org.junit.jupiter.api.Test;
import shoporswap.AbstractProduct;
import shoporswap.AbstractProductFactory;
import shoporswap.AccountFactory;
import shoporswap.Client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractProductFactoryTest {

    /**
     * Automated tests for shoporswap.AbstractProductFactory.getProduct method
     */
    @Test
    void getProductTest(){

        AccountFactory accountFactory = new AccountFactory();

        Client testClient1 = (Client) accountFactory.getAccount("client");
        testClient1.setAccountName("test1");
        testClient1.setAccountPassword("pass1");
        Client testClient2 = (Client) accountFactory.getAccount("client");
        testClient2.setAccountName("test2");
        testClient2.setAccountPassword("pass2");

        AbstractProductFactory testAbstractProductFactory = new AbstractProductFactory();

        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct(""));

        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct(""));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("ssell"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("selll"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("sselll"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct(" sell"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("sell "));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("@#$%^&*!"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("S ell"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("Sel l"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("S el l"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("sel"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("ell"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("el"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct(""));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("sswap"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("swapp"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("sswapp"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct(" swap"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("swap "));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("@#$%^&*!"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("S wap"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("Swa p"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("S wa p"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("swa"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("wap"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractProductFactory.getProduct("wa"));

        AbstractProduct testSellProduct1, testSellProduct2, testSellProduct3;
        testSellProduct1 = testAbstractProductFactory.getProduct("sell");
        assertEquals("DEFAULT NAME", testSellProduct1.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testSellProduct1.getProductDescription());
        assertEquals(0.0, testSellProduct1.getProductValue());
        assertNull(testSellProduct1.getProductMerchant());
        assertEquals(0, testSellProduct1.getProductTags().size());
        
        AbstractProduct testSwapProduct1;
        testSwapProduct1 = testAbstractProductFactory.getProduct("swap");
        assertEquals("DEFAULT NAME", testSwapProduct1.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testSwapProduct1.getProductDescription());
        assertEquals(0.0, testSwapProduct1.getProductValue());
        assertNull(testSwapProduct1.getProductMerchant());
        assertEquals(0, testSwapProduct1.getProductTags().size());
    }
    
}
