public class AbstractMessageFactory {

    /**
     * Default constructor for AbstractMessageFactory object
     */
    public AbstractMessageFactory(){

    }

    /**
     * Gets a message of a desired type
     * @param typeIn the type of message
     * @return a new message instance of type typeIn
     * @throws IllegalArgumentException if typeIn is invalid (if typeIn does not equal "user" or "report")
     */
    public AbstractMessage getMessage(String typeIn){
        if(typeIn.compareToIgnoreCase("user") == 0){
            return new UserMessage();
        }
        if(typeIn.compareToIgnoreCase("report") == 0){
            return new ReportMessage();
        }
        throw new IllegalArgumentException("Message type is invalid");
    }

}
