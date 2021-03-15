package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestaurantSystem {
    private List<Client> clients;
    private List<Employee> employees;
    private List<User> users;
    private List<Product> products;
    private List<Ingredient> ingredients;
    private List<Order> orders;
    private Date actualDate;
    private String actualUser;

    public RestaurantSystem(String actualUser) {
        clients = new ArrayList<>(); 
        employees = new ArrayList<>(); 
        users = new ArrayList<>(); 
        products = new ArrayList<>(); 
        ingredients = new ArrayList<>(); 
        orders = new ArrayList<>(); 
        this.actualUser = actualUser;
    }

    // -------------------- Getters an setters

    public List<Client> getClients() {
        return clients;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Date getActualDate() {
        return actualDate;
    }

    // -------------------- adds

    public void addProduct(String name, int typeNum, List<Ingredient> ingedients, int sizeNum, Double price) {
        Product temp = new Product(name, typeNum, ingedients, sizeNum, price, actualUser, actualUser);
        products.add(temp);
    }

    public void addIngredient(String name, int typeNum) {
        Ingredient temp = new Ingredient(name, typeNum, actualUser, actualUser);
        ingredients.add(temp);
    }

    public void addOrder(String id, int stateNum, List<Product> products, List<Integer> poductsQuantity,
            String clientName, String employeeName, Date deliveryDate, String observations) {
        Order temp = new Order(id, stateNum, products, poductsQuantity, clientName, employeeName, deliveryDate,
                observations, actualUser, actualUser);
        orders.add(temp);
    }

    public void addClient(String name, String lastName, String idNumber, String address, int phone,
            String observations) {
        Client temp = new Client(name, lastName, idNumber, actualUser, actualUser, address, phone, observations);
        clients.add(temp);
    }

    public void addEmployee(String name, String lastName, String idNumber){
        Employee temp = new Employee(name, lastName, idNumber, actualUser, actualUser);
        employees.add(temp);
    }

    public void addUser(Employee employee, String userName, String userPassword){
        User temp = new User(employee, userName, userPassword, actualUser, actualUser);
        users.add(temp);
    }

    // -------------------- remove

    public void removeProduct(Product product){
        products.remove(product);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }

    public void removeClient(Client client){
        clients.remove(client);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    // -------------------- actualize

    public void actualizeProduct(Product product, String name, int typeNum, List<Ingredient> ingedients, int sizeNum, Double price){
        Product temp = new Product(name, typeNum, ingedients, sizeNum, price, product.getCreatedBy(), actualUser);
        products.remove(product);
        products.add(temp);
    }

    public void actualizeIngredient(Ingredient ingredient, String name, int typeNum){
        Ingredient temp = new Ingredient(name, typeNum, ingredient.getCreatedBy(), actualUser);
        ingredients.remove(ingredient);
        ingredients.add(temp);
    }

    public void actualizeOrder(Order order, String id, int stateNum, List<Product> products, List<Integer> poductsQuantity,
    String clientName, String employeeName, Date deliveryDate, String observations){
        Order temp = new Order(id, stateNum, products, poductsQuantity, clientName, employeeName, deliveryDate,
                observations, order.getCreatedBy(), actualUser);
        orders.remove(order);
        orders.add(temp);
    }

    public void actualizeClient(Client client,String name, String lastName, String idNumber, String address, int phone,
    String observations ){
        Client temp = new Client(name, lastName, idNumber, client.getCreatedBy(), actualUser, address, phone, observations);
        clients.remove(client);
        clients.add(temp);
    }

}
