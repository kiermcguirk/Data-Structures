package projectCode20280;

import java.util.Comparator;

public class SplayTreeMap<K,V> extends TreeMap<K,V> {

    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();

    
	  /** Constructs an empty map using the natural ordering of keys. */
	  public SplayTreeMap() { super(); }

	  /**
	   * Constructs an empty map using the given comparator to order keys.
	   * @param comp comparator defining the order of keys in the map
	   */
	  public SplayTreeMap(Comparator<K> comp) { super(comp); }

	  /** Utility used to rebalance after a map operation. */
	  private void splay(Position<Entry<K,V>> p) {
		 while(!isRoot(p))
		 {
		 	Position<Entry<K,V>> parent = parent(p);
		 	Position<Entry<K,V>> grandParent = parent(parent);
		 	//boolean zzParent =  (parent == left(grandParent)) == (p == left(parent));

		 	if(grandParent == null) //if there's no grandparent zig
			{
				rotate(p);
			}
		 	else if(/*zzParent*/ (parent == left(grandParent)) == (p == left(parent))) // if parent = the grandparent's left subtree && p = the parent's left subtree then zig-zig
			 {
			 	rotate(parent);
			 	rotate(p);
			 }
		 	else //zig-zag
			{
				rotate(p); //Promote p's position twice
				rotate(p);
			}
		 }
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after a node access. */
	  @Override
	  protected void rebalanceAccess(Position<Entry<K,V>> p) {
		  //If p is external position
		  if(isExternal(p))
		  	p = parent(p); //set p to its parent

		  if(p != null) //If position is not empty
		  {
		  	splay(p);
		  }
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
	  @Override
	  protected void rebalanceInsert(Position<Entry<K,V>> p) {
		  splay(p);
	  }

	  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
	  @Override
	  protected void rebalanceDelete(Position<Entry<K,V>> p) {
		  if (!isRoot(p))
			  splay(parent(p));
	  }

	  public String toString()
	  {
	  	if(tree == null)
		{
			return "";
		}
	  	else
		{
			return tree.toString();
		}
	  }
	}
