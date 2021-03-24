package model;

public class Client extends Person { 
     /**
     * Standard version 1.0 
     * Contains de basic features for an client element
     */
    private static final long serialVersionUID = 7708175366329555125L;
   
    private String address;
    private String phone;
    private String observations;
    private String fullName;

    public Client(String name, String lastName, String idNumber, String createdBy, String modifiedBy, String address,
                  String phone, String observations) {
        super(name, lastName, idNumber, createdBy, modifiedBy);
        this.address = address;
        this.phone = phone;
        this.observations = observations;
        fullName = name +  " " +lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getFullName() {
        return fullName;
    }

    // -------------------- compare

    public int compareByLastName(Client other) {
        return getLastName().compareTo(other.getLastName());
    }

    public int compareByName(Client other) {
        return getName().compareTo(other.getName());
    }

    public int compareByLastName(String other) {
        return getLastName().compareTo(other);
    }

    public int compareByName(String other) {
        return getName().compareTo(other);
    }

    public int compareByFullName(Client other) {
        return fullName.compareTo(other.getFullName());
    }

    public int compareByFullNameString(String otherFulName) {
        return fullName.compareTo(otherFulName);
    }

    public int compareById(Client other) {
        return getIdNumber().compareTo(other.getIdNumber());
    }
}
