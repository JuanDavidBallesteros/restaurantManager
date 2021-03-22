package model;

import java.io.Serializable;

public abstract class Person implements Serializable {
    /**
     * Standard version 1.0 
     * Contains de basic features for a person element
     */
    private static final long serialVersionUID = 4154816871133299828L;

    private String name;
    private String lastName;
    private String idNumber;
    private String createdBy;
    private String modifiedBy;

    public Person(String name, String lastName, String idNumber, String createdBy, String modifiedBy) {
        this.name = name;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
