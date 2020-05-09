package shoporswap;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Tag {
    private String name;
    private final List<AbstractProduct> products;

    public Tag(){
        this.name = "name";
        this.products = new ArrayList<AbstractProduct>();
    }

    public Tag(String name){
        if(!isValidTagName(name)){ // calls validity check on name
            throw new IllegalArgumentException("invalid tag");
        }
        this.name = name;
        this.products = new ArrayList<AbstractProduct>();

    }

    /**
     * Static method to check the validity of a tag (tag must be alphanumeric and not containing spaces)
     * @param tag the tag to validate
     * @return true if the tag is valid, false otherwise
     */
    public static boolean isValidTagName(String tag){
        if(tag.indexOf(" ") == 0){ // checks if the tag begins with a space
            return false;
        }
        if(tag.lastIndexOf(" ") == tag.length() - 1){ // checks if the tag ends with a space
            return false;
        }
        String descriptionStringPattern = "[\\w]+"; // regex representing a 1+ length string which pass the initial if-else conditions
        return Pattern.matches(descriptionStringPattern, tag); // checks if the tag matches the required expression
    }

    public void addProduct(AbstractProduct product){
        this.products.add(product);
    }

    public void removeProduct(AbstractProduct product){
        this.products.remove(product);
    }

    public List<AbstractProduct> accessProducts() {
        // implement method to pass corresponding tests after the tests have been written
        return this.products;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

}
