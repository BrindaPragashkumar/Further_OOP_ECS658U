package Lab4;

import java.util.ArrayList;
import java.util.Map;

public class TestFieldTypeCounter {
    public static void testFieldCount(Object o){
        System.out.println("Counting fields in " + o.getClass().getName()+":");
        FieldTypeCounter counter = new FieldTypeCounter();
        counter.stringify(o);
        for(Map.Entry<Object ,Integer> entry : counter.counter){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println();
    }
    public static void main(String[] args){
        ReferenceClass a = new ReferenceClass(10);
        ReferenceClass b = new ReferenceClass(20);
        a.next = b;
        testFieldCount(a);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(10);
        arrayList.add(20);
        testFieldCount(arrayList);

    }
}
