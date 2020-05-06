import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportMessageTest {

    /**
     * Automated tests for the constructor methods of ReportMessage
     */
    @Test
    void constructorsTests(){
        ReportMessage testMessage1, testMessage2;

        assertThrows(IllegalArgumentException.class, ()-> new ReportMessage(
                new Client("", ""),
                new Admin("test1", "pass1"),
                "valid content",
                new Client("test1", "pass1")));
        assertThrows(IllegalArgumentException.class, ()-> new ReportMessage(
                new Client("test1", "test1"),
                new Admin("", ""),
                "valid content",
                new Client("test1", "pass1")));
        assertThrows(IllegalArgumentException.class, ()-> new ReportMessage(
                new Client("test1", "pass1"),
                new Admin("test1", "pass1"),
                "#invalid content$ ",
                new Client("test1", "pass1")
                ));
        assertThrows(IllegalArgumentException.class, ()-> new ReportMessage(
                new Client("test1", "pass1"),
                new Admin("test1", "pass1"),
                "valid content",
                new Client("", "")
                ));


        testMessage1 = new ReportMessage();
        assertNull(testMessage1.getSender());
        assertEquals("DefaultReport", testMessage1.getSubject());
        assertEquals("DefaultContent", testMessage1.getContent());

        testMessage2 = new ReportMessage(new Client("test1", "pass1"), new Client("test2", "pass2"), "content", new Client("test2", "pass2"));
        assertEquals("test1", testMessage2.getSender().getAccountName());
        assertEquals("pass1", testMessage2.getSender().getAccountPassword());
        assertEquals("test2", testMessage2.getRecipient().getAccountName());
        assertEquals("pass2", testMessage2.getRecipient().getAccountPassword());
        assertEquals("test2", testMessage2.getReportedAccount().getAccountName());
        assertEquals("pass2", testMessage2.getReportedAccount().getAccountPassword());
        assertEquals("Report: " + testMessage2.getReportedAccount().getAccountName(), testMessage2.getSubject());
        assertEquals("content", testMessage2.getContent());
    }

    /**
     * Automated tests for the mutator methods of ReportMessage
     */
    void mutatorsTests(){
        ReportMessage testMessage1 = new ReportMessage();
        testMessage1.setReportedAccount(new Client("test1", "pass1"));
        assertEquals("test1", testMessage1.getReportedAccount().getAccountName());
        assertEquals("pass1", testMessage1.getReportedAccount().getAccountPassword());
    }

}
