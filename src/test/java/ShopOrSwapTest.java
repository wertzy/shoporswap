import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShopOrSwapTest {

    @Test
    void constructorsTest() {
        // write automated tests for method ShopOrSwap() and ShopOrSwap(List<User> users, List<Product> products), then implement corresponding methods to these tests
        List<User> testUserList1, testUserList2, testUserList3, testUserList4,
                   testUserList5, testUserList6, testUserList7, testUserList8;
        User testUser1, testUser2, testUser3;
        List<Product> testProductList1, testProductList2, testProductList3, testProductList4,
                      testProductList5, testProductList6, testProductList7, testProductList8;
        Product testProduct1, testProduct2, testProduct3;
        ShopOrSwap testShopOrSwap1, testShopOrSwap2, testShopOrSwap3, testShopOrSwap4, testShopOrSwap5,
                   testShopOrSwap6, testShopOrSwap7, testShopOrSwap8;

        // Equivalency class: default constructor (valid case, border case)
        testShopOrSwap1 = new ShopOrSwap();
        assertTrue(testShopOrSwap1.getUserList().isEmpty());
        assertTrue(testShopOrSwap1.getProductList().isEmpty());

        // Equivalence class: the Users list is valid (invalid case, border case)
        testUserList1 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser1", "testpassword2");
        testUserList1.add(testUser1);
        testUserList1.add(testUser2);
        testProductList1 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription2", testUser2);
        testProductList1.add(testProduct1);
        testProductList1.add(testProduct2);
        assertThrows(IllegalArgumentException.class, () -> new ShopOrSwap(testUserList1));
        assertThrows(IllegalArgumentException.class, () -> new ShopOrSwap(testUserList1, testProductList1));

        // Equivalence class: the Users list is valid (invalid case, middle case)
        testUserList2 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser1", "testpassword2");
        testUser3 = new User("testuser1", "testpassword2");
        testUserList2.add(testUser1);
        testUserList2.add(testUser2);
        testUserList2.add(testUser3);
        testProductList2 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription2", testUser2);
        testProduct3 = new Product("testname3", "testdescription2", testUser2);
        testProductList2.add(testProduct1);
        testProductList2.add(testProduct2);
        testProductList2.add(testProduct3);
        assertThrows(IllegalArgumentException.class, () -> new ShopOrSwap(testUserList2));
        assertThrows(IllegalArgumentException.class, () -> new ShopOrSwap(testUserList2, testProductList2));

        // Equivalence class: the Products list is valid (invalid case, border case)
        testUserList3 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUserList3.add(testUser1);
        testUserList3.add(testUser2);
        testProductList3 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname1", "testdescription2", testUser2);
        testProductList3.add(testProduct1);
        testProductList3.add(testProduct2);
        assertThrows(IllegalArgumentException.class, () -> new ShopOrSwap(testUserList3, testProductList3));

        // Equivalence class: the Products list is valid (invalid case, middle case)
        testUserList4 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUser3 = new User("testuser3", "testpassword2");
        testUserList4.add(testUser1);
        testUserList4.add(testUser2);
        testProductList4 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription2", testUser2);
        testProduct3 = new Product("testname3", "testdescription2", testUser3);
        testProductList4.add(testProduct1);
        testProductList4.add(testProduct2);
        testProductList4.add(testProduct3);
        assertThrows(IllegalArgumentException.class, () -> new ShopOrSwap(testUserList4, testProductList4));

        // Equivalence class: the Users list is valid (valid case, border case),
        // the Products list is valid (valid case, border case)
        testUserList5 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUserList5.add(testUser1);
        testProductList5 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProductList5.add(testProduct1);
        testShopOrSwap2 = new ShopOrSwap(testUserList5);
        assertTrue(testShopOrSwap2.getUserList().containsAll(testUserList5));
        assertTrue(testShopOrSwap2.getProductList().isEmpty());
        testShopOrSwap3 = new ShopOrSwap(testUserList5, testProductList5);
        assertTrue(testShopOrSwap3.getUserList().containsAll(testUserList5));
        assertTrue(testShopOrSwap3.getProductList().containsAll(testProductList5));

        // Equivalence class: the Users list is valid (valid case, middle case),
        // the Products list is valid (valid case, border case)
        testUserList6 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUser3 = new User("testuser3", "testpassword3");
        testUserList6.add(testUser1);
        testUserList6.add(testUser2);
        testUserList6.add(testUser3);
        testProductList6 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProductList6.add(testProduct1);
        testShopOrSwap4 = new ShopOrSwap(testUserList6);
        assertTrue(testShopOrSwap4.getUserList().containsAll(testUserList6));
        assertTrue(testShopOrSwap4.getProductList().isEmpty());
        testShopOrSwap5 = new ShopOrSwap(testUserList6, testProductList6);
        assertTrue(testShopOrSwap5.getUserList().containsAll(testUserList6));
        assertTrue(testShopOrSwap5.getProductList().containsAll(testProductList6));

        // Equivalence class: the Users list is valid (valid case, border case),
        // the Products list is valid (valid case, middle case)
        testUserList7 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUserList7.add(testUser1);
        testProductList7 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription2", testUser1);
        testProduct3 = new Product("testname3", "testdescription3", testUser1);
        testProductList7.add(testProduct1);
        testProductList7.add(testProduct2);
        testProductList7.add(testProduct3);
        testShopOrSwap6 = new ShopOrSwap(testUserList7);
        assertTrue(testShopOrSwap6.getUserList().containsAll(testUserList7));
        assertTrue(testShopOrSwap6.getProductList().isEmpty());
        testShopOrSwap7 = new ShopOrSwap(testUserList7, testProductList7);
        assertTrue(testShopOrSwap7.getUserList().containsAll(testUserList7));
        assertTrue(testShopOrSwap7.getProductList().containsAll(testProductList7));

        // Equivalence class: the Users list is valid (valid case, middle case),
        // the Products list is valid (valid case, middle case)
        testUserList8 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUser3 = new User("testuser3", "testpassword3");
        testUserList8.add(testUser1);
        testUserList8.add(testUser2);
        testUserList8.add(testUser3);
        testProductList8 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription1", testUser1);
        testProduct3 = new Product("testname3", "testdescription1", testUser1);
        testProductList8.add(testProduct1);
        testProductList8.add(testProduct2);
        testProductList8.add(testProduct3);
        testShopOrSwap7 = new ShopOrSwap(testUserList8);
        assertTrue(testShopOrSwap7.getUserList().containsAll(testUserList8));
        assertTrue(testShopOrSwap7.getProductList().isEmpty());
        testShopOrSwap8 = new ShopOrSwap(testUserList8, testProductList8);
        assertTrue(testShopOrSwap8.getUserList().containsAll(testUserList8));
        assertTrue(testShopOrSwap8.getProductList().containsAll(testProductList8));
    }

    @Test
    void signInTest(){
        // write automated tests for method signIn(String accountName, String password), then implement corresponding methods to these tests
        List<User> testUserList;
        User testUser1, testUser2;
        testUser1 = new User("testname1", "testpassword1");
        testUser2 = new User("testname2", "testpassword2");
        testUserList = new ArrayList<User>();
        testUserList.add(testUser1);
        testUserList.add(testUser2);
        ShopOrSwap testShopOrSwap = new ShopOrSwap(testUserList);

        // Equivalence class: correct username (invalid case, border case)
        assertNull(testShopOrSwap.signIn("testuser11", "testpassword1"));

        // Equivalence class: correct username (invalid case, middle case)
        assertNull(testShopOrSwap.signIn("thisisanincorrectusername", "testpassword2"));

        // Equivalence class: correct password (invalid case, border case)
        assertNull(testShopOrSwap.signIn("testuser2", "testpassword22"));

        // Equivalence class: correct password (invalid case, middle case)
        assertNull(testShopOrSwap.signIn("testuser1", "thisisanincorrectpassword"));

        // Equivalence class: correct username (valid case, border case), correct password (valid case, border case)
        assertEquals(testUser1, testShopOrSwap.signIn("testname1", "testpassword1"));
        assertEquals(testUser2, testShopOrSwap.signIn("testname2", "testpassword2"));
    }

    @Test
    void signOutTest(){
        // write automated tests for method signOut(String accountName, String password), then implement corresponding methods to these tests
        List<User> testUserList;
        User testUser1, testUser2;
        testUser1 = new User("testname1", "testpassword1");
        testUser2 = new User("testname2", "testpassword2");
        testUserList = new ArrayList<User>();
        testUserList.add(testUser1);
        testUserList.add(testUser2);
        ShopOrSwap testShopOrSwap = new ShopOrSwap(testUserList);

        // Equivalence class: correct username (invalid case, border case)
        assertNull(testShopOrSwap.signOut("testuser11", "testpassword1"));

        // Equivalence class: correct username (invalid case, middle case)
        assertNull(testShopOrSwap.signOut("thisisanincorrectusername", "testpassword2"));

        // Equivalence class: correct password (invalid case, border case)
        assertNull(testShopOrSwap.signOut("testuser2", "testpassword22"));

        // Equivalence class: correct password (invalid case, middle case)
        assertNull(testShopOrSwap.signOut("testuser1", "thisisanincorrectpassword"));

        // Equivalence class: correct username (valid case, border case), correct password (valid case, border case)
        assertEquals(testUser1, testShopOrSwap.signOut("testname1", "testpassword1"));
        assertEquals(testUser2, testShopOrSwap.signOut("testname2", "testpassword2"));
    }

    @Test
    void createAccountTest(){
        // write automated tests for method createAccount(String accountName, String password), then implement corresponding methods to these tests
        User testUser1;
        ShopOrSwap testShopOrSwap = new ShopOrSwap();

        // Equivalence class: valid username (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.createAccount(" ", "testpassword"));

        // Equivalence class: valid username (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.createAccount(" invalid account name$ ", "testpassword"));

        // Equivalence class: valid password (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.createAccount("testname", " "));

        // Equivalence class: valid password (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.createAccount("testname", " invalid pa$$word "));

        // Equivalence class: valid username (valid case), valid password (valid case)
        testUser1 = testShopOrSwap.createAccount("testname", "testpassword");
        assertTrue(testShopOrSwap.getUserList().contains(testUser1));
    }

    @Test
    void addAccountTest(){
        // write automated tests for method addAccount(User user), then implement corresponding methods to these tests
        User testUser1;
        ShopOrSwap testShopOrSwap = new ShopOrSwap();

        // Equivalence class: valid username (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addAccount(new User(" ", "testpassword")));

        // Equivalence class: valid username (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addAccount(new User(" invalid account name$ ", "testpassword")));

        // Equivalence class: valid password (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addAccount(new User("testname", " ")));

        // Equivalence class: valid password (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap.addAccount(new User("testname", " invalid pa$$word ")));

        // Equivalence class: valid username (valid case), valid password (valid case)
        testUser1 = testShopOrSwap.addAccount(new User("testname", "testpassword"));
        assertTrue(testShopOrSwap.getUserList().contains(testUser1));
    }

    @Test
    void removeAccountTest(){
        // write automated tests for method addAccount(User user), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2, testShopOrSwap3;
        User testUser1, testUser2, testUser3;

        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUser3 = new User("testuser3", "testpassword3");

        // Equivalence class: user to remove does exist (invalid case, border case)
        testShopOrSwap1 = new ShopOrSwap();
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap1.removeAccount(testUser1));

        // Equivalence class: user to remove does exist (invalid case, border case)
        testShopOrSwap2 = new ShopOrSwap();
        testShopOrSwap1.addAccount(testUser2);
        testShopOrSwap1.addAccount(testUser3);
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap1.removeAccount(testUser1));

        // Equivalence class: user to remove does exist (valid case, border case)
        testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount(testUser1);
        assertEquals(testUser1, testShopOrSwap3.removeAccount(testUser1));

        // Equivalence class: user to remove does exist (valid case, middle case)
        testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount(testUser1);
        testShopOrSwap3.addAccount(testUser2);
        testShopOrSwap3.addAccount(testUser3);
        assertEquals(testUser2, testShopOrSwap3.removeAccount(testUser2));

    }

    @Test
    void findAccountTest(){
        // write automated tests for method addAccount(User user), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2, testShopOrSwap3, testShopOrSwap4;
        User testUser1, testUser2, testUser3;

        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUser3 = new User("testuser3", "testpassword3");

        // Equivalence class: user to find does exist (invalid case, border case)
        testShopOrSwap1 = new ShopOrSwap();
        assertNull(testShopOrSwap1.findAccount(testUser1));

        // Equivalence class: user to find does exist (invalid case, middle case)
        testShopOrSwap2 = new ShopOrSwap();
        testShopOrSwap2.addAccount(testUser1);
        testShopOrSwap2.addAccount(testUser2);
        assertNull(testShopOrSwap2.findAccount(testUser3));

        // Equivalence class: user to find does exist (valid case, border case)
        testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount(testUser1);
        assertEquals(testUser1, testShopOrSwap3.findAccount(testUser1));

        // Equivalence class: user to find does exist (valid case, middle case)
        testShopOrSwap4 = new ShopOrSwap();
        testShopOrSwap4.addAccount(testUser1);
        testShopOrSwap4.addAccount(testUser2);
        testShopOrSwap4.addAccount(testUser3);
        assertEquals(testUser3, testShopOrSwap4.findAccount(testUser3));

    }

    @Test
    void createSellProductTest(){
        //TODO write automated tests for method createSellProduct(String name, String description, String price, User merchant), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2, testShopOrSwap3, testShopOrSwap4;
        User testUser1, testUser2, testUser3;
        Product testProduct1, testProduct2, testProduct3;
        testUser1 = new User("testname1", "testpassword1");
        testUser2 = new User("testname2", "testpassword2");
        testUser3 = new User("testname3", "testpassword3");

        // Equivalence class: valid product (invalid case, border case)
        testShopOrSwap1 = new ShopOrSwap();
        testShopOrSwap1.addAccount(testUser2);
        testShopOrSwap1.addAccount(testUser3);
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap1.createSellProduct("testname1", "testdescription1", "55.55", testUser1));

        // Equivalence class: valid product (invalid case, middle case)
        testShopOrSwap2 = new ShopOrSwap();
        testShopOrSwap2.addAccount(testUser1);
        testShopOrSwap2.addAccount(testUser2);
        testShopOrSwap2.addAccount(testUser3);
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap2.createSellProduct(" invalid name ", " invalid desc ", " invalid price ", testUser1));

        // Equivalence class: valid product (valid case, border case)
        testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount(testUser1);
        testShopOrSwap3.addAccount(testUser2);
        testShopOrSwap3.addAccount(testUser3);
        testShopOrSwap3.createSellProduct("testname1", "testdescription1", "55.55", testUser1);
        assertTrue(testShopOrSwap3.findProduct("testname1", testUser1).getTags().contains("sell"));

        // Equivalence class: valid product (valid case, middle case)
        testShopOrSwap4 = new ShopOrSwap();
        testShopOrSwap4.addAccount(testUser1);
        testShopOrSwap4.addAccount(testUser2);
        testShopOrSwap4.addAccount(testUser3);
        testShopOrSwap4.createSellProduct("testname1", "testdescription1", "55.55", testUser1);
        testShopOrSwap4.createSellProduct("testname2", "testdescription2", "55.55", testUser2);
        testShopOrSwap4.createSellProduct("testname3", "testdescription3", "55.55", testUser3);
        assertTrue(testShopOrSwap4.findProduct("testname2", testUser2).getTags().contains("sell"));
    }

    @Test
    void createSwapProductTest(){
        //TODO write automated tests for method createSwapProduct(String name, String description, String price, User merchant), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2, testShopOrSwap3, testShopOrSwap4;
        User testUser1, testUser2, testUser3;
        Product testProduct1, testProduct2, testProduct3;
        testUser1 = new User("testname1", "testpassword1");
        testUser2 = new User("testname2", "testpassword2");
        testUser3 = new User("testname3", "testpassword3");

        // Equivalence class: valid product (invalid case, border case)
        testShopOrSwap1 = new ShopOrSwap();
        testShopOrSwap1.addAccount(testUser2);
        testShopOrSwap1.addAccount(testUser3);
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap1.createSwapProduct("testname1", "testdescription1", "55.55", testUser1));

        // Equivalence class: valid product (invalid case, middle case)
        testShopOrSwap2 = new ShopOrSwap();
        testShopOrSwap2.addAccount(testUser1);
        testShopOrSwap2.addAccount(testUser2);
        testShopOrSwap2.addAccount(testUser3);
        assertThrows(IllegalArgumentException.class, ()-> testShopOrSwap2.createSwapProduct(" invalid name ", " invalid desc ", " invalid price ", testUser1));

        // Equivalence class: valid product (valid case, border case)
        testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount(testUser1);
        testShopOrSwap3.addAccount(testUser2);
        testShopOrSwap3.addAccount(testUser3);
        testShopOrSwap3.createSwapProduct("testname1", "testdescription1", "55.55", testUser1);
        assertTrue(testShopOrSwap3.findProduct("testname1", testUser1).getTags().contains("swap"));

        // Equivalence class: valid product (valid case, middle case)
        testShopOrSwap4 = new ShopOrSwap();
        testShopOrSwap4.addAccount(testUser1);
        testShopOrSwap4.addAccount(testUser2);
        testShopOrSwap4.addAccount(testUser3);
        testShopOrSwap4.createSwapProduct("testname1", "testdescription1", "55.55", testUser1);
        testShopOrSwap4.createSwapProduct("testname2", "testdescription2", "55.55", testUser2);
        testShopOrSwap4.createSwapProduct("testname3", "testdescription3", "55.55", testUser3);
        assertTrue(testShopOrSwap4.findProduct("testname2", testUser2).getTags().contains("swap"));
    }

    @Test
    void findProductTest(){
        // write automated tests for method findProduct(String name, User user), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2, testShopOrSwap3, testShopOrSwap4;
        User testUser1 = new User("testname1", "testpassword1");
        User testUser2 = new User("testname2", "testpassword2");
        Product testProduct1, testProduct2;

        // Equivalence class: product to find does exist (invalid case, border case)
        testShopOrSwap1 = new ShopOrSwap();
        testShopOrSwap1.addAccount(testUser1);
        testShopOrSwap1.createSellProduct("testname1", "testdescription1", "55.55", testUser1);
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap1.findProduct("testname2", testUser1));

        // Equivalence class: product to find does exist (invalid case, middle case)
        testShopOrSwap2 = new ShopOrSwap();
        testShopOrSwap2.addAccount(testUser1);
        testShopOrSwap2.addAccount(testUser2);
        testShopOrSwap2.createSellProduct("testname1","testdescription1","55.55", testUser1);
        testShopOrSwap2.createSellProduct("testname2","testdescription2","55.55", testUser2);
        testShopOrSwap2.createSwapProduct("testname3","testdescription3","55.55", testUser1);
        testShopOrSwap2.createSwapProduct("testname4","testdescription4","55.55", testUser2);
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap1.findProduct("testname2", testUser1));
        assertThrows(NoSuchElementException.class, ()-> testShopOrSwap1.findProduct("testname3", testUser2));

        // Equivalence class: product to find does exist (valid case, border case)
        testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount(testUser1);
        testShopOrSwap3.createSellProduct("testname1", "testdescription1", "55.55", testUser1);
        assertEquals("testname1", testShopOrSwap1.findProduct("testname1", testUser1).getName());

        // Equivalence class: product to find does exist (valid case, middle case)
        testShopOrSwap4 = new ShopOrSwap();
        testShopOrSwap4.addAccount(testUser1);
        testShopOrSwap4.addAccount(testUser2);
        testShopOrSwap4.createSellProduct("testname1","testdescription1","55.55", testUser1);
        testShopOrSwap4.createSellProduct("testname2","testdescription2","55.55", testUser2);
        testShopOrSwap4.createSwapProduct("testname3","testdescription3","55.55", testUser1);
        testShopOrSwap4.createSwapProduct("testname4","testdescription4","55.55", testUser2);
        assertEquals("testname3", testShopOrSwap4.findProduct("testname3", testUser1).getName());
        assertEquals("testname4", testShopOrSwap4.findProduct("testname4", testUser2).getName());
    }

    @Test
    void getUserProductsTest(){
        // write automated tests for method getUserProducts(User user), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid user (valid case, border case)
        testShopOrSwap1 = new ShopOrSwap(testUsers);
        testShopOrSwap1.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        assertEquals(0, testShopOrSwap1.getUserProducts(testUser2).size());
        assertEquals(1, testShopOrSwap1.getUserProducts(testUser1).size());

        // Equivalence class: valid user (valid case, middle case)
        testShopOrSwap2 = new ShopOrSwap(testUsers);
        testShopOrSwap2.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        testShopOrSwap2.createSwapProduct("testproduct2", "testdescription2", "20", testUser1);
        testShopOrSwap2.createSellProduct("testproduct3", "testdescription3", "20", testUser1);
        testShopOrSwap2.createSwapProduct("testproduct4", "testdescription4", "20", testUser1);
        assertEquals(0, testShopOrSwap2.getUserProducts(testUser2).size());
        assertEquals(4, testShopOrSwap2.getUserProducts(testUser1).size());
    }

    @Test
    void getSellProductsTest(){
        // write automated tests for method getSellProducts(), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid user (valid case, border case)
        testShopOrSwap1 = new ShopOrSwap(testUsers);
        testShopOrSwap1.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        assertEquals(1, testShopOrSwap1.getSellProducts().size());

        // Equivalence class: valid user (valid case, middle case)
        testShopOrSwap2 = new ShopOrSwap(testUsers);
        testShopOrSwap2.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        testShopOrSwap2.createSwapProduct("testproduct2", "testdescription2", "20", testUser1);
        testShopOrSwap2.createSellProduct("testproduct3", "testdescription3", "20", testUser1);
        testShopOrSwap2.createSwapProduct("testproduct4", "testdescription4", "20", testUser1);
        assertEquals(2, testShopOrSwap2.getSellProducts().size());
    }

    @Test
    void getSwapProductsTest(){
        // write automated tests for method getSwapProducts(), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid user (valid case, border case)
        testShopOrSwap1 = new ShopOrSwap(testUsers);
        testShopOrSwap1.createSwapProduct("testproduct1", "testdescription1", "20", testUser1);
        assertEquals(1, testShopOrSwap1.getSwapProducts().size());

        // Equivalence class: valid user (valid case, middle case)
        testShopOrSwap2 = new ShopOrSwap(testUsers);
        testShopOrSwap2.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        testShopOrSwap2.createSwapProduct("testproduct2", "testdescription2", "20", testUser1);
        testShopOrSwap2.createSellProduct("testproduct3", "testdescription3", "20", testUser1);
        testShopOrSwap2.createSwapProduct("testproduct4", "testdescription4", "20", testUser1);
        assertEquals(2, testShopOrSwap2.getSwapProducts().size());
    }

    @Test
    void getUserList(){
        // write automated tests for method getUserList(), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid number of users (valid case, border case)
        testShopOrSwap1 = new ShopOrSwap();
        assertEquals(0, testShopOrSwap1.getUserList().size());

        // Equivalence class: valid number of users (valid case, middle case)
        testShopOrSwap2 = new ShopOrSwap(testUsers);
        assertEquals(2, testShopOrSwap2.getUserList().size());
    }

    @Test
    void getProductList(){
        // write automated tests for method getProductList(), then implement corresponding methods to these tests
        ShopOrSwap testShopOrSwap1, testShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid number of users (valid case, border case)
        testShopOrSwap1 = new ShopOrSwap();
        assertEquals(0, testShopOrSwap1.getProductList().size());

        // Equivalence class: valid number of users (valid case, middle case)
        testShopOrSwap2 = new ShopOrSwap(testUsers);
        testShopOrSwap2.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        testShopOrSwap2.createSwapProduct("testproduct2", "testdescription2", "20", testUser1);
        testShopOrSwap2.createSellProduct("testproduct3", "testdescription3", "20", testUser2);
        testShopOrSwap2.createSwapProduct("testproduct4", "testdescription4", "20", testUser2);
        assertEquals(4, testShopOrSwap2.getProductList().size());
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

    @Test
    public void importUserDataTest(){

    }

    @Test
    public void importProductDataTest(){

    }

    @Test
    public void exitTest(){

    }

    @Test
    public void findUserByNameTest(){
        ShopOrSwap testShopOrSwap1 = new ShopOrSwap();
        // Equivalence class: find 1 of 0 users in system (invalid case, border case)
        // Equivalence class: find 1 of 10 users in system (invalid case, middle case)
        // Equivalence class: find 1 of 1 users in system (valid case, border case)
        // Equivalence class: find 1 of 10 users in system (valid case, middle case)
    }

    @Test
    public void swapProductTest(){

    }

}
