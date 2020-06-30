package compoundpattern.factory;

import compoundpattern.decorator.QuackCounter;
import compoundpattern.ducks.DuckCall;
import compoundpattern.ducks.MallardDuck;
import compoundpattern.ducks.Quackable;
import compoundpattern.ducks.RedheadDuck;
import compoundpattern.ducks.RubberDuck;

public class CountingDuckFactory extends AbstractDuckFactory {

	public Quackable createMallardDuck() {
		return new QuackCounter(new MallardDuck());
	}

	public Quackable createRedheadDuck() {
		return new QuackCounter(new RedheadDuck());
	}

	public Quackable createDuckCall() {
		return new QuackCounter(new DuckCall());
	}

	public Quackable createRubberDuck() {
		return new QuackCounter(new RubberDuck());
	}

}
