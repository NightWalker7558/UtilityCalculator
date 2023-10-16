package Controller;

import Model.ServiceType;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class manages service prices for various service types.
 * It provides methods to update and retrieve service prices from a file.
 * Service prices are stored in a map, and changes are persisted to a text file.
 * The file format for storing service prices is as follows:
 * - Each line contains the service name, unit price, and service price
 * separated by colons and commas.
 * - Example: GAS:0.4,0.6
 *
 * @version 1.0
 * @since 2023-10-16
 */

public class ServiceController {

  /**
   * The file path for storing and loading service prices, and the map to store
   * service prices.
   *
   * <p>
   * This class utilizes a text file to persist service prices and a map to
   * maintain
   * them in memory. The path to the prices file and the map are initialized as
   * constants
   * to manage service prices efficiently.
   *
   * @since 2023-10-16
   * @version 1.0
   */
  private static final String PRICES_FILE_PATH = "src\\Model\\ServicePrices.txt";

  /**
   * A map that associates {@link ServiceType} with an array of {@link Double} to
   * represent service prices.
   *
   * <p>
   * The map is used to store service prices in memory, and it is populated during
   * the class
   * initialization by reading data from the prices file.
   *
   * @see ServiceType
   * @see Double
   * @see #loadPricesFromFile()
   */
  private static Map<ServiceType, Double[]> servicePrices = new HashMap<>();

  /**
   * Initializes the {@code servicePrices} map by loading data from the prices
   * file.
   *
   * @implSpec This method is automatically called when the class is loaded to
   *           populate
   *           the service prices map from the prices file.
   * @see #servicePrices
   * @see #PRICES_FILE_PATH
   * @see #loadPricesFromFile()
   */
  static {
    loadPricesFromFile();
  }

  /**
   * Loads service prices from the prices file and populates the
   * {@link #servicePrices} map.
   *
   * <p>
   * This method reads service prices from a text file specified by the
   * {@link #PRICES_FILE_PATH}
   * constant and parses them into a map, associating service types with their
   * respective unit and
   * service prices.
   *
   * <p>
   * The format of each line in the prices file should be:
   * {@code ServiceType:unitPrice,servicePrice}
   *
   * @throws IOException if an I/O error occurs while reading the prices file.
   * @see #PRICES_FILE_PATH
   * @see #servicePrices
   * @see ServiceType
   * @see Double
   */
  private static void loadPricesFromFile() {
    try (
        BufferedReader reader = new BufferedReader(new FileReader(PRICES_FILE_PATH))) {
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

  /**
   * Saves service prices from the {@link #servicePrices} map to the prices file.
   *
   * <p>
   * This method writes the service prices stored in the {@code servicePrices} map
   * to a text file,
   * specified by the {@link #PRICES_FILE_PATH} constant, in the format:
   * {@code ServiceType:unitPrice,servicePrice}.
   *
   * <p>
   * Existing data in the prices file will be overwritten with the updated service
   * prices.
   *
   * @throws IOException if an I/O error occurs while writing to the prices file.
   * @see #PRICES_FILE_PATH
   * @see #servicePrices
   * @see ServiceType
   * @see Double
   */
  private static void savePricesToFile() {
    try (
        BufferedWriter writer = new BufferedWriter(new FileWriter(PRICES_FILE_PATH))) {
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

  /**
   * Updates the unit charges for a specific service type and saves the changes.
   *
   * <p>
   * This method allows you to update the unit charges for a given
   * {@link ServiceType}. If the
   * specified service type does not exist in the price data, it will be added
   * with default prices
   * (0.0 for unit charges and service charges).
   *
   * <p>
   * After updating the unit charges, the changes are saved to the prices file
   * using
   * {@link #savePricesToFile()} to persist the updated service prices.
   *
   * @param serviceType    The type of service for which unit charges need to be
   *                       updated.
   * @param newUnitCharges The new unit charges to set for the specified service
   *                       type.
   * @throws IOException if an I/O error occurs while saving the updated prices to
   *                     the file.
   * @see #servicePrices
   * @see #savePricesToFile()
   * @see ServiceType
   */
  public static void updateUnitCharges(
      ServiceType serviceType,
      double newUnitCharges) {
    Double[] prices = servicePrices.getOrDefault(serviceType, new Double[] { 0.0, 0.0 });
    prices[0] = newUnitCharges;
    servicePrices.put(serviceType, prices);
    savePricesToFile();
  }

  /**
   * Updates the service charges for a specific service type and saves the
   * changes.
   *
   * <p>
   * This method allows you to update the service charges for a given
   * {@link ServiceType}. If the
   * specified service type does not exist in the price data, it will be added
   * with default prices
   * (0.0 for unit charges and service charges).
   *
   * <p>
   * After updating the service charges, the changes are saved to the prices file
   * using
   * {@link #savePricesToFile()} to persist the updated service prices.
   *
   * @param serviceType       The type of service for which service charges need
   *                          to be updated.
   * @param newServiceCharges The new service charges to set for the specified
   *                          service type.
   * @throws IOException if an I/O error occurs while saving the updated prices to
   *                     the file.
   * @see #servicePrices
   * @see #savePricesToFile()
   * @see ServiceType
   */
  public static void updateServiceCharges(
      ServiceType serviceType,
      double newServiceCharges) {
    Double[] prices = servicePrices.getOrDefault(serviceType, new Double[] { 0.0, 0.0 });
    prices[1] = newServiceCharges;
    servicePrices.put(serviceType, prices);
    savePricesToFile();
  }

  /**
   * Retrieves the service price for a specific service type.
   *
   * <p>
   * This method returns the service price for the specified {@link ServiceType}.
   * If the service type
   * does not exist in the price data, it will return a default service price of
   * 0.0.
   *
   * @param serviceType The type of service for which the service price is to be
   *                    retrieved.
   * @return The service price for the specified service type.
   * @see #servicePrices
   * @see ServiceType
   */
  public static double getServicePrice(ServiceType serviceType) {
    Double[] prices = servicePrices.getOrDefault(serviceType, new Double[] { 0.0, 0.0 });
    return prices[1]; // Return the service price
  }

  /**
   * Retrieves the unit price for a specific service type.
   *
   * <p>
   * This method returns the unit price for the specified {@link ServiceType}. If
   * the service type
   * does not exist in the price data, it will return a default unit price of 0.0.
   *
   * @param serviceType The type of service for which the unit price is to be
   *                    retrieved.
   * @return The unit price for the specified service type.
   * @see #servicePrices
   * @see ServiceType
   */
  public static double getUnitPrice(ServiceType serviceType) {
    Double[] prices = servicePrices.getOrDefault(serviceType, new Double[] { 0.0, 0.0 });
    return prices[0]; // Return the unit price
  }

  /**
   * The main method provides an example usage of the {@link ServiceController}
   * class.
   *
   * <p>
   * This example demonstrates how to interact with the service prices using the
   * {@link ServiceController} methods. It initializes a service type (in this
   * case, 'GAS'),
   * prints the initial service and unit charges, updates the charges, and prints
   * the updated
   * charges.
   *
   * <p>
   * Usage:
   * - Run this method to see the effect of updating service charges for a
   * specific service type.
   *
   * @param args The command-line arguments (not used in this example).
   * @see ServiceType
   * @see ServiceController
   */
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