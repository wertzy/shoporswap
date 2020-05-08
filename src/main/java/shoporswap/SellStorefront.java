package shoporswap;

import java.util.*;

public class SellStorefront extends Storefront {

    private List<SellProduct> sellProducts;

    /**
     * Default constructor for a shoporswap.SellStorefront object
     */
    public SellStorefront(){
        super();
        this.setSellProducts(new ArrayList<SellProduct>());
    }

    /**
     * Constructor for a shoporswap.SellStorefront object
     * @param nameIn the name of the shoporswap.SellStorefront
     * @param ownerIn the owner of the shoporswap.SellStorefront
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if the owner of the shoporswap.Storefront is invalid
     */
    public SellStorefront(String nameIn, Client ownerIn){
        super(nameIn, ownerIn);
        this.setSellProducts(new ArrayList<SellProduct>());
    }

    /**
     * Constructor for a shoporswap.SellStorefront object
     * @param nameIn the name of the shoporswap.SellStorefront
     * @param ownerIn the owner of the shoporswap.SellStorefront
     * @param sellProductsIn the list of shoporswap.SellProduct items in the shoporswap.SellStorefront
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if the owner of the shoporswap.Storefront is invalid
     * @throws IllegalArgumentException if at least one of the shoporswap.SellProduct items in sellProductsIn is invalid
     */
    public SellStorefront(String nameIn, Client ownerIn, List<SellProduct> sellProductsIn){
        super(nameIn, ownerIn);
        this.setSellProducts(sellProductsIn);
    }

    /**
     * Adds a shoporswap.SellProduct to the shoporswap.SellStorefront
     * @param sellProductIn the shoporswap.SellProduct to sell at the shoporswap.SellStorefront
     * @return the shoporswap.SellProduct to sell at the shoporswap.SellStorefront
     * @throws IllegalArgumentException if the shoporswap.SellProduct to sell is not owned by the shoporswap.SellStorefront owner
     */
    public SellProduct addProduct(SellProduct sellProductIn){
        if(this.retrieveStorefrontOwner() != sellProductIn.getProductMerchant()){
            throw new IllegalArgumentException("Merchant must be the same as the store owner");
        }
        this.sellProducts.add(sellProductIn);
        return sellProductIn;
    }

    /**
     * Finds a shoporswap.SellProduct in the shoporswap.SellStorefront
     * @param sellProductIn the shoporswap.SellProduct to find in the shoporswap.Storefront
     * @return the shoporswap.SellProduct to find in the shoporswap.Storefront
     * @throws NoSuchElementException if sellProductIn does not exist in the shoporswap.SellStorefront
     */
    public SellProduct findProduct(SellProduct sellProductIn){
        for(AbstractProduct product : this.sellProducts){
            if(product == sellProductIn){
                return (SellProduct) product;
            }
        }
        throw new NoSuchElementException("Product does not exist in this storefront");
    }

    /**
     * Removes a shoporswap.SellProduct from the shoporswap.SellStorefront
     * @param sellProductIn the shoporswap.SellProduct to remove from selling at the shoporswap.SellStorefront
     * @return the shoporswap.SellProduct removed from selling at the shoporswap.SellStorefront
     * @throws NoSuchElementException if sellProductIn does not exist in the shoporswap.SellStorefront
     */
    public SellProduct removeProduct(SellProduct sellProductIn){
        for(AbstractProduct product : this.sellProducts){
            if(product == sellProductIn){
                this.sellProducts.remove(product);
                return (SellProduct) product;
            }
        }
        throw new NoSuchElementException("Product does not exist in this storefront");
    }

    /**
     * Sells a shoporswap.SellProduct to a User in the shoporswap.SellStorefront
     * @param sellProductIn the shoporswap.SellProduct to sell to the User to in the shoporswap.SellStorefront
     * @param consumerIn the User to sell the shoporswap.SellProduct to in the shoporswap.SellStorefront
     * @return the shoporswap.SellProduct sold to the User in the shoporswap.SellStorefront
     */
    public SellProduct completeTransaction(SellProduct sellProductIn, Client consumerIn){
        SellProduct product = this.removeProduct(sellProductIn);
        consumerIn.addSellProduct(product);
        consumerIn.subtractWallet(sellProductIn.getProductValue());
        Client merchant=sellProductIn.getProductMerchant();
        merchant.addWallet(sellProductIn.getProductValue());
        consumerIn.addMerchant(merchant);
        return product;
    }

    /**
     * Accessor method for the sellProducts property of the shoporswap.SellStorefront
     * @return the list of products listed for sale by the shoporswap.SellStorefront
     */
    public List<SellProduct> getSellProducts(){
        return this.sellProducts;
    }

    @Override
    public List<AbstractProduct> getStorefrontProducts() {
        List<AbstractProduct> storefrontProductsOut = new ArrayList<AbstractProduct>();
        for(SellProduct product : this.getSellProducts()){
            storefrontProductsOut.add(product);
        }
        return storefrontProductsOut;
    }

    /**
     * Mutator method for the sellProducts property of the shoporswap.SellStorefront
     * @param sellProductsIn the list of products for the shoporswap.SellStorefront to sell
     */
    public void setSellProducts(List<SellProduct> sellProductsIn){
        this.sellProducts = new ArrayList<SellProduct>();
        for(SellProduct product : sellProductsIn){
            this.sellProducts.add(product);
        }
    }

}
