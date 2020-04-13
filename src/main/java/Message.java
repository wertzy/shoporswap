public class Message {
    private String name;
    private String body;
    private String recipient;
    private String sender;
    private boolean read;
    private int readCount;
    private Product incomingSwap;
    private Product outgoingSwap;

    public Message(String name, String body, String recipient, String sender){
        this.name = name;
        this.body = body;
        this.recipient = recipient;
        this.sender = sender;
        this.read = false;
        this.readCount = 0;
        incomingSwap = null;
        outgoingSwap = null;
    }

    public Message(String name, String body, String recipient, String sender, Product incomingSwap, Product outgoingSwap){
        this.name = name;
        this.body = body;
        this.recipient = recipient;
        this.sender = sender;
        this.incomingSwap = incomingSwap;
        this.outgoingSwap = outgoingSwap;
    }

    public void messageOpened(){
        readCount++;
        this.read = true;
    }

    public void confirm(){

    }

    public String getName(){return name;}
    public String getBody(){return body;}
    public String getRecipient(){return recipient;}
    public String getSender(){return sender;}
    public boolean getRead(){return read;}
    public int getReadCount(){return readCount;}
}
