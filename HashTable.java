package Assignment3;

//HashTable.java

public class HashTable<Key, Value> {
 // these are parallel arrays, so indices in one correspond to the other
 private Value[] values;
 private Key[] keys;
 
 private int maxSize;
 private int entries;
 
 public HashTable(int maxSize) {
     this.maxSize = maxSize;
     entries = 0;
     // make the arrays (with generic array workaround)
     values = (Value[]) new Object[maxSize];
     keys = (Key[]) new Object[maxSize];
 }

 // insert a key/value pair
 public void insert(Key key, Value value) {
	 //Check if entries is more than half of maxSize
	 if (entries > (maxSize/2)) {
		expand();
	 }
	 
     // get the starting index from hash function
     int index = Math.abs(key.hashCode()) % maxSize;

     // linear probe until spot is open
     while (values[index] != null) {
         index = (index + 1) % maxSize;
     }
 
     // now insert this pair here
     values[index] = value;
     keys[index] = key;
     entries ++;
 }

 // lookup a value by its key
 public Value lookup(Key key) {
     // get the starting index from hash function
     int index = Math.abs(key.hashCode()) % maxSize;

     // loop while there is data here
     while (keys[index] != null) {
         // if the key here matches target, return corresponding value
         if (keys[index].equals(key)) {
             return values[index];
         }

         // move to next index to search (with wrap around)
         index = (index + 1) % maxSize;
     }

     // if we fell off the end, the key is not here
     return null;
 }
 
 public Value[] getValues() {
	return values;
}

public void setValues(Value[] values) {
	this.values = values;
}

public Key[] getKeys() {
	return keys;
}

public void setKeys(Key[] keys) {
	this.keys = keys;
}

private  void expand() {
	 int entriesExp= 0;
	 int newSize = maxSize *2;
	 Value[] valuesExp = (Value[]) new Object[newSize];
	 Key[] keysExp = (Key[]) new Object[newSize];

	 
//Insert into new table
	 for (int i = 0; i < keys.length; i++) {
		 if (keys[i] != null) {
			 //System.out.println(keys[i]);
		 
	 int indexExp = Math.abs(keys[i].hashCode()) % newSize;
	 
	 // linear probe until spot is open
     while (valuesExp[indexExp] != null) {
         indexExp = (indexExp + 1) % newSize;
     }
 
     // now insert this pair here
     valuesExp[indexExp] = values[i];
     keysExp[indexExp] = keys[i];
     entriesExp++;
		 } 
     
 }
	 
	 values = valuesExp;
	 keys = keysExp;
     maxSize = newSize;
     entries = entriesExp;
	 
	
 }
 
 public void print() {
	 for (int i = 0; i < keys.length; i++) {
	if(keys[i]!= null) {
			System.out.println(keys[i]);
	 }
	 }
 }

public int getMaxSize() {
	return maxSize;
}


public int getEntries() {
	return entries;
}

 
}
