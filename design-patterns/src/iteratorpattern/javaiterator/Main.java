package iteratorpattern.javaiterator;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Menu dinerMenu = new DinerMenu();
		Menu pancakeMenu = new PancakeHouseMenu();
		Menu cafeMenu = new CafeMenu();
		ArrayList<Menu> menus = new ArrayList<>();
		menus.add(dinerMenu);
		menus.add(pancakeMenu);
		menus.add(cafeMenu);
		Waitress waitress = new Waitress(menus);
		waitress.printMenu();
	}

}
