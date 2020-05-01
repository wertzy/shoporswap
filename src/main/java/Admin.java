public class Admin extends Account {

    /**
     * Default constructor for Admin Account
     */
    public Admin(){
        super("DefaultAdmin", "DefaultPassword");
    }

    /**
     * Constructor for Admin Account
     * @param nameIn the name of the Admin Account
     * @param passwordIn the password of the Admin Account
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Admin(String nameIn, String passwordIn){
        super(nameIn, passwordIn);
    }

    /**
     * Method to freeze accounts
     * @param clientIn the Client to freeze
     * @return the Client frozen
     */
    public Client freezeAccount(Client clientIn){
        clientIn.setIsFrozen(true);
        return clientIn;
    }

    /**
     * Method to freeze accounts
     * @param clientIn the Client to unfreeze
     * @return the Client frozen
     */
    public Client unfreezeAccount(Client clientIn){
        clientIn.setIsFrozen(false);
        return clientIn;
    }

}
