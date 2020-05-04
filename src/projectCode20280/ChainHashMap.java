package projectCode20280;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
	// a fixed capacity array of UnsortedTableMap that serve as buckets
	private UnsortedTableMap<K, V>[] table; // initialized within createTable

	/** Creates a hash table with capacity 11 and prime factor 109345121. */
	public ChainHashMap() {
		super();
	}

	/** Creates a hash table with given capacity and prime factor 109345121. */
	public ChainHashMap(int cap) {
		super(cap);
	}

	/** Creates a hash table with the given capacity and prime factor. */
	public ChainHashMap(int cap, int p) {
		super(cap, p);
	}

	/** Creates an empty table having length equal to current capacity. */
	@Override
	@SuppressWarnings({ "unchecked" })
	protected void createTable() {
		table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
	}

	/**
	 * Returns value associated with key k in bucket with hash value h. If no such
	 * entry exists, returns null.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return associate value (or null, if no such entry)
	 */
	@Override
	protected V bucketGet(int h, K k) {

		//return key at h
		UnsortedTableMap<K,V> b = table[h];
		if(b == null)
			return null;
		else
			return b.get(k);

	}

	/**
	 * Associates key k with value v in bucket with hash value h, returning the
	 * previously associated value, if any.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @param v the value to be associated
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketPut(int h, K k, V v) {

		UnsortedTableMap<K,V> b = table[h];
		if(b == null)
			b = table[h] = new UnsortedTableMap<>();
		int temp = b.size();
		V val = b.put(k,v);
		int size = b.size() - temp;
		n += size; //Readjust size
		return val;
	}

	/**
	 * Removes entry having key k from bucket with hash value h, returning the
	 * previously associated value, if found.
	 * 
	 * @param h the hash value of the relevant bucket
	 * @param k the key of interest
	 * @return previous value associated with k (or null, if no such entry)
	 */
	@Override
	protected V bucketRemove(int h, K k) {

		UnsortedTableMap<K,V> b = table[h];
		if(b == null) //if bucket doesn't exist
			return null;
		else
		{
			int temp = b.size();
			V val = b.remove(k); //remove element
			int size = temp - b.size();
			n -= size; //readjust size
			return val; //return old value
		}
	}

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		SinglyLinkedList<Entry<K,V>> list = new SinglyLinkedList<>();

		for(int i = 0; i< capacity; i++)
		{
			if(table[i] != null)
			{
				for(Entry<K,V> entry : table[i].entrySet())
				{
					list.addFirst(entry); //If entry exists add it to linked list for each enry
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		//HashMap<Integer, String> m = new HashMap<Integer, String>();
		ChainHashMap<Integer, String> m = new ChainHashMap<Integer, String>();
		m.put(1, "One");
		m.put(10, "Ten");
		m.put(11, "Eleven");
		m.put(20, "Twenty");
		
		System.out.println("m: " + m);
		
		m.remove(11);
		System.out.println("m: " + m);
	}
}
