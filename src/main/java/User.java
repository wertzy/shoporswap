public class User {
    String accountName;
    private String password;
    double rating;

    public User(String accountNameIn, String passwordIn){
        this.accountName = accountNameIn;
        this.password = passwordIn;
        this.rating = 0.0;
    }

    public String getAccountName(){
        return null;
    }

    public String getPassword(){
        return null;
    }

    public static boolean isAccountNameValid(String accountName) {
        return false;
    }
    public static boolean isPasswordValid(String password) {
        return false;
    }

    public static boolean isRatingValid(double rating) {
        return false;
    }
}
