package carsharing.commands.Customer;

import carsharing.commands.MainMenu;
import carsharing.commands.Menu;
import carsharing.Utils;
import carsharing.commands.MenuAction;

public class CustomerMenu implements MenuAction {
    private final Menu menu;

    public CustomerMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        System.out.println("1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back");
    }

    @Override
    public void execute() {
        int input = Utils.getIntInput();
        switch (input) {
            case 1:
                menu.setCurrentMenu(new ChooseCompany(menu));
                break;
            case 2:
                menu.setCurrentMenu(new ReturnCar(menu));
                break;
            case 3:
                menu.setCurrentMenu(new DisplayCar(menu));
                break;
            case 0:
                menu.setCurrentMenu(new MainMenu(menu));
                menu.setSelectedCustomer(null);
                break;
        }
    }
}