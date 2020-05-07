package io;

import shoporswap.*;

import java.util.ArrayList;
import java.util.List;

public class StorefrontRecord {

    private String storefrontName;
    private List<SellProduct> sellProductList;
    private List<SwapProduct> swapProductList;

    public StorefrontRecord(){
        this.setStorefrontName("DEFAULT NAME");
        this.setSellProductList(null);
        this.setSwapProductList(null);
    }

    public StorefrontRecord(Storefront storefrontIn){
        this.setStorefrontName(storefrontIn.getStorefrontName());
        if(storefrontIn instanceof SellStorefront){
            this.setSellProductList(((SellStorefront) storefrontIn).getSellProducts());
            this.setSwapProductList(null);
        }else{
            this.setSwapProductList(((SwapStorefront) storefrontIn).getSwapProducts());
            this.setSellProductList(null);
        }
    }

    public List<SellProduct> getSellProductList() {
        return this.sellProductList;
    }

    public void setSellProductList(List<SellProduct> sellProductListIn) {
        this.sellProductList = sellProductListIn;
    }

    public List<SwapProduct> getSwapProductList() {
        return this.swapProductList;
    }

    public void setSwapProductList(List<SwapProduct> swapProductListIn) {
        this.swapProductList = swapProductListIn;
    }

    public String getStorefrontName() {
        return this.storefrontName;
    }

    public void setStorefrontName(String storefrontNameIn) {
        this.storefrontName = storefrontNameIn;
    }
}
