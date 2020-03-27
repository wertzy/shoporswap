import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class userTest {

    @Test
    void constructorTest(){
        user user= new user();
    }

    @Test
    void isAccountNameValidTest() {
        //True Tests
        assertTrue(user.isAccountNameValid("desmond"));
        //EC: Standard valid emails. This is not a border case.

        //False Tests
        assertFalse(user.isAccountNameValid(""));
        assertFalse(user.isAccountNameValid("des mond"));
    }

    @Test
    void isPasswordValidTest() {
        //True Tests
        assertTrue(user.isPasswordValid("desmond"));
        assertTrue(user.isPasswordValid("deslee123"));
        assertTrue(user.isPasswordValid("$$@richb0i$$"));
        //EC: Standard name should allow numbers and any character

        //False Tests
        assertFalse(user.isPasswordValid(""));
        assertFalse(user.isPasswordValid("des mond"));
        //accounts cannot have spaces in them
    }

    @Test
    void isValidRatingTest(){
        assertTrue(user.isRatingValid(5.0));
        //EC: Max value of rating is 5 out of 5 stars. This is a border case
        assertTrue(user.isRatingValid(0.0));
        //EC: Min value of rating is 0 out of 5 stars. This is a border case
        assertTrue(user.isRatingValid(2.5));
        //EC: rating can be one decimal place
        assertTrue(user.isRatingValid(1.36));
        //EC: rating can be two decimal places

        assertFalse(user.isRatingValid(-5.0));
        //EC: Rating cannot be negative/ below zero
        assertFalse(user.isRatingValid(5.1));
        //EC: Rating cannot be higher than 5
        assertFalse(user.isRatingValid(4.523));
        assertFalse(user.isRatingValid(4.12355));
        //EC: Rating cannot be more than 3 decimal places or more
    }
}
