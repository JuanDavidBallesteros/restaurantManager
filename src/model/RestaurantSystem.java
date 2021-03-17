package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
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

    private Import imports;

    private long ordersIdCount;
    private long productIdCount;
    private long ingredientIdCount;

    public RestaurantSystem(String actualUser) {
        clients = new ArrayList<>();
        employees = new ArrayList<>();
        users = new ArrayList<>();
        products = new ArrayList<>();
        ingredients = new ArrayList<>();
        orders = new ArrayList<>();
        this.actualUser = actualUser;
        ordersIdCount = 0;
        imports = new Import();
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
        String id = "#P" + productIdCount;
        Product temp = new Product(id, name, typeNum, ingedients, sizeNum, price, actualUser, actualUser);
        products.add(temp);
        productIdCount += 1;
    }

    public void addIngredient(String name, int typeNum) {
        String id = "#I" + ingredientIdCount;
        Ingredient temp = new Ingredient(id, name, typeNum, actualUser, actualUser);
        ingredients.add(temp);
        ingredientIdCount += 1;
    }

    public void addOrder(int stateNum, List<Product> products, List<Integer> poductsQuantity, String clientName, String clientLastName,
            String employeeID, Date deliveryDate, String observations) {
        String id = "#O" + ordersIdCount;

        Client client = searchClientByName(clientName, clientLastName);

        Employee employee = searchEmployee(employeeID);

        Order temp = new Order(id, stateNum, products, poductsQuantity, client, employee, deliveryDate,
                observations, actualUser, actualUser);
        orders.add(temp);
        ordersIdCount += 1;
    }

    public void addClient(String name, String lastName, String idNumber, String address, int phone,
            String observations) {
        Client temp = new Client(name, lastName, idNumber, actualUser, actualUser, address, phone, observations);
        orderAddCliente(temp);
    }

    public void orderAddCliente(Client client) {
        boolean space = false;
        if (clients.size() > 0) {
            for (int i = 0; i < clients.size() && !space; i++) {
                if (client.compareByLastName(clients.get(i)) > 0) {
                    clients.add(i, client);
                    space = true;
                } else if (client.compareByLastName(clients.get(i)) == 0) {
                    if (client.compareByName(clients.get(i)) > 0) {
                        clients.add(i, client);
                        space = true;
                    }
                }
            }
        } else {
            clients.add(client);
        }
    }

    public void addEmployee(String name, String lastName, String idNumber) {
        Employee temp = new Employee(name, lastName, idNumber, actualUser, actualUser);
        employees.add(temp);
    }

    public void addUser(Employee employee, String userName, String userPassword) {
        User temp = new User(employee, userName, userPassword, actualUser, actualUser);
        users.add(temp);
    }

    // -------------------- remove

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    // -------------------- actualize

    public void actualizeProduct(Product product, String name, int typeNum, List<Ingredient> ingedients, int sizeNum,
            Double price) {
        Product temp = new Product(product.getId(), name, typeNum, ingedients, sizeNum, price, product.getCreatedBy(), actualUser);
        products.remove(product);
        products.add(temp);
    }

    public void actualizeIngredient(Ingredient ingredient, String name, int typeNum) {
        Ingredient temp = new Ingredient(ingredient.getId(), name, typeNum, ingredient.getCreatedBy(), actualUser);
        ingredients.remove(ingredient);
        ingredients.add(temp);
    }

    public void actualizeOrder(Order order, int stateNum, List<Product> products, List<Integer> poductsQuantity,
            String clientName, String clientLastName, String employeeID, Date deliveryDate, String observations) {

        Client client = searchClientByName(clientName, clientLastName);

        Employee employee = searchEmployee(employeeID);

        Order temp = new Order(order.getId(), stateNum, products, poductsQuantity, client, employee,
                deliveryDate, observations, order.getCreatedBy(), actualUser);
        orders.remove(order);
        orders.add(temp);
    }

    public void actualizeClient(Client client, String name, String lastName, String idNumber, String address, int phone,
            String observations) {
        Client temp = new Client(name, lastName, idNumber, client.getCreatedBy(), actualUser, address, phone,
                observations);
        removeClient(client);
        orderAddCliente(temp);
    }

    public void actualizeEmployee(Employee employee, String name, String lastName, String idNumber) {
        Employee temp = new Employee(name, lastName, idNumber, employee.getCreatedBy(), actualUser);
        removeEmployee(employee);
        employees.add(temp);
    }

    public void actualizeUser(User user, Employee employee, String userName, String userPassword) {
        User temp = new User(employee, userName, userPassword, user.getCreatedBy(), actualUser);
        removeUser(user);
        users.add(temp);
    }

    // -------------------- disable / enable

    public void disableProduct(Product product) {
        product.setAvilable(false);
    }

    public void disableIngredient(Ingredient ingredient) {
        ingredient.setAvilable(false);
    }

    public void enableProduct(Product product) {
        product.setAvilable(true);
    }

    public void enableIngredient(Ingredient ingredient) {
        ingredient.setAvilable(true);
    }

    // -------------------- search

    public Client searchClientByName(String name, String lastName) {
        Client temp = null;

        int pos = -1;
        int i = 0;
        int j = clients.size() - 1;

        while (i <= j && pos < 0) {

            int m = (i + j) / 2;

            if (clients.get(m).compareByLastName(lastName) == 0) {
                if (clients.get(m).compareByName(name) == 0) {
                    pos = m;
                } else if (clients.get(m).compareByName(name) < 0) {
                    i = m + 1;
                } else if (clients.get(m).compareByName(name) > 0) {
                    j = m - 1;
                }

            } else if (clients.get(m).compareByLastName(lastName) < 0) {
                i = m + 1;
            } else if (clients.get(m).compareByLastName(lastName) > 0) {
                j = m - 1;
            }
        }

        temp = clients.get(pos);
        return temp;
    }

    public Employee searchEmployee(String employeeId){
        Employee temp = null;

        selectionSorting(employees);

        int pos = -1;
        int i = 0;
        int j = employees.size() - 1;

        while (i <= j && pos < 0) {

            int m = (i + j) / 2;

            if (employees.get(m).compareById(employeeId) == 0) {
                
                    pos = m;

            } else if (employees.get(m).compareById(employeeId) < 0) {
                i = m + 1;
            } else if (employees.get(m).compareById(employeeId) > 0) {
                j = m - 1;
            }
        }

        temp = employees.get(pos);
        return temp;
    }

    public User searchUser(String userName) {
        User temp = null;

        bubbleSorting(users);

        int pos = -1;
        int i = 0;
        int j = users.size() - 1;

        while (i <= j && pos < 0) {

            int m = (i + j) / 2;

            if (users.get(m).compareByUserName(userName) == 0) {

                pos = m;

            } else if (users.get(m).compareByUserName(userName) < 0) {
                i = m + 1;
            } else if (users.get(m).compareByUserName(userName) > 0) {
                j = m - 1;
            }
        }

        temp = users.get(pos);
        return temp;
    }

    // -------------------- imports

    public void importClients(String path, String separator) throws FileNotFoundException, IOException{
        imports.importClients(clients, path, separator);
    }

    public void importProducts(String path, String separator) throws FileNotFoundException, IOException{
        productIdCount = imports.importProducts(productIdCount, products, path, separator, ingredients);
    }

    public void importIngredients(String path, String separator) throws FileNotFoundException, IOException{
        ingredientIdCount = imports.importIngredients(ingredientIdCount, ingredients, path, separator);
    }

    public void importOrders(String path, String separator) throws FileNotFoundException, IOException, ParseException{
        ordersIdCount = imports.importOrder(ordersIdCount, orders, products, clients, employees, users, path, separator);
    }


    // ---------------- Sorts 

    private void bubbleSorting(List<User> list) {

        for (int i = list.size() - 1; i > 0; i--) {

            for (int j = 0; j < list.size() - 1; j++) {

                if (list.get(j).compareByUserName(list.get(j + 1).getUserName()) > 0) {
                    User temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set((j + 1), temp);
                }
            }
        }        
    }

    private void selectionSorting(List<Employee> list){

        for (int i = 0; i < list.size(); i++) {
            Employee minor = list.get(i);
            int pos = i;

            for (int j = i + 1; j < list.size(); j++) {
                if (minor.compareById(list.get(j).getIdNumber()) > 0) {
                    minor = list.get(j);
                    pos = j;
                }
            }
            Employee temp = list.get(i);
            list.set(i, minor);
            list.set(pos, temp);
        }
    }

}
