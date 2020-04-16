import java.util.*;

public class ShopOrSwap implements BasicAPI{

    private List<User> userList;
    private List<Product> productList;

    /**
     * Default constructor for a ShopOrSwap object
     */
    public ShopOrSwap(){
        // implement method to pass corresponding tests after the tests have been written
        this.userList = new ArrayList<User>();
        this.productList = new ArrayList<Product>();
    }

    /**
     * Constructor for a ShopOrSwap object
     * @param users the users to import into the ShopOrSwap
     * @throws IllegalArgumentException if there are multiple Users with the same account name
     */
    public ShopOrSwap(List<User> users){
        // implement method to pass corresponding tests after the tests have been written
        if(!isValidUserList(users)){
            throw new IllegalArgumentException("List of Users to import is invalid");
        }
        this.userList = users;
        this.productList = new ArrayList<Product>();
    }

    /**
     * Constructor for a ShopOrSwap object
     * @param users the users to import into the ShopOrSwap
     * @param products the products to import into the ShopOrSwap
     * @throws IllegalArgumentException if there are multiple Users with the same account name
     * @throws IllegalArgumentException if there are Products whose merchant is not a User in users
     */
    public ShopOrSwap(List<User> users, List<Product> products){
        // implement method to pass corresponding tests after the tests have been written
        if(!isValidUserList(users)){
            throw new IllegalArgumentException("List of Users to import is invalid");
        }
        if(!isValidProductList(products, users)){
            throw new IllegalArgumentException("List of Products to import is invalid");
        }
        this.userList = users;
        this.productList = products;
    }

    /**
     * signs a User into the program
     * @param accountName the account name of the User to sign in
     * @param password the password of the User to sign in
     */
    @Override
    public User signIn(String accountName, String password) {
        // implement method to pass corresponding tests after the tests have been written
        for(User user : this.userList){
            if(user.getAccountName().compareTo(accountName) == 0 && user.getPassword().compareTo(password) == 0){
                return user;
            }
        }
        return null;
    }

    /**
     * signs a User out of the program
     * @param accountName the account name of the User to sign out
     * @param password the password of the User to sign out
     */
    @Override
    public User signOut(String accountName, String password) {
        // implement method to pass corresponding tests after the tests have been written
        for(User user : this.userList){
            if(user.getAccountName().compareTo(accountName) == 0 && user.getPassword().compareTo(password) == 0){
                return user;
            }
        }
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
        // implement method to pass corresponding tests after the tests have been written
        User nextUser = new User(accountName, password);
        return this.addAccount(nextUser);
    }

    /**
     * adds a User to the program
     * @param user the User to create
     * @return the User added
     */
    @Override
    public User addAccount(User user){
        // implement method to pass corresponding tests after the tests have been written
        for(User aUser : this.userList){
            if(aUser.getAccountName().compareTo(user.getAccountName()) == 0){
                return null;
            }
        }
        this.userList.add(user);
        return user;
    }

    /**
     * removes a User from the program
     * @param user the User to remove
     * @return the User removed
     * @throws NoSuchElementException if the User to remove does not exist in the program
     */
    @Override
    public User removeAccount(User user){
        // implement method to pass corresponding tests after the tests have been written
        for(User aUser : this.userList){
            if(aUser.getAccountName().compareTo(user.getAccountName()) == 0){
                int nextUserIndex = this.userList.indexOf(aUser);
                return this.userList.remove(nextUserIndex);
            }
        }
        throw new NoSuchElementException("User to remove does not exist in the system");
    }

    /**
     * finds a User in the program
     * @param user the User to find
     * @return the User found
     */
    @Override
    public User findAccount(User user){
        // implement method to pass corresponding tests after the tests have been written
        for(User aUser : this.userList){
            if(aUser.getAccountName().compareTo(user.getAccountName()) == 0){
                return aUser;
            }
        }
        return null;
    }

