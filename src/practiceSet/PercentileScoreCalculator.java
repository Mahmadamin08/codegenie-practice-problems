package practiceSet;

public class PercentileScoreCalculator {

    public static void main(String[] args) {

        int totalStudents = 7;
        int[] marks = {800, 300, 950, 760, 680, 490, 640};
        int yourScore = 760;

        float percentileScore = calculatePercentileScore(totalStudents, marks, yourScore);

        System.out.printf("My percentile : %.2f", percentileScore);

    }

    public static float calculatePercentileScore(int totalStudents, int[] marks, int yourScore)  {

        int x = 0;

        // finding total number of students having marks less than me
        for(int m: marks) if(m < yourScore) x++;

        // calculating pr
        float pr = ((float) x / totalStudents) * 100;

        return pr;
    }

}
