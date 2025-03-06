package carsharing.commands.Manager;

import carsharing.Utils;
import carsharing.commands.Menu;
import carsharing.commands.MenuAction;

public class CreateCar implements MenuAction {
    private final Menu menu;

    public CreateCar(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        System.out.println("Enter the car name:");
    }

    @Override
    public void execute() {
        String carName = Utils.getStringInput();
        int companyId = menu.getSelectedCompany().getId();
        menu.getCarService().addCar(companyId, carName);
        System.out.println("The car was created!");
        menu.setCurrentMenu(new ManageCompany(menu));
    }
}