package hashing;

public class HashingMain {

	public static void main(String[] args) {

		DirectChaining directChaining = new DirectChaining();

		directChaining.insertKeyInHashTable("The");
		directChaining.insertKeyInHashTable("quick");
		directChaining.insertKeyInHashTable("brown");
		directChaining.insertKeyInHashTable("fox");
		directChaining.insertKeyInHashTable("over");
		directChaining.insertKeyInHashTable("lazy");
		directChaining.display();

		directChaining.insertKeyInHashTable("fox");
		directChaining.display();

		directChaining.insertKeyInHashTable("fox");
		directChaining.display();

		directChaining.insertKeyInHashTable("fox");
		directChaining.display();

		directChaining.insertKeyInHashTable("fox");
		directChaining.display();

		LinearProbing linearProbing = new LinearProbing();

		linearProbing.insertKey("The");
		linearProbing.insertKey("quick");
		linearProbing.insertKey("brown");
		linearProbing.insertKey("fox");
		linearProbing.insertKey("over");
		linearProbing.insertKey("lazy");
		linearProbing.displayHashTable();

		linearProbing.insertKey("fox");
		linearProbing.displayHashTable();

		linearProbing.insertKey("fox");
		linearProbing.displayHashTable();

		linearProbing.insertKey("fox");
		linearProbing.displayHashTable();

		linearProbing.insertKey("fox");
		linearProbing.displayHashTable();

		linearProbing.insertKey("fox");
		linearProbing.displayHashTable();

		linearProbing.searchKey("jump");
		linearProbing.searchKey("brown");

		linearProbing.deleteKey("jump");
		linearProbing.deleteKey("quick");
		linearProbing.displayHashTable();

		linearProbing.deleteHashTable();
		linearProbing.displayHashTable();

		QuadraticProbing quadraticProbing = new QuadraticProbing();

		quadraticProbing.insertKey("The");
		quadraticProbing.insertKey("quick");
		quadraticProbing.insertKey("brown");
		quadraticProbing.insertKey("fox");
		quadraticProbing.insertKey("over");
		quadraticProbing.insertKey("lazy");
		quadraticProbing.displayHashTable();

		quadraticProbing.insertKey("fox");
		quadraticProbing.displayHashTable();

		quadraticProbing.insertKey("fox");
		quadraticProbing.displayHashTable();

		quadraticProbing.insertKey("fox");
		quadraticProbing.displayHashTable();

		quadraticProbing.insertKey("fox");
		quadraticProbing.displayHashTable();

		quadraticProbing.insertKey("fox");
		quadraticProbing.displayHashTable();

		quadraticProbing.searchKey("jump");
		quadraticProbing.searchKey("brown");

		quadraticProbing.deleteKey("jump");
		quadraticProbing.displayHashTable();

		quadraticProbing.deleteKey("quick");
		quadraticProbing.displayHashTable();

		quadraticProbing.deleteHashTable();
		quadraticProbing.displayHashTable();

		DoubleHashing doubleHashing = new DoubleHashing();

		doubleHashing.insertKey("The");
		doubleHashing.insertKey("quick");
		doubleHashing.insertKey("brown");
		doubleHashing.insertKey("fox");
		doubleHashing.insertKey("over");
		doubleHashing.insertKey("lazy");
		doubleHashing.displayHashTable();

		doubleHashing.insertKey("fox");
		doubleHashing.displayHashTable();

		doubleHashing.insertKey("fox");
		doubleHashing.displayHashTable();

		doubleHashing.insertKey("fox");
		doubleHashing.displayHashTable();

		doubleHashing.insertKey("fox");
		doubleHashing.displayHashTable();

		doubleHashing.insertKey("fox");
		doubleHashing.displayHashTable();

		doubleHashing.searchKey("jump");
		doubleHashing.searchKey("brown");

		doubleHashing.deleteKeyFromHashTable("jump");
		doubleHashing.deleteKeyFromHashTable("quick");
		doubleHashing.displayHashTable();

		doubleHashing.deleteHashTable();
		doubleHashing.displayHashTable();

	}

}
