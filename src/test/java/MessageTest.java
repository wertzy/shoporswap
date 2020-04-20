import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageTest {

    @Test
    void constructorTest(){
        User user1 = new User("name1", "pass1");
        User user2 = new User("name2", "pass2");
        Product prod1 = new Product("name1", "description", 0.01, user1);
        Product prod2 = new Product("name2", "description", 0.01, user2);
        Message message = new Message("a", "b", user1, user2);
        assertEquals("a", message.getName());
        assertEquals("b", message.getBody());
        assertEquals("name1", message.getRecipient().getAccountName());
        assertEquals("name2", message.getSender().getAccountName());

        Message messageProduct = new Message("a", "b", user1, user2, prod1, prod2);
        assertEquals("a", messageProduct.getName());
        assertEquals("b", messageProduct.getBody());
        assertEquals("name1", messageProduct.getRecipient().getAccountName());
        assertEquals("name2", messageProduct.getSender().getAccountName());
        assertEquals("name1", messageProduct.getIncomingSwap().getName());
        assertEquals("name2", messageProduct.getOutgoingSwap().getName());
    }

    @Test
    void readMessageTest(){
        User user1 = new User("name1", "pass1");
        User user2 = new User("name2", "pass2");
        //Product prod1 = new Product("name1", "description", 0.01, user1);
        //Product prod2 = new Product("name2", "description", 0.01, user2);
        Message message = new Message("a", "b", user1, user2);
        assertEquals(message, user1.getMessages().get(0));
        assertEquals(1 ,user1.getMessages().size());
        assertEquals("a: b; sent by name2", user1.checkMessage(user1.getMessages().get(0)));
    }

    @Test
    void messageSendTest(){
        User user1 = new User("name1", "pass1");
        User user2 = new User("name2", "pass2");
        Product prod1 = new Product("name1", "description", 0.01, user1);
        Product prod2 = new Product("name2", "description", 0.01, user2);
        user1.sendMessage("a", "b", user2);
        assertEquals(1 ,user2.getMessages().size());
        assertEquals("a: b; sent by name1", user2.checkMessage(user1.getMessages().get(0)));
        user1.sendMessage("a", "b", user2);
        assertEquals(2 ,user2.getMessages().size());
        assertEquals("a: b; sent by name1", user2.checkMessage(user1.getMessages().get(1)));
    }

}