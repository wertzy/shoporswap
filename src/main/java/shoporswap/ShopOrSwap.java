package shoporswap;

import io.AccountRecord;

import java.util.*;

public class ShopOrSwap {

    private AbstractMessageFactory messageFactory;
    private AccountFactory accountFactory;
    private StorefrontFactory storefrontFactory;
    private AbstractProductFactory productFactory;
    private Map<String, Account> accountCollection;
    private List<AbstractMessage> systemMessages;
    private Map<String, Tag> systemTags;

    /**
     * Default constructor for the shoporswap.ShopOrSwap system
     */
    public ShopOrSwap(){
        this.establishMessageFactory(new AbstractMessageFactory());
        this.establishAccountFactory(new AccountFactory());
        this.establishStorefrontFactory(new StorefrontFactory());
        this.establishProductFactory(new AbstractProductFactory());
        this.establishAccountCollection(new HashMap<String, Account>());
        this.setSystemMessages(new ArrayList<AbstractMessage>());
        this.setSystemTags(new HashMap<String, Tag>());
    }

    /**
     * Adds an shoporswap.Account to the shoporswap.ShopOrSwap system
     * @param accountIn the account to add to the system
     * @return the account added to the system
     * @throws IllegalArgumentException if the account added is invalid
     */
    public Account addAccount(Account accountIn){
        if(this.getAccountCollection().containsKey(accountIn.getAccountName())){
            throw new IllegalArgumentException("shoporswap.Account name already exists in system");
        }
        this.getAccountCollection().put(accountIn.getAccountName(), accountIn);
        return this.getAccountCollection().get(accountIn.getAccountName());
    }

    /**
     * Adds an shoporswap.Account to the shoporswap.ShopOrSwap system
     * @param typeIn the type of Account ("Client" or "Admin")
     * @param nameIn the account name of the Account
     * @param passwordIn the password of the Account
     * @return the account added to the system
     * @throws IllegalArgumentException if any of typeIn, nameIn, and passwordIn are invalid
     */
    public Account addAccount(String typeIn, String nameIn, String passwordIn){
        Account account = this.accessAccountFactory().getAccount(typeIn);
        account.setAccountName(nameIn);
        account.setAccountPassword(passwordIn);
        return this.addAccount(account);
    }

    /**
     * Finds an shoporswap.Account in the shoporswap.ShopOrSwap system
     * @param accountIn the account to find in the system
     * @return the account found in the system based on criteria
     * @throws NoSuchElementException if accountIn does not exist in the system
     */
    public Account findAccount(Account accountIn){
        return this.findAccount(accountIn.getAccountName());
    }

    /**
     * Finds an shoporswap.Account in the shoporswap.ShopOrSwap system
     * @param nameIn the name of the Account to find in the system
     * @return the account found in the system
     * @throws NoSuchElementException if accountIn does not exist in the system
     */
    public Account findAccount(String nameIn){
        if(this.getAccountCollection().containsKey(nameIn)){
            return this.getAccountCollection().get(nameIn);
        }
        throw new NoSuchElementException("shoporswap.Account name does not exist in system");
    }

    /**
     * Removes an shoporswap.Account from the shoporswap.ShopOrSwap system
     * @param accountIn the account to remove from the system
     * @return the removed account
     * @throws NoSuchElementException if the account to remove cannot be found in the system
     */
    public Account removeAccount(Account accountIn){
        Account account = this.findAccount(accountIn);
        this.getAccountCollection().remove(accountIn.getAccountName());
        return account;
    }

    /**
     * Removes an shoporswap.Account from the shoporswap.ShopOrSwap system
     * @param nameIn the name of the account to remove
     * @return the removed account
     * @throws NoSuchElementException if the account to remove cannot be found in the system
     */
    public Account removeAccount(String nameIn){
        Account account = this.findAccount(nameIn);
        this.removeAccount(account);
        return account;
    }

