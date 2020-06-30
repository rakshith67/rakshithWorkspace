package compoundpattern.adaptor;

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
		Quackable mallardDuck = new MallardDuck();
		Quackable redheadDuck = new RedheadDuck();
		Quackable duckCall = new DuckCall();
		Quackable rubberDuck = new RubberDuck();
		Quackable gooseDuck = new GooseAdaptor(new Goose());

		System.out.println("Duck Simulator");

		simulate(mallardDuck);
		simulate(redheadDuck);
		simulate(duckCall);
		simulate(rubberDuck);
		simulate(gooseDuck);
	}

	void simulate(Quackable duck) {
		duck.quack();
	}
}
