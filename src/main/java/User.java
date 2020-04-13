import java.util.*;
import java.util.regex.Pattern;

public class User {
    protected String accountName;
    protected String password;
    protected ArrayList<String> transactionHistory;
    protected ArrayList<Message> messages;
    protected double rating;

    /**
     * Default constructor of a User
     */
    public User(){
        this.accountName = "accountname";
        this.password = "password";
        this.rating = 0.0;
        this.transactionHistory = new ArrayList<String>();}

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
        this.rating = 0.0;
        this.transactionHistory = new ArrayList<>(); // suggested revision
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
    public List <String> getTransactionHistory(){return this.transactionHistory;}

    /**
     * Accessor for the rating property of a User
     * @return the rating of the User
     */
    public double getRating(){
        return this.rating;
    }

    /**
     * Checks to see if a desired account name has any illegal characters (spaces)
     * @param accountName the account name to check
     * @return true if a valid account name, false if an invalid account name
     */
    public static boolean isAccountNameValid(String accountName) {
        String exp = "\\A[^\\s]+\\z";
        return Pattern.matches(exp, accountName);
    }

    /**
     * Checks to see if a desired password has any illegal characters (spaces)
     * @param password the password to check
     * @return true if a valid password, false if an invalid password
     */
    public static boolean isPasswordValid(String password) {
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
        Message message = new Message(name, body, recipient.accountName, this.accountName);
        recipient.receiveMessage(message);
    }

    public void receiveMessage(Message message){
        messages.add(message);
    }

    public ArrayList<Message> getMessages(){return messages;}

    /**
     * Views the collection of clothing available to see (varies by seller, shopper, swapper)
     * @return the viewable clothing (as a list) of clothing
     */
    public Collection<Product> viewClothing(){
        return null;
    }
}
