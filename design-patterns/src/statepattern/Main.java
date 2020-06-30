package statepattern;

/**
 * 
 * The State Pattern allows an object to alter its behavior when its internal
 * state changes. The object will appear to change its class.
 * 
 * Think of the State Pattern as an alternative to putting lots of conditionals
 * in your context; by encapsulating the behaviors within state objects, you can
 * simply change the state object in context to change its behavior.
 * 
 * The Context gets its behavior by delegating to the current state object it is
 * composed with.
 * 
 * The State and Strategy Patterns have the same class diagram, but they differ
 * in intent.
 * 
 * State transitions can be controlled by the State classes or by the Context
 * classes. Using the State Pattern will typically result in a greater number of
 * classes in your design.
 * 
 * @author rakshim1
 *
 */
public class Main {

	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(10);

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
	}

}
