package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    /**
     * Standard version 1.0 
     * Contains de basic features for an Order element
     */
    private static final long serialVersionUID = 5935813510141312573L;
    
    private String id;
    private String state;
    private List<Product> products;
    //private List<Integer> productsQuantity;
    private Client client;
    private Employee employee;
    private Date deliveryDate;
    private String observations;
    private String createdBy;
    private String modifiedBy;
    private String dateTxt;

    public Order(String id, int stateNum, List<Product> products,  Client client,
                 Employee employee, Date deliveryDate, String observations, String createdBy, String modifiedBy) {
        this.id = id;
        state = OrderState.values()[stateNum].name();
        this.products = products;
        //this.productsQuantity = productsQuantity;
        this.client = client;
        this.employee = employee;
        this.deliveryDate = deliveryDate;
        this.observations = observations;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        dateToTxt();
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

    public void setState(int stateNum) {
        this.state = OrderState.values()[stateNum].name();
    }

    public List<Product> getProducts() {
        return products;
    }

    /* public List<Integer> getProductsQuantity() {
        return productsQuantity;
    } */

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

    public long getAmount() {
        long amount = 0;
        for (int i = 0; i < products.size(); i++) {
            amount += (products.get(i).getPrice());
        }
        return amount;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getDateTxt() {
        return dateTxt;
    }

    /* public void setProductsQuantity(List<Integer> productsQuantity) {
        this.productsQuantity = productsQuantity;
    }
 */

    public void dateToTxt(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        dateTxt = formatter.format(deliveryDate);
    }
    
}
