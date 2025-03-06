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

public class ChooseCompany implements MenuAction {
    private final Menu menu;
    private boolean emptyList = true;

    public ChooseCompany(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        Customer customer = menu.getSelectedCustomer();
        if(customer.getRentedCarId() > 0){
            emptyList = true;
            System.out.println("You've already rented a car!");
        } else {
            List<Company> companies = menu.getCompanyService().getAllCompanies();
            if (companies.isEmpty()) {
                emptyList = true;
                System.out.println("The company list is empty!");
                System.out.println();
                menu.setCurrentMenu(new CompanyMenu(menu));
            } else {
                emptyList = false;
                System.out.println("Choose a company:");
                for (Company company : companies) {
                    System.out.println(company.getId() + ". " + company.getName());
                }
                System.out.println("0. Back");
            }
        }
    }

    @Override
    public void execute() {
        int input = 0;
        if(!emptyList) {
            input = Utils.getIntInput();
        }
        if (input == 0) {
            menu.setCurrentMenu(new CustomerMenu(menu));
        } else {
            Company selectCompany = menu.getCompanyService().getCompanyById(input);
            menu.setSelectedCompany(selectCompany);
            if (selectCompany == null) {
                System.out.println("Company with id " + input + " not found!");
                menu.setCurrentMenu(new ChooseCompany(menu));
            } else {
                menu.setSelectedCompany(selectCompany);
                menu.setCurrentMenu(new RentCar(menu));
            }
        }
    }
}