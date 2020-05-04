package projectCode20280;

public class LinkedQueue<E> implements Queue<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	public static void main(String[] args) {
		LinkedQueue queue = new LinkedQueue();
		
		System.out.println("**Linked Queue Test Cases**");

		System.out.println("\n-Empty Queue test");
		System.out.println("--Expected: 'list is empty' Size: 0, isEmpty: true");
		System.out.println("--Actual: ");
		queue.toString();
		System.out.println("Size:  "+ queue.size() + ", isEmpty: "+ queue.isEmpty() );


		System.out.println("\n-Pushing with 10 elements");
		System.out.println("--Expected: [0,1,2,3,4,5,6,7,8,9] Size: 10");
		System.out.println("--Actual ");

		for(int i = 0; i<10; i++)
		{
			queue.enqueue(i);
		}
		System.out.println(queue.toString());
		System.out.print(" Size: "+ queue.size());

		System.out.println("\n\n-Popping 5 elements");
		System.out.println("--Expected: [0,1,2,3,4], Size: 5, isEmpty: false");
		for(int i = 0; i<5; i++)
		{
			//queue.pop();
		}
		System.out.println("--Actual ");
		queue.toString();
		System.out.print(" Size: "+ queue.size() +", is Empty: "+queue.isEmpty());
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		list.addLast(e);

	}

	@Override
	public E first() {
		return list.first();
	}

	@Override
	public E dequeue() {
		return list.removeFirst();
	}

	@Override
	public String toString()
	{
		return list.toString();
	}

}
