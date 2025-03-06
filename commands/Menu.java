package carsharing.commands;

import carsharing.entities.Company;
import carsharing.entities.Customer;
import carsharing.services.CarService;
import carsharing.services.CompanyService;
import carsharing.services.CustomerService;

public class Menu {
    private final CompanyService companyService;
    private final CarService carService;
    private final CustomerService customerService;
    private MenuAction currentMenu;
    private boolean running = true;
    private Company selectedCompany;
    private Customer selectedCustomer;

    public Menu(CompanyService companyService, CarService carService, CustomerService customerService) {
        this.companyService = companyService;
        this.carService = carService;
        this.customerService = customerService;
        this.currentMenu = new MainMenu(this);
    }

    public void start() {
        while (running) {
            currentMenu.display();
            currentMenu.execute();
        }
    }

    public void setCurrentMenu(MenuAction menu) {
        this.currentMenu = menu;
    }

    public void exit() {
        running = false;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public CarService getCarService() {
        return carService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setSelectedCompany(Company company) {
        this.selectedCompany = company;
    }

    public Company getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCustomer(Customer customer) {
        this.selectedCustomer = customer;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }
}