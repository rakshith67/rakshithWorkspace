package compoundpattern.adaptor;

import compoundpattern.ducks.Quackable;

public class GooseAdaptor implements Quackable {

	Goose goose;
	
	public GooseAdaptor(Goose goose) {
		this.goose = goose;
	}

	@Override
	public void quack() {
		goose.honk();
	}

}
