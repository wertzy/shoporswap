package shoporswap;

import java.util.*;

public class ShopOrSwap {

    private AbstractMessageFactory messageFactory;
    private AccountFactory accountFactory;
    private StorefrontFactory storefrontFactory;
    private AbstractProductFactory productFactory;
    private Map<String, Account> accountCollection;
    private List<AbstractMessage> systemMessages;

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
    }

    /**
     * Adds an shoporswap.Account to the shoporswap.ShopOrSwap system
     * @param accountIn
     * @return
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
     * @param typeIn
     * @param nameIn
     * @param passwordIn
     * @return
     */
    public Account addAccount(String typeIn, String nameIn, String passwordIn){
        Account account = this.accessAccountFactory().getAccount(typeIn);
        account.setAccountName(nameIn);
        account.setAccountPassword(passwordIn);
        return this.addAccount(account);
    }

    /**
     * Finds an shoporswap.Account in the shoporswap.ShopOrSwap system
     * @param accountIn
     * @return
     * @throws NoSuchElementException if accountIn does not exist in the system
     */
    public Account findAccount(Account accountIn){
        return this.findAccount(accountIn.getAccountName());
    }

    /**
     * Finds an shoporswap.Account in the shoporswap.ShopOrSwap system
     * @param nameIn
     * @return
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
     * @param accountIn
     * @return
     */
    public Account removeAccount(Account accountIn){
        Account account = this.findAccount(accountIn);
        this.getAccountCollection().remove(accountIn.getAccountName());
        return account;
    }

    /**
     * Removes an shoporswap.Account from the shoporswap.ShopOrSwap system
     * @param nameIn
     * @return
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
     * @return
     * @throws IllegalArgumentException if nameIn is invalid for shoporswap.Storefront name
     * @throws NoSuchElementException if the ownerIn is not a member of the System
     * @throws IllegalArgumentException if a shoporswap.Storefront already exists for ownerIn with the name of nameIn
     */
    public Storefront addStorefront(String typeIn, String nameIn, Client ownerIn){
        Account owner = this.findAccount(ownerIn);
        Storefront storefront = this.storefrontFactory.getStorefront(typeIn);
        storefront.setStorefrontName(nameIn);
        storefront.establishStorefrontOwner(ownerIn);
        ownerIn.addStorefront(storefront);
        return storefront;
    }

    /**
     * Finds a shoporswap.Storefront in the shoporswap.ShopOrSwap system
     * @param nameIn the name of the shoporswap.Storefront
     * @param ownerIn the owner of the shoporswap.Storefront
     * @return
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
     * @return
     * @throws NoSuchElementException if the shoporswap.Storefront cannot be found
     */
    public Storefront removeStorefront(String nameIn, Client ownerIn){
        Storefront storefront = this.findStorefront(nameIn, ownerIn);
        ownerIn.getMyStorefronts().remove(nameIn);
        return storefront;
    }

    /**
     * Adds a product to a shoporswap.Storefront
     * @param nameIn
     * @param descriptionIn
     * @param valueIn
     * @param storefrontIn
     * @return
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
     * @param productIn
     * @param storefrontIn
     * @return
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
     * @param productIn
     * @param storefrontIn
     * @return
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
     * @param nameIn
     * @param storefrontIn
     * @return
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
     * @param productIn
     * @param storefrontIn
     * @return
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
     * @param nameIn
     * @param storefrontIn
     * @return
     * @throws NoSuchElementException if productIn does not exist in storefrontIn (if owner shoporswap.Client of storefrontIn does not own the instance of productIn)
     */
    public AbstractProduct removeFromStorefront(String nameIn, Storefront storefrontIn){
        AbstractProduct product = this.findInStorefront(nameIn, storefrontIn);
        return this.removeFromStorefront(product, storefrontIn);
    }

    /**
     * System process for buying products
     * @param productToBuy
     * @param consumerIn
     */
    public void buyProduct(Storefront storefrontIn, AbstractProduct productToBuy, Client consumerIn){
        if(storefrontIn.getClass().getName().contains((CharSequence) "Sell")){
            SellStorefront sellStorefront = (SellStorefront) storefrontIn;
            AbstractProduct product = sellStorefront.completeTransaction((SellProduct) productToBuy, consumerIn);
        }else{
            throw new IllegalArgumentException("shoporswap.Storefront must be a selling storefront");
        }
    }

    /**
     * System process for swapping products
     * @param product1
     * @param product2
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
     * @param messageIn
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
     * @param typeIn
     * @param receiverNameIn
     * @param subjectIn
     * @param contentIn
     * @throws IllegalArgumentException if the type of message is invalid
     * @throws NoSuchElementException if the sender or the recipient does not exist
     * @throws IllegalArgumentException if the message subject or the message content is invalid
     */
    public void sendMessage(String typeIn, String senderNameIn, String receiverNameIn, String subjectIn, String contentIn){
        AbstractMessage message = messageFactory.getMessage(typeIn);
        if(typeIn.compareToIgnoreCase("User") == 0 && !receiverNameIn.isEmpty()){
            message.setSender(this.findAccount(senderNameIn));
            message.setRecipient(this.findAccount(receiverNameIn));
            message.setSubject(subjectIn);
            message.setContent(contentIn);
            this.addMessage(message);
        }else{
            if(receiverNameIn.isEmpty()) {
                for (Account account : this.getAccountCollection().values()) {
                    if (account.getClass().getName().compareToIgnoreCase("shoporswap.Admin") == 0) {
                        message.setSender(this.findAccount(senderNameIn));
                        message.setRecipient(account);
                        message.setSubject(subjectIn);
                        message.setContent(contentIn);
                        this.addMessage(message);
                    }
                }
            }else{
                throw new IllegalArgumentException("Report recipient must be empty");
            }
        }
    }

    /**
     * System process for freezing accounts
     * @param freezerIn
     * @param toFreezeIn
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
     * @param unfreezerIn
     * @param toUnFreezeIn
     * @throws IllegalArgumentException if unfreezerIn is not an shoporswap.Admin
     */
    public void unfreezeAccount(Account unfreezerIn, Account toUnFreezeIn){
        if(!unfreezerIn.getClass().getName().contains("Admin")|| unfreezerIn == toUnFreezeIn){
            throw new IllegalArgumentException("Clients cannot unfreeze accounts");
        }
        this.findAccount(toUnFreezeIn).setIsFrozen(false);
    }

    public AccountFactory accessAccountFactory(){
        return this.accountFactory;
    }

    public AbstractProductFactory accessProductFactory(){
        return this.productFactory;
    }

    public AbstractMessageFactory accessMessageFactory(){
        return this.messageFactory;
    }

    public StorefrontFactory accessStorefrontFactory(){
        return this.storefrontFactory;
    }

    public Map<String, Account> getAccountCollection(){
        return this.accountCollection;
    }

    public void establishAccountCollection(Map<String, Account> accountCollectionIn){
        this.accountCollection = accountCollectionIn;
    }

    public void establishAccountFactory(AccountFactory accountFactoryIn){
        this.accountFactory = accountFactoryIn;
    }

    public void establishProductFactory(AbstractProductFactory abstractProductFactoryIn){
        this.productFactory = abstractProductFactoryIn;
    }

    public void establishMessageFactory(AbstractMessageFactory abstractMessageFactoryIn){
        this.messageFactory = abstractMessageFactoryIn;
    }

    public void establishStorefrontFactory(StorefrontFactory storefrontFactoryIn){
        this.storefrontFactory = storefrontFactoryIn;
    }

    public List<AbstractMessage> getSystemMessages() {
        return systemMessages;
    }

    public void setSystemMessages(List<AbstractMessage> systemMessages) {
        this.systemMessages = systemMessages;
    }
}
