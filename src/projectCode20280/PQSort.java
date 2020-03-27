package projectCode20280;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

public class PQSort {


    public PQSort(){}

    public static Integer removeMin(SinglyLinkedList<Integer> list){
        int index = 0;
        Integer val = list.get(index);
        for(int i = 1; i< list.size(); i++)
        {
            Integer temp = list.get(i);
            if(temp < val){
                val = temp;
                index = i;
            }
        }
        list.remove(index);
        return val;
    }

    public static boolean isListSorted(Integer[] array, int n){
        if(n <2 || array == null) {return true;}

        if(array[n -2].compareTo(array[n - 1])>0) {return false;}
        return isListSorted(array,n - 1);
    }
    public static void randomVals(SinglyLinkedList list, int n){
        Random randomnum = new  Random();
        for(int i = 0; i < n; i ++)
            list.addLast(randomnum.nextInt());
    }
    public static void main(String[] args) {
        System.out.println("**PQ Sort of SinglyLinkedList**");
        int n = 100;
        while (n < 1000) {
            LinkedList<Integer> array = new Random().ints(0, 1000).distinct().limit(n).boxed().collect(Collectors.toCollection(LinkedList::new));
            //System.out.println(array);

            long start_time = System.nanoTime();

            SinglyLinkedList<Integer> pq = new SinglyLinkedList<Integer>();
            while (!array.isEmpty()) {
                pq.addLast(array.removeFirst());
            }

            while (!pq.isEmpty()) {
                array.addLast(removeMin(pq));
            }
            long end_time = System.nanoTime();
            long total_time = end_time - start_time;

            //System.out.println(array);

            boolean sort = (isListSorted(array.toArray(new Integer[array.size()]), array.size()));
            System.out.println(n + ", " +total_time + ", "+ sort);

            n = (int) ( n * 1.1);
        }

    }

    public static void main_heapsort(String[] args) {
        //SinglyLinkedList<Integer> array = new SinglyLinkedList<Integer>();
        System.out.println("**Heap Sort**");

        int n = 100;
        //randomVals(array, n);
        //System.out.println(array);

        while (n < 1000) {
            LinkedList<Integer> array = new Random().ints(0, 1000).distinct().limit(n).boxed().collect(Collectors.toCollection(LinkedList::new));
            //System.out.println(array);

            long start_time = System.nanoTime();




            //naive implementation
           HeapPriorityQueue<Integer,Integer> pq = new HeapPriorityQueue<Integer,Integer>();

           while (!array.isEmpty()) {
               Integer element = array.removeFirst();

               pq.insert(element, element);

           }
            /*
            // more efficient method with heapify
            Integer [] arrayheap = array.toArray(new Integer[array.size()]);
            HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<Integer,Integer>(arrayheap,arrayheap);

             */

            while (!pq.isEmpty()) {
                array.addLast(pq.removeMin().getValue());
            }

            long end_time = System.nanoTime();
            long total_time = end_time - start_time;

            //System.out.println(array);
            boolean sort = (isListSorted(array.toArray(new Integer[array.size()]), array.size()));
            System.out.println(n + ", " + total_time + ", "+ sort);
            //System.out.println(total_time);
            n = (int) ( n * 1.1);
        }
    }
}

