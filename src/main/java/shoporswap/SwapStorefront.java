package shoporswap;

import java.util.*;

public class SwapStorefront extends Storefront {

    private List<SwapProduct> swapProducts;

    /**
     * Default constructor for shoporswap.SwapStorefront object
     */
    public SwapStorefront(){
        super();
        this.setSwapProducts(new ArrayList<SwapProduct>());
    }

    /**
     * Constructor for a shoporswap.SwapStorefront object
     * @param nameIn the name of the shoporswap.SwapStorefront
     * @param ownerIn the owner of the shoporswap.SwapStorefront
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if the owner of the shoporswap.Storefront is invalid
     */
    public SwapStorefront(String nameIn, Client ownerIn){
        super(nameIn, ownerIn);
        this.setSwapProducts(new ArrayList<SwapProduct>());
    }

    /**
     * Constructor for a shoporswap.SwapStorefront object
     * @param nameIn the name of the shoporswap.SwapStorefront
     * @param ownerIn the owner of the shoporswap.SwapStorefront
     * @param swapProductsIn the list of shoporswap.SwapProduct items in the shoporswap.SwapStorefront
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if the owner of the shoporswap.Storefront is invalid
     * @throws IllegalArgumentException if at least one of the shoporswap.SwapProduct items in SwapProductsIn is invalid
     */
    public SwapStorefront(String nameIn, Client ownerIn, List<SwapProduct> swapProductsIn){
        super(nameIn, ownerIn);
        this.setSwapProducts(swapProductsIn);
    }

    /**
     * Adds a shoporswap.SwapProduct to the shoporswap.SwapStorefront
     * @param swapProductIn the shoporswap.SwapProduct to swap at the shoporswap.SwapStorefront
     * @return the shoporswap.SwapProduct to waps at the shoporswap.SwapStorefront
     * @throws IllegalArgumentException if the shoporswap.SwapProduct to swap is not owned by the shoporswap.SwapProduct owner
     */
    public SwapProduct addProduct(SwapProduct swapProductIn){
        if(this.retrieveStorefrontOwner() != swapProductIn.getProductMerchant()){
            throw new IllegalArgumentException("Merchant must be the same as the store owner");
        }
        this.swapProducts.add(swapProductIn);
        return swapProductIn;
    }

    /**
     * Finds a shoporswap.SwapProduct in the shoporswap.SwapStorefront
     * @param swapProductIn the shoporswap.SwapProduct to find in the shoporswap.Storefront
     * @return the shoporswap.SwapProduct to find in the shoporswap.Storefront
     * @throws NoSuchElementException if swapProduct in does not exist in the shoporswap.SwapStorefront
     */
    public SwapProduct findProduct(SwapProduct swapProductIn){
        for(AbstractProduct product : this.swapProducts){
            if(product == swapProductIn){
                return (SwapProduct) product;
            }
        }
        throw new NoSuchElementException("Product does not exist in this storefront");
    }

    /**
     * Removes a shoporswap.SwapProduct from the shoporswap.SwapStorefront
     * @param swapProductIn the shoporswap.SwapProduct to remove from swapping at the shoporswap.SwapStorefront
     * @return the shoporswap.SwapProduct removed from selling at the shoporswap.SwapStorefront
     * @throws NoSuchElementException if swapProductIn does not exist in the shoporswap.SwapStorefront
     */
    public SwapProduct removeProduct(SwapProduct swapProductIn){
        for(AbstractProduct product : this.swapProducts){
            if(product == swapProductIn){
                this.swapProducts.remove(product);
                return (SwapProduct) product;
            }
        }
        throw new NoSuchElementException("Product does not exist in this storefront");
    }

    /**
     * Swaps two SwapProducts between Users in the shoporswap.SwapStorefront
     * @param swapProduct1 the first of two SwapProducts to swap in the shoporswap.SwapStorefront (initiator)
     * @param swapProduct2 the other of two SwapProducts to swap in the shoporswap.SwapStorefront (recipient)
     * @return swapProduct2, the other of two SwapProducts, now owned by the initiator
     * @throws IllegalArgumentException if swapProduct1 and swapProduct2 are the same shoporswap.SwapProduct
     * @throws IllegalArgumentException if swapProduct1 and swapProduct2 are owned by the same User
     */
    public SwapProduct completeTransaction(SwapProduct swapProduct1, SwapProduct swapProduct2){
        Client client1 = swapProduct1.getProductMerchant();
        Client client2 = swapProduct2.getProductMerchant();
        if(client1 == client2){
            throw new IllegalArgumentException("Swappers are the same");
        }
        if(swapProduct1 == swapProduct2){
            throw new IllegalArgumentException("Products are the same");
        }
        SwapProduct productToTrade = null;
        for(Storefront swapStorefront : client1.getMyStorefronts().values()){
            try{
                productToTrade = ((SwapStorefront) swapStorefront).removeProduct(swapProduct1);
            }catch(Exception e){

            }
        }
        if(productToTrade == null){
            throw new NoSuchElementException("Product to trade does not exist");
        }
        SwapProduct productTraded = null;
        for(Storefront swapStorefront : client2.getMyStorefronts().values()){
            try{
                productTraded = ((SwapStorefront) swapStorefront).removeProduct(swapProduct2);
            }catch(Exception e){

            }
        }
        if(productTraded == null){
            throw new NoSuchElementException("Product to trade does not exist");
        }
        client2.addSwapProduct(productToTrade);
        return client1.addSwapProduct(productTraded);
    }

    /**
     * Accessor method for the swapProducts property of the shoporswap.SwapStorefront
     * @return the list of products listed for swapping by the shoporswap.SwapStorefront
     */
    public List<SwapProduct> getSwapProducts(){
        return this.swapProducts;
    }

    @Override
    public List<AbstractProduct> getStorefrontProducts() {
        List<AbstractProduct> storefrontProductsOut = new ArrayList<AbstractProduct>();
        for(SwapProduct product : this.getSwapProducts()){
            storefrontProductsOut.add(product);
        }
        return storefrontProductsOut;
    }

    /**
     * Mutator method for the swapProducts property of the shoporswap.SwapStorefront
     * @param swapProductsIn the list of products for the shoporswap.SwapStorefront to swap
     */
    public void setSwapProducts(List<SwapProduct> swapProductsIn){
        this.swapProducts = swapProductsIn;
    }

}
