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
     * @param products the products to import into the ShopOrSwap
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
     * @return
     */
    @Override
    public User createAccount(String accountName, String password) {
        //TODO implement method to pass corresponding tests after the tests have been written
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
}
