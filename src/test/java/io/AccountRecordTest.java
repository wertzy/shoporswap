package io;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import shoporswap.*;
import util.JsonUtil;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AccountRecordTest {

    /**
     * Automated tests for constructor methods of AccountRecord
     */
    @Test
    void constructorsTests(){

        AccountRecord testAccountRecord1, testAccountRecord2, testAccountRecord3, testAccountRecord4, testAccountRecord5;

        assertThrows(IllegalArgumentException.class, ()-> new AccountRecord(null));

        testAccountRecord1 = new AccountRecord();
        assertEquals("DefaultName", testAccountRecord1.getAccountName());
        assertEquals("DefaultPassword", testAccountRecord1.getAccountPassword());
        assertFalse(testAccountRecord1.getIsFrozen());
        assertEquals(0, testAccountRecord1.accessMyOwnedSellProducts().size());
        assertEquals(0, testAccountRecord1.accessMyOwnedSwapProducts().size());
        assertEquals(0, testAccountRecord1.accessMySellStorefronts().size());
        assertEquals(0, testAccountRecord1.accessMySwapStorefronts().size());

        Account testClient1 = new Client("test1", "pass1");
        testAccountRecord2 = new AccountRecord(testClient1);
        assertEquals("test1", testAccountRecord2.getAccountName());
        assertEquals("pass1", testAccountRecord2.getAccountPassword());
        assertFalse(testAccountRecord2.getIsFrozen());
        assertEquals(0, testAccountRecord2.accessMyOwnedSellProducts().size());
        assertEquals(0, testAccountRecord2.accessMyOwnedSwapProducts().size());
        assertEquals(0, testAccountRecord2.accessMySellStorefronts().size());
        assertEquals(0, testAccountRecord2.accessMySwapStorefronts().size());

        Account testClient2 = new Client();
        testAccountRecord4 = new AccountRecord(testClient2);
        assertEquals("DefaultClient", testAccountRecord4.getAccountName());
        assertEquals("DefaultPassword", testAccountRecord4.getAccountPassword());
        assertFalse(testAccountRecord4.getIsFrozen());
        assertEquals(0, testAccountRecord4.accessMyOwnedSellProducts().size());
        assertEquals(0, testAccountRecord4.accessMyOwnedSwapProducts().size());
        assertEquals(0, testAccountRecord4.accessMySellStorefronts().size());
        assertEquals(0, testAccountRecord4.accessMySwapStorefronts().size());

        Account testAdmin1 = new Admin("test1", "pass1");
        testAccountRecord3 = new AccountRecord(testAdmin1);
        assertEquals("test1", testAccountRecord3.getAccountName());
        assertEquals("pass1", testAccountRecord3.getAccountPassword());
        assertFalse(testAccountRecord3.getIsFrozen());
        assertNull(testAccountRecord3.accessMyOwnedSellProducts());
        assertNull(testAccountRecord3.accessMyOwnedSwapProducts());
        assertNull(testAccountRecord3.accessMySellStorefronts());
        assertNull(testAccountRecord3.accessMySwapStorefronts());

        Account testAdmin2 = new Admin();
        testAccountRecord5 = new AccountRecord(testAdmin2);
        assertEquals("DefaultAdmin", testAccountRecord5.getAccountName());
        assertEquals("DefaultPassword", testAccountRecord5.getAccountPassword());
        assertFalse(testAccountRecord5.getIsFrozen());
        assertNull(testAccountRecord5.accessMyOwnedSellProducts());
        assertNull(testAccountRecord5.accessMyOwnedSwapProducts());
        assertNull(testAccountRecord5.accessMySellStorefronts());
        assertNull(testAccountRecord5.accessMySwapStorefronts());

    }

    /**
     * Automated tests for JsonUtil.toJsonString method
     * @throws JsonProcessingException if a specified object cannot be made into a JSON string
     */
    @Test
    void toJsonStringTest() throws JsonProcessingException {

        AccountRecord testAccountRecord1, testAccountRecord2, testAccountRecord4, testAccountRecord3, testAccountRecord5;

        testAccountRecord1 = new AccountRecord();
        String testJsonString1 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"DefaultName\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"DefaultPassword\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : null," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : null," + System.lineSeparator() +
                "  \"ratingsList\" : null," + System.lineSeparator() +
                "  \"wallet\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString1, JsonUtil.toJsonString(testAccountRecord1));

        Account testClient1 = new Client("test1", "pass1");
        testAccountRecord2 = new AccountRecord(testClient1);
        String testJsonString2 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"test1\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : [ ]," + System.lineSeparator() +
                "  \"ratingsList\" : [ ]," + System.lineSeparator() +
                "  \"wallet\" : \"0.0\"" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString2, JsonUtil.toJsonString(testAccountRecord2));

        Account testClient2 = new Client();
        testAccountRecord4 = new AccountRecord(testClient2);
        String testJsonString4 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"DefaultClient\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"DefaultPassword\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : [ ]," + System.lineSeparator() +
                "  \"ratingsList\" : [ ]," + System.lineSeparator() +
                "  \"wallet\" : \"0.0\"" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString4, JsonUtil.toJsonString(testAccountRecord4));

        Account testAdmin1 = new Admin("test1", "pass1");
        testAccountRecord3 = new AccountRecord(testAdmin1);
        String testJsonString3 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"test1\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : null," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : null," + System.lineSeparator() +
                "  \"ratingsList\" : null," + System.lineSeparator() +
                "  \"wallet\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString3, JsonUtil.toJsonString(testAccountRecord3));

        Account testAdmin2 = new Admin();
        testAccountRecord5 = new AccountRecord(testAdmin2);
        String testJsonString5 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"DefaultAdmin\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"DefaultPassword\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : null," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : null," + System.lineSeparator() +
                "  \"ratingsList\" : null," + System.lineSeparator() +
                "  \"wallet\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString5, JsonUtil.toJsonString(testAccountRecord5));

        Account testClient3 = new Client("test3", "pass3");
        Account testClient4 = new Client("test4", "pass4");
        SellProduct testSellProduct1 = new SellProduct("test1", "description1", 50, (Client) testClient4);
        ((Client) testClient3).addSellProduct(testSellProduct1);
        AccountRecord testAccountRecord6 = new AccountRecord(testClient3);
        String testJsonString6 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"test3\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"pass3\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : [ {" + System.lineSeparator() +
                "    \"productType\" : \"shoporswap.SellProduct\"," + System.lineSeparator() +
                "    \"productName\" : \"test1\"," + System.lineSeparator() +
                "    \"productDescription\" : \"description1\"," + System.lineSeparator() +
                "    \"productValue\" : 50.0," + System.lineSeparator() +
                "    \"productMerchantName\" : \"test4\"," + System.lineSeparator() +
                "    \"productTags\" : [ ]" + System.lineSeparator() +
                "  } ]," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : [ ]," + System.lineSeparator() +
                "  \"ratingsList\" : [ ]," + System.lineSeparator() +
                "  \"wallet\" : \"0.0\"" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString6, JsonUtil.toJsonString(testAccountRecord6));

        Account testClient5 = new Client("test5", "pass5");
        ((Client) testClient5).addStorefront(new SellStorefront("sell1", (Client) testClient5));
        AccountRecord testAccountRecord7 = new AccountRecord(testClient5);
        String testJsonString7 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"test5\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"pass5\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : [ {" + System.lineSeparator() +
                "    \"storefrontType\" : \"shoporswap.SellStorefront\"," + System.lineSeparator() +
                "    \"storefrontName\" : \"sell1\"," + System.lineSeparator() +
                "    \"productRecordList\" : [ ]" + System.lineSeparator() +
                "  } ]," + System.lineSeparator() +
                "  \"ratingsList\" : [ ]," + System.lineSeparator() +
                "  \"wallet\" : \"0.0\"" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString7, JsonUtil.toJsonString(testAccountRecord7));
    }

    /**
     * Automated tests for JsonUtil.toJsonString and JsonUtil.fromJsonString methods
     * @throws IOException if the file cannot be written to or read from
     */
    @Test
    void toAndFromJsonFileTest() throws IOException {

        AccountRecord testAccountRecord1, testAccountRecord2, testAccountRecord4, testAccountRecord3, testAccountRecord5;
        String directoryPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

        testAccountRecord1 = new AccountRecord();
        String testJsonString1 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"DefaultName\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"DefaultPassword\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : null," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : null," + System.lineSeparator() +
                "  \"ratingsList\" : null," + System.lineSeparator() +
                "  \"wallet\" : null" + System.lineSeparator() +
                "}";
        String testFileName1 = directoryPath + "AccountRecordTest-toAndFromJsonFileTest-1.json";
        JsonUtil.toJsonFile(testFileName1, testAccountRecord1);
        assertEquals(testJsonString1, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName1, AccountRecord.class)));

        Account testClient1 = new Client("test1", "pass1");
        testAccountRecord2 = new AccountRecord(testClient1);
        String testJsonString2 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"test1\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : [ ]," + System.lineSeparator() +
                "  \"ratingsList\" : [ ]," + System.lineSeparator() +
                "  \"wallet\" : \"0.0\"" + System.lineSeparator() +
                "}";
        String testFileName2 = directoryPath + "AccountRecordTest-toAndFromJsonFileTest-2.json";
        JsonUtil.toJsonFile(testFileName2, testAccountRecord2);
        assertEquals(testJsonString2, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName2, AccountRecord.class)));

        Account testClient2 = new Client();
        testAccountRecord4 = new AccountRecord(testClient2);
        String testJsonString4 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"DefaultClient\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"DefaultPassword\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : [ ]," + System.lineSeparator() +
                "  \"ratingsList\" : [ ]," + System.lineSeparator() +
                "  \"wallet\" : \"0.0\"" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString4, JsonUtil.toJsonString(testAccountRecord4));
        String testFileName4 = directoryPath + "AccountRecordTest-toAndFromJsonFileTest-4.json";
        JsonUtil.toJsonFile(testFileName4, testAccountRecord4);
        assertEquals(testJsonString4, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName4, AccountRecord.class)));

        Account testAdmin1 = new Admin("test1", "pass1");
        testAccountRecord3 = new AccountRecord(testAdmin1);
        String testJsonString3 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"test1\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : null," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : null," + System.lineSeparator() +
                "  \"ratingsList\" : null," + System.lineSeparator() +
                "  \"wallet\" : null" + System.lineSeparator() +
                "}";
        String testFileName3 = directoryPath + "AccountRecordTest-toAndFromJsonFileTest-3.json";
        JsonUtil.toJsonFile(testFileName3, testAccountRecord3);
        assertEquals(testJsonString3, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName3, AccountRecord.class)));

        Account testAdmin2 = new Admin();
        testAccountRecord5 = new AccountRecord(testAdmin2);
        String testJsonString5 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"DefaultAdmin\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"DefaultPassword\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : null," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : null," + System.lineSeparator() +
                "  \"ratingsList\" : null," + System.lineSeparator() +
                "  \"wallet\" : null" + System.lineSeparator() +
                "}";
        String testFileName5 = directoryPath + "AccountRecordTest-toAndFromJsonFileTest-5.json";
        JsonUtil.toJsonFile(testFileName5, testAccountRecord5);
        assertEquals(testJsonString5, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName5, AccountRecord.class)));

        Account testClient5 = new Client("test5", "pass5");
        ((Client) testClient5).addStorefront(new SellStorefront("sell1", (Client) testClient5));
        AccountRecord testAccountRecord7 = new AccountRecord(testClient5);
        String testJsonString7 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"test5\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"pass5\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : [ {" + System.lineSeparator() +
                "    \"storefrontType\" : \"shoporswap.SellStorefront\"," + System.lineSeparator() +
                "    \"storefrontName\" : \"sell1\"," + System.lineSeparator() +
                "    \"productRecordList\" : [ ]" + System.lineSeparator() +
                "  } ]," + System.lineSeparator() +
                "  \"ratingsList\" : [ ]," + System.lineSeparator() +
                "  \"wallet\" : \"0.0\"" + System.lineSeparator() +
                "}";
        String testFileName6 = directoryPath + "AccountRecordTest-toAndFromJsonFileTest-6.json";
        JsonUtil.toJsonFile(testFileName6, testAccountRecord7);
        assertEquals(testJsonString7, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName6, AccountRecord.class)));
    }

    /**
     * Automated test for JsonUtil.listFromJsonFile method
     * @throws IOException if the file cannot be written to or read from
     */
    @Test
    void listFromJsonFileTest() throws IOException {

        AccountRecord testAccountRecord1, testAccountRecord2, testAccountRecord4, testAccountRecord3, testAccountRecord5;
        String directoryPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

        testAccountRecord1 = new AccountRecord();
        String testJsonString1 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"DefaultName\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"DefaultPassword\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : null," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : null," + System.lineSeparator() +
                "  \"ratingsList\" : null," + System.lineSeparator() +
                "  \"wallet\" : null" + System.lineSeparator() +
                "}";

        Account testClient1 = new Client("test1", "pass1");
        testAccountRecord2 = new AccountRecord(testClient1);
        String testJsonString2 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"test1\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : [ ]," + System.lineSeparator() +
                "  \"ratingsList\" : [ ]," + System.lineSeparator() +
                "  \"wallet\" : \"0.0\"" + System.lineSeparator() +
                "}";

        Account testClient2 = new Client();
        testAccountRecord4 = new AccountRecord(testClient2);
        String testJsonString4 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"DefaultClient\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"DefaultPassword\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : [ ]," + System.lineSeparator() +
                "  \"ratingsList\" : [ ]," + System.lineSeparator() +
                "  \"wallet\" : \"0.0\"" + System.lineSeparator() +
                "}";

        Account testAdmin1 = new Admin("test1", "pass1");
        testAccountRecord3 = new AccountRecord(testAdmin1);
        String testJsonString3 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"test1\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : null," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : null," + System.lineSeparator() +
                "  \"ratingsList\" : null," + System.lineSeparator() +
                "  \"wallet\" : null" + System.lineSeparator() +
                "}";

        Account testAdmin2 = new Admin();
        testAccountRecord5 = new AccountRecord(testAdmin2);
        String testJsonString5 = "{" + System.lineSeparator() +
                "  \"accountName\" : \"DefaultAdmin\"," + System.lineSeparator() +
                "  \"accountPassword\" : \"DefaultPassword\"," + System.lineSeparator() +
                "  \"isFrozen\" : false," + System.lineSeparator() +
                "  \"myProductRecords\" : null," + System.lineSeparator() +
                "  \"myStorefrontRecords\" : null," + System.lineSeparator() +
                "  \"ratingsList\" : null," + System.lineSeparator() +
                "  \"wallet\" : null" + System.lineSeparator() +
                "}";

        String testFileName1 = directoryPath + "AccountRecordTest-listFromJsonFileTest-1.json";
        JsonUtil.toJsonFile(testFileName1, Arrays.asList(testAccountRecord1, testAccountRecord2, testAccountRecord4, testAccountRecord3, testAccountRecord5));

        List<AccountRecord> testRecordList = JsonUtil.listFromJsonFile(testFileName1, AccountRecord.class);

        assertEquals(testJsonString1, JsonUtil.toJsonString(testRecordList.get(0)));
        assertEquals(testJsonString2, JsonUtil.toJsonString(testRecordList.get(1)));
        assertEquals(testJsonString4, JsonUtil.toJsonString(testRecordList.get(2)));
        assertEquals(testJsonString3, JsonUtil.toJsonString(testRecordList.get(3)));
        assertEquals(testJsonString5, JsonUtil.toJsonString(testRecordList.get(4)));
    }

    /**
     * Automated tests for AccountRecord.toAccount method
     */
    @Test
    void toAccountTest(){

        AccountRecord testAccountRecord1, testAccountRecord2, testAccountRecord3, testAccountRecord4, testAccountRecord5;

        Account testClient1 = new Client("test1", "pass1");
        testAccountRecord1 = new AccountRecord(testClient1);
        Account testExportAccount1 = testAccountRecord1.toAccount();
        assertEquals(testClient1.getAccountName(), testExportAccount1.getAccountName());
        assertEquals(testClient1.getAccountPassword(), testExportAccount1.getAccountPassword());
        assertEquals(testClient1.getIsFrozen(), testExportAccount1.getIsFrozen());
        assertEquals(((Client) testClient1).getMyStorefronts().size(), ((Client) testExportAccount1).getMyStorefronts().size());
        assertEquals(((Client) testClient1).getMyOwnedProductList().size(), ((Client) testExportAccount1).getMyOwnedProductList().size());

        Account testClient2 = new Client();
        testAccountRecord2 = new AccountRecord(testClient2);
        Account testExportAccount2 = testAccountRecord2.toAccount();
        assertEquals(testClient2.getAccountName(), testExportAccount2.getAccountName());
        assertEquals(testClient2.getAccountPassword(), testExportAccount2.getAccountPassword());
        assertEquals(testClient2.getIsFrozen(), testExportAccount2.getIsFrozen());
        assertEquals(((Client) testClient2).getMyStorefronts().size(), ((Client) testExportAccount2).getMyStorefronts().size());
        assertEquals(((Client) testClient2).getMyOwnedProductList().size(), ((Client) testExportAccount2).getMyOwnedProductList().size());

        Account testAdmin1 = new Admin("test1", "pass1");
        testAccountRecord3 = new AccountRecord(testAdmin1);
        Account testExportAccount3 = testAccountRecord3.toAccount();
        assertEquals(testAdmin1.getAccountName(), testExportAccount3.getAccountName());
        assertEquals(testAdmin1.getAccountPassword(), testExportAccount3.getAccountPassword());
        assertEquals(testAdmin1.getIsFrozen(), testExportAccount3.getIsFrozen());

        Account testAdmin2 = new Admin();
        testAccountRecord4 = new AccountRecord(testAdmin2);
        Account testExportAccount4 = testAccountRecord4.toAccount();
        assertEquals(testAdmin2.getAccountName(), testExportAccount4.getAccountName());
        assertEquals(testAdmin2.getAccountPassword(), testExportAccount4.getAccountPassword());
        assertEquals(testAdmin2.getIsFrozen(), testExportAccount4.getIsFrozen());

        Account testClient3 = new Client();
        ((Client) testClient3).addStorefront(new SellStorefront("sell storefront1", (Client) testClient3));
        testAccountRecord5 = new AccountRecord(testClient3);
        Account testExportAccount5 = testAccountRecord5.toAccount();
        assertEquals(testClient3.getAccountName(), testExportAccount5.getAccountName());
        assertEquals(testClient3.getAccountPassword(), testExportAccount5.getAccountPassword());
        assertEquals(testClient3.getIsFrozen(), testExportAccount5.getIsFrozen());
        assertEquals(((Client) testClient3).getMyStorefronts().size(), ((Client) testExportAccount5).getMyStorefronts().size());
        assertEquals(((Client) testClient3).getMyOwnedProductList().size(), ((Client) testExportAccount5).getMyOwnedProductList().size());
    }

}
