import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountFactoryTest {

    /**
     * Automated tests for AccountFactory.getAccount method
     */
    @Test
    void getAccountTest(){

        Account testAdminAccount1, testAdminAccount2, testAdminAccount3;
        Account testClientAccount1, testClientAccount2, testClientAccount3;

        AccountFactory testAccountFactory = new AccountFactory();

        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount(""));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("cclient"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("clientt"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("ccliiennt"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount(" client"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("client "));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("@#$%^&*!"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("C lient"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("Clien t"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("C lien t"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("clien"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("lient"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("lien"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("aadmin"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("adminn"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("aadmminn"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount(" admin"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("admin "));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount(" admin "));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("!*&^%$#@"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("A dmin"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("Admin "));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("A dmi n"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("admi"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("dmin"));
        assertThrows(IllegalArgumentException.class, ()-> testAccountFactory.getAccount("dmi"));

        testAdminAccount1 = testAccountFactory.getAccount("admin");
        assertEquals("DefaultAdmin", testAdminAccount1.getAccountName());
        assertEquals("DefaultPassword", testAdminAccount1.getAccountPassword());
        assertFalse(testAdminAccount1.getIsFrozen());

        testAdminAccount2 = testAccountFactory.getAccount("ADMIN");
        assertEquals("DefaultAdmin", testAdminAccount2.getAccountName());
        assertEquals("DefaultPassword", testAdminAccount2.getAccountPassword());
        assertFalse(testAdminAccount2.getIsFrozen());

        testAdminAccount3 = testAccountFactory.getAccount("aDmIn");
        assertEquals("DefaultAdmin", testAdminAccount3.getAccountName());
        assertEquals("DefaultPassword", testAdminAccount3.getAccountPassword());
        assertFalse(testAdminAccount3.getIsFrozen());

        testClientAccount1 = testAccountFactory.getAccount("client");
        assertEquals("DefaultClient", testClientAccount1.getAccountName());
        assertEquals("DefaultPassword", testClientAccount1.getAccountPassword());
        assertFalse(testClientAccount1.getIsFrozen());
        assertEquals(0, ((Client) testClientAccount1).getMyProductList().size());

        testClientAccount2 = testAccountFactory.getAccount("CLIENT");
        assertEquals("DefaultClient", testClientAccount2.getAccountName());
        assertEquals("DefaultPassword", testClientAccount2.getAccountPassword());
        assertFalse(testClientAccount2.getIsFrozen());
        assertEquals(0, ((Client) testClientAccount2).getMyProductList().size());

        testClientAccount3 = testAccountFactory.getAccount("ClIeNt");
        assertEquals("DefaultClient", testClientAccount3.getAccountName());
        assertEquals("DefaultPassword", testClientAccount3.getAccountPassword());
        assertFalse(testClientAccount3.getIsFrozen());
        assertEquals(0, ((Client) testClientAccount3).getMyProductList().size());

    }

}
