package splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitwiseApp {

	private Map<String, Group> groups = new HashMap<>();

	public User createUser(String name, String id) {
		User user = new User(id, name, false);
		return user;
	}

	public boolean createGroupAndAddToList(User admin, String name) {
		Group group = new Group(admin, new ArrayList<>());
		groups.put(name, group);
		return true;
	}

	public boolean addUserToGroup(String groupName, User user) {
		Group group = groups.get(groupName);
		if (group == null) {
			return false;
		}
		return group.addUser(user);
	}

	public boolean removeUserFromGroup(String groupName, User user) {
		Group group = groups.get(groupName);
		if (group == null) {
			return false;
		}
		return group.removeUser(user.getId());
	}

	public boolean addBill(String groupName, String userId, SplitType type, List<Owe> oweList) {
		Group group = groups.get(groupName);
		return group.addBill(userId, type, oweList);
	}

	public void showPercentage(String groupName, String userId) {
		Group group = groups.get(groupName);
		group.showPercentage(userId);
	}

}
