package io;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import shoporswap.*;
import util.JsonUtil;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShopOrSwapRecordTest {

    @Test
    void constructorsTests(){
        ShopOrSwapRecord testShopOrSwapRecord1, testShopOrSwapRecord2, testShopOrSwapRecord3, testShopOrSwapRecord4, testShopOrSwapRecord5, testShopOrSwapRecord6;

        testShopOrSwapRecord1 = new ShopOrSwapRecord();
        assertNull(testShopOrSwapRecord1.getAccountRecords());
        assertNull(testShopOrSwapRecord1.getMessageRecords());

        ShopOrSwap testShopOrSwap1 = new ShopOrSwap();
        testShopOrSwapRecord2 = new ShopOrSwapRecord(testShopOrSwap1);
        assertEquals(0, testShopOrSwapRecord2.getAccountRecords().size());
        assertEquals(0, testShopOrSwapRecord2.getMessageRecords().size());

        ShopOrSwap testShopOrSwap2 = new ShopOrSwap();
        testShopOrSwap2.addAccount("Client", "client1", "pass1");
        testShopOrSwapRecord3 = new ShopOrSwapRecord(testShopOrSwap2);
        assertEquals(1, testShopOrSwapRecord3.getAccountRecords().size());
        assertEquals(0, testShopOrSwapRecord3.getMessageRecords().size());

        ShopOrSwap testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount("Client", "client1", "pass1");
        testShopOrSwap3.addAccount("Client", "client2", "pass2");
        testShopOrSwapRecord4 = new ShopOrSwapRecord(testShopOrSwap3);
        assertEquals(2, testShopOrSwapRecord4.getAccountRecords().size());
        assertEquals(0, testShopOrSwapRecord4.getMessageRecords().size());

        ShopOrSwap testShopOrSwap4 = new ShopOrSwap();
        testShopOrSwap4.addAccount("Client", "client1", "pass1");
        testShopOrSwap4.addAccount("Admin", "admin1", "pass1");
        testShopOrSwapRecord5 = new ShopOrSwapRecord(testShopOrSwap4);
        assertEquals(2, testShopOrSwapRecord5.getAccountRecords().size());
        assertEquals(0, testShopOrSwapRecord5.getMessageRecords().size());

        ShopOrSwap testShopOrSwap5 = new ShopOrSwap();
        testShopOrSwap5.addAccount("Client", "client1", "pass1");
        testShopOrSwap5.addAccount("Admin", "admin1", "pass1");
        testShopOrSwap5.addAccount("Client", "client2", "pass2");
        testShopOrSwap5.addAccount("Admin", "admin2", "pass2");
        testShopOrSwap5.sendMessage("User", "client1", "client2", "subject1", "content1");
        testShopOrSwap5.sendMessage("Report", "client1", "client2", "subject1", "content1");
        testShopOrSwapRecord6 = new ShopOrSwapRecord(testShopOrSwap5);
        assertEquals(4, testShopOrSwapRecord6.getAccountRecords().size());
        assertEquals(3, testShopOrSwapRecord6.getMessageRecords().size());

    }

    @Test
    void toShopOrSwapTest(){

        ShopOrSwapRecord testShopOrSwapRecord1, testShopOrSwapRecord2, testShopOrSwapRecord3, testShopOrSwapRecord4, testShopOrSwapRecord5;

        ShopOrSwap testShopOrSwap1 = new ShopOrSwap();
        testShopOrSwapRecord1 = new ShopOrSwapRecord(testShopOrSwap1);
        ShopOrSwap testExportShopOrSwap1 = testShopOrSwapRecord1.toShopOrSwap();
        assertEquals(testShopOrSwap1.getAccountCollection().size(), testExportShopOrSwap1.getAccountCollection().size());
        assertEquals(testShopOrSwap1.getSystemMessages().size(), testExportShopOrSwap1.getSystemMessages().size());

        ShopOrSwap testShopOrSwap2 = new ShopOrSwap();
        testShopOrSwap2.addAccount("Client", "client1", "pass1");
        testShopOrSwapRecord2 = new ShopOrSwapRecord(testShopOrSwap2);
        ShopOrSwap testExportShopOrSwap2 = testShopOrSwapRecord2.toShopOrSwap();
        assertEquals(testShopOrSwap2.getAccountCollection().size(), testExportShopOrSwap2.getAccountCollection().size());
        assertEquals(testShopOrSwap2.getSystemMessages().size(), testExportShopOrSwap2.getSystemMessages().size());

        ShopOrSwap testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount("Client", "client1", "pass1");
        testShopOrSwap3.addAccount("Client", "client2", "pass2");
        testShopOrSwapRecord3 = new ShopOrSwapRecord(testShopOrSwap3);
        ShopOrSwap testExportShopOrSwap3 = testShopOrSwapRecord3.toShopOrSwap();
        assertEquals(testShopOrSwap3.getAccountCollection().size(), testExportShopOrSwap3.getAccountCollection().size());
        assertEquals(testShopOrSwap3.getSystemMessages().size(), testExportShopOrSwap3.getSystemMessages().size());

        ShopOrSwap testShopOrSwap4 = new ShopOrSwap();
        testShopOrSwap4.addAccount("Client", "client1", "pass1");
        testShopOrSwap4.addAccount("Admin", "admin1", "pass1");
        testShopOrSwapRecord4 = new ShopOrSwapRecord(testShopOrSwap4);
        ShopOrSwap testExportShopOrSwap4 = testShopOrSwapRecord4.toShopOrSwap();
        assertEquals(testShopOrSwap4.getAccountCollection().size(), testExportShopOrSwap4.getAccountCollection().size());
        assertEquals(testShopOrSwap4.getSystemMessages().size(), testExportShopOrSwap4.getSystemMessages().size());

        ShopOrSwap testShopOrSwap5 = new ShopOrSwap();
        testShopOrSwap5.addAccount("Client", "client1", "pass1");
        testShopOrSwap5.addAccount("Admin", "admin1", "pass1");
        testShopOrSwap5.addAccount("Client", "client2", "pass2");
        testShopOrSwap5.addAccount("Admin", "admin2", "pass2");
        testShopOrSwap5.sendMessage("User", "client1", "client2", "subject1", "content1");
        testShopOrSwap5.sendMessage("Report", "client1", "client2", "subject1", "content1");
        testShopOrSwap5.addStorefront("Sell", "sell storefront 1", (Client) testShopOrSwap5.findAccount("client1"));
        testShopOrSwapRecord5 = new ShopOrSwapRecord(testShopOrSwap5);
        ShopOrSwap testExportShopOrSwap5 = testShopOrSwapRecord5.toShopOrSwap();
        assertEquals(testShopOrSwap5.getAccountCollection().size(), testExportShopOrSwap5.getAccountCollection().size());
        assertEquals(testShopOrSwap5.getSystemMessages().size(), testExportShopOrSwap5.getSystemMessages().size());

    }

    @Test
    void toJsonStringTest() throws JsonProcessingException {
        ShopOrSwapRecord testShopOrSwapRecord1, testShopOrSwapRecord2, testShopOrSwapRecord3, testShopOrSwapRecord4, testShopOrSwapRecord5, testShopOrSwapRecord6;

        testShopOrSwapRecord1 = new ShopOrSwapRecord();
        String testJsonString1 =
                "{" + System.lineSeparator() +
                "  \"accountRecords\" : null," + System.lineSeparator() +
                "  \"messageRecords\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString1, JsonUtil.toJsonString(testShopOrSwapRecord1));

        ShopOrSwap testShopOrSwap1 = new ShopOrSwap();
        testShopOrSwapRecord2 = new ShopOrSwapRecord(testShopOrSwap1);
        String testJsonString2 =
                "{" + System.lineSeparator() +
                "  \"accountRecords\" : [ ]," + System.lineSeparator() +
                "  \"messageRecords\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString2, JsonUtil.toJsonString(testShopOrSwapRecord2));

        ShopOrSwap testShopOrSwap2 = new ShopOrSwap();
        testShopOrSwap2.addAccount("Client", "client1", "pass1");
        testShopOrSwapRecord3 = new ShopOrSwapRecord(testShopOrSwap2);
        String testJsonString3 =
                "{" + System.lineSeparator() +
                "  \"accountRecords\" : [ {" + System.lineSeparator() +
                "    \"accountName\" : \"client1\"," + System.lineSeparator() +
                "    \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "    \"isFrozen\" : false," + System.lineSeparator() +
                "    \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "    \"myStorefrontRecords\" : [ ]" + System.lineSeparator() +
                "  } ]," + System.lineSeparator() +
                "  \"messageRecords\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString3, JsonUtil.toJsonString(testShopOrSwapRecord3));

        ShopOrSwap testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount("Client", "client1", "pass1");
        testShopOrSwap3.addAccount("Client", "client2", "pass2");
        testShopOrSwapRecord4 = new ShopOrSwapRecord(testShopOrSwap3);
        String testJsonString4 =
                "{" + System.lineSeparator() +
                "  \"accountRecords\" : [ {" + System.lineSeparator() +
                "    \"accountName\" : \"client2\"," + System.lineSeparator() +
                "    \"accountPassword\" : \"pass2\"," + System.lineSeparator() +
                "    \"isFrozen\" : false," + System.lineSeparator() +
                "    \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "    \"myStorefrontRecords\" : [ ]" + System.lineSeparator() +
                "  }, {" + System.lineSeparator() +
                "    \"accountName\" : \"client1\"," + System.lineSeparator() +
                "    \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "    \"isFrozen\" : false," + System.lineSeparator() +
                "    \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "    \"myStorefrontRecords\" : [ ]" + System.lineSeparator() +
                "  } ]," + System.lineSeparator() +
                "  \"messageRecords\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString4, JsonUtil.toJsonString(testShopOrSwapRecord4));

        ShopOrSwap testShopOrSwap4 = new ShopOrSwap();
        testShopOrSwap4.addAccount("Client", "client1", "pass1");
        testShopOrSwap4.addAccount("Admin", "admin1", "pass1");
        testShopOrSwapRecord5 = new ShopOrSwapRecord(testShopOrSwap4);
        String testJsonString5 =
                "{" + System.lineSeparator() +
                "  \"accountRecords\" : [ {" + System.lineSeparator() +
                "    \"accountName\" : \"admin1\"," + System.lineSeparator() +
                "    \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "    \"isFrozen\" : false," + System.lineSeparator() +
                "    \"myProductRecords\" : null," + System.lineSeparator() +
                "    \"myStorefrontRecords\" : null" + System.lineSeparator() +
                "  }, {" + System.lineSeparator() +
                "    \"accountName\" : \"client1\"," + System.lineSeparator() +
                "    \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "    \"isFrozen\" : false," + System.lineSeparator() +
                "    \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "    \"myStorefrontRecords\" : [ ]" + System.lineSeparator() +
                "  } ]," + System.lineSeparator() +
                "  \"messageRecords\" : [ ]" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString5, JsonUtil.toJsonString(testShopOrSwapRecord5));

        ShopOrSwap testShopOrSwap5 = new ShopOrSwap();
        testShopOrSwap5.addAccount("Client", "client1", "pass1");
        testShopOrSwap5.addAccount("Admin", "admin1", "pass1");
        testShopOrSwap5.addAccount("Client", "client2", "pass2");
        testShopOrSwap5.addAccount("Admin", "admin2", "pass2");
        testShopOrSwap5.sendMessage("User", "client1", "client2", "subject1", "content1");
        testShopOrSwap5.sendMessage("Report", "client1", "client2", "subject1", "content1");
        testShopOrSwapRecord6 = new ShopOrSwapRecord(testShopOrSwap5);
        String testJsonString6 =
                "{" + System.lineSeparator() +
                "  \"accountRecords\" : [ {" + System.lineSeparator() +
                "    \"accountName\" : \"admin1\"," + System.lineSeparator() +
                "    \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "    \"isFrozen\" : false," + System.lineSeparator() +
                "    \"myProductRecords\" : null," + System.lineSeparator() +
                "    \"myStorefrontRecords\" : null" + System.lineSeparator() +
                "  }, {" + System.lineSeparator() +
                "    \"accountName\" : \"admin2\"," + System.lineSeparator() +
                "    \"accountPassword\" : \"pass2\"," + System.lineSeparator() +
                "    \"isFrozen\" : false," + System.lineSeparator() +
                "    \"myProductRecords\" : null," + System.lineSeparator() +
                "    \"myStorefrontRecords\" : null" + System.lineSeparator() +
                "  }, {" + System.lineSeparator() +
                "    \"accountName\" : \"client2\"," + System.lineSeparator() +
                "    \"accountPassword\" : \"pass2\"," + System.lineSeparator() +
                "    \"isFrozen\" : false," + System.lineSeparator() +
                "    \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "    \"myStorefrontRecords\" : [ ]" + System.lineSeparator() +
                "  }, {" + System.lineSeparator() +
                "    \"accountName\" : \"client1\"," + System.lineSeparator() +
                "    \"accountPassword\" : \"pass1\"," + System.lineSeparator() +
                "    \"isFrozen\" : false," + System.lineSeparator() +
                "    \"myProductRecords\" : [ ]," + System.lineSeparator() +
                "    \"myStorefrontRecords\" : [ ]" + System.lineSeparator() +
                "  } ]," + System.lineSeparator() +
                "  \"messageRecords\" : [ {" + System.lineSeparator() +
                "    \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "    \"senderName\" : \"client1\"," + System.lineSeparator() +
                "    \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "    \"recipientName\" : \"client2\"," + System.lineSeparator() +
                "    \"recipientType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "    \"subject\" : \"subject1\"," + System.lineSeparator() +
                "    \"content\" : \"content1\"," + System.lineSeparator() +
                "    \"reportedAccountName\" : null," + System.lineSeparator() +
                "    \"reportedAccountType\" : null" + System.lineSeparator() +
                "  }, {" + System.lineSeparator() +
                "    \"messageType\" : \"shoporswap.ReportMessage\"," + System.lineSeparator() +
                "    \"senderName\" : \"client1\"," + System.lineSeparator() +
                "    \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "    \"recipientName\" : \"admin1\"," + System.lineSeparator() +
                "    \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "    \"subject\" : \"subject1\"," + System.lineSeparator() +
                "    \"content\" : \"content1\"," + System.lineSeparator() +
                "    \"reportedAccountName\" : \"client2\"," + System.lineSeparator() +
                "    \"reportedAccountType\" : \"shoporswap.Client\"" + System.lineSeparator() +
                "  }, {" + System.lineSeparator() +
                "    \"messageType\" : \"shoporswap.ReportMessage\"," + System.lineSeparator() +
                "    \"senderName\" : \"client1\"," + System.lineSeparator() +
                "    \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "    \"recipientName\" : \"admin2\"," + System.lineSeparator() +
                "    \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "    \"subject\" : \"subject1\"," + System.lineSeparator() +
                "    \"content\" : \"content1\"," + System.lineSeparator() +
                "    \"reportedAccountName\" : \"client2\"," + System.lineSeparator() +
                "    \"reportedAccountType\" : \"shoporswap.Client\"" + System.lineSeparator() +
                "  } ]" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString6, JsonUtil.toJsonString(testShopOrSwapRecord6));

    }

    @Test
    void toAndFromJsonFileTest() throws IOException{
        ShopOrSwapRecord testShopOrSwapRecord1, testShopOrSwapRecord2, testShopOrSwapRecord3, testShopOrSwapRecord4, testShopOrSwapRecord5;
        String directoryPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

        ShopOrSwap testShopOrSwap1 = new ShopOrSwap();
        testShopOrSwapRecord1 = new ShopOrSwapRecord(testShopOrSwap1);
        String testFileName1 = directoryPath + "ShopOrSwapRecordTest-toAndFromJsonFileTest-1.json";
        JsonUtil.toJsonFile(testFileName1, testShopOrSwapRecord1);
        ShopOrSwapRecord testImportShopOrSwapRecord1 = JsonUtil.fromJsonFile(testFileName1, ShopOrSwapRecord.class);
        assertEquals(testShopOrSwapRecord1.getAccountRecords().size(), testImportShopOrSwapRecord1.getAccountRecords().size());
        assertEquals(testShopOrSwapRecord1.getMessageRecords().size(), testImportShopOrSwapRecord1.getMessageRecords().size());

        ShopOrSwap testShopOrSwap2 = new ShopOrSwap();
        testShopOrSwap2.addAccount("Client", "client1", "pass1");
        testShopOrSwapRecord2 = new ShopOrSwapRecord(testShopOrSwap2);
        String testFileName2 = directoryPath + "ShopOrSwapRecordTest-toAndFromJsonFileTest-2.json";
        JsonUtil.toJsonFile(testFileName2, testShopOrSwapRecord2);
        ShopOrSwapRecord testImportShopOrSwapRecord2 = JsonUtil.fromJsonFile(testFileName2, ShopOrSwapRecord.class);
        assertEquals(testShopOrSwapRecord2.getAccountRecords().size(), testImportShopOrSwapRecord2.getAccountRecords().size());
        assertEquals(testShopOrSwapRecord2.getMessageRecords().size(), testImportShopOrSwapRecord2.getMessageRecords().size());

        ShopOrSwap testShopOrSwap3 = new ShopOrSwap();
        testShopOrSwap3.addAccount("Client", "client1", "pass1");
        testShopOrSwap3.addAccount("Client", "client2", "pass2");
        testShopOrSwapRecord3 = new ShopOrSwapRecord(testShopOrSwap3);
        String testFileName3 = directoryPath + "ShopOrSwapRecordTest-toAndFromJsonFileTest-3.json";
        JsonUtil.toJsonFile(testFileName3, testShopOrSwapRecord3);
        ShopOrSwapRecord testImportShopOrSwapRecord3 = JsonUtil.fromJsonFile(testFileName3, ShopOrSwapRecord.class);
        assertEquals(testShopOrSwapRecord3.getAccountRecords().size(), testImportShopOrSwapRecord3.getAccountRecords().size());
        assertEquals(testShopOrSwapRecord3.getMessageRecords().size(), testImportShopOrSwapRecord3.getMessageRecords().size());

        ShopOrSwap testShopOrSwap4 = new ShopOrSwap();
        testShopOrSwap4.addAccount("Client", "client1", "pass1");
        testShopOrSwap4.addAccount("Admin", "admin1", "pass1");
        testShopOrSwapRecord4 = new ShopOrSwapRecord(testShopOrSwap4);
        String testFileName4 = directoryPath + "ShopOrSwapRecordTest-toAndFromJsonFileTest-4.json";
        JsonUtil.toJsonFile(testFileName4, testShopOrSwapRecord4);
        ShopOrSwapRecord testImportShopOrSwapRecord4 = JsonUtil.fromJsonFile(testFileName4, ShopOrSwapRecord.class);
        assertEquals(testShopOrSwapRecord4.getAccountRecords().size(), testImportShopOrSwapRecord4.getAccountRecords().size());
        assertEquals(testShopOrSwapRecord4.getMessageRecords().size(), testImportShopOrSwapRecord4.getMessageRecords().size());

        ShopOrSwap testShopOrSwap5 = new ShopOrSwap();
        testShopOrSwap5.addAccount("Client", "client1", "pass1");
        testShopOrSwap5.addAccount("Admin", "admin1", "pass1");
        testShopOrSwap5.addAccount("Client", "client2", "pass2");
        testShopOrSwap5.addAccount("Admin", "admin2", "pass2");
        testShopOrSwap5.sendMessage("User", "client1", "client2", "subject1", "content1");
        testShopOrSwap5.sendMessage("Report", "client1", "client2", "subject1", "content1");
        testShopOrSwapRecord5 = new ShopOrSwapRecord(testShopOrSwap5);
        String testFileName5 = directoryPath + "ShopOrSwapRecordTest-toAndFromJsonFileTest-5.json";
        JsonUtil.toJsonFile(testFileName5, testShopOrSwapRecord5);
        ShopOrSwapRecord testImportShopOrSwapRecord5 = JsonUtil.fromJsonFile(testFileName5, ShopOrSwapRecord.class);
        assertEquals(testShopOrSwapRecord5.getAccountRecords().size(), testImportShopOrSwapRecord5.getAccountRecords().size());
        assertEquals(testShopOrSwapRecord5.getMessageRecords().size(), testImportShopOrSwapRecord5.getMessageRecords().size());
    }

}
