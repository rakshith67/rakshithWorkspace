package design;

import java.util.ArrayList;
import java.util.List;

public class ProductOfNumbersApp {

	public static void main(String[] args) {
		ProductOfNumbers productOfNumbers = new ProductOfNumbers();
		productOfNumbers.add(3);        // [3]
		productOfNumbers.add(0);        // [3,0]
		productOfNumbers.add(2);        // [3,0,2]
		productOfNumbers.add(5);        // [3,0,2,5]
		productOfNumbers.add(4);        // [3,0,2,5,4]
		productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
		productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
		productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
		productOfNumbers.add(8);        // [3,0,2,5,4,8]
		productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32 
	}
}

class ProductOfNumbers {

	List<Integer> list;

	public ProductOfNumbers() {
		list = new ArrayList<>();
	}

	public void add(int num) {
		if (num == 0) {	
			list.clear();
			return;
		}
		if (list.isEmpty())
			list.add(num);
		else
			list.add(list.get(list.size() - 1) * num);
	}

	public int getProduct(int k) {
		if (list.size() < k) {
			return 0;
		}
		if (k == list.size())
			return list.get(list.size() - 1);
		return list.get(list.size() - 1) / list.get(list.size() - 1 - k);
	}
}
