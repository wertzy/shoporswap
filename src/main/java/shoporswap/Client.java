package shoporswap;

import java.math.BigDecimal;
import java.util.*;

public class Client extends Account{

    private List<AbstractProduct> myOwnedProductList;
    private Map<String, Storefront> myStorefronts;
    private double wallet=0, rating=0;
    public List<Integer> numOfRatings=new ArrayList<Integer>();
    public List<Client> pastMerchants=new ArrayList<Client>();

    /**
     * Default constructor for shoporswap.Client object
     */
    public Client(){
        super("DefaultClient", "DefaultPassword");
        this.setMyOwnedProductList(new ArrayList<AbstractProduct>());
        this.setMyStorefronts(new HashMap<String, Storefront>());
    }

    /**
     * Constructor for shoporswap.Client shoporswap.Account object
     * @param nameIn the name of the shoporswap.Client shoporswap.Account
     * @param passwordIn the password of the shoporswap.Client shoporswap.Account
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if passwordIn is invalid
     */
    public Client(String nameIn, String passwordIn){
        super(nameIn, passwordIn);
        this.setMyOwnedProductList(new ArrayList<AbstractProduct>());
        this.setMyStorefronts(new HashMap<String, Storefront>());
    }

    /**
     * Adds a shoporswap.SellProduct to owned shoporswap.AbstractProduct list (from transaction)
     * @param productIn the shoporswap.SellProduct to add
     * @return the shoporswap.SellProduct added
     */
    public SellProduct addSellProduct(SellProduct productIn){
        this.myOwnedProductList.add(productIn);
        return productIn;
    }

    /**
     * Adds a shoporswap.SwapProduct to owned shoporswap.AbstractProduct list (from transaction)
     * @param productIn the shoporswap.SwapProduct to add
     * @return the shoporswap.SwapProduct added
     */
    public SwapProduct addSwapProduct(SwapProduct productIn){
        this.myOwnedProductList.add(productIn);
        return productIn;
    }

    /**
     * Finds a shoporswap.SellProduct in the owned shoporswap.AbstractProduct list
     * @param productIn the shoporswap.SellProduct to find
     * @return the shoporswap.SellProduct found
     * @throws NoSuchElementException if the shoporswap.SellProduct does not exist in the shoporswap.AbstractProduct list
     */
    public SellProduct findSellProduct(SellProduct productIn){
        for(AbstractProduct product : this.myOwnedProductList){
            if(product == productIn){
                return (SellProduct) product;
            }
        }
        throw new NoSuchElementException("shoporswap.SellProduct does not exist");
    }

    /**
     * Finds a shoporswap.SwapProduct in the owned shoporswap.AbstractProduct list
     * @param productIn the shoporswap.SwapProduct to find
     * @return the shoporswap.SwapProduct found
     * @throws NoSuchElementException if the shoporswap.SwapProduct does not exist in the shoporswap.AbstractProduct list
     */
    public SwapProduct findSwapProduct(SwapProduct productIn){
        for(AbstractProduct product : this.myOwnedProductList){
            if(product == productIn){
                return (SwapProduct) product;
            }
        }
        throw new NoSuchElementException("shoporswap.SwapProduct does not exist");
    }

    /**
     * Removes a shoporswap.SellProduct from the owned shoporswap.AbstractProduct list
     * @param productIn the shoporswap.SellProduct to remove
     * @return the shoporswap.SellProduct removed
     * @throws NoSuchElementException if the shoporswap.SellProduct does not exist in the shoporswap.AbstractProduct list
     */
    public SellProduct removeSellProduct(SellProduct productIn){
        for(AbstractProduct product : this.myOwnedProductList){
            if(product == productIn){
                this.myOwnedProductList.remove(product);
                return (SellProduct) product;
            }
        }
        throw new NoSuchElementException("shoporswap.SwapProduct does not exist");
    }

    /**
     * Adds a shoporswap.Storefront to the shoporswap.Client's collection of Storefronts
     * @param storefrontIn the shoporswap.Storefront to add
     * @return the shoporswap.Storefront that is added
     * @throws IllegalArgumentException if storefrontIn is invalid (if there is already a shoporswap.Storefront with that name for the User)
     */
    public Storefront addStorefront(Storefront storefrontIn){
        if(this.getMyStorefronts().containsKey(storefrontIn.getStorefrontName())){
            throw new IllegalArgumentException("shoporswap.Storefront invalid (you already have a shoporswap.Storefront with this name)");
        }
        this.getMyStorefronts().put(storefrontIn.getStorefrontName(), storefrontIn);
        return this.findStorefront(storefrontIn);
    }

    /**
     * Finds a shoporswap.Storefront in the shoporswap.Client's collection of Storefronts
     * @param storefrontIn the shoporswap.Storefront to find
     * @return the shoporswap.Storefront that is found
     * @throws NoSuchElementException if storefrontIn is invalid (if the shoporswap.Storefront does not exist in the collection of the shoporswap.Client's Storefronts)
     */
    public Storefront findStorefront(Storefront storefrontIn){
        if(this.getMyStorefronts().containsKey(storefrontIn.getStorefrontName())){
            return this.getMyStorefronts().get(storefrontIn.getStorefrontName());
        }
        throw new NoSuchElementException("shoporswap.Storefront invalid (you do not have a shoporswap.Storefront with this name)");
    }

