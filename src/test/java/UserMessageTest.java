import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserMessageTest {

    /**
     * Automated tests for constructor methods of UserMessage
     */
    @Test
    void constructorsTests(){
        UserMessage testMessage1, testMessage2;

        assertThrows(IllegalArgumentException.class, ()-> new UserMessage(
                new Client("", ""),
                "valid subject",
                "valid content"));
        assertThrows(IllegalArgumentException.class, ()-> new UserMessage(
                new Client("test1", "pass1"),
                " invalid subject-",
                "valid content"));
        assertThrows(IllegalArgumentException.class, ()-> new UserMessage(
                new Client("test1", "pass1"),
                "valid subject",
                "#invalid content$ "));

        testMessage1 = new UserMessage();
        assertNull(testMessage1.getSender());
        assertEquals("DefaultSubject", testMessage1.getSubject());
        assertEquals("DefaultContent", testMessage1.getContent());

        testMessage2 = new UserMessage(new Client("test1", "pass1"), "subject", "content");
        assertEquals("test1", testMessage2.getSender().getAccountName());
        assertEquals("pass1", testMessage2.getSender().getAccountPassword());
        assertEquals("subject", testMessage2.getSubject());
        assertEquals("content", testMessage2.getContent());
    }

}
