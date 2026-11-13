package netbeans_school;

import java.util.ArrayList;
// class made by yehia, changes not listed
public class ClassService {
    private final ArrayList<SchoolClass> classes = new ArrayList<SchoolClass>();
    private final ArrayList<Subject> subjects = new ArrayList<Subject>();

    public void addClass(SchoolClass schoolClass) {
        if (schoolClass == null) {
            throw new IllegalArgumentException("Class is required.");
        }
        if (findClass(schoolClass.getClassName()) != null) {
            throw new IllegalArgumentException("Duplicate class name.");
        }
        classes.add(schoolClass);
    }

    public boolean removeClass(String className) {
        SchoolClass schoolClass = findClass(className);

        if (schoolClass == null) {
            return false;
        }

        ArrayList<Student> enrolledStudents = schoolClass.getStudents();

        for (Student student : enrolledStudents) {
            schoolClass.removeStudent(student);

            if (student.getSchoolClass() == schoolClass) {
                student.setSchoolClass(null);
            }
        }

        return classes.remove(schoolClass);
    }

    public SchoolClass findClass(String className) {
        if (className == null) {
            return null;
        }

        for (SchoolClass schoolClass : classes) {
            if (schoolClass.getClassName().equalsIgnoreCase(className)) {
                return schoolClass;
            }
        }

        return null;
    }

    public ArrayList<SchoolClass> getAllClasses() {
        return new ArrayList<SchoolClass>(classes);
    }

    public void addSubject(Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject is required.");
        }
        if (subjects.contains(subject)) {
            throw new IllegalArgumentException("Duplicate subject code.");
        }
        subjects.add(subject);
    }

    public Subject findSubject(String code) {
        if (code == null) {
            return null;
        }

        for (Subject subject : subjects) {
            if (subject.getCode().equalsIgnoreCase(code)) {
                return subject;
            }
        }

        return null;
    }

    public ArrayList<Subject> getAllSubjects() {
        return new ArrayList<Subject>(subjects);
    }

    public void assignStudent(Student student, SchoolClass schoolClass) {
        if (student == null || schoolClass == null) {
            throw new IllegalArgumentException("Student and class are required.");
        }

        SchoolClass oldClass = student.getSchoolClass();

        if (oldClass == schoolClass) {
            return;
        }

        if (!schoolClass.canAccept(student)) {
            throw new IllegalStateException("Student cannot be assigned to this class.");
        }

        if (oldClass != null) {
            oldClass.removeStudent(student);
        }

        schoolClass.addStudent(student);
        student.setSchoolClass(schoolClass);
    }

    public void assignTeacher(Teacher teacher, SchoolClass schoolClass) {
        if (teacher == null || schoolClass == null) {
            throw new IllegalArgumentException("Teacher and class are required.");
        }
        schoolClass.setClassTeacher(teacher);
    }

    public void assignSubject(Subject subject, SchoolClass schoolClass) {
        if (subject == null || schoolClass == null) {
            throw new IllegalArgumentException("Subject and class are required.");
        }
        schoolClass.addSubject(subject);
    }
}
