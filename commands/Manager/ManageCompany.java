package carsharing.commands.Manager;

import carsharing.Utils;
import carsharing.commands.Menu;
import carsharing.commands.MenuAction;

public class ManageCompany implements MenuAction {
    private final Menu menu;

    public ManageCompany(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
    }

    @Override
    public void execute() {
        int input = Utils.getIntInput();
        switch (input) {
            case 1:
                menu.setCurrentMenu(new ListCars(menu));
                break;
            case 2:
                menu.setCurrentMenu(new CreateCar(menu));
                break;
            case 0:
                menu.setCurrentMenu(new CompanyMenu(menu));
                menu.setSelectedCompany(null);
                break;
        }
    }
}