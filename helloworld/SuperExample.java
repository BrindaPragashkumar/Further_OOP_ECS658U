package helloworld;

import java.util.List;

public class SuperExample {
    static void addStringToList(List<? super String> list) {
        list.add("hello");
    }

    public static void main(String[] args) {
        List<Object> list = new java.util.ArrayList<>();
        addStringToList(list);
        System.out.println(list);
    }
}
