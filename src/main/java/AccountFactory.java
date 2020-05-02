public class AccountFactory {

    /**
     * Default constructor for AccountFactory class
     */
    public AccountFactory(){

    }

    /**
     * Creates an Account based on input about what type
     * @param typeIn the type of Account
     * @return the Account (Client or Admin)
     * @throws IllegalArgumentException if typeIn is invalid (not "client" or "admin")
     */
    public Account getAccount(String typeIn){
        if(typeIn.compareToIgnoreCase("admin") == 0){
            return new Admin();
        }
        if(typeIn.compareToIgnoreCase("client") == 0){
            return new Client();
        }
        throw new IllegalArgumentException("Invalid Account type");
    }

}
