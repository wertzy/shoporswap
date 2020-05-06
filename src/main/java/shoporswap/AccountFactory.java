package shoporswap;

public class AccountFactory {

    /**
     * Default constructor for shoporswap.AccountFactory class
     */
    public AccountFactory(){

    }

    /**
     * Creates an shoporswap.Account based on input about what type
     * @param typeIn the type of shoporswap.Account
     * @return the shoporswap.Account (shoporswap.Client or shoporswap.Admin)
     * @throws IllegalArgumentException if typeIn is invalid (not "client" or "admin")
     */
    public Account getAccount(String typeIn){
        if(typeIn.compareToIgnoreCase("admin") == 0){
            return new Admin();
        }
        if(typeIn.compareToIgnoreCase("client") == 0){
            return new Client();
        }
        throw new IllegalArgumentException("Invalid shoporswap.Account type");
    }

}
