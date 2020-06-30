package proxypattern;

import java.rmi.Naming;

/**
 * 
 * The Proxy Pattern provides a surrogate or placeholder for another object to
 * control access to it.
 * 
 * Use the Proxy Pattern to create a representative object that controls access
 * to another object, which may be remote, expensive to create, or in need of
 * securing.
 * 
 * @author rakshim1
 *
 */
public class Main {

	public static void main(String[] args) {
		GumballMachineRemote gumballMachine = null;
		int count;

		if (args.length < 2) {
			System.out.println("GumballMachine <name> <inventory>");
			System.exit(1);
		}

		try {
			count = Integer.parseInt(args[1]);

			gumballMachine = new GumballMachine(args[0], count);
			Naming.rebind("rmi://" + args[0] + "/gumballmachine", gumballMachine);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
