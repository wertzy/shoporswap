package io;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import shoporswap.*;
import util.JsonUtil;

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

}
