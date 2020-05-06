package shoporswap;

import java.util.*;
import java.util.regex.Pattern;

public abstract class AbstractProduct {

    // primary properties, will include direct accessors/mutators
    private String productName;
    private String productDescription;
    private double productValue;
    private Client productMerchant;
    private List<Tag> productTags;

    /**
     * Default constructor for shoporswap.AbstractProduct
     * @throws IllegalArgumentException if the default name is invalid
     * @throws IllegalArgumentException if the default description is invalid
     * @throws IllegalArgumentException if the default value is invalid
     * @throws IllegalArgumentException if the default merchant is invalid
     * @throws IllegalArgumentException if the default tag list is invalid
     */
    public AbstractProduct(){
        this.setProductName("DEFAULT NAME");
        this.setProductDescription("DEFAULT DESCRIPTION");
        this.setProductValue(0.0);
        this.setProductMerchant(null);
        this.setProductTags(new ArrayList<Tag>());
    }

    /**
     * Constructor for shoporswap.AbstractProduct
     * @param nameIn the name of the shoporswap.AbstractProduct
     * @param descriptionIn the description of the shoporswap.AbstractProduct
     * @param valueIn the value of the shoporswap.AbstractProduct
     * @param merchantIn the merchant of the shoporswap.AbstractProduct
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if descriptionIn is invalid
     * @throws IllegalArgumentException if valueIn is invalid
     * @throws IllegalArgumentException if merchantIn is invalid
     */
    public AbstractProduct(String nameIn, String descriptionIn, double valueIn, Client merchantIn){
        this.setProductName(nameIn);
        this.setProductDescription(descriptionIn);
        this.setProductValue(valueIn);
        this.setProductMerchant(merchantIn);
        this.setProductTags(new ArrayList<Tag>());
    }

    /**
     * Adds a tag to the shoporswap.AbstractProduct
     * @param tagIn the tag to add to the shoporswap.AbstractProduct
     * @return the tag added to the shoporswap.AbstractProduct
     */
    public final Tag addTag(Tag tagIn){
        if(!this.productTags.contains(tagIn)){
            this.productTags.add(tagIn);
        }
        return tagIn;
    }

    /**
     * Finds a tag in the AbsteactProduct
     * @param tagIn the tag to find in the AbsractProduct
     * @return the tag found in the shoporswap.AbstractProduct
     * @throws NoSuchElementException if a shoporswap.Tag tagIn does not exist for the shoporswap.AbstractProduct
     */
    public final Tag findTag(Tag tagIn){
        for(Tag tag : this.productTags){
            if(tag.getName().compareTo(tagIn.getName()) == 0){
                return tag;
            }
        }
        throw new NoSuchElementException("shoporswap.Tag does not exist for the product");
    }

    /**
     * Removes a tag from the shoporswap.AbstractProduct
     * @param tagIn the tag to remove from the shoporswap.AbstractProduct
     * @return the tag removed from the Absract Product
     * @throws NoSuchElementException if a shoporswap.Tag tagIn does not exist for the shoporswap.AbstractProduct
     */
    public final Tag removeTag(Tag tagIn){
        Tag foundTag = this.findTag(tagIn);
        this.productTags.remove(foundTag);
        return foundTag;
    }

    /**
     * Accessor method for the productName property of the shoporswap.AbstractProduct
     * @return the name of the shoporswap.AbstractProduct
     */
    public final String getProductName(){
        return this.productName;
    }

    /**
     * Accessor method for the productDescription property of the shoporswap.AbstractProduct
     * @return the description of the shoporswap.AbstractProduct
     */
    public final String getProductDescription(){
        return this.productDescription;
    }

    /**
     * Accessor method for the productValue property of the shoporswap.AbstractProduct
     * @return the name of the shoporswap.AbstractProduct
     */
    public final double getProductValue(){
        return this.productValue;
    }

    /**
     * Accessor method for the productTags property of the shoporswap.AbstractProduct
     * @return the name of the shoporswap.AbstractProduct
     */
    public final List<Tag> getProductTags(){
        return this.productTags;
    }