    /**
     * finds a User in the program
     * @param accountName the account name of the User to find
     * @return the User found
     */
    public User findAccount(String accountName){
        // implement method to pass corresponding tests after the tests have been written
        for(User aUser : this.userList){
            if(aUser.getAccountName().compareTo(accountName) == 0){
                return aUser;
            }
        }
        return null;
    }

    /**
     * Creates a Product to sell by a User
     * @param name the name of the Product to sell
     * @param description the description of the Product to sell
     * @param price the price of the Product to sell
     * @param merchant the User merchant of the Product to sell
     * @return the Product object
     * @throws IllegalArgumentException if the User merchant does not exist in the system
     */
    @Override
    public Product createSellProduct(String name, String description, String price, User merchant) {
        // implement method to pass corresponding tests after the tests have been written
        User merchantSystem;
        if(this.findAccount(merchant) == null){
            throw new IllegalArgumentException("Merchant User does not exist in the system");
        }else{
            merchantSystem = this.findAccount(merchant);
        }
        try{
            Double.parseDouble(price);
        }catch(Exception e){
            throw new IllegalArgumentException("Price is cannot be nonnumeric");
        }
        Product nextProduct = new Product(name, description, Double.parseDouble(price), merchantSystem);
        nextProduct.addTag("sell");
        List<Product> tempNextProductList = new ArrayList<Product>();
        tempNextProductList.add(nextProduct);
        if(!isValidProductList(tempNextProductList, this.userList)){
            return null;
        }else{
            this.productList.add(nextProduct);
            return this.productList.get(this.productList.size() - 1);
        }
    }
    /**
     * Creates a Product to swap by a User
     * @param product the name of the Product to remove
     * @throws IllegalArgumentException if the User merchant does not exist in the system
     */

    public Product removeSellProduct(Product product){
        Product returnProduct=findProduct(product.getName(),product.getMerchant());
        productList.remove(findProduct(product.getName(),product.getMerchant()));
        return returnProduct;
    }

    /**
     * Creates a Product to swap by a User
     * @param name the name of the Product to swap
     * @param description the description of the Product to swap
     * @param price the price of the Product to swap
     * @param merchant the User merchant of the Product to swap
     * @return the Product object
     * @throws IllegalArgumentException if the User merchant does not exist in the system
     */
    @Override
    public Product createSwapProduct(String name, String description, String price, User merchant) {
        // implement method to pass corresponding tests after the tests have been written
        User merchantSystem;
        if(this.findAccount(merchant) == null){
            throw new IllegalArgumentException("Merchant User does not exist in the system");
        }else{
            merchantSystem = this.findAccount(merchant);
        }
        try{
            Double.parseDouble(price);
        }catch(Exception e){
            throw new IllegalArgumentException("Price is cannot be nonnumeric");
        }
        Product nextProduct = new Product(name, description, Double.parseDouble(price), merchantSystem);
        nextProduct.addTag("swap");
        List<Product> tempNextProductList = new ArrayList<Product>();
        tempNextProductList.add(nextProduct);
        if(!isValidProductList(tempNextProductList, this.userList)){
            return null;
        }else{
            this.productList.add(nextProduct);
            return this.productList.get(this.productList.size() - 1);
        }
    }

    /**
     * Finds a Product from the User
     * @param name the name of the Product
     * @param user the User
     * @return the Product to find
     * @throws NoSuchElementException if the Product does not exist for the User
     */
    @Override
    public Product findProduct(String name, User user){
        // implement method to pass corresponding tests after the tests have been written
        for(Product product : this.productList){
            if(product.getName().compareToIgnoreCase(name) == 0 && product.getMerchant().getAccountName().compareTo(user.getAccountName()) == 0){
                return product;
            }
        }
        throw new NoSuchElementException("Product does not exist for the User in the system");
    }

