package projectCode20280;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedCircularQueueTest {

    @Test
    void size() {
        LinkedCircularQueue<Integer> s = new LinkedCircularQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals(10, s.size());
    }

    @Test
    void isEmpty() {
        LinkedCircularQueue<Integer> s = new LinkedCircularQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        for(int i = 0; i < 10; ++i)
            s.dequeue();
        assertEquals(true, s.isEmpty());
    }

    @Test
    void enqueue() {

        LinkedCircularQueue<Integer> s = new LinkedCircularQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", s.toString());
    }

    @Test
    void first() {
        LinkedCircularQueue<Integer> s = new LinkedCircularQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        //1 afer tail (tail =0)
        assertEquals(1, s.first());
    }

    @Test
    void dequeue() {
        LinkedCircularQueue<Integer> s = new LinkedCircularQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);

        //1 after tail
        assertEquals(1, s.dequeue());
        assertEquals(9, s.size());
    }

}