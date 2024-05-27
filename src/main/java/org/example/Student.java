package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private List<Course> registeredCourses;
    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = "S" + String.format("%05d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.add(course);
        course.getRegisteredStudents().add(this);
        for (Assignment assignment : course.getAssignments()) {
            assignment.getScores().add(null);
        }
        course.getFinalScores().add(null);
        return true;
    }

    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);
        for (Assignment assignment : course.getAssignments()) {
            assignment.getScores().remove(course.getRegisteredStudents().indexOf(this));
        }
        course.getFinalScores().remove(course.getRegisteredStudents().indexOf(this));
        return true;
    }

    public String toSimplifiedString() {
        return studentId + " " + studentName + " " + department.getDepartmentName();
    }

    @Override
    public String toString() {
        String result = studentId + " " + studentName + " " + gender + " " + address + " " + department.getDepartmentName() + " Registered Courses: [";
        for (Course course : registeredCourses) {
            result += course.getCourseId() + " " + course.getCourseName() + " " + course.getDepartment().getDepartmentName() + ", ";
        }
        result += "]";
        return result;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public static void setNextId(int nextId) {
        Student.nextId = nextId;
    }
}
