package Controller;

import Model.ServiceType;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ServiceController {

    private static final String PRICES_FILE_PATH = "src\\Model\\ServicePrices.txt";

    private static Map<ServiceType, Double[]> servicePrices = new HashMap<>();

    static {
        loadPricesFromFile();
    }

    private static void loadPricesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PRICES_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String serviceName = parts[0];
                    String[] prices = parts[1].split(",");
                    if (prices.length == 2) {
                        Double unitPrice = Double.parseDouble(prices[0]);
                        Double servicePrice = Double.parseDouble(prices[1]);
                        ServiceType serviceType = ServiceType.valueOf(serviceName);
                        servicePrices.put(serviceType, new Double[] { unitPrice, servicePrice });
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void savePricesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRICES_FILE_PATH))) {
            for (Map.Entry<ServiceType, Double[]> entry : servicePrices.entrySet()) {
                ServiceType serviceType = entry.getKey();
                Double[] prices = entry.getValue();
                writer.write(serviceType.name() + ":" + prices[0] + "," + prices[1]);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUnitCharges(ServiceType serviceType, double newUnitCharges) {
        Double[] prices = servicePrices.getOrDefault(serviceType, new Double[] { 0.0, 0.0 });
        prices[0] = newUnitCharges;
        servicePrices.put(serviceType, prices);
        savePricesToFile();
    }

    public static void updateServiceCharges(ServiceType serviceType, double newServiceCharges) {
        Double[] prices = servicePrices.getOrDefault(serviceType, new Double[] { 0.0, 0.0 });
        prices[1] = newServiceCharges;
        servicePrices.put(serviceType, prices);
        savePricesToFile();
    }

    public static double getServicePrice(ServiceType serviceType) {
        Double[] prices = servicePrices.getOrDefault(serviceType, new Double[] { 0.0, 0.0 });
        return prices[1]; // Return the service price
    }

    public static double getUnitPrice(ServiceType serviceType) {
        Double[] prices = servicePrices.getOrDefault(serviceType, new Double[] { 0.0, 0.0 });
        return prices[0]; // Return the unit price
    }

    public static void main(String[] args) {
        // Example usage
        ServiceType gas = ServiceType.GAS;

        System.out.println("Before Update");

        System.out.println(getUnitPrice(gas));
        System.out.println(getServicePrice(gas));

        updateUnitCharges(gas, 0.4);
        updateServiceCharges(gas, 0.6);

        System.out.println("After update");
        // Print updated charges
        System.out.println(getUnitPrice(gas));
        System.out.println(getServicePrice(gas));

    }
}
