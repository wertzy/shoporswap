import java.util.List;

public interface BasicAPI {

    User signIn(String accountName, String password);

    User signOut(String accountName, String password);

    User createAccount(String accountName, String password);

    User addAccount(User user);

    User removeAccount(User user);

    User findAccount(User user);

    Product createSellProduct(String name, String description, String price, User merchant);

    Product createSwapProduct(String name, String description, String price, User merchant);

    Product findProduct(String name, User merchant);

    List<Product> findUserProducts(User user);

    List<Product> findSellProducts();

    List<Product> findSwapProducts();

    List<User> getUserList();

    List<Product> getProductList();

    void swapProducts(Product product1, Product product2);

}
