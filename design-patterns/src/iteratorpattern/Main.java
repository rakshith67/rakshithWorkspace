package iteratorpattern;

/**
 * The Iterator Pattern provides a way to access the elements of an aggregate
 * object sequentially without exposing its underlying representation.
 * 
 * It also places the task of traversal on the iterator object, not on the
 * aggregate, which simplifies the aggregate interface and implementation, and
 * places the responsibility where it should be.
 * 
 * Every responsibility of a class is an area of potential change. More than one
 * responsibility means more than one area of change. This principle guides us
 * to keep each class to a single responsibility.
 * 
 * Cohesion is a term you’ll hear used as a measure of how closely a class or a
 * module supports a single purpose or responsibility. We say that a module or
 * class has high cohesion when it is designed around a set of related
 * functions, and we say it has low cohesion when it is designed around a set of
 * unrelated functions.
 * 
 * @author rakshim1
 *
 */
public class Main {

	public static void main(String[] args) {

		Menu dinerMenu = new DinerMenu();
		Menu pancakeMenu = new PancakeHouseMenu();
		Waitress waitress = new Waitress(pancakeMenu, dinerMenu);
		waitress.printMenu();
	}

}
