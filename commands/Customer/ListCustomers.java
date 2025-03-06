package carsharing.commands.Customer;

import carsharing.Utils;
import carsharing.commands.MainMenu;
import carsharing.commands.Manager.CompanyMenu;
import carsharing.commands.Manager.ManageCompany;
import carsharing.commands.Menu;
import carsharing.commands.MenuAction;
import carsharing.entities.Company;
import carsharing.entities.Customer;

import java.util.List;

public class ListCustomers implements MenuAction {
    private final Menu menu;
    private boolean emptyList = true;

    public ListCustomers(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        List<Customer> customers = menu.getCustomerService().getAllCustomers();
        if (customers.isEmpty()) {
            emptyList = true;
            System.out.println("The customer list is empty!");
        } else {
            emptyList = false;
            System.out.println("Choose a customer:");
            for (Customer customer : customers) {
                System.out.println(customer.getId() + ". " + customer.getName());
            }
            System.out.println("0. Back");
        }
    }

    @Override
    public void execute() {
        int input = 0;
        if(!emptyList) {
            input = Utils.getIntInput();
        }
        if (input == 0) {
            menu.setCurrentMenu(new MainMenu(menu));
        } else {
            Customer selectCustomer = menu.getCustomerService().getCustomerById(input);
            menu.setSelectedCustomer(selectCustomer);
            if (selectCustomer == null) {
                System.out.println("Customer with id " + input + " not found!");
                menu.setCurrentMenu(new ListCustomers(menu));
            } else {
                menu.setCurrentMenu(new CustomerMenu(menu));
            }
        }
    }
}