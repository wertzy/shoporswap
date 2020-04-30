import java.util.*;

public abstract class Account {

    private String accountName;
    private String accountPassword;
    private List<AbstractProduct> myProductList;
    private boolean isFrozen;

    /**
     * Default constructor for Account class
     */
    public Account(){

    }

    /**
     * Constructor for Account class
     * @param nameIn
     * @param passwordIn
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Account(String nameIn, String passwordIn){

    }

    /**
     * Constructor for Account class
     * @param nameIn
     * @param passwordIn
     * @param productListIn
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Account(String nameIn, String passwordIn, List<AbstractProduct> productListIn){

    }

    /**
     * Adds a product to the Account's product list
     * @return the product added to the Account
     */
    public AbstractProduct addProduct(){
        return null;
    }

    /**
     * Finds a product in the Account's product list
     * @return the product found in the Account
     * @throws NoSuchElementException if the product does not exist in the Account
     */
    public AbstractProduct findProduct(){
        return null;
    }

    /**
     * Removes a product from the Account's product list
     * @return the product removed from the Account
     * @throws NoSuchElementException if the product does not exist in the Account
     */
    public AbstractProduct removeProduct(){
        return null;
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
     * Accessor method for the account product list of the Account
     * @return the list of products of the Account
     */
    public final List<AbstractProduct> getMyProductList(){
        return null;
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
     * Mutator method for the account product property
     * @param productListIn the desired list of products
     * @throws IllegalArgumentException if at least one product in ProductListIn is invalid
     */
    public final void setMyProductList(List<AbstractProduct> productListIn){

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
