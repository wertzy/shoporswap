package shoporswap;

public class Admin extends Account {

    /**
     * Default constructor for shoporswap.Admin shoporswap.Account
     */
    public Admin(){
        super("DefaultAdmin", "DefaultPassword");
    }

    /**
     * Constructor for shoporswap.Admin shoporswap.Account
     * @param nameIn the name of the shoporswap.Admin shoporswap.Account
     * @param passwordIn the password of the shoporswap.Admin shoporswap.Account
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Admin(String nameIn, String passwordIn){
        super(nameIn, passwordIn);
    }

    /**
     * Method to freeze accounts
     * @param clientIn the shoporswap.Client to freeze
     * @return the shoporswap.Client frozen
     */
    public Client freezeAccount(Client clientIn){
        clientIn.setIsFrozen(true);
        return clientIn;
    }

    /**
     * Method to freeze accounts
     * @param clientIn the shoporswap.Client to unfreeze
     * @return the shoporswap.Client frozen
     */
    public Client unfreezeAccount(Client clientIn){
        clientIn.setIsFrozen(false);
        return clientIn;
    }

}
