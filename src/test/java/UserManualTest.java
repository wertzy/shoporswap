import java.util.Scanner;

public class UserManualTest {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter a valid account name: ");
        String accountName = input.nextLine();

        while(!User.isAccountNameValid(accountName)){
            System.out.println("Enter a valid account name: ");
            accountName = input.nextLine();
        }

        System.out.println("Enter a valid password: ");
        String password = input.nextLine();

        while(!User.isPasswordValid(password)){
            System.out.println("Enter a valid password: ");
            password = input.nextLine();
        }

        System.out.println("Attempting to create User with given information");
        User user1 = new User(accountName, password);
        System.out.println("\tInput account name: " + accountName);
        System.out.println("\tUser account name: " + user1.getAccountName());
        System.out.println("\tInput password: " + password);
        System.out.println("\tUser password: " + user1.getPassword());

        System.out.println("Attempting to create Seller with given information");
        User user2 = new Seller(accountName, password);
        System.out.println("\tInput account name: " + accountName);
        System.out.println("\tUser account name: " + user2.getAccountName());
        System.out.println("\tInput password: " + password);
        System.out.println("\tUser password: " + user2.getPassword());

        System.out.println("Attempting to create Shopper with given information");
        User user3 = new Shopper(accountName, password);
        System.out.println("\tInput account name: " + accountName);
        System.out.println("\tUser account name: " + user3.getAccountName());
        System.out.println("\tInput password: " + password);
        System.out.println("\tUser password: " + user3.getPassword());

        System.out.println("Attempting to create Swapper with given information");
        User user4 = new Swapper(accountName, password);
        System.out.println("\tInput account name: " + accountName);
        System.out.println("\tUser account name: " + user4.getAccountName());
        System.out.println("\tInput password: " + password);
        System.out.println("\tUser password: " + user4.getPassword());

        System.out.println("Attempting to create a rating");
        String rating = "-100";
        boolean badStatus = true;
        while(badStatus) {
            System.out.println("Enter a valid rating: ");
            rating = input.nextLine();
            try{
                badStatus = !User.isRatingValid(Double.parseDouble(rating));
            }catch(Exception e){
                continue;
            }
        }
        System.out.println("\tRating: " + rating);

    }

}
