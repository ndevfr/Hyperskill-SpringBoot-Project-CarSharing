package carsharing.commands.Manager;

import carsharing.commands.MainMenu;
import carsharing.commands.Menu;
import carsharing.Utils;
import carsharing.commands.MenuAction;

public class CompanyMenu implements MenuAction {
    private final Menu menu;

    public CompanyMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
    }

    @Override
    public void execute() {
        int input = Utils.getIntInput();
        switch (input) {
            case 1:
                menu.setCurrentMenu(new ListCompanies(menu));
                break;
            case 2:
                menu.setCurrentMenu(new CreateCompany(menu));
                break;
            case 0:
                menu.setCurrentMenu(new MainMenu(menu));
                break;
        }
    }
}