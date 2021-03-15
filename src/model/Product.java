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
    
    public Product(String name, String type, List<Ingredient> ingedients, String size, Double price, String createdBy,
            String modifiedBy) {
        this.name = name;
        this.type = type;
        ingedients = new ArrayList<>();
        this.size = size;
        this.price = price;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
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

    public List<Ingredient> getIngedients() {
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

    
}
