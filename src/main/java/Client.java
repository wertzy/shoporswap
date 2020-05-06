import java.util.*;

public class Client extends Account{

    private List<AbstractProduct> myOwnedProductList;
    private Map<String, Storefront> myStorefronts;

    /**
     * Default constructor for Client object
     */
    public Client(){
        super("DefaultClient", "DefaultPassword");
        this.setMyOwnedProductList(new ArrayList<AbstractProduct>());
        this.setMyStorefronts(new HashMap<String, Storefront>());
    }

    /**
     * Constructor for Client Account object
     * @param nameIn the name of the Client Account
     * @param passwordIn the password of the Client Account
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Client(String nameIn, String passwordIn){
        super(nameIn, passwordIn);
        this.setMyOwnedProductList(new ArrayList<AbstractProduct>());
        this.setMyStorefronts(new HashMap<String, Storefront>());
    }

    /**
     * Adds a SellProduct to owned AbstractProduct list (from transaction)
     * @param productIn the SellProduct to add
     * @return the SellProduct added
     */
    public SellProduct addSellProduct(SellProduct productIn){
        this.myOwnedProductList.add(productIn);
        return productIn;
    }

    /**
     * Adds a SwapProduct to owned AbstractProduct list (from transaction)
     * @param productIn the SwapProduct to add
     * @return the SwapProduct added
     */
    public SwapProduct addSwapProduct(SwapProduct productIn){
        this.myOwnedProductList.add(productIn);
        return productIn;
    }

    /**
     * Finds a SellProduct in the owned AbstractProduct list
     * @param productIn the SellProduct to find
     * @return the SellProduct found
     * @throws NoSuchElementException if the SellProduct does not exist in the AbstractProduct list
     */
    public SellProduct findSellProduct(SellProduct productIn){
        for(AbstractProduct product : this.myOwnedProductList){
            if(product == productIn){
                return (SellProduct) product;
            }
        }
        throw new NoSuchElementException("SellProduct does not exist");
    }

    /**
     * Finds a SwapProduct in the owned AbstractProduct list
     * @param productIn the SwapProduct to find
     * @return the SwapProduct found
     * @throws NoSuchElementException if the SwapProduct does not exist in the AbstractProduct list
     */
    public SwapProduct findSwapProduct(SwapProduct productIn){
        for(AbstractProduct product : this.myOwnedProductList){
            if(product == productIn){
                return (SwapProduct) product;
            }
        }
        throw new NoSuchElementException("SwapProduct does not exist");
    }

    /**
     * Removes a SellProduct from the owned AbstractProduct list
     * @param productIn the SellProduct to remove
     * @return the SellProduct removed
     * @throws NoSuchElementException if the SellProduct does not exist in the AbstractProduct list
     */
    public SellProduct removeSellProduct(SellProduct productIn){
        for(AbstractProduct product : this.myOwnedProductList){
            if(product == productIn){
                this.myOwnedProductList.remove(product);
                return (SellProduct) product;
            }
        }
        throw new NoSuchElementException("SwapProduct does not exist");
    }

    /**
     * Adds a Storefront to the Client's collection of Storefronts
     * @param storefrontIn the Storefront to add
     * @return the Storefront that is added
     * @throws IllegalArgumentException if storefrontIn is invalid (if there is already a Storefront with that name for the User)
     */
    public Storefront addStorefront(Storefront storefrontIn){
        if(this.getMyStorefronts().containsKey(storefrontIn.getStorefrontName())){
            throw new IllegalArgumentException("Storefront invalid (you already have a Storefront with this name)");
        }
        this.getMyStorefronts().put(storefrontIn.getStorefrontName(), storefrontIn);
        return this.findStorefront(storefrontIn);
    }

    /**
     * Finds a Storefront in the Client's collection of Storefronts
     * @param storefrontIn the Storefront to find
     * @return the Storefront that is found
     * @throws NoSuchElementException if storefrontIn is invalid (if the Storefront does not exist in the collection of the Client's Storefronts)
     */
    public Storefront findStorefront(Storefront storefrontIn){
        if(this.getMyStorefronts().containsKey(storefrontIn.getStorefrontName())){
            return this.getMyStorefronts().get(storefrontIn.getStorefrontName());
        }
        throw new NoSuchElementException("Storefront invalid (you do not have a Storefront with this name)");
    }

    /**
     * Removes a Storefront in the Client's collection of Storefronts
     * @param storefrontIn the Storefront to remove
     * @return the Storefront that is removed
     * @throws NoSuchElementException if storefrontIn is invalid (if the Storefront does not exist in the collection of the Client's Storefronts)
     */
    public Storefront removeStorefront(Storefront storefrontIn){
        if(this.getMyStorefronts().containsKey(storefrontIn.getStorefrontName())){
            return this.getMyStorefronts().remove(storefrontIn.getStorefrontName());
        }
        throw new NoSuchElementException("Storefront invalid (you do not have a Storefront with this name)");
    }

    /**
     * Removes a SwapProduct form the owned AbstractProduct list
     * @param productIn the SwapProduct to remove
     * @return the SwapProduct removed
     * @throws NoSuchElementException if the SwapProduct does not exist in the AbstractProduct list
     */
    public SwapProduct removeSwapProduct(SwapProduct productIn){
        for(AbstractProduct product : this.myOwnedProductList){
            if(product == productIn){
                this.myOwnedProductList.remove(product);
                return (SwapProduct) product;
            }
        }
        throw new NoSuchElementException("SwapProduct does not exist");
    }

    /**
     * Accessor method for the myProductList property of the Client Account
     * @return the list of products of the Client Account
     */
    public List<AbstractProduct> getMyOwnedProductList(){
        return this.myOwnedProductList;
    }

    /**
     * Accessor for the myStorefronts property of Client Account
     * @return the Storefronts of the User
     */
    public Map<String, Storefront> getMyStorefronts(){
        return this.myStorefronts;
    }

    /**
     * Mutator method for the myProductList property of the Client Account
     * @param productListIn the list of products of the Client Account
     * @throws IllegalArgumentException if at least one of the AbstractProducts is invalid
     */
    public void setMyOwnedProductList(List<AbstractProduct> productListIn){
        this.myOwnedProductList = productListIn;
    }

    /**
     * Mutator method for the myStorefronts property of Client Account (not supposed to have a direct accessor)
     * @param myStorefrontsIn the storefronts
     */
    public void setMyStorefronts(Map<String, Storefront> myStorefrontsIn){
        this.myStorefronts = myStorefrontsIn;
    }

}
