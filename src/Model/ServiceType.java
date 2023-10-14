package Model;

public enum ServiceType {
    ELECTRICITY (0.12, 10.0),
    GAS         (0.08, 15.0),
    WATER       (0.05, 20.0);
    
    private double unitCharges;
    private double serviceCharges;
    
    private ServiceType(double unitCharges, double serviceCharges) {
        this.unitCharges = unitCharges;
        this.serviceCharges = serviceCharges;
    }
    
    public double getUnitCharges() {
        return unitCharges;
    }
    
    public void setUnitCharges(double unitCharges) {
        this.unitCharges = unitCharges;
    }
    
    public double getServiceCharges() {
        return serviceCharges;
    }
    
    public void setServiceCharges(double serviceCharges) {
        this.serviceCharges = serviceCharges;
    }
}
