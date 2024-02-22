package Lab2;

//type variables
// a class can have many different type variables.
//most recent object of any type, and on request it returns an
//object of that type

public class TestMostRecentObject{
    public static void main(String[] args){
        MostRecentObject<String> mro = new MostRecentObject<>();
        System.out.println(mro.getMostRecentObject());
        mro.add("hello");
//        mro.add("world");
        System.out.println(mro.getMostRecentObject());
        //String str = mro.getMostRecentObject();
        //System.out.println(str);

//        Integer x = mro.getMostRecentObject();
//        String cannot be converted to java.lang.Integer

        }

}
