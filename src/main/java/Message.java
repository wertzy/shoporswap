public class Message {
    private String name;
    private String body;
    private String recipient;
    private String sender;
    private boolean read;

    public Message(String name, String body, String recipient, String sender){
        this.name = name;
        this.body = body;
        this.recipient = recipient;
        this.sender = sender;
        this.read = false;
    }

    public String getName(){return name;}
    public String getBody(){return body;}
    public String getRecipient(){return recipient;}
    public String getSender(){return sender;}
    public boolean getRead(){return read;}
}
