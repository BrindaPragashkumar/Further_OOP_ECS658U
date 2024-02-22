package Lab3;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class UMLShapesExample {
    public static void main(String[] args) {
        List<Class<?>> classes = new ArrayList<>();

        classes.add(MyShape.class);
        classes.add(MyCircle.class);
        classes.add(Connector.class);
        ClassData cd = new ClassData(classes);
        System.out.println(cd.toMermaid());
    }
}

class ClassData {
    Set<Class<?>> classes = new HashSet<>();

    List<Link> links = new ArrayList<>();

    public ClassData(List<Class<?>> classes) {


        this.classes.addAll(classes);
        findSuperclasses();
        findDependencies();
        findInterfaces();
        findFields();
    }


    public void findParamTypes(Class<?> c, Type type) {
        if (type instanceof ParameterizedType pType) {
            for (Type typeArg : pType.getActualTypeArguments()) {
                links.add(new Link(c, (Class<?>) typeArg, LinkType.DEPENDENCY));
                findParamTypes(c, typeArg);
            }
        }
    }

    public void findSuperclasses() {
        for (Class<?> c : classes) {
            Class<?> superClass = c.getSuperclass();
            if (superClass != null && classes.contains(superClass)) {
                links.add(new Link(superClass, c, LinkType.SUPERCLASS));
            }
        }
    }

    public void findDependencies() {
        for (Class<?> c : classes) {
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                Class<?> fieldType = field.getType();
                if (classes.contains(fieldType)) {
                    links.add(new Link(c, fieldType, LinkType.DEPENDENCY));
                }
            }
        }
    }

    public void findInterfaces() {
        for (Class<?> c : classes) {
            for (Class<?> i : c.getInterfaces()) {
                if (classes.contains(i)) {
                    links.add(new Link(i, c, LinkType.SUPERCLASS));
                }
            }
        }

    }

    //There is a limitation in the current implementation of findFields: it does not work with types
    //passed as parameters (see exercise to fix this below).
    public void findFields() {
        for (Class<?> c : classes) {
            for (Field f : c.getDeclaredFields()) {
                if (classes.contains(f.getType())) {
                    links.add(new Link(c, f.getType(), LinkType.DEPENDENCY));
                }
                findParamTypes(c, f.getGenericType());
            }
        }

    }

    public static String mermaidClassString(Class<?> c) {
        StringBuilder sb = new StringBuilder();
        sb.append("class ").append(c.getSimpleName()).append(" {\n");

        for (Field f : c.getDeclaredFields()) {
            String fieldString = getFieldString(f);
            sb.append(fieldString).append("\n");
        }

        for (Method method : c.getDeclaredMethods()) {
            String methodString = getMethodString(method);
            sb.append(methodString).append("\n");
        }

        sb.append("}\n");

        return sb.toString();
    }

    private static String getFieldString(Field field) {
        String visibilityModifier = getVisibilityModifier(field.getModifiers());
        String staticModifier = Modifier.isStatic(field.getModifiers()) ? "static: " : "";
        return visibilityModifier + " " + staticModifier + field.getName() + ": " + field.getType().getSimpleName();
    }

    private static String getMethodString(Method method) {
        String visibilityModifier = getVisibilityModifier(method.getModifiers());
        String staticModifier = Modifier.isStatic(method.getModifiers()) ? "static :  " : "";
        return visibilityModifier + " " + staticModifier + method.getName() + "()";
    }

    private static String getVisibilityModifier(int modifiers) {
        if (Modifier.isPublic(modifiers)) {
            return "+"; // Public
        } else if (Modifier.isPrivate(modifiers)) {
            return "-"; // Private
        } else if (Modifier.isProtected(modifiers)) {
            return "#"; // Protected
        } else {
            return "~"; // Package-private (default)
        }
    }

    public String toMermaid() {
        StringBuilder sb = new StringBuilder();
        sb.append("classDiagram\n");
        for (Class<?> c : classes) {
            sb.append(mermaidClassString(c));
        }
        for (Link l : links) {
            String linkString = String.format("%s %s %S \n", l.from().getSimpleName(), l.type().linkString, l.to().getSimpleName());
            sb.append(linkString);
        }
        return sb.toString();
    }

}

enum LinkType {
    SUPERCLASS("<|--"),
    DEPENDENCY("..>");


    public final String linkString;

    LinkType(String linkString) {
        this.linkString = linkString;
    }
}

record Link(Class<?> from, Class<?> to, LinkType type) {
}