    /**
     * Swaps a Product from one User with another Product from another User
     * @param product1 the Product offered by the User offering the swap
     * @param product2 the Product requested by the User accepting the swap
     */
    @Override
    public void swapProducts(Product product1, Product product2){
        // checks for the same Product
        if(product1.getName().compareToIgnoreCase(product2.getName()) == 0){
            throw new IllegalArgumentException("Products are the same");
        }

        // checks for the same User
        if(product1.getMerchant().getAccountName().compareToIgnoreCase(product2.getMerchant().getAccountName()) == 0){
            throw new IllegalArgumentException("Users are the same");
        }

        // checks for a non-existent User
        if(this.findAccount(product1.getMerchant().getAccountName()) == null || this.findAccount(product2.getMerchant().getAccountName()) == null){
            throw new IllegalArgumentException("Invalid merchant");
        }

        // checks for a non-existent Product
        if(this.findProduct(product1.getName(), this.findAccount(product1.getMerchant().getAccountName())) == null || this.findProduct(product2.getName(), this.findAccount(product2.getMerchant().getAccountName())) == null){
            throw new IllegalArgumentException("Invalid product");
        }

        // completing swap
        User merchant1 = product1.getMerchant();
        User merchant2 = product2.getMerchant();
        product1.setMerchant(merchant2);
        product2.setMerchant(merchant1);
    }

    /**
     * Views the Products listed by a User
     * @param user the User whose products are being viewed
     * @return a list of the User's Products
     */
    @Override
    public List<Product> getUserProducts(User user) {
        // implement method to pass corresponding tests after the tests have been written
        List<Product> products = new ArrayList<Product>();
        for(Product product : this.productList){
            if(product.getMerchant().getAccountName().compareTo(user.getAccountName()) == 0){
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Views the Products listed as sell by all Users
     * @return a list of the Products listed as sell
     */
    @Override
    public List<Product> getSellProducts() {
        // implement method to pass corresponding tests after the tests have been written
        List<Product> products = new ArrayList<Product>();
        for(Product product : this.productList){
            if(product.getTags().contains("sell")){
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Views the Products listed as swap by all Users
     * @return a list of the Products listed as swap
     */
    @Override
    public List<Product> getSwapProducts() {
        //TODO implement method to pass corresponding tests after the tests have been written
        List<Product> products = new ArrayList<Product>();
        for(Product product : this.productList){
            if(product.getTags().contains("swap")){
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Accessor method for getting the list of Users of the ShopOrSwap object
     * @return
     */
    @Override
    public List<User> getUserList() {
        // implement method to pass corresponding tests after the tests have been written
        return this.userList;
    }

    /**
     * Accessor method for getting the list of Products of the ShopOrSwap object
     * @return
     */
    @Override
    public List<Product> getProductList() {
        // implement method to pass corresponding tests after the tests have been written
        return this.productList;
    }

    /**
     * Checks to see if a list of Users is valid (all Users have different account names, Users list is not empty)
     * @param users the list of Users to validate
     * @return true if the list of Users is valid, false otherwise
     */
    public static boolean isValidUserList(List<User> users) {
        //TODO implement method to pass corresponding tests after the tests have been written
        if(users.size() == 0){
            return false;
        }
        List<String> userNames = new ArrayList<String>();
        for(User user : users) {
            if(userNames.contains(user.getAccountName())) {
                return false;
            }else{
                userNames.add(user.getAccountName());
            }
        }
        return true;
    }

    /**
     * Checks to see if a list of Products is valid (all Products have User merchants in users, Products list is not empty, all Products have different names)
     * @param products the list of Products to validate
     * @param users the list of Users to check are merchants
     * @return true if the list of Products is valid, false otherwise
     */
    public static boolean isValidProductList(List<Product> products, List<User> users) {
        // implement method to pass corresponding tests after the tests have been written
        if(products.isEmpty()){
            return false;
        }

        List<String> userNames = new ArrayList<String>();
        if(isValidUserList(users)){
            for (User user : users) {
                userNames.add(user.getAccountName());
            }
        }

        List<String> productNames = new ArrayList<String>();
        for(Product product : products){
            if(product.getMerchant() == null){
                return false;
            }
            if(!productNames.contains(product.getName().toUpperCase()) && userNames.contains(product.getMerchant().getAccountName())){
                productNames.add(product.getName().toUpperCase());
            }else{
                return false;
            }
        }

        return true;
    }

}
