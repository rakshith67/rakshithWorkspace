package vendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class VendingMachine {

	private static VendingMachine inventory;

	private static Map<ItemType, Integer> inventoryCount;
	private static Map<CoinType, Integer> denominations;

	public static VendingMachine getInstance() {
		if (inventory != null) {
			return inventory;
		}
		inventoryCount = new HashMap<>();
		denominations = new HashMap<>();
		return new VendingMachine();
	}

	public void addInventory(String type, int currentCount) {
		ItemType currentItem = ItemType.valueOf(type);
		if (currentItem == null) {
			System.out.println("Invalid item type");
			return;
		}
		Integer currentValue = inventoryCount.get(currentItem);
		if (currentValue == null) {
			inventoryCount.put(currentItem, currentCount);
		} else {
			inventoryCount.put(currentItem, currentValue + currentCount);
		}
	}

	public void addCoins(int coinValue, int numberOfCoins) {
		CoinType coinType = validCoin(coinValue);
		if (coinType == null) {
			return;
		}
		Integer value = denominations.get(coinType);
		if (value == null) {
			denominations.put(coinType, numberOfCoins);
		} else {
			denominations.put(coinType, value + numberOfCoins);
		}
	}

	public void removeCoins(int coinValue, int numberOfCoins) {
		CoinType coinType = validCoin(coinValue);
		if (coinType == null) {
			System.out.println("Please give a valid denomination");
			return;
		}
		Integer value = denominations.get(coinType);
		if (value == null || value < numberOfCoins) {
			System.out.println("Cannot remove " + numberOfCoins + " coins of " + coinType.getPrice()
					+ " because the count is less");
			// denominations.put(coinType, numberOfCoins);
		} else {
			denominations.put(coinType, value - numberOfCoins);
		}
	}

	private CoinType validCoin(int coinValue) {
		CoinType[] coinTypes = CoinType.values();
		for (CoinType coinType : coinTypes) {
			if (coinType.getPrice() == coinValue) {
				return coinType;
			}
		}
		return null;
	}

	public void showInventory() {
		for (Map.Entry<ItemType, Integer> item : inventoryCount.entrySet()) {
			ItemType itemType = item.getKey();
			System.out.println(
					"Item: " + itemType.name() + " Price: " + itemType.getPrice() + " Count: " + item.getValue());
		}
	}

	public List<Integer> buyItem(String item, List<Integer> coinValues) {
		ItemType itemType = null;
		List<Integer> result = new ArrayList<>();
		
		//check whether given item exists
		try {
			itemType = ItemType.valueOf(item);
		} catch (Exception e) {
			System.out.println(item + " not found");
		}

		if (itemType == null) {
			System.out.println("Invalid item type");
			return result;
		}

		Integer count = inventoryCount.get(itemType);

		//check whether given item inventory is greater than 0
		if (count == 0) {
			System.out.println("Not having the inventory for given item");
			return result;
		}

		//check whether all the given coins are valid
		boolean isValidCoins = isValid(coinValues);
		if (!isValidCoins) {
			System.out.println("Not valid coins");
			return result;
		}

		int totalSum = 0;
		for (Integer integer : coinValues) {
			totalSum += integer;
		}

		//check whether the given sum is less than the item price
		if (totalSum < itemType.getPrice()) {
			System.out.println("Given price is less");
			return result;
		}
		
		Integer toGiveChange = Math.abs(itemType.getPrice() - totalSum);
		
		for (Integer integer : coinValues) {
			CoinType type = validCoin(integer);
			denominations.put(type, denominations.getOrDefault(type, 0) + 1);
		}

		//check whether we can give change with available denominations
		boolean changeValidation = validateChangeOfGivenValues(toGiveChange);
		if (!changeValidation) {
			for (Integer integer : coinValues) {
				CoinType type = validCoin(integer);
				denominations.put(type, denominations.get(type) - 1);
			}
			System.out.println("Change not possible");
			return result;
		}

		inventoryCount.put(itemType, count - 1);

		calculateChange(result, toGiveChange);

		return result;

	}

	private boolean isValid(List<Integer> coinValues) {
		for (Integer coinValue : coinValues) {
			if (validCoin(coinValue) == null) {
				return false;
			}
		}
		return true;
	}

	private boolean validateChangeOfGivenValues(int toGiveChange) {
		Set<CoinType> set = denominations.keySet();
		Map<Integer, Integer> dummyDenominations = new HashMap<>();

		for (Entry<CoinType, Integer> entry : denominations.entrySet()) {
			dummyDenominations.put(entry.getKey().getPrice(), entry.getValue());
		}

		PriorityQueue<Integer> coins = new PriorityQueue<>((x1, x2) -> {
			return x2 - x1;
		});
		for (CoinType coinType : set) {
			coins.add(coinType.getPrice());
		}

		while (!coins.isEmpty() && toGiveChange > 0) {
			Integer currentCoin = coins.poll();
			if (currentCoin > toGiveChange) {
				continue;
			}
			int value = dummyDenominations.get(currentCoin);
			if (value <= 0) {
				continue;
			}
			toGiveChange -= currentCoin;
			dummyDenominations.put(currentCoin, value - 1);
			coins.add(currentCoin);

		}

		if (toGiveChange == 0) {
			return true;
		}

		return false;
	}

	private void calculateChange(List<Integer> result, int toGiveChange) {
		Set<CoinType> set = denominations.keySet();

		PriorityQueue<Integer> coins = new PriorityQueue<>((x1, x2) -> {
			return x2 - x1;
		});
		for (CoinType coinType : set) {
			coins.add(coinType.getPrice());
		}

		while (!coins.isEmpty() && toGiveChange > 0) {
			Integer currentCoin = coins.poll();
			if (currentCoin > toGiveChange) {
				continue;
			}
			CoinType coinType = validCoin(currentCoin);
			int value = denominations.get(coinType);
			if (value <= 0) {
				continue;
			}
			toGiveChange -= currentCoin;
			result.add(currentCoin);
			denominations.put(coinType, value - 1);
			coins.add(currentCoin);
		}

	}

}