    /**
     * Adds a shoporswap.Storefront to the shoporswap.ShopOrSwap system
     * @param nameIn the name of the shoporswap.Storefront to add
     * @param ownerIn the owner of the shoporswap.Storefront to add
     * @return the Storefront added to the system
     * @throws IllegalArgumentException if nameIn is invalid for shoporswap.Storefront name
     * @throws NoSuchElementException if the ownerIn is not a member of the System
     * @throws IllegalArgumentException if a shoporswap.Storefront already exists for ownerIn with the name of nameIn
     */
    public Storefront addStorefront(String typeIn, String nameIn, Client ownerIn){
        Account owner = this.findAccount(ownerIn);
        Storefront storefront = this.storefrontFactory.getStorefront(typeIn);
        storefront.setStorefrontName(nameIn);
        storefront.setStorefrontOwner(ownerIn);
        ownerIn.addStorefront(storefront);
        return storefront;
    }

    /**
     * Finds a shoporswap.Storefront in the shoporswap.ShopOrSwap system
     * @param nameIn the name of the shoporswap.Storefront
     * @param ownerIn the owner of the shoporswap.Storefront
     * @return the Storefront found in the system
     * @throws NoSuchElementException if the shoporswap.Storefront cannot be found
     */
    public Storefront findStorefront(String nameIn, Client ownerIn){
        Client owner = (Client) this.findAccount(ownerIn);
        if(owner.getMyStorefronts().containsKey(nameIn)){
            return owner.getMyStorefronts().get(nameIn);
        }
        throw new NoSuchElementException("shoporswap.Storefront does not exist for owner");
    }

    /**
     * Removes a shoporswap.Storefront from the shoporswap.ShopOrSwap system
     * @param nameIn the name of the shoporswap.Storefront to remove
     * @param ownerIn the owner of the shoporswap.Storefront to remove
     * @return the Storefront removed from the system
     * @throws NoSuchElementException if the shoporswap.Storefront cannot be found
     */
    public Storefront removeStorefront(String nameIn, Client ownerIn){
        Storefront storefront = this.findStorefront(nameIn, ownerIn);
        ownerIn.getMyStorefronts().remove(nameIn);
        return storefront;
    }

    /**
     * Adds a product to a shoporswap.Storefront
     * @param nameIn the name of the product to add
     * @param descriptionIn the description of the product to add
     * @param valueIn the value of the product to add
     * @param storefrontIn the Storefront to add the product to
     * @return the product added to the system
     * @throws IllegalArgumentException if nameIn, descriptionIn, or valueIn are invalid
     * @throws NoSuchElementException if storefrontIn does not exist (if owner shoporswap.Client of storefrontIn does not own the instance of storefrontIn)
     */
    public AbstractProduct addToStorefront(String nameIn, String descriptionIn, double valueIn, Storefront storefrontIn){
        Storefront storefrontTemp = this.findStorefront(storefrontIn.getStorefrontName(), storefrontIn.retrieveStorefrontOwner());
        AbstractProduct product;
        if(storefrontIn.getClass().getName().contains((CharSequence) "Sell")){
            product = this.accessProductFactory().getProduct("sell");
            product.setProductName(nameIn);
            product.setProductDescription(descriptionIn);
            product.setProductValue(valueIn);
            product.setProductMerchant(storefrontIn.retrieveStorefrontOwner());
            return this.addToStorefront(product, storefrontTemp);
        }else{
            product = this.accessProductFactory().getProduct("swap");
            product.setProductName(nameIn);
            product.setProductDescription(descriptionIn);
            product.setProductValue(valueIn);
            product.setProductMerchant(storefrontIn.retrieveStorefrontOwner());
            return this.addToStorefront(product, storefrontTemp);
        }
    }

