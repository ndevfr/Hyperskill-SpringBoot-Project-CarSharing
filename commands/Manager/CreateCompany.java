package carsharing.commands.Manager;

import carsharing.Utils;
import carsharing.commands.Menu;
import carsharing.commands.MenuAction;

public class CreateCompany implements MenuAction {
    private final Menu menu;

    public CreateCompany(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        System.out.println("Enter the company name:");
    }

    @Override
    public void execute() {
        String companyName = Utils.getStringInput();
        menu.getCompanyService().addCompany(companyName);
        System.out.println("The company was created!");
        menu.setCurrentMenu(new CompanyMenu(menu));
    }
}