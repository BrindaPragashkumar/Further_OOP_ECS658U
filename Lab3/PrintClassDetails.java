package Lab3;

import java.lang.reflect.*;

public class PrintClassDetails {

    public static void main(String[] args) {
        printClass(MyCircle.class);
    }

    static void printClass(Class<?> c) {
        System.out.println("Inspecting class:" + c.getName());
        System.out.println("\nFields: ");
        for (Field field : c.getDeclaredFields()) {
            printType(field.getName(), field.getGenericType());
        }

        // Methods
        System.out.println("\nMethods: ");
        for (Method method : c.getDeclaredMethods()) {
            System.out.println("Method Name: " + method.getName());

            System.out.println("Return Type :" + method.getReturnType());
            Parameter[] parameter = method.getParameters();
            System.out.println("Parameters : ");
            for(Parameter param : parameter){
                System.out.println(param.getName()+" Type: "+param.getType());
            }

        }

    // Inspect methods - omitted for brevity

    System.out.println("\nConstructors: ");

    for(Constructor<?> constructor :c.getDeclaredConstructors()){
        System.out.println(constructor);
        Type[] parameterType = constructor.getGenericParameterTypes();
        for (Type paramType : parameterType) {
            printType(constructor.getName(), paramType);
        }
        System.out.println();
    }

}
    public static void printType(String name, Type type){
        System.out.println("Name : " + name + " Type :" + type.getTypeName());

    }

}
