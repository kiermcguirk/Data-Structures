package projectCode20280;
import java.util.Iterator;


public class SinglyLinkedList<E> implements List<E> {
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size;

	private class Node<E>
	{
		private E element;
		private Node <E> next;

		//constructors/getters/setters
		public Node(E e, Node<E> n)
		{
			this.element = e;
			this.next = n;
		}

		public E getElement()
		{
			return element;
		}

		public void setNext(Node<E> next)
		{
			this.next = next;
		}

		public Node<E> getNext()
		{
			return next;
		}



	}


	public E first(){
		if (isEmpty()) //empty
			return null;
		else
		return head.getElement();
	}

	public E last()
	{
		if(isEmpty())
			return null;
		else
		return tail.element;
	}


	@Override
	public boolean isEmpty()
	{
		return this.size() == 0;
	}

	@Override
	public E get(int i) {
		if (i < 0){
			throw new IllegalArgumentException(); //can't get negatiive index
		}
		if(i < size() -1) //if last element
		{
			Node<E> curr= head;
//
			while(i > 0) //traverse to ith element
			{
				curr = curr.getNext();
				i--;
			}
			return curr.getElement();
		}

		return tail.getElement();

	}
	@Override
	public Iterator<E> iterator()
	{
		return new SinglyLinkedListIterator();
	}

	@Override
	public int size()
	{
		return this.size;
	}

	@Override
	public E remove(int i) {
		if (i< 0)
		{
			throw new IllegalArgumentException();
		}
		Iterator<E> iterator = this.iterator();
		for (int index = 0; index < i; index++){
			iterator.next();
		}
		E e = iterator.next();
		iterator.remove();
		return  e;
	}

	@Override
	public E removeFirst() {
		if(isEmpty())
		{
			return null;
		}

		E first = head.getElement();
		head = head.getNext();
		size--;

		if(size == 0)
		{
			tail = null;
		}
		return first;

	}

	@Override
	public E removeLast() {

		if (isEmpty())
			return null;

		Node<E> curr = head;
		Node<E> prev = null;

		while (curr.getNext() != null)
		{
			prev = curr;
			curr =curr.getNext();
		}

		prev.setNext(null);
		tail = prev;

		return curr.getElement();
	}

	@Override
	public void add(int i, E e) {
		if (i < 0){
			throw new IllegalArgumentException();
		}
		if (i == size())
		{
			addLast(e);
			return;
		}
		if (i == 0)
			addFirst(e);

		else
			{
			Node<E> curr = head;
			for (int j = 0; j < i-1; j++)
			{
				curr = curr.getNext();
			}
			curr.setNext(new Node<>(e, curr.getNext()));
		}

		size++;
	}

	@Override
	public void addFirst(E e)
	{
		head = new Node<>(e, head);
		if(size == 0)
			tail = head;

		size++;
	}

	@Override
	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null);

		if(isEmpty()) {
			head = newest;
			tail = head;
		}
		else
		{
			tail.setNext(newest);
			tail = newest;
		}
		size++;
	}

	@Override
	public String toString()
	{
		String test = "";

		String str = "[";
		Node curr = head;
		if(curr == null)
		{
			System.out.println("empty list");
		}
		else {
			while (curr!= null)
			{
				str += curr.element;
				if (curr.getNext() != null) {
					str += ", ";
				}
				curr = curr.getNext();

			}
		}
		str += "]";

//		StringBuilder sb = new StringBuilder(result);
//		sb.replace(sb.length()-3,sb.length(),"]" );
		return str;
	}
	class SinglyLinkedListIterator implements Iterator<E> //Iterator for efficient deletion
	{

		private Node<E> curr;
		private Node<E> prev;
		private Node<E> prev2;
		public SinglyLinkedListIterator()
		{
			curr = head;
			prev = null;
			prev2 = null;
		}

		@Override
		public boolean hasNext()
		{
			return curr != null;
		}

		public E next(){
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
		public void remove()
		{
			if (prev == null)
				throw new IllegalArgumentException();

			if (prev2 == null)
				head = curr;
			else
			{
				prev2.setNext(curr);
				prev = prev2;
			}

			size--;

		}
	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();

	}
}