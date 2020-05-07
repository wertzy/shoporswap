package io;

import shoporswap.*;
public class MessageRecord {

    private String messageType;
    private String senderName;
    private String senderType;
    private String recipientName;
    private String recipientType;
    private String subject;
    private String content;
    private String reportedAccountName;
    private String reportedAccountType;

    public MessageRecord(){
        this.setMessageType(null);
        this.setSenderName(null);
        this.setSenderType(null);
        this.setRecipientName(null);
        this.setRecipientType(null);
        this.setSubject(null);
        this.setContent(null);
        this.setReportedAccountName(null);
        this.setReportedAccountType(null);
    }

    public MessageRecord(AbstractMessage messageIn){
        this.setMessageType(messageIn.getClass().getName());
        this.setSenderName(messageIn.getSender().getAccountName());
        this.setSenderType(messageIn.getSender().getClass().getName());
        this.setRecipientName(messageIn.getRecipient().getAccountName());
        this.setRecipientType(messageIn.getRecipient().getClass().getName());
        this.setSubject(messageIn.getSubject());
        this.setContent(messageIn.getContent());
        if(messageIn instanceof ReportMessage){
            this.setReportedAccountName(((ReportMessage) messageIn).getReportedAccount().getAccountName());
            this.setReportedAccountType(((ReportMessage) messageIn).getReportedAccount().getClass().getName());
        }else {
            this.setReportedAccountName(null);
            this.setReportedAccountType(null);
        }
    }

    public AbstractMessage toMessage(){
        AbstractMessage messageOut;
        if(this.getMessageType().compareToIgnoreCase(UserMessage.class.getName()) == 0){
            messageOut = new UserMessage();
        }else{
            messageOut = new ReportMessage();
            Account reportedAccountOut;
            if(this.getReportedAccountType().compareToIgnoreCase(Client.class.getName()) == 0){
                reportedAccountOut = new Client();
            }else{
                reportedAccountOut = new Admin();
            }
            reportedAccountOut.setAccountName(this.getReportedAccountName());
        }

        Account senderOut;
        if(this.getSenderType().compareToIgnoreCase(Client.class.getName()) == 0){
            senderOut = new Client();
        }else{
            senderOut = new Admin();
        }
        senderOut.setAccountName(this.getSenderName());
        messageOut.setSender(senderOut);

        Account recipientOut;
        if(this.getRecipientType().compareToIgnoreCase(Client.class.getName()) == 0){
            recipientOut = new Client();
        }else{
            recipientOut = new Admin();
        }
        recipientOut.setAccountName(this.getRecipientName());
        messageOut.setRecipient(recipientOut);

        messageOut.setSubject(this.getSubject());
        messageOut.setContent(this.getContent());

        return messageOut;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderNameIn) {
        this.senderName = senderNameIn;
    }

    public String getRecipientName() {
        return this.recipientName;
    }

    public void setRecipientName(String recipientNameIn) {
        this.recipientName = recipientNameIn;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subjectIn) {
        this.subject = subjectIn;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String contentIn) {
        this.content = contentIn;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public void setMessageType(String messageTypeIn) {
        this.messageType = messageTypeIn;
    }

    public String getSenderType() {
        return this.senderType;
    }

    public void setSenderType(String senderTypeIn) {
        this.senderType = senderTypeIn;
    }

    public String getRecipientType() {
        return this.recipientType;
    }

    public void setRecipientType(String recipientTypeIn) {
        this.recipientType = recipientTypeIn;
    }

    public String getReportedAccountName() {
        return reportedAccountName;
    }

    public void setReportedAccountName(String reportedAccountName) {
        this.reportedAccountName = reportedAccountName;
    }

    public String getReportedAccountType() {
        return this.reportedAccountType;
    }

    public void setReportedAccountType(String reportedAccountTypeIn) {
        this.reportedAccountType = reportedAccountTypeIn;
    }
}
