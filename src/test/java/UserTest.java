import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void constructorTest(){
        User user1 = new User("desmond", "desmond");
        assertEquals("desmond", user1.getAccountName());
        assertEquals("desmond", user1.getPassword());

        assertThrows(IllegalArgumentException.class, ()-> new User("des mond", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new User("", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new User("desmond", ""));
        assertThrows(IllegalArgumentException.class, ()-> new User("desmond", "des mond"));
    }

    @Test
    void isAccountNameValidTest() {
        //True Tests
        assertTrue(User.isAccountNameValid("desmond"));
        //EC: Standard valid emails. This is not a border case.

        //False Tests
        assertFalse(User.isAccountNameValid(""));
        assertFalse(User.isAccountNameValid("des mond"));
    }

    @Test
    void isPasswordValidTest() {
        //True Tests
        assertTrue(User.isPasswordValid("desmond"));
        assertTrue(User.isPasswordValid("deslee123"));
        assertTrue(User.isPasswordValid("$$@richb0i$$"));
        //EC: Standard name should allow numbers and any character

        //False Tests
        assertFalse(User.isPasswordValid(""));
        assertFalse(User.isPasswordValid("des mond"));
        //accounts cannot have spaces in them
    }

    @Test
    void isValidRatingTest(){
        assertTrue(User.isRatingValid(5.0));
        //EC: Max value of rating is 5 out of 5 stars. This is a border case
        assertTrue(User.isRatingValid(0.0));
        //EC: Min value of rating is 0 out of 5 stars. This is a border case
        assertTrue(User.isRatingValid(2.5));
        //EC: rating can be one decimal place
        assertTrue(User.isRatingValid(1.36));
        //EC: rating can be two decimal places

        assertFalse(User.isRatingValid(-5.0));
        //EC: Rating cannot be negative/ below zero
        assertFalse(User.isRatingValid(5.1));
        //EC: Rating cannot be higher than 5
        assertFalse(User.isRatingValid(4.523));
        assertFalse(User.isRatingValid(4.12355));
        //EC: Rating cannot be more than 3 decimal places or more
    }

    @Test
    void findTest(){
        User user=new User("desmond","desmond");
        assertThrows(NoSuchElementException.class,()->user.find("tee shirt"));

        User user1=new User("testuser","testuser2");
        Product testProduct=new Product("red tee","description",user1);
        User.addClothing("red tee","description",user1);
        assertEquals("red tee",user1.find("red tee").getName());

    }


    @Test
    void sellTest(){
        User user1=new User("testuser","testuser2");
        user1.sell("red tee","red tee from hm",user1);
        user1.sell("blue tee","blue tee from hm",user1);
        user1.sell("green tee","green tee from hm",user1);
        assertEquals(3,user1.productList.size());
    }

    @Test
    void buyTest(){
        User user=new User("desmond","desmond");
        User user1=new User("testuser","testuser2");
        user1.sell("red tee","red tee from hm",user1);
        user1.sell("blue tee","blue tee from hm",user1);
        user1.sell("green tee","green tee from hm",user1);
        user.buy("green tee",user1);

        assertThrows(NoSuchElementException.class,()->user.buy("yellow tee",user1));

    }

    @Test
    void ratingTest(){
        User user=new User("desmond","desmond");
        User user1=new User("testuser","testuser2");
        User user2=new User("testuser","testuser");
        user1.rate(user,5);
        user2.rate(user,1);
        assertEquals(3,user.getRating());

        assertThrows(IllegalArgumentException.class,()->user.rate(user1,6));
        assertThrows(IllegalArgumentException.class,()->user.rate(user1,0));


    }

    @Test
    void walletTest(){
        User user=new User("testuser","testuser");
        user.walletAdd(100);
        assertEquals(100,user.getWallet());
        user.walletSubtract(50);
        assertEquals(50,user.getWallet());

        assertThrows(IllegalArgumentException.class,()->user.walletAdd(7.891));
        assertThrows(IllegalArgumentException.class,()->user.walletAdd(-1));

        assertThrows(IllegalArgumentException.class,()->user.walletSubtract(7.891));
        assertThrows(IllegalArgumentException.class,()->user.walletSubtract(51));
        assertThrows(IllegalArgumentException.class,()->user.walletSubtract(-1));

    }

}
