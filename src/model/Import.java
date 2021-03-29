package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        if (searchClientByName(client.getName(), client.getLastName(), clients) == null) {
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
        }
        return count;
    }

    // ------------------------- Products

    public long importProducts(long productIdCount, List<Product> products, String path, String separator,
            List<Ingredient> ingredients) throws IOException, FileNotFoundException {

        long countProduct = productIdCount;

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        line = br.readLine();

        while (line != null) {
            String[] parts = line.split(separator);

            List<Ingredient> addedIngredients = new ArrayList<>();

            int i = 5;

            do {
                if (ingredientFinder(ingredients, parts[i]) != null) {
                    addedIngredients.add(ingredientFinder(ingredients, parts[i]));

                }
                i++;
            } while (i < parts.length);

            String id = "#P" + countProduct;

            Product temp = new Product(id, parts[0], Integer.parseInt(parts[1]), addedIngredients,
                    Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), parts[4], parts[5]);

            products.add(temp);

            countProduct++;

            line = br.readLine();

        }

        br.close();

        return countProduct;
    }

    public Ingredient ingredientFinder(List<Ingredient> list, String id) {
        Ingredient temp = null;

        List<Ingredient> tempList = list;

        Comparator<Ingredient> idComparator = new Comparator<Ingredient>() {

            @Override
            public int compare(Ingredient in1, Ingredient in2) {
                return in1.compareById(in2.getId());
            }

        };

        tempList.sort(idComparator);

        int pos = -1;
        int i = 0;
        int j = tempList.size() - 1;

        while (i <= j && pos < 0) {

            int m = (i + j) / 2;

            if (tempList.get(m).compareById(id) == 0) {

                pos = m;

            } else if (tempList.get(m).compareById(id) < 0) {
                i = m + 1;
            } else if (tempList.get(m).compareById(id) > 0) {
                j = m - 1;
            }
        }

        if (pos != -1) {
            temp = tempList.get(pos);
        }

        return temp;
    }

    // ------------------------- Ingredients

    public long importIngredients(long ingredientIdCount, List<Ingredient> ingredients, String path, String separator)
            throws IOException {

        // int testcount = 0;

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        line = br.readLine();

        while (line != null) {

            String[] parts = line.split(separator);
            String id = "#I" + ingredientIdCount;

            Ingredient temp = new Ingredient(id, parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
            if (validateName(ingredients, temp) == -1) {
                ingredientIdCount += 1;
                ingredients.add(temp);
            }

            line = br.readLine();
        }
        br.close();

        return ingredientIdCount;
    }

    private int validateName(List<Ingredient> ingredients, Ingredient ingredient) {
        int out = -1;

        for (int i = 0; i < ingredients.size() && out == -1; i++) {
            if (ingredient.compareByName(ingredients.get(i)) == 0) {
                out = i;
            }
        }
        return out;
    }

    // ------------------------- Orders

    public long importOrder(long ordersIdCount, List<Order> orders, List<Product> products, List<Client> clients,
            List<Employee> employees, List<User> users, String path, String separator)
            throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        String line = br.readLine();
        line = br.readLine();

        while (line != null) {
            String[] parts = line.split(separator);

            List<Product> addedProducts = new ArrayList<>();

            int i = 8;

            do {
                if (productFinder(products, parts[i]) != null) {
                    addedProducts.add(productFinder(products, parts[i]));

                }
                i++;
            } while (i < parts.length);

            Client clientToAdd = searchClientByName(parts[1], parts[2], clients);

            Employee employeeToAdd = searchEmployee(parts[3], employees);

            if (employeeToAdd != null){
                System.out.println(employeeToAdd.getFullname());
            }

            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.GERMANY);
            String sDate = parts[4].replaceAll("\\+0([0-9]){1}\\:00", "+0$100");
            Date deliveryDate = isoFormat.parse(sDate);

            // Date deliveryDate = new Date(System.currentTimeMillis());
        

            // Date deliveryDate = new SimpleDateFormat("dd/MM/yyyy").parse(parts[6]);

            String id = "#O" + ordersIdCount;

            if (deliveryDate != null){
               // System.out.println(employeeToAdd.getName());
               //System.out.println("Existe");
            }

            //System.out.println(addedProducts.size());

            if (clientToAdd != null && employeeToAdd != null && deliveryDate != null && addedProducts.size() > 0) {
                ordersIdCount += 1;
                Order temp = new Order(id, Integer.parseInt(parts[0]), addedProducts, clientToAdd, employeeToAdd,
                        deliveryDate, parts[5], parts[6], parts[7]);
                orders.add(temp);
            }

            line = br.readLine();
        }
        br.close();

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

        if (pos != -1) {
            temp = list.get(pos);
        }
        return temp;
    }

    public Client searchClientByName(String name, String lastName, List<Client> clients) {

        List<Client> tempList = clients;

        Client temp = null;
        String fullname = name + " " + lastName;

        int pos = -1;
        int i = 0;
        int j = tempList.size() - 1;

        while (i <= j && pos < 0) {

            int m = (i + j) / 2;

            if (tempList.get(m).compareByFullNameString(fullname) == 0) {
                pos = m;

            } else if (tempList.get(m).compareByFullNameString(fullname) < 0) {
                i = m + 1;
            } else if (tempList.get(m).compareByFullNameString(fullname) > 0) {
                j = m - 1;
            }
        }

        
        if (pos != -1) {
            temp = clients.get(pos);
        }
        return temp;
    }

    public Employee searchEmployee(String employeeName, List<Employee> employees) {
        Employee temp = null;

        int pos = -1;

        for (int i = 0; i < employees.size() && pos < 0; i++) {
            if (employees.get(i).compareByFullname(employeeName) == 0) {
                pos = i;
            }
        }

        if (pos != -1) {
            temp = employees.get(pos);
        }
        return temp;
    }
}
