package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Export {

    // ------------------- All methods needs filtered array/list
    public Export() {
    }

    public void exportOrders(File file, List<Order> orders, String separator) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);

        String line = "Nombre Cliente" + separator + "Apellido" + separator + " Dirección Cliente" + separator
                + "Teléfono Cliente" + separator + "Entrega" + separator + "Fecha y Hora" + separator + "Observaciones"
                + separator + "Productos";
        pw.println(line);

        for (int i = 0; i < orders.size(); i++) {
            Client client = orders.get(i).getClient();
            Employee deliver = orders.get(i).getEmployee();
            List<Product> products = orders.get(i).getProducts();
            Order order = orders.get(i);

            line = client.getName() + separator + client.getLastName() + separator + client.getAddress() + separator
                    + client.getPhone();
            line += deliver.getName() + separator + deliver.getLastName();
            line += order.getDeliveryDate() + separator + order.getObservations();

            // Add product list at the end
            for (int j = 0; j < products.size(); j++) {
                Product temp = products.get(j);
                //int quantity = order.getProductsQuantity().get(j);
                line += temp.getName() + separator + temp.getPrice();
            }

            pw.println(line);

        }

        pw.close();
    }

    public void exportEmployees(File file, List<String> employeesReport, String separator) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        String line = "Empleado" + separator + "Cantidad pedidos" + separator + " Valor entregas";
        pw.println(line);

        for (int i = 0; i < employeesReport.size(); i++) {
            line = employeesReport.get(i);
            pw.println(line);

        }

        pw.close();
    }

    public void exportProducts(File file, List<String> productsReport) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        String line = "";
        pw.println(line);

        for (int i = 0; i < productsReport.size(); i++) {
            line = productsReport.get(i);
            pw.println(line);

        }

        pw.close();
    }

}
