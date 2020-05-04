package projectCode20280;

public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	public LinkedStack(){}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(E e)
	{
		list.addFirst(e);
	}

	@Override
	public E top() {
		if (isEmpty()) return null;

		return list.first();
	}

	@Override
	public E pop() {
		return list.removeFirst();
	}

	@Override
	public String toString()
	{
		return list.toString();
	}
	public static void main(String[] args) {

		LinkedStack stack = new LinkedStack();


	}

}
