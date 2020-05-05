public class ReportMessage extends AbstractMessage{

    Account reportedAccount;

    /**
     * Default constructor for ReportMessage object
     */
    public ReportMessage(){
        super();
        this.setSubject("DefaultReport");
        this.reportedAccount = null;
    }

    /**
     * Constructor for ReportMessage object
     * @param senderIn the sender of the ReportMessage
     * @param contentIn the content of the ReportMessage
     * @param reportedAccountIn the reported account of the ReportMessage
     * @throws IllegalArgumentException if senderIn is invalid
     * @throws IllegalArgumentException if contentIn is invalid
     */
    public ReportMessage(Account senderIn, String contentIn, Account reportedAccountIn){
        super(senderIn, "Report: " + reportedAccountIn.getAccountName(), contentIn);
        this.reportedAccount = reportedAccountIn;
    }

    /**
     * Accessor method for the reported account property of the ReportMesaage
     * @return the reported account of the ReportMessage
     */
    public Account getReportedAccount(){
        return this.reportedAccount;
    }

    /**
     * Mutator method for the reported account property of the ReportMessage
     * @param reportedAccountIn the desired reported account of the ReportMessage
     */
    public void setReportedAccount(Account reportedAccountIn){
        this.reportedAccount = reportedAccountIn;
    }

}
