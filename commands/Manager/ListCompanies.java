package carsharing.commands.Manager;

import carsharing.commands.Menu;
import carsharing.Utils;
import carsharing.commands.MenuAction;
import carsharing.entities.Company;

import java.util.List;

public class ListCompanies implements MenuAction {
    private final Menu menu;
    private boolean emptyList = true;

    public ListCompanies(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
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

    @Override
    public void execute() {
        int input = 0;
        if(!emptyList) {
            input = Utils.getIntInput();
        }
        if (input == 0) {
            menu.setCurrentMenu(new CompanyMenu(menu));
        } else {
            Company selectCompany = menu.getCompanyService().getCompanyById(input);
            menu.setSelectedCompany(selectCompany);
            if (selectCompany == null) {
                System.out.println("Company with id " + input + " not found!");
                menu.setCurrentMenu(new CompanyMenu(menu));
            } else {
                System.out.println("'" + selectCompany.getName() + "' company:");
                menu.setCurrentMenu(new ManageCompany(menu));
            }
        }
    }
}