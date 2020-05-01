import java.util.*;

public class Client extends Account{

    private List<AbstractProduct> myProductList;

    /**
     * Default constructor for Client object
     */
    public Client(){

    }

    /**
     * Constructor for Client Account object
     * @param nameIn the name of the Client Account
     * @param passwordIn the password of the Client Account
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Client(String nameIn, String passwordIn){

    }

    /**
     * Adds a SellProduct to owned AbstractProduct list (from transaction)
     * @param productIn the SellProduct to add
     * @return the SellProduct added
     */
    public SellProduct addSellProduct(SellProduct productIn){
        return null;
    }

    /**
     * Adds a SwapProduct to owned AbstractProduct list (from transaction)
     * @param productIn the SwapProduct to add
     * @return the SwapProduct added
     */
    public SwapProduct addSwapProduct(SwapProduct productIn){
        return null;
    }

    /**
     * Finds a SellProduct in the owned AbstractProduct list
     * @param productIn the SellProduct to find
     * @return the SellProduct found
     * @throws NoSuchElementException if the SellProduct does not exist in the AbstractProduct list
     */
    public SellProduct findSellProduct(SellProduct productIn){
        return null;
    }

    /**
     * Finds a SwapProduct in the owned AbstractProduct list
     * @param productIn the SwapProduct to find
     * @return the SwapProduct found
     * @throws NoSuchElementException if the SwapProduct does not exist in the AbstractProduct list
     */
    public SwapProduct findSwapProduct(SwapProduct productIn){
        return null;
    }

    /**
     * Removes a SellProduct from the owned AbstractProduct list
     * @param productIn the SellProduct to remove
     * @return the SellProduct removed
     * @throws NoSuchElementException if the SellProduct does not exist in the AbstractProduct list
     */
    public SellProduct removeSellProduct(SellProduct productIn){
        return null;
    }

    /**
     * Removes a SwapProduct form the owned AbstractProduct list
     * @param productIn the SwapProduct to remove
     * @return the SwapProduct removed
     * @throws NoSuchElementException if the SwapProduct does not exist in the AbstractProduct list
     */
    public SwapProduct removeSwapProduct(SwapProduct productIn){
        return null;
    }

    /**
     * Accessor method for the myProductList property of the Client Account
     * @return the list of products of the Client Account
     */
    public List<AbstractProduct> getMyProductList(){
        return null;
    }

    /**
     * Mutator method for the myProductList property of the Client Account
     * @param productListIn the list of products of the Client Account
     * @throws IllegalArgumentException if at least one of the AbstractProducts is invalid
     */
    public void setMyProductList(List<AbstractProduct> productListIn){

    }


}
