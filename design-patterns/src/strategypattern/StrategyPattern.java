package strategypattern;

/**
 * 
 * This uses the composition principle by which the behaviors composites to the
 * duck. This is called as Strategy Pattern.
 * 
 * The Strategy Pattern defines a family of algorithms, encapsulates each one,
 * and makes them interchangeable. Strategy lets the algorithm vary
 * independently from clients that use it.
 * 
 * @author Rakshith
 *
 */
public class StrategyPattern {

	public static void main(String[] args) {
		Duck mallordDuck = new MallordDuck();
		mallordDuck.setFlyBehaviour(new FlyWithNoWings());
		mallordDuck.setQuackBehaviour(new Squeak());
		mallordDuck.fly();
		mallordDuck.quack();
		mallordDuck.swim();
		mallordDuck.display();
		Duck duck = new NormalDuck();
		duck.setFlyBehaviour(new FlyWithWings());
		duck.setQuackBehaviour(new Quack());
		duck.fly();
		duck.quack();
		duck.swim();
		duck.display();
	}
}

abstract class Duck {

	protected FlyBehaviour flyBehaviour;

	protected QuackBehaviour quackBehaviour;

	public void setFlyBehaviour(FlyBehaviour flyBehaviour) {
		this.flyBehaviour = flyBehaviour;
	}

	public void setQuackBehaviour(QuackBehaviour quackBehaviour) {
		this.quackBehaviour = quackBehaviour;
	}

	void swim() {
		System.out.println("Swimming");
	}

	abstract void display();

	void quack() {
		quackBehaviour.quack();
	}

	void fly() {
		flyBehaviour.fly();
	}

}

interface FlyBehaviour {
	void fly();
}

interface QuackBehaviour {
	void quack();
}

class FlyWithWings implements FlyBehaviour {

	@Override
	public void fly() {
		System.out.println("Flying with wings");
	}

}

class FlyWithNoWings implements FlyBehaviour {

	@Override
	public void fly() {
		System.out.println("Flying with no wings");
	}

}

class Quack implements QuackBehaviour {

	@Override
	public void quack() {
		System.out.println("Quacking");
	}

}

class Squeak implements QuackBehaviour {

	@Override
	public void quack() {
		System.out.println("Squeaking");
	}

}

class MallordDuck extends Duck {

	/*
	 * public MallordDuck() { flyBehaviour = new FlyWithNoWings(); quackBehaviour =
	 * new Squeak(); }
	 */

	@Override
	void display() {
		System.out.println("Mallord Duck");
	}

}

class NormalDuck extends Duck {

	/*
	 * public NormalDuck() { flyBehaviour = new FlyWithWings(); quackBehaviour = new
	 * Quack(); }
	 */

	@Override
	void display() {
		System.out.println("Normal Duck");
	}

}