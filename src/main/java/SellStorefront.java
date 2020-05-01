import java.util.*;

public class SellStorefront extends Storefront {

    private List<AbstractProduct> sellProducts;

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
     * @throws IllegalArgumentException if the owner of the Storefront is invalid
     */
    public SellStorefront(String nameIn, Client ownerIn){

    }

    /**
     * Constructor for a SellStorefront object
     * @param nameIn the name of the SellStorefront
     * @param ownerIn the owner of the SellStorefront
     * @param sellProductsIn the list of SellProduct items in the SellStorefront
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if the owner of the Storefront is invalid
     * @throws IllegalArgumentException if at least one of the SellProduct items in sellProductsIn is invalid
     */
    public SellStorefront(String nameIn, Client ownerIn, List<SellProduct> sellProductsIn){

    }

    /**
     * Adds a SellProduct to the SellStorefront
     * @param sellProductIn the SellProduct to sell at the SellStorefront
     * @return the SellProduct to sell at the SellStorefront
     * @throws IllegalArgumentException if the SellProduct to sell is not owned by the SellStorefront owner
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
    public SellProduct completeTransaction(SellProduct sellProductIn, Client consumerIn){
        return null;
    }

    /**
     * Accessor method for the sellProducts property of the SellStorefront
     * @return the list of products listed for sale by the SellStorefront
     */
    public List<SellProduct> getSellProducts(){
        return null;
    }

    @Override
    public List<AbstractProduct> getStorefrontProducts() {
        return null;
    }

    /**
     * Mutator method for the sellProducts property of the SellStorefront
     * @param sellProductsIn the list of products for the SellStorefront to sell
     */
    public void setSellProducts(List<SellProduct> sellProductsIn){

    }

}
