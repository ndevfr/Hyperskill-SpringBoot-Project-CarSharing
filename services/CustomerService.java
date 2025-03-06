package carsharing.services;

import carsharing.dao.CompanyDao;
import carsharing.dao.CustomerDao;
import carsharing.entities.Customer;

import java.util.List;

public class CustomerService {
    public void initTable() {
        CustomerDao.get().createTable();
    }

    public List<Customer> getAllCustomers() {
        return CustomerDao.get().getAll();
    }

    public Customer getCustomerById(int id){
        return CustomerDao.get().getById(id);
    }

    public void setRentedCarId(Integer customerId, Integer carId){
        CustomerDao.get().setRentedCarId(customerId, carId);
    }

    public void addCustomer(String name) {
        CustomerDao.get().addCustomer(name);
    }

}