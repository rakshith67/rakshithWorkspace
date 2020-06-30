package compoundpattern.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import compoundpattern.ducks.Quackable;

public class Flock implements Quackable {

	List<Quackable> quackableList = new ArrayList<>();

	public void add(Quackable quacker) {
		quackableList.add(quacker);
	}

	public void quack() {
		Iterator<Quackable> iterator = quackableList.iterator();
		while (iterator.hasNext()) {
			Quackable quacker = iterator.next();
			quacker.quack();
		}
	}

}
