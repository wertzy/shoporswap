package shoporswap;

public class AbstractProductFactory {

    /**
     * Default constructor for ProductFactory
     */
    public AbstractProductFactory(){

    }

    /**
     * Creates a new shoporswap.AbstractProduct
     * @param typeIn the type of Product to create
     * @return a shoporswap.SellProduct or shoporswap.SwapProduct
     * @throws IllegalArgumentException if typeIn is invalid (if typeIn is neither "sell" nor "swap")
     */
    public AbstractProduct getProduct(String typeIn){
        if(typeIn.compareToIgnoreCase("sell") == 0){
            return new SellProduct();
        }
        if(typeIn.compareToIgnoreCase("swap") == 0){
            return new SwapProduct();
        }
        throw new IllegalArgumentException("Product type is invalid");
    }

}
