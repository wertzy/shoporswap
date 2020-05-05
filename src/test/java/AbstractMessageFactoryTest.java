import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractMessageFactoryTest {

    /**
     * Automated tests for AbstractMessageFactory.getMessage method
     */
    @Test
    void getMessageTests(){
        AbstractMessageFactory testAbstractMessageFactory = new AbstractMessageFactory();
        AbstractMessage testUserMessage1, testUserMessage2, testUserMessage3;
        AbstractMessage testReportMessage1, testReportMessage2, testReportMessage3;

        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage(""));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("userr"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("uuser"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("uuserr"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("ser"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("use"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("se"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage(" user"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("user "));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage(" user "));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("-user"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("user-"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("-user-"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("&user"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("user&"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("&user&"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("@#$%^&*!"));

        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage(""));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("reportt"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("rreport"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("rreportt"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("eport"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("repor"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("epor"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage(" report"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("report "));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage(" report "));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("-report"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("report-"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("-report-"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("&report"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("report&"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("&report&"));
        assertThrows(IllegalArgumentException.class, ()-> testAbstractMessageFactory.getMessage("@#$%^&*!"));

        testUserMessage1 = testAbstractMessageFactory.getMessage("user");
        assertNull(testUserMessage1.getSender());
        assertEquals("DefaultSubject", testUserMessage1.getSubject());
        assertEquals("DefaultContent", testUserMessage1.getContent());

        testUserMessage2 = testAbstractMessageFactory.getMessage("USER");
        assertNull(testUserMessage2.getSender());
        assertEquals("DefaultSubject", testUserMessage2.getSubject());
        assertEquals("DefaultContent", testUserMessage2.getContent());

        testUserMessage3 = testAbstractMessageFactory.getMessage("UsEr");
        assertNull(testUserMessage3.getSender());
        assertEquals("DefaultSubject", testUserMessage3.getSubject());
        assertEquals("DefaultContent", testUserMessage3.getContent());

        testReportMessage1 = testAbstractMessageFactory.getMessage("report");
        assertNull(testReportMessage1.getSender());
        assertEquals("DefaultReport", testReportMessage1.getSubject());
        assertEquals("DefaultContent", testReportMessage1.getContent());
        assertNull(((ReportMessage) testReportMessage1).getReportedAccount());

        testReportMessage2 = testAbstractMessageFactory.getMessage("REPORT");
        assertNull(testReportMessage2.getSender());
        assertEquals("DefaultReport", testReportMessage2.getSubject());
        assertEquals("DefaultContent", testReportMessage2.getContent());
        assertNull(((ReportMessage) testReportMessage2).getReportedAccount());

        testReportMessage3 = testAbstractMessageFactory.getMessage("rEpOrT");
        assertNull(testReportMessage3.getSender());
        assertEquals("DefaultReport", testReportMessage3.getSubject());
        assertEquals("DefaultContent", testReportMessage3.getContent());
        assertNull(((ReportMessage) testReportMessage3).getReportedAccount());

    }

}
