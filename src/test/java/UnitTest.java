import org.Util.Util;
import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    public void testIsPostalCodeValid() {
        assertTrue(Address.isPostalCodeValid("Z7C 1R4"));
        assertTrue(Address.isPostalCodeValid("P7K8G4"));

        assertFalse(Address.isPostalCodeValid("873412"));
        assertFalse(Address.isPostalCodeValid("Z5G3J"));
        assertFalse(Address.isPostalCodeValid("K0 GE5J"));
        assertFalse(Address.isPostalCodeValid("JGEION"));
    }

    @Test
    public void testCalcAssignmentAvg() {

        Assignment assignment = new Assignment("Assignment 1", 0.4, 100);
        ArrayList<Integer> scores = new ArrayList<>();

        scores.add(60);
        scores.add(40);
        scores.add(80);
        assignment.setScores(scores);
        assignment.calcAssignmentAvg();

        assertEquals(60, assignment.getAssignmentAverage(), 0.01);
    }

    @Test
    public void testIsAssignmentWeightValid() {

        ArrayList<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment("Assignment 1", 0.4, 100));
        assignments.add(new Assignment("Assignment 2", 0.6, 100));


        Department department = new Department("D02", "Computer Science");
        Course course = new Course("C-D02-05", "Programming", 3.0, department, assignments, new ArrayList<>(), new ArrayList<>());


        assertTrue(course.isAssignmentWeightValid());


        assignments.clear();
        assignments.add(new Assignment("Assignment 1", 0.5, 100));
        assignments.add(new Assignment("Assignment 2", 0.6, 100));


        course = new Course("C-D02-05", "Game Programming", 3.0, department, assignments, new ArrayList<>(), new ArrayList<>());

        assertFalse(course.isAssignmentWeightValid());
    }

    @Test
    public void calcStudentAvg1() {
        Department department = new Department("D01", "Computer Science");

        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        assignments.add(new Assignment("Assignment1", 0.2, 100));
        assignments.add(new Assignment("Assignment2", 0.8, 100));

        Course course = new Course("01", "Programming", 4, department,
                assignments, new ArrayList<Student>(), new ArrayList<Double>());

        Student student = new Student("Luca Vaccaro", Gender.MALE, new Address(123,
                "Negra Arroyo Lane", "Albuquerque", "New Mexico", "H5K0D5", "USA"),
                department);

        student.registerCourse(course);
        assignments.getFirst().setScores(new ArrayList<>(List.of(68)));
        assignments.get(1).setScores(new ArrayList<>(List.of(68)));

        int[] averages = course.calcStudentsAverage();
        Assertions.assertEquals(68, averages[0]);
    }

    @Test
    public void testToTitleCase() {
        assertEquals("Money Cash", Util.toTitleCase("money cash"));
        assertEquals("Mani Street", Util.toTitleCase("mani street"));
        assertEquals("Los", Util.toTitleCase("LOS"));
        assertEquals("Test Case", Util.toTitleCase("test CASE"));
        assertEquals("Amen", Util.toTitleCase("AmEN"));
    }
}
