import java.util.List;
import java.util.regex.Pattern;

public abstract class Storefront {

    private String storefrontName;
    private Client storefrontOwner;

    /**
     * Default constructor for Storefront object
     */
    public Storefront(){
        this.setStorefrontName("DEFAULT NAME");
        this.establishStorefrontOwner(null);
    }

    /**
     * Constructor for Storefront object
     * @param nameIn the name of the Storefront object
     * @param ownerIn the owner of the Storefront object
     * @throws IllegalArgumentException if the name of the Storefront is invalid
     * @throws IllegalArgumentException if the owner of the Storefront is invalid
     */
    public Storefront(String nameIn, Client ownerIn){
        this.setStorefrontName(nameIn);
        this.establishStorefrontOwner(ownerIn);
    }

    /**
     * Accessor method for the name of the Storefront
     * @return the name of the Storefront
     */
    public final String getStorefrontName(){
        return this.storefrontName;
    }

    /**
     * Accessor method for the owner of the Storefront (NOT a direct accessor)
     * @return the owner of the Storefront
     */
    public final Client retrieveStorefrontOwner(){
        return this.storefrontOwner;
    }

    public abstract List<AbstractProduct> getStorefrontProducts();

    /**
     * Mutator method for the name of the Storefront
     * @param nameIn the name to set
     * @throws IllegalArgumentException if the name is invalid
     */
    public final void setStorefrontName(String nameIn){
        if(isValidStorefrontName(nameIn)){
            this.storefrontName = nameIn;
        }else{
            throw new IllegalArgumentException("Storefront name is invalid");
        }
    }

    /**
     * Mutator method for the name of the Storefront (NOT a direct mutator)
     * @param ownerIn the owner to set
     */
    public final void establishStorefrontOwner(Client ownerIn){
        this.storefrontOwner = ownerIn;
    }

    /**
     * Determines whether a name is valid or not (names of Storefronts by owner must be unique)
     * @param nameIn the name to validate
     * @return true if valid, false otherwise
     */
    public static final boolean isValidStorefrontName(String nameIn){
        if(nameIn.isEmpty()){
            return false;
        }
        if(nameIn.indexOf(" ") == 0 || nameIn.lastIndexOf(" ") == nameIn.length() - 1){
            return false;
        }
        if(nameIn.indexOf("-") == 0 || nameIn.lastIndexOf("-") == nameIn.length() - 1){
            return false;
        }
        String exp = "[\\w[-\\s]]+\\z";
        return Pattern.matches(exp, nameIn);
    }

}
