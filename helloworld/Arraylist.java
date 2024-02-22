package helloworld;

import java.util.Iterator;
public class Arraylist {
    private static class GenericArrayListIterator<T> implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
        // details omitted
    }
}
