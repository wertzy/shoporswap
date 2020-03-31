import java.util.*;

public class Swapper extends User {

    public Swapper(){
        super();
    }

    public Swapper(String accountName, String password){
        super(accountName, password);
    }

    /**
     * Views the collection of clothing available to see (varies by seller, shopper, swapper)
     * @return the viewable clothing (as a list) of clothing
     */
    @Override
    public Collection<Product> viewClothing(){
        return null;
    }

}
