package Controller;

import Model.Customer;

public class CustomerController {
    public boolean validateLogin(String username, String password) {
        return true;
    }

    public Customer loadCustomer(String username, String password) {
        return new Customer();
    }

    public boolean registerNewUser(String username, String email, String password) {
        return true;
    }
}
