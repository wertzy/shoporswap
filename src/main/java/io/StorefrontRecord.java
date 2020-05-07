package io;

import shoporswap.*;

import java.util.ArrayList;
import java.util.List;

public class StorefrontRecord {

    private String storefrontType;
    private String storefrontName;

    private List<SellProduct> sellProductList;
    private List<SwapProduct> swapProductList;

    private List<ProductRecord> productRecordList;

    public StorefrontRecord(){
        this.setStorefrontType(null);
        this.setStorefrontName("DEFAULT NAME");
        this.establishSellProductList(null);
        this.establishSwapProductList(null);
        this.setProductRecordList(null);
    }

    public StorefrontRecord(Storefront storefrontIn){
        this.setStorefrontType(storefrontIn.getClass().getName());
        this.setStorefrontName(storefrontIn.getStorefrontName());
        if(storefrontIn instanceof SellStorefront){
            this.establishSellProductList(((SellStorefront) storefrontIn).getSellProducts());
            this.establishSwapProductList(null);
        }else{
            this.establishSwapProductList(((SwapStorefront) storefrontIn).getSwapProducts());
            this.establishSellProductList(null);
        }
        this.setProductRecordList(this.toProductRecordList());
    }

    public List<ProductRecord> toProductRecordList(){
        List<ProductRecord> productRecordsOut = new ArrayList<ProductRecord>();
        if(this.accessSellProductList() == null){
            for(SwapProduct product : this.accessSwapProductList()){
                productRecordsOut.add(new ProductRecord(product));
            }
        }else{
            for(SellProduct product : this.accessSellProductList()){
                productRecordsOut.add(new ProductRecord(product));
            }
        }
        return productRecordsOut;
    }

    public List<SellProduct> accessSellProductList() {
        return this.sellProductList;
    }

    public void establishSellProductList(List<SellProduct> sellProductListIn) {
        this.sellProductList = sellProductListIn;
    }

    public List<SwapProduct> accessSwapProductList() {
        return this.swapProductList;
    }

    public void establishSwapProductList(List<SwapProduct> swapProductListIn) {
        this.swapProductList = swapProductListIn;
    }

    public String getStorefrontName() {
        return this.storefrontName;
    }

    public void setStorefrontName(String storefrontNameIn) {
        this.storefrontName = storefrontNameIn;
    }

    public List<ProductRecord> getProductRecordList(){
        return this.productRecordList;
    }

    public void setProductRecordList(List<ProductRecord> productRecordListIn) {
        this.productRecordList = productRecordListIn;
    }

    public String getStorefrontType() {
        return this.storefrontType;
    }

    public void setStorefrontType(String storefrontTypeIn) {
        this.storefrontType = storefrontTypeIn;
    }
}
