import java.util.*;
import java.io.*;

public class ShopOrSwap {

    private AbstractMessageFactory messageFactory;
    private AccountFactory accountFactory;
    private StorefrontFactory storefrontFactory;
    private AbstractProductFactory productFactory;
    private Map<String, Account> accountCollection;

    /**
     * Default constructor for the ShopOrSwap system
     */
    public ShopOrSwap(){}

    /**
     * Adds an Account to the ShopOrSwap system
     * @param accountIn
     * @return
     */
    public Account addAccount(Account accountIn){
        return null;
    }

    /**
     * Finds an Account in the ShopOrSwap system
     * @param accountIn
     * @return
     */
    public Account findAccount(Account accountIn){
        return null;
    }

    /**
     * Removes an Account from the ShopOrSwap system
     * @param accountIn
     * @return
     */
    public Account removeAccount(Account accountIn){
        return null;
    }

    /**
     * Adds a Storefront to the ShopOrSwap system
     * @param storefrontIn
     * @return
     */
    public Storefront addStorefront(Storefront storefrontIn){
        return null;
    }

    /**
     * Finds a Storefront in the ShopOrSwap system
     * @param storefrontIn
     * @return
     */
    public Storefront findStorefront(Storefront storefrontIn){
        return null;
    }

    /**
     * Removes a Storefront from the ShopOrSwap system
     * @param storefrontIn
     * @return
     */
    public Storefront removeStorefront(Storefront storefrontIn){
        return null;
    }

    /**
     * Adds a product to a Storefront
     * @param productIn
     * @param storefrontIn
     * @return
     * @throws NoSuchElementException if storefrontIn does not exist (if owner Client of storefrontIn does not own the instance of storefrontIn)
     */
    public AbstractProduct addToStorefront(AbstractProduct productIn, Storefront storefrontIn){
        return null;
    }

    /**
     * Finds a product in a Storefront
     * @param productIn
     * @param storefrontIn
     * @return
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct findInStorefront(AbstractProduct productIn, Storefront storefrontIn){
        return null;
    }

    /**
     * Removes a product from the Storefront
     * @param productIn
     * @param storefrontIn
     * @return
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct removeFromStorefront(AbstractProduct productIn, Storefront storefrontIn){
        return null;
    }

    /**
     * System process for buying products
     * @param productToBuy
     * @param consumerIn
     */
    public void buyProduct(AbstractProduct productToBuy, Client consumerIn){}

    /**
     * System process for swapping products
     * @param product1
     * @param product2
     */
    public void swapProducts(AbstractProduct product1, AbstractProduct product2){}

    /**
     * System process for message communications
     * @param messageIn
     */
    public void sendMessage(AbstractMessage messageIn){}

    public AccountFactory getAccountFactory(){
        return null;
    }

    public AbstractProductFactory getProductFactory(){
        return null;
    }

    public AbstractMessageFactory getMessageFactory(){
        return null;
    }

    public StorefrontFactory getStorefrontFactory(){
        return null;
    }

    public Map<String, Account> getAccountCollection(){
        return null;
    }

    public void setAccountCollection(Map<String, Account> accountCollectionIn){

    }

    public void setAccountFactory(AccountFactory accountFactoryIn){}

    public void setProductFactory(AbstractProductFactory abstractProductFactoryIn){}

    public void setMessageFactory(AbstractMessageFactory abstractMessageFactoryIn){}

    public void setStorefrontFactory(StorefrontFactory storefrontFactoryIn){}

}
