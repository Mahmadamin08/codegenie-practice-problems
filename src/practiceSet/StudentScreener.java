package practiceSet;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class StudentScreener {
    public static void main(String[] args) {

        int totalStudents = 7;
        int totalMarks = 1000;
        float requiredPercentile = 50f;

        int[] marks = {800, 300, 750, 760, 680, 790, 640};
        String[] students = { "Kartik", "Devang", "Pari", "Ketan", "Sheetal", "Darshana", "Mohan" };
        String[] examResults = {"Passed", "Failed", "Passed", "Failed", "Passed", "Passed", "Passed"};

        String studentList = getEligibleStudents(totalStudents, students, marks, examResults, requiredPercentile);

        System.out.println(studentList);

    }

    public static String getEligibleStudents(int totalStudents, String[] students, int[] marks, String[] examResults, float requiredPercentile) {

        /**
         * building max heap to store student data in descending order of their marks, so rank will be calculated easily
         * here max heap will be efficient to store data in descending order, otherwise we have to sort manually
         * by default PriorityQueue implements min heap, Collections.reverseOrder() is used to build max heap
         */
        PriorityQueue<Student> pq = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(Student::getMarks)));

        // insertion into max heap based on marks
        for(int i = 0 ; i<marks.length ; i++)
            pq.add(new Student(marks[i], i));

        // student data is stored in decreasing order, hence first element of max heap is 1st ranked, so called ...
        int studentRank = 1;

        StringBuilder studentList = new StringBuilder();

        // process all students in pq
        while(!pq.isEmpty()) {

            // remove top student
            Student top = pq.poll();

            // calculate percentile
            float pr = getPercentile(totalStudents, studentRank);

            // matching eligibility criteria
            if(examResults[top.markIndex].equals("Passed") && pr >= requiredPercentile ) {
                if(studentRank != 1) studentList.append(",");
                studentList.append(students[top.markIndex]);
            }

            // incrementing student rank
            studentRank++;
        }

        return studentList.toString();
    }

    public static float getPercentile(int n, int rank) {
        return ((float)(n- rank) / n) * 100;
    }
}

class Student {
    int marks;
    int markIndex;

    public Student(int marks, int markIndex) {
        this.marks = marks;
        this.markIndex = markIndex;
    }

    public int getMarks() {
        return marks;
    }
}