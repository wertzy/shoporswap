import java.util.*;

public class Shopper extends User {

    public Shopper(){
        super();
    }

    public Shopper(String accountName, String password){
        super(accountName, password);
    }

    /**
     * Views the collection of clothing available to see (varies by seller, shopper, swapper)
     * @return the viewable clothing (as a list) of clothing
     */
    @Override
    public Collection<Clothing> viewClothing(){
        return null;
    }

}
