package shoporswap;

import io.*;
import util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
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
        String choice = input.next();
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
        System.out.print("Account Name: ");
        String nameIn = input.nextLine();
        System.out.print("Account Password: ");
        String passwordIn = input.nextLine();
        try{
            Account account = shopOrSwap.findAccount(nameIn);
            if(account.getAccountPassword().compareTo(passwordIn) != 0){
                throw new NoSuchElementException("Cannot find account with specified credentials");
            }
            if(account.getIsFrozen()){
                System.out.println("Account is frozen. You cannot access the system. Please contact an administrator.");
                return;
            }
            System.out.println("Welcome back " + account.getAccountName());
            if((new AccountRecord(account)).accessAccountType().contains("Client")) {
                clientMenu(shopOrSwap, account);
            }else{
                adminMenu(shopOrSwap, account);
            }
        }catch(NoSuchElementException e){
            System.out.println("Cannot find account with specified credentials");
        }
        return;
    }

    // create account procedure for the user
    private static void createAccountProcedure(ShopOrSwap shopOrSwap){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Create Account Procedure--");
        System.out.print("Desired Account Name: ");
        String nameIn = input.nextLine();
        System.out.print("Desired Account Password: ");
        String passwordIn = input.nextLine();
        try{
            Account account = shopOrSwap.addAccount("Client", nameIn, passwordIn);
            System.out.println("Welcome " + account.getAccountName());
        }catch (IllegalArgumentException e){
            System.out.println("Cannot create account with specified credentials");
        }
        return;
    }

    // account menu for a successfully signed-in client
    private static void clientMenu(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Account Menu--");
        System.out.println("\t1. View My Account Information");
        System.out.println("\t2. View My Messages");
        System.out.println("\t-1. Sign Out");
        System.out.print("Selection #: ");
        String choice = input.next();
        switch(choice){
            case "1":
                viewAccountInformation(account);
                break;
            case "2":
                viewAccountMessages(shopOrSwap, account);
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
        String choice = input.next();
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
        try{
            Account accountToFreeze = shopOrSwap.findAccount(nameIn);
            if(!accountToFreeze.getIsFrozen()){
                shopOrSwap.freezeAccount(account, accountToFreeze);
                System.out.println("Account " + accountToFreeze.getAccountName() + " frozen status: " + accountToFreeze.getIsFrozen());
            }else{
                System.out.println("Account is already frozen");
            }
        }catch(NoSuchElementException e){
            System.out.println("Account does not exist");
        }
        return;
    }

    // procedure to unfreeze an account
    private static void unfreezeAccount(ShopOrSwap shopOrSwap, Account account){
        Scanner input = new Scanner(System.in);
        System.out.println("\n--Freeze Account Procedure--");
        System.out.print("Account name of Account to unfreeze: ");
        String nameIn = input.nextLine();
        try{
            Account accountToFreeze = shopOrSwap.findAccount(nameIn);
            if(accountToFreeze.getIsFrozen()){
                shopOrSwap.unfreezeAccount(account, accountToFreeze);
                System.out.println("Account " + accountToFreeze.getAccountName() + " frozen status: " + accountToFreeze.getIsFrozen());
            }else{
                System.out.println("Account is not frozen already");
            }
        }catch(NoSuchElementException e){
            System.out.println("Account does not exist");
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

    private static void viewAccountMessages(ShopOrSwap shopOrSwap, Account account){
        System.out.println("\n--View Received Messages Procedure--");
        for(AbstractMessage message : shopOrSwap.findMessagesByRecipient(account)){
            System.out.println(new MessageRecord(message));
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
            System.out.println("Data export failure");
            System.exit(1);
        }
    }

}
