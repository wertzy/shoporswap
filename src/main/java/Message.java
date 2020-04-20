public class Message {
    private String name;
    private String body;
    private User recipient;
    private User sender;
    private boolean read;
    private int readCount;
    private Product incomingSwap;
    private Product outgoingSwap;

    public Message(String name, String body, User recipient, User sender){
        this.name = name;
        this.body = body;
        this.recipient = recipient;
        this.sender = sender;
        this.read = false;
        this.readCount = 0;
        incomingSwap = null;
        outgoingSwap = null;
    }

    public Message(String name, String body, User recipient, User sender, Product incomingSwap, Product outgoingSwap){
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

    public String checked(){
        String returnable = "";
        returnable+=name + ": " + body + "; Sent by" + sender.getAccountName();
        return returnable;
    }

    public void confirm(){

    }

    public String getName(){return name;}
    public String getBody(){return body;}
    public User getRecipient(){return recipient;}
    public User getSender(){return sender;}
    public Product getIncomingSwap(){return incomingSwap;}
    public Product getOutgoingSwap(){return outgoingSwap;}
    public boolean getRead(){return read;}
    public int getReadCount(){return readCount;}
}
