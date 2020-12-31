package splitwise;

import java.util.ArrayList;
import java.util.List;

public class SplitwiseApplication {

	public static void main(String[] args) {
		SplitwiseApp app = new SplitwiseApp();
		
		User user1 = app.createUser("Michael", "1");
		User user2 = app.createUser("Jane", "2");
		User user3 = app.createUser("Shawn", "3");
		app.createGroupAndAddToList(user1, "MyGroup");
		app.addUserToGroup("MyGroup", user2);
		app.addUserToGroup("MyGroup", user3);
		
		Owe owe = new Owe("2", 300);
		List<Owe> list = new ArrayList<>();
		list.add(owe);
		app.addBill("MyGroup", "1", SplitType.EQUAL, list);
		
		list.clear();
		
		Owe owe2 = new Owe("1", 200);
		Owe owe3 = new Owe("3", 50);
		list.add(owe2);
		list.add(owe3);
		app.addBill("MyGroup", "2", SplitType.FIXED, list);
		
		app.showPercentage("MyGroup", "1");
		app.showPercentage("MyGroup", "2");
		app.showPercentage("MyGroup", "3");
	}

}
