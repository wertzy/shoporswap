public class Message {
    private String name;
    private String body;
    private User recipient;
    private User sender;
    private boolean read;
    private int readCount;
    private Product incomingSwap;
    private Product outgoingSwap;

    public Message(){

    }

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

    //Alternate constructor, for use in swap code.
    public Message(String name, String body, User recipient, User sender, Product incomingSwap, Product outgoingSwap){
        this.name = name;
        this.body = body;
        this.recipient = recipient;
        this.sender = sender;
        this.incomingSwap = incomingSwap;
        this.outgoingSwap = outgoingSwap;
    }

    //Returns a line of text containing the message's contents and sender.
    public String checked(){
        this.read = true;
        readCount++;
        String returnable = "";
        returnable+=name + ": " + body + "; sent by " + sender.getAccountName();
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

    public void setName(String nameIn){
        this.name = nameIn;
    }

    public void setBody(String bodyIn){
        this.body = bodyIn;
    }

    public void setRecipient(User recipientIn){
        this.recipient = recipientIn;
    }

    public void setSender(User senderIn){
        this.sender = senderIn;
    }

    public void setIncomingSwap(Product incomingSwapIn){
        this.incomingSwap = incomingSwapIn;
    }

    public void setOutgoingSwap(Product outgoingSwapIn){
        this.outgoingSwap = outgoingSwapIn;
    }

    public void setRead(boolean readIn){
        this.read = readIn;
    }

    public void setReadCount(int countIn){
        this.readCount = countIn;
    }
}
