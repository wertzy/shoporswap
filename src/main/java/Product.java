import java.util.*;
import java.util.regex.Pattern;

public class Product {
    private String name;
    private String description;
    private double price;
    private List<String> tags;
    private User merchant;
    private List<User> consumers;

    /**
     * Default constructor
     */
    public Product(){
        this.name = "";
        this.description = "";
        this.price = 0.0;
        this.tags = new ArrayList<String>();
        this.merchant = null;
        this.consumers = new ArrayList<User>();
    }

    public void setName(String nameIn){
        if(!isValidName(nameIn)){
            throw new IllegalArgumentException("Invalid value for name");
        }
        this.name = nameIn;
    }

    public void setDescription(String descriptionIn){
        if(!isValidDescription(descriptionIn)){
            throw new IllegalArgumentException("Invalid value for description");
        }
        this.description = descriptionIn;
    }

    public void setPrice(double priceIn){
        if(!isValidPrice(priceIn)){
            throw new IllegalArgumentException("Invalid value for price");
        }
        this.price = priceIn;
    }

    /**
     * Constructor for a Product object
     * @param name the name of the Product
     * @param description the description of the Product
     * @param merchant the User advertising the Product
     * @throws IllegalArgumentException if the name of the Product is invalid
     * @throws IllegalArgumentException if the description of the Product is invalid
     */
    public Product(String name, String description, User merchant){
        if(!isValidName(name)){ // calls validity check on name
            throw new IllegalArgumentException("invalid name");
        }
        if(!isValidDescription(description)){ // calls validity check on description
            throw new IllegalArgumentException("invalid description");
        }
        this.name = name;
        this.description = description;
        this.price = 0;
        this.tags = new ArrayList<String>();
        this.merchant = merchant;
        this.consumers = new ArrayList<User>();
    }

    /**
     * Constructor for a Product object
     * @param name the name of the Product
     * @param description the description of the Product
     * @param price the price of the Product
     * @param merchant the User advertising the Product
     * @throws IllegalArgumentException if the name of the Product is invalid
     * @throws IllegalArgumentException if the description of the Product is invalid
     * @throws IllegalArgumentException if the price of the Product is invalid
     */
    public Product(String name, String description, double price, User merchant){
        if(!isValidName(name)){ // calls validity check on name
            throw new IllegalArgumentException("invalid name");
        }
        if(!isValidDescription(description)){ // calls validity check on description
            throw new IllegalArgumentException("invalid description");
        }
        if(!isValidPrice(price)){ // calls validity check on price
            throw new IllegalArgumentException("invalid price");
        }
        this.name = name;
        this.description = description;
        this.price = price;
        this.tags = new ArrayList<String>();
        this.merchant = merchant;
        this.consumers = new ArrayList<User>();
    }

    /**
     * Add a tag to the Product object
     * @param tag the tag to add
     * @throws IllegalArgumentException if the tag to add is invalid
     */
    public void addTag(String tag){
        if(!isValidTag(tag)){
            throw new IllegalArgumentException("invalid tag");
        }
        this.tags.add(tag);
    }

    /**
     * Remove a tag from the Product object
     * @param tag the tag to remove
     * @throws IllegalArgumentException if the tag to remove is invalid
     */
    public void removeTag(String tag) {
        if (!isValidTag(tag)) {
            throw new IllegalArgumentException("invalid tag");
        }
        if (this.tags.contains(tag)) {
            this.tags.remove(tag);
        } else {
            throw new NoSuchElementException("no such tag attached to product");
        }
    }
    /*
    * Moves the clothing from one user to another, and adds the previous owner to the history.
    */
    public void transferOwnership(User newOwner) {
        this.consumers.add(merchant);
        merchant = newOwner;
    }

    /**
     * Accessor method for accessing the name property of the Product object
     * @return the name of the Product object
     */
    public String getName(){
        return this.name;
    }

    /**
     * Accessor method for accessing the description property of the Product
     * @return the description of the Product
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Accessor method for accessing the price property of the Product
     * @return the price of the Product
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Accessor method for accessing the tags property of the Product
     * @return the tags of the Product
     */
    public List<String> getTags(){
        return this.tags;
    }

