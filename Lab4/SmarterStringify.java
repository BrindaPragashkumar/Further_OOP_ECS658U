package Lab4;

import javax.naming.Binding;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class SmarterStringify implements Stringify {
    static String incIndent = "  ";

    public String stringify(Object o) {
        StringBuilder sb = new StringBuilder();
        stringify(o, " ", sb, new HashSet<>());
        return sb.toString();

    }

    public void stringify(Object o, String indent, StringBuilder sb, Set<Object> seen) {
        if (o == null) {
            sb.append("null");
            return;
        }
        Class<?> c = o.getClass();
        sb.append(indent).append(c.getName()).append(" ");

        if (seen.contains(o)) {

            int hashcode = System.identityHashCode(o);
            String hex = Integer.toHexString(hashcode);
            sb.append("@").append(hex).append("\n");
            return;
        }
        seen.add(o);
        if (c.isArray()) {
            sb.append("[");
            if (o instanceof int[]) {
                for (int el : (int[]) o) {
                    sb.append(el).append(", ");
                }
            } else if (o instanceof Object[]) {
                for (Object el : (Object[]) o) {
                }}else{
                    throw new UnsupportedOperationException("Only int[] and Object[] supported for arrays");

                }
                sb.append("]\n");
                return;
            }
            Field[] fields = c.getDeclaredFields();
            for(Field f :fields){
                try{
                    f.setAccessible(true);
                    if(Modifier.isStatic(f.getModifiers())){
                        continue;
                    }
                    sb.append(indent).append(f.getName()).append(" = ");
                    if(f.getType().isPrimitive()||f.getType().equals(String.class)||f.get(o) == null){
                        sb.append(f.get(o)).append("\n");
                    } else {
                        stringify(f.get(o), indent + incIndent , sb,seen);
                    }
                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }


        }
    }



