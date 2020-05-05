import java.util.Date;

public abstract class AbstractMessage {

    Account sender;
    String subject;
    String content;

    /**
     * Default constructor for an AbstractMessage object
     */
    public AbstractMessage(){
        //TODO complete implementation after automated test implementation
    }

    /**
     * Constructor for an AbstractMessage object
     * @param sender the sender of the AbstractMessage
     * @param subject the subject of the AbstractMessage
     * @param content the content of the AbstractMessage
     */
    public AbstractMessage(Account sender, String subject, String content){
        //TODO complete implementation after automated test implementation
    }

    /**
     * Accessor method for the sender property of the AbstractMessage object
     * @return the recipient of the message
     */
    public final Account getSender(){
        //TODO complete implementation after automated test implementation
        return null;
    }

    /**
     * Accessor method for the subject property of the AbstractMessage object
     * @return the subject of the message
     */
    public final String getSubject(){
        //TODO complete implementation after automated test implementation
        return null;
    }

    /**
     * Accessor method for the content property of the AbstractMessage object
     * @return
     */
    public final String getContent(){
        //TODO complete implementation after automated test implementation
        return null;
    }

    /**
     * Mutator method for the sender property of the AbstractMessage
     * @param senderIn the desired sender of the AbstractMessage
     */
    public void setSender(Account senderIn){
        //TODO complete implementation after automated test implementation
    }

    /**
     * Mutator method for the subject property of the AbstractMessage
     * @param subjectIn the desired subject of the AbstractMessage
     */
    public void setSubject(String subjectIn){
        //TODO complete implementation after automated test implementation
    }

    /**
     * Mutator method for the content property of the AbstractMessage
     * @param contentIn the desired content of the AbstractMessage
     */
    public void setContent(String contentIn){
        //TODO complete implementation after automated test implementation
    }

    /**
     * Determines whether a desired sender for the AbstractMessage is valid or not
     * @param senderIn the desired sender for the AbstractMessage
     * @return true if senderIn is valid, false otherwise
     */
    public static boolean isValidMessageSender(Account senderIn){
        //TODO complete implementation after automated test implementation
        return false;
    }

    /**
     * Determines whether a desired subject for the AbstractMessage is valid or not
     * @param subjectIn the desired subject for the AbstractMessage
     * @return true if subjectIn is valid, false otherwise
     */
    public static boolean isValidMessageSubject(String subjectIn){
        //TODO complete implementation after automated test implementation
        return false;
    }

    /**
     * Determines whether a desired content for the AbstractMessage is valid or not
     * @param contentIn the desired content for the AbstractMessage
     * @return true if contentIn is valid, false otherwise
     */
    public static boolean isValidMessageContent(String contentIn){
        //TODO complete implementation after automated test implementation
        return false;
    }

}
