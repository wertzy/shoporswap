import java.util.List;

public class SellProduct extends AbstractProduct{

    /**
     * Default constructor for a SellProduct object
     */
    public SellProduct(){

    }

    /**
     * Constructor for a SellProduct object
     * @param nameIn the name of the SellProduct
     * @param descriptionIn the description of the SellProduct
     * @param valueIn the value of the SellProduct
     * @param merchantIn the merchant of the SellProduct
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if descriptionIn is invalid
     * @throws IllegalArgumentException if valueIn is invalid
     * @throws IllegalArgumentException if merchantIn is an invalid User
     */
    public SellProduct(String nameIn, String descriptionIn, double valueIn, User merchantIn){

    }

    /**
     * Constructor for a SellProduct object
     * @param nameIn the name of the SellProduct
     * @param descriptionIn the description of the SellProduct
     * @param valueIn the value of the SellProduct
     * @param merchantIn the merchant of the SellProduct
     * @param tagsIn the list of tags associated with the SellProduct
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if descriptionIn is invalid
     * @throws IllegalArgumentException if valueIn is invalid
     * @throws IllegalArgumentException if merchantIn is an invalid User
     * @throws IllegalArgumentException if at least one of the Tag objects in tagsIn is an invalid Tag
     */
    public SellProduct(String nameIn, String descriptionIn, double valueIn, User merchantIn, List<Tag> tagsIn){

    }

}