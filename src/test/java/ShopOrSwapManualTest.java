import java.util.*;

public class ShopOrSwapManualTest {

    public static void main(String[] args) {
        OldShopOrSwap testOldShopOrSwap = new OldShopOrSwap();
        User testUser = testOldShopOrSwap.createAccount("test1", "test1");
        testOldShopOrSwap.createSellProduct("test1", "testsell", "20", testUser);
        testOldShopOrSwap.createSwapProduct("test2", "testswap", "20", testUser);
        loginMenu(testOldShopOrSwap);
    }

    private static void loginMenu(OldShopOrSwap oldShopOrSwap){
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
            loginMenu(oldShopOrSwap);
        }
        if(choice == 1){
            User currentUser = signInProcedure(oldShopOrSwap, input);
            if(currentUser != null){
                System.out.println("Welcome " + currentUser.getAccountName());
                userMenu(oldShopOrSwap, currentUser, input);
            }else{
                loginMenu(oldShopOrSwap);
            }
        }else if(choice == 2){
            User currentUser = createAccountProcedure(oldShopOrSwap, input);
            if(currentUser != null){
                System.out.println("Welcome " + currentUser.getAccountName());
                userMenu(oldShopOrSwap, currentUser, input);
            }else{
                loginMenu(oldShopOrSwap);
            }
        }else if(choice == 3){
            exitProcedure();
        }else if(choice != -1){
            System.out.println("No corresponding option");
        }
        loginMenu(oldShopOrSwap);
    }

    private static User signInProcedure(OldShopOrSwap oldShopOrSwap, Scanner reader) {
        System.out.println("--Sign In Procedure--");
        System.out.print("Account name (case-sensitive): ");
        String accountName = reader.next();
        System.out.print("Account password (case-sensitive): ");
        String accountPassword = reader.next();
        if(oldShopOrSwap.signIn(accountName, accountPassword) == null){
            System.out.println("No User found with those credentials");
            return null;
        }
        return oldShopOrSwap.signIn(accountName, accountPassword);
    }

    private static User createAccountProcedure(OldShopOrSwap oldShopOrSwap, Scanner reader){
        System.out.println("--Create Account Procedure--");
        System.out.print("Make an account name (alphanmueric): ");
        String accountName = reader.next();
        System.out.print("Make an account password (alphanumeric): ");
        String accountPassword = reader.next();
        try{
            return oldShopOrSwap.createAccount(accountName, accountPassword);
        }catch(Exception e){
            System.out.println("Cannot create account");
            return null;
        }
    }

    private static void userMenu(OldShopOrSwap oldShopOrSwap, User user, Scanner reader){
        System.out.println("--User Menu: " + user.getAccountName() + "--");
        System.out.println("Options:\n1. View My Products\n2. Post Sell Product\n3. View Selling Products\n4. Post Swap Product\n5. View Swapping Products\n6. Swap Products\n7. Sign Out");
        System.out.print("Choose the number of your selection: ");
        int userChoice;
        try {
            userChoice = reader.nextInt();
        }catch(InputMismatchException e){
            userChoice = -1;
            System.out.println("Invalid option selection");
            reader = new Scanner(System.in);
            userMenu(oldShopOrSwap, user, reader);
        }
        if(userChoice == 1){
            viewMyProductsProcedure(oldShopOrSwap, user);
        }else if(userChoice == 2){
            postSellProcedure(oldShopOrSwap, user);
        }else if(userChoice == 3){
            viewSellProcedure(oldShopOrSwap);
        }else if(userChoice == 4){
            postSwapProcedure(oldShopOrSwap, user);
        }else if(userChoice == 5) {
            viewSwapProcedure(oldShopOrSwap);
        }else if(userChoice == 6){
            swapProcedure(oldShopOrSwap, user);
        }else if(userChoice == 7){
            signOutProcedure(oldShopOrSwap, user);
        }else if(userChoice != -1){
            System.out.println("No corresponding option");
        }
        userMenu(oldShopOrSwap, user, reader);
    }

    private static void viewMyProductsProcedure(OldShopOrSwap oldShopOrSwap, User user){
        System.out.println("--View My Products Procedure--");
        List<Product> myProducts = oldShopOrSwap.findUserProducts(user);
        for(Product product : myProducts){
            System.out.println(product.getName() + "\n\t" + product.getPrice() + "\n\t" + product.getDescription() + "\n\t" + product.getTags());
        }
    }

    private static void postSellProcedure(OldShopOrSwap oldShopOrSwap, User user){
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
            oldShopOrSwap.createSellProduct(nameInput, descInput, "" + priceInput, user);
        }catch(Exception e){
            System.out.println("Cannot make product as specified");
            return;
        }
        viewMyProductsProcedure(oldShopOrSwap, user);
        return;
    }

    private static void viewSellProcedure(OldShopOrSwap oldShopOrSwap){
        System.out.println("--View Sell Products Procedure--");
        List<Product> myProducts = oldShopOrSwap.findSellProducts();
        for(Product product : myProducts){
            System.out.println(product.getName() + "\n\t" + product.getPrice() + "\n\t" + product.getDescription() + "\n\t" + product.getTags() + "\n\t" + product.getMerchant().getAccountName());
        }
    }

    private static void postSwapProcedure(OldShopOrSwap oldShopOrSwap, User user){
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
            oldShopOrSwap.createSwapProduct(nameInput, descInput, "" + priceInput, user);
        }catch(Exception e){
            System.out.println("Cannot make product as specified");
            return;
        }
        viewMyProductsProcedure(oldShopOrSwap, user);
        return;
    }

    private static void viewSwapProcedure(OldShopOrSwap oldShopOrSwap){
        System.out.println("--View Swap Products Procedure--");
        List<Product> myProducts = oldShopOrSwap.findSwapProducts();
        for(Product product : myProducts){
            System.out.println(product.getName() + "\n\t" + product.getDescription() + "\n\t" + product.getTags() + "\n\t" + product.getMerchant().getAccountName());
        }
    }

    private static void swapProcedure(OldShopOrSwap oldShopOrSwap, User user){
        System.out.println("--Swap Products Procedure--");
        if(oldShopOrSwap.findSwapProducts().size() < 2){
            System.out.println("Cannot complete swap procedure (insufficient swap inventory)");
            return;
        }
        if(oldShopOrSwap.findUserProducts(user).size() == 0){
            System.out.println("Cannot complete swap procedure (you have no swap inventory)");
            return;
        }
    }

    private static void signOutProcedure(OldShopOrSwap oldShopOrSwap, User user){
        System.out.println("--Sign Out Procedure--");
        System.out.println("" + user.getAccountName() + " is signing out");
        loginMenu(oldShopOrSwap);
    }

    private static void exitProcedure(){
        System.out.println("Goodbye");
        System.exit(0);
    }

}
