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
    private static boolean USE_SCRIPT;
    private static String DATA_FILE_NAME;

    private static void setNoScript(){
        USE_SCRIPT = false;
    }

    private static String getInput(String message){
        Scanner scanner = new Scanner(System.in);
        String output;
        System.out.print("\n" + message + ": ");
        if(USE_SCRIPT && SCRIPT_INPUT != null && SCRIPT_INPUT.hasNextLine()){
            output = SCRIPT_INPUT.nextLine();
            System.out.print("\n" + message + ": \"" + output + "\"");
        }else {
            output = scanner.nextLine();
            System.out.print(message + ": \"" + output + "\"");
            setNoScript();
        }
        return output;
    }

    private static void makeScriptReader() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Script name (include file extension, leave blank for no script): ");
            String scriptName = input.nextLine();
            if(scriptName.isEmpty()){
                setNoScript();
                return;
            }
            SCRIPT_INPUT = new Scanner(new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "scripts" + File.separator + scriptName));
            USE_SCRIPT = true;
        } catch (FileNotFoundException e) {
            System.out.println("Error: test script not found");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        makeScriptReader();
        ShopOrSwap shopOrSwap = getShopOrSwapData();
        try {
            preliminaryMenu(shopOrSwap);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    // JSON data import
    private static ShopOrSwap getShopOrSwapData(){
        DATA_FILE_NAME = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "systemData.json";
        ShopOrSwap shopOrSwapOut;
        try{
            ShopOrSwapRecord shopOrSwapRecord = JsonUtil.fromJsonFile(DATA_FILE_NAME, ShopOrSwapRecord.class);
            shopOrSwapOut = shopOrSwapRecord.toShopOrSwap();
            System.out.println("Data import success");
        } catch (IOException e) {
            System.out.println("Data Not Found");
            shopOrSwapOut = new ShopOrSwap();
            shopOrSwapOut.addAccount("Admin", "admin1", "admin1");
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
        String choice = getInput("Selection #");
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
        String nameIn = getInput("Account name");
        String passwordIn = getInput("Account password");
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
            System.out.print("\n" + e.getMessage());
        }
        return;
    }

    // create account procedure for the user
    private static void createAccountProcedure(ShopOrSwap shopOrSwap){
        System.out.println("\n--Create Account Procedure--");
        String nameIn = getInput("Desired Account Name");
        String passwordIn = getInput("Desired Password");
        try{
            Account account = shopOrSwap.addAccount("Client", nameIn, passwordIn);
            System.out.print("\nWelcome " + account.getAccountName() + ". Please sign in using your new credentials to access your new account.");
        }catch (IllegalArgumentException e){
            System.out.print("\n" + e.getLocalizedMessage());
        }
        return;
    }

    private static void sendMessage(ShopOrSwap shopOrSwap, Account account){
        String receiverName = getInput("Enter account name of Account to send message to");
        Account receiver;
        try{
            receiver = shopOrSwap.findAccount(receiverName);
        }catch(NoSuchElementException e){
            System.out.print("\nCannot find account with account name; cannot send message.");
            return;
        }
        String subject = getInput("Enter message subject");
        String content = getInput("Enter message content");
        try{
            int messageCount = shopOrSwap.getSystemMessages().size();
            shopOrSwap.sendMessage("User", account.getAccountName(), receiverName, subject, content);
            assert shopOrSwap.getSystemMessages().size() == messageCount + 1;
        }catch(Exception e){
            System.out.println("Error: message not sent");
        }
        return;
    }

    // account menu for a successfully signed-in client
    private static void clientMenu(ShopOrSwap shopOrSwap, Account account){
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
        System.out.println("\t11. Send Message to User");
        System.out.println("\t-1. Sign Out");
        String choice = getInput("Selection #");
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
            case "11":
                sendMessage(shopOrSwap, account);
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
        System.out.println("\t5. Reset System");
        System.out.println("\t-1. Sign Out");
        String choice = getInput("Selection #");
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
            case "5":
                resetSystem();
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
        System.out.println("\n--Freeze Account Procedure--");
        String nameIn = getInput("Account name of Account to freeze");
        try{
            Account accountToFreeze = shopOrSwap.findAccount(nameIn);
            if(!accountToFreeze.getIsFrozen()){
                shopOrSwap.freezeAccount(account, accountToFreeze);
                System.out.print("\nAccount " + accountToFreeze.getAccountName() + " frozen status: " + accountToFreeze.getIsFrozen());
            }else{
                System.out.print("\nAccount is already frozen");
            }
        }catch(NoSuchElementException e){
            System.out.print("\n" + e.getMessage());
        }
        return;
    }

    // procedure to unfreeze an account
    private static void unfreezeAccount(ShopOrSwap shopOrSwap, Account account){
        System.out.println("\n--Freeze Account Procedure--");
        String nameIn = getInput("Account name of Account to unfreeze");
        try{
            Account accountToFreeze = shopOrSwap.findAccount(nameIn);
            if(accountToFreeze.getIsFrozen()){
                shopOrSwap.unfreezeAccount(account, accountToFreeze);
                System.out.print("\nAccount " + accountToFreeze.getAccountName() + " frozen status: " + accountToFreeze.getIsFrozen());
            }else{
                System.out.print("\nAccount is not frozen already");
            }
        }catch(NoSuchElementException e){
            System.out.print("\n" + e.getMessage());
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
            System.out.println("\taccount rating: " + ((Client) account).calculateRating());
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
        String nameIn = getInput("Enter name of Storefront");
        String ownerIn = getInput("Enter account owner name of Storefront");
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
        System.out.println("\n--Go to Storefront Procedure--");
        String nameIn = getInput("Enter name of Storefront");
        String typeIn = getInput("Enter type of Storefront");
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
        System.out.println("\n--View Storefronts Directory by Owner Procedure--");
        String ownerIn = getInput("Enter account owner name of Storefront");
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
        System.out.println("\n--Find Products by Tag Procedure--");
        String tagIn = getInput("Enter tag to search by");
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
        System.out.println("\n--Remove from Wallet: " + account.getAccountName() + "--");
        String amountString = getInput("Enter amount of money to add");
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
        System.out.println("\n--Add to Wallet: " + account.getAccountName() + "--");
        String amountString = getInput("Enter amount of money to remove");
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
        System.out.println("\n--Report Procedure--");
        String nameToReport = getInput("Enter account name of User to report");
        String reportContent = getInput("Enter reasoning for report");
        try {
            Account accountToReport = shopOrSwap.findAccount(nameToReport);
            shopOrSwap.sendMessage("Report", account.getAccountName(), accountToReport.getAccountName(), "Report: " + nameToReport, reportContent);
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
        }
        return;
    }

    // rate user
    private static void rateUser(ShopOrSwap shopOrSwap, Account account){
        System.out.println("\n--Rate User Procedure--");
        String nameIn = getInput("Enter the name of the user to rate");
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
        String ratingIn = getInput("Enter the rating (integer between 1 and 5 (both inclusive))");
        try {
            ((Client) accountToRate).rate(Integer.parseInt(ratingIn));
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
        }
        return;
    }

    // view storefront as an owner menu
    private static void storefrontOwnerMenu(ShopOrSwap shopOrSwap, Account account, Storefront storefront){
        System.out.println("\n--Storefront Menu: Owner--");
        System.out.println("\t1. Add Storefront Product");
        System.out.println("\t2. Remove Storefront Product");
        System.out.println("\t3. View Storefront Products");
        System.out.println("\t-1. Back");
        String choice = getInput("Selection #");
        switch(choice){
            case "1":
                addProductToStorefront(shopOrSwap, account, storefront);
                break;
            case "2":
                removeProductFromStorefront(shopOrSwap, storefront);
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
        System.out.println("\n--Add Product to Storefront Procedure--");
        String nameIn = getInput("Enter product name");
        String descriptionIn = getInput("Enter product description");
        String valueInString = getInput("Enter product value (double)");
        double valueIn;
        try{
            valueIn = Double.parseDouble(valueInString);
        }catch(InputMismatchException e1){
            System.out.println("Error: did not input numeric value. Halting process of adding product.");
            return;
        }
        String tagsString = getInput("Enter tags separated by \",\"(leave blank if no tags are to be added)");
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
            System.out.println("\tTags: " + product.getProductTagLabels());
        }
        return;
    }

    // remove a product from a storefront as an owner
    private static void removeProductFromStorefront(ShopOrSwap shopOrSwap, Storefront storefront){
        System.out.println("\n--Remove Product from Storefront Procedure--");
        String nameIn = getInput("Enter product name");
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
        System.out.println("\n--Storefront Menu: " + storefront.retrieveStorefrontOwner().getAccountName() + " (merchant rating: " + storefront.retrieveStorefrontOwner().calculateRating() + ")" + "--");
        System.out.println("\t1. Complete Transaction");
        System.out.println("\t2. View Storefront Products");
        System.out.println("\t-1. Back");
        String choice = getInput("Selection #");
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
        System.out.println("\n--Complete Transaction: Buy--");
        viewStorefrontProducts(storefront);
        String nameIn = getInput("Enter the name of the product to buy");
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
        System.out.println("\n--Complete Transaction: Swap--");
        viewStorefrontProducts(storefront);
        String productWantName = getInput("Enter the name of the product you want");
        AbstractProduct productWant, productGive;
        try{
            productWant = shopOrSwap.findInStorefront(productWantName, storefront);
            System.out.print("\nProduct found: " + productWant.getProductName());
        }catch(Exception e){
            System.out.print("\nError: " + e.getMessage());
            return;
        }
        String storefrontGiveName = getInput("Enter the name of the storefront of the product you give");
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
        String productGiveName = getInput("Enter the name of the product you give");
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
            System.out.println("Error: Data export failure\n");
            System.exit(1);
        }
    }

    private static void resetSystem(){
        (new File(DATA_FILE_NAME)).deleteOnExit();
        System.out.print("\nSystem Data Reset. Please restart program to see changes.");
        System.exit(0);
    }

}
