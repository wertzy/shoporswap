package shoporswap;

import io.*;
import util.JsonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    
    private static Scanner SCRIPT_INPUT;

    private static void makeScriptReader() {
        try {
            SCRIPT_INPUT = new Scanner(new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "script1.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error: test script not found");
        }
    }

    public static void main(String[] args) throws IOException {
        makeScriptReader();
        ShopOrSwap shopOrSwap = getShopOrSwapData();
        try {
            preliminaryMenu(shopOrSwap);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // JSON data import
    private static ShopOrSwap getShopOrSwapData(){
        String dataFileName = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "systemData.json";
        ShopOrSwap shopOrSwapOut;
        try{
            ShopOrSwapRecord shopOrSwapRecord = JsonUtil.fromJsonFile(dataFileName, ShopOrSwapRecord.class);
            shopOrSwapOut = shopOrSwapRecord.toShopOrSwap();
            System.out.println("Data import success");
        } catch (IOException e) {
            System.out.println("Data import failure (either error in program or the first launch of the program)");
            shopOrSwapOut = new ShopOrSwap();
            shopOrSwapOut.addAccount("Admin", "admin1", "admin1");
            shopOrSwapOut.addAccount("Client", "client1", "client1");
        }
        return shopOrSwapOut;
    }

    // First menu user interacts with
    private static void preliminaryMenu(ShopOrSwap shopOrSwap) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Menu--");
        System.out.println("\t1. Sign In");
        System.out.println("\t2. Create Account");
        System.out.println("\t-1. Exit");
        System.out.print("Selection #: ");
        String choice = input.nextLine();
        System.out.print("\n-> Selection #: " + choice);
        switch(choice){
            case "1":
                signInProcedure(shopOrSwap);
                break;
            case "2":
                createAccountProcedure(shopOrSwap);
                break;
            case "-1":
                exitProcedure(shopOrSwap);
                break;
            default:
                System.out.println("\nInvalid menu selection");
                break;
        }
        preliminaryMenu(shopOrSwap);
    }

    // sign in procedure for the user
    private static void signInProcedure(ShopOrSwap shopOrSwap){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Sign In Procedure--");
        System.out.print("\nAccount Name: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Account Name: " + nameIn);
        System.out.print("\nAccount Password: ");
        String passwordIn = input.nextLine();
        System.out.print("\n-> Account Password: " + passwordIn);
        try{
            Account account = shopOrSwap.findAccount(nameIn);
            if(account.getAccountPassword().compareTo(passwordIn) != 0){
                throw new NoSuchElementException("Cannot find account with specified credentials");
            }
            if(account.getIsFrozen()){
                System.out.print("\nAccount is frozen. You cannot access the system. Please contact an administrator.");
                return;
            }
            System.out.print("\nWelcome back " + account.getAccountName());
            if((new AccountRecord(account)).accessAccountType().contains("Client")) {
                clientMenu(shopOrSwap, account);
            }else{
                adminMenu(shopOrSwap, account);
            }
        }catch(NoSuchElementException e){
            System.out.print("\nError: " + e.getMessage());
        }
        return;
    }

    // create account procedure for the user
    private static void createAccountProcedure(ShopOrSwap shopOrSwap){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Create Account Procedure--");
        System.out.print("Desired Account Name: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Desired Account Name: " + nameIn);
        System.out.print("\nDesired Account Password: ");
        String passwordIn = input.nextLine();
        System.out.print("\n-> Desired Account Password: " + passwordIn);
        try{
            Account account = shopOrSwap.addAccount("Client", nameIn, passwordIn);
            System.out.print("\nWelcome " + account.getAccountName() + ". Please sign in using your new credentials to access your new account.");
        }catch (IllegalArgumentException e){
            System.out.print("\nError: " + e.getMessage());
        }
        return;
    }

    // account menu for a successfully signed-in client
    private static void clientMenu(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Account Menu--");
        System.out.println("\t1. View My Account Information");
        System.out.println("\t2. View My Messages");
        System.out.println("\t3. Go to A Storefront");
        System.out.println("\t4. Make A Storefront");
        System.out.println("\t5. View Storefronts Directory by Owner");
        System.out.println("\t6. View All Products By Tag");
        System.out.println("\t7. Add funds to Wallet");
        System.out.println("\t8. Withdraw funds from Wallet");
        System.out.println("\t9. Report User");
        System.out.println("\t10. Rate User");
        System.out.println("\t-1. Sign Out");
        System.out.print("Selection #: ");
        String choice = input.nextLine();
        System.out.print("\n-> Selection #: " + choice);
        switch(choice){
            case "1":
                viewAccountInformation(account);
                break;
            case "2":
                viewAccountMessages(shopOrSwap, account);
                break;
            case "3":
                goToStorefront(shopOrSwap, account);
                break;
            case "4":
                makeAStorefront(shopOrSwap, account);
                break;
            case "5":
                viewStorefrontsByOwner(shopOrSwap);
                break;
            case "6":
                viewAllProductsByTag(shopOrSwap);
                break;
            case "7":
                addToWallet(account);
                break;
            case "8":
                withdrawFromWallet(account);
                break;
            case "9":
                reportUser(shopOrSwap, account);
                break;
            case "10":
                rateUser(shopOrSwap, account);
                break;
            case "-1":
                return;
            default:
                System.out.println("\nInvalid menu selection");
                break;
        }
        clientMenu(shopOrSwap, account);
    }

    // account menu for a successfully signed-in admin
    private static void adminMenu(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Account Menu--");
        System.out.println("\t1. View My Account Information");
        System.out.println("\t2. View My Received Messages");
        System.out.println("\t3. Freeze Account");
        System.out.println("\t4. Unfreeze Account");
        System.out.println("\t-1. Sign Out");
        System.out.print("Selection #: ");
        String choice = input.nextLine();
        System.out.print("\n-> Selection #: " + choice);
        switch(choice){
            case "1":
                viewAccountInformation(account);
                break;
            case "2":
                viewAccountMessages(shopOrSwap, account);
                break;
            case "3":
                freezeAccount(shopOrSwap, account);
                break;
            case "4":
                unfreezeAccount(shopOrSwap, account);
                break;
            case "-1":
                return;
            default:
                System.out.println("\nInvalid menu selection");
                break;
        }
        adminMenu(shopOrSwap, account);
    }

    // procedure to freeze an account
    private static void freezeAccount(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Freeze Account Procedure--");
        System.out.print("Account name of Account to freeze: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Account name of Account to freeze: " + nameIn);
        try{
            Account accountToFreeze = shopOrSwap.findAccount(nameIn);
            if(!accountToFreeze.getIsFrozen()){
                shopOrSwap.freezeAccount(account, accountToFreeze);
                System.out.print("\nAccount " + accountToFreeze.getAccountName() + " frozen status: " + accountToFreeze.getIsFrozen());
            }else{
                System.out.print("\nAccount is already frozen");
            }
        }catch(NoSuchElementException e){
            System.out.print("\nAccount does not exist");
        }
        return;
    }

    // procedure to unfreeze an account
    private static void unfreezeAccount(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Freeze Account Procedure--");
        System.out.print("Account name of Account to unfreeze: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Account name of Account to unfreeze: " + nameIn);
        try{
            Account accountToFreeze = shopOrSwap.findAccount(nameIn);
            if(accountToFreeze.getIsFrozen()){
                shopOrSwap.unfreezeAccount(account, accountToFreeze);
                System.out.print("\nAccount " + accountToFreeze.getAccountName() + " frozen status: " + accountToFreeze.getIsFrozen());
            }else{
                System.out.print("\nAccount is not frozen already");
            }
        }catch(NoSuchElementException e){
            System.out.print("\nAccount does not exist");
        }
        return;
    }

    // view account information of a signed-in user
    private static void viewAccountInformation(Account account){
        System.out.println("\n--View Account Procedure--");
        System.out.println("Account " + account.getAccountName() + ":");
        System.out.println("\taccount name: " + account.getAccountName());
        System.out.println("\taccount password: " + account.getAccountPassword());
        System.out.println("\tis blocked account: " + account.getIsFrozen());
        if(account.getClass().getName().contains("Client")) {
            System.out.println("\twallet amount: " + ((Client) account).getWallet());
            System.out.println("\taccount rating: " + ((Client) account).getRating());
        }
        return;
    }

    // view account messages of a signed-in user
    private static void viewAccountMessages(ShopOrSwap shopOrSwap, Account account){
        System.out.println("\n--View Received Messages Procedure--");
        List<AbstractMessage> messageList = shopOrSwap.findMessagesByRecipient(account);
        for(AbstractMessage message : messageList){
            System.out.println("Sender: " + message.getSender().getAccountName());
            System.out.println("\tSubject: " + message.getSubject());
            System.out.println("\tContent: " + message.getContent());
        }
        return;
    }

    // go to Storefront procedure of a client
    private static void goToStorefront(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Go to Storefront Procedure--");
        System.out.print("Enter name of Storefront: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Enter name of Storefront: " + nameIn);
        System.out.print("\nEnter account owner name of Storefront: ");
        String ownerIn = input.nextLine();
        System.out.print("\n-> Enter account owner name of Storefront: " + ownerIn);
        Storefront storefront;
        try{
            storefront = shopOrSwap.findStorefront(nameIn, (Client) shopOrSwap.findAccount(ownerIn));
            System.out.print("\nGoing to Storefront " + nameIn + " of " + ownerIn);
            if(shopOrSwap.findAccount(ownerIn) == account){
                storefrontOwnerMenu(shopOrSwap, account, storefront);
            }else{
                storefrontCustomerMenu(shopOrSwap, account, storefront);
            }
        }catch(NoSuchElementException e){
            System.out.print("\nError: " + e.getMessage());
        }
    }

    // make a Storefront procedure of a client
    private static void makeAStorefront(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Go to Storefront Procedure--");
        System.out.print("Enter name of Storefront: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Enter name of Storefront: " + nameIn);
        System.out.print("\nEnter type of Storefront (\"sell\" or \"swap\"): ");
        String typeIn = input.nextLine();
        System.out.print("\n-> Enter type of Storefront (\"sell\" or \"swap\"): " + typeIn);
        Storefront storefront;
        try{
            storefront = shopOrSwap.addStorefront(typeIn, nameIn, (Client) shopOrSwap.findAccount(account));
            System.out.print("\nStorefront " + storefront.getStorefrontName() + " has been successfully created for products to " + typeIn);
        }catch(IllegalArgumentException e){
            System.out.print("\nError: " + e.getMessage());
        }
        return;
    }

    // view storefront directory for an owner procedure
    private static void viewStorefrontsByOwner(ShopOrSwap shopOrSwap){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--View Storefronts Directory by Owner Procedure--");
        System.out.print("\nEnter account owner name of Storefront: ");
        String ownerIn = input.nextLine();
        System.out.print("\n-> Enter account owner name of Storefront: " + ownerIn);
        try{
            System.out.println("\nStorefronts:");
            for(Storefront storefront : ((Client) shopOrSwap.findAccount(ownerIn)).getMyStorefronts().values()){
                System.out.println("\t" + storefront.getStorefrontName());
            }
        }catch(NoSuchElementException e){
            System.out.print("\nError: " + e.getMessage());
        }
        return;
    }

    // find products by tag
    private static void viewAllProductsByTag(ShopOrSwap shopOrSwap){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Find Products by Tag Procedure--");
        System.out.print("\nEnter tag to search by: ");
        String tagIn = input.nextLine();
        System.out.print("\n-> Enter tag to search by: " + tagIn);
        if(tagIn.isEmpty()){
            System.out.println("\nProducts Found for all tags:");
            for(Tag tag : shopOrSwap.getSystemTags().values()){
                System.out.println("\nTag " + tag.getName());
                for(AbstractProduct product : shopOrSwap.findProductsByTag(tag.getName())){
                    System.out.println("\t" + product.getProductName());
                    System.out.println("\t\tDescription: " + product.getProductDescription());
                    System.out.println("\t\tValue: " + product.getProductValue());
                    System.out.println("\t\tMerchant: " + product.getProductMerchant().getAccountName());
                }
            }
        }else {
            try {
                System.out.println("\nProducts Found for tag " + tagIn + ":");
                for (AbstractProduct product : shopOrSwap.findProductsByTag(tagIn)) {
                    System.out.println(product.getProductName());
                    System.out.println("\tDescription: " + product.getProductDescription());
                    System.out.println("\tValue: " + product.getProductValue());
                    System.out.println("\tMerchant: " + product.getProductMerchant().getAccountName());
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // add funds to wallet
    private static void addToWallet(Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Remove from Wallet: " + account.getAccountName() + "--");
        System.out.print("\nEnter amount of money to add: ");
        String amountString = input.nextLine();
        System.out.print("\n-> Enter amount of money to add: " + amountString);
        try {
            ((Client) account).addWallet(Double.parseDouble(amountString));
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
        }
        System.out.print("\nWallet funds: " + ((Client) account).getWallet());
        return;
    }

    // remove funds from wallet
    private static void withdrawFromWallet(Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Add to Wallet: " + account.getAccountName() + "--");
        System.out.print("\nEnter amount of money to remove: ");
        String amountString = input.nextLine();
        System.out.print("\n-> Enter amount of money to remove: " + amountString);
        try {
            ((Client) account).subtractWallet(Double.parseDouble(amountString));
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
        }
        System.out.print("\nWallet funds: " + ((Client) account).getWallet());
        return;
    }

    // report user
    private static void reportUser(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Report Procedure--");
        System.out.print("\nEnter account name of User to report: ");
        String nameToReport = input.nextLine();
        System.out.print("\n-> Enter account name of User to report: " + nameToReport);
        System.out.print("\nEnter reasoning for report: ");
        String reportContent = input.nextLine();
        System.out.print("\n-> Enter reasoning for report: " + reportContent);
        try {
            Account accountToReport = shopOrSwap.findAccount(nameToReport);
            shopOrSwap.sendMessage("Report", account.getAccountName(), nameToReport, "Report: " + nameToReport, reportContent);
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
        }
        return;
    }

    // rate user
    private static void rateUser(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Rate User Procedure--");
        System.out.print("Enter the name of the user to rate: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Enter the name of the user to rate: " + nameIn);
        Account accountToRate;
        try{
            accountToRate = shopOrSwap.findAccount(nameIn);
            if(accountToRate.getClass().getName().contains("Admin")){
                throw new IllegalArgumentException("Admins are not valid users to rate");
            }else if(accountToRate == account){
                throw new IllegalArgumentException("Cannot rate your own account");
            }
            System.out.print("\nUser found: " + accountToRate.getAccountName());
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
            return;
        }
        System.out.print("\nEnter the rating (integer between 1 and 5 (both inclusive)): ");
        String ratingIn = input.nextLine();
        System.out.print("\n-> Enter the rating (integer between 1 and 5 (both inclusive)): " + ratingIn);
        try {
            ((Client) accountToRate).rate(Integer.parseInt(ratingIn));
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
        }
        return;
    }

    // view storefront as an owner menu
    private static void storefrontOwnerMenu(ShopOrSwap shopOrSwap, Account account, Storefront storefront){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Storefront Menu: Owner--");
        System.out.println("\t1. Add Storefront Product");
        System.out.println("\t2. Remove Storefront Product");
        System.out.println("\t3. View Storefront Products");
        System.out.println("\t-1. Back");
        System.out.print("Selection #: ");
        String choice = input.nextLine();
        System.out.print("\n-> Selection #: " + choice);
        switch(choice){
            case "1":
                addProductToStorefront(shopOrSwap, account, storefront);
                break;
            case "2":
                removeProductFromStorefront(shopOrSwap, account, storefront);
                break;
            case "3":
                viewStorefrontProducts(storefront);
                break;
            case "-1":
                return;
            default:
                System.out.println("\nInvalid menu selection");
                break;
        }
        storefrontOwnerMenu(shopOrSwap, account, storefront);
    }

    // add a product to a storefront as an owner
    private static void addProductToStorefront(ShopOrSwap shopOrSwap, Account account, Storefront storefront){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Add Product to Storefront Procedure--");
        System.out.print("Enter product name: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Enter product name (string): " + nameIn);
        System.out.print("\nEnter product description: ");
        String descriptionIn = input.nextLine();
        System.out.print("\n-> Enter product description (string): " + descriptionIn);
        System.out.print("\nEnter product value (double): ");
        double valueIn;
        try{
            valueIn = Double.parseDouble(input.nextLine());
            System.out.print("\n-> Enter product value (double): " + valueIn);
        }catch(InputMismatchException e1){
            System.out.println("Error: did not input numeric value. Halting process of adding product.");
            return;
        }
        System.out.print("\nEnter tags separated by \",\"(leave blank if no tags are to be added): ");
        String tagsString = input.nextLine();
        System.out.print("\n-> Enter tags separated by \",\"(leave blank if no tags are to be added): " + tagsString);
        AbstractProduct nextProduct;
        if(storefront.getClass().getName().contains("Sell")){
            try {
                nextProduct = ((SellStorefront) storefront).addProduct(new SellProduct(nameIn, descriptionIn, valueIn, (Client) account));
                if (!tagsString.isEmpty()) {
                    for (String tagLabel : tagsString.split(",")) {
                        try {
                            nextProduct.addTag(shopOrSwap.findTag(tagLabel.trim()));
                        } catch (NoSuchElementException e) {
                            nextProduct.addTag(shopOrSwap.addTag(tagLabel.trim()));
                        } catch (IllegalArgumentException e2) {
                            System.out.println("invalid tag");
                        }
                    }
                }
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }else{
            try {
                nextProduct = ((SwapStorefront) storefront).addProduct(new SwapProduct(nameIn, descriptionIn, valueIn, (Client) account));
                if (!tagsString.isEmpty()) {
                    for (String tagLabel : tagsString.split(",")) {
                        try {
                            nextProduct.addTag(shopOrSwap.findTag(tagLabel.trim()));
                        } catch (NoSuchElementException e) {
                            nextProduct.addTag(shopOrSwap.addTag(tagLabel.trim()));
                        } catch (IllegalArgumentException e2) {
                            System.out.println("invalid tag");
                        }
                    }
                }
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
        System.out.print("\nProduct " + nextProduct.getProductName() + " has been successfully added to the storefront " + storefront.getStorefrontName());
        return;
    }

    // views all products in a storefront (both as an owner and as a customer)
    private static void viewStorefrontProducts(Storefront storefront){
        System.out.println("\n--Storefront Products: " + storefront.getStorefrontName() + "--");
        for(AbstractProduct product : storefront.getStorefrontProducts()){
            System.out.println("Name: " + product.getProductName());
            System.out.println("\tDescription: " + product.getProductDescription());
            System.out.println("\tValue: " + product.getProductValue());
            System.out.println("\tMerchant: " + product.getProductMerchant().getAccountName());
            System.out.println("\tTags: " + product.getProductTags());
        }
        return;
    }

    // remove a product from a storefront as an owner
    private static void removeProductFromStorefront(ShopOrSwap shopOrSwap, Account account, Storefront storefront){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Remove Product from Storefront Procedure--");
        System.out.print("Enter product name: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Enter product name: " + nameIn);
        try{
            AbstractProduct removedProduct = shopOrSwap.removeFromStorefront(nameIn, storefront);
            System.out.print("\nRemoved product: " + removedProduct.getProductName());
        }catch (NoSuchElementException e){
            System.out.print("\nError: " + e.getMessage());
        }
        return;
    }

    // view storefront as a customer menu
    private static void storefrontCustomerMenu(ShopOrSwap shopOrSwap, Account account, Storefront storefront){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Storefront Menu: " + storefront.retrieveStorefrontOwner().getAccountName() + " (merchant rating: " + storefront.retrieveStorefrontOwner().getRating() + ")" + "--");
        System.out.println("\t1. Complete Transaction");
        System.out.println("\t2. View Storefront Products");
        System.out.println("\t-1. Back");
        System.out.print("Selection #: ");
        String choice = input.nextLine();
        System.out.print("\n-> Selection #: " + choice);
        switch(choice){
            case "1":
                if(storefront.getClass().getName().contains("Sell")){
                    buyProcedure(shopOrSwap, account, storefront);
                }else{
                    swapProcedure(shopOrSwap, account, storefront);
                }
                break;
            case "2":
                viewStorefrontProducts(storefront);
                break;
            case "-1":
                return;
            default:
                System.out.println("\nInvalid menu selection");
                break;
        }
        storefrontCustomerMenu(shopOrSwap, account, storefront);
    }

    // buy a product as a customer from the storefront
    private static void buyProcedure(ShopOrSwap shopOrSwap, Account account, Storefront storefront){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Complete Transaction: Buy--");
        viewStorefrontProducts(storefront);
        System.out.print("\nEnter the name of the product to buy: ");
        String nameIn = input.nextLine();
        System.out.print("\n-> Enter the name of the product to buy: " + nameIn);
        try{
            AbstractProduct buyProduct = shopOrSwap.findInStorefront(nameIn, storefront);
            shopOrSwap.buyProduct(storefront, (SellProduct) buyProduct, (Client) account);
            String subjectIn = "Transaction: " + buyProduct.getProductName();
            String contentIn = account.getAccountName() + " has completed a transaction to buy " + buyProduct.getProductName() + " from " + buyProduct.getProductMerchant().getAccountName();
            shopOrSwap.sendMessage("User", storefront.retrieveStorefrontOwner().getAccountName(), account.getAccountName(), subjectIn, contentIn);
            shopOrSwap.sendMessage("User", account.getAccountName(), storefront.retrieveStorefrontOwner().getAccountName(), subjectIn, contentIn);
            System.out.print("\nTransaction complete");
        }catch(Exception e1){
            System.out.print("\nError: " + e1.getMessage());
            return;
        }
        return;
    }

    // swap a product as a customer from the storefront
    private static void swapProcedure(ShopOrSwap shopOrSwap, Account account, Storefront storefront){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Complete Transaction: Swap--");
        viewStorefrontProducts(storefront);
        System.out.print("\nEnter the name of the product you want: ");
        String productWantName = input.nextLine();
        System.out.print("\n-> Enter the name of the product to you want: " + productWantName);
        AbstractProduct productWant, productGive;
        try{
            productWant = shopOrSwap.findInStorefront(productWantName, storefront);
            System.out.print("\nProduct found: " + productWant.getProductName());
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
            return;
        }
        System.out.print("\nEnter the name of the storefront of the product you give: ");
        String storefrontGiveName = input.nextLine();
        System.out.print("\n-> Enter the name of the storefront of the product you give: " + storefrontGiveName);
        Storefront storefrontGive;
        try{
            storefrontGive = shopOrSwap.findStorefront(storefrontGiveName, (Client) account);
            if(storefrontGive.getClass().getName().contains("Sell")){
                throw new IllegalArgumentException("Storefront of the product you give must be a swapping storefront");
            }else if(((SwapStorefront) storefrontGive).retrieveStorefrontOwner() != account){
                throw new IllegalArgumentException("Storefront of the product you give must be owned by you");
            }
            System.out.print("\nStorefront found: " + storefrontGive.getStorefrontName());
            viewStorefrontProducts(storefrontGive);
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
            return;
        }
        System.out.println("\nEnter the name of the product you give: ");
        String productGiveName = input.nextLine();
        System.out.println("\n-> Enter the name of the product you give: " + productGiveName);
        try{
            productGive = shopOrSwap.findInStorefront(productGiveName, storefrontGive);
            System.out.print("\nProduct found: " + productGive.getProductName());
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
            return;
        }
        try {
            String subjectIn = "Transaction: " + productWant.getProductName() + " for " + productGive.getProductName();
            String contentIn = account.getAccountName() + " has completed a transaction to get " + productWant.getProductName() + " from " + productWant.getProductMerchant().getAccountName() + " by giving " + productGive.getProductName();
            shopOrSwap.sendMessage("User", storefront.retrieveStorefrontOwner().getAccountName(), account.getAccountName(), subjectIn, contentIn);
            shopOrSwap.sendMessage("User", account.getAccountName(), storefront.retrieveStorefrontOwner().getAccountName(), subjectIn, contentIn);
            shopOrSwap.swapProducts(storefront, productWant, storefrontGive, productGive);
            System.out.print("\nTransaction complete");
        }catch(Exception e){
            System.out.print("\nError: " + e);
            return;
        }
        return;
    }

    // exit procedure for the program
    private static void exitProcedure(ShopOrSwap shopOrSwap) throws IOException {
        System.out.println("\n--Exit Procedure--");
        try {
            ShopOrSwapRecord recordOut = new ShopOrSwapRecord(shopOrSwap);
            JsonUtil.toJsonFile("src" + File.separator + "main" + File.separator + "resources" + File.separator + "systemData.json", recordOut);
            System.out.println("Data export success");
            System.exit(0);
        }catch(Exception e){
            System.out.println("Error: Data export failure");
            System.exit(1);
        }
    }

}
