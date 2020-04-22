import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

    public class SearchTest {
        @Test
        void searchProductTest() {
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
            testShopOrSwap.createSellProduct("Pink shirt", "shirt that is pink", "19.00", testUser2);

            Tag testTag = new Tag("shirt");
            testTag.addProduct(testShopOrSwap.findProduct("tshirt",testUser1));
            List<Product> productListTest = testTag.getProductList();
            assertEquals(1, productListTest.size());
            assertEquals("tshirt", productListTest.get(0).getName());

            testTag.addProduct(testShopOrSwap.findProduct("Pink shirt",testUser2));
            List<Product> productListTest2 = testTag.getProductList();
            assertEquals(2, productListTest2.size());
            assertEquals("Pink shirt", productListTest.get(1).getName());

        }
        @Test
        void productTagsToTagTest(){
            ShopOrSwap testShopOrSwap = new ShopOrSwap();
            User testUser1 = new User("testname1", "testpassword1");
            User testUser2 = new User("testname2", "testpassword2");
            testShopOrSwap.addAccount(testUser1);
            testShopOrSwap.addAccount(testUser2);
            testShopOrSwap.createSellProduct("tshirt", "tshirt", "12.00", testUser1);
            testShopOrSwap.createSellProduct("Pink shirt", "shirt that is pink", "19.00", testUser2);

            Product testProduct=testShopOrSwap.findProduct("tshirt",testUser1);
            testProduct.textToTag("#Funny #Supreme #shirt");
            Product testProduct2=testShopOrSwap.findProduct("tshirt",testUser1);
            System.out.println(testProduct2.getTags());
            testShopOrSwap.productTagsToTag(testProduct2);
            testShopOrSwap.productTagsToTag(testProduct2); //tests that running the method on the same product twice works

            assertEquals("sell", testShopOrSwap.getTagList().get(1).getName());
            assertEquals("Funny", testShopOrSwap.getTagList().get(2).getName());
            assertEquals("Supreme", testShopOrSwap.getTagList().get(3).getName());
            assertEquals("shirt", testShopOrSwap.getTagList().get(4).getName());
            assertEquals(testShopOrSwap.findProduct("tshirt",testUser1), testShopOrSwap.getTagList().get(2).getProductList().get(0));


            Product testProduct3=testShopOrSwap.findProduct("Pink shirt",testUser2);
            testProduct3.textToTag("#Hilarious #supreme #shirt"); //tests new tag, tag with different capitalization , old tag
            testShopOrSwap.productTagsToTag(testProduct3);
            System.out.println(testShopOrSwap.getTagList());
            assertEquals("sell", testShopOrSwap.getTagList().get(1).getName());
            assertEquals("Funny", testShopOrSwap.getTagList().get(2).getName());
            assertEquals("Supreme", testShopOrSwap.getTagList().get(3).getName());
            assertEquals("shirt", testShopOrSwap.getTagList().get(4).getName());
            assertEquals("Hilarious", testShopOrSwap.getTagList().get(5).getName());
            assertEquals(2,testShopOrSwap.getTagList().get(3).getProductList().size());
            assertEquals(2,testShopOrSwap.getTagList().get(4).getProductList().size());
            assertEquals(testShopOrSwap.findProduct("tshirt",testUser1), testShopOrSwap.getTagList().get(4).getProductList().get(0));
            assertEquals(testShopOrSwap.findProduct("Pink shirt",testUser2), testShopOrSwap.getTagList().get(4).getProductList().get(1));




        }
        @Test
        void searchTagTest(){

        }
}
