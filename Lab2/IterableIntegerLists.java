package Lab2;
import java.util.Iterator;

interface IntegerList {
    boolean contains(int value);
    void append(int value);
    int length();

//    Iterator<Integer> iterator();
}

//public Iterator<Integer> iterator() {
//    return new ArrayListIterator(this);
//}




public class IterableIntegerLists {
    static void testList(IntegerList list){
        list.append(10);
        list.append(20);
        System.out.println(list.contains(10));
        System.out.println(list.contains(10));
        System.out.println(list.contains(10));
        System.out.println(list.contains(10));

//        printList(list);
        printListBadly(list);
    }

   // This will print out all items in the list.
    public static void printListBadly(IntegerList list){
        if(list instanceof LinkedList linkedList){
            Node current = linkedList.head;
            while(current != null){
                System.out.println();
                current = current.next;
            }

        }else if(list instanceof ArrayList arrayList){
            for(int i = 0; i < arrayList.len; i++){
                System.out.println(arrayList.values[i]);
            }
        }else{
            throw new IllegalArgumentException("Unknown list implementation:" + list.getClass().getName());
        }
    }

//    static void printList(IntegerList list){
//        for (Integer e : list){
//            System.out.println(e);
//        }
//    }
    public static void main(String[] args) {
        testList(new LinkedList());
//        testList(new ArrayList());
    }
}

class ArrayList implements IntegerList{
    static int initial_capacity = 10;
    int[] values;
    int len;

    public ArrayList(){
        values = new int[initial_capacity];
        len=0;
    }
    public Iterator<Integer> iterator(){

        return new ArrayList.ArrayListIterator(this);
    }
    private static class ArrayListIterator implements Iterator<Integer>{
        private int index;
        private final ArrayList list;
        public ArrayListIterator(ArrayList list){
            this.list =list;
            index = 0;
        }

        public boolean hasNext(){return index < list.len;}
        public Integer next() { return list.values[index++];}

    }
    public boolean contains(int value) {
        for (int i = 0; i < len; i++) {
            if (values[i] == value) {
                return true;
            }
        }
        return false;
    }
    public void append(int value) {
        if (len == values.length){
            int new_size ;
            if(values.length == 0){
                new_size = 1;
            }else{
                new_size = values.length * 2;
            }
            int[] newValues = new int[new_size];
            for(int i = 0; i <len; i++){
                newValues[i] = values[i];
            }
            values = newValues;
        }
        values[len] = value;
        len++;
    }

    public int length() {
        return len;
    }
}
class LinkedList implements IntegerList {
    Node head;
    Node tail;
    int len;
    public  LinkedList() {
        head = null;
        tail = null;
        len=0;
    }
    public boolean contains(int value) {

        if(head == null){ return false;}
        else{
           Node current = head;

            while(current!= null){
                if (current.value == value){
                    return true;
                }
                current = current.next;
            }
        }return false;
    }
    public void append(int value) {
       Node newNode = new Node(value);
        if(head==null){
            head = newNode;
            tail = newNode;
        } else{
            tail.next = newNode;
        }
        len++;
    }

    public int length() {
        return len;
    }

    public Iterator<Integer> iterator(){

        return new LinkedListIterator(this);
    }
    private static class LinkedListIterator implements Iterator<Integer>{
        private Node current;

        public LinkedListIterator(LinkedList list){
            current = list.head;
        }

        public boolean hasNext(){
            return current != null;
        }

        public Integer next(){
            int value = current.value;
            current = current.next;
            return value;
        }
    }
}


class Node{
    int value;
    Node next;

    public Node(int value){
        this.value=value;
        this.next= null;

    }
}

