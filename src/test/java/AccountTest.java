import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    /**
     * Automated tests for Account mutator methods:
     * - Account.getAccountName
     * - Account.getAccountPassword
     * - Account.getIsFrozen
     * - Account.setAccountName
     * - Account.setAccountPassword
     * - Account.setIsFrozen
     */
    @Test
    void mutatorsTests(){

        Account testAccount1, testAccount2;
        
        testAccount1 = new Client();
        assertEquals("DefaultClient", testAccount1.getAccountName());
        assertEquals("DefaultPassword", testAccount1.getAccountPassword());
        assertFalse(testAccount1.getIsFrozen());
        testAccount1.setAccountName("test1");
        testAccount1.setAccountPassword("pass1");
        testAccount1.setIsFrozen(true);
        assertEquals("test1", testAccount1.getAccountName());
        assertEquals("pass1", testAccount1.getAccountPassword());
        assertTrue(testAccount1.getIsFrozen());
        
        testAccount2 = new Admin();
        assertEquals("DefaultAdmin", testAccount2.getAccountName());
        assertEquals("DefaultPassword", testAccount2.getAccountPassword());
        assertFalse(testAccount2.getIsFrozen());
        testAccount2.setAccountName("test2");
        testAccount2.setAccountPassword("pass2");
        testAccount2.setIsFrozen(true);
        assertEquals("test2", testAccount2.getAccountName());
        assertEquals("pass2", testAccount2.getAccountPassword());
        assertTrue(testAccount2.getIsFrozen());
        
    }

    /**
     * Automated tests for Account.isValidAccountName method
     */
    @Test
    void isValidAccountNameTest(){
        //True Tests
        assertTrue(Account.isValidAccountName("desmond"));
        assertFalse(Account.isValidAccountName("desmond@email.com"));
        //False Tests
        assertFalse(Account.isValidAccountName(""));
        assertFalse(Account.isValidAccountName("des mond"));
        assertFalse(Account.isValidAccountName("a#b"));
        assertFalse(Account.isValidAccountName("%&l-()d"));
    }

    /**
     * Automated tests for Account.isValidAccountPassword method
     */
    @Test
    void isValidAccountPasswordTest(){
        //True Tests
        assertTrue(Account.isValidAccountPassword("desmond"));
        assertTrue(Account.isValidAccountPassword("deslee123"));
        assertTrue(Account.isValidAccountPassword("$$@richb0i$$"));
        //EC: Standard name should allow numbers and any character

        //False Tests
        assertFalse(Account.isValidAccountPassword(""));
        assertFalse(Account.isValidAccountPassword("des mond"));
        //accounts cannot have spaces in them
    }

}
