import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

public class User {
    protected String accountName;
    protected String password;
    protected List<String> transactionHistory;
    protected ArrayList<Message> messages;
    protected static List<Product> productList = new ArrayList<Product>();
    protected static ArrayList<String> pastTransactions;
    protected ArrayList<Integer> ratingList = new ArrayList<>();
    protected double wallet;
    protected double ratingAverage;

    /**
     * Default constructor of a User
     */
    public User() {
        this.accountName = "accountname";
        this.password = "password";
        this.ratingAverage = 0.0;
        this.transactionHistory = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    /**
     * Constructor of a User
     * @throws IllegalArgumentException if either the account name or the password are invalid (checked using static methods below)
     * @param accountNameIn the desired account name for the User
     * @param passwordIn the desired password for the User
     */
    public User(String accountNameIn, String passwordIn) throws IllegalArgumentException{
        if(!isAccountNameValid(accountNameIn)){
            throw new IllegalArgumentException("invalid account name");
        }else {
            this.accountName = accountNameIn;
        }

        if(!isPasswordValid(passwordIn)){
            throw new IllegalArgumentException("invalid password");
        }else {
            this.password = passwordIn;
        }
        this.ratingAverage = 0.0;
        this.transactionHistory = new ArrayList<>(); // suggested revision
        this.messages = new ArrayList<Message>();
        this.wallet=0;
    }

    /**
     * Accessor for the account name property of a User
     * @return the account name of the User
     */
    public String getAccountName(){
        return this.accountName;
    }

    /**
     * Accessor for the password property of a User
     * @return the password of the User
     */
    public String getPassword(){
        return this.password;
    }
    public List<String> getTransactionHistory(){return this.transactionHistory;}

    /**
     * Accessor for the rating property of a User
     * @return the rating of the User
     */
    public double getRating(){
        return this.ratingAverage;
    }

    /**
     * Accessor for the wallet property of a User
     * @return the wallet of the User
     */
    public double getWallet(){
        return this.wallet;
    }


    /**
     * Checks to see if a desired account name has any illegal characters (spaces)
     * @param accountName the account name to check
     * @return true if a valid account name, false if an invalid account name
     */
    public static boolean isAccountNameValid(String accountName) {
        if(accountName.isEmpty()){
            return false;
        }
        String exp = "\\A[^\\s]+\\z";
        return Pattern.matches(exp, accountName);
    }

    /**
     * Checks to see if a desired password has any illegal characters (spaces)
     * @param password the password to check
     * @return true if a valid password, false if an invalid password
     */
    public static boolean isPasswordValid(String password) {
        if(password.isEmpty()){
            return false;
        }
        String exp = "\\A[^\\s]+\\z";
        return Pattern.matches(exp, password);
    }

    /**
     * Checks to see if a desired rating is valid (between 0 and 5 inclusive, no more than 2 decimal places)
     * @param rating the rating to check
     * @return true if a valid rating, false if an invalid rating
     */
    public static boolean isRatingValid(double rating) {
        String ratingStr = "" + rating;
        if(rating >= 0 && rating <= 5) {
            if (ratingStr.length() - ratingStr.lastIndexOf(".") > 3) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void sendMessage(String name, String body, User recipient){
        Message message = new Message(name, body, recipient, this);
        recipient.receiveMessage(message);
    }

    public void sendMessage(String name, String body, User recipient, Product incomingSwap, Product outgoingSwap){
        Message message = new Message(name, body, recipient, this, incomingSwap, outgoingSwap);
        recipient.receiveMessage(message);
    }

    public void receiveMessage(Message message){
        messages.add(message);
    }

    public String checkMessage(Message message){
        return message.checked();
    }
    public ArrayList<Message> retrieveMessages(){return messages;}

    /**
     * Views the Collection of clothing available to see (varies by seller, shopper, swapper)
     * @return the viewable clothing (as a list) of clothing
     */
    public void viewClothing(){
        for(int i=0;i<transactionHistory.size();i++){
            System.out.println(transactionHistory.get(i)+"\n");
        }

    }


    public static void addClothing(String name, String description, User self){
        Product newProduct=new Product(name,description,self);
        List<Product> tempNextProductList = new ArrayList<Product>();
        tempNextProductList.add(newProduct);
        productList.add(tempNextProductList.get(0));
    }

    public Product find(String name){
        for(Product product : productList){
            if(product.getName().compareToIgnoreCase(name) == 0){
                return product;
            }
        }
        throw new NoSuchElementException("Product does not exist for the User in the system");
    }


    public void buy(String productName, User merchant,int amount){
        //Product merchantProduct=merchant.find(name);
        String transaction = merchant.accountName+":"+ productName;
        transactionHistory.add(transaction);
        walletSubtract(amount);
        merchant.walletAdd(amount);
    }

    public void sell(String name, String description,User self){
        Product product=new Product(name,description, self);
        productList.add(product);
    }


    public void rate(User merchant,int rating){
        if(rating>5 || rating<1){
            throw new IllegalArgumentException("invalid rating");
        }
        merchant.ratingList.add(rating);
        merchant.calcRatingAverage();

    }

    public void calcRatingAverage(){
        double tempRatingAverage=0;
        for (int i=0; i<ratingList.size();i++){
            tempRatingAverage+=ratingList.get(i);
        }
        tempRatingAverage=tempRatingAverage/ratingList.size();
        ratingAverage=tempRatingAverage;
    }

    public void walletAdd(double amount){
        if(BigDecimal.valueOf(amount).scale()>2){
            throw new IllegalArgumentException("amount must be less than three decimals");
        }
        if(amount<0){
            throw new IllegalArgumentException("amount cannot be negative");
        }
        wallet+=amount;
    }

    public void walletSubtract(double amount){
        if(BigDecimal.valueOf(amount).scale()>2){
            throw new IllegalArgumentException("amount must be less than three decimals");
        }
        if(amount<0){
            throw new IllegalArgumentException("amount cannot be negative");
        }
        if(wallet-amount<0){
            throw new IllegalArgumentException("will have negative wallet with amount given");
        }
        wallet-=amount;
    }




}
