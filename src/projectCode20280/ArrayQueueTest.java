package projectCode20280;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {

    @Test
    void size() {

        ArrayQueue<Integer> s = new ArrayQueue<>(10);
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals(10, s.size());
    }

    @Test
    void isEmpty() {
        ArrayQueue<Integer> s = new ArrayQueue<>(10);
        assertEquals(true,s.isEmpty());
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals(false, s.isEmpty());
    }

    @Test
    void enqueue() {
        ArrayQueue<Integer> s = new ArrayQueue<>(10);
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", s.toString());
    }

    @Test
    void first() {
        ArrayQueue<Integer> s = new ArrayQueue<>(10);
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals(0, s.first());
    }


    @Test
    void dequeue() {

        ArrayQueue<Integer> s = new ArrayQueue<>(10);
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);

        assertEquals(0, s.dequeue());
        assertEquals(9, s.size());
    }
}