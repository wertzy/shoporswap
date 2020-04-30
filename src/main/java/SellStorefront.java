import java.util.*;

public class SellStorefront extends Storefront {

    private List<SellProduct> sellProducts;

    /**
     * Default constructor for a SellStorefront object
     */
    public SellStorefront(){

    }

    /**
     * Constructor for a SellStorefront object
     * @param nameIn the name of the SellStorefront
     * @param ownerIn the owner of the SellStorefront
     * @throws IllegalArgumentException if nameIn is invalid
     */
    public SellStorefront(String nameIn, User ownerIn){

    }

    /**
     * Constructor for a SellStorefront object
     * @param nameIn the name of the SellStorefront
     * @param ownerIn the owner of the SellStorefront
     * @param sellProductsIn the list of SellProduct items in the SellStorefront
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if at least one of the SellProduct items in sellProductsIn is invalid
     */
    public SellStorefront(String nameIn, User ownerIn, List<SellProduct> sellProductsIn){

    }

    /**
     * Adds a SellProduct to the SellStorefront
     * @param sellProductIn the SellProduct to sell at the SellStorefront
     * @return the SellProduct to sell at the SellStorefront
     */
    public SellProduct addProduct(SellProduct sellProductIn){
        return null;
    }

    /**
     * Finds a SellProduct in the SellStorefront
     * @param sellProductIn the SellProduct to find in the Storefront
     * @return the SellProduct to find in the Storefront
     * @throws NoSuchElementException if sellProductIn does not exist in the SellStorefront
     */
    public SellProduct findProduct(SellProduct sellProductIn){
        return null;
    }

    /**
     * Removes a SellProduct from the SellStorefront
     * @param sellProductIn the SellProduct to remove from selling at the SellStorefront
     * @return the SellProduct removed from selling at the SellStorefront
     * @throws NoSuchElementException if sellProductIn does not exist in the SellStorefront
     */
    public SellProduct removeProduct(SellProduct sellProductIn){
        return null;
    }

    /**
     * Sells a SellProduct to a User in the SellStorefront
     * @param sellProductIn the SellProduct to sell to the User to in the SellStorefront
     * @param consumerIn the User to sell the SellProduct to in the SellStorefront
     * @return the SellProduct sold to the User in the SellStorefront
     */
    public SellProduct completeTransaction(SellProduct sellProductIn, User consumerIn){
        return null;
    }

    /**
     * Accessor method for the sellProducts property of the SellStorefront
     * @return the list of products listed for sale by the SellStorefront
     */
    public List<SellProduct> getSellProducts(){
        return null;
    }

    /**
     * Mutator method for the sellProducts property of the SellStorefront
     * @param sellProductsIn the list of products for the SellStorefront to sell
     */
    public void setSellProducts(List<SellProduct> sellProductsIn){

    }

}
