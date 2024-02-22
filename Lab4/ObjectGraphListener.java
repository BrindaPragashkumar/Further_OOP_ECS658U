package Lab4;

import java.lang.reflect.Field;

public interface ObjectGraphListener {
    void onObjectStart(Object o);
    void onObjectEnd(Object o);
    void onField(Object parent, Field field, Object value);
}