import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductFactoryTest {

    /**
     * Automated tests for ProductFactory.getProduct method
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

        ProductFactory testProductFactory = new ProductFactory();

        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct(""));

        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct(""));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("ssell"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("selll"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("sselll"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct(" sell"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("sell "));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("@#$%^&*!"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("S ell"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("Sel l"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("S el l"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("sel"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("ell"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("el"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct(""));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("sswap"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("swapp"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("sswapp"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct(" swap"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("swap "));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("@#$%^&*!"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("S wap"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("Swa p"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("S wa p"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("swa"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("wap"));
        assertThrows(IllegalArgumentException.class, ()-> testProductFactory.getProduct("wa"));

        AbstractProduct testSellProduct1, testSellProduct2, testSellProduct3;
        testSellProduct1 = testProductFactory.getProduct("sell");
        assertEquals("DEFAULT NAME", testSellProduct1.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testSellProduct1.getProductDescription());
        assertEquals(0.0, testSellProduct1.getProductValue());
        assertNull(testSellProduct1.getProductMerchant());
        assertEquals(0, testSellProduct1.getProductTags().size());
        
        AbstractProduct testSwapProduct1;
        testSwapProduct1 = testProductFactory.getProduct("swap");
        assertEquals("DEFAULT NAME", testSwapProduct1.getProductName());
        assertEquals("DEFAULT DESCRIPTION", testSwapProduct1.getProductDescription());
        assertEquals(0.0, testSwapProduct1.getProductValue());
        assertNull(testSwapProduct1.getProductMerchant());
        assertEquals(0, testSwapProduct1.getProductTags().size());
    }
    
}
