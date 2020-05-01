import java.util.*;
import java.util.regex.Pattern;

public abstract class Account {

    private String accountName;
    private String accountPassword;
    private boolean isFrozen;

    /**
     * Default constructor for Account class
     */
    public Account(){
        this.accountName = "DefaultName";
        this.accountPassword = "DefaultPassword";
    }

    /**
     * Constructor for Account class
     * @param nameIn the name of the Account
     * @param passwordIn the password of the Account
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Account(String nameIn, String passwordIn){
        this.setAccountName(nameIn);
        this.setAccountPassword(passwordIn);
    }

    /**
     * Accessor method for the account name property of the Account
     * @return the name of the Account
     */
    public final String getAccountName(){
        return this.accountName;
    }

    /**
     * Accessor method for the account password property of the Account
     * @return the password of the Account
     */
    public final String getAccountPassword(){
        return this.accountPassword;
    }

    /**
     * Accessor method for the account frozen status of the Account
     * @return the frozen status of the Account
     */
    public final boolean getIsFrozen(){
        return this.isFrozen;
    }

    /**
     * Mutator method for the account name property
     * @param nameIn the desired name
     * @throws IllegalArgumentException if nameIn is invalid
     */
    public final void setAccountName(String nameIn){
        if(isValidAccountName(nameIn)){
            this.accountName = nameIn;
        }else {
            throw new IllegalArgumentException("Name is invalid");
        }
    }

    /**
     * Mutator method for the account password property
     * @param passwordIn the desired password
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public final void setAccountPassword(String passwordIn){
        if(isValidAccountName(passwordIn)){
            this.accountPassword = passwordIn;
        }else {
            throw new IllegalArgumentException("Password is invalid");
        }
    }

    /**
     * Mutator method for the account frozen status property
     * @param isFrozenIn the desired frozen status
     */
    public final void setIsFrozen(boolean isFrozenIn){
        this.isFrozen = isFrozenIn;
    }

    /**
     * Determines if a desired account name is valid
     * @param nameIn the desired account name
     * @return true if the desired account name is valid, false otherwise
     */
    public static final boolean isValidAccountName(String nameIn){
        if(nameIn.isEmpty()){
            return false;
        }
        if(nameIn.indexOf(" ") != -1){
            return false;
        }
        String exp = "[\\w]+\\z";
        return Pattern.matches(exp, nameIn);
    }

    /**
     * Determines if a desired account password is valid
     * @param passwordIn the desired account password
     * @return true if the desired account password is valid, false otherwise
     */
    public static final boolean isValidAccountPassword(String passwordIn){
        if(passwordIn.isEmpty()){
            return false;
        }
        String exp = "\\A[^\\s]+\\z";
        return Pattern.matches(exp, passwordIn);
    }

}
