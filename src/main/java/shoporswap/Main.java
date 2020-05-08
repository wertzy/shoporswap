package shoporswap;

import io.*;
import util.JsonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
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
        preliminaryMenu(shopOrSwap);
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
                System.out.println("Invalid menu selection");
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
            System.out.print("\nWelcome " + account.getAccountName());
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
            case "-1":
                return;
            default:
                System.out.println("Invalid menu selection");
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
                System.out.println("Invalid menu selection");
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
        return;
    }

    // view account messages of a signed-in user
    private static void viewAccountMessages(ShopOrSwap shopOrSwap, Account account){
        System.out.println("\n--View Received Messages Procedure--");
        for(AbstractMessage message : shopOrSwap.findMessagesByRecipient(account)){
            System.out.println(new MessageRecord(message));
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
            System.out.println("");
            for(Storefront storefront : ((Client) shopOrSwap.findAccount(ownerIn)).getMyStorefronts().values()){
                System.out.println(storefront.getStorefrontName());
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
                break;
            case "3":
                break;
            case "4":
                break;
            case "-1":
                return;
            default:
                System.out.println("Invalid menu selection");
                break;
        }
        storefrontOwnerMenu(shopOrSwap, account, storefront);
    }

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
            }
        }
        return;
    }

    // view storefront as a customer menu
    private static void storefrontCustomerMenu(ShopOrSwap shopOrSwap, Account account, Storefront storefront){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Storefront Menu: Customer--");
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
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "-1":
                return;
            default:
                System.out.println("Invalid menu selection");
                break;
        }
        storefrontCustomerMenu(shopOrSwap, account, storefront);
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
