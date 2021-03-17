package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Import {

    public Import() {
    }

    public void importClients(List<Client> clients, String path, String separator)
            throws IOException, FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        line = br.readLine();

        while (line != null) {
            String[] parts = line.split(separator);

            Client temp = new Client(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5],
                    Integer.parseInt(parts[6]), parts[7]);
            orderAddClient(temp, clients);
            line = br.readLine();
        }
        br.close();
    }

    public void orderAddClient(Client client, List<Client> clients) {
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

    public void importProducts(List<Product> products, String path, String separator)
            throws IOException, FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        line = br.readLine();

        List<String> ingredients = new ArrayList<>();

        while (line != null) {
            String[] parts = line.split(separator);

            String[] list = parts[2].split(",");
            for (int i = 0; i < list.length; i++) {
                ingredients.add(list[i]);
            }

            Product temp = new Product(parts[0], Integer.parseInt(parts[1]), ingredients, Integer.parseInt(parts[3]),
                    Double.parseDouble(parts[4]), parts[5], parts[6]);
            products.add(temp);

            line = br.readLine();
        }

        ingredients.clear();
        br.close();
    }
}
