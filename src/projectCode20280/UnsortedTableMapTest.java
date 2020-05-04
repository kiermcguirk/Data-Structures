package projectCode20280;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsortedTableMapTest {

    @Test
    void get() {
        UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();
        Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};

        for(Integer i : arr) {
            map.put(i, Integer.toString(i));
        }
        assertEquals("15", map.get(15));
        assertEquals("24", map.get(24));
        assertEquals(null, map.get(-1));

    }

    @Test
    void put() {
        UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();
        Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};

        for(Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals("[35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5]", map.keySet().toString());
    }

    @Test
    void remove() {
        UnsortedTableMap<Integer, String> map = new UnsortedTableMap<>();
        Integer[] arr = new Integer[] {35,26,15,24,33,4,12,1,23,21,2,5};

        for(Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(12, map.size());
        assertEquals("26", map.remove(26));
        assertEquals(11, map.size());
    }


}