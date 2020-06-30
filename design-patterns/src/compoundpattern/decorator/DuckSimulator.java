package compoundpattern.decorator;

import compoundpattern.adaptor.Goose;
import compoundpattern.adaptor.GooseAdaptor;
import compoundpattern.ducks.DuckCall;
import compoundpattern.ducks.MallardDuck;
import compoundpattern.ducks.Quackable;
import compoundpattern.ducks.RedheadDuck;
import compoundpattern.ducks.RubberDuck;

public class DuckSimulator {

	public static void main(String[] args) {
		DuckSimulator simulator = new DuckSimulator();
		simulator.simulate();
	}

	void simulate() {
		Quackable mallardDuck = new QuackCounter(new MallardDuck());
		Quackable redheadDuck = new QuackCounter(new RedheadDuck());
		Quackable duckCall = new QuackCounter(new DuckCall());
		Quackable rubberDuck = new QuackCounter(new RubberDuck());
		Quackable gooseDuck = new QuackCounter(new GooseAdaptor(new Goose()));

		System.out.println("Duck Simulator");

		simulate(mallardDuck);
		simulate(redheadDuck);
		simulate(duckCall);
		simulate(rubberDuck);
		simulate(gooseDuck);

		System.out.println("Number of quacks = " + QuackCounter.getNumberOfQuacks());
	}

	void simulate(Quackable duck) {
		duck.quack();
	}
}
