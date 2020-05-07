package io;

import shoporswap.*;
import java.util.*;

public class AccountRecord {

    private String accountName;
    private String accountPassword;
    private boolean isFrozen;
    private List<SellProduct> myOwnedSellProducts;
    private List<SwapProduct> myOwnedSwapProducts;
    private List<SellStorefront> mySellStorefronts;
    private List<SwapStorefront> mySwapStorefronts;

    /**
     * Default constructor for an AccountRecord object
     */
    public AccountRecord(){
        this.setAccountName("DefaultName");
        this.setAccountPassword("DefaultPassword");
        this.setIsFrozen(false);
        this.setMyOwnedSellProducts(new ArrayList<SellProduct>());
        this.setMyOwnedSwapProducts(new ArrayList<SwapProduct>());
        this.setMySellStorefronts(new ArrayList<SellStorefront>());
        this.setMySwapStorefronts(new ArrayList<SwapStorefront>());
    }

    /**
     * Constructor for an AccountRecord object
     * @param accountIn the Account to create an AccountRecord for
     * @throws IllegalArgumentException if accountIn is null
     */
    public AccountRecord(Account accountIn){
        if(accountIn == null){
            throw new IllegalArgumentException("Account to make an AccountRecord for cannot be null");
        }
        this.setAccountName(accountIn.getAccountName());
        this.setAccountPassword(accountIn.getAccountPassword());
        this.setIsFrozen(accountIn.getIsFrozen());
        if(accountIn instanceof Admin){
            this.setMyOwnedSellProducts(null);
            this.setMyOwnedSwapProducts(null);
            this.setMySellStorefronts(null);
            this.setMySwapStorefronts(null);
        }else{
            this.toStorefrontLists(((Client) accountIn).getMyStorefronts());
            this.toProductLists(((Client) accountIn).getMyOwnedProductList());
        }
    }

    /**
     * Converts Map object holding Storefront values by String keys to lists of SellStorefronts and SwapStorefronts
     * @param storefrontMapIn the Map of Storefront values by String keys to convert
     */
    public void toStorefrontLists(Map<String, Storefront> storefrontMapIn){
        this.setMySellStorefronts(this.toSellStorefrontList(storefrontMapIn));
        this.setMySwapStorefronts(this.toSwapStorefrontList(storefrontMapIn));
    }

    /**
     * Converts List holding AbstractProduct objects to lists of SellProducts and SwapProducts
     * @param productListIn the List of AbstractProduct objects to convert
     */
    public void toProductLists(List<AbstractProduct> productListIn){
        this.setMyOwnedSellProducts(this.toSellProductList(productListIn));
        this.setMyOwnedSwapProducts(this.toSwapProductList(productListIn));
    }

    /**
     * Converts Map object holding Storefront values by String keys to list of SellStorefronts
     * @param storefrontMapIn the Map of Storefront values by String keys to convert
     * @return a List of SellStorefronts contained in the Map
     */
    public List<SellStorefront> toSellStorefrontList(Map<String, Storefront> storefrontMapIn){
        List<SellStorefront> sellStorefrontsOut = new ArrayList<SellStorefront>();
        for(Storefront storefront : storefrontMapIn.values()){
            if(storefront instanceof SellStorefront){
                sellStorefrontsOut.add((SellStorefront) storefront);
            }
        }
        return sellStorefrontsOut;
    }

    /**
     * Converts List object holding AbstractProduct objects to list of SellProducts
     * @param productListIn the List of AbstractProduct objects to convert
     * @return a List of SellProducts contained in the List
     */
    public List<SellProduct> toSellProductList(List<AbstractProduct> productListIn){
        List<SellProduct> sellProductsOut = new ArrayList<SellProduct>();
        for(AbstractProduct product : productListIn){
            if(product instanceof SellProduct){
                sellProductsOut.add((SellProduct) product);
            }
        }
        return sellProductsOut;
    }

    /**
     * Converts Map object holding Storefront values by String keys to list of SwapStorefronts
     * @param storefrontMapIn the Map of Storefront values by String keys to convert
     * @return a List of SwapStorefronts contained in the Map
     */
    public List<SwapStorefront> toSwapStorefrontList(Map<String, Storefront> storefrontMapIn){
        List<SwapStorefront> swapStorefrontsOut = new ArrayList<SwapStorefront>();
        for(Storefront storefront : storefrontMapIn.values()){
            if(storefront instanceof SwapStorefront){
                swapStorefrontsOut.add((SwapStorefront) storefront);
            }
        }
        return swapStorefrontsOut;
    }

