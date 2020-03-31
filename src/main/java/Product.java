import java.util.*;

public class Product {
    private String name;
    private String description;
    private double price;
    private List<String> tags;
    private User merchant;
    private User consumer;

    /**
     * Constructor for a Product object
     * @param name the name of the Product
     * @param description the description of the Product
     * @param merchant the User selling the Product
     */
    public Product(String name, String description, User merchant){
        //TODO implementation
    }

    /**
     * Constructor for a Product object
     * @param name the name of the Product
     * @param description the description of the Product
     * @param price the price of the Product
     * @param merchant the User selling the Product
     */
    public Product(String name, String description, double price, User merchant){
        //TODO implementation
    }

    /**
     * Add a tag to the Product object
     * @param tag the tag to add
     */
    public void addTag(String tag){
        //TODO implementation
    }

    /**
     * Remove a tag from the Product object
     * @param tag the tag to remove
     */
    public void removeTag(String tag){
        //TODO implementation
    }

    /**
     * Accessor method for accessing the name property of the Product object
     * @return the name of the Product object
     */
    public String getName(){
        //TODO implementation
        return "";
    }

    /**
     * Accessor method for accessing the description property of the Product
     * @return the description of the Product
     */
    public String getDescription(){
        //TODO implementation
        return "";
    }

    /**
     * Accessor method for accessing the price property of the Product
     * @return the price of the Product
     */
    public double getPrice(){
        //TODO implementation
        return 0;
    }

    /**
     * Accessor method for accessing the tags property of the Product
     * @return the tags of the Product
     */
    public List<String> getTags(){
        //TODO implementation
        return null;
    }

    /**
     * Accessor method for accessing the merchant property of the Product
     * @return the merchant of the Product
     */
    public User getMerchant(){
        //TODO implementation
        return null;
    }

    /**
     * Accessor method for accessing the consumer property of the Product
     * @return the consumer of the Product
     */
    public User getConsumer(){
        //TODO implementation
        return null;
    }

    /**
     * Static method to check the validity of a name (only alphanumeric and space characters, 50 character maximum)
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidName(String name){
        //TODO implementation
        return false;
    }

    /**
     * Static method to check the validity of a description (only alphanumeric and space characters, 500 character maximum)
     * @param description the description to validate
     * @return true if the description is valid, false otherwise
     */
    public static boolean isValidDescription(String description){
        //TODO implementation
        return false;
    }

    /**
     * Static method to check the validity of a price (price must be nonnegative and have no more than 2 decimal places)
     * @param price the price to validate
     * @return true if the price is valid, false otherwise
     */
    public static boolean isValidPrice(double price){
        //TODO implementation
        return false;
    }

}
