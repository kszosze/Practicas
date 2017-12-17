package es.bag;

import java.util.Collection;
import java.util.Iterator;

public interface Bag<E> extends Collection<E> {

    boolean add(E e);

    boolean addAll(Collection<? extends E> c);

    void clear();

    boolean contains(Object o);

    boolean isEmpty();

    public Iterator<E> iterator();

    int size();

    E extract();

    Bag emptyIn(Bag<? super E> b);
}
