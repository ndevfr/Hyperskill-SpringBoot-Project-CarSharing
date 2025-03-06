package carsharing.commands.Manager;

import carsharing.commands.Menu;
import carsharing.commands.MenuAction;
import carsharing.entities.Car;
import carsharing.entities.Company;

import java.util.List;

public class ListCars implements MenuAction {
    private final Menu menu;

    public ListCars(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void display() {
        Company selectedCompany = menu.getSelectedCompany();
        List<Car> cars = menu.getCarService().getCarsByCompanyId(selectedCompany.getId());
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("'" + selectedCompany.getName() + "' cars:");
            for (int i = 1; i <= cars.size(); i++) {
                Car car = cars.get(i - 1);
                System.out.println(i + ". " + car.getName());
            }
        }
    }

    @Override
    public void execute() {
        menu.setCurrentMenu(new ManageCompany(menu));
    }
}