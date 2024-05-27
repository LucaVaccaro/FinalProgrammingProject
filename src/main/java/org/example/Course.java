package org.example;


import lombok.Getter;
import lombok.Setter;
import org.Util.Util;

import java.util.ArrayList;
@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Double> finalScores;
    private static int nextId = 1;

    public Course(String courseId, String courseName, double credits, Department department,
                  ArrayList<Assignment> assignments, ArrayList<Student> registeredStudents,
                  ArrayList<Double> finalScores) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = assignments;
        this.registeredStudents = registeredStudents;
        this.finalScores = finalScores;
    }

    /**
     * checks if the sum of weights of all assignments of that course equals to 1 (100%)
     * @return true if the weights the assignments is 1 and false if the weights is another number.
     */
    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;
        for (Assignment assignment : assignments) {
            totalWeight += assignment.getWeight();
        }
        return totalWeight == 1.0;
    }

    /**
     *  adds a student to the student list of the course,
     *  also add a new null element to each assignment of this course,
     *  and add a new null element for the finalScores.
     * @param student input student
     * @return true after you register the student and false if you didn't.
     */
    public boolean registerStudent(Student student) {
        registeredStudents.add(student);
        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }
        finalScores.add(null);
        return true;
    }

    /**
     * calculates the weighted average score of a student.
     */
    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];

        for (int i = 0; i < registeredStudents.size(); i++) {
            double weightedSumOfScores = 0;
            for (Assignment assignment : assignments) {
                Integer tempScore = assignment.getScores().get(i);
                weightedSumOfScores += tempScore * assignment.getWeight();
            }
            averages[i] = (int) Math.round(weightedSumOfScores);
        }
        return averages;
    }

    /**
     * adds a new assignment to the course
     * @param assignmentName the input assignment name
     * @param weight the input assignment weight
     * @param maxScore the input max score
     * @return true after adding the assignment to assignments
     */
    public boolean addAssignment(String assignmentName, double weight, int maxScore) {
        Assignment assignment = new Assignment(assignmentName, weight, maxScore);
        assignments.add(assignment);
        return true;
    }

    /**
     * generates random scores for each assignment and student, and calculates the final score for each student.
     */
    public void generateScores() {
        for (int i = 0; i < assignments.size(); i++) {
            Assignment assignment = assignments.get(i);
            assignment.getScores().clear();
            for (int j = 0; j < registeredStudents.size(); j++) {
                assignment.generateRandomScore();
            }
        }
        int[] averages = calcStudentsAverage();

        for (int k = 0; k < averages.length; k++) {
            finalScores.set(k, (double) averages[k]);
        }
    }


    /**
     * displays the scores of a course in a table, with the assignment averages and student weighted average
     */
    public void displayScores() {
        generateScores();

        System.out.println("Course: " + courseName + "(C-" + department.getDepartmentId() + "-" + nextId++ + ")");

        System.out.print("Assignments: ");
        for (Assignment assignment : assignments) {
            System.out.printf("%20s", assignment.getAssignmentName());
        }
        System.out.printf("%20s%n", "Final Score");

        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            System.out.printf("%-15s", student.getStudentName());
            for (Assignment assignment : assignments) {
                int score = assignment.getScores().get(i);
                System.out.printf("%20d", score);
            }
            System.out.printf("%20.0f%n", finalScores.get(i));
        }

        System.out.printf("%-15s", "Average");
        for (Assignment assignment : assignments) {
            assignment.calcAssignmentAvg();
            System.out.printf("%20.0f", assignment.getAssignmentAverage());
        }
        System.out.println();
    }

    /**
     * converts a course to a simple string with only the courseId, courseName, credits, and departmentName.
     * @return a course with only the courseId, courseName, credits, and departmentName.
     */
    public String toSimplifiedString() {
        return String.format("Course:\n" +
                        "Course ID: C-%s-%s\n" +
                        "Course name: %s\n" +
                        "Credits: %f\n" +
                        "Department: %s", department.getDepartmentId(),
                nextId++, Util.toTitleCase(courseName), credits, department.getDepartmentName());
    }

    /**
     *  converts a course to a string that contains the courseId, the courseName,
     *  the credits, the departmentName the assignments, and the registeredStudents
     *  (only the studentId, the studentName and the departmentName)
     * @return string that contains the courseId, the courseName,
     * the credits, the departmentName the assignments, and the registeredStudents
     */
    @Override
    public String toString() {
        String students = "";
        for (Student student : registeredStudents) {
            students = student.toSimplifiedString() + "\n";
        }
        return String.format("Course:\n" +
                        "Course ID: C-%s-%s\n" +
                        "Course name: %s\n" +
                        "Department: %s\n" +
                        "Assignment: %s" +
                        "Registered students: %s", department.getDepartmentId(),
                nextId++, Util.toTitleCase(courseName), department, assignments, students);
    }

    public static void setNextId(int nextId) {
        Course.nextId = nextId;
    }
}
