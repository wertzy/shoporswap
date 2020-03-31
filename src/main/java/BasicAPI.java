import java.util.List;

public interface BasicAPI {

    User signIn(String accountName, String password);

    User signOut(String accountName, String password);

    Product createSellProduct(String name, String description, String price, User merchant);

    Product createSwapProduct(String name, String description, String price, User merchant);

    List<Product> viewUserProducts(User user);

    List<Product> viewSellProducts();

    List<Product> viewSwapProducts();

}
