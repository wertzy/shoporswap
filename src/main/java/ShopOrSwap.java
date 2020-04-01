import java.util.*;

public class ShopOrSwap implements BasicAPI{

    private List<User> userList;
    private List<Product> productList;

    /**
     * Default constructor for a ShopOrSwap object
     */
    public ShopOrSwap(){
        //TODO implement method to pass corresponding tests after the tests have been written
    }

    /**
     * Constructor for a ShopOrSwap object
     * @param users the users to import into the ShopOrSwap
     * @throws IllegalArgumentException if there are multiple Users with the same account name
     */
    public ShopOrSwap(List<User> users){
        //TODO implement method to pass corresponding tests after the tests have been written
    }

    /**
     * Constructor for a ShopOrSwap object
     * @param users the users to import into the ShopOrSwap
     * @param products the products to import into the ShopOrSwap
     * @throws IllegalArgumentException if there are multiple Users with the same account name
     * @throws IllegalArgumentException if there are Products whose merchant is not a User in users
     */
    public ShopOrSwap(List<User> users, List<Product> products){
        //TODO implement method to pass corresponding tests after the tests have been written
    }

    /**
     * signs a User into the program
     * @param accountName the account name of the User to sign in
     * @param password the password of the User to sign in
     */
    @Override
    public User signIn(String accountName, String password) {
        //TODO implement method to pass corresponding tests after the tests have been written
        return null;
    }

    /**
     * signs a User out of the program
     * @param accountName the account name of the User to sign out
     * @param password the password of the User to sign out
     */
    @Override
    public User signOut(String accountName, String password) {
        //TODO implement method to pass corresponding tests after the tests have been written
        return null;
    }

    /**
     * creates a User in the program
     * @param accountName the account name of the User to create
     * @param password the password of the User to create
     * @return the new User
     */
    @Override
    public User createAccount(String accountName, String password) {
        //TODO implement method to pass corresponding tests after the tests have been written
        return null;
    }

    /**
     * adds a User to the program
     * @param user the User to create
     * @return the User added
     */
    @Override
    public User addAccount(User user){
        return null;
    }

    /**
     * removes a User from the program
     * @param user the User to remove
     * @return the User removed
     * @throws NoSuchElementException if the User to remove does not exist in the program
     */
    @Override
    public User removeAccount(User user){
        return null;
    }

    /**
     * finds a User in the program
     * @param user the User to find
     * @return the User found
     */
    @Override
    public User findAccount(User user){
        return null;
    }

    /**
     * Creates a Product to sell by a User
     * @param name the name of the Product to sell
     * @param description the description of the Product to sell
     * @param price the price of the Product to sell
     * @param merchant the User merchant of the Product to sell
     * @return the Product object
     */
    @Override
    public Product createSellProduct(String name, String description, String price, User merchant) {
        //TODO implement method to pass corresponding tests after the tests have been written
        return null;
    }

    /**
     * Creates a Product to swap by a User
     * @param name the name of the Product to swap
     * @param description the description of the Product to swap
     * @param price the price of the Product to swap
     * @param merchant the User merchant of the Product to swap
     * @return the Product object
     */
    @Override
    public Product createSwapProduct(String name, String description, String price, User merchant) {
        //TODO implement method to pass corresponding tests after the tests have been written
        return null;
    }

    /**
     * Views the Products listed by a User
     * @param user the User whose products are being viewed
     * @return a list of the User's Products
     */
    @Override
    public List<Product> viewUserProducts(User user) {
        //TODO implement method to pass corresponding tests after the tests have been written
        return null;
    }

    /**
     * Views the Products listed as sell by all Users
     * @return a list of the Products listed as sell
     */
    @Override
    public List<Product> viewSellProducts() {
        //TODO implement method to pass corresponding tests after the tests have been written
        return null;
    }

    /**
     * Views the Products listed as swap by all Users
     * @return a list of the Products listed as swap
     */
    @Override
    public List<Product> viewSwapProducts() {
        //TODO implement method to pass corresponding tests after the tests have been written
        return null;
    }

    /**
     * Accessor method for getting the list of Users of the ShopOrSwap object
     * @return
     */
    @Override
    public List<User> getUserList() {
        return null;
    }

    /**
     * Accessor method for getting the list of Products of the ShopOrSwap object
     * @return
     */
    @Override
    public List<Product> getProductList() {
        return null;
    }

    /**
     * Checks to see if a list of Users is valid (all Users have different account names, Users list is not empty)
     * @param users the list of Users to validate
     * @return true if the list of Users is valid, false otherwise
     */
    public static boolean isValidUserList(List<User> users) {
        return false;
    }

    /**
     * Checks to see if a list of Products is valid (all Products have User merchants in users, Products list is not empty, all Products have different names)
     * @param products the list of Products to validate
     * @param users the list of Users to check are merchants
     * @return true if the list of Products is valid, false otherwise
     */
    public static boolean isValidProductList(List<Product> products, List<User> users) {
        return false;
    }
}
