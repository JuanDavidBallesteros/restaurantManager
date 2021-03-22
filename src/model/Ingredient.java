package model;

import java.io.Serializable;

public class Ingredient implements Comparable<Ingredient>, Serializable {
    
    /**
     * Standard version 1.0 
     * Contains de basic features for an Ingredient element
     */
    private static final long serialVersionUID = 7918050140709892827L;

    private String name;
    private String type;
    private String createdBy;
    private String modifiedBy;
    private boolean isAvailable;
    private String id;

    public Ingredient(String id, String name, int typeNum, String createdBy, String modifiedBy) {
        this.name = name;
        type = IngredientType.values()[typeNum].name();
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
        this.type = IngredientType.values()[typeNum].name();
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
        return this.id.compareTo(id);
    }

    @Override
    public int compareTo(Ingredient other) {
        return name.compareTo(other.getName());
    }
}
