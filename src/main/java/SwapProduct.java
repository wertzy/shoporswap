import java.util.List;

public class SwapProduct extends AbstractProduct {

    /**
     * Default constructor for a SwapProduct object
     */
    public SwapProduct(){

    }

    /**
     * Constructor for a SwapProduct object
     * @param nameIn the name of the SellProduct
     * @param descriptionIn the description of the SellProduct
     * @param valueIn the value of the SellProduct
     * @param merchantIn the merchant of the SellProduct
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if descriptionIn is invalid
     * @throws IllegalArgumentException if valueIn is invalid
     * @throws IllegalArgumentException if merchantIn is an invalid User
     */
    public SwapProduct(String nameIn, String descriptionIn, double valueIn, User merchantIn){

    }

    /**
     * Constructor for a SwapProduct object
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
    public SwapProduct(String nameIn, String descriptionIn, double valueIn, User merchantIn, List<Tag> tagsIn){

    }

}
