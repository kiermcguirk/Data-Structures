package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

	private Node<E> tail = null;
	private int size = 0;


	public CircularlyLinkedList(){}

	private class Node<E> {
		private E element;
		private Node<E> next;
		private Node<E> prev;

		public Node(E e, Node<E> n)
		{
			element = e;

			next = n;
		}

		public void setNext(Node<E> n) {
			this.next = n;
		}

		public Node<E> getNext() {
			return this.next;
		}

		public E getElement() {
			return this.element;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E get(int i) {
		return null;
	}

	@Override
	public void add(int i, E e) {

	}

	@Override
	public E remove(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeFirst() {
		if(isEmpty())
		{
			return null;
		}
		Node<E> head = tail.getNext();
		if(head == tail){
			tail = null;
		}
		//else tail.setNext(head.)
		return null;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E e) {
		if(size == 0)
		{
			tail = new Node<>(e,null);
		}
	}

	@Override
	public void addLast(E e) {
		// TODO Auto-generated method stub

	}

	public void rotate() {
				
	}
	
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
		for(int i = 10; i < 20; ++i) {
			ll.addLast(i);
		}

		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.removeLast();

		ll.rotate();
		System.out.println(ll);

		ll.removeFirst();
		ll.rotate();
		System.out.println(ll);

		ll.removeLast();
		ll.rotate();
		System.out.println(ll);

		for (Integer e : ll) {
			System.out.println("value: " + e);
		}

	}
}
