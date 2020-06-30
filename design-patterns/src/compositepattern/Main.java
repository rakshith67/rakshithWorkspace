package compositepattern;

/**
 * The Composite Pattern allows you to compose objects into tree structures to
 * represent part-whole hierarchies. Composite lets clients treat individual
 * objects and compositions of objects uniformly.
 * 
 * The Composite Pattern allows us to build structures of objects in the form of
 * trees that contain both compositions of objects and individual objects as
 * nodes.
 * 
 * Using a composite structure, we can apply the same operations over both
 * composites and individual objects. In other words, in most cases we can
 * ignore the differences between compositions of objects and individual
 * objects.
 * 
 * All components must implement the MenuComponent interface; however, because
 * leaves and nodes have different roles we can’t always define a default
 * implementation for each method that makes sense. Sometimes the best you can
 * do is throw a runtime exception
 * 
 * @author rakshim1
 *
 */
public class Main {

	public static void main(String[] args) {
		MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
		MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
		MenuComponent cafeMenu = new Menu("CAFE MENU", "Dinner");
		MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");

		MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");

		allMenus.add(pancakeHouseMenu);
		allMenus.add(dinerMenu);
		allMenus.add(cafeMenu);

		pancakeHouseMenu
				.add(new MenuItem("K&B's Pancake Breakfast", "Pancakes with scrambled eggs, and toast", true, 2.99));
		pancakeHouseMenu
				.add(new MenuItem("Regular Pancake Breakfast", "Pancakes with fried eggs, sausage", false, 2.99));
		pancakeHouseMenu.add(new MenuItem("Blueberry Pancakes",
				"Pancakes made with fresh blueberries, and blueberry syrup", true, 3.49));
		pancakeHouseMenu
				.add(new MenuItem("Waffles", "Waffles, with your choice of blueberries or strawberries", true, 3.59));

		dinerMenu
				.add(new MenuItem("Vegetarian BLT", "(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99));
		dinerMenu.add(new MenuItem("BLT", "Bacon with lettuce & tomato on whole wheat", false, 2.99));
		dinerMenu.add(new MenuItem("Soup of the day", "A bowl of the soup of the day, with a side of potato salad",
				false, 3.29));
		dinerMenu.add(
				new MenuItem("Hotdog", "A hot dog, with saurkraut, relish, onions, topped with cheese", false, 3.05));
		dinerMenu.add(new MenuItem("Steamed Veggies and Brown Rice", "A medly of steamed vegetables over brown rice",
				true, 3.99));

		dinerMenu.add(
				new MenuItem("Pasta", "Spaghetti with Marinara Sauce, and a slice of sourdough bread", true, 3.89));

		dinerMenu.add(dessertMenu);

		dessertMenu.add(
				new MenuItem("Apple Pie", "Apple pie with a flakey crust, topped with vanilla icecream", true, 1.59));
		dessertMenu.add(
				new MenuItem("Cheesecake", "Creamy New York cheesecake, with a chocolate graham crust", true, 1.99));
		dessertMenu.add(new MenuItem("Sorbet", "A scoop of raspberry and a scoop of lime", true, 1.89));

		cafeMenu.add(new MenuItem("Veggie Burger and Air Fries",
				"Veggie burger on a whole wheat bun, lettuce, tomato, and fries", true, 3.99));
		cafeMenu.add(new MenuItem("Soup of the day", "A cup of the soup of the day, with a side salad", false, 3.69));
		cafeMenu.add(new MenuItem("Burrito", "A large burrito, with whole pinto beans, salsa, guacamole", true, 4.29));

		Waitress waitress = new Waitress(allMenus);
		waitress.printMenu();
		waitress.printVegetarianMenu();
	}
}
