import java.util.*;
import java.io.*;

public class ShopOrSwap {

    private AbstractMessageFactory messageFactory;
    private AccountFactory accountFactory;
    private StorefrontFactory storefrontFactory;
    private AbstractProductFactory productFactory;
    private Map<String, Account> accountCollection;
    private String dataFileName;

    /**
     * Default constructor for ShopOrSwap object
     */
    public ShopOrSwap(){}

    /**
     * Constructor for ShopOrSwap object
     * @param dataFileNameIn the name of the JSON file to load data from
     * @throws IOException if dataFileIn is not found
     */
    public ShopOrSwap(String dataFileNameIn){}

    /**
     * Accessor method for the data file property of the ShopOrSwap object
     * @return
     */
    public String getDataFileName(){
        return "";
    }

    /**
     * Mutator method for the data file property of the ShopOrSwap object
     * @param dataFileNameIn the name of the JSON file to load data from
     * @throws IOException if dataFileNameIn is not found
     */
    public void setDataFileName(String dataFileNameIn){}

}
