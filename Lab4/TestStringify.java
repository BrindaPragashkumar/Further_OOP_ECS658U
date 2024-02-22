package Lab4;

import java.util.ArrayList;


public class TestStringify {
    public static void testFieldCount(Object o) {
        System.out.println("Counting fields in" + o.getClass().getName() + ":");
        FieldTypeCounter counter = new FieldTypeCounter();
    }

    public static void main(String[] args) {
        ObjectStringifier stringify = new ObjectStringifier();
        //BasicStringify stringify = new BasicStringify();
//        System.out.println(stringify.stringify(new SimpleClass()));
//        ReferenceClass a = new ReferenceClass(10);
//        ReferenceClass b = new ReferenceClass(20);
//        a.next = b;
//        System.out.println(stringify.stringify(a));
//        System.out.println(stringify.stringify(new RecordClass(10, "hello")));
//        System.out.print(stringify.stringify(new WithInteger()));
//        testFieldCount(a);

        System.out.println("123");
//        SimpleClass reference = new SimpleClass();
//        System.out.println(stringify.stringify(reference));


        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

       System.out.println(stringify.stringify(arrayList));

    }

}

class SimpleClass {
    public int x = 10;
    public String y = "hello";
}

record RecordClass(int x, String y) {
}

class ReferenceClass {
    public int value;
    public ReferenceClass next;

    public ReferenceClass(int value) {
        this.value = value;
    }
}

class WithInteger {
    public int x = 10;
    public Integer y = 20;
}

