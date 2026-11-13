package netbeans_school;

import java.util.ArrayList;
/* CHANGES
--> removed regular lists for simplicity 
--> methods completely renovated 
--> added exception handling */
public class StudentService {
    private final ArrayList<Student> students = new ArrayList<Student>();

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student is required.");
        }
        if (findStudentById(student.getId()) != null) {
            throw new IllegalArgumentException("Duplicate student ID.");
        }
        students.add(student);
    }

    public boolean removeStudent(int id) {
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        if (student.getSchoolClass() != null) {
            student.getSchoolClass().removeStudent(student);
            student.setSchoolClass(null);
        }

        return students.remove(student);
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> search(String keyword) {
        ArrayList<Student> results = new ArrayList<Student>();

        for (Student student : students) {
            if (student.matches(keyword)) {
                results.add(student);
            }
        }

        return results;
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<Student>(students);
    }
}
