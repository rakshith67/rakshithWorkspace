package splitwise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class BillService {

	private Map<String, Set<Owe>> bills = new HashMap<>();
	
	public BillService(int number) {
		for(int i = 1; i <= number; i++) {
			bills.put(String.valueOf(i), new HashSet<>());
		}
	}
	
	public boolean addBillForUser(String userId) {
		bills.put(userId, new HashSet<>());
		return true;
	}

	public boolean isSettled(String userId) {
		int totalAmountToGet = 0;
		Set<Owe> userOweSet = bills.get(userId);
		for (Owe owe : userOweSet) {
			totalAmountToGet += owe.getAmount();
		}

		int totalAmountToGive = 0;
		for (String id : bills.keySet()) {
			if (!id.equals(userId)) {
				for (Owe current : bills.get(id)) {
					if (current != null && current.getId().equals(userId)) {
						totalAmountToGive += current.getAmount();
					}
				}
			}
		}

		if (totalAmountToGet != 0 || totalAmountToGive != 0) {
			return false;
		}
		return true;
	}

	public void showPercentage(String userId) {
		int[] debts = new int[bills.size() + 1];

		Set<Owe> userOweSet = bills.get(userId);
		for (Owe owe : userOweSet) {
			debts[Integer.valueOf(owe.getId())] += owe.getAmount();
		}

		for (String id : bills.keySet()) {
			if (!id.equals(userId)) {
				for (Owe current : bills.get(id)) {
					if (current.getId().equals(userId)) {
						Integer index = Integer.valueOf(id);
						debts[index] -= current.getAmount();
					}
				}
			}
		}

		Integer userIndex = Integer.valueOf(userId);
		for (int i = 1; i < debts.length; i++) {
			if (i != userIndex) {
				if (debts[i] < 0) {
					System.out.println(i + " should get " + -debts[i] + " from " + userId);
				}
				if (debts[i] > 0) {
					System.out.println(i + " should give " + debts[i] + " to " + userId);
				}
			}
		}

	}

	public boolean addBill(String userId, SplitType type, List<Owe> oweList) {
		bills.putIfAbsent(userId, new HashSet<>());
		Set<Owe> set = bills.get(userId);
		for (Owe owe : oweList) {
			if (!owe.getId().equals(userId)) {
				Optional<Owe> oweInSet = set.stream().filter(current -> current.getId().equals(owe.getId()))
						.findFirst();
				if (!oweInSet.isPresent()) {
					set.add(owe);
					continue;
				}
				int amount = oweInSet.get().getAmount();
				oweInSet.get().setAmount(owe.getAmount() + amount);
			}
		}
		return true;
	}
}
