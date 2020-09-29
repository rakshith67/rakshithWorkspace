package hashing;

import java.util.ArrayList;

public class QuadraticProbing {

	String[] hashTable;
	int noOfCellsUsedInHashTable;

	public QuadraticProbing() {
		hashTable = new String[13];
		noOfCellsUsedInHashTable = 0;
	}

	public int hashValue(String x, int M) {
		char ch[];
		ch = x.toCharArray();
		int i, sum;
		for (sum = 0, i = 0; i < x.length(); i++)
			sum += ch[i];
		return sum % M;
	}

	public double getLoadFactor() {
		return noOfCellsUsedInHashTable * 1.0 / hashTable.length;
	}

	public void insertKey(String value) {
		double loadFactor = getLoadFactor();
		if (loadFactor >= 0.75) {
			rehashKeys(value);
		} else {
			int hashValue = hashValue(value, hashTable.length);
			int counter = 0;
			for (int i = hashValue; i < hashValue + hashTable.length; i++) {
				int index = (i + (counter * counter)) % hashTable.length;
				if (hashTable[index] == null) {
					hashTable[index] = value;
					break;
				}
				counter++;
			}
		}
		noOfCellsUsedInHashTable++;
	}

	public boolean searchKey(String value) {
		int hashValue = hashValue(value, hashTable.length);
		int counter = 0;
		for (int i = hashValue; i < hashValue + hashTable.length; i++) {
			int index = (i + (counter * counter)) % hashTable.length;
			if (hashTable[index] != null) {
				return true;
			}
			counter++;
		}
		return false;
	}

	public boolean deleteKey(String value) {
		int hashValue = hashValue(value, hashTable.length);
		int counter = 0;
		for (int i = hashValue; i < hashValue + hashTable.length; i++) {
			int index = (i + (counter * counter)) % hashTable.length;
			if (hashTable[index] != null && hashTable[index].equals(value)) {
				hashTable[index] = null;
				return true;
			}
			counter++;
		}
		return false;
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
