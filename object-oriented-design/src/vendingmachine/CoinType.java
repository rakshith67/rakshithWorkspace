package vendingmachine;

public enum CoinType {

	c1(1), c2(5), c3(10), c4(20);

	private Integer price;

	CoinType(int price) {
		this.price = price;
	}

	int getPrice() {
		return this.price;
	}
}
