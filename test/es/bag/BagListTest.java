package es.bag;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class BagListTest {

    private Bag<String> bolsa;

    @Before
    public void setUp() {
        bolsa = new BagList<>();
    }

    @Test
    public void add() {
        assertEquals(0, bolsa.size());
        bolsa.add("Es una cadena");
        assertEquals(1, bolsa.size());
    }

    @Test
    public void remove() {
        assertEquals(0, bolsa.size());
        bolsa.add("es una cadena");
        assertEquals(1, bolsa.size());
        bolsa.remove("es una cadena");
        assertEquals(0, bolsa.size());
    }

    @Test
    public void containsAll() {
        final List<String> ejemplo = Arrays.asList("es", "una", "lista");
        assertEquals(0, bolsa.size());
        bolsa.add("es");
        bolsa.add("una");
        bolsa.add("cadena");
        assertEquals(3, bolsa.size());
        assertTrue(bolsa.containsAll(ejemplo));
    }

    @Test
    public void addAll() {
        final List<String> ejemplo = Arrays.asList("es", "una", "lista");
        assertEquals(0, bolsa.size());
        bolsa.addAll(ejemplo);
        assertEquals(3, bolsa.size());
    }

    @Test
    public void removeAll() {
        final List<String> ejemplo = Arrays.asList("es", "una", "lista");
        assertEquals(0, bolsa.size());
        bolsa.add("es");
        bolsa.add("una");
        bolsa.add("cadena");
        assertTrue(bolsa.removeAll(ejemplo));
        assertEquals(0, bolsa.size());
    }

    @Test
    public void retainAll() {
    }

    @Test
    public void clear() {
        assertEquals(0, bolsa.size());
        bolsa.add("es");
        bolsa.add("una");
        bolsa.add("cadena");
        assertFalse(bolsa.isEmpty());
        bolsa.clear();
        assertTrue(bolsa.isEmpty());
    }

    @Test
    public void contains() {
        assertEquals(0, bolsa.size());
        bolsa.add("es");
        bolsa.add("una");
        bolsa.add("cadena");
        assertTrue(bolsa.contains("una"));
        assertTrue(bolsa.contains("es"));
    }

    @Test
    public void isEmpty() {
        assertTrue(bolsa.isEmpty());
    }

    @Test
    public void iterator() {
        bolsa.add("es");
        bolsa.add("una");
        bolsa.add("cadena");
        final Iterator<String> it = bolsa.iterator();
        assertNotNull(it);
        assertTrue(it.hasNext());
        assertEquals("es", it.next());
    }

    @Test
    public void toArray() {
        bolsa.add("es");
        bolsa.add("una");
        bolsa.add("cadena");
        final String[] resultado = (String[]) bolsa.toArray();
        assertEquals(3, resultado.length);
    }

    @Test
    public void toArray1() {
        bolsa.add("es");
        bolsa.add("una");
        bolsa.add("cadena");
        final String[] resultado = bolsa.toArray(new String[0]);
        assertEquals(3, resultado.length);
    }

    @Test
    public void size() {
        assertEquals(0, bolsa.size());
        bolsa.add("es");
        bolsa.add("una");
        bolsa.add("cadena");
        assertEquals(3, bolsa.size());
    }

    @Test
    public void extract() throws Exception {

    }

    @Test
    public void emptyIn() throws Exception {

    }

}