package projectCode20280;

import java.util.Iterator;



public class DoublyLinkedList<E> implements List<E>
{


	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;


	public DoublyLinkedList() //constructor
	{
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}


	static class Node<E>
	{
		private E element;
		private Node<E> previous;
		public E getElement() {
			return element;
		}
		private Node<E> next;


		public Node(E element, Node<E> previous, Node<E> next) {
			this.element = element;
			this.previous = previous;
			this.next = next;
		}

		public void setNext(Node<E> next){
			this.next = next;
		}
		public Node<E> getNext(){
			return next;
		}
		public Node<E> getPrevious() {
			return previous;
		}
		public void setPrevious(Node<E> previous)
		{
			this.previous = previous;
		}
		
	}

	class DoublyLinkedListIterator implements Iterator<E> {

		private DoublyLinkedList.Node<E> curr;
		private DoublyLinkedList.Node<E> prev;
		private DoublyLinkedList.Node<E> prev2;


		public DoublyLinkedListIterator()
		{
			curr = header.getNext();
			prev = null;
			prev2 = null;
		}

		@Override
		public boolean hasNext()
		{
			return curr != null;
		}

		public E next()
		{
			if (curr == null)
			{
				throw new IllegalArgumentException();
			}
			E temp = curr.getElement();
			prev2 = prev;
			prev = curr;
			curr = curr.getNext();
			
			return temp;
		}

		@Override
		public void remove() {
			if (prev == null)
			{
				throw new IllegalArgumentException();
			}
			if (prev2 == null)
			{
				header.next = curr;
			}
			else{
				prev2.setNext(curr);
				prev = prev2;
			}
			size--;

		}
	}

	public Iterator<E> iterator(int type) //Argument used for overloading
	{
			return  new DoublyLinkedListIterator();
	}
	@Override
	public Iterator<E> iterator()
	{
		return new Iterator() {
			Node<E> curr = header;

			@Override
			public boolean hasNext()
			{
				return curr != null;
			}
			@Override
			public E next()
			{
				if (hasNext())
				{
					E data = curr.element;
					curr = curr.next;

					return data;
				}
				return null;
			}

		};
	}
	@Override
	public int size()
	{
		return this.size;
	}

	@Override
	public boolean isEmpty()
	{
		return this.size() == 0;
	}


	@Override
	public E get(int i) //returns the element of the given index
	{
		if (i < 0)
		{
			throw new IllegalArgumentException();
		}
		Node<E> curr = header;


		while(i > 0 ) //traverse to ith element
		{
			curr = curr.getNext();
			i--;
		}
		return curr.getNext().getElement();
	}

	@Override
	public void add(int i, E e)
	{
		if (i < 0 )
		{
			throw new IllegalArgumentException(); //Can't get negatvie index
		}
		if (i == 0)
			addFirst(e);  //add first if i = 0
		else if (i == size -1) //add last if last entry
			addLast(e);
		else
		{
			Node<E> curr = header;
			while(i >= 0) //Traverse to ith element
			{
				curr = curr.next;
				i--;
			}

			//Place between nodes
			Node<E> prev = curr.previous;
			Node<E> newest = new Node<E>(e, null, null); 

			newest.next = curr;
			newest.previous = prev;

			prev.next = newest;
			curr.previous = newest;
		}
		size++;
	}

	/**
	 * Removes and returns the first element of the list.
	 * @return the removed element (or null if empty)
	 */
	@Override
	public E removeFirst()
	{
		if(isEmpty())
		{
			return null;
		}
//		size--;
		return remove(header.getNext());

	}

	/**
	 * Removes and returns the last element of the list.
	 * @return the removed element (or null if empty)
	 */
	@Override
	public E removeLast()
	{
		if(isEmpty())
		{
			return null;
		}
		size--;
		return remove(trailer.getPrevious());
	}

	/**
	 * Removes the given node from the list and returns its element
	 * @param node  the node to be remobed (must not be sentinel)
	 */
	
	
	public E remove(Node<E> node)
	{
		//point predecessor to successor
		Node<E> pre = node.getPrevious(); //predecessor and successor
		Node<E> succ = node.getNext();
		pre.setNext(succ);
		succ.setPrevious(pre);

		size--;
		return node.getElement();
	}



	// public update methods
	/**
	 * * Adds an element to the front of the list.
	 * @param e the new element to add
	 */
	@Override
	public void addFirst(E e)
	{
		addBetween(e, header, header.getNext());
//		size++;
	}


	/**
	 * Adds an element to the end of the list.
	 * @param e   the new element to add
	 */
	@Override
	public void addLast(E e)
	{
		addBetween(e, trailer.getPrevious(), trailer);
		size++;
	}

	@Override
	public E remove(int i) {

		if (i < 0 )
			throw new IllegalArgumentException();
		
		Iterator iterator = this.iterator(1); 
		for (int index = 0; index < i; index++)
		{
			iterator.next(); //increment iterator
		}
		
		E e = (E) iterator.next();  //Cast bug
		iterator.remove(); //remove node
		return  e;

	}



	/**
	 * Adds an element to the linked list in between the given nodes.
	 * The given predecessor and successor should be neighboring each
	 * other prior to the call.
	 *
	 * @param predecessor   node indexust before the location where the new element is inserted
	 * @param successor     node indexust after the location where the new element is inserted
	 */

	public void addBetween(E e, Node<E> predecessor, Node<E> successor)
	{
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrevious(newest);
		
		size++;
	}

	public E first()
	{
		if (isEmpty())
			return null;
		else
		return header.getNext().getElement();
	}

	public E last()
	{
		if(isEmpty())
			return null;
		else
		return trailer.getPrevious().element;
	}

	@Override
	public String toString()
	{
		String str ="[";
		Node<E> curr= header.getNext();

		while (curr != trailer)
		{
			str +=curr.getElement();
			curr = curr.getNext();
			if (curr != trailer)
				str += ", ";

		}
		str += "]";
		return str;
	}
	public static void main(String[] args) {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		ll.addFirst(0);
		ll.addFirst(1);
		ll.addFirst(2);
		ll.addLast(-1);
		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.removeLast();
		System.out.println(ll);

		for(Integer e: ll) {
			System.out.println("element: " + e);
		}
	}
}