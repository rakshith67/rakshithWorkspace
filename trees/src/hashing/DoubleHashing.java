package hashing;

import java.util.ArrayList;

public class DoubleHashing {

	String[] hashTable;

	int noOfCellsUsedInHashTable;

	public DoubleHashing() {
		hashTable = new String[13];
		noOfCellsUsedInHashTable = 0;
	}

	public int hashValue(String x, int M) {
		char ch[];
		ch = x.toCharArray();
		int i, sum;
		for (sum = 0, i = 0; i < x.length(); i++) {
			sum = sum + ch[i];
		}
		return sum % M;
	}

	int secondHashValue(String x, int M) {
		char ch[];
		ch = x.toCharArray();
		int xlength = x.length();
		int i, sum;
		for (sum = 0, i = 0; i < xlength; i++)
			sum += ch[i];
		while (sum > 13)
			sum = addAllTheDigitsTogether(sum);
		return sum % M;
	}

	private int addAllTheDigitsTogether(int sum) {
		int value = 0;
		while (sum > 0) {
			value = sum % 10;
			sum = sum / 10;
		}
		return value;
	}

	public double getLoadFactor() {
		return noOfCellsUsedInHashTable * 1.0 / hashTable.length;
	}

	public void insertKey(String value) {
		double loadFactor = getLoadFactor();
		if (loadFactor >= 0.75) {
			rehashKeys(value);
		} else {
			int x = hashValue(value, hashTable.length);
			int y = secondHashValue(value, hashTable.length);
			for (int i = 0; i < hashTable.length; i++) {
				int newIndex = (x + i * y) % hashTable.length;
				if (hashTable[newIndex] == null) {
					hashTable[newIndex] = value;
					break;
				}
			}
		}
		noOfCellsUsedInHashTable++;
	}

	public void rehashKeys(String newStringToBeInserted) {
		noOfCellsUsedInHashTable = 0;
		ArrayList<String> data = new ArrayList<String>();
		for (String s : hashTable) {
			if (s != null)
				data.add(s);
		}
		data.add(newStringToBeInserted);
		hashTable = new String[hashTable.length * 2];
		for (String s : data) {
			insertKey(s);
		}
	}

	public boolean searchKey(String value) {
		int x = hashValue(value, hashTable.length);
		int y = secondHashValue(value, hashTable.length);
		for (int i = 0; i < hashTable.length; i++) {
			int newIndex = (x + i * y) % hashTable.length;
			if (hashTable[newIndex] != null && hashTable[newIndex].equals(value)) {
				return true;
			}
		}
		return false;
	}

	public void deleteKeyFromHashTable(String value) {
		int x = hashValue(value, hashTable.length);
		int y = secondHashValue(value, hashTable.length);
		for (int i = 0; i < hashTable.length; i++) {
			int newIndex = (x + i * y) % hashTable.length;
			if (hashTable[newIndex] != null && hashTable[newIndex].equals(value)) {
				hashTable[newIndex] = null;
				return;
			}
		}
	}

	public void displayHashTable() {
		if (hashTable == null) {
			System.out.println("***********");
			return;
		} else {
			for (int i = 0; i < hashTable.length; i++) {
				System.out.println("Index:" + i + ".   Key:" + hashTable[i]);
			}
			System.out.println("***********");
		}
	}

	public void deleteHashTable() {
		hashTable = null;
	}

}
