import org.junit.jupiter.api.Test;

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
}