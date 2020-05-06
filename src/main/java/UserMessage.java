public class UserMessage extends AbstractMessage {

    /**
     * Default constructor for UserMessage object
     */
    public UserMessage(){
        super();
    }

    /**
     * Constructor for UserMessage object
     * @param senderIn the sender of the UserMessage
     * @param recipientIn the recipient of the UserMessage
     * @param subjectIn the subject of the UserMessage
     * @param contentIn the content of the UserMessage
     * @throws IllegalArgumentException if senderIn is invalid
     * @throws IllegalArgumentException if subjectIn is invalid
     * @throws IllegalArgumentException if contentIn is invalid
     */
    public UserMessage(Client senderIn, Account recipientIn, String subjectIn, String contentIn){
        super(senderIn, recipientIn, subjectIn, contentIn);
    }

}
