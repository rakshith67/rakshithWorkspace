package greedy;

class Weight implements Comparable<Weight> {
	public int quantity;
	public int price;
	public double density;

	public Weight(int quantity, int price) {
		this.quantity = quantity;
		this.price = price;
		density = price / (double) quantity;
	}

	public int compareTo(Weight act) {
		return Double.compare(act.density, density);
	}

}
