package splitwise;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private List<User> users = new ArrayList<>();

	private User admin;

	private BillService billService;

	public Group(User admin, List<User> users) {
		this.admin = admin;
		this.users = users;
		this.users.add(admin);
		billService = new BillService(users.size() + 1);
	}

	public boolean isAdmin(User user) {
		if (user == null) {
			return false;
		}
		if (admin.getId().equals(user.getId())) {
			return true;
		}
		return false;
	}

	public boolean addUser(User user) {
		if (user == null) {
			return false;
		}
		users.add(user);
		billService.addBillForUser(user.getId());
		return true;
	}

	public boolean removeUser(String userId) {
		if (userId == null) {
			return false;
		}
		if (!billService.isSettled(userId)) {
			System.out.println("User with id : " + userId + " has dues");
			return false;
		}
		User current = users.stream().filter(user -> userId.equals(user.getId())).findFirst().get();
		if (current != null) {
			users.remove(current);
			return true;
		}
		return false;
	}

	public void showPercentage(String userId) {
		billService.showPercentage(userId);
	}


	public boolean addBill(String userId, SplitType type, List<Owe> oweList) {
		return billService.addBill(userId, type, oweList);
	}

}