    /**
     * Adds a product object to a shoporswap.Storefront
     * @param productIn the product to add
     * @param storefrontIn the storefront to add the product to
     * @return the product added to the storefront
     * @throws NoSuchElementException if storefrontIn does not exist (if owner shoporswap.Client of storefrontIn does not own the instance of storefrontIn)
     */
    public AbstractProduct addToStorefront(AbstractProduct productIn, Storefront storefrontIn){
        Client owner = storefrontIn.retrieveStorefrontOwner();
        if(!this.getAccountCollection().containsKey(owner.getAccountName())){
            throw new NoSuchElementException("shoporswap.Storefront owner does not exist");
        }
        Storefront storefront = owner.findStorefront(storefrontIn);
        if(storefront.getClass().getName().contains((CharSequence) "Sell")){
            SellStorefront sellStorefront = (SellStorefront) storefront;
            return sellStorefront.addProduct((SellProduct) productIn);
        }else{
            SwapStorefront swapStorefront = (SwapStorefront) storefront;
            return swapStorefront.addProduct((SwapProduct) productIn);
        }
    }

    /**
     * Finds a product in a shoporswap.Storefront
     * @param productIn the product to find in the system
     * @param storefrontIn the storefront to find the product in
     * @return the product found in the system
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner shoporswap.Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct findInStorefront(AbstractProduct productIn, Storefront storefrontIn){
        Account account = storefrontIn.retrieveStorefrontOwner();
        if(account == null){
            throw new NoSuchElementException("There are no null accounts in shoporswap.Storefront");
        }
        Storefront storefront = this.findStorefront(storefrontIn.getStorefrontName(), storefrontIn.retrieveStorefrontOwner());
        return this.findInStorefront(productIn.getProductName(), storefrontIn);
    }

    /**
     * Finds a product in a shoporswap.Storefront
     * @param nameIn the name of the product to find
     * @param storefrontIn the storefront to find the product in
     * @return the product found in the system
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner shoporswap.Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct findInStorefront(String nameIn, Storefront storefrontIn){
        if(storefrontIn.getClass().getName().contains((CharSequence) "Sell")){
            SellStorefront sellStorefront = (SellStorefront) storefrontIn;
            for(SellProduct product : sellStorefront.getSellProducts()){
                if(product.getProductName().compareTo(nameIn) == 0){
                    return product;
                }
            }
        }else{
            SwapStorefront sellStorefront = (SwapStorefront) storefrontIn;
            for(SwapProduct product : sellStorefront.getSwapProducts()){
                if(product.getProductName().compareTo(nameIn) == 0){
                    return product;
                }
            }
        }
        throw new NoSuchElementException("Product does not exist in storefront");
    }

    /**
     * Removes a product from the shoporswap.Storefront
     * @param productIn the product to remove
     * @param storefrontIn the storefront to remove the product from
     * @return the product removed from the system
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner shoporswap.Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct removeFromStorefront(AbstractProduct productIn, Storefront storefrontIn){
        AbstractProduct product = this.findInStorefront(productIn, storefrontIn);
        if(storefrontIn.getClass().getName().contains((CharSequence) "Sell")){
            SellStorefront sellStorefront = (SellStorefront) storefrontIn;
            return sellStorefront.removeProduct((SellProduct) product);
        }else{
            SwapStorefront swapStorefront = (SwapStorefront) storefrontIn;
            return swapStorefront.removeProduct((SwapProduct) product);
        }
    }

    /**
     * Removes a product from the shoporswap.Storefront
     * @param nameIn the name of the product to remove
     * @param storefrontIn the storefront to remove the product from
     * @return the product removed from the system
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner shoporswap.Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct removeFromStorefront(String nameIn, Storefront storefrontIn){
        AbstractProduct product = this.findInStorefront(nameIn, storefrontIn);
        return this.removeFromStorefront(product, storefrontIn);
    }

    /**
     * System process for buying products
     * @param productToBuy the product to buy
     * @param consumerIn the consumer buying the product
     */
    public void buyProduct(Storefront storefrontIn, SellProduct productToBuy, Client consumerIn){

        if(storefrontIn.getClass().getName().contains((CharSequence) "Sell")){
            double consumerWallet=consumerIn.getWallet();
            SellStorefront sellStorefront = (SellStorefront) storefrontIn;
            double productValue=sellStorefront.findProduct(productToBuy).getProductValue();
            if(consumerWallet-productValue<0){
                throw new IllegalArgumentException("insufficient amount of money to buy product in wallet");
            }
            AbstractProduct product = sellStorefront.completeTransaction((SellProduct) productToBuy, consumerIn);
        }else{
            throw new IllegalArgumentException("shoporswap.Storefront must be a selling storefront");
        }
    }

