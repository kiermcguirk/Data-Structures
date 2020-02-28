package projectCode20280;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {
	private Node<E> head = null;
	private int size = 0;

	private class Node<E> {
		/// TODO
		private E element;
		private Node<E> next;

		public Node (E data, Node<E> pointer)
		{
			this.element = data;
			this.next = pointer;
		}


		//Accessor methods
		public E getElement() { return element; }

		public Node<E> getNext() { return next; }

		// Modifier methods
		public void setNext(Node<E> n) { next = n; }  //----------- e


		public E getElement(int i)
		{
			// TODO Auto-generated method stub
			return null;
		}
	}

	private class ListIterator implements Iterator<E>
	{
		Node curr;
		public ListIterator()
		{
			curr = head;
		}

		public boolean hasNext()
		{
			return curr != null;
		}

		@Override
		public E next()
		{
			E res = (E) curr.getElement();
			curr = curr.getNext();
			return res;
		}
	}

	public void printReverse()
	{
		printReverse1(this.head);
	}

	private void printReverse1(Node<E> node)
	{
		if(node.next == null)
		{
			System.out.print(node.element + " ");
		}
		else
		{
			printReverse1(node.next);
			System.out.print(node.element + " ");
		}
	}


	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return (head == null);
	}

	@Override
	public void add(int i, E e) {
		// TODO Auto-generated method stub
		int counter = 0;
		Node<E> temp = head.next;
		while (counter != i && temp != null)
		{
			temp = temp.next;
			counter++;
		}
		Node<E> p = new Node<E>(e,temp.next);
		temp.next = p;
		size++;
	}

	@Override
	public E remove(int i)
	{
		// TODO Auto-generated method stubff
		return null;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new ListIterator();
	}


	@Override
	public E get(int i) {
		return null;
	}


	@Override
	public E removeFirst() {

		if(isEmpty())
		{
			return null;
		}
		else
		{
		Node<E> temp = head;
		head = head.next;
		return temp.getElement();
	}
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		Node<E> previous = head;
		Node<E> last = head.next;
		if(isEmpty()) {
			throw new IllegalArgumentException("Nothing to remove");
		}
		else if(head.next == null) {
			removeFirst();
		}
		else{
			while(last.next != null)
			{
				previous = last;
				last = last.next;
			}
			previous.next = null;
		}
		return previous.getElement();
	}

	@Override
	public void addFirst(E e) {
		// TODO Auto-generated method stub
		head = new Node<E>(e, head);
		size++;
	}

	public int size()
	{
		return size;
	}


	@Override
	public void addLast(E e)
	{
		// TODO Auto-generated method stub
		Node<E> newest = new Node<E>(e,null);
		Node<E> last = head;
		if(last == null) {
			head = newest;
		}
		else
		{
			while(last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(newest);
		}
		size++;
	}

	@Override
	public String toString()
	{
		ListIterator x = new ListIterator();

		int counter = size;
		String toString = "";

		while(counter > 0 && x.curr != null)
		{
			toString += x.curr.element;
			x.curr = x.curr.next;
			counter--;
		}
		return toString;
	}



	public static void main(String[] args) {
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
		for (String s : alphabet) {
			//sll.addFirst(s);
			sll.addLast(s);
		}
		//System.out.println(sll.toString());
/*
		sll.removeFirst();
		System.out.println(sll.toString());
		
		sll.removeLast();
		System.out.println(sll.toString());

		sll.remove(2);
		System.out.println(sll.toString());
		
		for (String s : sll) {
			System.out.print(s + ", ");
		}
		*/


		sll.printReverse();
	}
}
