import java.util.List;

public abstract class AbstractProduct {

    // primary properties, will include direct accessors/mutators
    private String productName;
    private String productDescription;
    private double productValue;
    private User productMerchant;
    private List<Tag> productTags;

    /**
     * Adds a tag to the AbstractProduct
     * @param tagIn the tag to add to the AbstractProduct
     * @return the tag added to the AbstractProduct
     */
    public final Tag addTag(Tag tagIn){
        return null;
    }

    /**
     * Finds a tag in the AbsteactProduct
     * @param tagIn the tag to find in the AbsractProduct
     * @return the tag found in the AbstractProduct
     */
    public final Tag findTag(Tag tagIn){
        return null;
    }

    /**
     * Removes a tag from the AbstractProduct
     * @param tagIn the tag to remove from the AbstractProduct
     * @return the tag removed from the Absract Product
     */
    public final Tag removeTag(Tag tagIn){
        return null;
    }

    /**
     * Accessor method for the productName property of the AbstractProduct
     * @return the name of the AbstractProduct
     */
    public final String getProductName(){
        return "";
    }

    /**
     * Accessor method for the productDescription property of the AbstractProduct
     * @return the description of the AbstractProduct
     */
    public final String getProductDescription(){
        return "";
    }

    /**
     * Accessor method for the productValue property of the AbstractProduct
     * @return the name of the AbstractProduct
     */
    public final double getProductValue(){
        return -1.0;
    }

    /**
     * Accessor method for the productTags property of the AbstractProduct
     * @return the name of the AbstractProduct
     */
    public final List<Tag> getProductTags(){
        return null;
    }

    /**
     * Accessor method for the productMerchant property of the AbstractProduct
     * @return the merchant of the AbstractProduct
     */
    public final User getProductMerchant(){
        return null;
    }

    /**
     * Mutator method for the productName property of the AbstractProduct
     * @param nameIn the name to set for the AbstractProduct
     * @throws IllegalArgumentException if the value of nameIn is invalid
     */
    public final void setProductName(String nameIn){

    }

    /**
     * Mutator method for the productDescription property of the AbstractProduct
     * @param descriptionIn the description to set for the AbstractProduct
     * @throws IllegalArgumentException if the value of descriptionIn is invalid
     */
    public final void setProductDescription(String descriptionIn){

    }

    /**
     * Mutator method for the productValue property of the AbstractProduct
     * @param valueIn the value to set for the AbstractProduct
     * @throws IllegalArgumentException if the value of valueIn is invalid
     */
    public final void setProductValue(double valueIn){

    }

    /**
     * Mutator method for the productMerchant property of the AbstractProduct
     * @param userIn the merchant to set for the AbstractProduct
     */
    public final void setProductMerchant(User userIn){

    }

    /**
     * Mutator method for the productTags property of the AbstractProduct
     * @param tagsIn the list of tags to set for the AbstractProduct
     */
    public final void setProductTags(List<Tag> tagsIn){

    }

    /**
     * Validation method for determining whether a product's name is valid
     * @param nameIn
     * @return true if the desired name is valid, false otherwise
     */
    public static final boolean isValidProductName(String nameIn){
        return false;
    }

    /**
     * Validation method for determining whether a product's description is valid
     * @param descriptionIn
     * @return true if the desired description is valid, false otherwise
     */
    public static final boolean isValidProductDescription(String descriptionIn){
        return false;
    }

    /**
     * Validation method for determining whether a product's price is valid
     * @param priceIn
     * @return true if the desired price is valid, false otherwise
     */
    public static final boolean isValidProductPrice(double priceIn){
        return false;
    }

}
