package io;

import shoporswap.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopOrSwapRecord {

    private List<AccountRecord> accountRecords;
    private List<MessageRecord> messageRecords;


    public ShopOrSwapRecord(){
        this.setAccountRecords(null);
        this.setMessageRecords(null);
    }

    public ShopOrSwapRecord(ShopOrSwap shopOrSwapIn){
        this.setAccountRecords(this.toAccountRecordList(shopOrSwapIn.getAccountCollection()));
        this.setMessageRecords(this.toMessageRecordList(shopOrSwapIn.getSystemMessages()));
    }

    public List<AccountRecord> toAccountRecordList(Map<String, Account> accountMapIn){
        List<AccountRecord> accountRecordsOut = new ArrayList<AccountRecord>();
        for(Account account : accountMapIn.values()){
            accountRecordsOut.add(new AccountRecord(account));
        }
        return accountRecordsOut;
    }

    public List<MessageRecord> toMessageRecordList(List<AbstractMessage> messageListIn){
        List<MessageRecord> messageRecordsOut = new ArrayList<MessageRecord>();
        for(AbstractMessage message : messageListIn){
            messageRecordsOut.add(new MessageRecord(message));
        }
        return messageRecordsOut;
    }


    public ShopOrSwap toShopOrSwap() {
        ShopOrSwap shopOrSwapOut = new ShopOrSwap();
        Map<String, Account> accountMapIn = new HashMap<String, Account>();
        for(AccountRecord accountRecord : this.getAccountRecords()){
            if(accountMapIn.containsKey(accountRecord.getAccountName())){
                throw new IllegalArgumentException("Cannot add multiple records with the same account name into the system");
            }
            accountMapIn.put(accountRecord.getAccountName(), accountRecord.toAccount());
        }
        List<AbstractMessage> messageListIn = new ArrayList<AbstractMessage>();
        for(MessageRecord message : this.getMessageRecords()){
            messageListIn.add(message.toMessage());
        }
        shopOrSwapOut.establishAccountCollection(accountMapIn);
        shopOrSwapOut.setSystemMessages(messageListIn);
        shopOrSwapOut.setSystemTags(new HashMap<String, Tag>());
        for(Account account : shopOrSwapOut.getAccountCollection().values()){
            if(account instanceof Client){
                ((Client) account).setMyStorefronts(new HashMap<String, Storefront>());
                for(Storefront storefront : ((Client) account).getMyStorefronts().values()){
                    for(AbstractProduct product : storefront.getStorefrontProducts()){
                        for(Tag tag : product.getProductTags()){
                            shopOrSwapOut.getSystemTags().put(tag.getName(), tag);
                        }
                    }
                }
            }
        }
        return shopOrSwapOut;
    }

    public List<AccountRecord> getAccountRecords() {
        return this.accountRecords;
    }

    public void setAccountRecords(List<AccountRecord> accountRecordsIn) {
        this.accountRecords = accountRecordsIn;
    }

    public List<MessageRecord> getMessageRecords() {
        return this.messageRecords;
    }

    public void setMessageRecords(List<MessageRecord> messageRecordsIn) {
        this.messageRecords = messageRecordsIn;
    }

}