    /**
     * Converts List object holding AbstractProduct objects to list of SwapProducts
     * @param productListIn the List of AbstractProduct objects to convert
     * @return a List of SwapProducts contained in the List
     */
    public List<SwapProduct> toSwapProductList(List<AbstractProduct> productListIn){
        List<SwapProduct> swapProductsOut = new ArrayList<SwapProduct>();
        for(AbstractProduct product : productListIn){
            if(product instanceof SwapProduct){
                swapProductsOut.add((SwapProduct) product);
            }
        }
        return swapProductsOut;
    }

    /**
     * Converts this record into an Account usable in the ShopOrSwap class
     * @return
     */
    public Account toAccount(){
        boolean hasNullClientProperties = (
                this.getMyOwnedSellProducts() == null &&
                this.getMyOwnedSwapProducts() == null &&
                this.getMySellStorefronts() == null &&
                this.getMySwapStorefronts() == null
        );
        Account accountOut;
        if(hasNullClientProperties){
            accountOut = new Admin(this.getAccountName(), this.getAccountPassword());
        }else{
            accountOut = new Client(this.getAccountName(), this.getAccountPassword());
            ((Client) accountOut).setMyOwnedProductList(this.makeAbstractProductList());
            ((Client) accountOut).setMyStorefronts(this.makeStorefrontMap());
        }
        return accountOut;
    }

    public List<AbstractProduct> makeAbstractProductList(){
        List<AbstractProduct> productsOut = new ArrayList<AbstractProduct>();
        productsOut.addAll(this.getMyOwnedSellProducts());
        productsOut.addAll(this.getMyOwnedSwapProducts());
        return productsOut;
    }

    public Map<String, Storefront> makeStorefrontMap(){
        Map<String, Storefront> storefrontsOut = new HashMap<String, Storefront>();
        for(SellStorefront sellStorefront : this.getMySellStorefronts()){
            if(storefrontsOut.containsKey(sellStorefront.getStorefrontName())){
                throw new IllegalArgumentException("Selling Storefront already exists");
            }
            storefrontsOut.put(sellStorefront.getStorefrontName(), sellStorefront);
        }
        for(SwapStorefront swapStorefront : this.getMySwapStorefronts()){
            if(storefrontsOut.containsKey(swapStorefront.getStorefrontName())){
                throw new IllegalArgumentException("Swapping Storefront already exists");
            }
            storefrontsOut.put(swapStorefront.getStorefrontName(), swapStorefront);
        }
        return storefrontsOut;
    }


    /**
     * Accessor method for the property of the AccountRecord object
     * @return
     */
    public String getAccountName() {
        return this.accountName;
    }

    /**
     * Mutator method for the property of the AccountRecord object
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Accessor method for the property of the AccountRecord object
     * @return
     */
    public String getAccountPassword() {
        return this.accountPassword;
    }

    /**
     * Mutator method for the property of the AccountRecord object
     */
    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    /**
     * Accessor method for the property of the AccountRecord object
     * @return
     */
    public List<SellProduct> getMyOwnedSellProducts() {
        return this.myOwnedSellProducts;
    }

    /**
     * Mutator method for the property of the AccountRecord object
     */
    public void setMyOwnedSellProducts(List<SellProduct> myOwnedSellProducts) {
        this.myOwnedSellProducts = myOwnedSellProducts;
    }

    /**
     * Accessor method for the property of the AccountRecord object
     * @return
     */
    public List<SwapProduct> getMyOwnedSwapProducts() {
        return this.myOwnedSwapProducts;
    }

    /**
     * Mutator method for the property of the AccountRecord object
     */
    public void setMyOwnedSwapProducts(List<SwapProduct> myOwnedSwapProducts) {
        this.myOwnedSwapProducts = myOwnedSwapProducts;
    }

    /**
     * Accessor method for the property of the AccountRecord object
     * @return
     */
    public List<SellStorefront> getMySellStorefronts() {
        return this.mySellStorefronts;
    }

    /**
     * Mutator method for the property of the AccountRecord object
     */
    public void setMySellStorefronts(List<SellStorefront> mySellStorefronts) {
        this.mySellStorefronts = mySellStorefronts;
    }

    /**
     * Accessor method for the property of the AccountRecord object
     * @return
     */
    public List<SwapStorefront> getMySwapStorefronts() {
        return this.mySwapStorefronts;
    }

    /**
     * Mutator method for the property of the AccountRecord object
     */
    public void setMySwapStorefronts(List<SwapStorefront> mySwapStorefronts) {
        this.mySwapStorefronts = mySwapStorefronts;
    }

    public boolean getIsFrozen() {
        return this.isFrozen;
    }

    public void setIsFrozen(boolean frozen) {
        this.isFrozen = frozen;
    }
}
