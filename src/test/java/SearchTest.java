import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

    public class SearchTest {
        @Test
        void searchTest(){
            OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap();
            User testUser1 = new User("testname1", "testpassword1");
            User testUser2 = new User("testname2", "testpassword2");
            testOldShopOrSwap.addAccount(testUser1);
            testOldShopOrSwap.addAccount(testUser2);
            testOldShopOrSwap.createSellProduct("tshirt", "tshirt", "12.00", testUser1);
            testOldShopOrSwap.createSellProduct("Pink shirt", "shirt that is pink", "23.21", testUser2);

            Product testProduct= testOldShopOrSwap.findProduct("tshirt",testUser1);
            testProduct.textToTag("#Funny #Supreme #shirt");
            testOldShopOrSwap.productTagsToTag(testProduct);
            Product testProduct2= testOldShopOrSwap.findProduct("Pink shirt",testUser2);
            testProduct2.textToTag("#Hilarious #supreme #shirt");
            testOldShopOrSwap.productTagsToTag(testProduct2);

            List<Product> searchResults = testOldShopOrSwap.search("#shirt"); //Search returns one search result

            assertEquals(2, searchResults.size());
            assertEquals("tshirt", searchResults.get(0).getName());
            assertEquals("Pink shirt", searchResults.get(1).getName());

            List<Product> searchResults2 = testOldShopOrSwap.search("#hilarious"); //Search returns two search results from two different users

            assertEquals(1, searchResults2.size());
            assertEquals("Pink shirt", searchResults2.get(0).getName());

            assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap.search("#AWESOMENESS"));//No search results
            assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.search(""));//No search results

            List<Product> searchResults4 = testOldShopOrSwap.search("shirt"); //Search returns two search results from two different users

            assertEquals(2, searchResults4.size());
            assertEquals("tshirt", searchResults4.get(0).getName());
            assertEquals("Pink shirt", searchResults4.get(1).getName());

            assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap.search("Pink Pants"));//No search results
            assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.search(""));//No search results



        }
        @Test
        void searchProductTest() {
            OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap();
            User testUser1 = new User("testname1", "testpassword1");
            User testUser2 = new User("testname2", "testpassword2");
            testOldShopOrSwap.addAccount(testUser1);
            testOldShopOrSwap.addAccount(testUser2);
            testOldShopOrSwap.createSellProduct("tshirt", "tshirt", "12.00", testUser1);
            testOldShopOrSwap.createSellProduct("pants", "long pants", "23.21", testUser2);
            List<Product> searchResults = testOldShopOrSwap.searchForProduct("shirt"); //Search returns one search result

            assertEquals(1, searchResults.size());
            assertEquals("tshirt", searchResults.get(0).getName());

            testOldShopOrSwap.createSellProduct("Pink shirt", "shirt that is pink", "19.00", testUser2);
            List<Product> searchResults2 = testOldShopOrSwap.searchForProduct("shirt"); //Search returns two search results from two different users

            assertEquals(2, searchResults2.size());
            assertEquals("tshirt", searchResults2.get(0).getName());
            assertEquals("Pink shirt", searchResults2.get(1).getName());

            assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap.searchForProduct("Pink Pants"));//No search results
            assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.searchForProduct(""));//No search results


        }
        @Test
        void tagTest(){
            OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap();
            User testUser1 = new User("testname1", "testpassword1");
            User testUser2 = new User("testname2", "testpassword2");
            testOldShopOrSwap.addAccount(testUser1);
            testOldShopOrSwap.addAccount(testUser2);
            testOldShopOrSwap.createSellProduct("tshirt", "tshirt", "12.00", testUser1);
            testOldShopOrSwap.createSellProduct("Pink shirt", "shirt that is pink", "19.00", testUser2);

            Tag testTag = new Tag("shirt");
            testTag.addProduct(testOldShopOrSwap.findProduct("tshirt",testUser1));
            List<Product> productListTest = testTag.getProducts();
            assertEquals(1, productListTest.size());
            assertEquals("tshirt", productListTest.get(0).getName());

            testTag.addProduct(testOldShopOrSwap.findProduct("Pink shirt",testUser2));
            List<Product> productListTest2 = testTag.getProducts();
            assertEquals(2, productListTest2.size());
            assertEquals("Pink shirt", productListTest.get(1).getName());

        }
        @Test
        void productTagsToTagTest(){
            OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap();
            User testUser1 = new User("testname1", "testpassword1");
            User testUser2 = new User("testname2", "testpassword2");
            testOldShopOrSwap.addAccount(testUser1);
            testOldShopOrSwap.addAccount(testUser2);
            testOldShopOrSwap.createSellProduct("tshirt", "tshirt", "12.00", testUser1);
            testOldShopOrSwap.createSellProduct("Pink shirt", "shirt that is pink", "19.00", testUser2);

            Product testProduct= testOldShopOrSwap.findProduct("tshirt",testUser1);
            testProduct.textToTag("#Funny #Supreme #shirt");
            Product testProduct2= testOldShopOrSwap.findProduct("tshirt",testUser1);
            System.out.println(testProduct2.getTags());
            testOldShopOrSwap.productTagsToTag(testProduct2);
            testOldShopOrSwap.productTagsToTag(testProduct2); //tests that running the method on the same product twice works

            assertEquals("sell", testOldShopOrSwap.getTagList().get(1).getName());
            assertEquals("Funny", testOldShopOrSwap.getTagList().get(2).getName());
            assertEquals("Supreme", testOldShopOrSwap.getTagList().get(3).getName());
            assertEquals("shirt", testOldShopOrSwap.getTagList().get(4).getName());
            assertEquals(testOldShopOrSwap.findProduct("tshirt",testUser1), testOldShopOrSwap.getTagList().get(2).getProducts().get(0));


            Product testProduct3= testOldShopOrSwap.findProduct("Pink shirt",testUser2);
            testProduct3.textToTag("#Hilarious #supreme #shirt"); //tests new tag, tag with different capitalization , old tag
            testOldShopOrSwap.productTagsToTag(testProduct3);
            System.out.println(testOldShopOrSwap.getTagList());
            assertEquals("sell", testOldShopOrSwap.getTagList().get(1).getName());
            assertEquals("Funny", testOldShopOrSwap.getTagList().get(2).getName());
            assertEquals("Supreme", testOldShopOrSwap.getTagList().get(3).getName());
            assertEquals("shirt", testOldShopOrSwap.getTagList().get(4).getName());
            assertEquals("Hilarious", testOldShopOrSwap.getTagList().get(5).getName());
            assertEquals(2, testOldShopOrSwap.getTagList().get(3).getProducts().size());
            assertEquals(2, testOldShopOrSwap.getTagList().get(4).getProducts().size());
            assertEquals(testOldShopOrSwap.findProduct("tshirt",testUser1), testOldShopOrSwap.getTagList().get(4).getProducts().get(0));
            assertEquals(testOldShopOrSwap.findProduct("Pink shirt",testUser2), testOldShopOrSwap.getTagList().get(4).getProducts().get(1));




        }
        @Test
        void searchTagTest(){
            OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap();
            User testUser1 = new User("testname1", "testpassword1");
            User testUser2 = new User("testname2", "testpassword2");
            testOldShopOrSwap.addAccount(testUser1);
            testOldShopOrSwap.addAccount(testUser2);
            testOldShopOrSwap.createSellProduct("tshirt", "tshirt", "12.00", testUser1);
            testOldShopOrSwap.createSellProduct("Pink shirt", "shirt that is pink", "23.21", testUser2);

            Product testProduct= testOldShopOrSwap.findProduct("tshirt",testUser1);
            testProduct.textToTag("#Funny #Supreme #shirt");
            testOldShopOrSwap.productTagsToTag(testProduct);
            Product testProduct2= testOldShopOrSwap.findProduct("Pink shirt",testUser2);
            testProduct2.textToTag("#Hilarious #supreme #shirt");
            testOldShopOrSwap.productTagsToTag(testProduct2);

            List<Product> searchResults = testOldShopOrSwap.searchForTag("#shirt"); //Search returns one search result
            assertEquals(2, searchResults.size());
            assertEquals("tshirt", searchResults.get(0).getName());
            assertEquals("Pink shirt", searchResults.get(1).getName());

            List<Product> searchResults2 = testOldShopOrSwap.searchForTag("#hilarious"); //Search returns two search results from two different users

            assertEquals(1, searchResults2.size());
            assertEquals("Pink shirt", searchResults2.get(0).getName());

            assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap.searchForTag("#AWESOMENESS"));//No search results
            assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.searchForTag(""));//No search results

        }
}
