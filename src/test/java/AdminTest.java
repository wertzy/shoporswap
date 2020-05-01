import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    /**
     * Automated tests for Admin constructor methods:
     * - 0 parameters (default)
     * - 2 parameters
     */
    @Test
    void constructorsTest(){
        Admin testAdmin1, testAdmin2;
        testAdmin1 = new Admin();
        assertEquals("DefaultAdmin", testAdmin1.getAccountName());
        assertEquals("DefaultPassword", testAdmin1.getAccountPassword());

        testAdmin2 = new Admin("test1", "pass1");
        assertEquals("test1", testAdmin2.getAccountName());
        assertEquals("pass1", testAdmin2.getAccountPassword());

        List<String> invalidAccountNames = Arrays.asList(
                "",
                "des mond",
                "a#b",
                "%&l-()d"
        );

        List<String> invalidAccountPasswords = Arrays.asList(
                "",
                "des mond"
        );

        for(String invalidName : invalidAccountNames){
            assertThrows(IllegalArgumentException.class, ()-> new Admin(invalidName, "pass1"));
            for(String invalidPassword : invalidAccountPasswords){
                assertThrows(IllegalArgumentException.class, ()-> new Admin(invalidName, invalidPassword));
                assertThrows(IllegalArgumentException.class, ()-> new Admin("test1", invalidPassword));
            }
        }
    }

    /**
     * Automated tests for Admin.freezeAccount method
     */
    @Test
    void freezeAccountTest(){
        Admin testAdmin1 = new Admin("testAdmin1", "pass1");
        Client testClient1 = new Client("testClient1", "pass2");

        testAdmin1.freezeAccount(testClient1);
        assertTrue(testClient1.getIsFrozen());
        testAdmin1.freezeAccount(testClient1);
        assertTrue(testClient1.getIsFrozen());
    }

    /**
     * Automated tests for Admin.unfreezeAccount method
     */
    @Test
    void unfreezeAccountTest(){
        Admin testAdmin1 = new Admin("testAdmin1", "pass1");
        Client testClient1 = new Client("testClient1", "pass2");

        testAdmin1.freezeAccount(testClient1);
        assertTrue(testClient1.getIsFrozen());
        testAdmin1.unfreezeAccount(testClient1);
        assertFalse(testClient1.getIsFrozen());
        testAdmin1.unfreezeAccount(testClient1);
        assertFalse(testClient1.getIsFrozen());
    }

}
