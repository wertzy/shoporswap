import java.util.*;

public class SwapStorefront extends Storefront {

    private List<AbstractProduct> swapProducts;

    /**
     * Default constructor for SwapStorefront object
     */
    public SwapStorefront(){
        super();
        this.setSwapProducts(new ArrayList<SwapProduct>());
    }

    /**
     * Constructor for a SwapStorefront object
     * @param nameIn the name of the SwapStorefront
     * @param ownerIn the owner of the SwapStorefront
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if the owner of the Storefront is invalid
     */
    public SwapStorefront(String nameIn, Client ownerIn){
        super(nameIn, ownerIn);
        this.setSwapProducts(new ArrayList<SwapProduct>());
    }

    /**
     * Constructor for a SwapStorefront object
     * @param nameIn the name of the SwapStorefront
     * @param ownerIn the owner of the SwapStorefront
     * @param swapProductsIn the list of SwapProduct items in the SwapStorefront
     * @throws IllegalArgumentException if nameIn is invalid
     * @throws IllegalArgumentException if the owner of the Storefront is invalid
     * @throws IllegalArgumentException if at least one of the SwapProduct items in SwapProductsIn is invalid
     */
    public SwapStorefront(String nameIn, Client ownerIn, List<SwapProduct> swapProductsIn){
        super(nameIn, ownerIn);
        this.setSwapProducts(swapProductsIn);
    }

    /**
     * Adds a SwapProduct to the SwapStorefront
     * @param swapProductIn the SwapProduct to swap at the SwapStorefront
     * @return the SwapProduct to waps at the SwapStorefront
     * @throws IllegalArgumentException if the SwapProduct to swap is not owned by the SwapProduct owner
     */
    public SwapProduct addProduct(SwapProduct swapProductIn){
        if(this.getStorefrontOwner() != swapProductIn.getProductMerchant()){
            throw new IllegalArgumentException("Merchant must be the same as the store owner");
        }
        this.swapProducts.add(swapProductIn);
        return swapProductIn;
    }

    /**
     * Finds a SwapProduct in the SwapStorefront
     * @param swapProductIn the SwapProduct to find in the Storefront
     * @return the SwapProduct to find in the Storefront
     * @throws NoSuchElementException if swapProduct in does not exist in the SwapStorefront
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
     * Removes a SwapProduct from the SwapStorefront
     * @param swapProductIn the SwapProduct to remove from swapping at the SwapStorefront
     * @return the SwapProduct removed from selling at the SwapStorefront
     * @throws NoSuchElementException if swapProductIn does not exist in the SwapStorefront
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
     * Swaps two SwapProducts between Users in the SwapStorefront
     * @param swapProduct1 the first of two SwapProducts to swap in the SwapStorefront (initiator)
     * @param swapProduct2 the other of two SwapProducts to swap in the SwapStorefront (recipient)
     * @return the other of two SwapProducts, now owned by the initiator
     * @throws IllegalArgumentException if swapProduct1 and swapProduct2 are the same SwapProduct
     * @throws IllegalArgumentException if swapProduct1 and swapProduct2 are owned by the same User
     */
    public SwapProduct completeTransaction(SwapProduct swapProduct1, SwapProduct swapProduct2){
        return null;
    }

    /**
     * Accessor method for the swapProducts property of the SwapStorefront
     * @return the list of products listed for swapping by the SwapStorefront
     */
    public List<SwapProduct> getSwapProducts(){
        List<SwapProduct> sellProductsOut = new ArrayList<SwapProduct>();
        for(AbstractProduct product : this.swapProducts){
            sellProductsOut.add((SwapProduct) product);
        }
        return sellProductsOut;
    }

    @Override
    public List<AbstractProduct> getStorefrontProducts() {
        return this.swapProducts;
    }

    /**
     * Mutator method for the swapProducts property of the SwapStorefront
     * @param swapProductsIn the list of products for the SwapStorefront to swap
     */
    public void setSwapProducts(List<SwapProduct> swapProductsIn){
        this.swapProducts = new ArrayList<AbstractProduct>();
        for(SwapProduct product : swapProductsIn){
            this.swapProducts.add(product);
        }
    }

}
