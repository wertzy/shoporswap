import java.util.*;

public class ShopOrSwapManualTest {

    public static void main(String[] args) {
        ShopOrSwap testShopOrSwap = new ShopOrSwap();
        User testUser = testShopOrSwap.createAccount("test1", "test1");
        testShopOrSwap.createSellProduct("test1", "testsell", "20", testUser);
        testShopOrSwap.createSwapProduct("test2", "testswap", "20", testUser);
        loginMenu(testShopOrSwap);
    }

    private static void loginMenu(ShopOrSwap shopOrSwap){
        Scanner input = new Scanner(System.in);
        System.out.println("--Login Menu--");
        System.out.println("Options:\n1. Sign In\n2. Create Account\n3. Quit");
        System.out.print("Choose the number of your selection: ");
        int choice;
        try {
            choice = input.nextInt();
        }catch(InputMismatchException e){
            choice = -1;
            System.out.println("Invalid option selection");
            input = new Scanner(System.in);
            loginMenu(shopOrSwap);
        }
        if(choice == 1){
            User currentUser = signInProcedure(shopOrSwap, input);
            if(currentUser != null){
                System.out.println("Welcome " + currentUser.getAccountName());
                userMenu(shopOrSwap, currentUser, input);
            }else{
                loginMenu(shopOrSwap);
            }
        }else if(choice == 2){
            User currentUser = createAccountProcedure(shopOrSwap, input);
            if(currentUser != null){
                System.out.println("Welcome " + currentUser.getAccountName());
                userMenu(shopOrSwap, currentUser, input);
            }else{
                loginMenu(shopOrSwap);
            }
        }else if(choice == 3){
            exitProcedure();
        }else if(choice != -1){
            System.out.println("No corresponding option");
        }
        loginMenu(shopOrSwap);
    }

    private static User signInProcedure(ShopOrSwap shopOrSwap, Scanner reader) {
        System.out.println("--Sign In Procedure--");
        System.out.print("Account name (case-sensitive): ");
        String accountName = reader.next();
        System.out.print("Account password (case-sensitive): ");
        String accountPassword = reader.next();
        if(shopOrSwap.signIn(accountName, accountPassword) == null){
            System.out.println("No User found with those credentials");
            return null;
        }
        return shopOrSwap.signIn(accountName, accountPassword);
    }

    private static User createAccountProcedure(ShopOrSwap shopOrSwap, Scanner reader){
        System.out.println("--Create Account Procedure--");
        System.out.print("Make an account name (alphanmueric): ");
        String accountName = reader.next();
        System.out.print("Make an account password (alphanumeric): ");
        String accountPassword = reader.next();
        try{
            return shopOrSwap.createAccount(accountName, accountPassword);
        }catch(Exception e){
            System.out.println("Cannot create account");
            return null;
        }
    }

    private static void userMenu(ShopOrSwap shopOrSwap, User user, Scanner reader){
        System.out.println("--User Menu: " + user.getAccountName() + "--");
        System.out.println("Options:\n1. View My Products\n2. Post Sell Product\n3. View Selling Products\n4. Post Swap Product\n5. View Swapping Products\n6. Sign Out");
        System.out.print("Choose the number of your selection: ");
        int userChoice;
        try {
            userChoice = reader.nextInt();
        }catch(InputMismatchException e){
            userChoice = -1;
            System.out.println("Invalid option selection");
            reader = new Scanner(System.in);
            userMenu(shopOrSwap, user, reader);
        }
        if(userChoice == 1){
            viewMyProductsProcedure(shopOrSwap, user);
        }else if(userChoice == 2){
            postSellProcedure(shopOrSwap, user);
        }else if(userChoice == 3){
            viewSellProcedure(shopOrSwap);
        }else if(userChoice == 4){
            postSwapProcedure(shopOrSwap, user);
        }else if(userChoice == 5){
            viewSwapProcedure(shopOrSwap);
        }else if(userChoice == 6){
            signOutProcedure(shopOrSwap, user);
        }else if(userChoice != -1){
            System.out.println("No corresponding option");
        }
        userMenu(shopOrSwap, user, reader);
    }

