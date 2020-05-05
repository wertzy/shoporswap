public class StorefrontFactory {

    /**
     * Creates a new Storefront based on the desired Storefront
     * @param storefrontType the type of Storefront as a string
     * @return a new Storefront object
     * @throws IllegalArgumentException if the Storefront type is invalid (valid types are "sell" or "swap")
     */
    public Storefront getStorefront(String storefrontType){
        if(storefrontType.compareToIgnoreCase("sell") == 0){
            return new SellStorefront();
        }else if(storefrontType.compareToIgnoreCase("swap") == 0){
            return new SwapStorefront();
        }else{
            throw new IllegalArgumentException("Invalid Storefront type");
        }

    }
}
