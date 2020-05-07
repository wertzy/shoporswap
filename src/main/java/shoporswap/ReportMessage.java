package shoporswap;

public class ReportMessage extends AbstractMessage{

    Account reportedAccount;

    /**
     * Default constructor for shoporswap.ReportMessage object
     */
    public ReportMessage(){
        super();
        this.setSubject("DefaultReport");
        this.reportedAccount = null;
    }

    /**
     * Constructor for shoporswap.ReportMessage object
     * @param senderIn the sender of the shoporswap.ReportMessage
     * @param recipientIn the recipient of the shoporswap.ReportMessage
     * @param contentIn the content of the shoporswap.ReportMessage
     * @param reportedAccountIn the reported account of the shoporswap.ReportMessage
     * @throws IllegalArgumentException if senderIn is invalid
     * @throws IllegalArgumentException if contentIn is invalid
     */
    public ReportMessage(Account senderIn, Admin recipientIn, String contentIn, Account reportedAccountIn){
        super(senderIn, recipientIn, "Report: " + reportedAccountIn.getAccountName(), contentIn);
        this.reportedAccount = reportedAccountIn;
    }

    /**
     * Accessor method for the reported account property of the ReportMesaage
     * @return the reported account of the shoporswap.ReportMessage
     */
    public Account getReportedAccount(){
        return this.reportedAccount;
    }

    /**
     * Mutator method for the reported account property of the shoporswap.ReportMessage
     * @param reportedAccountIn the desired reported account of the shoporswap.ReportMessage
     */
    public void setReportedAccount(Account reportedAccountIn){
        this.reportedAccount = reportedAccountIn;
    }

}
