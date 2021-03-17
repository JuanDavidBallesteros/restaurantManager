package model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private String type;
    private List<Ingredient> ingedients;
    private String size;
    private Double price;
    private String createdBy;
    private String modifiedBy;
    private boolean isAvilable;
    private String id;
    
    public Product(String id, String name, int typeNum, List<Ingredient> ingedients, int sizeNum, Double price, String createdBy,
            String modifiedBy) {
        this.name = name;
        type = ProductType.values()[typeNum].name();
        ingedients = new ArrayList<>();
        size = ProductSize.values()[sizeNum].name();
        this.price = price;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        isAvilable = true;
        this.id = id;
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

    public void setType(int typeNum) {
        this.type = ProductType.values()[typeNum].name();
    }

    public List<Ingredient> getIngedients() {
        return ingedients;
    }

    public String getSize() {
        return size;
    }

    public void setSize(int sizeNum) {
        this.size = ProductSize.values()[sizeNum].name();
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

    public String getId() {
        return id;
    }
    
    // --------------- compare

    public int compareById(String id){
        return id.compareTo(id);
    }
}
