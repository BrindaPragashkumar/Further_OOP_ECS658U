import java.lang.reflect.Field;
import java.util.*;


public class ObjectGraphIterable implements Iterable<Object> {
    private final Object root;

    public ObjectGraphIterable(Object root) {
        this.root = root;
    }

    private void follow(Object o, List<Object> objects, Set<Object> seen) {
        if (o == null || seen.contains(o)) {
            return;
        }
        seen.add(o);
        objects.add(o);

        Class<?> c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            try {
                f.setAccessible(true);
                if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) {
                    continue;
                }

                if (!f.getType().isPrimitive() && !f.getType().equals(String.class)
                        && f.get(o) != null) {
                    follow(f.get(o), objects, seen);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public Iterator<Object> iterator() {
        List<Object> objects = new ArrayList<>();
        Set<Object> seen = new HashSet<>();
        follow(root, objects, seen);
        return objects.iterator();
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(10);
        arrayList.add(20);

        ObjectGraphIterable iterable = new ObjectGraphIterable(arrayList);
        for (Object obj : iterable) {
            System.out.println(obj);
        }
    }
}