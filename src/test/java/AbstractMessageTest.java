import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractMessageTest {

    /**
     * Automated tests for constructor methods of AbstractMessage
     */
    @Test
    void constructorsTests(){
        //TODO implement automated tests
        assertTrue(false);
    }

    /**
     * Automated tests for mutator methods of AbstractMessage
     */
    @Test
    void mutatorsTests(){
        //TODO implement automated tests
        assertTrue(false);
    }

    /**
     * Automated tests for AbstractMessage.isValidMessageSender method
     */
    @Test
    void isValidMessageSenderTest(){
        //TODO implement automated tests
        assertTrue(false);
    }

    /**
     * Automated tests for AbstractMessage.isValidMessageSubject method
     */
    @Test
    void isValidMessageSubjectTest(){
        //TODO implement automated tests
        assertFalse(AbstractMessage.isValidMessageSubject(""));
        assertFalse(AbstractMessage.isValidMessageSubject("thisistencthisistencthisistencthisistencthisistenct"));
//        assertFalse(AbstractMessage.isValidMessageSubject());
//        assertFalse(AbstractMessage.isValidMessageSubject());

//        assertTrue(AbstractMessage.isValidMessageSubject());
    }

    /**
     * Automated tests for AbstractMessage.isValidMessageContent method
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
                "this$iste$this$iste$this$iste$this$iste$this$iste$this$iste$" +
                        "this$iste$this$iste$this$iste$this$iste$this$iste$this$iste$" +
                        "this$iste$this$iste$this$iste$this$iste$this$iste$this$iste$" +
                        "this$iste$this$iste$this$iste$this$iste$this$iste$this$iste$"
        ));
        assertTrue(AbstractMessage.isValidMessageContent("t%$^i"));
        assertTrue(AbstractMessage.isValidMessageContent("this%$^is%$^content"));
        assertTrue(AbstractMessage.isValidMessageContent(
                "this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^" +
                        "this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^" +
                        "this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^this%$^iste%$^"
        ));
    }

}
