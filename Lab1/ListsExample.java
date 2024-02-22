package Lab1;

interface List {
    boolean contains(int value);

    void append(int value);

    int length();
}


class LinkedList implements List {

    Node head;
    int len;

    public LinkedList() {
        head = null;
        len = 0;
    }

    @Override
    public boolean contains(int value) {
//        if(head == null){ return false;}

        Node current = head;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;

        }
        return false;
    }

    @Override
    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        len++;
    }

    @Override
    public int length() {
        return len;
    }
}

class Node {

    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;

    }
}

class ArrayList implements List {
    int[] values;
    int len;


    public ArrayList() {
        values = new int[0];
        len = 0;
    }

    @Override
    public boolean contains(int value) {
        for (int i = 0; i < len; i++) {
            if (values[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void append(int value) {
        int[] newValues = new int[len + 1];
        for (int i = 0; i < len; i++) {
            newValues[i] = values[i];
        }
        newValues[len] = value;
        values = newValues;
        len++;
    }

    @Override
    public int length() {
        return len;
    }
}


class EfficientArrayList implements List {

    static int initial_capacity = 10;
    int[] values;
    int len;


    public EfficientArrayList() {
        values = new int[initial_capacity];
        len = 0;
    }

    @Override
    public boolean contains(int value) {
        for (int i = 0; i < len; i++) {
            if (values[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void append(int value) {
        if (len == values.length) {
            int new_size;
            if (values.length == 0) {
                new_size = 1;
            } else {
                new_size = values.length * 2;
            }
            int[] newValues = new int[new_size];
            for (int i = 0; i < len; i++) {
                newValues[i] = values[i];
            }
            values = newValues;
        }
        values[len] = value;
        len++;
    }

    @Override
    public int length() {
        return len;
    }

}


class EfficientLinkedList implements List {

    Node head;
    Node tail;
    int len;

    public EfficientLinkedList() {
        head = null;
        tail = null;
        len = 0;
    }

    @Override
    public boolean contains(int value) {

        if (head == null) {
            return false;
        } else {
            Node current = head;

            while (current != null) {
                if (current.value == value) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        len++;
    }

    @Override
    public int length() {
        return len;
    }
}


public class ListsExample {
    static void testList(List list) {
        list.append(10);
        list.append(20);

        System.out.println(list.contains(10));
        System.out.println(list.contains(20));
        System.out.println(list.contains(30));
        System.out.println(list.length());
    }

    public static void main(String[] args) {
        testList(new LinkedList());
        System.out.println();
        testList(new ArrayList());

        testList(new EfficientArrayList());
        testList(new EfficientLinkedList());
    }
}



