package Lab4;
import java.lang.Object;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class BasicStringify implements Stringify{
    // basic flawed version that only works for simple classes
    //no private fields
    //no cyclic graphs
    // no traversal of object graph
    public String stringify(Object o){
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getSimpleName());
        sb.append(" (");
        for(var f : o.getClass().getDeclaredFields()){
            try{
                f.setAccessible(true); // Exercise 1
                sb.append(f.getName());
                sb.append("=");
                sb.append(f.get(o));
                sb.append(",");
            }  catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        sb.append(")");
        return sb.toString();
    }





}

