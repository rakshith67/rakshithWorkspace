package splitwise;

public class Owe {

	private String id;

	private int amount;

	public Owe(String id, int amount) {
		this.id = id;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "id: " + id + ", amount: " + amount;
	}
}
