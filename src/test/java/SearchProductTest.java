import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

    public class SearchProductTest {
        @Test
        void searchTest() {
            ShopOrSwap testShopOrSwap = new ShopOrSwap();
            User testUser1 = new User("testname1", "testpassword1");
            User testUser2 = new User("testname2", "testpassword2");
            testShopOrSwap.addAccount(testUser1);
            testShopOrSwap.addAccount(testUser2);
            testShopOrSwap.createSellProduct("tshirt", "tshirt", "12.00", testUser1);
            testShopOrSwap.createSellProduct("pants", "long pants", "23.21", testUser2);
            List<Product> searchResults = testShopOrSwap.searchForProduct("shirt"); //Search returns one search result

            assertEquals(1, searchResults.size());
            assertEquals("tshirt", searchResults.get(0).getName());

            testShopOrSwap.createSellProduct("Pink shirt", "shirt that is pink", "19.00", testUser2);
            List<Product> searchResults2 = testShopOrSwap.searchForProduct("shirt"); //Search returns two search results from two different users

            assertEquals(2, searchResults2.size());
            assertEquals("tshirt", searchResults2.get(0).getName());
            assertEquals("Pink shirt", searchResults2.get(1).getName());

            assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.searchForProduct("Pink Pants"));//No search results
            assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.searchForProduct(""));//No search results


        }
        @Test
        void tagTest(){
            ShopOrSwap testShopOrSwap = new ShopOrSwap();
            User testUser1 = new User("testname1", "testpassword1");
            User testUser2 = new User("testname2", "testpassword2");
            testShopOrSwap.addAccount(testUser1);
            testShopOrSwap.addAccount(testUser2);
            testShopOrSwap.createSellProduct("tshirt", "tshirt", "12.00", testUser1);

            Tag testTag = new Tag("shirt");
            testTag.addProduct(testShopOrSwap.findProduct("tshirt",testUser1));
            List<Product> productListTest = testTag.getProductList();
            assertEquals(1, productListTest);
            assertEquals("tshirt", productListTest.get(0).getName());



        }
}