    /**
     * System process for swapping products
     * @param product1 one of the products to swap
     * @param product2 the other product to swap
     */
    public void swapProducts(Storefront storefront1, AbstractProduct product1, Storefront storefront2, AbstractProduct product2){
        if(storefront1.getClass().getName().contains("Swap") && storefront2.getClass().getName().contains("Swap")){
            SwapStorefront swapStorefront1 = (SwapStorefront) storefront1;
            SwapProduct swapProduct1 = swapStorefront1.findProduct((SwapProduct) product1);
            SwapStorefront swapStorefront2 = (SwapStorefront) storefront2;
            SwapProduct swapProduct2 = swapStorefront2.findProduct((SwapProduct) product2);
            swapStorefront1.completeTransaction((SwapProduct) product1, (SwapProduct) product2);
        }else {
            throw new IllegalArgumentException("Storefronts must be swapping storefronts");
        }
    }

    /**
     * System process for message communications
     * @param messageIn the message to send in the system
     */
    public AbstractMessage addMessage(AbstractMessage messageIn){
        this.getSystemMessages().add(messageIn);
        return this.findMessage(messageIn);
    }

    public AbstractMessage findMessage(AbstractMessage messageIn){
        for(AbstractMessage message : this.getSystemMessages()){
            if(message.getSender() == messageIn.getSender() && message.getSubject().compareTo(message.getSubject()) == 0 && message.getContent().compareTo(messageIn.getContent()) == 0){
                return message;
            }
        }
        throw new NoSuchElementException("No message can be found matching the criteria");
    }

    public List<AbstractMessage> findMessagesByRecipient(Account recipientIn){
        List<AbstractMessage> recipientMessages = new ArrayList<AbstractMessage>();
        for(AbstractMessage message : this.getSystemMessages()){
            if(message.getRecipient() == recipientIn){
                recipientMessages.add(message);
            }
        }
        return recipientMessages;
    }

    /**
     * Finds messages in the system by the account sending them
     * @param senderIn the account sending the messages
     * @return the messages sent by senderIn
     */
    public List<AbstractMessage> findMessagesBySender(Account senderIn){
        List<AbstractMessage> recipientMessages = new ArrayList<AbstractMessage>();
        for(AbstractMessage message : this.getSystemMessages()){
            if(message.getSender() == senderIn){
                recipientMessages.add(message);
            }
        }
        return recipientMessages;
    }

    /**
     * System process for general messaging between clients
     * @param typeIn the type of message ("User" or "Report")
     * @param senderNameIn the sender of the message
     * @param receiverNameIn the recipient of the message
     * @param subjectIn the subject of the message
     * @param contentIn the content of the message
     * @throws IllegalArgumentException if the type of message is invalid
     * @throws NoSuchElementException if the sender or the recipient does not exist
     * @throws IllegalArgumentException if the message subject or the message content is invalid
     */
    public void sendMessage(String typeIn, String senderNameIn, String receiverNameIn, String subjectIn, String contentIn){
        AbstractMessage message;
        if(typeIn.compareToIgnoreCase("User") == 0 && !receiverNameIn.isEmpty()){
            message = messageFactory.getMessage(typeIn);
            message.setSender(this.findAccount(senderNameIn));
            message.setRecipient(this.findAccount(receiverNameIn));
            message.setSubject(subjectIn);
            message.setContent(contentIn);
            this.addMessage(message);
        }else{
            if(!receiverNameIn.isEmpty()) {
                for (Account account : this.getAccountCollection().values()) {
                    if (account.getClass().getName().compareToIgnoreCase("shoporswap.Admin") == 0) {
                        message = messageFactory.getMessage(typeIn);
                        message.setSender(this.findAccount(senderNameIn));
                        message.setRecipient(this.findAccount(account));
                        if(!subjectIn.isEmpty()) {
                            message.setSubject(subjectIn);
                        }
                        message.setContent(contentIn);
                        ((ReportMessage) message).setReportedAccount(this.findAccount(receiverNameIn));
                        this.addMessage(message);
                    }
                }
            }else{
                throw new IllegalArgumentException("Report recipient must not be empty");
            }
        }
    }