    /**
     * Accessor method for the productMerchant property of the shoporswap.AbstractProduct
     * @return the merchant of the shoporswap.AbstractProduct
     */
    public final Client getProductMerchant(){
        return this.productMerchant;
    }

    /**
     * Mutator method for the productName property of the shoporswap.AbstractProduct
     * @param nameIn the name to set for the shoporswap.AbstractProduct
     * @throws IllegalArgumentException if the value of nameIn is invalid
     */
    public final void setProductName(String nameIn){
        if(!AbstractProduct.isValidProductName(nameIn)){
            throw new IllegalArgumentException("Invalid Product name");
        }
        this.productName = nameIn;
    }

    /**
     * Mutator method for the productDescription property of the shoporswap.AbstractProduct
     * @param descriptionIn the description to set for the shoporswap.AbstractProduct
     * @throws IllegalArgumentException if the value of descriptionIn is invalid
     */
    public final void setProductDescription(String descriptionIn){
        if(!AbstractProduct.isValidProductDescription(descriptionIn)){
            throw new IllegalArgumentException("Invalid Product description");
        }
        this.productDescription = descriptionIn;
    }

    /**
     * Mutator method for the productValue property of the shoporswap.AbstractProduct
     * @param valueIn the value to set for the shoporswap.AbstractProduct
     * @throws IllegalArgumentException if the value of valueIn is invalid
     */
    public final void setProductValue(double valueIn){
        if(!AbstractProduct.isValidProductValue(valueIn)){
            throw new IllegalArgumentException("Invalid Product value");
        }
        this.productValue = valueIn;
    }

    /**
     * Mutator method for the productMerchant property of the shoporswap.AbstractProduct
     * @param userIn the merchant to set for the shoporswap.AbstractProduct
     */
    public final void setProductMerchant(Client userIn){
        this.productMerchant = userIn;
    }

    /**
     * Mutator method for the productTags property of the shoporswap.AbstractProduct
     * @param tagsIn the list of tags to set for the shoporswap.AbstractProduct
     */
    public final void setProductTags(List<Tag> tagsIn){
        this.productTags = tagsIn;
    }

    /**
     * Validation method for determining whether a product's name is valid
     * @param nameIn the desired name
     * @return true if the desired name is valid, false otherwise
     */
    public static final boolean isValidProductName(String nameIn){
        if(nameIn.indexOf(" ") == 0){ // checks if the name begins with a space
            return false;
        }
        if(nameIn.lastIndexOf(" ") == nameIn.length() - 1){ // checks if the name ends with a space
            return false;
        }
        String nameStringPattern = "[\\w[\\s]]{1,50}+"; // regex representing a 1-50 length string which pass the initial if-else conditions
        return Pattern.matches(nameStringPattern, nameIn); // checks if the name matches the required expression
    }

    /**
     * Validation method for determining whether a product's description is valid
     * @param descriptionIn the desired description
     * @return true if the desired description is valid, false otherwise
     */
    public static final boolean isValidProductDescription(String descriptionIn){
        if(descriptionIn.indexOf(" ") == 0 || descriptionIn.indexOf("-") == 0){ // checks if the description begins with a space or dash
            return false;
        }
        if(descriptionIn.lastIndexOf(" ") == descriptionIn.length() - 1 || descriptionIn.lastIndexOf("-") == descriptionIn.length() - 1){ // checks if the description ends with a space or dash
            return false;
        }
        String descriptionStringPattern = "[\\w[\\s][-]]{1,500}+"; // regex representing a 1-500 length string which pass the initial if-else conditions
        return Pattern.matches(descriptionStringPattern, descriptionIn); // checks if the description matches the required expression
    }

    /**
     * Validation method for determining whether a product's price is valid
     * @param valueIn the desired value
     * @return true if the desired price is valid, false otherwise
     */
    public static final boolean isValidProductValue(double valueIn){
        if(valueIn < 0){ // checks if the price is negative
            return false;
        }
        String valueString = "" + valueIn;
        if(valueString.contains(".") && valueString.substring(valueString.indexOf(".") + 1).length() > 2){ // checks if the price has more than 2 decimal places
            return false;
        }
        return true;
    }

}
