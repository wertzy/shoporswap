import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class OldShopOrSwapTest {

    @Test
    void constructorsTest() {
        // write automated tests for method ShopOrSwap() and ShopOrSwap(List<User> users, List<Product> products), then implement corresponding methods to these tests
        List<User> testUserList1, testUserList2, testUserList3, testUserList4,
                   testUserList5, testUserList6, testUserList7, testUserList8;
        User testUser1, testUser2, testUser3;
        List<Product> testProductList1, testProductList2, testProductList3, testProductList4,
                      testProductList5, testProductList6, testProductList7, testProductList8;
        Product testProduct1, testProduct2, testProduct3;
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2, testOldShopOrSwap3, testOldShopOrSwap4, testOldShopOrSwap5,
                testOldShopOrSwap6, testOldShopOrSwap7, testOldShopOrSwap8;

        // Equivalency class: default constructor (valid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap();
        assertTrue(testOldShopOrSwap1.getUserList().isEmpty());
        assertTrue(testOldShopOrSwap1.getProductList().isEmpty());

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
        assertThrows(IllegalArgumentException.class, () -> new OldShopOrSwap(testUserList1));
        assertThrows(IllegalArgumentException.class, () -> new OldShopOrSwap(testUserList1, testProductList1));

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
        assertThrows(IllegalArgumentException.class, () -> new OldShopOrSwap(testUserList2));
        assertThrows(IllegalArgumentException.class, () -> new OldShopOrSwap(testUserList2, testProductList2));

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
        assertThrows(IllegalArgumentException.class, () -> new OldShopOrSwap(testUserList3, testProductList3));

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
        assertThrows(IllegalArgumentException.class, () -> new OldShopOrSwap(testUserList4, testProductList4));

        // Equivalence class: the Users list is valid (valid case, border case),
        // the Products list is valid (valid case, border case)
        testUserList5 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUserList5.add(testUser1);
        testProductList5 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProductList5.add(testProduct1);
        testOldShopOrSwap2 = new OldShopOrSwap(testUserList5);
        assertTrue(testOldShopOrSwap2.getUserList().containsAll(testUserList5));
        assertTrue(testOldShopOrSwap2.getProductList().isEmpty());
        testOldShopOrSwap3 = new OldShopOrSwap(testUserList5, testProductList5);
        assertTrue(testOldShopOrSwap3.getUserList().containsAll(testUserList5));
        assertTrue(testOldShopOrSwap3.getProductList().containsAll(testProductList5));

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
        testOldShopOrSwap4 = new OldShopOrSwap(testUserList6);
        assertTrue(testOldShopOrSwap4.getUserList().containsAll(testUserList6));
        assertTrue(testOldShopOrSwap4.getProductList().isEmpty());
        testOldShopOrSwap5 = new OldShopOrSwap(testUserList6, testProductList6);
        assertTrue(testOldShopOrSwap5.getUserList().containsAll(testUserList6));
        assertTrue(testOldShopOrSwap5.getProductList().containsAll(testProductList6));

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
        testOldShopOrSwap6 = new OldShopOrSwap(testUserList7);
        assertTrue(testOldShopOrSwap6.getUserList().containsAll(testUserList7));
        assertTrue(testOldShopOrSwap6.getProductList().isEmpty());
        testOldShopOrSwap7 = new OldShopOrSwap(testUserList7, testProductList7);
        assertTrue(testOldShopOrSwap7.getUserList().containsAll(testUserList7));
        assertTrue(testOldShopOrSwap7.getProductList().containsAll(testProductList7));

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
        testOldShopOrSwap7 = new OldShopOrSwap(testUserList8);
        assertTrue(testOldShopOrSwap7.getUserList().containsAll(testUserList8));
        assertTrue(testOldShopOrSwap7.getProductList().isEmpty());
        testOldShopOrSwap8 = new OldShopOrSwap(testUserList8, testProductList8);
        assertTrue(testOldShopOrSwap8.getUserList().containsAll(testUserList8));
        assertTrue(testOldShopOrSwap8.getProductList().containsAll(testProductList8));
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
        OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap(testUserList);

        // Equivalence class: correct username (invalid case, border case)
        assertNull(testOldShopOrSwap.signIn("testuser11", "testpassword1"));

        // Equivalence class: correct username (invalid case, middle case)
        assertNull(testOldShopOrSwap.signIn("thisisanincorrectusername", "testpassword2"));

        // Equivalence class: correct password (invalid case, border case)
        assertNull(testOldShopOrSwap.signIn("testuser2", "testpassword22"));

        // Equivalence class: correct password (invalid case, middle case)
        assertNull(testOldShopOrSwap.signIn("testuser1", "thisisanincorrectpassword"));

        // Equivalence class: correct username (valid case, border case), correct password (valid case, border case)
        assertEquals(testUser1, testOldShopOrSwap.signIn("testname1", "testpassword1"));
        assertEquals(testUser2, testOldShopOrSwap.signIn("testname2", "testpassword2"));
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
        OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap(testUserList);

        // Equivalence class: correct username (invalid case, border case)
        assertNull(testOldShopOrSwap.signOut("testuser11", "testpassword1"));

        // Equivalence class: correct username (invalid case, middle case)
        assertNull(testOldShopOrSwap.signOut("thisisanincorrectusername", "testpassword2"));

        // Equivalence class: correct password (invalid case, border case)
        assertNull(testOldShopOrSwap.signOut("testuser2", "testpassword22"));

        // Equivalence class: correct password (invalid case, middle case)
        assertNull(testOldShopOrSwap.signOut("testuser1", "thisisanincorrectpassword"));

        // Equivalence class: correct username (valid case, border case), correct password (valid case, border case)
        assertEquals(testUser1, testOldShopOrSwap.signOut("testname1", "testpassword1"));
        assertEquals(testUser2, testOldShopOrSwap.signOut("testname2", "testpassword2"));
    }

    @Test
    void createAccountTest(){
        // write automated tests for method createAccount(String accountName, String password), then implement corresponding methods to these tests
        User testUser1;
        OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap();

        // Equivalence class: valid username (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.createAccount(" ", "testpassword"));

        // Equivalence class: valid username (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.createAccount(" invalid account name$ ", "testpassword"));

        // Equivalence class: valid password (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.createAccount("testname", " "));

        // Equivalence class: valid password (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.createAccount("testname", " invalid pa$$word "));

        // Equivalence class: valid username (valid case), valid password (valid case)
        testUser1 = testOldShopOrSwap.createAccount("testname", "testpassword");
        assertTrue(testOldShopOrSwap.getUserList().contains(testUser1));
    }

    @Test
    void addAccountTest(){
        // write automated tests for method addAccount(User user), then implement corresponding methods to these tests
        User testUser1;
        OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap();

        // Equivalence class: valid username (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.addAccount(new User(" ", "testpassword")));

        // Equivalence class: valid username (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.addAccount(new User(" invalid account name$ ", "testpassword")));

        // Equivalence class: valid password (invalid case, border case)
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.addAccount(new User("testname", " ")));

        // Equivalence class: valid password (invalid case, middle case)
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap.addAccount(new User("testname", " invalid pa$$word ")));

        // Equivalence class: valid username (valid case), valid password (valid case)
        testUser1 = testOldShopOrSwap.addAccount(new User("testname", "testpassword"));
        assertTrue(testOldShopOrSwap.getUserList().contains(testUser1));
    }

    @Test
    void removeAccountTest(){
        // write automated tests for method addAccount(User user), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2, testOldShopOrSwap3;
        User testUser1, testUser2, testUser3;

        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUser3 = new User("testuser3", "testpassword3");

        // Equivalence class: user to remove does exist (invalid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap();
        assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap1.removeAccount(testUser1));

        // Equivalence class: user to remove does exist (invalid case, border case)
        testOldShopOrSwap2 = new OldShopOrSwap();
        testOldShopOrSwap1.addAccount(testUser2);
        testOldShopOrSwap1.addAccount(testUser3);
        assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap1.removeAccount(testUser1));

        // Equivalence class: user to remove does exist (valid case, border case)
        testOldShopOrSwap3 = new OldShopOrSwap();
        testOldShopOrSwap3.addAccount(testUser1);
        assertEquals(testUser1, testOldShopOrSwap3.removeAccount(testUser1));

        // Equivalence class: user to remove does exist (valid case, middle case)
        testOldShopOrSwap3 = new OldShopOrSwap();
        testOldShopOrSwap3.addAccount(testUser1);
        testOldShopOrSwap3.addAccount(testUser2);
        testOldShopOrSwap3.addAccount(testUser3);
        assertEquals(testUser2, testOldShopOrSwap3.removeAccount(testUser2));

    }

    @Test
    void findAccountTest(){
        // write automated tests for method addAccount(User user), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2, testOldShopOrSwap3, testOldShopOrSwap4;
        User testUser1, testUser2, testUser3;

        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUser3 = new User("testuser3", "testpassword3");

        // Equivalence class: user to find does exist (invalid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap();
        assertNull(testOldShopOrSwap1.findAccount(testUser1));
        assertNull(testOldShopOrSwap1.findAccount("testuser1"));

        // Equivalence class: user to find does exist (invalid case, middle case)
        testOldShopOrSwap2 = new OldShopOrSwap();
        testOldShopOrSwap2.addAccount(testUser1);
        testOldShopOrSwap2.addAccount(testUser2);
        assertNull(testOldShopOrSwap2.findAccount(testUser3));
        assertNull(testOldShopOrSwap1.findAccount("testuser3"));

        // Equivalence class: user to find does exist (valid case, border case)
        testOldShopOrSwap3 = new OldShopOrSwap();
        testOldShopOrSwap3.addAccount(testUser1);
        assertEquals(testUser1, testOldShopOrSwap3.findAccount(testUser1));
        assertEquals(testUser1, testOldShopOrSwap3.findAccount("testuser1"));

        // Equivalence class: user to find does exist (valid case, middle case)
        testOldShopOrSwap4 = new OldShopOrSwap();
        testOldShopOrSwap4.addAccount(testUser1);
        testOldShopOrSwap4.addAccount(testUser2);
        testOldShopOrSwap4.addAccount(testUser3);
        assertEquals(testUser3, testOldShopOrSwap4.findAccount(testUser3));
        assertEquals(testUser3, testOldShopOrSwap4.findAccount("testuser3"));

    }

    @Test
    void createSellProductTest(){
        //TODO write automated tests for method createSellProduct(String name, String description, String price, User merchant), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2, testOldShopOrSwap3, testOldShopOrSwap4;
        User testUser1, testUser2, testUser3;
        Product testProduct1, testProduct2, testProduct3;
        testUser1 = new User("testname1", "testpassword1");
        testUser2 = new User("testname2", "testpassword2");
        testUser3 = new User("testname3", "testpassword3");

        // Equivalence class: valid product (invalid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap();
        testOldShopOrSwap1.addAccount(testUser2);
        testOldShopOrSwap1.addAccount(testUser3);
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.createSellProduct("testname1", "testdescription1", "55.55", testUser1));

        // Equivalence class: valid product (invalid case, middle case)
        testOldShopOrSwap2 = new OldShopOrSwap();
        testOldShopOrSwap2.addAccount(testUser1);
        testOldShopOrSwap2.addAccount(testUser2);
        testOldShopOrSwap2.addAccount(testUser3);
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap2.createSellProduct(" invalid name ", " invalid desc ", " invalid price ", testUser1));

        // Equivalence class: valid product (valid case, border case)
        testOldShopOrSwap3 = new OldShopOrSwap();
        testOldShopOrSwap3.addAccount(testUser1);
        testOldShopOrSwap3.addAccount(testUser2);
        testOldShopOrSwap3.addAccount(testUser3);
        testOldShopOrSwap3.createSellProduct("testname1", "testdescription1", "55.55", testUser1);
        assertTrue(testOldShopOrSwap3.findProduct("testname1", testUser1).getTags().contains("sell"));

        // Equivalence class: valid product (valid case, middle case)
        testOldShopOrSwap4 = new OldShopOrSwap();
        testOldShopOrSwap4.addAccount(testUser1);
        testOldShopOrSwap4.addAccount(testUser2);
        testOldShopOrSwap4.addAccount(testUser3);
        testOldShopOrSwap4.createSellProduct("testname1", "testdescription1", "55.55", testUser1);
        testOldShopOrSwap4.createSellProduct("testname2", "testdescription2", "55.55", testUser2);
        testOldShopOrSwap4.createSellProduct("testname3", "testdescription3", "55.55", testUser3);
        assertTrue(testOldShopOrSwap4.findProduct("testname2", testUser2).getTags().contains("sell"));
    }

    @Test
    void createSwapProductTest(){
        //TODO write automated tests for method createSwapProduct(String name, String description, String price, User merchant), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2, testOldShopOrSwap3, testOldShopOrSwap4;
        User testUser1, testUser2, testUser3;
        Product testProduct1, testProduct2, testProduct3;
        testUser1 = new User("testname1", "testpassword1");
        testUser2 = new User("testname2", "testpassword2");
        testUser3 = new User("testname3", "testpassword3");

        // Equivalence class: valid product (invalid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap();
        testOldShopOrSwap1.addAccount(testUser2);
        testOldShopOrSwap1.addAccount(testUser3);
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.createSwapProduct("testname1", "testdescription1", "55.55", testUser1));

        // Equivalence class: valid product (invalid case, middle case)
        testOldShopOrSwap2 = new OldShopOrSwap();
        testOldShopOrSwap2.addAccount(testUser1);
        testOldShopOrSwap2.addAccount(testUser2);
        testOldShopOrSwap2.addAccount(testUser3);
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap2.createSwapProduct(" invalid name ", " invalid desc ", " invalid price ", testUser1));

        // Equivalence class: valid product (valid case, border case)
        testOldShopOrSwap3 = new OldShopOrSwap();
        testOldShopOrSwap3.addAccount(testUser1);
        testOldShopOrSwap3.addAccount(testUser2);
        testOldShopOrSwap3.addAccount(testUser3);
        testOldShopOrSwap3.createSwapProduct("testname1", "testdescription1", "55.55", testUser1);
        assertTrue(testOldShopOrSwap3.findProduct("testname1", testUser1).getTags().contains("swap"));

        // Equivalence class: valid product (valid case, middle case)
        testOldShopOrSwap4 = new OldShopOrSwap();
        testOldShopOrSwap4.addAccount(testUser1);
        testOldShopOrSwap4.addAccount(testUser2);
        testOldShopOrSwap4.addAccount(testUser3);
        testOldShopOrSwap4.createSwapProduct("testname1", "testdescription1", "55.55", testUser1);
        testOldShopOrSwap4.createSwapProduct("testname2", "testdescription2", "55.55", testUser2);
        testOldShopOrSwap4.createSwapProduct("testname3", "testdescription3", "55.55", testUser3);
        assertTrue(testOldShopOrSwap4.findProduct("testname2", testUser2).getTags().contains("swap"));
    }

    @Test
    void findProductTest(){
        // write automated tests for method findProduct(String name, User user), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2, testOldShopOrSwap3, testOldShopOrSwap4;
        User testUser1 = new User("testname1", "testpassword1");
        User testUser2 = new User("testname2", "testpassword2");
        Product testProduct1, testProduct2;

        // Equivalence class: product to find does exist (invalid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap();
        testOldShopOrSwap1.addAccount(testUser1);
        testOldShopOrSwap1.createSellProduct("testname1", "testdescription1", "55.55", testUser1);
        assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap1.findProduct("testname2", testUser1));

        // Equivalence class: product to find does exist (invalid case, middle case)
        testOldShopOrSwap2 = new OldShopOrSwap();
        testOldShopOrSwap2.addAccount(testUser1);
        testOldShopOrSwap2.addAccount(testUser2);
        testOldShopOrSwap2.createSellProduct("testname1","testdescription1","55.55", testUser1);
        testOldShopOrSwap2.createSellProduct("testname2","testdescription2","55.55", testUser2);
        testOldShopOrSwap2.createSwapProduct("testname3","testdescription3","55.55", testUser1);
        testOldShopOrSwap2.createSwapProduct("testname4","testdescription4","55.55", testUser2);
        assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap1.findProduct("testname2", testUser1));
        assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap1.findProduct("testname3", testUser2));

        // Equivalence class: product to find does exist (valid case, border case)
        testOldShopOrSwap3 = new OldShopOrSwap();
        testOldShopOrSwap3.addAccount(testUser1);
        testOldShopOrSwap3.createSellProduct("testname1", "testdescription1", "55.55", testUser1);
        assertEquals("testname1", testOldShopOrSwap1.findProduct("testname1", testUser1).getName());

        // Equivalence class: product to find does exist (valid case, middle case)
        testOldShopOrSwap4 = new OldShopOrSwap();
        testOldShopOrSwap4.addAccount(testUser1);
        testOldShopOrSwap4.addAccount(testUser2);
        testOldShopOrSwap4.createSellProduct("testname1","testdescription1","55.55", testUser1);
        testOldShopOrSwap4.createSellProduct("testname2","testdescription2","55.55", testUser2);
        testOldShopOrSwap4.createSwapProduct("testname3","testdescription3","55.55", testUser1);
        testOldShopOrSwap4.createSwapProduct("testname4","testdescription4","55.55", testUser2);
        assertEquals("testname3", testOldShopOrSwap4.findProduct("testname3", testUser1).getName());
        assertEquals("testname4", testOldShopOrSwap4.findProduct("testname4", testUser2).getName());
    }

    @Test
    void getUserProductsTest(){
        // write automated tests for method getUserProducts(User user), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid user (valid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap(testUsers);
        testOldShopOrSwap1.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        assertEquals(0, testOldShopOrSwap1.findUserProducts(testUser2).size());
        assertEquals(1, testOldShopOrSwap1.findUserProducts(testUser1).size());

        // Equivalence class: valid user (valid case, middle case)
        testOldShopOrSwap2 = new OldShopOrSwap(testUsers);
        testOldShopOrSwap2.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        testOldShopOrSwap2.createSwapProduct("testproduct2", "testdescription2", "20", testUser1);
        testOldShopOrSwap2.createSellProduct("testproduct3", "testdescription3", "20", testUser1);
        testOldShopOrSwap2.createSwapProduct("testproduct4", "testdescription4", "20", testUser1);
        assertEquals(0, testOldShopOrSwap2.findUserProducts(testUser2).size());
        assertEquals(4, testOldShopOrSwap2.findUserProducts(testUser1).size());
    }

    @Test
    void getSellProductsTest(){
        // write automated tests for method getSellProducts(), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid user (valid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap(testUsers);
        testOldShopOrSwap1.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        assertEquals(1, testOldShopOrSwap1.findSellProducts().size());

        // Equivalence class: valid user (valid case, middle case)
        testOldShopOrSwap2 = new OldShopOrSwap(testUsers);
        testOldShopOrSwap2.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        testOldShopOrSwap2.createSwapProduct("testproduct2", "testdescription2", "20", testUser1);
        testOldShopOrSwap2.createSellProduct("testproduct3", "testdescription3", "20", testUser1);
        testOldShopOrSwap2.createSwapProduct("testproduct4", "testdescription4", "20", testUser1);
        assertEquals(2, testOldShopOrSwap2.findSellProducts().size());
    }

    @Test
    void getSwapProductsTest(){
        // write automated tests for method getSwapProducts(), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid user (valid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap(testUsers);
        testOldShopOrSwap1.createSwapProduct("testproduct1", "testdescription1", "20", testUser1);
        assertEquals(1, testOldShopOrSwap1.findSwapProducts().size());

        // Equivalence class: valid user (valid case, middle case)
        testOldShopOrSwap2 = new OldShopOrSwap(testUsers);
        testOldShopOrSwap2.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        testOldShopOrSwap2.createSwapProduct("testproduct2", "testdescription2", "20", testUser1);
        testOldShopOrSwap2.createSellProduct("testproduct3", "testdescription3", "20", testUser1);
        testOldShopOrSwap2.createSwapProduct("testproduct4", "testdescription4", "20", testUser1);
        assertEquals(2, testOldShopOrSwap2.findSwapProducts().size());
    }

    @Test
    void removeProductsTest(){
        // write automated tests for method removeProducts(), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2, testOldShopOrSwap3;
        User testUser1 = new User("testuser1", "testpassword1");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);

        // Equivalence class: removes a sellproduct with only one product in shoporswap
        testOldShopOrSwap1 = new OldShopOrSwap(testUsers);
        testOldShopOrSwap1.createSwapProduct("testproduct1", "testdescription1", "20", testUser1);
        assertEquals(1, testOldShopOrSwap1.findSwapProducts().size());
        testOldShopOrSwap1.removeSellProduct(testOldShopOrSwap1.findProduct("testproduct1",testUser1));
        assertEquals(0, testOldShopOrSwap1.findSellProducts().size());

        // Equivalence class: removes a sellproduct with multiple products in shoporswap
        testOldShopOrSwap2 = new OldShopOrSwap(testUsers);
        testOldShopOrSwap2.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        testOldShopOrSwap2.createSellProduct("testproduct3", "testdescription3", "20", testUser1);
        assertEquals(2, testOldShopOrSwap2.findSellProducts().size());
        testOldShopOrSwap2.removeSellProduct(testOldShopOrSwap2.findProduct("testproduct1",testUser1));
        assertEquals(1, testOldShopOrSwap2.findSellProducts().size());

        // Equivalence class: removes a sellproduct with no products in shoporswap
        testOldShopOrSwap3 = new OldShopOrSwap(testUsers);
        assertThrows(NoSuchElementException.class ,()-> testOldShopOrSwap3.removeSellProduct(testOldShopOrSwap2.findProduct("testProduct1",testUser1)));

    }


    @Test
    void getUserList(){
        // write automated tests for method getUserList(), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid number of users (valid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap();
        assertEquals(0, testOldShopOrSwap1.getUserList().size());

        // Equivalence class: valid number of users (valid case, middle case)
        testOldShopOrSwap2 = new OldShopOrSwap(testUsers);
        assertEquals(2, testOldShopOrSwap2.getUserList().size());
    }

    @Test
    void getProductList(){
        // write automated tests for method getProductList(), then implement corresponding methods to these tests
        OldShopOrSwap testOldShopOrSwap1, testOldShopOrSwap2;
        User testUser1 = new User("testuser1", "testpassword1");
        User testUser2 = new User("testuser2", "testpassword2");
        List<User> testUsers = new ArrayList<User>();
        testUsers.add(testUser1);
        testUsers.add(testUser2);

        // Equivalence class: valid number of users (valid case, border case)
        testOldShopOrSwap1 = new OldShopOrSwap();
        assertEquals(0, testOldShopOrSwap1.getProductList().size());

        // Equivalence class: valid number of users (valid case, middle case)
        testOldShopOrSwap2 = new OldShopOrSwap(testUsers);
        testOldShopOrSwap2.createSellProduct("testproduct1", "testdescription1", "20", testUser1);
        testOldShopOrSwap2.createSwapProduct("testproduct2", "testdescription2", "20", testUser1);
        testOldShopOrSwap2.createSellProduct("testproduct3", "testdescription3", "20", testUser2);
        testOldShopOrSwap2.createSwapProduct("testproduct4", "testdescription4", "20", testUser2);
        assertEquals(4, testOldShopOrSwap2.getProductList().size());
    }

    @Test
    void isValidUserListTest(){
        // write automated tests for method isValidUserList(List<User> users), then implement corresponding methods to these tests
        User testUser1, testUser2, testUser3, testUser4, testUser5;
        List<User> testUserList1, testUserList2, testUserList3, testUserList4, testUserList5, testUserList6;

        // Equivalence class: Users list cannot be empty (invalid case, border case)
        testUserList1 = new ArrayList<User>();
        assertFalse(OldShopOrSwap.isValidUserList(testUserList1));

        // Equivalence class: Users list cannot have multiple Users with the same account name (invalid case, border case)
        testUserList2 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser1", "testpassword2");
        testUserList2.add(testUser1);
        testUserList2.add(testUser2);
        assertFalse(OldShopOrSwap.isValidUserList(testUserList2));

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
        assertFalse(OldShopOrSwap.isValidUserList(testUserList3));

        // Equivalence class: Users list cannot be empty (valid case, border case)
        testUserList4 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUserList4.add(testUser1);
        assertTrue(OldShopOrSwap.isValidUserList(testUserList4));

        // Equivalence class: Users list cannot be empty (valid case, middle case), Users list cannot have mutliple Users with the same account name (valid case, border case)
        testUserList5 = new ArrayList<User>();
        testUser1 = new User("testuser1", "testpassword1");
        testUser2 = new User("testuser2", "testpassword2");
        testUserList5.add(testUser1);
        testUserList5.add(testUser2);
        assertTrue(OldShopOrSwap.isValidUserList(testUserList5));

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
        assertTrue(OldShopOrSwap.isValidUserList(testUserList6));

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
        assertFalse(OldShopOrSwap.isValidProductList(testProductList1, testUserList1));

        // Equivalence class: Products list cannot have multiple Products with the same name (invalid cases, middle cases)
        testProductList2 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname1", "testdescription1", testUser1);
        testProductList2.add(testProduct1);
        testProductList2.add(testProduct2);
        assertFalse(OldShopOrSwap.isValidProductList(testProductList2, testUserList1));

        testProductList3 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname1", "testdescription1", testUser2);
        testProductList3.add(testProduct1);
        testProductList3.add(testProduct2);
        assertFalse(OldShopOrSwap.isValidProductList(testProductList3, testUserList1));

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
        assertFalse(OldShopOrSwap.isValidProductList(testProductList4, testUserList1));

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
        assertFalse(OldShopOrSwap.isValidProductList(testProductList5, testUserList1));

        // Equivalence class: Products list cannot have Products with merchants not in the Users list (invalid cases, border cases)
        testProductList6 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser6);
        testProductList6.add(testProduct1);
        assertFalse(OldShopOrSwap.isValidProductList(testProductList6, testUserList1));

        testProductList7 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", null);
        testProductList7.add(testProduct1);
        assertFalse(OldShopOrSwap.isValidProductList(testProductList7, testUserList1));

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
        assertFalse(OldShopOrSwap.isValidProductList(testProductList8, testUserList1));

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
        assertFalse(OldShopOrSwap.isValidProductList(testProductList9, testUserList1));

        // Equivalence class: Products list cannot be empty (valid case, border case)
        testProductList10 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProductList10.add(testProduct1);
        assertTrue(OldShopOrSwap.isValidProductList(testProductList10, testUserList1));

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
        assertTrue(OldShopOrSwap.isValidProductList(testProductList11, testUserList1));

        // Equivalence class: Products list cannot have multiple Products with the same name (valid cases, border cases),
        // Products list cannot have Products with merchants not in the Users list (invalid cases, border cases)
        testProductList12 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription1", testUser1);
        testProductList12.add(testProduct1);
        testProductList12.add(testProduct2);
        assertTrue(OldShopOrSwap.isValidProductList(testProductList12, testUserList1));

        testProductList13 = new ArrayList<Product>();
        testProduct1 = new Product("testname1", "testdescription1", testUser1);
        testProduct2 = new Product("testname2", "testdescription1", testUser2);
        testProductList13.add(testProduct1);
        testProductList13.add(testProduct2);
        assertTrue(OldShopOrSwap.isValidProductList(testProductList13, testUserList1));

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
        assertTrue(OldShopOrSwap.isValidProductList(testProductList14, testUserList1));

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
        assertTrue(OldShopOrSwap.isValidProductList(testProductList15, testUserList1));

    }

    @Test
    void swapProductsTest(){
        OldShopOrSwap testOldShopOrSwap1 = new OldShopOrSwap();
        testOldShopOrSwap1.createAccount("user1", "pass1");
        testOldShopOrSwap1.createAccount("user2", "pass2");
        testOldShopOrSwap1.createSwapProduct("product1", "product 1 to swap", "50", testOldShopOrSwap1.findAccount("user1"));
        testOldShopOrSwap1.createSwapProduct("product2", "product 2 to swap", "50", testOldShopOrSwap1.findAccount("user1"));
        testOldShopOrSwap1.createSwapProduct("product3", "product 3 to swap", "50", testOldShopOrSwap1.findAccount("user2"));
        testOldShopOrSwap1.createSwapProduct("product4", "product 4 to swap", "50", testOldShopOrSwap1.findAccount("user2"));
        testOldShopOrSwap1.createSellProduct("product5", "product 5 to sell", "50", testOldShopOrSwap1.findAccount("user1"));
        testOldShopOrSwap1.createSellProduct("product6", "product 6 to sell", "50", testOldShopOrSwap1.findAccount("user1"));
        testOldShopOrSwap1.createSellProduct("product7", "product 7 to sell", "50", testOldShopOrSwap1.findAccount("user2"));
        testOldShopOrSwap1.createSellProduct("product8", "product 8 to sell", "50", testOldShopOrSwap1.findAccount("user2"));
        List<User> testUsers1 = testOldShopOrSwap1.getUserList();
        List<Product> testSwapProducts1 = testOldShopOrSwap1.findSwapProducts();
        List<Product> testSellProducts1 = testOldShopOrSwap1.findSellProducts();
        // equivalence class: cannot swap between the same product, invalid, exact
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.swapProducts(testSwapProducts1.get(0), testSwapProducts1.get(0)));
        // equivalence class: cannot swap between the same user, invalid, exact
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.swapProducts(testSwapProducts1.get(0), testSwapProducts1.get(1)));
        // equivalence class: cannot swap a product for sale
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.swapProducts(testSellProducts1.get(0), testSwapProducts1.get(0)));
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.swapProducts(testSwapProducts1.get(0), testSellProducts1.get(0)));
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.swapProducts(testSellProducts1.get(0), testSellProducts1.get(1)));
        // equivalence class: cannot swap non-existing products and non-existing users in the ShopOrSwap program
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.swapProducts(
                new Product("invalid product 1", "description", new User("user3", "pass3")),
                testSwapProducts1.get(0)));
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.swapProducts(
                testSwapProducts1.get(0),
                new Product("invalid product 1", "description", new User("user3", "pass3"))));
        assertThrows(IllegalArgumentException.class, ()-> testOldShopOrSwap1.swapProducts(
                new Product("invalid product 1", "description", new User("user3", "pass3")),
                new Product("invalid product 2", "description", new User("user4", "pass4"))));
        // valid case
        testOldShopOrSwap1.swapProducts(testSwapProducts1.get(0), testSwapProducts1.get(2));
        assertNotNull(testOldShopOrSwap1.findProduct(testSwapProducts1.get(0).getName(), testOldShopOrSwap1.findAccount("user2")));
        assertNotNull(testOldShopOrSwap1.findProduct(testSwapProducts1.get(2).getName(), testOldShopOrSwap1.findAccount("user1")));
        assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap1.findProduct(testSwapProducts1.get(0).getName(), testOldShopOrSwap1.findAccount("user1")));
        assertThrows(NoSuchElementException.class, ()-> testOldShopOrSwap1.findProduct(testSwapProducts1.get(2).getName(), testOldShopOrSwap1.findAccount("user2")));
    }

    @Test
    void exitAndJSONConstructorTest() throws Exception{
        OldShopOrSwap testExportOrSwap1, testExportOrSwap2, testExportOrSwap3, testExportOrSwap4, testExportOrSwap5, testExportOrSwap6, testExportOrSwap7;
        String testFile1, testFile2, testFile3, testFile4, testFile5, testFile6, testFile7;
        OldShopOrSwap testImportOrSwap1, testImportOrSwap2, testImportOrSwap3, testImportOrSwap4, testImportOrSwap5, testImportOrSwap6, testImportOrSwap7;
        
        // equivalence class: ShopOrSwap with Product count 0 and User count 0
        testExportOrSwap1 = new OldShopOrSwap();
        testFile1 = "src/test/resources/exitTest1_1.json";
        testExportOrSwap1.exit(testFile1);
        testImportOrSwap1 = new OldShopOrSwap(testFile1);
        assertEquals(0, testImportOrSwap1.getUserList().size());
        assertEquals(0, testImportOrSwap1.getProductList().size());
        assertEquals(0, testImportOrSwap1.findSellProducts().size());
        assertEquals(0, testImportOrSwap1.findSwapProducts().size());

        // equivalence class: ShopOrSwap with Product count 0 and User count 1
        testExportOrSwap2 = new OldShopOrSwap();
        testExportOrSwap2.createAccount("test1", "pass1");
        testFile2 = "src/test/resources/exitTest2_1.json";
        testExportOrSwap2.exit(testFile2);
        testImportOrSwap2 = new OldShopOrSwap(testFile2);
        assertEquals(1, testImportOrSwap2.getUserList().size());
        assertEquals(0, testImportOrSwap2.getProductList().size());
        assertEquals(0, testImportOrSwap2.findSellProducts().size());
        assertEquals(0, testImportOrSwap2.findSwapProducts().size());

        // equivalence class: ShopOrSwap with Product count 0 and User count 5
        testExportOrSwap3 = new OldShopOrSwap();
        testExportOrSwap3.createAccount("test1", "pass1");
        testExportOrSwap3.createAccount("test2", "pass2");
        testExportOrSwap3.createAccount("test3", "pass3");
        testExportOrSwap3.createAccount("test4", "pass4");
        testExportOrSwap3.createAccount("test5", "pass5");
        testFile3 = "src/test/resources/exitTest3_1.json";
        testExportOrSwap3.exit(testFile3);
        testImportOrSwap3 = new OldShopOrSwap(testFile3);
        assertEquals(5, testImportOrSwap3.getUserList().size());
        assertEquals(0, testImportOrSwap3.getProductList().size());
        assertEquals(0, testImportOrSwap3.findSellProducts().size());
        assertEquals(0, testImportOrSwap3.findSwapProducts().size());
        
        // equivalence class: ShopOrSwap with Product count 1 and User count 1
        testExportOrSwap4 = new OldShopOrSwap();
        testExportOrSwap4.createAccount("test1", "pass1");
        testExportOrSwap4.createSellProduct("name 1", "description 1", "50", testExportOrSwap4.findAccount("test1"));
        testFile4 = "src/test/resources/exitTest4_1.json";
        testExportOrSwap4.exit(testFile4);
        testImportOrSwap4 = new OldShopOrSwap(testFile4);
        assertEquals(1, testImportOrSwap4.getUserList().size());
        assertEquals(1, testImportOrSwap4.getProductList().size());
        assertEquals(1, testImportOrSwap4.findSellProducts().size());
        assertEquals(0, testImportOrSwap4.findSwapProducts().size());

        testExportOrSwap4 = new OldShopOrSwap();
        testExportOrSwap4.createAccount("test1", "pass1");
        testExportOrSwap4.createSwapProduct("name 1", "description 1", "50", testExportOrSwap4.findAccount("test1"));
        testFile4 = "src/test/resources/exitTest4_2.json";
        testExportOrSwap4.exit(testFile4);
        testImportOrSwap4 = new OldShopOrSwap(testFile4);
        assertEquals(1, testImportOrSwap4.getUserList().size());
        assertEquals(1, testImportOrSwap4.getProductList().size());
        assertEquals(0, testImportOrSwap4.findSellProducts().size());
        assertEquals(1, testImportOrSwap4.findSwapProducts().size());
        
        // equivalence class: ShopOrSwap with Product count 1 and User count 5
        testExportOrSwap5 = new OldShopOrSwap();
        testExportOrSwap5.createAccount("test1", "pass1");
        testExportOrSwap5.createAccount("test2", "pass2");
        testExportOrSwap5.createAccount("test3", "pass3");
        testExportOrSwap5.createAccount("test4", "pass4");
        testExportOrSwap5.createAccount("test5", "pass5");
        testExportOrSwap5.createSellProduct("name 1", "description 1", "50", testExportOrSwap5.findAccount("test1"));
        testFile5 = "src/test/resources/exitTest5_1.json";
        testExportOrSwap5.exit(testFile5);
        testImportOrSwap5 = new OldShopOrSwap(testFile5);
        assertEquals(5, testImportOrSwap5.getUserList().size());
        assertEquals(1, testImportOrSwap5.getProductList().size());
        assertEquals(1, testImportOrSwap5.findSellProducts().size());
        assertEquals(0, testImportOrSwap5.findSwapProducts().size());

        testExportOrSwap5 = new OldShopOrSwap();
        testExportOrSwap5.createAccount("test1", "pass1");
        testExportOrSwap5.createAccount("test2", "pass2");
        testExportOrSwap5.createAccount("test3", "pass3");
        testExportOrSwap5.createAccount("test4", "pass4");
        testExportOrSwap5.createAccount("test5", "pass5");
        testExportOrSwap5.createSwapProduct("name 1", "description 1", "50", testExportOrSwap5.findAccount("test1"));
        testFile5 = "src/test/resources/exitTest5_2.json";
        testExportOrSwap5.exit(testFile5);
        testImportOrSwap5 = new OldShopOrSwap(testFile5);
        assertEquals(5, testImportOrSwap5.getUserList().size());
        assertEquals(1, testImportOrSwap5.getProductList().size());
        assertEquals(0, testImportOrSwap5.findSellProducts().size());
        assertEquals(1, testImportOrSwap5.findSwapProducts().size());
        
        // equivalence class: ShopOrSwap with Product count 5 and User count 1
        testExportOrSwap6 = new OldShopOrSwap();
        testExportOrSwap6.createAccount("test1", "pass1");
        testExportOrSwap6.createSellProduct("name 1", "description 1", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSellProduct("name 2", "description 2", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSellProduct("name 3", "description 3", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSellProduct("name 4", "description 4", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSellProduct("name 5", "description 5", "50", testExportOrSwap6.findAccount("test1"));
        testFile6 = "src/test/resources/exitTest6_1.json";
        testExportOrSwap6.exit(testFile6);
        testImportOrSwap6 = new OldShopOrSwap(testFile6);
        assertEquals(1, testImportOrSwap6.getUserList().size());
        assertEquals(5, testImportOrSwap6.getProductList().size());
        assertEquals(5, testImportOrSwap6.findSellProducts().size());
        assertEquals(0, testImportOrSwap6.findSwapProducts().size());

        testExportOrSwap6 = new OldShopOrSwap();
        testExportOrSwap6.createAccount("test1", "pass1");
        testExportOrSwap6.createSwapProduct("name 1", "description 1", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSwapProduct("name 2", "description 2", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSwapProduct("name 3", "description 3", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSwapProduct("name 4", "description 4", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSwapProduct("name 5", "description 5", "50", testExportOrSwap6.findAccount("test1"));
        testFile6 = "src/test/resources/exitTest6_2.json";
        testExportOrSwap6.exit(testFile6);
        testImportOrSwap6 = new OldShopOrSwap(testFile6);
        assertEquals(1, testImportOrSwap6.getUserList().size());
        assertEquals(5, testImportOrSwap6.getProductList().size());
        assertEquals(0, testImportOrSwap6.findSellProducts().size());
        assertEquals(5, testImportOrSwap6.findSwapProducts().size());

        testExportOrSwap6 = new OldShopOrSwap();
        testExportOrSwap6.createAccount("test1", "pass1");
        testExportOrSwap6.createSellProduct("name 1", "description 1", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSwapProduct("name 2", "description 2", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSellProduct("name 3", "description 3", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSwapProduct("name 4", "description 4", "50", testExportOrSwap6.findAccount("test1"));
        testExportOrSwap6.createSellProduct("name 5", "description 5", "50", testExportOrSwap6.findAccount("test1"));
        testFile6 = "src/test/resources/exitTest6_3.json";
        testExportOrSwap6.exit(testFile6);
        testImportOrSwap6 = new OldShopOrSwap(testFile6);
        assertEquals(1, testImportOrSwap6.getUserList().size());
        assertEquals(5, testImportOrSwap6.getProductList().size());
        assertEquals(3, testImportOrSwap6.findSellProducts().size());
        assertEquals(2, testImportOrSwap6.findSwapProducts().size());
        
        // equivalence class: ShopOrSwap with Product count 5 and User count 5
        testExportOrSwap7 = new OldShopOrSwap();
        testExportOrSwap7.createAccount("test1", "pass1");
        testExportOrSwap7.createAccount("test2", "pass2");
        testExportOrSwap7.createAccount("test3", "pass3");
        testExportOrSwap7.createAccount("test4", "pass4");
        testExportOrSwap7.createAccount("test5", "pass5");
        testExportOrSwap7.createSellProduct("name 1", "description 1", "50", testExportOrSwap7.findAccount("test1"));
        testExportOrSwap7.createSellProduct("name 2", "description 2", "50", testExportOrSwap7.findAccount("test2"));
        testExportOrSwap7.createSellProduct("name 3", "description 3", "50", testExportOrSwap7.findAccount("test3"));
        testExportOrSwap7.createSellProduct("name 4", "description 4", "50", testExportOrSwap7.findAccount("test4"));
        testExportOrSwap7.createSellProduct("name 5", "description 5", "50", testExportOrSwap7.findAccount("test5"));
        testFile7 = "src/test/resources/exitTest7_1.json";
        testExportOrSwap7.exit(testFile7);
        testImportOrSwap7 = new OldShopOrSwap(testFile7);
        assertEquals(5, testImportOrSwap7.getUserList().size());
        assertEquals(5, testImportOrSwap7.getProductList().size());
        assertEquals(5, testImportOrSwap7.findSellProducts().size());
        assertEquals(0, testImportOrSwap7.findSwapProducts().size());

        testExportOrSwap7 = new OldShopOrSwap();
        testExportOrSwap7.createAccount("test1", "pass1");
        testExportOrSwap7.createAccount("test2", "pass2");
        testExportOrSwap7.createAccount("test3", "pass3");
        testExportOrSwap7.createAccount("test4", "pass4");
        testExportOrSwap7.createAccount("test5", "pass5");
        testExportOrSwap7.createSwapProduct("name 1", "description 1", "50", testExportOrSwap7.findAccount("test1"));
        testExportOrSwap7.createSwapProduct("name 2", "description 2", "50", testExportOrSwap7.findAccount("test2"));
        testExportOrSwap7.createSwapProduct("name 3", "description 3", "50", testExportOrSwap7.findAccount("test3"));
        testExportOrSwap7.createSwapProduct("name 4", "description 4", "50", testExportOrSwap7.findAccount("test4"));
        testExportOrSwap7.createSwapProduct("name 5", "description 5", "50", testExportOrSwap7.findAccount("test5"));
        testFile7 = "src/test/resources/exitTest7_2.json";
        testExportOrSwap7.exit(testFile7);
        testImportOrSwap7 = new OldShopOrSwap(testFile7);
        assertEquals(5, testImportOrSwap7.getUserList().size());
        assertEquals(5, testImportOrSwap7.getProductList().size());
        assertEquals(0, testImportOrSwap7.findSellProducts().size());
        assertEquals(5, testImportOrSwap7.findSwapProducts().size());

        testExportOrSwap7 = new OldShopOrSwap();
        testExportOrSwap7.createAccount("test1", "pass1");
        testExportOrSwap7.createAccount("test2", "pass2");
        testExportOrSwap7.createAccount("test3", "pass3");
        testExportOrSwap7.createAccount("test4", "pass4");
        testExportOrSwap7.createAccount("test5", "pass5");
        testExportOrSwap7.createSwapProduct("name 1", "description 1", "50", testExportOrSwap7.findAccount("test1"));
        testExportOrSwap7.createSellProduct("name 2", "description 2", "50", testExportOrSwap7.findAccount("test2"));
        testExportOrSwap7.createSwapProduct("name 3", "description 3", "50", testExportOrSwap7.findAccount("test3"));
        testExportOrSwap7.createSellProduct("name 4", "description 4", "50", testExportOrSwap7.findAccount("test4"));
        testExportOrSwap7.createSwapProduct("name 5", "description 5", "50", testExportOrSwap7.findAccount("test5"));
        testFile7 = "src/test/resources/exitTest7_3.json";
        testExportOrSwap7.exit(testFile7);
        testImportOrSwap7 = new OldShopOrSwap(testFile7);
        assertEquals(5, testImportOrSwap7.getUserList().size());
        assertEquals(5, testImportOrSwap7.getProductList().size());
        assertEquals(2, testImportOrSwap7.findSellProducts().size());
        assertEquals(3, testImportOrSwap7.findSwapProducts().size());
    }

}
