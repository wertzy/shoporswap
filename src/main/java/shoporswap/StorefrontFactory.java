package shoporswap;

public class StorefrontFactory {

    /**
     * Creates a new shoporswap.Storefront based on the desired shoporswap.Storefront
     * @param storefrontType the type of shoporswap.Storefront as a string
     * @return a new shoporswap.Storefront object
     * @throws IllegalArgumentException if the shoporswap.Storefront type is invalid (valid types are "sell" or "swap")
     */
    public Storefront getStorefront(String storefrontType){
        if(storefrontType.compareToIgnoreCase("sell") == 0){
            return new SellStorefront();
        }else if(storefrontType.compareToIgnoreCase("swap") == 0){
            return new SwapStorefront();
        }else{
            throw new IllegalArgumentException("Invalid shoporswap.Storefront type");
        }

    }
}