    /**
     * System process for freezing accounts
     * @param freezerIn the account doing the freezing
     * @param toFreezeIn the account being frozen
     * @throws IllegalArgumentException if freezerIn is not an shoporswap.Admin
     * @throws IllegalArgumentException if freezerIn and toFreezeIn are the same shoporswap.Admin account
     */
    public void freezeAccount(Account freezerIn, Account toFreezeIn){
        if(!freezerIn.getClass().getName().contains("Admin") || freezerIn == toFreezeIn){
            throw new IllegalArgumentException("Clients cannot freeze accounts");
        }
        this.findAccount(toFreezeIn).setIsFrozen(true);
    }

    /**
     * System process for unfreezing accounts
     * @param unfreezerIn the account doing the unfreezing
     * @param toUnFreezeIn the account being unfrozen
     * @throws IllegalArgumentException if unfreezerIn is not an shoporswap.Admin
     */
    public void unfreezeAccount(Account unfreezerIn, Account toUnFreezeIn){
        if(!unfreezerIn.getClass().getName().contains("Admin")|| unfreezerIn == toUnFreezeIn){
            throw new IllegalArgumentException("Clients cannot unfreeze accounts");
        }
        this.findAccount(toUnFreezeIn).setIsFrozen(false);
    }

    /**
     * Collects the data from the Map of Account values by String keys and creates a list of these records which can be exported to JSON
     * @return the List of AccountRecord objects created from the Map of Account values by String keys
     */
    public List<AccountRecord> exportAccounts(){
        List<AccountRecord> accountRecordsOut = new ArrayList<AccountRecord>();
        for(Account account : this.getAccountCollection().values()){
            AccountRecord nextAccountRecord = new AccountRecord(account);
            accountRecordsOut.add(nextAccountRecord);
        }
        return accountRecordsOut;
    }

    /**
     * Adds a tag into the system
     * @param labelIn the label of the tag
     * @return the new tag
     * @throws IllegalArgumentException if a tag with the label already exists or if labelIn is invalid
     */
    public Tag addTag(String labelIn){
        if(this.getSystemTags().containsKey(labelIn)){
            throw new IllegalArgumentException("Tag already exists in system");
        }
        Tag tag = new Tag(labelIn);
        this.getSystemTags().put(labelIn, tag);
        return this.getSystemTags().get(labelIn);
    }

    /**
     * Finds a tag in the system
     * @param labelIn the label of the tag
     * @return the tag found
     * @throws NoSuchElementException if a tag with the label does not exist in the system
     */
    public Tag findTag(String labelIn){
        if(!this.getSystemTags().containsKey(labelIn)){
            throw new NoSuchElementException("Tag with desired label does not exist in the system");
        }
        return this.getSystemTags().get(labelIn);
    }

    /**
     * Removes a tag from the system
     * @param labelIn the label of the tag
     * @return the tag found
     * @throws NoSuchElementException if a tag with the label does not exist in the system
     */
    public Tag removeTag(String labelIn){
        if(!this.getSystemTags().containsKey(labelIn)){
            throw new NoSuchElementException("Tag with desired label does not exist in the system");
        }
        return this.getSystemTags().remove(labelIn);
    }


    public void addTagToProduct(String tagLabelIn, AbstractProduct productIn){
        if(!this.getSystemTags().containsKey(tagLabelIn)){
            this.getSystemTags().put(tagLabelIn, new Tag(tagLabelIn));
        }
        productIn.addTag(this.getSystemTags().get(tagLabelIn));
        this.getSystemTags().get(tagLabelIn).addProduct(productIn);
    }

