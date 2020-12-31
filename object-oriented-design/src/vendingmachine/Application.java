package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		VendingMachine vendingMachine = VendingMachine.getInstance();
		vendingMachine.addInventory("coke", 5);
		vendingMachine.addInventory("pepsi", 5);
		vendingMachine.addInventory("soda", 5);
		vendingMachine.addCoins(1, 10);
		vendingMachine.addCoins(5, 1);
		vendingMachine.addCoins(10, 10);
		vendingMachine.showInventory();
		List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(10);
		list.add(10);
		list.add(10);
		
		List<Integer> result = vendingMachine.buyItem("soda", list);
		
		for(Integer integer: result) {
			System.out.println(integer);
		}
		vendingMachine.showInventory();
	}

}