    /**
     * Accessor method for accessing the merchant property of the Product
     * @return the merchant of the Product
     */
    public User getMerchant(){
        return this.merchant;
    }

    public List<User> getConsumers(){
        return this.consumers;
    }

    /**
     * Accessor method for accessing the consumer history of the Product, both as raw data and as a neat string.
     * @return the consumer of the Product, as an array or as a string.
     */
    public List<User> retrieveConsumersRaw(){
        List<User> returnable = new ArrayList<User>();
        returnable.add(merchant);
        return this.consumers;
    }

    public String retrieveConsumersNeat(){
        String returnable = ("History: " + consumers.toString());
        returnable = returnable.replace("]", "");
        returnable = returnable + ", " + merchant + "]";
        return returnable;
    }

    public void setMerchant(User userIn){
        this.merchant = userIn;
    }

    /**
     * Static method to check the validity of a name (only alphanumeric and space characters, 50 character maximum)
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidName(String name){
        if(name.isEmpty()){
            return true;
        }
        if(name.indexOf(" ") == 0){ // checks if the name begins with a space
            return false;
        }
        if(name.lastIndexOf(" ") == name.length() - 1){ // checks if the name ends with a space
            return false;
        }
        String nameStringPattern = "[\\w[\\s]]{1,50}+"; // regex representing a 1-50 length string which pass the initial if-else conditions
        return Pattern.matches(nameStringPattern, name); // checks if the name matches the required expression
    }

    /**
     * Static method to check the validity of a description (only alphanumeric and space characters, 500 character maximum)
     * @param description the description to validate
     * @return true if the description is valid, otherwise false
     */
    public static boolean isValidDescription(String description){
        if(description.isEmpty()){
            return true;
        }
        if(description.indexOf(" ") == 0 || description.indexOf("-") == 0){ // checks if the description begins with a space or dash
            return false;
        }
        if(description.lastIndexOf(" ") == description.length() - 1 || description.lastIndexOf("-") == description.length() - 1){ // checks if the description ends with a space or dash
            return false;
        }
        String descriptionStringPattern = "[\\w[\\s][-]]{1,500}+"; // regex representing a 1-500 length string which pass the initial if-else conditions
        return Pattern.matches(descriptionStringPattern, description); // checks if the description matches the required expression
    }

    /**
     * Static method to check the validity of a price (price must be nonnegative and have no more than 2 decimal places)
     * @param price the price to validate
     * @return true if the price is valid, false otherwise
     */
    public static boolean isValidPrice(double price){
        if(price < 0){ // checks if the price is negative
            return false;
        }
        String priceString = "" + price;
        if(priceString.contains(".") && priceString.substring(priceString.indexOf(".") + 1).length() > 2){ // checks if the price has more than 2 decimal places
            return false;
        }
        return true;
    }

    /**
     * Static method to check the validity of a tag (tag must be alphanumeric and not containing spaces)
     * @param tag the tag to validate
     * @return true if the tag is valid, false otherwise
     */
    public static boolean isValidTag(String tag){
        if(tag.indexOf(" ") == 0){ // checks if the tag begins with a space
            return false;
        }
        if(tag.lastIndexOf(" ") == tag.length() - 1){ // checks if the tag ends with a space
            return false;
        }
        String descriptionStringPattern = "[\\w]+"; // regex representing a 1+ length string which pass the initial if-else conditions
        return Pattern.matches(descriptionStringPattern, tag); // checks if the tag matches the required expression
    }
    /**
     * Method to transform a block of text into a tag or multiple tags.(tag must be alphanumeric and not containing spaces)
     * @param text a block of text containing #s and spaces that can be made into a list
     * Adds tags to the product
     */
    public void textToTag(String text) {
        text=text.replaceAll(",",""); //Removes all commas from the text file leaving only characters and spaces
        text=text.replaceAll("\\s",""); //Removes all spaces from the text file leaving only characters

        String[] arrOfTags=text.split("#"); //
        for(int i=1; i<arrOfTags.length; i++){ //Starts at i=1 because splits creates a first element regardless of whether or not a # exists. so we skip the first entry.
            if(isValidTag(arrOfTags[i])){
                this.tags.add(arrOfTags[i]);
            }
        }

    }


    public static String sendMessage(String message){
        return "";
    }


}


