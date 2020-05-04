package projectCode20280;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    @Test
    void size() {
        LinkedStack<Integer> s = new LinkedStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(10, s.size());
    }

    @Test
    void isEmpty() {
        LinkedStack<Integer> s = new LinkedStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);
        for(int i = 0; i < 10; ++i)
            s.pop();
        assertEquals(true, s.isEmpty());
    }

    @Test
    void push() {
        LinkedStack<Integer> s = new LinkedStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());
    }

    @Test
    void top() {
        LinkedStack<Integer> s = new LinkedStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, s.top());
    }

    @Test
    void pop() {
        LinkedStack<Integer> s = new LinkedStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);

        for(int i = 0; i< 5; ++i)
            s.pop();
        assertEquals("[4, 3, 2, 1, 0]", s.toString());
    }
}