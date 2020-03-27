public class User {
    protected String accountName;
    protected String password;
    protected double rating;

    public User(){
        this.accountName = "accountname";
        this.password = "password";
        this.rating = 0.0;
    }

    public User(String accountNameIn, String passwordIn){
        if(!isAccountNameValid(accountNameIn)){
            throw new IllegalArgumentException("invalid account name");
        }else {
            this.accountName = accountNameIn;
        }

        if(!isPasswordValid(passwordIn)){
            throw new IllegalArgumentException("invalid password");
        }else {
            this.password = passwordIn;
        }
        this.rating = 0.0;
    }

    public String getAccountName(){
        return this.accountName;
    }
    public String getPassword(){
        return this.password;
    }
    public double getRating(){return this.rating;}

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
