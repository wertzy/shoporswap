package io;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import shoporswap.*;
import util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MessageRecordTest {

    @Test
    void constructorsTests(){

        MessageRecord testMessageRecord1, testMessageRecord2, testMessageRecord3, testMessageRecord4, testMessageRecord5;

        testMessageRecord1 = new MessageRecord();
        assertNull(testMessageRecord1.getMessageType());
        assertNull(testMessageRecord1.getSenderName());
        assertNull(testMessageRecord1.getSenderType());
        assertNull(testMessageRecord1.getRecipientName());
        assertNull(testMessageRecord1.getRecipientType());
        assertNull(testMessageRecord1.getSubject());
        assertNull(testMessageRecord1.getContent());
        assertNull(testMessageRecord1.getReportedAccountName());
        assertNull(testMessageRecord1.getReportedAccountType());

        Account testClient1 = new Client("test1", "pass1");
        Account testClient2 = new Client("test2", "pass2");
        Account testAdmin1 = new Admin("test1", "pass1");
        Account testAdmin2 = new Admin("test2", "pass2");

        AbstractMessage testUserMessage1 = new UserMessage((Client) testClient1, testClient2, "subject1", "content1");
        testMessageRecord2 = new MessageRecord(testUserMessage1);
        assertEquals("shoporswap.UserMessage", testMessageRecord2.getMessageType());
        assertEquals("test1", testMessageRecord2.getSenderName());
        assertEquals("shoporswap.Client", testMessageRecord2.getSenderType());
        assertEquals("test2", testMessageRecord2.getRecipientName());
        assertEquals("shoporswap.Client", testMessageRecord2.getRecipientType());
        assertEquals("subject1", testMessageRecord2.getSubject());
        assertEquals("content1", testMessageRecord2.getContent());
        assertNull(testMessageRecord2.getReportedAccountName());
        assertNull(testMessageRecord2.getReportedAccountType());

        AbstractMessage testUserMessage2 = new UserMessage((Client) testClient1, testAdmin1, "subject2", "content2");
        testMessageRecord3 = new MessageRecord(testUserMessage2);
        assertEquals("shoporswap.UserMessage", testMessageRecord3.getMessageType());
        assertEquals("test1", testMessageRecord3.getSenderName());
        assertEquals("shoporswap.Client", testMessageRecord3.getSenderType());
        assertEquals("test1", testMessageRecord3.getRecipientName());
        assertEquals("shoporswap.Admin", testMessageRecord3.getRecipientType());
        assertEquals("subject2", testMessageRecord3.getSubject());
        assertEquals("content2", testMessageRecord3.getContent());
        assertNull(testMessageRecord3.getReportedAccountName());
        assertNull(testMessageRecord3.getReportedAccountType());

        AbstractMessage testUserMessage3 = new UserMessage((Client) testClient1, testAdmin2, "subject3", "content3");
        testMessageRecord4 = new MessageRecord(testUserMessage3);
        assertEquals("shoporswap.UserMessage", testMessageRecord4.getMessageType());
        assertEquals("test1", testMessageRecord4.getSenderName());
        assertEquals("shoporswap.Client", testMessageRecord4.getSenderType());
        assertEquals("test2", testMessageRecord4.getRecipientName());
        assertEquals("shoporswap.Admin", testMessageRecord4.getRecipientType());
        assertEquals("subject3", testMessageRecord4.getSubject());
        assertEquals("content3", testMessageRecord4.getContent());
        assertNull(testMessageRecord4.getReportedAccountName());
        assertNull(testMessageRecord4.getReportedAccountType());

        AbstractMessage testReportMessage1 = new ReportMessage(testClient2, (Admin) testAdmin1, "content1", testClient1);
        testMessageRecord5 = new MessageRecord(testReportMessage1);
        assertEquals("shoporswap.ReportMessage", testMessageRecord5.getMessageType());
        assertEquals("test2", testMessageRecord5.getSenderName());
        assertEquals("shoporswap.Client", testMessageRecord5.getSenderType());
        assertEquals("test1", testMessageRecord5.getRecipientName());
        assertEquals("shoporswap.Admin", testMessageRecord5.getRecipientType());
        assertEquals("Report: " + testClient1.getAccountName(), testMessageRecord5.getSubject());
        assertEquals("content1", testMessageRecord5.getContent());
        assertEquals("test1", testMessageRecord5.getReportedAccountName());
        assertEquals("shoporswap.Client", testMessageRecord5.getReportedAccountType());

    }

    @Test
    void toMessageTest(){
        MessageRecord testMessageRecord1, testMessageRecord2, testMessageRecord3, testMessageRecord4;


        Account testClient1 = new Client("test1", "pass1");
        Account testClient2 = new Client("test2", "pass2");
        Account testAdmin1 = new Admin("test1", "pass1");
        Account testAdmin2 = new Admin("test2", "pass2");

        AbstractMessage testUserMessage1 = new UserMessage((Client) testClient1, testClient2, "subject1", "content1");
        testMessageRecord1 = new MessageRecord(testUserMessage1);
        AbstractMessage testExportUserMessage1 = testMessageRecord1.toMessage();
        assertEquals(testExportUserMessage1.getClass(), testUserMessage1.getClass());
        assertEquals(testExportUserMessage1.getSender().getAccountName(), testUserMessage1.getSender().getAccountName());
        assertEquals(testExportUserMessage1.getRecipient().getAccountName(), testUserMessage1.getRecipient().getAccountName());
        assertEquals(testExportUserMessage1.getSubject(), testUserMessage1.getSubject());
        assertEquals(testExportUserMessage1.getContent(), testUserMessage1.getContent());

        AbstractMessage testUserMessage2 = new UserMessage((Client) testClient1, testAdmin1, "subject2", "content2");
        testMessageRecord2 = new MessageRecord(testUserMessage2);
        AbstractMessage testExportUserMessage2 = testMessageRecord2.toMessage();
        assertEquals(testExportUserMessage2.getClass(), testUserMessage2.getClass());
        assertEquals(testExportUserMessage2.getSender().getAccountName(), testUserMessage2.getSender().getAccountName());
        assertEquals(testExportUserMessage2.getRecipient().getAccountName(), testUserMessage2.getRecipient().getAccountName());
        assertEquals(testExportUserMessage2.getSubject(), testUserMessage2.getSubject());
        assertEquals(testExportUserMessage2.getContent(), testUserMessage2.getContent());

        AbstractMessage testUserMessage3 = new UserMessage((Client) testClient1, testAdmin2, "subject3", "content3");
        testMessageRecord3 = new MessageRecord(testUserMessage3);
        AbstractMessage testExportUserMessage3 = testMessageRecord3.toMessage();
        assertEquals(testExportUserMessage3.getClass(), testUserMessage3.getClass());
        assertEquals(testExportUserMessage3.getSender().getAccountName(), testUserMessage3.getSender().getAccountName());
        assertEquals(testExportUserMessage3.getRecipient().getAccountName(), testUserMessage3.getRecipient().getAccountName());
        assertEquals(testExportUserMessage3.getSubject(), testUserMessage3.getSubject());
        assertEquals(testExportUserMessage3.getContent(), testUserMessage3.getContent());

        AbstractMessage testReportMessage1 = new ReportMessage(testClient2, (Admin) testAdmin1, "content1", testClient1);
        testMessageRecord4 = new MessageRecord(testReportMessage1);
        AbstractMessage testExportUserMessage4 = testMessageRecord4.toMessage();
        assertEquals(testExportUserMessage4.getClass(), testReportMessage1.getClass());
        assertEquals(testExportUserMessage4.getSender().getAccountName(), testReportMessage1.getSender().getAccountName());
        assertEquals(testExportUserMessage4.getRecipient().getAccountName(), testReportMessage1.getRecipient().getAccountName());
        assertEquals(testExportUserMessage4.getSubject(), testReportMessage1.getSubject());
        assertEquals(testExportUserMessage4.getContent(), testReportMessage1.getContent());
    }

    @Test
    void toJsonStringTest() throws JsonProcessingException {
        MessageRecord testMessageRecord1, testMessageRecord2, testMessageRecord3, testMessageRecord4, testMessageRecord5;

        testMessageRecord1 = new MessageRecord();
        String testJsonString1 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : null," + System.lineSeparator() +
                "  \"senderName\" : null," + System.lineSeparator() +
                "  \"senderType\" : null," + System.lineSeparator() +
                "  \"recipientName\" : null," + System.lineSeparator() +
                "  \"recipientType\" : null," + System.lineSeparator() +
                "  \"subject\" : null," + System.lineSeparator() +
                "  \"content\" : null," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString1, JsonUtil.toJsonString(testMessageRecord1));

        Account testClient1 = new Client("test1", "pass1");
        Account testClient2 = new Client("test2", "pass2");
        Account testAdmin1 = new Admin("test1", "pass1");
        Account testAdmin2 = new Admin("test2", "pass2");

        AbstractMessage testUserMessage1 = new UserMessage((Client) testClient1, testClient2, "subject1", "content1");
        testMessageRecord2 = new MessageRecord(testUserMessage1);
        String testJsonString2 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test1\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test2\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"subject\" : \"subject1\"," + System.lineSeparator() +
                "  \"content\" : \"content1\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString2, JsonUtil.toJsonString(testMessageRecord2));

        AbstractMessage testUserMessage2 = new UserMessage((Client) testClient1, testAdmin1, "subject2", "content2");
        testMessageRecord3 = new MessageRecord(testUserMessage2);
        String testJsonString3 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test1\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test1\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "  \"subject\" : \"subject2\"," + System.lineSeparator() +
                "  \"content\" : \"content2\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString3, JsonUtil.toJsonString(testMessageRecord3));

        AbstractMessage testUserMessage3 = new UserMessage((Client) testClient1, testAdmin2, "subject3", "content3");
        testMessageRecord4 = new MessageRecord(testUserMessage3);
        String testJsonString4 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test1\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test2\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "  \"subject\" : \"subject3\"," + System.lineSeparator() +
                "  \"content\" : \"content3\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString4, JsonUtil.toJsonString(testMessageRecord4));

        AbstractMessage testReportMessage1 = new ReportMessage(testClient2, (Admin) testAdmin1, "content1", testClient1);
        testMessageRecord5 = new MessageRecord(testReportMessage1);
        String testJsonString5 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.ReportMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test2\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test1\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "  \"subject\" : \"Report: test1\"," + System.lineSeparator() +
                "  \"content\" : \"content1\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : \"test1\"," + System.lineSeparator() +
                "  \"reportedAccountType\" : \"shoporswap.Client\"" + System.lineSeparator() +
                "}";
        assertEquals(testJsonString5, JsonUtil.toJsonString(testMessageRecord5));
    }

    @Test
    void toAndFromJsonFileTest() throws IOException {
        MessageRecord testMessageRecord1, testMessageRecord2, testMessageRecord3, testMessageRecord4, testMessageRecord5;
        String directoryPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

        testMessageRecord1 = new MessageRecord();
        String testJsonString1 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : null," + System.lineSeparator() +
                "  \"senderName\" : null," + System.lineSeparator() +
                "  \"senderType\" : null," + System.lineSeparator() +
                "  \"recipientName\" : null," + System.lineSeparator() +
                "  \"recipientType\" : null," + System.lineSeparator() +
                "  \"subject\" : null," + System.lineSeparator() +
                "  \"content\" : null," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";
        String testFileName1 = directoryPath + "MessageRecordTest-toAndFromJsonFileTest-1.json";
        JsonUtil.toJsonFile(testFileName1, testMessageRecord1);
        assertEquals(testJsonString1, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName1, MessageRecord.class)));

        Account testClient1 = new Client("test1", "pass1");
        Account testClient2 = new Client("test2", "pass2");
        Account testAdmin1 = new Admin("test1", "pass1");
        Account testAdmin2 = new Admin("test2", "pass2");

        AbstractMessage testUserMessage1 = new UserMessage((Client) testClient1, testClient2, "subject1", "content1");
        testMessageRecord2 = new MessageRecord(testUserMessage1);
        String testJsonString2 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test1\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test2\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"subject\" : \"subject1\"," + System.lineSeparator() +
                "  \"content\" : \"content1\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";
        String testFileName2 = directoryPath + "MessageRecordTest-toAndFromJsonFileTest-2.json";
        JsonUtil.toJsonFile(testFileName2, testMessageRecord2);
        assertEquals(testJsonString2, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName2, MessageRecord.class)));

        AbstractMessage testUserMessage2 = new UserMessage((Client) testClient1, testAdmin1, "subject2", "content2");
        testMessageRecord3 = new MessageRecord(testUserMessage2);
        String testJsonString3 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test1\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test1\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "  \"subject\" : \"subject2\"," + System.lineSeparator() +
                "  \"content\" : \"content2\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";
        String testFileName3 = directoryPath + "MessageRecordTest-toAndFromJsonFileTest-3.json";
        JsonUtil.toJsonFile(testFileName3, testMessageRecord3);
        assertEquals(testJsonString3, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName3, MessageRecord.class)));

        AbstractMessage testUserMessage3 = new UserMessage((Client) testClient1, testAdmin2, "subject3", "content3");
        testMessageRecord4 = new MessageRecord(testUserMessage3);
        String testJsonString4 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test1\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test2\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "  \"subject\" : \"subject3\"," + System.lineSeparator() +
                "  \"content\" : \"content3\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";
        String testFileName4 = directoryPath + "MessageRecordTest-toAndFromJsonFileTest-4.json";
        JsonUtil.toJsonFile(testFileName4, testMessageRecord4);
        assertEquals(testJsonString4, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName4, MessageRecord.class)));

        AbstractMessage testReportMessage1 = new ReportMessage(testClient2, (Admin) testAdmin1, "content1", testClient1);
        testMessageRecord5 = new MessageRecord(testReportMessage1);
        String testJsonString5 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.ReportMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test2\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test1\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "  \"subject\" : \"Report: test1\"," + System.lineSeparator() +
                "  \"content\" : \"content1\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : \"test1\"," + System.lineSeparator() +
                "  \"reportedAccountType\" : \"shoporswap.Client\"" + System.lineSeparator() +
                "}";
        String testFileName5 = directoryPath + "MessageRecordTest-toAndFromJsonFileTest-5.json";
        JsonUtil.toJsonFile(testFileName5, testMessageRecord5);
        assertEquals(testJsonString5, JsonUtil.toJsonString(JsonUtil.fromJsonFile(testFileName5, MessageRecord.class)));
    }

    @Test
    void listFromJsonFileTest() throws IOException {
        MessageRecord testMessageRecord1, testMessageRecord2, testMessageRecord3, testMessageRecord4, testMessageRecord5;
        String directoryPath = "src" + File.separator + "test" + File.separator + "resources" + File.separator;

        testMessageRecord1 = new MessageRecord();
        String testJsonString1 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : null," + System.lineSeparator() +
                "  \"senderName\" : null," + System.lineSeparator() +
                "  \"senderType\" : null," + System.lineSeparator() +
                "  \"recipientName\" : null," + System.lineSeparator() +
                "  \"recipientType\" : null," + System.lineSeparator() +
                "  \"subject\" : null," + System.lineSeparator() +
                "  \"content\" : null," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";

        Account testClient1 = new Client("test1", "pass1");
        Account testClient2 = new Client("test2", "pass2");
        Account testAdmin1 = new Admin("test1", "pass1");
        Account testAdmin2 = new Admin("test2", "pass2");

        AbstractMessage testUserMessage1 = new UserMessage((Client) testClient1, testClient2, "subject1", "content1");
        testMessageRecord2 = new MessageRecord(testUserMessage1);
        String testJsonString2 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test1\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test2\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"subject\" : \"subject1\"," + System.lineSeparator() +
                "  \"content\" : \"content1\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";

        AbstractMessage testUserMessage2 = new UserMessage((Client) testClient1, testAdmin1, "subject2", "content2");
        testMessageRecord3 = new MessageRecord(testUserMessage2);
        String testJsonString3 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test1\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test1\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "  \"subject\" : \"subject2\"," + System.lineSeparator() +
                "  \"content\" : \"content2\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";

        AbstractMessage testUserMessage3 = new UserMessage((Client) testClient1, testAdmin2, "subject3", "content3");
        testMessageRecord4 = new MessageRecord(testUserMessage3);
        String testJsonString4 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.UserMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test1\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test2\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "  \"subject\" : \"subject3\"," + System.lineSeparator() +
                "  \"content\" : \"content3\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : null," + System.lineSeparator() +
                "  \"reportedAccountType\" : null" + System.lineSeparator() +
                "}";

        AbstractMessage testReportMessage1 = new ReportMessage(testClient2, (Admin) testAdmin1, "content1", testClient1);
        testMessageRecord5 = new MessageRecord(testReportMessage1);
        String testJsonString5 =
                "{" + System.lineSeparator() +
                "  \"messageType\" : \"shoporswap.ReportMessage\"," + System.lineSeparator() +
                "  \"senderName\" : \"test2\"," + System.lineSeparator() +
                "  \"senderType\" : \"shoporswap.Client\"," + System.lineSeparator() +
                "  \"recipientName\" : \"test1\"," + System.lineSeparator() +
                "  \"recipientType\" : \"shoporswap.Admin\"," + System.lineSeparator() +
                "  \"subject\" : \"Report: test1\"," + System.lineSeparator() +
                "  \"content\" : \"content1\"," + System.lineSeparator() +
                "  \"reportedAccountName\" : \"test1\"," + System.lineSeparator() +
                "  \"reportedAccountType\" : \"shoporswap.Client\"" + System.lineSeparator() +
                "}";
        String testFileName5 = directoryPath + "MessageRecordTest-listFromJsonFileTest-1.json";
        JsonUtil.toJsonFile(testFileName5, Arrays.asList(testMessageRecord1, testMessageRecord2, testMessageRecord3, testMessageRecord4, testMessageRecord5));

        List<MessageRecord> testMessageRecordList = JsonUtil.listFromJsonFile(testFileName5, MessageRecord.class);
        assertEquals(testJsonString1, JsonUtil.toJsonString(testMessageRecordList.get(0)));
        assertEquals(testJsonString2, JsonUtil.toJsonString(testMessageRecordList.get(1)));
        assertEquals(testJsonString3, JsonUtil.toJsonString(testMessageRecordList.get(2)));
        assertEquals(testJsonString4, JsonUtil.toJsonString(testMessageRecordList.get(3)));
        assertEquals(testJsonString5, JsonUtil.toJsonString(testMessageRecordList.get(4)));
    }

}
