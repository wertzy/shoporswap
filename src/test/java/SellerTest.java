import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    @Test
    void constructorTest(){
        Seller seller1 = new Seller();
        assertEquals("accountname", seller1.getAccountName());
        assertEquals("password", seller1.getPassword());
        assertEquals(0.0, seller1.getRating());

        Seller seller2 = new Seller("desmond", "desmond");
        assertEquals("desmond", seller2.getAccountName());
        assertEquals("desmond", seller2.getPassword());
        assertEquals(0.0, seller2.getRating());

        User seller3 = new Seller();
        assertEquals("accountname", seller3.getAccountName());
        assertEquals("password", seller3.getPassword());
        assertEquals(0.0, seller3.getRating());

        User seller4 = new Seller("desmond",  "desmond");
        assertEquals("desmond", seller4.getAccountName());
        assertEquals("desmond", seller4.getPassword());
        assertEquals(0.0, seller4.getRating());

        assertThrows(IllegalArgumentException.class, ()-> new Seller("des mond", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new Seller("", "desmond"));
        assertThrows(IllegalArgumentException.class, ()-> new Seller("desmond", ""));
        assertThrows(IllegalArgumentException.class, ()-> new Seller("desmond", "des mond"));

    }

    @Test
    void sellTest(){
        Seller seller1 = new Seller("desmond","123456");
        Product product1= new Product("Supreme Box Logo Hoodie","box logo hoodie in gildan ", seller1);
        //seller1.sell("Supreme Box Logo Hoodie");
        //assertEquals()

    }
    @Test
    void addListing(){
        Seller seller1 = new Seller("desmond","123456");
        Product product1= new Product("Supreme Box Logo Hoodie","box logo hoodie in gildan", seller1);
        Product product2= new Product("ASS Club","t-shirt", seller1);
        Product product3= new Product("ithaca college tee","ithaca college t shirt in blue", seller1);
        //seller1.addListing(product1);
        //seller1.addListing(product2);
        //seller1.addListing(product3);
        System.out.println(seller1.productList);
    }

}
