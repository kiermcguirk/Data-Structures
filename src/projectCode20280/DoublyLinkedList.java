package projectCode20280;

import java.util.Iterator;
import java.util.ListIterator;

public class DoublyLinkedList<E> implements List<E> {
	private Node<E> header;
	private Node<E> trailer;
	private int size =0;

	private class Node<E> {
		private E element;
		private Node<E> next;
		private Node<E> prev;

		public Node(E e, Node<E> p, Node<E> n)
		{
			element = e;
			prev = p;
			next = n;
		}

		public void setNext(Node<E> n) {
			this.next = n;
		}

		public void setPrev(Node<E> p) {
			this.prev = p;
		}

		public Node<E> getNext() {
			return this.next;
		}

		public Node<E> getPrev() {
			return this.prev;
		}

		public E getElement() {
			return this.element;
		}
	}

	public DoublyLinkedList()
	{
		header = new Node<>(null,null,null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}


	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {

		Node<E> newest = new Node<>(e, predecessor,successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {

		return header==null;
	}

	@Override
	public E get(int i) {

		return null;
	}

	@Override
	public void add(int i, E e) {
		Node<E> tempheader = header;

		int counter = 0;
		while(tempheader.getNext() != null && counter != i)
		{
			tempheader = tempheader.getNext();
			counter++;
		}
		Node<E> predecessor = tempheader;
		Node<E> successor = tempheader.getNext();
		Node<E> newest = null;
		newest.element = e;
		newest.next = successor;
		newest.prev = predecessor;
		size++;

		
	}

	@Override
	public E remove(int i) {
		Node<E> tempheader = header;

		int counter = 0;
		while(tempheader.getNext() != null && counter != i)
		{
			tempheader = tempheader.getNext();
			counter++;
		}
		Node<E> predecessor = tempheader.getPrev();
		Node<E> successor = tempheader.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;

		return tempheader.getElement();
	}
	private class ListIterator implements Iterator<E>
	{
		Node<E> curr;
		public ListIterator(){
			curr = header;
		}
		public boolean hasNext(){
			return curr != null;
		}

		@Override
		public E next()
		{
			E x = (E)curr.getElement();

			curr = curr.getNext();
			return x;
		}
	}

	@Override
	public Iterator<E> iterator() {

		return new ListIterator();

	}



	@Override
	public E removeFirst() {

		if(isEmpty())
		{
			return null;
		}
		else
		{
			Node<E> temp = header;
			header = header.next;
			return temp.getElement();
		}
	}

	@Override
	public E removeLast() {
		if(isEmpty())
		{
			return null;
		}
		return remove(size-1);
	}


	@Override
	public void addFirst(E e) {
		addBetween(e, header, header.getNext());

		
	}

	@Override
	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer);
		
	}

	@Override
	public String toString()
	{
		Node<E> x = header;

		int counter = size;
		String toString = "";

		while(counter > 0 && x != null)
		{
			if(x.element != null)
			{
				toString += x.element;
			}
			x = x.getNext();
			counter--;
		}
		return toString;
	}
	
	public static void main(String[] args) {

		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
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
                   System.out.println("value: " + e);
           }
	}

	
}
