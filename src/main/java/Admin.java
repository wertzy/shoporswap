public class Admin extends Account {

    /**
     * Default constructor for Admin Account
     */
    public Admin(){

    }

    /**
     * Constructor for Admin Account
     * @param nameIn the name of the Admin Account
     * @param passwordIn the password of the Admin Account
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Admin(String nameIn, String passwordIn){

    }

    /**
     * Method to freeze accounts
     * @param clientIn the Client to freeze
     * @return the Client frozen
     */
    public Client freezeAccount(Client clientIn){
        return null;
    }

    /**
     * Method to freeze accounts
     * @param clientIn the Client to unfreeze
     * @return the Client frozen
     */
    public Client unfreezeAccount(Client clientIn){
        return null;
    }

}
