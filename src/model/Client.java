package model;

public class Client extends Person {
    private String address;
    private String phone;
    private String observations;

    public Client(String name, String lastName, String idNumber, String createdBy, String modifiedBy, String address,
                  String phone, String observations) {
        super(name, lastName, idNumber, createdBy, modifiedBy);
        this.address = address;
        this.phone = phone;
        this.observations = observations;
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
}
