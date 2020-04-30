import java.util.ArrayList;
import java.util.List;

public abstract class Storefront {

    private String storefrontName;
    private User storefrontOwner;

    /**
     * Default constructor for Storefront object
     */
    public Storefront(){

    }

    /**
     * Constructor for Storefront object
     * @param nameIn the name of the Storefront object
     * @param ownerIn the owner of the Storefront object
     * @throws IllegalArgumentException if the name of the Storefront is invalid
     * @throws IllegalArgumentException if the owner of the Storefront is invalid
     */
    public Storefront(String nameIn, User ownerIn){

    }

    /**
     * Accessor method for the name of the Storefront
     * @return the name of the Storefront
     */
    public final String getStorefrontName(){
        return "";
    }

    /**
     * Accessor method for the owner of the Storefront
     * @return the owner of the Storefront
     */
    public final User getStorefrontOwner(){
        return null;
    }

    public abstract List<AbstractProduct> getStorefrontProducts();

    /**
     * Mutator method for the name of the Storefront
     * @param nameIn the name to set
     * @throws IllegalArgumentException if the name is invalid
     */
    public final void setStorefrontName(String nameIn){
    }

    /**
     * Mutator method for the name of the Storefront
     * @param ownerIn the owner to set
     */
    public final void setStorefrontOwner(User ownerIn){
    }

    /**
     * Determines whether a name is valid or not (names of Storefronts by owner must be unique)
     * @param nameIn the name to validate
     * @return true if valid, false otherwise
     */
    public static final boolean isValidStorefrontName(String nameIn){
        return false;
    }

}
