package netbeans_school;

import java.util.ArrayList;
// class made by yehia, changes not listed
public class GradeService {
    private final ArrayList<Grade> grades = new ArrayList<Grade>();

    public void addGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Grade is required.");
        }
        grades.add(grade);
    }

    public boolean removeGrade(Grade grade) {
        return grades.remove(grade);
    }

    public ArrayList<Grade> getAllGrades() {
        return new ArrayList<Grade>(grades);
    }

    public ArrayList<Grade> getGradesForStudent(Student student) {
        ArrayList<Grade> results = new ArrayList<Grade>();

        for (Grade grade : grades) {
            if (grade.getStudent() == student) {
                results.add(grade);
            }
        }

        return results;
    }

    public ArrayList<Grade> getGradesForSubject(Subject subject) {
        ArrayList<Grade> results = new ArrayList<Grade>();

        for (Grade grade : grades) {
            if (grade.getSubject().equals(subject)) {
                results.add(grade);
            }
        }

        return results;
    }

    public ArrayList<Grade> getGradesForStudentAndSubject(
            Student student,
            Subject subject
    ) {
        ArrayList<Grade> results = new ArrayList<Grade>();

        for (Grade grade : grades) {
            if (grade.getStudent() == student
                    && grade.getSubject().equals(subject)) {
                results.add(grade);
            }
        }

        return results;
    }
}
