package projectCode20280;

public class ArrayQueue<E> implements Queue<E> {

	private int size = 0;
	private E[] data;
	private int front = 0;

	public ArrayQueue(int size)
	{
		data = (E[]) new Object[size];
	}

	public static void main(String[] args) {

		ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(10);


		System.out.println("**Array Queue Test Cases**");
		System.out.println("-Filling with 10 elements");

		//Fill queue
		for(int i = 0; i<10; i++)
		{
			arrayQueue.enqueue(i);
		}

		arrayQueue.toString();

		System.out.println("\n\n-isEmpty Test: expected false");
		System.out.println("--Actual: "+ arrayQueue.isEmpty());


		System.out.println("\n\n-First Test: expected '0'");
		System.out.println("--Actual: "+ arrayQueue.first());

		System.out.println("\n\n-First Test: expected '0'");
		System.out.println("--Actual: "+ arrayQueue.first());

		System.out.println("\n\n-Dequeue Test: Expected [null,1,2,3,4,5,6,7,8,9]");
		arrayQueue.dequeue();
		System.out.println("--Actual: ");
		arrayQueue.toString();



	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void enqueue(E e) throws IllegalStateException{
		if (size() == data.length){
			throw new IllegalStateException("Error: the queue is full");
		}
		int current = (front + size) % data.length;
		data[current] = e;
		size++;
	}

	@Override
	public E first() {
		if (isEmpty()) return null;
		return data[front];
	}

	@Override
	public E dequeue() {
		if (isEmpty()) return null;
		E temp = data[front];
		data[front] = null;

		front = (front + 1) % data.length;
		size--;

		return temp;
	}


	public String toString()
	{
		String string = "[";
		for(int i = 0; i < this.size(); i++)
		{
			if(i < this.size()-1)
				string += (data[i] + ", ");
			else
				string += (data[i]);
		}
		string += "]";
		return string;
	}
}
