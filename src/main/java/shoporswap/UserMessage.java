package shoporswap;

public class UserMessage extends AbstractMessage {

    /**
     * Default constructor for shoporswap.UserMessage object
     */
    public UserMessage(){
        super();
    }

    /**
     * Constructor for shoporswap.UserMessage object
     * @param senderIn the sender of the shoporswap.UserMessage
     * @param recipientIn the recipient of the shoporswap.UserMessage
     * @param subjectIn the subject of the shoporswap.UserMessage
     * @param contentIn the content of the shoporswap.UserMessage
     * @throws IllegalArgumentException if senderIn is invalid
     * @throws IllegalArgumentException if subjectIn is invalid
     * @throws IllegalArgumentException if contentIn is invalid
     */
    public UserMessage(Client senderIn, Account recipientIn, String subjectIn, String contentIn){
        super(senderIn, recipientIn, subjectIn, contentIn);
    }

}
