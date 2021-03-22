package model;

//import java.io.Serializable;

public class Employee extends Person { //implements Serializable {

    /**
     * Standard version 1.0 
     * Contains de basic features for an employee element
     */
    private static final long serialVersionUID = -4382153301499840256L;

    public Employee(String name, String lastName, String idNumber, String createdBy, String modifiedBy) {
        super(name, lastName, idNumber, createdBy, modifiedBy);
    }

    // --------------- compare

    public int compareById(String idNumber){
        return getIdNumber().compareTo(idNumber);
    }
}
