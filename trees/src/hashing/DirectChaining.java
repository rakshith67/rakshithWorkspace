package hashing;

import java.util.LinkedList;

public class DirectChaining {
	LinkedList<String>[] hashTable;
	int maximumChainSize = 5;

	@SuppressWarnings("unchecked")
	DirectChaining() {
		hashTable = new LinkedList[13];
	}

	public int hashValue(String x, int M) {
		char ch[];
		ch = x.toCharArray();
		int i, sum = 0;
		for (i = 0; i < x.length(); i++) {
			sum = sum + ch[i];
		}
		return sum % M;
	}

	public void insertKeyInHashTable(String value) {
		int hashValue = hashValue(value, hashTable.length);
		if (hashTable[hashValue] == null) {
			LinkedList<String> linkedList = new LinkedList<>();
			hashTable[hashValue] = linkedList;
			linkedList.add(value);
		} else {
			hashTable[hashValue].add(value);
		}
	}

	public void insertKeyInHashTableNoDuplicates(String value) {
		int hashValue = hashValue(value, hashTable.length);
		if (hashTable[hashValue] == null) {
			LinkedList<String> linkedList = new LinkedList<>();
			hashTable[hashValue] = linkedList;
		}
		LinkedList<String> linkedList = hashTable[hashValue];
		if (!linkedList.contains(value)) {
			linkedList.add(value);
		}
	}

	public boolean searchKeyInHashTable(String value) {
		int hashValue = hashValue(value, hashTable.length);
		if (hashTable[hashValue] != null && hashTable[hashValue].contains(value)) {
			return true;
		}
		return false;
	}

	public boolean deleteKeyInHashTable(String value) {
		int hashValue = hashValue(value, hashTable.length);
		if (hashTable[hashValue] != null && hashTable[hashValue].contains(value)) {
			return hashTable[hashValue].remove(value);
		}
		return false;
	}

	public void display() {
		if (hashTable == null) {
			System.out.println("****************");
		} else {
			for (int i = 0; i < hashTable.length; i++) {
				if (hashTable[i] != null) {
					System.out.print(i + " : ");
					for (String string : hashTable[i]) {
						System.out.print(string + " , ");
					}
					System.out.println();
				}
			}
			System.out.println("****************");
		}
	}

	public void deleteHashTable() {
		hashTable = null;
	}

}
