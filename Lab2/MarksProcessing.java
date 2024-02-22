package Lab2;

import java.util.Map;
import java.util.TreeMap;
record MarkRecord(int studentId , int moduleId , int mark){}

class MarksData{
    Map<Integer , GenericList<Integer>> moduleMap = new TreeMap<>();
    Map<Integer, GenericList<Integer>> studentMap = new TreeMap<>();


    void addRecord(MarkRecord record){
        GenericList<Integer> moduleMarks =
                moduleMap.computeIfAbsent(record.moduleId(), k -> new GenericArrayList<>());
        moduleMarks.append(record.mark());
        GenericList<Integer> studentMarks =
                studentMap.computeIfAbsent(record.studentId(), k -> new GenericArrayList<>());
        studentMarks.append(record.mark());
    }

}

public class MarksProcessing {
    static GenericList<MarkRecord> generateRandomMarkRecords(int nStudents, int nModules){
        GenericList<MarkRecord> records = new GenericArrayList<>();
        for(int i=0; i< nStudents; i++){
            for(int j=0; j< nModules; j++){
                records.append(new MarkRecord(i,j, (int) (Math.random() *100)));
            }
        }

        return  records;
    }

    static double average(GenericList<Integer> data){
        int count = 0;
        int index = 0;

        for (Integer mark: data){
            count += mark;
            index++;
        }
     return (double) count/index;
    }

    static void printStats(MarksData data){
        System.out.println("Printing in order of module ID:");
        for (Integer moduleId : data.moduleMap.keySet()) {
            GenericList<Integer> moduleMarks = data.moduleMap.get(moduleId);

            System.out.println("Module ID: " + moduleId);
            System.out.println("Average Mark: " + average(moduleMarks));
            System.out.println("Marks: " + average(data.moduleMap.get(moduleId)));
            System.out.println();
        }
        System.out.println("Printing in order of student ID:");
        for (Integer studentId : data.studentMap.keySet()) {
            GenericList<Integer> studentMarks = data.studentMap.get(studentId);

            System.out.println("Student ID: " + studentId);
            System.out.println("Average Mark: " + average(studentMarks));
            System.out.println("Marks: " + average(data.studentMap.get(studentId)));
            System.out.println();
        }

    }


    public static void main(String[] args){
        GenericList<MarkRecord> marks = generateRandomMarkRecords(5,2);
        for(MarkRecord mark : marks){
            System.out.println(mark);
        }

        MarksData processedMarks = new MarksData();
        for (MarkRecord mark: marks){
            processedMarks.addRecord(mark);
        }

        System.out.println();
        System.out.println(processedMarks.moduleMap);
        System.out.println();
        System.out.println(processedMarks.studentMap);
        System.out.println();
        printStats(processedMarks);
    }

}
