package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Assignment {

    private String assignmentId;
    private String assignmentName;
    private double weight;
    private int maxScore;
    private double assignmentAverage;
    private ArrayList<Integer> scores;
    private static int nextId;


    /**
     * calculates the average score for one assignment
     */
    public void calcAssignmentAvg() {
        if (scores.isEmpty()) {
            this.assignmentAverage = 0;
            return;}

        int sum = 0;
        for (int score : scores) {
            sum += score;}
        this.assignmentAverage = (double) sum / scores.size();
    }

    /**
     * generates random scores for all students in an assignment, the scores are generated with the following rule:
     * Firstly generate a random number in range [0, 10], then
     * if the number is 0, then generate a random score in range [0, 60) for the student
     * if the number is 1, 2, then generate a random score in range [60, 70) for the student
     * if the number is 3, 4, then generate a random score in range [70, 80) for the student
     * if the number is 5, 6, 7, 8, then generate a random score in range [80, 90) for the student
     * if the number is 9, 10, then generate a random score in range [90, 100] for the student
     */
    public void generateRandomScore() {
        Random rand = new Random();
        int randomRange = rand.nextInt(0, 11);

        int score = 0;
        score = switch (randomRange) {
            case 0 -> rand.nextInt(0, 60);
            case 1, 2 -> rand.nextInt(60, 70);
            case 3, 4 -> rand.nextInt(70, 80);
            case 5, 6, 7, 8 -> rand.nextInt(80, 90);
            default -> rand.nextInt(90, 101);
        };
        System.out.println(score);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                ", maxScore=" + maxScore +
                '}';
    }
}