package shoporswap;

import java.util.List;

public class SellProduct extends AbstractProduct{

    /**
     * Default constructor for a shoporswap.SellProduct object
     */
    public SellProduct(){
        super();
    }

    /**
     * Constructor for a shoporswap.SellProduct object
     * @param nameIn the name of the shoporswap.SellProduct
     * @param descriptionIn the description of the shoporswap.SellProduct
     * @param valueIn the value of the shoporswap.SellProduct
     * @param merchantIn the merchant of the shoporswap.SellProduct
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if descriptionIn is invalid
     * @throws IllegalArgumentException if valueIn is invalid
     * @throws IllegalArgumentException if merchantIn is an invalid User
     */
    public SellProduct(String nameIn, String descriptionIn, double valueIn, Client merchantIn){
        super(nameIn, descriptionIn, valueIn, merchantIn);
    }

    /**
     * Constructor for a shoporswap.SellProduct object
     * @param nameIn the name of the shoporswap.SellProduct
     * @param descriptionIn the description of the shoporswap.SellProduct
     * @param valueIn the value of the shoporswap.SellProduct
     * @param merchantIn the merchant of the shoporswap.SellProduct
     * @param tagsIn the list of tags associated with the shoporswap.SellProduct
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if descriptionIn is invalid
     * @throws IllegalArgumentException if valueIn is invalid
     * @throws IllegalArgumentException if merchantIn is an invalid User
     * @throws IllegalArgumentException if at least one of the shoporswap.Tag objects in tagsIn is an invalid shoporswap.Tag
     */
    public SellProduct(String nameIn, String descriptionIn, double valueIn, Client merchantIn, List<Tag> tagsIn){
        super(nameIn, descriptionIn, valueIn, merchantIn);
        for(Tag tag : tagsIn){
            super.addTag(tag);
        }
    }

}