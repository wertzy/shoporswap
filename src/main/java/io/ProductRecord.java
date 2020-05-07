package io;

import shoporswap.AbstractProduct;
import shoporswap.SellProduct;
import shoporswap.SwapProduct;
import shoporswap.Tag;

import java.util.List;

public class ProductRecord {
    private String productType;
    private String productName;
    private String productDescription;
    private double productValue;
    private List<Tag> productTags;

    public ProductRecord(){
        this.setProductType(null);
        this.setProductName("DEFAULT NAME");
        this.setProductDescription("DEFAULT DESCRIPTION");
        this.setProductValue(0.0);
        this.setProductTags(null);
    }

    public ProductRecord(AbstractProduct productIn){
        this.setProductType(productIn.getClass().getName());
        this.setProductName(productIn.getProductName());
        this.setProductDescription(productIn.getProductDescription());
        this.setProductValue(productIn.getProductValue());
        this.setProductTags(productIn.getProductTags());
    }

    public AbstractProduct toProduct(){
        AbstractProduct productOut;
        if(this.getProductType().compareToIgnoreCase(SellProduct.class.getName()) == 0){
            productOut = new SellProduct();
        }else{
            productOut = new SwapProduct();
        }
        productOut.setProductName(this.getProductName());
        productOut.setProductDescription(this.getProductDescription());
        productOut.setProductValue(this.getProductValue());
        productOut.setProductMerchant(null);
        productOut.setProductTags(this.getProductTags());
        return productOut;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productNameIn) {
        this.productName = productNameIn;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public void setProductDescription(String productDescriptionIn) {
        this.productDescription = productDescriptionIn;
    }

    public double getProductValue() {
        return this.productValue;
    }

    public void setProductValue(double productValueIn) {
        this.productValue = productValueIn;
    }

    public List<Tag> getProductTags() {
        return this.productTags;
    }

    public void setProductTags(List<Tag> productTagsIn) {
        this.productTags = productTagsIn;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
