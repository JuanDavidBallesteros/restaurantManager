package model;

public class Employee extends Person {

    public Employee(String name, String lastName, String idNumber, String createdBy, String modifiedBy) {
        super(name, lastName, idNumber, createdBy, modifiedBy);
    }

    // --------------- compare

    public int compareById(String idNumber){
        return idNumber.compareTo(idNumber);
    }
}
