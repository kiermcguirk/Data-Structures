package projectCode20280;


public class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {
	// -------------- nested BSTNode class --------------
	// this extends the inherited LinkedBinaryTree.Node class
	protected static class BSTNode<E> extends Node<E> {
		int aux = 0;

		BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
			super(e, parent, leftChild, rightChild);
		}

		public int getAux() {
			return aux;
		}

		public void setAux(int value) {
			aux = value;
		}
	} // --------- end of nested BSTNode class ---------

	// positional-based methods related to aux field
	public int getAux(Position<Entry<K, V>> p) {
		return ((BSTNode<Entry<K, V>>) p).getAux();
	}

	public void setAux(Position<Entry<K, V>> p, int value) {
		((BSTNode<Entry<K, V>>) p).setAux(value);
	}

	// Override node factory function to produce a BSTNode (rather than a Node)
	@Override
	protected Node<Entry<K, V>> createNode(Entry<K, V> e, Node<Entry<K, V>> parent, Node<Entry<K, V>> left,
			Node<Entry<K, V>> right) {
		return new BSTNode<>(e, parent, left, right);
	}

	/** Relinks a parent node with its oriented child node. */
	private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
		child.addParent(parent);

		if(!makeLeftChild)
			parent.addRight(child);
		else
			parent.addLeft(child);
	}

	/**
	 * Rotates Position p above its parent. Switches between these configurations,
	 * depending on whether p is a or p is b.
	 * 
	 * <pre>
	 *          b                  a
	 *         / \                / \
	 *        a  t2             t0   b
	 *       / \                    / \
	 *      t0  t1                 t1  t2
	 * </pre>
	 * 
	 * Caller should ensure that p is not the root.
	 */
	public void rotate(Position<Entry<K, V>> p) {
		Node<Entry<K,V>> xNode = validate(p);
		Node<Entry<K,V>> yNode = xNode.getParent(); // Parent node
		Node<Entry<K,V>> zNode = xNode.getParent().getParent(); //Parent of parent

		if(zNode == null) //If no grandparent
		{
			root = xNode;       //promote xNode to root of tree
			xNode.addParent(null); //prune parent node
		}
		else
		{
			boolean makeleft = yNode == zNode.getLeft();
			relink(zNode,xNode, makeleft); //make xNode child of zNode
		}
		if(xNode == yNode.getLeft()) //if xNode = the root of yNodes left subtree
		{
			relink(yNode, xNode.getRight(),true); //make xNode's right subtree yNode's left subtree
			relink(xNode,yNode,false); //make yNode xNode's right subtree
		}
		else
		{
			relink(yNode, xNode.getLeft(), false); //Make xNode's left subtree yNode's right subtree
			relink(xNode,yNode, true); //Make xNodes left subtree yNode
		}
	}

	/**
	 *
	 * Returns the Position that becomes the root of the restructured subtree.
	 *
	 * Assumes the nodes are in one of the following configurations:
	 * 
	 * <pre>
	 *     z=a                 z=c           z=a               z=c
	 *    /  \                /  \          /  \              /  \
	 *   t0  y=b             y=b  t3       t0   y=c          y=a  t3
	 *      /  \            /  \               /  \         /  \
	 *     t1  x=c         x=a  t2            x=b  t3      t0   x=b
	 *        /  \        /  \               /  \              /  \
	 *       t2  t3      t0  t1             t1  t2            t1  t2
	 * </pre>
	 * 
	 * The subtree will be restructured so that the node with key b becomes its
	 * root.
	 * 
	 * <pre>
	 *           b
	 *         /   \
	 *       a       c
	 *      / \     / \
	 *     t0  t1  t2  t3
	 * </pre>
	 * 
	 * Caller should ensure that x has a grandparent.
	 */
	public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
		Position<Entry<K,V>> xNode = x;
		Position<Entry<K,V>> yNode = parent(xNode);
		Position<Entry<K,V>> zNode = parent(yNode);

		if((xNode == right(yNode)) && (yNode == right(zNode))) //If xNode matches yNode;s right child and yNode = zNode's right child
		{
			rotate(yNode); //Rotate yNode
			return yNode; //Return yNode as new root
		}
		else
		{
			rotate(xNode); //rotate xNode twice
			rotate(xNode);
			return xNode; //Return xNode as new root
		}

	}
} 
