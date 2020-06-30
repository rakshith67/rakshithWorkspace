package templatepattern;

/**
 * The Template Method Pattern defines the skeleton of an algorithm in a method,
 * deferring some steps to subclasses. Template Method lets subclasses redefine
 * certain steps of an algorithm without changing the algorithm’s structure.
 * 
 * This pattern is all about creating a template for an algorithm. What’s a
 * template? As you’ve seen it’s just a method; more specifically, it’s a method
 * that defines an algorithm as a set of steps.
 * 
 * A hook is a method that is declared in the abstract class, but only given an
 * empty or default implementation. This gives subclasses the ability to “hook
 * into” the algorithm at various points, if they wish; a subclass is also free
 * to ignore the hook.
 * 
 * Hollywood Principle: Don't call us, We'll call you. The Hollywood Principle
 * gives us a way to prevent “dependency rot.” Dependency rot happens when you
 * have high-level components depending on low-level components depending on
 * high-level components depending on sideways components depending on low-level
 * components, and so on
 * 
 * @author rakshim1
 *
 */
public class Main {

	public static void main(String[] args) {
		Tea tea = new Tea();
		Coffee coffee = new Coffee();

		System.out.println("\nMaking tea...");
		tea.prepareRecipe();

		System.out.println("\nMaking coffee...");
		coffee.prepareRecipe();

		TeaWithHook teaHook = new TeaWithHook();
		CoffeeWithHook coffeeHook = new CoffeeWithHook();

		System.out.println("\nMaking tea...");
		teaHook.prepareRecipe();

		System.out.println("\nMaking coffee...");
		coffeeHook.prepareRecipe();
	}
}
