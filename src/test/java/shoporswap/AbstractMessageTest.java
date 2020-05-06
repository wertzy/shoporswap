package shoporswap;

import org.junit.jupiter.api.Test;
import shoporswap.*;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractMessageTest {

    /**
     * Automated tests for mutator methods of shoporswap.AbstractMessage
     */
    @Test
    void mutatorsTests(){
        AbstractMessage testMessage1 = new UserMessage();
        AbstractMessage testMessage2 = new ReportMessage();

        Account testAccount1, testAccount2, testAccount3, testAccount4;
        testAccount1 = new Client("test1", "pass1");
        testAccount1.setIsFrozen(true);
        testAccount2 = new Admin("test2", "pass2");
        testAccount2.setIsFrozen(true);
        testAccount3 = new Client("test3", "pass3");
        testAccount3.setIsFrozen(false);
        testAccount4 = new Admin("test4", "pass4");
        testAccount4.setIsFrozen(false);

        assertThrows(IllegalArgumentException.class, ()-> testMessage1.setSender(testAccount1));
        assertThrows(IllegalArgumentException.class, ()-> testMessage2.setSender(testAccount1));
        assertThrows(IllegalArgumentException.class, ()-> testMessage1.setSender(testAccount2));
        assertThrows(IllegalArgumentException.class, ()-> testMessage2.setSender(testAccount2));
        assertThrows(IllegalArgumentException.class, ()-> testMessage1.setSubject(""));
        assertThrows(IllegalArgumentException.class, ()-> testMessage2.setSubject(" m "));
        assertThrows(IllegalArgumentException.class, ()-> testMessage1.setSubject("thisistencthisistencthisistencthisistencthisistenct"));
        assertThrows(IllegalArgumentException.class, ()-> testMessage2.setSubject(" *&$hisis *&$enc *&$hisis *&$enc *&$hisis *&$enc *&$hisis *&$enc *&$hisis *&$enc *&$"));
        assertThrows(IllegalArgumentException.class, ()-> testMessage1.setContent(""));
        assertThrows(IllegalArgumentException.class, ()-> testMessage2.setContent(" m "));
        assertThrows(IllegalArgumentException.class, ()-> testMessage1.setContent(
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenct"
        ));
        assertThrows(IllegalArgumentException.class, ()-> testMessage2.setContent("#$content#$"));

        testMessage1.setSender(testAccount3);
        assertEquals(testAccount3, testMessage1.getSender());
        testMessage2.setSender(testAccount4);
        assertEquals(testAccount4, testMessage2.getSender());
        testMessage1.setSender(testAccount4);
        assertEquals(testAccount4, testMessage1.getSender());
        testMessage2.setSender(testAccount3);
        assertEquals(testAccount3, testMessage2.getSender());

        testMessage1.setSubject("v");
        assertEquals("v", testMessage1.getSubject());
        testMessage2.setSubject("valid content");
        assertEquals("valid content", testMessage2.getSubject());
        testMessage1.setSubject("valid content");
        assertEquals("valid content", testMessage1.getSubject());
        testMessage2.setSubject("v");
        assertEquals("v", testMessage2.getSubject());

        testMessage1.setContent("v");
        assertEquals("v", testMessage1.getContent());
        testMessage2.setContent("valid content");
        assertEquals("valid content", testMessage2.getContent());
        testMessage1.setContent("valid content");
        assertEquals("valid content", testMessage1.getContent());
        testMessage2.setContent("v");
        assertEquals("v", testMessage2.getContent());
    }

    /**
     * Automated tests for shoporswap.AbstractMessage.isValidMessageSender method
     */
    @Test
    void isValidMessageSenderTest(){
        Account testAccount1, testAccount2, testAccount3, testAccount4;

        testAccount1 = new Client("test1", "pass1");
        testAccount1.setIsFrozen(true);
        testAccount2 = new Admin("test2", "pass2");
        testAccount2.setIsFrozen(true);
        testAccount3 = new Client("test3", "pass3");
        testAccount3.setIsFrozen(false);
        testAccount4 = new Admin("test4", "pass4");
        testAccount4.setIsFrozen(false);

        assertFalse(AbstractMessage.isValidMessageSender(testAccount1));
        assertFalse(AbstractMessage.isValidMessageSender(testAccount2));
        assertTrue(AbstractMessage.isValidMessageSender(testAccount3));
        assertTrue(AbstractMessage.isValidMessageSender(testAccount4));

    }

    /**
     * Automated tests for shoporswap.AbstractMessage.isValidMessageSubject method
     */
    @Test
    void isValidMessageSubjectTest(){
        assertFalse(AbstractMessage.isValidMessageSubject(""));
        assertFalse(AbstractMessage.isValidMessageSubject("thisistencthisistencthisistencthisistencthisistenct"));
        assertFalse(AbstractMessage.isValidMessageSubject(" hisistencthisistencthisistencthisistencthisistenct"));
        assertFalse(AbstractMessage.isValidMessageSubject("thisistencthisistencthisistencthisistencthisistenc "));
        assertFalse(AbstractMessage.isValidMessageSubject(" hisistencthisistencthisistencthisistencthisistenc "));
        assertFalse(AbstractMessage.isValidMessageSubject(" hisis enc hisis enc hisis enc hisis enc hisis enc "));
        assertFalse(AbstractMessage.isValidMessageSubject("thisistencthisistencthisistencthisistencthisistenc-"));
        assertFalse(AbstractMessage.isValidMessageSubject("-hisistencthisistencthisistencthisistencthisistenc-"));
        assertFalse(AbstractMessage.isValidMessageSubject("-hisis-enc-hisis-enc-hisis-enc-hisis-enc-hisis-enc-"));
        assertFalse(AbstractMessage.isValidMessageSubject("thisistencthisistencthisistencthisistencthisistenc%"));
        assertFalse(AbstractMessage.isValidMessageSubject("%hisistencthisistencthisistencthisistencthisistenc%"));
        assertFalse(AbstractMessage.isValidMessageSubject("%hisis%enc%hisis%enc%hisis%enc%hisis%enc%hisis%enc%"));
        assertFalse(AbstractMessage.isValidMessageSubject("thisistencthisistencthisistencthisistencthisistenc *&$"));
        assertFalse(AbstractMessage.isValidMessageSubject(" *&$hisistencthisistencthisistencthisistencthisistenc *&$"));
        assertFalse(AbstractMessage.isValidMessageSubject(" *&$hisis *&$enc *&$hisis *&$enc *&$hisis *&$enc *&$hisis *&$enc *&$hisis *&$enc *&$"));
        
        assertTrue(AbstractMessage.isValidMessageContent("t"));
        assertTrue(AbstractMessage.isValidMessageContent("thisistenc"));
        assertTrue(AbstractMessage.isValidMessageContent("thisistencthisistencthisistencthisistencthisistenc"));
        assertTrue(AbstractMessage.isValidMessageSubject("t i"));
        assertTrue(AbstractMessage.isValidMessageSubject("this is content"));
        assertTrue(AbstractMessage.isValidMessageSubject("t *&$i"));
        assertTrue(AbstractMessage.isValidMessageSubject("this *&$is *&$content"));
        assertTrue(AbstractMessage.isValidMessageSubject("t$i"));
        assertTrue(AbstractMessage.isValidMessageSubject("this$is$content"));
        assertTrue(AbstractMessage.isValidMessageSubject("t-i"));
        assertTrue(AbstractMessage.isValidMessageSubject("this-is-content"));
    }

    /**
     * Automated tests for shoporswap.AbstractMessage.isValidMessageContent method
     */
    @Test
    void isValidMessageContentTest(){
        assertFalse(AbstractMessage.isValidMessageContent(""));
        assertFalse(AbstractMessage.isValidMessageContent(
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenc" +
            "thisistencthisistencthisistencthisistencthisistenct"
        ));
        assertFalse(AbstractMessage.isValidMessageContent(" content"));
        assertFalse(AbstractMessage.isValidMessageContent("content "));
        assertFalse(AbstractMessage.isValidMessageContent(" content "));
        assertFalse(AbstractMessage.isValidMessageContent("-content"));
        assertFalse(AbstractMessage.isValidMessageContent("content-"));
        assertFalse(AbstractMessage.isValidMessageContent("-content-"));
        assertFalse(AbstractMessage.isValidMessageContent("%content"));
        assertFalse(AbstractMessage.isValidMessageContent("content%"));
        assertFalse(AbstractMessage.isValidMessageContent("%content%"));
        assertFalse(AbstractMessage.isValidMessageContent("#$content"));
        assertFalse(AbstractMessage.isValidMessageContent("content#$"));
        assertFalse(AbstractMessage.isValidMessageContent("#$content#$"));
        assertFalse(AbstractMessage.isValidMessageContent(" ^ content"));
        assertFalse(AbstractMessage.isValidMessageContent("content ^ "));
        assertFalse(AbstractMessage.isValidMessageContent(" ^ content ^ "));

        assertTrue(AbstractMessage.isValidMessageContent("t"));
        assertTrue(AbstractMessage.isValidMessageContent("thisistenc"));
        assertTrue(AbstractMessage.isValidMessageContent(
                "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisisten"
        ));
        assertTrue(AbstractMessage.isValidMessageContent(
                "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistenc" +
                        "thisistencthisistencthisistencthisistencthisistenc"
        ));
        assertTrue(AbstractMessage.isValidMessageContent("t i"));
        assertTrue(AbstractMessage.isValidMessageContent("this is content"));
        assertTrue(AbstractMessage.isValidMessageContent(
                "this iste this iste this iste this iste this iste this iste " +
                        "this iste this iste this iste this iste this iste this iste " +
                        "this iste this iste this iste this iste this iste this iste " +
                        "this iste this iste this iste this iste this iste this iste " +
                        "this iste this iste this iste this iste this iste this iste"
        ));
        assertTrue(AbstractMessage.isValidMessageContent("t-i"));
        assertTrue(AbstractMessage.isValidMessageContent("this-is-content"));
        assertTrue(AbstractMessage.isValidMessageContent(
                "this-iste-this-iste-this-iste-this-iste-this-iste-this-iste-" +
                        "this-iste-this-iste-this-iste-this-iste-this-iste-this-iste-" +
                        "this-iste-this-iste-this-iste-this-iste-this-iste-this-iste-" +
                        "this-iste-this-iste-this-iste-this-iste-this-iste-this-iste-" +
                        "this-iste-this-iste-this-iste-this-iste-this-iste-this-iste"
        ));
        assertTrue(AbstractMessage.isValidMessageContent("t$i"));
        assertTrue(AbstractMessage.isValidMessageContent("this$is$content"));
        assertTrue(AbstractMessage.isValidMessageContent(
                "is$iste$is$iste$is$iste$is$iste$is$iste$is$iste$" +
                        "is$iste$is$iste$is$iste$is$iste$is$iste$is$iste$" +
                        "is$iste$is$iste$is$iste$is$iste$is$iste$is$iste$" +
                        "is$iste$is$iste$is$iste$is$iste$is$iste$is$iste"
        ));
        assertTrue(AbstractMessage.isValidMessageContent("t%$^i"));
        assertTrue(AbstractMessage.isValidMessageContent("this%$^is%$^content"));
        assertTrue(AbstractMessage.isValidMessageContent(
                "this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^" +
                        "this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^" +
                        "this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste"
        ));
    }

}
