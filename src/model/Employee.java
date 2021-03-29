package model;

//import java.io.Serializable;

public class Employee extends Person { //implements Serializable {

    /**
     * Standard version 1.0 
     * Contains de basic features for an employee element
     */
    private static final long serialVersionUID = -4382153301499840256L;

    private String fullname;

    public Employee(String name, String lastName, String idNumber, String createdBy, String modifiedBy) {
        super(name, lastName, idNumber, createdBy, modifiedBy);

        fullname = name + " " + lastName;
    }

    // --------------- compare

    public int compareById(String idNumber){
        return getIdNumber().compareTo(idNumber);
    }

    public int compareByFullname(String other){
        return fullname.compareTo(other);
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    
}
