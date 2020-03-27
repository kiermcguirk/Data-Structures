package projectCode20280;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

	private class Node<E> {
		private E element; //element stored at this node
		private Node <E> next; //store the following node
		//private Node<E> iterator = this.next;


		public Node(E e, Node<E> n){
			this.element = e;
			this.next = n;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

	}

	class SinglyLinkedListIterator implements Iterator<E> {

		private Node<E> current;
		private Node<E> previous;
		private Node<E> previous2;
		private boolean removeCalled;
		public SinglyLinkedListIterator(){
			current = head;
			previous = null;
			previous2 = null;
			removeCalled = false;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		public E next(){
			if (current == null){
				throw new NoSuchElementException();
			}
			E temp = current.getElement();
			previous2 = previous;
			previous = current;
			current = current.getNext();
			removeCalled = false;
			return temp;
		}

		@Override
		public void remove() {
			if (previous == null || removeCalled){
				throw new IllegalStateException();
			}
			if (previous2 == null){
				head = current;
			}
			else{
				previous2.setNext(current);
				previous = previous2; //Update remove to previous 2
			}
			size--;
			removeCalled = true;
		}
	}

	private Node<E> head = null; //first node of the list
	private Node<E> tail = null; //last node in the list
	private int size; //number of nodes in the list

	//Finds the first element on the list- need this for implementing methods for ArrayStack
	public E first(){
		if (isEmpty()) return null;
		return head.getElement();
	}


	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public E get(int i) {
		if (i < 0 || i >= size()){
			throw new IndexOutOfBoundsException(i + "out of the range");
		}
		if(i < size()-1){
			Node<E> current = head;
			for (int j = 0; j < i; j++){
				current = current.getNext(); // advance the node each time
			}
			return current.getElement(); //return element of the given index
		}
		return tail.getElement();

	}
	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public E remove(int i) {
		if (i < 0 || i >= size()){ //if the index if out of range
			throw new IndexOutOfBoundsException(i + "Not in range!");
		}
		Iterator<E> iterator = this.iterator(); //get an Iterator for the list
		for (int j = 0; j < i; j++){
			iterator.next(); // call .next certain number of times until the index that I want to return
		}
		E e = iterator.next(); // store the element of the index
		iterator.remove(); // remove the element
		return  e;
	}

	@Override
	public E removeFirst() {
		if(isEmpty()) {
			return null; //return null if no nodes to return
		}

		E first = head.getElement(); //set first node to get element in the head node
		head = head.getNext(); //assign head to the next node
		size--; //decrement size

		if(size == 0) {
			tail = null;
		}
		return first;

	}

	@Override
	public E removeLast() {

		if (isEmpty()){ return null;}

		Node<E> current = head;
		Node<E> previous = null;

		while (current.getNext() != null){ //loop if next node is not null
			previous = current; //set previous node to current
			current = current.getNext(); //advance to the next node
		}

		previous.setNext(null);
		tail = previous; //set tail to updated last node

		return current.getElement();
	}

	@Override
	public void add(int i, E e) {
		if (i < 0 || i > size()){
			throw new IndexOutOfBoundsException();
		}
		if (i == size()){ //add at end of list
			addLast(e);
			return;
		}
		if (i == 0){ //add start of list
			addFirst(e);
		}
		else {
			Node<E> current = head;
			for (int j = 0; j < i-1; j++){ //loop through until the current node in the list
				current = current.getNext(); //current is the node before the index
			}
			current.setNext(new Node<>(e, current.getNext())); //insert node where called in index
		}
		size++;
	}

	@Override
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if(size == 0) {
			tail = head;
		}
		size++;
	}

	@Override
	public void addLast(E e) {
		Node<E> lastAdd = new Node<>(e, null);

		if(isEmpty()) {
			head = lastAdd;    //assign tail to the newest node if list is empty
			tail = head;
		}
		else {
			tail.setNext(lastAdd); //point tail to the new node
			tail = lastAdd; // assign tail to the new node
		}
		size++; // increment number of nodes
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

	public static void main(String[] args) {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();


	}
}