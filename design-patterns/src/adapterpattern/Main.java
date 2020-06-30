package adapterpattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * 
 * The Adapter Pattern converts the interface of a class into another interface
 * the clients expect. Adapter lets classes work together that couldn’t
 * otherwise because of incompatible interfaces.
 * 
 * @author rakshim1
 *
 */
public class Main {

	public static void main(String[] args) {
		Duck duck = new MallardDuck();

		Turkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey);

		System.out.println("The Turkey says...");
		turkey.gobble();
		turkey.fly();

		System.out.println("\nThe Duck says...");
		testDuck(duck);

		System.out.println("\nThe TurkeyAdapter says...");
		testDuck(turkeyAdapter);

		List<String> l = new ArrayList<String>(Arrays.asList(args));
		Enumeration<?> enumeration = new IteratorEnumeration(l.iterator());
		while (enumeration.hasMoreElements()) {
			System.out.println(enumeration.nextElement());
		}

		Vector<String> v = new Vector<String>(Arrays.asList(args));
		Iterator<?> iterator = new EnumerationIterator(v.elements());
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

	}

	static void testDuck(Duck duck) {
		duck.quack();
		duck.fly();
	}
}
