package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private String state;
    private List<Product> products;
    private List<Integer> poductsQuantity;
    private Client client;
    private Employee employee;
    private Date deliveryDate;
    private String observations;
    private String createdBy;
    private String modifiedBy;

    public Order(String id, int stateNum, List<Product> products, List<Integer> poductsQuantity, Client client,
            Employee employee, Date deliveryDate, String observations, String createdBy, String modifiedBy) {
        this.id = id;
        state = OrderState.values()[stateNum].name();
        products = new ArrayList<>();
        poductsQuantity = new ArrayList<>();
        this.client = client;
        this.employee = employee;
        this.deliveryDate = deliveryDate;
        this.observations = observations;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Integer> getPoductsQuantity() {
        return poductsQuantity;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
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

    
}
