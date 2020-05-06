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
    public ShopOrSwap(){
        this.setMessageFactory(new AbstractMessageFactory());
        this.setAccountFactory(new AccountFactory());
        this.setStorefrontFactory(new StorefrontFactory());
        this.setProductFactory(new AbstractProductFactory());
        this.setAccountCollection(new HashMap<String, Account>());
    }

    /**
     * Adds an Account to the ShopOrSwap system
     * @param accountIn
     * @return
     */
    public Account addAccount(Account accountIn){
        if(this.getAccountCollection().containsKey(accountIn.getAccountName())){
            throw new IllegalArgumentException("Account name already exists in system");
        }
        this.getAccountCollection().put(accountIn.getAccountName(), accountIn);
        return this.getAccountCollection().get(accountIn.getAccountName());
    }

    /**
     * Adds an Account to the ShopOrSwap system
     * @param typeIn
     * @param nameIn
     * @param passwordIn
     * @return
     */
    public Account addAccount(String typeIn, String nameIn, String passwordIn){
        Account account = this.getAccountFactory().getAccount(typeIn);
        account.setAccountName(nameIn);
        account.setAccountPassword(passwordIn);
        return this.addAccount(account);
    }

    /**
     * Finds an Account in the ShopOrSwap system
     * @param accountIn
     * @return
     * @throws NoSuchElementException if accountIn does not exist in the system
     */
    public Account findAccount(Account accountIn){
        return this.findAccount(accountIn.getAccountName());
    }

    /**
     * Finds an Account in the ShopOrSwap system
     * @param nameIn
     * @return
     * @throws NoSuchElementException if accountIn does not exist in the system
     */
    public Account findAccount(String nameIn){
        if(this.getAccountCollection().containsKey(nameIn)){
            return this.getAccountCollection().get(nameIn);
        }
        throw new NoSuchElementException("Account name does not exist in system");
    }

    /**
     * Removes an Account from the ShopOrSwap system
     * @param accountIn
     * @return
     */
    public Account removeAccount(Account accountIn){
        Account account = this.findAccount(accountIn);
        this.getAccountCollection().remove(accountIn.getAccountName());
        return account;
    }

    /**
     * Removes an Account from the ShopOrSwap system
     * @param nameIn
     * @return
     */
    public Account removeAccount(String nameIn){
        Account account = this.findAccount(nameIn);
        this.removeAccount(account);
        return account;
    }

    /**
     * Adds a Storefront to the ShopOrSwap system
     * @param nameIn the name of the Storefront to add
     * @param ownerIn the owner of the Storefront to add
     * @return
     * @throws IllegalArgumentException if nameIn is invalid for Storefront name
     * @throws NoSuchElementException if the ownerIn is not a member of the System
     * @throws IllegalArgumentException if a Storefront already exists for ownerIn with the name of nameIn
     */
    public Storefront addStorefront(String typeIn, String nameIn, Client ownerIn){
        Account owner = this.findAccount(ownerIn);
        Storefront storefront = this.storefrontFactory.getStorefront(typeIn);
        storefront.setStorefrontName(nameIn);
        storefront.establishStorefrontOwner(ownerIn);
        ownerIn.addStorefront(storefront);
        return storefront;
    }

    /**
     * Finds a Storefront in the ShopOrSwap system
     * @param nameIn the name of the Storefront
     * @param ownerIn the owner of the Storefront
     * @return
     * @throws NoSuchElementException if the Storefront cannot be found
     */
    public Storefront findStorefront(String nameIn, Client ownerIn){
        Client owner = (Client) this.findAccount(ownerIn);
        if(owner.getMyStorefronts().containsKey(nameIn)){
            return owner.getMyStorefronts().get(nameIn);
        }
        throw new NoSuchElementException("Storefront does not exist for owner");
    }

    /**
     * Removes a Storefront from the ShopOrSwap system
     * @param nameIn the name of the Storefront to remove
     * @param ownerIn the owner of the Storefront to remove
     * @return
     * @throws NoSuchElementException if the Storefront cannot be found
     */
    public Storefront removeStorefront(String nameIn, Client ownerIn){
        Storefront storefront = this.findStorefront(nameIn, ownerIn);
        ownerIn.getMyStorefronts().remove(nameIn);
        return storefront;
    }

    /**
     * Adds a product to a Storefront
     * @param nameIn
     * @param descriptionIn
     * @param valueIn
     * @param storefrontIn
     * @return
     * @throws IllegalArgumentException if nameIn, descriptionIn, or valueIn are invalid
     * @throws NoSuchElementException if storefrontIn does not exist (if owner Client of storefrontIn does not own the instance of storefrontIn)
     */
    public AbstractProduct addToStorefront(String nameIn, String descriptionIn, double valueIn, Storefront storefrontIn){
        Storefront storefrontTemp = this.findStorefront(storefrontIn.getStorefrontName(), storefrontIn.retrieveStorefrontOwner());
        AbstractProduct product;
        if(storefrontIn.getClass().getName().contains((CharSequence) "Sell")){
            product = this.getProductFactory().getProduct("sell");
            product.setProductName(nameIn);
            product.setProductDescription(descriptionIn);
            product.setProductValue(valueIn);
            product.setProductMerchant(storefrontIn.retrieveStorefrontOwner());
            return this.addToStorefront(product, storefrontTemp);
        }else{
            product = this.getProductFactory().getProduct("swap");
            product.setProductName(nameIn);
            product.setProductDescription(descriptionIn);
            product.setProductValue(valueIn);
            product.setProductMerchant(storefrontIn.retrieveStorefrontOwner());
            return this.addToStorefront(product, storefrontTemp);
        }
    }

    /**
     * Adds a product object to a Storefront
     * @param productIn
     * @param storefrontIn
     * @return
     * @throws NoSuchElementException if storefrontIn does not exist (if owner Client of storefrontIn does not own the instance of storefrontIn)
     */
    public AbstractProduct addToStorefront(AbstractProduct productIn, Storefront storefrontIn){
        Client owner = storefrontIn.retrieveStorefrontOwner();
        if(!this.getAccountCollection().containsKey(owner.getAccountName())){
            throw new NoSuchElementException("Storefront owner does not exist");
        }
        Storefront storefront = owner.findStorefront(storefrontIn);
        if(storefront.getClass().getName().contains((CharSequence) "Sell")){
            SellStorefront sellStorefront = (SellStorefront) storefront;
            return sellStorefront.addProduct((SellProduct) productIn);
        }else{
            SwapStorefront swapStorefront = (SwapStorefront) storefront;
            return swapStorefront.addProduct((SwapProduct) productIn);
        }
    }

    /**
     * Finds a product in a Storefront
     * @param productIn
     * @param storefrontIn
     * @return
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct findInStorefront(AbstractProduct productIn, Storefront storefrontIn){
        Account account = storefrontIn.retrieveStorefrontOwner();
        if(account == null){
            throw new NoSuchElementException("There are no null accounts in Storefront");
        }
        Storefront storefront = this.findStorefront(storefrontIn.getStorefrontName(), storefrontIn.retrieveStorefrontOwner());
        return this.findInStorefront(productIn.getProductName(), storefrontIn);
    }

    /**
     * Finds a product in a Storefront
     * @param nameIn
     * @param storefrontIn
     * @return
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct findInStorefront(String nameIn, Storefront storefrontIn){
        if(storefrontIn.getClass().getName().contains((CharSequence) "Sell")){
            SellStorefront sellStorefront = (SellStorefront) storefrontIn;
            for(SellProduct product : sellStorefront.getSellProducts()){
                if(product.getProductName().compareTo(nameIn) == 0){
                    return product;
                }
            }
        }else{
            SwapStorefront sellStorefront = (SwapStorefront) storefrontIn;
            for(SwapProduct product : sellStorefront.getSwapProducts()){
                if(product.getProductName().compareTo(nameIn) == 0){
                    return product;
                }
            }
        }
        throw new NoSuchElementException("Product does not exist in storefront");
    }

    /**
     * Removes a product from the Storefront
     * @param productIn
     * @param storefrontIn
     * @return
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct removeFromStorefront(AbstractProduct productIn, Storefront storefrontIn){
        AbstractProduct product = this.findInStorefront(productIn, storefrontIn);
        if(storefrontIn.getClass().getName().contains((CharSequence) "Sell")){
            SellStorefront sellStorefront = (SellStorefront) storefrontIn;
            return sellStorefront.removeProduct((SellProduct) product);
        }else{
            SwapStorefront swapStorefront = (SwapStorefront) storefrontIn;
            return swapStorefront.removeProduct((SwapProduct) product);
        }
    }

    /**
     * Removes a product from the Storefront
     * @param nameIn
     * @param storefrontIn
     * @return
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct removeFromStorefront(String nameIn, Storefront storefrontIn){
        AbstractProduct product = this.findInStorefront(nameIn, storefrontIn);
        return this.removeFromStorefront(product, storefrontIn);
    }

    /**
     * System process for buying products
     * @param productToBuy
     * @param consumerIn
     */
    public void buyProduct(Storefront storefrontIn, AbstractProduct productToBuy, Client consumerIn){
        if(storefrontIn.getClass().getName().contains((CharSequence) "Sell")){
            SellStorefront sellStorefront = (SellStorefront) storefrontIn;
            AbstractProduct product = sellStorefront.completeTransaction((SellProduct) productToBuy, consumerIn);
        }else{
            throw new IllegalArgumentException("Storefront must be a selling storefront");
        }
    }

    /**
     * System process for swapping products
     * @param product1
     * @param product2
     */
    public void swapProducts(Storefront storefront1, AbstractProduct product1, Storefront storefront2, AbstractProduct product2){
        if(storefront1.getClass().getName().contains("Swap") && storefront2.getClass().getName().contains("Swap")){
            SwapStorefront swapStorefront1 = (SwapStorefront) storefront1;
            SwapProduct swapProduct1 = swapStorefront1.findProduct((SwapProduct) product1);
            SwapStorefront swapStorefront2 = (SwapStorefront) storefront2;
            SwapProduct swapProduct2 = swapStorefront2.findProduct((SwapProduct) product2);
            swapStorefront1.completeTransaction((SwapProduct) product1, (SwapProduct) product2);
        }else {
            throw new IllegalArgumentException("Storefronts must be swapping storefronts");
        }
    }

    /**
     * System process for message communications
     * @param messageIn
     */
    public void sendMessage(AbstractMessage messageIn){}

    public AccountFactory getAccountFactory(){
        return this.accountFactory;
    }

    public AbstractProductFactory getProductFactory(){
        return this.productFactory;
    }

    public AbstractMessageFactory getMessageFactory(){
        return this.messageFactory;
    }

    public StorefrontFactory getStorefrontFactory(){
        return this.storefrontFactory;
    }

    public Map<String, Account> getAccountCollection(){
        return this.accountCollection;
    }

    public void setAccountCollection(Map<String, Account> accountCollectionIn){
        this.accountCollection = accountCollectionIn;
    }

    public void setAccountFactory(AccountFactory accountFactoryIn){
        this.accountFactory = accountFactoryIn;
    }

    public void setProductFactory(AbstractProductFactory abstractProductFactoryIn){
        this.productFactory = abstractProductFactoryIn;
    }

    public void setMessageFactory(AbstractMessageFactory abstractMessageFactoryIn){
        this.messageFactory = abstractMessageFactoryIn;
    }

    public void setStorefrontFactory(StorefrontFactory storefrontFactoryIn){
        this.storefrontFactory = storefrontFactoryIn;
    }

}
