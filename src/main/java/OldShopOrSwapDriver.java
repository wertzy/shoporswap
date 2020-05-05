import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OldShopOrSwapDriver {

    private static final String DATA_FILE = "src/main/resources/systemData.json";

    private static OldShopOrSwap generateData() throws IOException {
        try {
            String dataFile = DATA_FILE;
            OldShopOrSwap dataOldShopOrSwap = new OldShopOrSwap();
            dataOldShopOrSwap.createAccount("user1", "pass1");
            dataOldShopOrSwap.createSellProduct("product 1", "description for product 1", "10", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSellProduct("product 2", "description for product 2", "20", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSellProduct("product 3", "description for product 3", "30", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSellProduct("product 4", "description for product 4", "40", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSellProduct("product 5", "description for product 5", "50", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSellProduct("product 6", "description for product 6", "10", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSellProduct("product 7", "description for product 7", "20", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSellProduct("product 8", "description for product 8", "30", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSellProduct("product 9", "description for product 9", "40", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSellProduct("product 10", "description for product 10", "50", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 51", "description for product 51", "10", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 52", "description for product 52", "20", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 53", "description for product 53", "30", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 54", "description for product 54", "40", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 55", "description for product 55", "50", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 56", "description for product 56", "10", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 57", "description for product 57", "20", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 58", "description for product 58", "30", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 59", "description for product 59", "40", dataOldShopOrSwap.findAccount("user1"));
            dataOldShopOrSwap.createSwapProduct("product 60", "description for product 60", "50", dataOldShopOrSwap.findAccount("user1"));

            dataOldShopOrSwap.createAccount("user2", "pass2");
            dataOldShopOrSwap.createSellProduct("product 11", "description for product 11", "10", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSellProduct("product 12", "description for product 12", "20", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSellProduct("product 13", "description for product 13", "30", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSellProduct("product 14", "description for product 14", "40", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSellProduct("product 15", "description for product 15", "50", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSellProduct("product 16", "description for product 16", "10", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSellProduct("product 17", "description for product 17", "20", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSellProduct("product 18", "description for product 18", "30", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSellProduct("product 19", "description for product 19", "40", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSellProduct("product 20", "description for product 20", "50", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 61", "description for product 61", "10", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 62", "description for product 62", "20", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 63", "description for product 63", "30", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 64", "description for product 64", "40", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 65", "description for product 65", "50", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 66", "description for product 66", "10", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 67", "description for product 67", "20", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 68", "description for product 68", "30", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 69", "description for product 69", "40", dataOldShopOrSwap.findAccount("user2"));
            dataOldShopOrSwap.createSwapProduct("product 70", "description for product 70", "50", dataOldShopOrSwap.findAccount("user2"));

            dataOldShopOrSwap.createAccount("user3", "pass3");
            dataOldShopOrSwap.createSellProduct("product 21", "description for product 21", "10", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSellProduct("product 22", "description for product 22", "20", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSellProduct("product 23", "description for product 23", "30", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSellProduct("product 24", "description for product 24", "40", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSellProduct("product 25", "description for product 25", "50", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSellProduct("product 26", "description for product 26", "10", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSellProduct("product 27", "description for product 27", "20", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSellProduct("product 28", "description for product 28", "30", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSellProduct("product 29", "description for product 29", "40", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSellProduct("product 30", "description for product 30", "50", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 71", "description for product 71", "10", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 72", "description for product 72", "20", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 73", "description for product 73", "30", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 74", "description for product 74", "40", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 75", "description for product 75", "50", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 76", "description for product 76", "10", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 77", "description for product 77", "20", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 78", "description for product 78", "30", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 79", "description for product 79", "40", dataOldShopOrSwap.findAccount("user3"));
            dataOldShopOrSwap.createSwapProduct("product 80", "description for product 80", "50", dataOldShopOrSwap.findAccount("user3"));

            dataOldShopOrSwap.createAccount("user4", "pass4");
            dataOldShopOrSwap.createSellProduct("product 31", "description for product 31", "10", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSellProduct("product 32", "description for product 32", "20", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSellProduct("product 33", "description for product 33", "30", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSellProduct("product 34", "description for product 34", "40", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSellProduct("product 35", "description for product 35", "50", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSellProduct("product 36", "description for product 36", "10", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSellProduct("product 37", "description for product 37", "20", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSellProduct("product 38", "description for product 38", "30", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSellProduct("product 39", "description for product 39", "40", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSellProduct("product 40", "description for product 40", "50", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 81", "description for product 81", "10", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 82", "description for product 82", "20", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 83", "description for product 83", "30", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 84", "description for product 84", "40", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 85", "description for product 85", "50", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 86", "description for product 86", "10", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 87", "description for product 87", "20", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 88", "description for product 88", "30", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 89", "description for product 89", "40", dataOldShopOrSwap.findAccount("user4"));
            dataOldShopOrSwap.createSwapProduct("product 90", "description for product 90", "50", dataOldShopOrSwap.findAccount("user4"));

            dataOldShopOrSwap.createAccount("user5", "pass5");
            dataOldShopOrSwap.createSellProduct("product 41", "description for product 41", "10", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSellProduct("product 42", "description for product 42", "20", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSellProduct("product 43", "description for product 43", "30", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSellProduct("product 44", "description for product 44", "40", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSellProduct("product 45", "description for product 45", "50", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSellProduct("product 46", "description for product 46", "10", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSellProduct("product 47", "description for product 47", "20", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSellProduct("product 48", "description for product 48", "30", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSellProduct("product 49", "description for product 49", "40", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSellProduct("product 50", "description for product 50", "50", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 91", "description for product 91", "10", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 92", "description for product 92", "20", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 93", "description for product 93", "30", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 94", "description for product 94", "40", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 95", "description for product 95", "50", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 96", "description for product 96", "10", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 97", "description for product 97", "20", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 98", "description for product 98", "30", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 99", "description for product 99", "40", dataOldShopOrSwap.findAccount("user5"));
            dataOldShopOrSwap.createSwapProduct("product 100", "description for product 100", "50", dataOldShopOrSwap.findAccount("user5"));

            dataOldShopOrSwap.exit(dataFile);

            OldShopOrSwap importedOldShopOrSwap = new OldShopOrSwap(dataFile);
            System.out.println("Imported ShopOrSwap data with User count " + importedOldShopOrSwap.getUserList().size() + " and Product count " + importedOldShopOrSwap.getProductList().size());
            return importedOldShopOrSwap;
        }catch(Exception e){
            System.out.println("Cannot import data");
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        OldShopOrSwap system;
        try {
            try {
                system = new OldShopOrSwap(DATA_FILE);
            } catch (FileNotFoundException e) {
                system = generateData();
            }
            loginMenu(system);
        }catch (Exception e){
            System.out.println("Could not start system");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void loginMenu(OldShopOrSwap oldShopOrSwap) throws IOException {
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
            exitProcedure(oldShopOrSwap, DATA_FILE);
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

    private static void userMenu(OldShopOrSwap oldShopOrSwap, User user, Scanner reader) throws IOException {
        System.out.println("--User Menu: " + user.getAccountName() + "--");
        System.out.println("Options:\n1. View My Products\n2. Post Sell Product\n3. View Selling Products\n4. Post Swap Product\n" +
                "5. View Swapping Products\n6. Swap Products\n7. Buy Another User's Product\n8. View My Account\n" +
                "9. Sign Out");
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
        }else if(userChoice == 7) {
            buyProcedure(oldShopOrSwap, user);
        }else if(userChoice == 8) {
            viewAccountProcedure(oldShopOrSwap, user);
        }else if(userChoice == 9){
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
        Scanner reader = new Scanner(System.in);
        String nameInput1, nameInput2, searchFeedback;
        Product product1, product2;
        List<Product> results1, results2;

        if(oldShopOrSwap.findSwapProducts().size() < 2){
            System.out.println("Cannot complete swap procedure (insufficient swap inventory)");
            return;
        }
        if(oldShopOrSwap.findUserProducts(user).size() == 0){
            System.out.println("Cannot complete swap procedure (you have no swap inventory)");
            return;
        }

        System.out.print("Which product do you offer for swap: ");
        nameInput1 = reader.nextLine();
        System.out.println("Input: " + nameInput1);

        product1 = null;
        try {
            results1 = oldShopOrSwap.searchForProduct(nameInput1);
        }catch (Exception e){
            System.out.println("Cannot complete swap procedure (product does not exist in inventory)");
            return;
        }
        if(results1.size() > 1){
            for(Product p1 : results1){
                System.out.println(p1.getName() + "\n\t" + p1.getDescription() + "\n\t" + p1.getMerchant().getAccountName() + "\n\t" + p1.getTags());
                System.out.print("Is this the product you want to offer? (Enter \"Y\" for \"Yes\") ");
                searchFeedback = reader.next();
                if(searchFeedback.compareToIgnoreCase("Y") == 0 && p1.getMerchant().getAccountName().compareTo(user.getAccountName()) == 0){
                    product1 = p1;
                    break;
                }else if(searchFeedback.compareToIgnoreCase("Y") == 0){
                    System.out.println("Cannot offer this product (you do not own this product)");
                }
            }
            if(product1 == null) {
                System.out.println("Cannot complete swap procedure (you did not choose a product to offer)");
                return;
            }
        }else if(results1.size() == 1){
            if (results1.get(0).getMerchant().getAccountName().compareTo(user.getAccountName()) == 0){
                product1 = results1.get(0);
            }else{
                System.out.println("Cannot offer this product (you do not own this product)");
                return;
            }
        }

        reader = new Scanner(System.in);
        System.out.print("Which product do you want for swap: ");
        nameInput2 = reader.nextLine();
        System.out.println("Input: " + nameInput2);

        product2 = null;
        try {
            results2 = oldShopOrSwap.searchForProduct(nameInput2);
        }catch(Exception e){
            System.out.println("Cannot complete swap procedure (product does not exist in inventory)");
            return;
        }
        if(results2.size() > 1){
            for(Product p2 : results2){
                System.out.println(p2.getName() + "\n\t" + p2.getDescription() + "\n\t" + p2.getMerchant().getAccountName() + "\n\t" + p2.getTags());
                System.out.print("Is this the product you want? (Enter \"Y\" for \"Yes\") ");
                searchFeedback = reader.next();
                if(searchFeedback.compareToIgnoreCase("Y") == 0 && p2.getMerchant().getAccountName().compareTo(user.getAccountName()) != 0){
                    product2 = p2;
                    break;
                }else if(searchFeedback.compareToIgnoreCase("Y") == 0){
                    System.out.println("Cannot want this product (you own this product)");
                }
            }
            if(product2 == null) {
                System.out.println("Cannot complete swap procedure (you did not choose a product to want)");
                return;
            }
        }else if(results2.size() == 1){
            if (results2.get(0).getMerchant().getAccountName().compareTo(user.getAccountName()) != 0){
                product2 = results2.get(0);
            }else{
                System.out.println("Cannot want this product (you own this product)");
                return;
            }
        }

        try{
            oldShopOrSwap.swapProducts(product1, product2);
            System.out.println("Swap successful");
            return;
        }catch (Exception e){
            System.out.println("Swap fail (error in system)");
            System.out.println(e.getMessage());
            return;
        }

    }

    private static void buyProcedure(OldShopOrSwap oldShopOrSwap, User user){
        System.out.println("--Buy Products Procedure--");
        Scanner reader = new Scanner(System.in);
        String nameInput1,nameInput2,  searchFeedback;
        Product results1;
        User merchant;

        System.out.print("What is the name of the user you would like to buy from ");
        nameInput1 = reader.nextLine();
        System.out.println("Input: " + nameInput1);
        try {
            merchant = oldShopOrSwap.findAccount(nameInput1);
        }
        catch(Exception e){
            System.out.println("User not found");
            return;
        }

        if(user.getAccountName().compareToIgnoreCase(merchant.getAccountName()) == 0){
            System.out.println("User is yourself. You cannot buy from yourself.");
            return;
        }

        System.out.print("What is the name of the item you would like to buy ");
        nameInput2 = reader.nextLine();
        System.out.println("Input: " + nameInput2);
        try {
            results1 = oldShopOrSwap.findProduct(nameInput2, merchant);
        }
        catch (Exception e){
            System.out.println("Product not found");
            return;
        }
        System.out.println(results1.getName() + "\n\t" + results1.getDescription() + "\n\t" + results1.getMerchant().getAccountName() + "\n\t" + results1.getTags());
        System.out.print("Is this the product you want to buy? (Enter \"Y\" for \"Yes\") ");
        searchFeedback = reader.next();
        if(searchFeedback.compareToIgnoreCase("Y") == 0){
            user.buy(results1.getName(), merchant);
            user.sendMessage("Product Sold", "Your product has been sold: " + results1.getName(), merchant);
            merchant.sendMessage("Product Bought", "Your transaction for " + results1.getName() + " has successfully been processed", user);
            oldShopOrSwap.removeSellProduct(results1);
        }
    }

    private static void viewAccountProcedure(OldShopOrSwap oldShopOrSwap, User user){
        System.out.println("Name: " + user.getAccountName());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Messages:");
        if(!user.retrieveMessages().isEmpty()){
            for(Message msg : user.retrieveMessages()){
                System.out.println("" + (user.retrieveMessages().indexOf(msg) + 1) + ": Message from " + msg.getSender().getAccountName());
                System.out.println("" + msg.getBody());
            }
        }
        System.out.println("Purchase History:");
        if(!user.getTransactionHistory().isEmpty()){
            for(String tsaction : user.getTransactionHistory()){
                System.out.println("" + tsaction);
            }
        }
    }

    private static void signOutProcedure(OldShopOrSwap oldShopOrSwap, User user) throws IOException {
        System.out.println("--Sign Out Procedure--");
        System.out.println("" + user.getAccountName() + " is signing out");
        loginMenu(oldShopOrSwap);
    }

//    private static void reportProcedure(ShopOrSwap shopOrSwap, User user){
//        Scanner input = new Scanner(System.in);
//        System.out.print("Who do you wish to report? ");
//        String username = input.nextLine();
//
//        if(shopOrSwap.findAccount(username).getAccountName().compareToIgnoreCase(user.getAccountName()) == 0){
//            System.out.println("Cannot report yourself");
//            return;
//        }
//
//        System.out.println("Type the complaint: ");
//        String comment = input.nextLine();
//        try {
//            shopOrSwap.genReport(shopOrSwap.findAccount(username), comment);
//        }catch(Exception e){
//            System.out.println("Could not report the specified user");
//        }
//        return;
//    }

    private static void exitProcedure(OldShopOrSwap oldShopOrSwap, String dataFile) throws IOException {
        System.out.println("Exporting data");
        try {
            oldShopOrSwap.exit(dataFile);
            System.out.println("Export Success. Goodbye");
            System.exit(0);
        }catch (Exception e){
            System.out.println("Export Fail. Goodbye");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

}
