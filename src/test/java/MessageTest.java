import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Product prod1 = new Product("name1", "description", 0.01, user1);
        Product prod2 = new Product("name2", "description", 0.01, user2);
        Message message = new Message("a", "b", user1, user2);
        user1.receiveMessage(message);
        assertEquals(message, user1.retrieveMessages().get(0));
        assertEquals(1 ,user1.retrieveMessages().size());
        assertFalse(user1.retrieveMessages().get(0).getRead());
        assertEquals(0, user1.retrieveMessages().get(0).getReadCount());
        assertEquals("a: b; sent by name2", user1.checkMessage(user1.retrieveMessages().get(0)));
        assertTrue(user1.retrieveMessages().get(0).getRead());
        assertEquals(1, user1.retrieveMessages().get(0).getReadCount());

        Message message2 = new Message("a", "b", user1, user2, prod1, prod2);
        user1.receiveMessage(message2);
        assertEquals(message2, user1.retrieveMessages().get(1));
        assertEquals(2 ,user1.retrieveMessages().size());
        assertFalse(user1.retrieveMessages().get(1).getRead());
        assertEquals(0, user1.retrieveMessages().get(1).getReadCount());
        assertEquals("a: b; sent by name2", user1.checkMessage(user1.retrieveMessages().get(1)));
        assertTrue(user1.retrieveMessages().get(0).getRead());
        assertEquals(1, user1.retrieveMessages().get(1).getReadCount());
    }

    @Test
    void messageSendTest(){
        User user1 = new User("name1", "pass1");
        User user2 = new User("name2", "pass2");
        Product prod1 = new Product("name1", "description", 0.01, user1);
        Product prod2 = new Product("name2", "description", 0.01, user2);
        user1.sendMessage("a", "b", user2);
        assertEquals(1 ,user2.retrieveMessages().size());
        assertEquals("a: b; sent by name1", user2.checkMessage(user2.retrieveMessages().get(0)));
        user1.sendMessage("a", "b", user2);
        assertEquals(2 ,user2.retrieveMessages().size());
        assertEquals("a: b; sent by name1", user2.checkMessage(user2.retrieveMessages().get(1)));
        user1.sendMessage("a", "b", user2, prod1, prod2);
        assertEquals(3 ,user2.retrieveMessages().size());
        assertEquals("a: b; sent by name1", user2.checkMessage(user2.retrieveMessages().get(2)));
    }

}