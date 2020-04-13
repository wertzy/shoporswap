import javax.naming.CompoundName;
import java.util.*;

public class Seller extends User {
    public Seller(){
        super();
    }

    public Seller(String accountName, String password){
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

    public static void addListing(Product product){
        productList.add(product);
    }

    public static void sell(Product product) {
        //Product sellerProduct
    }

}
