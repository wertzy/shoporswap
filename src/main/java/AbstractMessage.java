import java.util.Date;
import java.util.regex.Pattern;

public abstract class AbstractMessage {

    private Account sender;
    private Account recipient;
    private String subject;
    private String content;

    /**
     * Default constructor for an AbstractMessage object
     */
    public AbstractMessage(){
        this.sender = null;
        this.recipient = null;
        this.setSubject("DefaultSubject");
        this.setContent("DefaultContent");
    }

    /**
     * Constructor for an AbstractMessage object
     * @param sender the sender of the AbstractMessage
     * @param subject the subject of the AbstractMessage
     * @param content the content of the AbstractMessage
     * @throws IllegalArgumentException if senderIn is invalid
     * @throws IllegalArgumentException if subjectIn is invalid
     * @throws IllegalArgumentException if contentIn is invalid
     */
    public AbstractMessage(Account sender, Account recipient, String subject, String content){
        this.setSender(sender);
        this.setRecipient(recipient);
        this.setSubject(subject);
        this.setContent(content);
    }

    /**
     * Accessor method for the sender property of the AbstractMessage object
     * @return the recipient of the AbstractMessage
     */
    public final Account getSender(){
        return this.sender;
    }

    /**
     * Accessor method for the subject property of the AbstractMessage object
     * @return the subject of the AbstractMessage
     */
    public final String getSubject(){
        return this.subject;
    }

    /**
     * Accessor method for the content property of the AbstractMessage object
     * @return the content of the AbstractMessage
     */
    public final String getContent(){
        return this.content;
    }

    /**
     * Accessor method for the recipient property of the AbstractMessage object
     * @return the recipient of the AbstractMessage
     */
    public final Account getRecipient(){
        return this.recipient;
    }

    /**
     * Mutator method for the sender property of the AbstractMessage
     * @param senderIn the desired sender of the AbstractMessage
     * @throws IllegalArgumentException if senderIn is invalid
     */
    public void setSender(Account senderIn){
        if(!isValidMessageSender(senderIn)){
            throw new IllegalArgumentException("Sender is invalid");
        }
        this.sender = senderIn;
    }

    /**
     * Mutator method for the subject property of the AbstractMessage
     * @param subjectIn the desired subject of the AbstractMessage
     * @throws IllegalArgumentException if subjectIn is invalid
     */
    public void setSubject(String subjectIn){
        if(!isValidMessageSubject(subjectIn)){
            throw new IllegalArgumentException("Subject is invalid");
        }
        this.subject = subjectIn;
    }

    /**
     * Mutator method for the content property of the AbstractMessage
     * @param contentIn the desired content of the AbstractMessage
     * @throws IllegalArgumentException if contentIn is invalid
     */
    public void setContent(String contentIn){
        if(!isValidMessageContent(contentIn)){
            throw new IllegalArgumentException("Content is invalid");
        }
        this.content = contentIn;
    }

    /**
     * Mutator method for the recipient property of the AbstractMessage
     * @param recipientIn the recipient of the AbstractMessage
     */
    public void setRecipient(Account recipientIn){
        this.recipient = recipientIn;
    }

    /**
     * Determines whether a desired sender for the AbstractMessage is valid or not
     * @param senderIn the desired sender for the AbstractMessage
     * @return true if senderIn is valid, false otherwise
     */
    public static boolean isValidMessageSender(Account senderIn){
        if(senderIn.getIsFrozen() == true){
            return false;
        }
        return true;
    }

    /**
     * Determines whether a desired subject for the AbstractMessage is valid or not
     * @param subjectIn the desired subject for the AbstractMessage
     * @return true if subjectIn is valid, false otherwise
     */
    public static boolean isValidMessageSubject(String subjectIn){
        if(subjectIn.length() > 50){
            return false;
        }
        if(subjectIn.isEmpty()){
            return false;
        }

        String firstChar = subjectIn.substring(0,1);
        if(Pattern.matches("\\W", firstChar)){
            return false;
        }
        if(subjectIn.length() > 1) {
            String lastChar = subjectIn.substring(subjectIn.length() - 1);
            if (Pattern.matches("\\W", firstChar) || Pattern.matches("\\W", lastChar)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether a desired content for the AbstractMessage is valid or not
     * @param contentIn the desired content for the AbstractMessage
     * @return true if contentIn is valid, false otherwise
     */
    public static boolean isValidMessageContent(String contentIn){
        if(contentIn.length() > 300){
            return false;
        }
        if(contentIn.isEmpty()){
            return false;
        }

        String firstChar = contentIn.substring(0,1);
        if(Pattern.matches("\\W", firstChar)){
            return false;
        }
        if(contentIn.length() > 1) {
            String lastChar = contentIn.substring(contentIn.length() - 1);
            if (Pattern.matches("\\W", firstChar) || Pattern.matches("\\W", lastChar)) {
                return false;
            }
        }
        return true;
    }

}
