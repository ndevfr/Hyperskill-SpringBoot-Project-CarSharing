package carsharing.commands;

import carsharing.commands.Customer.CreateCustomer;
import carsharing.commands.Customer.ListCustomers;
import carsharing.commands.Manager.CompanyMenu;
import carsharing.Utils;

public class MainMenu implements MenuAction {
    private final Menu menu;

    public MainMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        System.out.println("1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit");
    }

    @Override
    public void execute() {
        int input = Utils.getIntInput();
        switch (input) {
            case 1:
                menu.setCurrentMenu(new CompanyMenu(menu));
                break;
            case 2:
                menu.setCurrentMenu(new ListCustomers(menu));
                break;
            case 3:
                menu.setCurrentMenu(new CreateCustomer(menu));
                break;
            case 0:
                menu.exit();
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
}