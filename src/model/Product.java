package model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private String type;
    private List<String> ingedients;
    private String size;
    private Double price;
    private String createdBy;
    private String modifiedBy;
    private boolean isAvilable;
    
    public Product(String name, int typeNum, List<String> ingedients, int sizeNum, Double price, String createdBy,
            String modifiedBy) {
        this.name = name;
        type = ProductType.values()[typeNum].name();
        ingedients = new ArrayList<>();
        size = productSize.values()[sizeNum].name();
        this.price = price;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        isAvilable = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getIngedients() {
        return ingedients;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public boolean isAvilable() {
        return isAvilable;
    }

    public void setAvilable(boolean isAvilable) {
        this.isAvilable = isAvilable;
    }

    
}
