package Lab2;

import java.util.Iterator;

public class OddRange implements Iterable<Integer>{
    private final int start;
    private final int end;
    public OddRange(int start, int end){
        this.start = start;
        this.end = end;
    }
    public Iterator<Integer> iterator(){
        return new OddRange.OddRangeIterator(start, end);
    }
    private static class OddRangeIterator implements Iterator<Integer>{
        private int current;
        private final int end;

        public OddRangeIterator(int start , int end){
            this .current = start;
            this.end = end;
        }
        public boolean hasNext(){
            return current < end;
        }

        public Integer next(){
            if(current%2 == 0) {
                return current+=1;
            }
            return current+=2;
        }

    }

    public static void main(String[] args) {
        OddRange range = new OddRange(-6, 5);
        for (int num : range) {
            System.out.println(num);
        }
    }
}
