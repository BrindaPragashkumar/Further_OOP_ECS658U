package Lab4;

import java.lang.reflect.Field;
import java.util.*;

public class FieldTypeCounter implements Stringify {
    static String incIndent = " ";
    HashCounter counter = new HashCounter();

    public FieldTypeCounter() {
        // Handle fields in the constructor
        Field[] fields = getClass().getDeclaredFields();
        for (Field f : fields) {
            counter.incrementCount(f.getType().getName());
        }
    }

    @Override
    public String stringify(Object o) {
        return null;
    }

    class HashCounter implements Iterable<Map.Entry<Object, Integer>> {
        private final HashMap<Object, Integer> counter;

        public HashCounter() {
            counter = new HashMap<>();
        }

        public void incrementCount(Object object) {
            counter.put(object, counter.computeIfAbsent(object, k->0) + 1);
        }

        public void incrementCount(Object object, int n) {
            counter.put(object, counter.computeIfAbsent(object, k->0) + n);
        }

        public int getCount(Object object) {
            return counter.computeIfAbsent(object, k -> 0);
        }

        public HashCounter add(HashCounter other) {
            HashCounter result = new HashCounter();
            for (Object key : counter.keySet()) {
                result.incrementCount(key, counter.get(key));
            }
            for (Object key : other.counter.keySet()) {
                result.incrementCount(key, other.counter.get(key));
            }
            return result;
        }

        // Method to return an iterator of key,value pairs in order of decreasing count
        @Override
        public Iterator<Map.Entry<Object, Integer>> iterator() {
            TreeMap<Object, Integer> sortedMap = new TreeMap<>(new ValueComparator(counter));
            sortedMap.putAll(counter);
            return sortedMap.entrySet().iterator();
        }

        class ValueComparator implements Comparator<Object> {
            HashMap<Object, Integer> base;

            public ValueComparator(HashMap<Object, Integer> base) {
                this.base = base;
            }

            @Override
            public int compare(Object a, Object b) {
                if (base.get(a) >= base.get(b)) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }

    }
}
