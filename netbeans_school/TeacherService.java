package netbeans_school;

import java.util.ArrayList;
/* CHANGES
--> removed regular lists for simplicity 
--> methods completely renovated 
--> added exception handling */
public class TeacherService {
    private final ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    public void addTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher is required.");
        }
        if (findTeacherById(teacher.getId()) != null) {
            throw new IllegalArgumentException("Duplicate teacher ID.");
        }
        teachers.add(teacher);
    }

    public boolean removeTeacher(int id) {
        Teacher teacher = findTeacherById(id);
        if (teacher == null) {
            return false;
        }
        return teachers.remove(teacher);
    }

    public Teacher findTeacherById(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    public ArrayList<Teacher> search(String keyword) {
        ArrayList<Teacher> results = new ArrayList<Teacher>();

        for (Teacher teacher : teachers) {
            if (teacher.matches(keyword)) {
                results.add(teacher);
            }
        }

        return results;
    }

    public ArrayList<Teacher> getAllTeachers() {
        return new ArrayList<Teacher>(teachers);
    }

    public void assignSubject(Teacher teacher, Subject subject) {
        if (teacher == null || subject == null) {
            throw new IllegalArgumentException("Teacher and subject are required.");
        }
        teacher.addSubject(subject);
    }
}
