
package Lab2;
import java.util.Iterator;


interface GenericList<T> extends Iterable<T> {
        boolean contains(T value);

        void append(T value);

        int length();
}
class GenericNode <T>{
    T value;
    GenericNode<T> next;


    public GenericNode(T value){
        this.value = value;
        this.next = null;
    }

}

class GenericLinkedList<T> implements GenericList<T> {
    GenericNode<T> head;
    int len;


    public GenericLinkedList(){
        head = null;
        len = 0;
    }

    public boolean contains(T value){
        GenericNode<T> current = head;
        while(current != null) {
            if(current.value == value)
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void append(T value) {
        GenericNode<T> newGenericNode = new GenericNode<>(value);
        if(head==null){
            head = newGenericNode;
        }else{
            GenericNode<T> current = head;
            while(current != null) {
            current = current.next;
            }
            current.next = newGenericNode;
        }
        len++;

    }

    public int length(){
        return len;
    }

    public Iterator<T> iterator(){
        return new GenericLinkedListIterator<T>(this);
    }

    private static class GenericLinkedListIterator<T> implements Iterator<T>{
        private GenericNode<T> current;

        public GenericLinkedListIterator(GenericLinkedList<T> list){
            current = list.head;
        }

        public boolean hasNext(){
            return current != null;
        }

        public T next(){
            T value = current.value;
            current = current.next;
            return value;
        }
    }






}
class GenericArrayList<T> implements GenericList<T>{
    static int initialCapacity = 10;

    T[] values = (T[]) new Object[initialCapacity];
    int len = 0;

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < len; i++) {
            if (values[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void append(T value) {
        T[] newValues = (T[]) new Object[initialCapacity];
        for(int i=0; i<len; i++){
            newValues[i] = values[i];
        }
        newValues[len] = value;
        values = newValues;
        len++;

    }

    @Override
    public int length() {return len;}

    @Override
    public Iterator<T> iterator() {
        return new GenericArrayListIterator<T>(this);
    }
    private static class GenericArrayListIterator<T> implements Iterator<T>{
        private int index;
        private T[] list;

        private int len;
        public GenericArrayListIterator(GenericArrayList<T> list){
            this.len = list.len;
            this.list = list.values;
            this.index = 0;
        }

        public boolean hasNext(){return index < len;}
        public T next() { return list[index++];}

    }
}
