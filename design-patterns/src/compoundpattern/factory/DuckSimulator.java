package compoundpattern.factory;

import compoundpattern.adaptor.Goose;
import compoundpattern.adaptor.GooseAdaptor;
import compoundpattern.decorator.QuackCounter;
import compoundpattern.ducks.Quackable;

public class DuckSimulator {

	public static void main(String[] args) {
		DuckSimulator simulator = new DuckSimulator();
		AbstractDuckFactory duckFactory = new CountingDuckFactory();

		simulator.simulate(duckFactory);
	}

	void simulate(AbstractDuckFactory duckFactory) {
		Quackable mallardDuck = duckFactory.createMallardDuck();
		Quackable redheadDuck = duckFactory.createRedheadDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable gooseDuck = new GooseAdaptor(new Goose());

		System.out.println("\nDuck Simulator: With Abstract Factory");

		simulate(mallardDuck);
		simulate(redheadDuck);
		simulate(duckCall);
		simulate(rubberDuck);
		simulate(gooseDuck);

		System.out.println("The ducks quacked " + QuackCounter.getNumberOfQuacks() + " times");
	}

	void simulate(Quackable duck) {
		duck.quack();
	}
}
