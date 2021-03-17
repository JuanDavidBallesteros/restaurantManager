package model;

public class Ingredient {
    private String name;
    private String type;
    private String createdBy;
    private String modifiedBy;
    private boolean isAvilable;
    private String id;
    
    public Ingredient(String id, String name, int typeNum, String createdBy, String modifiedBy) {
        this.name = name;
        type = IngredienteType.values()[typeNum].name();
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
        this.type = IngredienteType.values()[typeNum].name();
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
