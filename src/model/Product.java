package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Comparable<Product>, Serializable {
    
    /**
     * Standard version 1.0 
     * Contains de basic features for an Product element
     */
    private static final long serialVersionUID = 3042400769680388411L;

    private String name;
    private String type;
    private List<Ingredient> ingredients;
    private String size;
    private Double price;
    private String createdBy;
    private String modifiedBy;
    private boolean isAvailable;
    private String id;

    public Product(String id, String name, int typeNum, List<Ingredient> ingredients, int sizeNum, Double price, String createdBy,
                   String modifiedBy) {
        this.name = name;
        type = ProductType.values()[typeNum].name();
        ingredients = new ArrayList<>();
        size = ProductSize.values()[sizeNum].name();
        this.price = price;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        isAvailable = true;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    // --------------- compare

    public int compareById(String id) {
        return id.compareTo(id);
    }

    @Override
    public int compareTo(Product other) {
        int value = 0;
        if ((price - other.getPrice()) < 0) {
            value = -1;
        } else if ((price - other.getPrice()) > 0) {
            value = 1;
        } else {
            value = 0;
        }
        return value;
    }

}
