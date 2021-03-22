package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Import {

    public Import() {
    }

    // ------------------------- Clients

    public int importClients(List<Client> clients, String path, String separator)
            throws IOException, FileNotFoundException {
        int count = 0;
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        line = br.readLine();

        while (line != null) {
            String[] parts = line.split(separator);

            Client temp = new Client(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], (parts[6]), parts[7]);

            count = orderAddClient(temp, clients, count);

            line = br.readLine();
        }

        br.close();
        return count;
    }

    public int orderAddClient(Client client, List<Client> clients, int count) {

        if (clients.isEmpty()) {

            clients.add(client);
            count++;

        } else {
            int i = 0;
            while (i < clients.size() && client.compareByFullName(clients.get(i)) > 0) {
                i++;
            }
            clients.add(i, client);
            count++;
        }
        return count;
    }

    // ------------------------- Products

    public long importProducts(long productIdCount, List<Product> products, String path, String separator,
            List<Ingredient> ingredients) throws IOException, FileNotFoundException {

        long countProduct = productIdCount;
        List<Ingredient> addedIngredients = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        line = br.readLine();

        while (line != null) {
            String[] parts = line.split(separator);

            String[] list = parts[2].split(",");
            for (int i = 0; i < list.length; i++) {
                if (ingredientFinder(ingredients, list[i]) != null) {
                    addedIngredients.add(ingredientFinder(ingredients, list[i]));
                }
            }

            String id = "#P" + productIdCount;
            productIdCount += 1;

            Product temp = new Product(id, parts[0], Integer.parseInt(parts[1]), addedIngredients,
                    Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), parts[5], parts[6]);
            products.add(temp);

            line = br.readLine();
        }

        addedIngredients.clear();
        br.close();

        return countProduct;
    }

    public Ingredient ingredientFinder(List<Ingredient> list, String id) {
        Ingredient temp = null;

        int pos = -1;
        int i = 0;
        int j = list.size() - 1;

        while (i <= j && pos < 0) {

            int m = (i + j) / 2;

            if (list.get(m).compareById(id) == 0) {

                pos = m;

            } else if (list.get(m).compareById(id) < 0) {
                i = m + 1;
            } else if (list.get(m).compareById(id) > 0) {
                j = m - 1;
            }
        }

        temp = list.get(pos);
        return temp;
    }

    // ------------------------- Ingredients

    public long importIngredients(long ingredientIdCount, List<Ingredient> ingredients, String path, String separator)
            throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        line = br.readLine();

        while (line != null) {
            String[] parts = line.split(separator);
            String id = "#I" + ingredientIdCount;
            ingredientIdCount += 1;

            Ingredient temp = new Ingredient(id, parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
            ingredients.add(temp);
            line = br.readLine();
        }
        br.close();

        return ingredientIdCount;
    }

    // ------------------------- Orders

    public long importOrder(long ordersIdCount, List<Order> orders, List<Product> products, List<Client> clients,
            List<Employee> employees, List<User> users, String path, String separator)
            throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        List<Product> addedProducts = new ArrayList<>();
        List<Integer> productsQuantity = new ArrayList<>();

        String line = br.readLine();
        line = br.readLine();

        while (line != null) {
            String[] parts = line.split(separator);

            String[] productsIdList = parts[1].split(",");
            for (int i = 0; i < products.size(); i++) {
                if (productFinder(products, productsIdList[i]) != null) {
                    addedProducts.add(productFinder(products, productsIdList[i]));
                }
            }

            String[] productsQuantityList = parts[2].split(",");
            for (int i = 0; i < productsQuantityList.length; i++) {
                productsQuantity.add(Integer.parseInt(productsQuantityList[i]));
            }

            Client clientToAdd = searchClientByName(parts[3], parts[4], clients);

            Employee employeeToAdd = searchEmployee(parts[5], employees);

            Date deliveryDate = new SimpleDateFormat("dd/MM/yyyy").parse(parts[6]);

            String createdBy = searchUser(parts[8], users).getUserName();
            String modifiedBy = searchUser(parts[9], users).getUserName();

            String id = "#O" + ordersIdCount;
            ordersIdCount += 1;

            Order temp = new Order(id, Integer.parseInt(parts[0]), addedProducts, productsQuantity, clientToAdd,
                    employeeToAdd, deliveryDate, parts[7], createdBy, modifiedBy);
            orders.add(temp);

            line = br.readLine();
        }
        br.close();

        addedProducts.clear();
        productsQuantity.clear();

        return ordersIdCount;
    }

    public Product productFinder(List<Product> list, String id) {
        Product temp = null;

        int pos = -1;
        int i = 0;
        int j = list.size() - 1;

        while (i <= j && pos < 0) {

            int m = (i + j) / 2;

            if (list.get(m).compareById(id) == 0) {

                pos = m;

            } else if (list.get(m).compareById(id) < 0) {
                i = m + 1;
            } else if (list.get(m).compareById(id) > 0) {
                j = m - 1;
            }
        }

        temp = list.get(pos);
        return temp;
    }

    public Client searchClientByName(String name, String lastName, List<Client> clients) {
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

    public User searchUser(String userName, List<User> users) {
        User temp = null;

        int pos = -1;

        for (int i = 0; i < users.size() && pos < 0; i++) {
            if (users.get(i).compareByUserName(userName) == 0) {
                pos = i;
            }
        }

        temp = users.get(pos);
        return temp;
    }

    public Employee searchEmployee(String employeeId, List<Employee> employees) {
        Employee temp = null;

        int pos = -1;

        for (int i = 0; i < employees.size() && pos < 0; i++) {
            if (employees.get(i).compareById(employeeId) == 0) {
                pos = i;
            }
        }

        temp = employees.get(pos);
        return temp;
    }
}
