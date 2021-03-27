package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private User actualUser;

    private Import imports;
    private Export exports;

    private long ordersIdCount;
    private long productIdCount;
    private long ingredientIdCount;

    public final static String RESTAURANTSYSTEM_FILE_NAME = "data/restaurant_system.rsys";

    public RestaurantSystem() {

        clients = new ArrayList<>();
        employees = new ArrayList<>();
        users = new ArrayList<>();
        products = new ArrayList<>();
        ingredients = new ArrayList<>();
        orders = new ArrayList<>();
        this.actualUser = null;
        ordersIdCount = 0;
        imports = new Import();
        exports = new Export();
    }

    // -------------------- Getters and setters

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

    public void setActualDate(Date date) {
        this.actualDate = date;
    }

    public User getActualUser() {
        return actualUser;
    }

    public void setActualUser(User actualUser) {
        this.actualUser = actualUser;
    }

    // -------------------- adds

    public void addFirstUser(String name, String lastName, String idNumber, String userName, String userPassword)
            throws IOException {
        Employee tempE = new Employee(name, lastName, idNumber, userName, userName);
        employees.add(tempE);

        User tempU = new User(tempE, userName, userPassword);
        users.add(tempU);
        saveData();
    }

    public void addProduct(String name, int typeNum, List<Ingredient> ingredients, int sizeNum, Double price)
            throws IOException {
        String id = "#P" + productIdCount;

        Product temp = new Product(id, name, typeNum, ingredients, sizeNum, price, actualUser.getUserName(),
                actualUser.getUserName());
        products.add(temp);
        productIdCount += 1;
        saveData();
    }

    public boolean addIngredient(String name, int typeNum) throws IOException {
        boolean added = false; 

        String id = "#I" + ingredientIdCount;
        Ingredient temp = new Ingredient(id, name, typeNum, actualUser.getUserName(), actualUser.getUserName());
        if(ingredients.isEmpty()){
            ingredients.add(temp);
            ingredientIdCount += 1;
            added = true;
        } else {
            if (validateName(temp) == -1) {
                ingredients.add(temp);
                ingredientIdCount += 1;
                added = true;
            }
        }
        
        
        saveData();
        return added;
    }

    private int validateName(Ingredient ingredient) {
        int out = -1;

        for (int i = 0; i < ingredients.size() && out == -1; i++) {
            if (ingredient.compareByName(ingredients.get(i)) == 0) {
                out = i;
            }
        }
        return out;
    }

    public void addOrder(int stateNum, List<Product> products, List<Integer> productsQuantity, String clientName,
            String clientLastName, String employeeID, Date deliveryDate, String observations) throws IOException {
        String id = "#O" + ordersIdCount;

        Client client = searchClientByName(clientName, clientLastName);

        Employee employee = searchEmployee(employeeID);

        Order temp = new Order(id, stateNum, products, productsQuantity, client, employee, deliveryDate, observations,
                actualUser.getUserName(), actualUser.getUserName());

        orders.add(temp);
        ordersIdCount += 1;
        saveData();
    }

    public boolean addClient(String name, String lastName, String idNumber, String address, String phone,
            String observations) throws IOException {

        boolean added = false;

        Client temp = new Client(name, lastName, idNumber, actualUser.getUserName(), actualUser.getUserName(), address,
                phone, observations);

        if (clients.isEmpty()) {
            orderAddCliente(temp);
            saveData();
            added = true;
        } else {
            if (idVerificationClient(temp) == -1) {
                orderAddCliente(temp);
                saveData();
                added = true;
            }

        }

        return added;
    }

    private int idVerificationClient(Client client) {

        List<Client> temp = clients;

        int pos = -1;
        int i = 0;
        int j = employees.size() - 1;

        Comparator<Client> idComparator = new Comparator<Client>() {

            @Override
            public int compare(Client cl1, Client cl2) {
                return cl1.compareById(cl2);
            }

        };

        temp.sort(idComparator);

        while (i <= j && pos < 0) {

            int m = (i + j) / 2;

            if (client.compareById(clients.get(m)) == 0) {

                pos = m;

            } else if (client.compareById(clients.get(m)) < 0) {
                i = m + 1;
            } else if (client.compareById(clients.get(m)) > 0) {
                j = m - 1;
            }
        }

        return pos;
    }

    public void orderAddCliente(Client client) {
        if (clients.isEmpty()) {

            clients.add(client);

        } else {
            int i = 0;
            while (i < clients.size() && client.compareByFullName(clients.get(i)) > 0) {
                i++;
            }
            clients.add(i, client);
        }
    }

    public void addEmployee(String name, String lastName, String idNumber) throws IOException {
        Employee temp = new Employee(name, lastName, idNumber, actualUser.getUserName(), actualUser.getUserName());
        employees.add(temp);
        saveData();
    }

    public void addUser(Employee employee, String userName, String userPassword) throws IOException {
        User temp = new User(employee, userName, userPassword, actualUser.getUserName(), actualUser.getUserName());
        users.add(temp);
        saveData();
    }

    // -------------------- remove

    public void removeProduct(Product product) throws IOException {
        products.remove(product);
        saveData();
    }

    public void removeIngredient(Ingredient ingredient) throws IOException {
        ingredients.remove(ingredient);
        saveData();
    }

    public void removeOrder(Order order) throws IOException {
        orders.remove(order);
        saveData();
    }

    public void removeClient(Client client) throws IOException {
        clients.remove(client);
        saveData();
    }

    public void removeEmployee(Employee employee) throws IOException {
        employees.remove(employee);
        saveData();
    }

    public void removeUser(User user) throws IOException {
        users.remove(user);
        saveData();
    }

    // -------------------- update

    public void updateProduct(Product product, String name, int typeNum, List<Ingredient> ingredients, int sizeNum,
            Double price) throws IOException {
        /*
         * Product temp = new Product(product.getId(), name, typeNum, ingredients,
         * sizeNum, price, product.getCreatedBy(), actualUser.getUserName());
         * products.remove(product); products.add(temp);
         */
        product.setName(name);
        product.setType(typeNum);
        product.setIngredients(ingredients);
        product.setSize(sizeNum);
        product.setPrice(price);
        product.setModifiedBy(actualUser.getUserName());

        saveData();
    }

    public void updateIngredient(Ingredient ingredient, String name, int typeNum) throws IOException {
        /*
         * Ingredient temp = new Ingredient(ingredient.getId(), name, typeNum,
         * ingredient.getCreatedBy(), actualUser.getUserName());
         * ingredients.remove(ingredient); ingredients.add(temp);
         */
        ingredient.setName(name);
        ingredient.setType(typeNum);
        ingredient.setModifiedBy(actualUser.getUserName());

        saveData();
    }

    public void updateOrder(Order order, int stateNum, List<Product> products, List<Integer> productsQuantity,
            String clientName, String clientLastName, String employeeID, Date deliveryDate, String observations)
            throws IOException {

        /*
         * Order temp = new Order(order.getId(), stateNum, products, productsQuantity,
         * client, employee, deliveryDate, observations, order.getCreatedBy(),
         * actualUser.getUserName()); orders.remove(order); orders.add(temp);
         */

        Employee employee = searchEmployee(employeeID);

        Client client = searchClientByName(clientName, clientLastName);

        order.setState(stateNum);
        order.setProducts(products);
        order.setProductsQuantity(productsQuantity);
        order.setClient(client);
        order.setEmployee(employee);
        order.setDeliveryDate(actualDate);
        order.setObservations(observations);
        order.setModifiedBy(actualUser.getUserName());

        saveData();
    }

    public void updateClient(Client client, String name, String lastName, String idNumber, String address, String phone,
            String observations) throws IOException {
        /*
         * Client temp = new Client(name, lastName, idNumber, client.getCreatedBy(),
         * actualUser.getUserName(), address, phone, observations);
         * removeClient(client); orderAddCliente(temp);
         */

        client.setName(name);
        client.setLastName(lastName);
        client.setIdNumber(idNumber);
        client.setAddress(address);
        client.setPhone(phone);
        client.setObservations(observations);
        client.setModifiedBy(actualUser.getUserName());

        saveData();
    }

    public void updateEmployee(Employee employee, String name, String lastName, String idNumber) throws IOException {
        /*
         * Employee temp = new Employee(name, lastName, idNumber,
         * employee.getCreatedBy(), actualUser.getUserName()); removeEmployee(employee);
         * employees.add(temp);
         */

        employee.setName(name);
        employee.setLastName(lastName);
        employee.setIdNumber(idNumber);
        employee.setModifiedBy(actualUser.getUserName());

        saveData();
    }

    public void updateUser(User user, Employee employee, String userName, String userPassword) throws IOException {
        /*
         * User temp = new User(employee, userName, userPassword, user.getCreatedBy(),
         * actualUser.getUserName()); removeUser(user); users.add(temp);
         */

        user.setEmployee(employee);
        user.setUserName(userName);
        user.setUserPassword(userPassword);

        saveData();
    }

    // -------------------- disable / enable

    public void disableProduct(Product product) throws IOException {
        product.setAvailable(false);
        saveData();
    }

    public void disableIngredient(Ingredient ingredient) throws IOException {
        ingredient.setAvailable(false);
        saveData();
    }

    public void enableProduct(Product product) throws IOException {
        product.setAvailable(true);
        saveData();
    }

    public void enableIngredient(Ingredient ingredient) throws IOException {
        ingredient.setAvailable(true);
        saveData();
    }

    // -------------------- search

    public Client searchClientByName(String name, String lastName) {
        Client temp = null;
        String fullname = name + " " + lastName;

        int pos = -1;
        int i = 0;
        int j = clients.size() - 1;

        while (i <= j && pos < 0) {

            int m = (i + j) / 2;

            if (clients.get(m).compareByFullNameString(fullname) == 0) {
                pos = m;

            } else if (clients.get(m).compareByFullNameString(fullname) < 0) {
                i = m + 1;
            } else if (clients.get(m).compareByFullNameString(fullname) > 0) {
                j = m - 1;
            }
        }
        if (pos != -1) {
            temp = clients.get(pos);
        }
        return temp;
    }

    public Employee searchEmployee(String employeeId) {
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

    public int importClients(String path, String separator) throws FileNotFoundException, IOException {
        int count = imports.importClients(clients, path, separator);
        saveData();
        return count;
    }

    public void importProducts(String path, String separator) throws FileNotFoundException, IOException {
        productIdCount = imports.importProducts(productIdCount, products, path, separator, ingredients);
        saveData();
    }

    public int importIngredients(String path, String separator) throws FileNotFoundException, IOException {
        int count = 0;
        long temp = ingredientIdCount;
        ingredientIdCount = imports.importIngredients(ingredientIdCount, ingredients, path, separator);

        count = (int) (ingredientIdCount - temp);
        saveData();
        return count;
    }

    public void importOrders(String path, String separator) throws FileNotFoundException, IOException, ParseException {
        ordersIdCount = imports.importOrder(ordersIdCount, orders, products, clients, employees, users, path,
                separator);
        saveData();
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

    private void selectionSorting(List<Employee> list) {

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

    // ---------------- Exports

    public void exportOrders(File file, String separator, Date supLimit, Date infLimit) throws FileNotFoundException {
        List<Order> selected = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            Date temp = orders.get(i).getDeliveryDate();
            if (temp.compareTo(infLimit) > 0 && temp.compareTo(supLimit) < 0) {
                selected.add(orders.get(i));
            }
        }

        exports.exportOrders(file, selected, separator);
    }

    public void exportEmployeesReports(File file, String separator, Date supLimit, Date infLimit)
            throws FileNotFoundException {

        List<String> line = new ArrayList<>();
        List<Integer> countSells = new ArrayList<>();
        List<Long> sellsList = new ArrayList<>();

        Employee tEmployee = null;

        for (int k = 0; k < employees.size(); k++) {
            tEmployee = employees.get(k);
            int count = 0;
            long sellAmount = 0;

            for (int i = 0; i < orders.size(); i++) {
                Date temp = orders.get(i).getDeliveryDate();
                if (temp.compareTo(infLimit) > 0 && temp.compareTo(supLimit) < 0
                        && orders.get(i).getEmployee() == tEmployee) {
                    count += 1;
                    sellAmount = orders.get(i).getAmount();
                }
            }
            countSells.add(count);
            sellsList.add(sellAmount);
        }

        for (int i = 0; i < employees.size(); i++) {
            line.add(employees.get(i).getName() + " " + employees.get(i).getLastName() + separator + countSells.get(i)
                    + separator + sellsList.get(i));
        }

        exports.exportEmployees(file, line, separator);
    }

    public void exportProductsReport(File file, String separator, Date supLimit, Date infLimit)
            throws FileNotFoundException {

        List<String> line = new ArrayList<>();
        List<Integer> countSells = new ArrayList<>();

        Product tProduct = null;
        for (int k = 0; k < products.size(); k++) {
            tProduct = products.get(k);
            int count = 0;

            for (int i = 0; i < orders.size(); i++) {
                Date temp = orders.get(i).getDeliveryDate();
                if (temp.compareTo(infLimit) > 0 && temp.compareTo(supLimit) < 0) {
                    for (int j = 0; j < orders.get(i).getProducts().size(); j++) { // Product list of the order in rage
                        if (orders.get(i).getProducts().get(j) == tProduct) {
                            count += orders.get(i).getProductsQuantity().get(j);
                        }
                    }
                }
            }
            countSells.add(count);
        }

        for (int i = 0; i < products.size(); i++) {
            long amount = (long) (products.get(i).getPrice() * countSells.get(i));

            line.add(products.get(i) + separator + countSells.get(i) + separator + amount);
        }

        exports.exportProducts(file, line);
    }

    // ---------------- Generate display lists

    public List<Ingredient> getDisplayClientList() {
        List<Ingredient> tempList = ingredients;

        Collections.sort(tempList);

        return tempList;
    }

    public List<Product> getDisplayProduct() {
        List<Product> tempList = products;

        Collections.sort(tempList);

        return tempList;
    }

    public List<Order> getDisplayOrder() {
        List<Order> tempList = orders;
        Comparator<Order> dateComparator = new Comparator<Order>() {

            @Override
            public int compare(Order or1, Order or2) {
                return or1.getDeliveryDate().compareTo(or2.getDeliveryDate());
            }

        };

        tempList.sort(dateComparator); // Collections.reverseOrder(ComparatorObject) -> return a Comparator

        return tempList;
    }

    // ---------------- Serialization

    public void saveData() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESTAURANTSYSTEM_FILE_NAME));

        oos.writeObject(employees);
        oos.writeObject(users);
        oos.writeObject(clients);

        oos.writeObject(ingredients);
        oos.writeObject(products);
        oos.writeObject(orders);

        oos.writeObject(ordersIdCount);
        oos.writeObject(productIdCount);
        oos.writeObject(ingredientIdCount);
        oos.close();
    }

    @SuppressWarnings("unchecked") // Supress the cast warning
    public boolean loadData() throws IOException, ClassNotFoundException {
        File f = new File(RESTAURANTSYSTEM_FILE_NAME);
        boolean loaded = false;
        if (f.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));

            employees = (List<Employee>) ois.readObject();
            users = (List<User>) ois.readObject();
            clients = (List<Client>) ois.readObject();

            ingredients = (List<Ingredient>) ois.readObject();
            products = (List<Product>) ois.readObject();
            orders = (List<Order>) ois.readObject();

            ordersIdCount = (long) ois.readObject();
            productIdCount = (long) ois.readObject();
            ingredientIdCount = (long) ois.readObject();
            ois.close();
            loaded = true;
        }
        return loaded;
    }

}
