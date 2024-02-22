package Lab1;

public class ListSpeedTest {
    static long time_test(List list, int n){
            long startTime = System.currentTimeMillis();
            for(int i = 0; i < n; i++ ){
                list.append(i);
            }
            long endTime = System.currentTimeMillis();
            long timeElapsed = (endTime - startTime) ;
            System.out.println("List class:" + list.getClass() );
            System.out.println("Time taken to append "+ n + " elements:" + timeElapsed);
            return (int) (timeElapsed);
    }

    class ListFactory(){
        List create(class<?> clazz){

        }
    }

    public static void main(String[] args){
        List[] lists = {
                new ArrayList(),
                new LinkedList(),
                new EfficientArrayList(),
                new EfficientLinkedList(),
        };

        int n =10000;
        for (List list : lists){
            time_test(list,n);
        }
    }
}



