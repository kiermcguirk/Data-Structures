package projectCode20280;
import java.util.Iterator;
public class CircularlyLinkedList<E> implements List<E> {

	private Node<E> tail = null;
	private int size = 0; 

	private static class Node<E> {
		private E element;
		private Node<E> next;
		
		//constructor/getter/setters
		public Node(E element, Node<E> next) 
		{
			this.element = element;
			this.next = next;
		}
		public E getElement()
		{
			return element;
		}
		public Node<E> getNext() 
		{
			return next;
		}
		public void setNext(Node<E> next)
		{
			this.next = next;
		}
	}

	public CircularlyLinkedList() {}

	/**
	 * * Returns the number of elements in the linked list.
	 *
	 * @return the number of elements in the list
	 * *
	 */

	@Override
	public int size()
	{
		return size;
	}

	public boolean isEmpty() 
	{
		return size == 0;
	}

	@Override
	public E get(int i) {
		if (i < 0){
			throw new IllegalArgumentException(); //can't get negatiive index
		}
		if(i < size() -1) //if last element
		{
			Node<E> curr= tail;
			for (int j = 0; j < i; j++)
			{
				curr = curr.getNext();
			}
			return curr.getElement();
		}

		return tail.getElement();

	}

	@Override
	public void add(int i, E e) {
		Node<E> curr = tail; //begin at tail

		Node<E> newest = new Node<E>(e, null);

		while (i > 1)
		{
			curr = curr.next; //traverse to ith element
			i--;
		}

		//add node
		newest.setNext(curr.getNext());
		curr.setNext(newest);
	}

	@Override
	public E removeFirst() {
		if (isEmpty())
			return null;
		Node<E> curr = tail.getNext();
		if (curr == tail) //if following node is the same remove tail
		{
			tail = null;
		} else { //point tail to node after next
			tail.setNext(curr.getNext());
		}
		size--;

		return curr.getElement();
	}

	@Override
	public E removeLast()
	{
		Node<E> curr = tail.getNext(); //begin at tail

		while (curr.next != tail.getNext()) //Traverse until 'end' (node before tail)
		{
			curr = curr.next;
		}

		tail.next = null; //point tail to null
		curr.next = curr; //point to beginning

		return curr.getElement();
	}
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			Node<E> current = tail;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() {
				if (hasNext()) {
					E data = current.element;
					current = current.next;
					return data;
				}
				return null;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public void addFirst(E e) {
		if (size == 0) // if first point to tail
		{
			tail = new Node<>(e, null);
			tail.setNext(tail);                    
		} else
			{
			Node<E> newest = new Node<>(e, tail.getNext()); //add after tail
			tail.setNext(newest);
		}
		size++;
	}

	@Override
	public void addLast(E e) {
		Node<E> newest = new Node<E>(e, null);
		Node<E> curr = tail;

		if (curr == null) //if empty set tail to newest node
		{
			tail = newest;
		} else
			{

				while (curr.getNext() != null) //traverse to last node
			{
				curr = curr.getNext();
			}
			curr.setNext(newest); //point last node to new node
		}
		size++;
	}

	@Override
	public E remove(int i) {
		if (i < 0) {
			throw new IllegalArgumentException(); //Can't get negative index
		}

		Node<E> curr = tail;

		while(i > 1 ) //iterate to ith element
		{
			curr = curr.next;
			i--;
		}
		curr.next = curr.next.next; //point predecessor to successor

		return curr.getElement();

	}



	public void rotate()
	{
		if (tail != null) {
			tail = tail.getNext();
		}
	}

	public E first()
	{
		if (isEmpty()) return null;
		return tail.getNext().getElement();
	}



	@Override
	public String toString()
	{
//		String test = "";
		String str = "[";
		Node<E> current = tail;

		while(current != null)
		{
			str += current.getElement();

			if(current.getNext() != null)
			{
				str += ", ";
			}
			current = current.getNext();
		}
		str += "]";
		return  str;
	}

	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();


	}
}