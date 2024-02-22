package Lab4;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectStringifier {
    private HashMap<Object, Integer> seenObjects;
    private int objectId;

    public ObjectStringifier() {
        seenObjects = new HashMap<>();
        objectId = 0;
    }

    public String stringify(Object obj) {
        if (obj == null) {
            return "null";
        }

        if (seenObjects.containsKey(obj)) {
            return "Reference to Object #" + seenObjects.get(obj);
        }

        seenObjects.put(obj, objectId);
        objectId++;

        if (obj.getClass().isArray()) {
            return formatArray(obj);
        }

        if (obj instanceof ArrayList) {
            return formatArrayList((ArrayList<?>) obj);
        }

        StringBuilder output = new StringBuilder();
        for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
            if (!field.isAccessible()) {
                // Skip inaccessible fields
                continue;
            }

            try {
                Object fieldValue = field.get(obj);
                output.append(formatField(field.getName(), fieldValue));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return output.toString();
    }

    private String formatField(String fieldName, Object value) {
        if (value == null) {
            return fieldName + ": null\n";
        }

        StringBuilder fieldString = new StringBuilder();
        fieldString.append(fieldName).append(": ");
        if (value.getClass().isPrimitive() || value instanceof String) {
            fieldString.append(value).append("\n");
        } else {
            fieldString.append(stringify(value)).append("\n");
        }
        return fieldString.toString();
    }

    private String formatArray(Object array) {
        StringBuilder arrayString = new StringBuilder();
        arrayString.append(array.getClass().getName()).append("\n");

        arrayString.append("size = ").append(java.lang.reflect.Array.getLength(array)).append("\n");

        arrayString.append("[");

        int length = java.lang.reflect.Array.getLength(array);
        for (int i = 0; i < length; i++) {
            Object element = java.lang.reflect.Array.get(array, i);
            arrayString.append(element);
            if (i < length - 1) {
                arrayString.append(", ");
            }
        }

        arrayString.append("]\n");

        return arrayString.toString();
    }

    private String formatArrayList(ArrayList<?> arrayList) {
        StringBuilder arrayListString = new StringBuilder();
        arrayListString.append(arrayList.getClass().getName()).append("\n");

        arrayListString.append("elementData =\n");

        arrayListString.append("size : ").append(arrayList.size()).append("\n");

        arrayListString.append("[");

        int index = 0;
        for (Object element : arrayList) {
            arrayListString.append(element).append(", ");
            index++;
        }
        arrayListString.append("]\n");

        return arrayListString.toString();
    }
}