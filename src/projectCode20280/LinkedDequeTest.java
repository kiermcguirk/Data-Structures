package projectCode20280;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedDequeTest {

    @Test
    void size() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for(int i = 0; i < 10; ++i)
            s.addFirst(i);
        assertEquals(10, s.size());
    }

    @Test
    void isEmpty() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for(int i = 0; i < 10; ++i)
            s.addFirst(i);
        for(int i = 0; i < 10; ++i)
            s.removeFirst();
        assertEquals(true, s.isEmpty());
    }

    @Test
    void first() {

        LinkedDeque<Integer> s = new LinkedDeque<>();
        for(int i = 0; i < 10; ++i)
            s.addFirst(i);
        assertEquals(0, s.first());
    }

    @Test
    void last() {
    }

    @Test
    void addFirst() {
    }

    @Test
    void addLast() {
    }

    @Test
    void removeFirst() {
    }

    @Test
    void removeLast() {
    }
}