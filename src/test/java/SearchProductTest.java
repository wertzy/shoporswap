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

            System.out.println(searchResults.size());
            assertEquals(1, searchResults.size());
            assertEquals("tshirt", searchResults.get(0).getName());

            testShopOrSwap.createSellProduct("Pink shirt", "shirt that is pink", "19.00", testUser2);
            List<Product> searchResults2 = testShopOrSwap.searchForProduct("shirt"); //Search returns two search results from two different users
            System.out.println(searchResults2.size());
            assertEquals(2, searchResults2.size());
            assertEquals("tshirt", searchResults2.get(0).getName());
            assertEquals("Pink shirt", searchResults2.get(1).getName());

            assertThrows(NoSuchElementException.class, ()-> testShopOrSwap.searchForProduct("Pink Pants"));//No search results


        }
}
