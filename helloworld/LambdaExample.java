package helloworld;

import java.util.function.Function;
import java.util.Iterator;
public class LambdaExample {

    public static void main(String[] args) {
        Function<Integer, Integer> func = x -> x * x;
        var x = func.apply(func.apply(2));
        System.out.println(x);
    }


    private static class GenericArrayListIterator<T> implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
        // which methods must be implemented?
    }
}