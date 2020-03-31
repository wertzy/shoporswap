import java.util.List;

public interface BasicAPI {

    User login(String accountName, String password);

    Product makeProduct();

    List<Product> viewMyProducts();

    List<Product> viewAllSellableProducts();

    List<Product> viewAllSwappableProducts();

}
