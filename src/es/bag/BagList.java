package es.bag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class BagList<E> implements Bag<E> {

    final ArrayList<E> bolsa = new ArrayList<>();

    @Override
    public boolean add(final E e) {
        return bolsa.add(e);
    }

    @Override
    public boolean remove(final Object object) {
        return bolsa.remove(object);
    }

    @Override
    public boolean containsAll(final Collection<?> collection) {
        return bolsa.containsAll(collection);
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        return bolsa.addAll(c);
    }

    @Override
    public boolean removeAll(final Collection<?> collection) {
        return bolsa.removeAll(collection);
    }

    @Override
    public boolean retainAll(final Collection<?> collection) {
        return bolsa.retainAll(collection);
    }

    @Override
    public void clear() {
        bolsa.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return bolsa.contains(o);
    }

    @Override
    public boolean isEmpty() {
        return bolsa.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return bolsa.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        return bolsa.toArray(array);
    }

    @Override
    public int size() {
        return bolsa.size();
    }

    @Override
    public E extract() {
        Random aleatorio = new Random();
        int intAleatorio = aleatorio.nextInt(size());

        return bolsa.get(intAleatorio);
    }

    @Override
    public Bag emptyIn(Bag<? super E> b) {
        for (int i = 0; i <= size(); i++) {
            b.add(bolsa.get(i));
        }
        return b;
    }
}
