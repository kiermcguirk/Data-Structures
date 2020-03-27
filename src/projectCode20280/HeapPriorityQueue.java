package projectCode20280;

/*
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

  /** Creates an empty priority queue based on the natural ordering of its keys. */
  public HeapPriorityQueue() { super(); }

  /**
   * Creates an empty priority queue using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the priority queue
   */
  public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

  protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

  /**
   * Creates a priority queue initialized with the respective
   * key-value pairs.  The two arrays given will be paired
   * element-by-element. They are presumed to have the same
   * length. (If not, entries will be created only up to the length of
   * the shorter of the arrays)
   *
   * @param keys   an array of the initial keys for the priority queue
   * @param values an array of the initial values for the priority queue
   */
  public HeapPriorityQueue(K[] keys, V[] values) {
    super();
    for (int i = 0; i < Math.min(keys.length, values.length); i++){
      heap.add(new PQEntry<>(keys[i], values[i]));

      heapify();

    }
  }

  // protected utilities
  protected int parent(int j) {
    return (j - 1) / 2;
  }

  protected int left(int j) {
    return (2 * j) + 1;
  }

  protected int right(int j) {
    return (2 * j) + 2;
  }

  protected boolean hasLeft(int j) {
    return left(j) < heap.size();
  }

  protected boolean hasRight(int j) { return right(j) < heap.size(); }




  /**
   * Exchanges the entries at indices i and j of the array list.
   */
  protected void swap(int i, int j) {
    Entry<K,V> temp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, temp);
  }

  /**
   * Moves the entry at index j higher, if necessary, to restore the heap property.
   */
  protected void upheap(int j) {
    while (j > 0){
      int p = parent(j);
      if (compare(heap.get(j), heap.get(p)) >= 0)
        break;

      swap(j,p);

      j = p;
    }

  }

  /**
   * Moves the entry at index j lower, if necessary, to restore the heap property.
   */
  protected void downheap(int j) {
    while (hasLeft(j)){
      int leftIndx = left(j);
      int smallIndx = leftIndx; //left index is smaller
      if (hasRight(j))
      {
        int rightIndx = right(j);
        smallIndx = rightIndx; //right index is smaller
      }
      if (compare(heap.get(smallIndx), heap.get(j)) >= 0)
        break; //check if heap has been restored
      swap(j, smallIndx);
      j = smallIndx;
    }
  }

  /**
   * Performs a bottom-up construction of the heap in linear time.
   */
  protected void heapify() {
    int startIndx = parent(size()-1);
    for (int i = startIndx; i >= 0; i--){
      downheap(i);
    }
  }

  // public methods

  /**
   * Returns the number of items in the priority queue.
   * @return number of items
   */
  @Override
  public int size() { return heap.size(); }

  /**
   * Returns (but does not remove) an entry with minimal key.
   *
   * @return entry having a minimal key (or null if empty)
   */
  @Override
  public Entry<K, V> min() {
    if (heap.isEmpty()) return null;

    else return heap.get(0);
  }

  /**
   * Inserts a key-value pair and return the entry created.
   *
   * @param key   the key of the new entry
   * @param value the associated value of the new entry
   * @return the entry storing the new key-value pair
   * @throws IllegalArgumentException if the key is unacceptable for this queue
   */
  @Override
  public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
    checkKey(key);

    Entry<K,V> newValue = new PQEntry<>(key,value);

    heap.add(newValue); //add value at the end

    upheap(heap.size()-1); //upheap new list
    return newValue;
  }

  /**
   * Removes and returns an entry with minimal key.
   *
   * @return the removed entry (or null if empty)
   */
  @Override
  public Entry<K, V> removeMin() {
    if (heap.isEmpty()) return null;
    Entry<K, V> answer = heap.get(0);
    swap(0, heap.size()-1); //store min value at the end
    heap.remove(heap.size()-1); //remove last value
    downheap(0); //new root
    return answer;
  }



  public static <E> void pqSort(LinkedList<E> S, PriorityQueue<E,?> P)
  {
    int n = S.size();
    for(int i = 0; i < n; i++)
    {
        //E element = S.remove(S.first());
    }
  }
}