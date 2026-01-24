package service;

import dao.CustomerDAO;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    public int createCustomer(String name, String phone, String email) {

        if (!isValidPhone(phone)) {
            System.out.println("Invalid phone number. Must be 10 digits.");
            return -1;
        }

        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return -1;
        }

        return customerDAO.createCustomer(name, phone, email);
    }

    private boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