    /**
     * Finds a list of products with the tag
     * @param tagLabelIn the tag to find the labelled products of
     * @return the list of products with the tag
     * @throws NoSuchElementException if a tag with the label does not exist in the system
     */
    public List<AbstractProduct> findProductsByTag(String tagLabelIn){
        return this.findTag(tagLabelIn).accessProducts();
    }

    /**
     * Removes a tag from a product
     * @param tagLabelIn the tag label to remove
     * @param productIn the product to remove the tag from
     * @throws NoSuchElementException if the product is not tagged with a tag with the label
     */
    public void removeTagFromProduct(String tagLabelIn, AbstractProduct productIn){
        productIn.removeTag(this.findTag(tagLabelIn));
        this.findTag(tagLabelIn).accessProducts().remove(productIn);
    }

    /**
     * Indirect accessor method for the AccountFactory of the system
     * @return the AccountFactory of the system
     */
    public AccountFactory accessAccountFactory(){
        return this.accountFactory;
    }

    /**
     * Indirect accessor method for the ProductFactory of the system
     * @return the ProductFactory of the system
     */
    public AbstractProductFactory accessProductFactory(){
        return this.productFactory;
    }

    /**
     * Indirect accessor method for the MessageFactory of the system
     * @return the MessageFactory of the system
     */
    public AbstractMessageFactory accessMessageFactory(){
        return this.messageFactory;
    }

    /**
     * Indirect accessor method for the StorefrontFactory of the system
     * @return the StorefrontFactory of the system
     */
    public StorefrontFactory accessStorefrontFactory(){
        return this.storefrontFactory;
    }

    /**
     * Accessor method for the accounts in the system
     * @return a Map of the accounts in the system
     */
    public Map<String, Account> getAccountCollection(){
        return this.accountCollection;
    }

    /**
     * Indirect mutator method for the account Map for the system
     * @param accountCollectionIn the desired account Map
     */
    public void establishAccountCollection(Map<String, Account> accountCollectionIn){
        this.accountCollection = accountCollectionIn;
    }

    /**
     * Indirect mutator method for the AccountFactory for the system
     * @param accountFactoryIn the desired AccountFactory
     */
    public void establishAccountFactory(AccountFactory accountFactoryIn){
        this.accountFactory = accountFactoryIn;
    }

    /**
     * Indirect mutator method for the AbstractProductFactory for the system
     * @param abstractProductFactoryIn the desired AbstractProductFactory
     */
    public void establishProductFactory(AbstractProductFactory abstractProductFactoryIn){
        this.productFactory = abstractProductFactoryIn;
    }

    /**
     * Indirect mutator method for the AbstractMessageFactory for the system
     * @param abstractMessageFactoryIn the desired AbstractMessageFactory
     */
    public void establishMessageFactory(AbstractMessageFactory abstractMessageFactoryIn){
        this.messageFactory = abstractMessageFactoryIn;
    }

    /**
     * Indirect mutator method for the StorefrontFactory for the system
     * @param storefrontFactoryIn the desired StorefrontFactory
     */
    public void establishStorefrontFactory(StorefrontFactory storefrontFactoryIn){
        this.storefrontFactory = storefrontFactoryIn;
    }

    /**
     * Accessor method for the messages in the system
     * @return the List of messages in the system
     */
    public List<AbstractMessage> getSystemMessages() {
        return systemMessages;
    }

    /**
     * Mutator method for the messages in the system
     * @param systemMessages the desired List of messages
     */
    public void setSystemMessages(List<AbstractMessage> systemMessages) {
        this.systemMessages = systemMessages;
    }

    public Map<String, Tag> getSystemTags() {
        return this.systemTags;
    }

    public void setSystemTags(Map<String, Tag> systemTagsIn) {
        this.systemTags = systemTagsIn;
    }
}
