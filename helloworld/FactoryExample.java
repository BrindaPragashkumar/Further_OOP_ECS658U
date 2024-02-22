package helloworld;

import java.util.function.Function;

record C (int x) {}

public class FactoryExample {

    public static void main(String[] args) {
        Function<Integer, C> aFactory = C::new;
        var x = aFactory.apply(5);
        System.out.println(x);
    }
}

