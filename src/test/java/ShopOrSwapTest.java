import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShopOrSwapTest {

    @Test
    void constructorsTest(){
        //TODO write automated tests for method ShopOrSwap() and ShopOrSwap(List<User> users, List<Product> products), then implement corresponding methods to these tests
    }

    @Test
    void signInTest(){
        //TODO write automated tests for method signIn(String accountName, String password), then implement corresponding methods to these tests
    }

    @Test
    void signOutTest(){
        //TODO write automated tests for method signOut(String accountName, String password), then implement corresponding methods to these tests
    }

    @Test
    void createAccountTest(){
        //TODO write automated tests for method createAccount(String accountName, String password), then implement corresponding methods to these tests
    }

    @Test
    void createSellProductTest(){
        //TODO write automated tests for method createSellProduct(String name, String description, String price, User merchant), then implement corresponding methods to these tests
    }

    @Test
    void createSwapProductTest(){
        //TODO write automated tests for method createSwapProduct(String name, String description, String price, User merchant), then implement corresponding methods to these tests
    }

    @Test
    void viewUserProductsTest(){
        //TODO write automated tests for method viewUserProducts(User user), then implement corresponding methods to these tests
    }

    @Test
    void viewSellProductsTest(){
        //TODO write automated tests for method viewSellProducts(), then implement corresponding methods to these tests
    }

    @Test
    void viewSwapProductsTest(){
        //TODO write automated tests for method viewSwapProducts(), then implement corresponding methods to these tests
    }

    @Test
    void getUserList(){
        //TODO write automated tests for method getUserList(), then implement corresponding methods to these tests
    }

    @Test
    void getProductList(){
        //TODO write automated tests for method getProductList(), then implement corresponding methods to these tests
    }

    @Test
    void isValidUserListTest(){
        // write automated tests for method isValidUserList(List<User> users), then implement corresponding methods to these tests
        User testUser1, testUser2, testUser3, testUser4, testUser5;
        List<User> testUserList1, testUserList2, testUserList3, testUserList4, testUserList5, testUserList6;

        // Equivalence class: Users list cannot be empty (invalid case, border case)
        testUserList1 = new ArrayList<User>();
        assertFalse(ShopOrSwap.isValidUserList(testUserList1));

        // Equivalence class: Users list cannot have multiple Users with the same account name (invalid case, border case)
        testUserList2 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser1", "testpassword2");
        testUserList2.add(testUser1);
        testUserList2.add(testUser2);
        assertFalse(ShopOrSwap.isValidUserList(testUserList2));

        // Equivalence class: Users list cannot have multiple Users with the same account name (invalid case, middle case)
        testUserList3 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser1", "testpassword2");
        testUser3 = new User("testuser1", "testpassword3");
        testUser4 = new User("testuser1", "testpassword4");
        testUser5 = new User("testuser1", "testpassword5");
        testUserList3.add(testUser1);
        testUserList3.add(testUser2);
        testUserList3.add(testUser3);
        testUserList3.add(testUser4);
        testUserList3.add(testUser5);
        assertFalse(ShopOrSwap.isValidUserList(testUserList3));

        // Equivalence class: Users list cannot be empty (valid case, border case)
        testUserList4 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUserList4.add(testUser1);
        assertTrue(ShopOrSwap.isValidUserList(testUserList4));

        // Equivalence class: Users list cannot be empty (valid case, middle case), Users list cannot have mutliple Users with the same account name (valid case, border case)
        testUserList5 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUserList5.add(testUser1);
        testUserList5.add(testUser2);
        assertTrue(ShopOrSwap.isValidUserList(testUserList5));

        // Equivalence class: Users list cannot have multiple Users with the same account name (valid case, middle case)
        testUserList6 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUser3 = new User("testuser3", "testpassword3");
        testUser4 = new User("testuser4", "testpassword4");
        testUser5 = new User("testuser5", "testpassword5");
        testUserList6.add(testUser1);
        testUserList6.add(testUser2);
        testUserList6.add(testUser3);
        testUserList6.add(testUser4);
        testUserList6.add(testUser5);
        assertTrue(ShopOrSwap.isValidUserList(testUserList6));

    }

    @Test
    void isValidProductListTest(){
        // write automated tests for method isValidProductList(List<Product> products, List<User> users), then implement corresponding methods to these tests
        List<User> testUserList1 = new ArrayList<User>();
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        User testUser3 = new User("testuser3", "testpassword3");
        User testUser4 = new User("testuser4", "testpassword4");
        User testUser5 = new User("testuser5", "testpassword5");
        User testUser6 = new User("testuser6", "testpassword6");
        testUserList1.add(testUser1);
        testUserList1.add(testUser2);
        testUserList1.add(testUser3);
        testUserList1.add(testUser4);
        testUserList1.add(testUser5);

        List<Product> testProductList1, testProductList2, testProductList3,
                      testProductList4, testProductList5, testProductList6,
                      testProductList7, testProductList8, testProductList9,
                      testProductList10, testProductList11, testProductList12,
                      testProductList13, testProductList14, testProductList15;
        Product testProduct1, testProduct2, testProduct3, testProduct4, testProduct5;

        // Equivalence class: Products list cannot be empty (invalid case, border case)
        testProductList1 = new ArrayList<Product>();
        assertFalse(ShopOrSwap.isValidProductList(testProductList1, testUserList1));

        // Equivalence class: Products list cannot have multiple Products with the same name (invalid cases, middle cases)
        testProductList2 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname1", "testdescription1", testUser1);
        testProductList2.add(testProduct1);
        testProductList2.add(testProduct2);
        assertFalse(ShopOrSwap.isValidProductList(testProductList2, testUserList1));

        testProductList3 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname1", "testdescription1", testUser2);
        testProductList3.add(testProduct1);
        testProductList3.add(testProduct2);
        assertFalse(ShopOrSwap.isValidProductList(testProductList3, testUserList1));

        // Equivalence class: Products list cannot have multiple Products with the same name (invalid cases, middle cases)
        testProductList4 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname1", "testdescription1", testUser1);
        testProduct3 = new Product("testname1", "testdescription1", testUser1);
        testProduct4 = new Product("testname1", "testdescription1", testUser1);
        testProduct5 = new Product("testname1", "testdescription1", testUser1);
        testProductList4.add(testProduct1);
        testProductList4.add(testProduct2);
        testProductList4.add(testProduct3);
        testProductList4.add(testProduct4);
        testProductList4.add(testProduct5);
        assertFalse(ShopOrSwap.isValidProductList(testProductList4, testUserList1));

        testProductList5 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname1", "testdescription1", testUser2);
        testProduct3 = new Product("testname1", "testdescription1", testUser3);
        testProduct4 = new Product("testname1", "testdescription1", testUser4);
        testProduct5 = new Product("testname1", "testdescription1", testUser5);
        testProductList5.add(testProduct1);
        testProductList5.add(testProduct2);
        testProductList5.add(testProduct3);
        testProductList5.add(testProduct4);
        testProductList5.add(testProduct5);
        assertFalse(ShopOrSwap.isValidProductList(testProductList5, testUserList1));

        // Equivalence class: Products list cannot have Products with merchants not in the Users list (invalid cases, border cases)
        testProductList6 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser6);
        testProductList6.add(testProduct1);
        assertFalse(ShopOrSwap.isValidProductList(testProductList6, testUserList1));

        testProductList7 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", null);
        testProductList7.add(testProduct1);
        assertFalse(ShopOrSwap.isValidProductList(testProductList7, testUserList1));

        // Equivalence class: Products list cannot have Products with merchants not in the Users list (invalid cases, middle cases)
        testProductList8 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser6);
        testProduct2 = new Product("testname2", "testdescription1", testUser6);
        testProduct3 = new Product("testname3", "testdescription1", testUser6);
        testProduct4 = new Product("testname4", "testdescription1", testUser6);
        testProduct5 = new Product("testname5", "testdescription1", testUser6);
        testProductList8.add(testProduct1);
        testProductList8.add(testProduct2);
        testProductList8.add(testProduct3);
        testProductList8.add(testProduct4);
        testProductList8.add(testProduct5);
        assertFalse(ShopOrSwap.isValidProductList(testProductList8, testUserList1));

        testProductList9 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", null);
        testProduct2 = new Product("testname2", "testdescription1", null);
        testProduct3 = new Product("testname3", "testdescription1", null);
        testProduct4 = new Product("testname4", "testdescription1", null);
        testProduct5 = new Product("testname5", "testdescription1", null);
        testProductList9.add(testProduct1);
        testProductList9.add(testProduct2);
        testProductList9.add(testProduct3);
        testProductList9.add(testProduct4);
        testProductList9.add(testProduct5);
        assertFalse(ShopOrSwap.isValidProductList(testProductList9, testUserList1));

        // Equivalence class: Products list cannot be empty (valid case, border case)
        testProductList10 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProductList10.add(testProduct1);
        assertTrue(ShopOrSwap.isValidProductList(testProductList10, testUserList1));

        // Equivalence class: Products list cannot be empty (valid case, middle case)
        testProductList11 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription1", testUser2);
        testProduct3 = new Product("testname3", "testdescription1", testUser3);
        testProduct4 = new Product("testname4", "testdescription1", testUser4);
        testProduct5 = new Product("testname5", "testdescription1", testUser5);
        testProductList11.add(testProduct1);
        testProductList11.add(testProduct2);
        testProductList11.add(testProduct3);
        testProductList11.add(testProduct4);
        testProductList11.add(testProduct5);
        assertTrue(ShopOrSwap.isValidProductList(testProductList11, testUserList1));

        // Equivalence class: Products list cannot have multiple Products with the same name (valid cases, border cases),
        // Products list cannot have Products with merchants not in the Users list (invalid cases, border cases)
        testProductList12 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription1", testUser1);
        testProductList12.add(testProduct1);
        testProductList12.add(testProduct2);
        assertTrue(ShopOrSwap.isValidProductList(testProductList12, testUserList1));

        testProductList13 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription1", testUser2);
        testProductList13.add(testProduct1);
        testProductList13.add(testProduct2);
        assertTrue(ShopOrSwap.isValidProductList(testProductList13, testUserList1));

        // Equivalence class: Products list cannot have multiple Products with the same name (valid cases, middle cases),
        // Products list cannot have Products with merchants not in the Users list (invalid cases, middle cases)
        testProductList14 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription1", testUser1);
        testProduct3 = new Product("testname3", "testdescription1", testUser1);
        testProduct4 = new Product("testname4", "testdescription1", testUser1);
        testProduct5 = new Product("testname5", "testdescription1", testUser1);
        testProductList14.add(testProduct1);
        testProductList14.add(testProduct2);
        testProductList14.add(testProduct3);
        testProductList14.add(testProduct4);
        testProductList14.add(testProduct5);
        assertTrue(ShopOrSwap.isValidProductList(testProductList14, testUserList1));

        testProductList15 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription1", testUser2);
        testProduct3 = new Product("testname3", "testdescription1", testUser3);
        testProduct4 = new Product("testname4", "testdescription1", testUser4);
        testProduct5 = new Product("testname5", "testdescription1", testUser5);
        testProductList15.add(testProduct1);
        testProductList15.add(testProduct2);
        testProductList15.add(testProduct3);
        testProductList15.add(testProduct4);
        testProductList15.add(testProduct5);
        assertTrue(ShopOrSwap.isValidProductList(testProductList15, testUserList1));

    }
}