    /**
     * Removes a shoporswap.Storefront in the shoporswap.Client's collection of Storefronts
     * @param storefrontIn the shoporswap.Storefront to remove
     * @return the shoporswap.Storefront that is removed
     * @throws NoSuchElementException if storefrontIn is invalid (if the shoporswap.Storefront does not exist in the collection of the shoporswap.Client's Storefronts)
     */
    public Storefront removeStorefront(Storefront storefrontIn){
        if(this.getMyStorefronts().containsKey(storefrontIn.getStorefrontName())){
            return this.getMyStorefronts().remove(storefrontIn.getStorefrontName());
        }
        throw new NoSuchElementException("shoporswap.Storefront invalid (you do not have a shoporswap.Storefront with this name)");
    }

    /**
     * Removes a shoporswap.SwapProduct form the owned shoporswap.AbstractProduct list
     * @param productIn the shoporswap.SwapProduct to remove
     * @return the shoporswap.SwapProduct removed
     * @throws NoSuchElementException if the shoporswap.SwapProduct does not exist in the shoporswap.AbstractProduct list
     */
    public SwapProduct removeSwapProduct(SwapProduct productIn){
        for(AbstractProduct product : this.myOwnedProductList){
            if(product == productIn){
                this.myOwnedProductList.remove(product);
                return (SwapProduct) product;
            }
        }
        throw new NoSuchElementException("shoporswap.SwapProduct does not exist");
    }

    /**
     * Accessor method for the myProductList property of the shoporswap.Client shoporswap.Account
     * @return the list of products of the shoporswap.Client shoporswap.Account
     */
    public List<AbstractProduct> getMyOwnedProductList(){
        return this.myOwnedProductList;
    }

    /**
     * Accessor for the myStorefronts property of shoporswap.Client shoporswap.Account
     * @return the Storefronts of the User
     */
    public Map<String, Storefront> getMyStorefronts(){
        return this.myStorefronts;
    }

    /**
     * Accessor method for the myProductList property of the shoporswap.Client shoporswap.Account
     * @return the list of products of the shoporswap.Client shoporswap.Account
     */
    public double getRating(){
        return this.rating;
    }

    /**
     * Accessor method for the wallet property
     * @return the wallet amount in user
     */
    public double getWallet(){
        return this.wallet;
    }

    /**
     * Mutator method for the myProductList property of the shoporswap.Client shoporswap.Account
     * @param productListIn the list of products of the shoporswap.Client shoporswap.Account
     * @throws IllegalArgumentException if at least one of the AbstractProducts is invalid
     */
    public void setMyOwnedProductList(List<AbstractProduct> productListIn){
        this.myOwnedProductList = productListIn;
    }

    /**
     * Mutator method for the myStorefronts property of shoporswap.Client shoporswap.Account (not supposed to have a direct accessor)
     * @param myStorefrontsIn the storefronts
     */
    public void setMyStorefronts(Map<String, Storefront> myStorefrontsIn){
        this.myStorefronts = myStorefrontsIn;
    }

    /**
     * Method to check if amount is valid or not
     * @param amount
     * @return true if amount is valid, false if amount is invalid
     */

    public boolean isValidAmount(double amount){
        if(amount<=0) {
            return false;
        }
        if(BigDecimal.valueOf(amount).scale()>2){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     *adds amount of money into wallet
     * @param amount
     */
    public void addWallet(double amount){
        if(!isValidAmount(amount)){
            throw new IllegalArgumentException("amount will lead to wallet to have a negative amount");
        }
        wallet+=amount;
    }

    /**
     *subtracts amount of money into wallet
     * @param amount
     */
    public void subtractWallet(double amount){
        if(!isValidAmount(amount)){
            throw new IllegalArgumentException("amount will lead to wallet to have a negative amount");
        }
        if(wallet-amount<0){
            throw new IllegalArgumentException("amount will lead to wallet to have a negative amount");
        }
        wallet-=amount;
    }

    /**
     * adds rating to list of ratings to be used to calculate an average later
     * @param rating
     */
    public void rate(int rating){
        if (rating>5||rating<=0){
            throw new IllegalArgumentException("rating cannot be greater than 5");
        }
        numOfRatings.add(rating);
        calculateRating();
    }

    /**
     * calculates the average for ratings
     */

    public void calculateRating() {
        int sum=0;
        for (int i=0; i<numOfRatings.size();i++){
            sum+=numOfRatings.get(i);
        }
        rating=sum/numOfRatings.size();
    }

    public void addMerchant(Client merchant){
        pastMerchants.add(merchant);
    }

    public void removeMerchant(Client merchant){
        pastMerchants.remove(merchant);
    }

}
