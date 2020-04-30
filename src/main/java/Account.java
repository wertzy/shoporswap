import java.util.*;

public abstract class Account {

    private String accountName;
    private String accountPassword;
    private boolean isFrozen;

    /**
     * Default constructor for Account class
     */
    public Account(){

    }

    /**
     * Constructor for Account class
     * @param nameIn the name of the Account
     * @param passwordIn the password of the Account
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Account(String nameIn, String passwordIn){

    }

    /**
     * Accessor method for the account name property of the Account
     * @return the name of the Account
     */
    public final String getAccountName(){
        return "";
    }

    /**
     * Accessor method for the account password property of the Account
     * @return the password of the Account
     */
    public final String getAccountPassword(){
        return "";
    }

    /**
     * Accessor method for the account frozen status of the Account
     * @return the frozen status of the Account
     */
    public final boolean getIsFrozen(){
        return false;
    }

    /**
     * Mutator method for the account name property
     * @param nameIn the desired name
     * @throws IllegalArgumentException if nameIn is invalid
     */
    public final void setAccountName(String nameIn){

    }

    /**
     * Mutator method for the account password property
     * @param passwordIn the desired password
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public final void setAccountPassword(String passwordIn){

    }

    /**
     * Mutator method for the account frozen status property
     * @param isFrozenIn the desired frozen status
     */
    public final void setIsFrozen(boolean isFrozenIn){

    }

    /**
     * Determines if a desired account name is valid
     * @param nameIn the desired account name
     * @return true if the desired account name is valid, false otherwise
     */
    public static final boolean isValidAccountName(String nameIn){
        return false;
    }

    /**
     * Determines if a desired account password is valid
     * @param passwordIn the desired account password
     * @return true if the desired account password is valid, false otherwise
     */
    public static final boolean isValidAccountPassword(String passwordIn){
        return false;
    }

}
