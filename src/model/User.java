package model;

import java.io.Serializable;

public class User implements Serializable {

     /**
     * Standard version 1.0 
     * Contains de basic features for an user element
     */
    private static final long serialVersionUID = 5614535401457398882L;

    private Employee employee;
    private String userName;
    private String userPassword;
    private String createdBy;
    private String modifiedBy;

    public User(Employee employee, String userName, String userPassword, String createdBy, String modifiedBy) {
        this.employee = employee;
        this.userName = userName;
        this.userPassword = userPassword;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public User(Employee employee, String userName, String userPassword) {
        this.employee = employee;
        this.userName = userName;
        this.userPassword = userPassword;
        createdBy = userName;
        modifiedBy = userName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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

    // --------------- compare

    public int compareByUserName(String userName) {
        return this.userName.compareTo(userName);
    }
}
