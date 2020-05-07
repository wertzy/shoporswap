package io;

import shoporswap.AbstractProduct;
import shoporswap.Tag;

import java.util.List;

public class ProductRecord {
    private String productName;
    private String productDescription;
    private double productValue;
    private List<Tag> productTags;

    public ProductRecord(){
        this.setProductName("DEFAULT NAME");
        this.setProductDescription("DEFAULT DESCRIPTION");
        this.setProductValue(0.0);
        this.setProductTags(null);
    }

    public ProductRecord(AbstractProduct productIn){
        this.setProductName(productIn.getProductName());
        this.setProductDescription(productIn.getProductDescription());
        this.setProductValue(productIn.getProductValue());
        this.setProductTags(productIn.getProductTags());
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
}
