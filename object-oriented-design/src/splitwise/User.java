package splitwise;

public class User {

	private String id;

	private String name;

	private boolean admin;

	public User(String id, String name, boolean admin) {
		this.id = id;
		this.name = name;
		this.admin = admin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return admin;
	}

}
