package projectCode20280;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {

	public static final int SIZE = 100;
	private E[] data;
	private int t = -1;
	public ArrayStack(int capacity){
		data = (E[]) new Object[capacity];
	}

	private ArrayStack() {
		this(SIZE);
	}

	public String toString()
	{
		String result = "";
		result += "[";
		for(int i = size()-1; i >= 0; i--)
		{
			if(i > 0)
				result+=(data[i] + ", ");
			else
				result += data[i];
		}
		result += "]";
		return result;
	}
	@Override
	public int size() {
		return (t+ 1);
	}

	@Override
	public boolean isEmpty() {

		return (t == -1);
	}

	@Override
	public void push(E e) throws IllegalStateException {
		if (size() == data.length) throw new IllegalStateException("Error: the stack is full");
		data[++t] = e;
	}

	@Override
	public E top() {
		if (isEmpty()) return null;
		return data[t];
	}

	@Override
	public E pop() {
		if (isEmpty()) return null;
		E current = data[t];
		data[t] = null;
		t--;

		return current;
	}


	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<Integer>();

		System.out.println("**Array Queue Test Cases**");

		System.out.println("\n-Empty stack test");
		System.out.println("--Expected: [] Size: 0, isEmpty: true");
		System.out.println("--Actual: ");
		stack.toString();
		System.out.println("Size:  "+ stack.size() + ", isEmpty: "+ stack.isEmpty() );


		System.out.println("\n-Pushing with 10 elements");
		System.out.println("--Expected: [0,1,2,3,4,5,6,7,8,9] Size: 10");
		System.out.println("--Actual ");

		for(int i = 0; i<10; i++)
		{
			stack.push(i);
		}
		stack.toString();
		System.out.print(" Size: "+ stack.size());

		System.out.println("\n\n-Popping 5 elements");
		System.out.println("--Expected: [0,1,2,3,4], Size: 5, isEmpty: false");
		for(int i = 0; i<5; i++)
		{
			stack.pop();
		}
		System.out.println("--Actual ");
		stack.toString();
		System.out.print(" Size: "+ stack.size() +", is Empty: "+stack.isEmpty());





	}


}
