package projectCode20280;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {


  /** Nested static class for a binary tree node. */
  protected static class Node<E> implements Position<E>
  {
    private Node<E> parent;
	  private E element;
	  private Node<E> left;
	  private Node<E> right;

    public Node(E e, Node<E> parent, Node<E> left, Node<E> right)
    {
      this.element = e;
      this.parent = parent;
      this.left = left;
      this.right = right;
    }

    public E getElement() throws IllegalStateException {
      return (E) this.element;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder().append(element);
      return sb.toString();

    }
    public Node<E> getParent() {
      return this.parent;
    }

    public Node<E> addRoot(E e)
    {
      return new Node<E>(e,null,null,null);
    }
    public Node<E> getLeft(){return left;}
    public Node<E> getRight(){return right;}
    public void addLeft(Node<E> leftChild) { left = leftChild; }
    public void addRight(Node<E> rightChild) { right = rightChild;}
    public void setElement(E e){ element = e;}
    public void addParent(Node<E> parent){this.parent = parent;}

  }

  public void createLevelOrder(E[] array)
  {
    root = createLevelOrderHelper(array,root,0);
  }

  private Node<E> createLevelOrderHelper(E[] array, Node<E> node, int i) {
    if(i < array.length)
    {
      Node<E> n = createNode(array[i],node,null,null);
      n.left = createLevelOrderHelper(array, n.left, 2 * i+1);
      n.right = createLevelOrderHelper(array,n.right, 2* i+2);
      size++;
      return n;
    }
    return node;
  }

  /** Factory function to create a new node storing element e. */
  protected Node<E> createNode(E e, Node<E> parent,
                                  Node<E> left, Node<E> right) {

    return new Node<E>(e, parent, left, right);
  }

  // LinkedBinaryTree instance variables
  /** The root of the binary tree */
  protected Node<E> root;  // root of the tree

  /** The number of nodes in the binary tree */
  private int size = 0;              // number of nodes in the tree

  // constructor
  /** Construts an empty binary tree. */
  public LinkedBinaryTree() { }      // constructs an empty binary tree

  // nonpublic utility
  /**
   * Verifies that a Position belongs to the appropriate class, and is
   * not one that has been previously removed. Note that our current
   * implementation does not actually verify that the position belongs
   * to this particular list instance.
   *
   * @param p   a Position (that should belong to this tree)
   * @return    the underlying Node instance for the position
   * @throws IllegalArgumentException if an invalid position is detected
   */
  protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
    Node<E> node = (Node<E>) p;       // safe cast
    if (node.getParent() == node)     // our convention for defunct node
      throw new IllegalArgumentException("p is no longer in the tree");
    return node;
  }

  // accessor methods (not already implemented in AbstractBinaryTree)
  /**
   * Returns the number of nodes in the tree.
   * @return number of nodes in the tree
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns the root Position of the tree (or null if tree is empty).
   * @return root Position of the tree (or null if tree is empty)
   */
  @Override
  public Position<E> root() {
    return root;
  }

  /**
   * Returns the Position of p's parent (or null if p is root).
   *
   * @param p    A valid Position within the tree
   * @return Position of p's parent (or null if p is root)
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public Position<E> parent(Position<E> p) throws IllegalArgumentException
  {
      Node<E> node = validate(p);
      return node.getParent();
  }

  /**
   * Returns the Position of p's left child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the left child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> left(Position<E> p) throws IllegalArgumentException
  {
	Node<E> node = validate(p);
	return node.getLeft();
  }

  /**
   * Returns the Position of p's right child (or null if no child exists).
   * @param p A valid Position within the tree
   * @return the Position of the right child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */

  @Override
  public Position<E> right(Position<E> p) throws IllegalArgumentException
  {
	Node<E> node = validate(p);
	return node.getRight();
  }

  // update methods supported by this class
  /**
   * Places element e at the root of an empty tree and returns its new Position.
   *
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalStateException if the tree is not empty
   */
  public Position<E> addRoot(E e) throws IllegalStateException
  {
	if(!isEmpty()){
	  throw new IllegalStateException("Warning: Tree not empty");
    }
	root = createNode(e,null,null,null);
	size++;
	return root;
  }

  public void insert(E e){
      //recursively add from root
      root = addRecursive(root, e);
      ++size;
  }
  
  //recursively add Nodes to binary tree in proper position
  private Node<E> addRecursive(Node<E> p, E e) {
    if(p==null)
    {
      return createNode(e,null,null,null);
    }
    else if(e.compareTo(p.element) < 0)
    {
      p.addLeft(addRecursive(p.left, e));
    }
    else
    {
      p.addRight(addRecursive(p.right,e));
    }
    return p;
  }

  
  /**
   * Creates a new left child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the left of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p already has a left child
   */
  public Position<E> addLeft(Position<E> p, E e)
                          throws IllegalArgumentException {
	Node<E> parent = validate(p); //check valid node

	if(parent.getLeft() != null) //Check if left child exists
	  throw new IllegalArgumentException("There's already a left node");

	Node<E> child = createNode(e,parent,null,null);
	parent.addLeft(child);  //Create left node and add it to left child

    size++; //Incremenet size
    return child;
  }

  /**
   * Creates a new right child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the right of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p already has a right child
   */
  public Position<E> addRight(Position<E> p, E e)
                          throws IllegalArgumentException {

    Node<E> parent = validate(p); //Check if valid node
    if(parent.getRight() != null) //Check if right child is empty
      throw new IllegalArgumentException("There's already a right child");

    Node<E> child = createNode(e,parent,null,null);
    parent.addRight(child); //Create right child
    size++;//Incremenet size
    return child;

  }

  /**
   * Replaces the element at Position p with element e and returns the replaced element.
   *
   * @param p   the relevant Position
   * @param e   the new element
   * @return the replaced element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */

  public E set(Position<E> p, E e) throws IllegalArgumentException {
	  Node<E> node = validate(p); //Validate node

	  E val = node.getElement(); //get nodes element
	  node.setElement(e); //set the node to new element
	  return val; //return val of old element
  }

  /**
   * Attaches trees t1 and t2, respectively, as the left and right subtree of the
   * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
   *
   * @param p   a leaf of the tree
   * @param t1  an independent tree whose structure becomes the left child of p
   * @param t2  an independent tree whose structure becomes the right child of p
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p is not a leaf
   */
  public void attach(Position<E> p, LinkedBinaryTree<E> t1,
                    LinkedBinaryTree<E> t2) throws IllegalArgumentException {
	Node<E> node = validate(p);

	if(isInternal(p))
	 throw new IllegalArgumentException("p is not a leaf ");



      if (!t1.isEmpty())  //If trree 1 is not empty
      {
        t1.root.addParent(node);
        node.addLeft(t1.root); //Add parent to first trees root

        t1.size = 0;
        t1.root = null;
      }

      size += t1.size()+t2.size(); //Add sizes of two trees
      if (!t2.isEmpty())
      { //If tree 2 is not empty
        t2.root.addParent(node); //Add parent to tree two's root
        node.addLeft(t2.root); //set left subtree of new root to the second tree

        t2.size = 0;
        t2.root = null;
      }


  }


  /**
   * Removes the node at Position p and replaces it with its child, if any.
   *
   * @param p   the relevant Position
   * @return element that was removed
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p has two children.
   */
  public E remove(Position<E> p) throws IllegalArgumentException
  {
    Node<E> node = validate(p);
    if (numChildren(p) == 2)
      throw new IllegalArgumentException("node p has two child nodes");
      Node<E> child;
      if (node.getLeft() == null)
        child = node.getRight();
      else
        child = node.getRight();

      if (child != null)
        child.addParent(node.getParent());


      if (node == root)
        root = child;
      else {
        Node<E> parent = node.getParent();
        if (node == parent.getLeft()) {
          parent.addLeft(child);
        } else
          parent.addRight(child);
      }
      size--;

      E val = node.getElement();
      node.setElement(null);
      node.addRight(null);
      node.addParent(null);
      node.addLeft(null);

      return val;
    }


  @Override
  public String toString(){
    if(this == null || size == 0)
    {
        return "";
    }
	  StringBuilder sb = new StringBuilder();
	  sb.append("[");

	  for(Position<E> p : positions()) {
		  sb.append(p.getElement());
		  sb.append(", ");
	  }
	  sb.append("]");

	  sb.replace(sb.length()-3,sb.length(),"]" );
	  return sb.toString();
  }

  public static void main(String [] args) {
	  LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
	  
	  int [] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
	  for(int i : arr) {
		  bt.insert(i);

	  }
	  System.out.println("bt: " + bt.size() + " " + bt );
	  
  }
} 

