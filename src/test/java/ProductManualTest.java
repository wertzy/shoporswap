import java.util.NoSuchElementException;
import java.util.Scanner;

public class ProductManualTest {

    public static void errorCheckInputManualTest(){
        Scanner input = new Scanner(System.in);

        System.out.println("Sample merchant:");
        System.out.println("\taccount name: \"user1\"");
        System.out.println("\tpassword: \"pass1\"");

        User testUser = new User("user1", "pass1");

        String testNamePrompt = "Enter a valid product name: (cannot start or end with a space, must contain 1 to 50 (both inclusive) alphanumeric or space characters";
        System.out.println(testNamePrompt);
        String testName = input.nextLine();

        while(!Product.isValidName(testName)){
            System.out.println(testNamePrompt);
            testName = input.nextLine();
        }

        String testDescriptionPrompt = "Enter a valid product description: (cannot start or end with a space, must contain 1 to 500 (both inclusive) alphanumeric or space characters";
        System.out.println(testDescriptionPrompt);
        String testDescription = input.nextLine();

        while(!Product.isValidDescription(testDescription)){
            System.out.println(testDescriptionPrompt);
            testDescription = input.nextLine();
        }

        String testPricePrompt = "Enter a valid product price: (cannot be negative, must have no more than 2 decimal places)";
        System.out.println(testPricePrompt);
        String testPrice = input.nextLine();
        try{
            while(!Product.isValidPrice(Double.parseDouble(testPrice))){
                System.out.println(testPricePrompt);
                testPrice = input.nextLine();
            }
        }catch(Exception e){
            System.out.println("You did not enter a parseable price. Setting test price to 55.55");
            testPrice = "55.55";
        }

        try {
            Product testProduct = new Product(testName, testDescription, Double.parseDouble(testPrice), testUser);
            System.out.println("\tProduct name: \"" + testProduct.getName() + "\"");
            System.out.println("\tProduct description: \"" + testProduct.getDescription() + "\"");
            System.out.println("\tProduct price: \"" + testProduct.getPrice() + "\"");
            System.out.println("\tProduct tags: \"" + testProduct.getTags().toString() + "\"");
            String testTag;
            while(testProduct.getTags().size() < 3){
                System.out.println("Add a valid tag: (alphanumeric, cannot contain spaces, at least 1 character)");
                testTag = input.nextLine();
                while(!Product.isValidTag(testTag)){
                    System.out.println("Add a valid tag: (alphanumeric, cannot contain spaces, at least 1 character)");
                    testTag = input.nextLine();
                }
                testProduct.addTag(testTag);
                System.out.println("\tProduct name: \"" + testProduct.getName() + "\"");
                System.out.println("\tProduct description: \"" + testProduct.getDescription() + "\"");
                System.out.println("\tProduct price: \"" + testProduct.getPrice() + "\"");
                System.out.println("\tProduct tags: \"" + testProduct.getTags().toString() + "\"");
            }
            while(!testProduct.getTags().isEmpty()){
                System.out.println("Remove a valid tag: (alphanumeric, cannot contain spaces, at least 1 character, product labeled with the tag already)");
                testTag = input.nextLine();
                while(!Product.isValidTag(testTag)){
                    System.out.println("Remove a valid tag: (alphanumeric, cannot contain spaces, at least 1 character, product labeled with the tag already)");
                    testTag = input.nextLine();
                }
                try{
                    testProduct.removeTag(testTag);
                    System.out.println("\tProduct name: \"" + testProduct.getName() + "\"");
                    System.out.println("\tProduct description: \"" + testProduct.getDescription() + "\"");
                    System.out.println("\tProduct price: \"" + testProduct.getPrice() + "\"");
                    System.out.println("\tProduct tags: \"" + testProduct.getTags().toString() + "\"");
                }catch(NoSuchElementException e){
                    System.out.println(e.getMessage());
                    continue;
                }
            }
            System.out.println("ERROR CHECK INPUT MANUAL TEST COMPLETION SUCCESSFUL");
        }catch(Exception e){
            System.out.println("Error in manual test:");
            System.out.println("\t" + e);
            System.out.println("ERROR CHECK INPUT MANUAL TEST COMPLETION FAILURE");
        }

    }

    public static void noErrorCheckInputManualTest(){
        Scanner input = new Scanner(System.in);

        System.out.println("Sample merchant:");
        System.out.println("\taccount name: \"user1\"");
        System.out.println("\tpassword: \"pass1\"");

        User testUser = new User("user1", "pass1");

        String testNamePrompt = "Enter a valid product name: (cannot start or end with a space, must contain 1 to 50 (both inclusive) alphanumeric or space characters";
        System.out.println(testNamePrompt);
        String testName = input.nextLine();

        String testDescriptionPrompt = "Enter a valid product description: (cannot start or end with a space, must contain 1 to 500 (both inclusive) alphanumeric or space characters";
        System.out.println(testDescriptionPrompt);
        String testDescription = input.nextLine();

        String testPricePrompt = "Enter a valid product price: (cannot be negative, must have no more than 2 decimal places)";
        System.out.println(testPricePrompt);
        String testPrice = input.nextLine();
        try{
            Product testProduct = new Product(testName, testDescription, Double.parseDouble(testPrice), testUser);
            System.out.println("\tProduct name: \"" + testProduct.getName() + "\"");
            System.out.println("\tProduct description: \"" + testProduct.getDescription() + "\"");
            System.out.println("\tProduct price: \"" + testProduct.getPrice() + "\"");
            System.out.println("\tProduct tags: \"" + testProduct.getTags().toString() + "\"");
            String testTag;
            while(testProduct.getTags().size() < 3){
                System.out.println("Add a valid tag: (alphanumeric, cannot contain spaces, at least 1 character, product labeled with the tag already)");
                testTag = input.nextLine();
                testProduct.addTag(testTag);
                System.out.println("\tProduct name: \"" + testProduct.getName() + "\"");
                System.out.println("\tProduct description: \"" + testProduct.getDescription() + "\"");
                System.out.println("\tProduct price: \"" + testProduct.getPrice() + "\"");
                System.out.println("\tProduct tags: \"" + testProduct.getTags().toString() + "\"");
            }
            while(!testProduct.getTags().isEmpty()){
                System.out.println("Remove a valid tag: (alphanumeric, cannot contain spaces, at least 1 character, product labeled with the tag already)");
                testTag = input.nextLine();
                while(!Product.isValidTag(testTag)){
                    System.out.println("Remove a valid tag: (alphanumeric, cannot contain spaces, at least 1 character, product labeled with the tag already)");
                    testTag = input.nextLine();
                }
                testProduct.removeTag(testTag);
                System.out.println("\tProduct name: \"" + testProduct.getName() + "\"");
                System.out.println("\tProduct description: \"" + testProduct.getDescription() + "\"");
                System.out.println("\tProduct price: \"" + testProduct.getPrice() + "\"");
                System.out.println("\tProduct tags: \"" + testProduct.getTags().toString() + "\"");
            }
            System.out.println("NO ERROR CHECK INPUT MANUAL TEST COMPLETION SUCCESSFUL");
        }catch(Exception e){
            System.out.println("Error in manual test:");
            System.out.println("\t" + e);
            System.out.println("NO ERROR CHECK INPUT MANUAL TEST COMPLETION FAILURE");
        }
    }

    public static void main(String[] args) {
        System.out.println("Manual Test #1: Error Check");
        errorCheckInputManualTest();
        System.out.println("Manual Test #2: No Error Check");
        noErrorCheckInputManualTest();
    }
}
