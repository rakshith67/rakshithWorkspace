package factorypattern.after;

/**
 * 
 * A factory method handles object creation and encapsulates it in a subclass.
 * This decouples the client code in the superclass from the object creation
 * code in the subclass.
 * 
 * The Factory Method Pattern defines an interface for creating an object, but
 * lets subclasses decide which class to instantiate. Factory Method lets a
 * class defer instantiation to subclasses.
 * 
 * Dependency Inversion Principle: Depend upon abstractions. Do not depend upon
 * concrete classes.It suggests that our high-level components should not depend
 * on our low-level components; rather, they should both depend on abstractions.
 * 
 * The Abstract Factory Pattern provides an interface for creating families of
 * related or dependent objects without specifying their concrete classes.
 * 
 * Factory Method creates objects using inheritance and Abstract Factory creates
 * objects using composition.
 * 
 * Abstract Factory: use me whenever you have families of products you need to
 * create and you want to make sure your clients create products that belong
 * together.
 * 
 * Factory Method: use me to decouple your client code from the concrete classes
 * you need to instantiate, or if you don’t know ahead of time all the concrete
 * classes you are going to need. To use me, just subclass me and implement my
 * factory method!
 * 
 * @author Rakshith
 *
 */
public class Main {

	public static void main(String[] args) {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();

		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza + "\n");

		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("clam");
		System.out.println("Ethan ordered a " + pizza + "\n");

		pizza = chicagoStore.orderPizza("clam");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("pepperoni");
		System.out.println("Ethan ordered a " + pizza + "\n");

		pizza = chicagoStore.orderPizza("pepperoni");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("veggie");
		System.out.println("Ethan ordered a " + pizza + "\n");

		pizza = chicagoStore.orderPizza("veggie");
		System.out.println("Joel ordered a " + pizza + "\n");
	}

}
