package model;

public class Client extends Person {
    private String address;
    private int phone;
    private String observations;
    
    public Client(String name, String lastName, String idNumber, String address, int phone, String observations) {
        super(name, lastName, idNumber, observations, observations);
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    
}
