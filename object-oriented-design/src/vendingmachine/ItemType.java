package vendingmachine;

public enum ItemType {
	soda(33), coke(25), pepsi(45);

	private Integer price;

	ItemType(int price) {
		this.price = price;
	}

	int getPrice() {
		return this.price;
	}

}
