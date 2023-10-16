package Model;

/**
 * The `ServiceType` enum represents various types of utility services, each with its associated unit charges and service charges.
 */
public enum ServiceType {
  /**
   * ELECTRICITY service type.
   * Unit Charges: $0.12 per unit
   * Service Charges: $10.0
   */
  ELECTRICITY(0.12, 10.0),

  /**
   * GAS service type.
   * Unit Charges: $0.08 per unit
   * Service Charges: $15.0
   */
  GAS(0.08, 15.0),

  /**
   * WATER service type.
   * Unit Charges: $0.05 per unit
   * Service Charges: $20.0
   */
  WATER(0.05, 20.0);

  private double unitCharges;
  private double serviceCharges;

  /**
   * Constructs a `ServiceType` with the specified unit charges and service charges.
   *
   * @param unitCharges     The unit charges for the service type.
   * @param serviceCharges  The service charges for the service type.
   */
  private ServiceType(double unitCharges, double serviceCharges) {
    this.unitCharges = unitCharges;
    this.serviceCharges = serviceCharges;
  }

  /**
   * Get the unit charges for this service type.
   *
   * @return The unit charges.
   */
  public double getUnitCharges() {
    return unitCharges;
  }

  /**
   * Set the unit charges for this service type.
   *
   * @param unitCharges The unit charges to set.
   */
  public void setUnitCharges(double unitCharges) {
    this.unitCharges = unitCharges;
  }

  /**
   * Get the service charges for this service type.
   *
   * @return The service charges.
   */
  public double getServiceCharges() {
    return serviceCharges;
  }

  /**
   * Set the service charges for this service type.
   *
   * @param serviceCharges The service charges to set.
   */
  public void setServiceCharges(double serviceCharges) {
    this.serviceCharges = serviceCharges;
  }
}
