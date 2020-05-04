package projectCode20280;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    void size() {
        ArrayStack<Integer> s = new ArrayStack<>(10);
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(10, s.size());
    }

    @Test
    void isEmpty() {
        ArrayStack<Integer> s = new ArrayStack<>(10);
        assertEquals(true,s.isEmpty());
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(false, s.isEmpty());
    }

    @Test
    void push() {
        ArrayStack<Integer> s = new ArrayStack<>(10);
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());
    }

    @Test
    void top() {
        ArrayStack<Integer> s = new ArrayStack<>(10);
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, s.top());
    }

    @Test
    void pop() {
        ArrayStack<Integer> s = new ArrayStack<>(10);
        for(int i = 0; i < 10; ++i)
            s.push(i);

        for(int i = 0; i< 5; ++i)
            s.pop();
        assertEquals("[4, 3, 2, 1, 0]", s.toString());
    }
}