    private static void viewMyProductsProcedure(ShopOrSwap shopOrSwap, User user){
        System.out.println("--View My Products Procedure--");
        List<Product> myProducts = shopOrSwap.findUserProducts(user);
        for(Product product : myProducts){
            System.out.println(product.getName() + "\n\t" + product.getPrice() + "\n\t" + product.getDescription() + "\n\t" + product.getTags());
        }
    }

    private static void postSellProcedure(ShopOrSwap shopOrSwap, User user){
        System.out.println("--Post Product Sell Procedure--");
        String nameInput, descInput;
        double priceInput;
        Scanner reader = new Scanner(System.in);
        try{
            System.out.print("Product name (1-50 alphanumeric or space characters): ");
            nameInput = reader.nextLine();
            System.out.println("Input: " + nameInput);
            System.out.print("Product description (1-500 alphanumeric or space characters): ");
            descInput = reader.nextLine();
            System.out.println("Input: " + descInput);
            System.out.print("Product price (numeric, at most 2 decimal places): ");
            priceInput = reader.nextDouble();
            System.out.println("Input: " + priceInput);
        }catch(Exception e){
            System.out.println("Cannot make product as specified");
            return;
        }
        try{
            shopOrSwap.createSellProduct(nameInput, descInput, "" + priceInput, user);
        }catch(Exception e){
            System.out.println("Cannot make product as specified");
            return;
        }
        viewMyProductsProcedure(shopOrSwap, user);
        return;
    }

    private static void viewSellProcedure(ShopOrSwap shopOrSwap){
        System.out.println("--View Sell Products Procedure--");
        List<Product> myProducts = shopOrSwap.findSellProducts();
        for(Product product : myProducts){
            System.out.println(product.getName() + "\n\t" + product.getPrice() + "\n\t" + product.getDescription() + "\n\t" + product.getTags() + "\n\t" + product.getMerchant().getAccountName());
        }
    }

    private static void postSwapProcedure(ShopOrSwap shopOrSwap, User user){
        System.out.println("--Post Product Swap Procedure--");
        String nameInput, descInput;
        double priceInput;
        Scanner reader = new Scanner(System.in);
        try{
            System.out.print("Product name (1-50 alphanumeric or space characters): ");
            nameInput = reader.nextLine();
            System.out.println("Input: " + nameInput);
            System.out.print("Product description (1-500 alphanumeric or space characters): ");
            descInput = reader.nextLine();
            System.out.println("Input: " + descInput);
            System.out.print("Product price (numeric, at most 2 decimal places): ");
            priceInput = reader.nextDouble();
            System.out.println("Input: " + priceInput);
        }catch(Exception e){
            System.out.println("Cannot make product as specified");
            return;
        }
        try{
            shopOrSwap.createSwapProduct(nameInput, descInput, "" + priceInput, user);
        }catch(Exception e){
            System.out.println("Cannot make product as specified");
            return;
        }
        viewMyProductsProcedure(shopOrSwap, user);
        return;
    }

    private static void viewSwapProcedure(ShopOrSwap shopOrSwap){
        System.out.println("--View Swap Products Procedure--");
        List<Product> myProducts = shopOrSwap.findSwapProducts();
        for(Product product : myProducts){
            System.out.println(product.getName() + "\n\t" + product.getDescription() + "\n\t" + product.getTags() + "\n\t" + product.getMerchant().getAccountName());
        }
    }

    private static void signOutProcedure(ShopOrSwap shopOrSwap, User user){
        System.out.println("--Sign Out Procedure--");
        System.out.println("" + user.getAccountName() + " is signing out");
        loginMenu(shopOrSwap);
    }

    private static void exitProcedure(){
        System.out.println("Goodbye");
        System.exit(0);
    }

}
