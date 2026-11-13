package netbeans_school;
// class made by Yehia so changes will not be listed
import java.util.ArrayList;

public class SchoolClass {
    private final String className;
    private int capacity;
    private Teacher classTeacher;// --> no arraylist for teachers since each class has only 1 teacher
    private final ArrayList<Student> students = new ArrayList<Student>();
    private final ArrayList<Subject> subjects = new ArrayList<Subject>();
// arraylists here in specific since each class does not have a constant amount of students
    public SchoolClass(String className, int capacity) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be blank.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }

        this.className = className.trim();
        this.capacity = capacity;
    }

    public String getClassName() {
        return className;
    }

    public int getCapacity() {
        return capacity;
    }

    public Teacher getClassTeacher() {
        return classTeacher;
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<Student>(students);
    }

    public ArrayList<Subject> getSubjects() {
        return new ArrayList<Subject>(subjects);
    }

    public void setCapacity(int capacity) {
        if (capacity < students.size()) {
            throw new IllegalArgumentException(
                    "Capacity cannot be below the current student count."
            );
        }
        this.capacity = capacity;
    }

    public void setClassTeacher(Teacher classTeacher) {
        this.classTeacher = classTeacher;
    }

    public boolean canAccept(Student student) {
        return student != null && !students.contains(student) && !isFull();
    }

    public boolean addStudent(Student student) {
        if (!canAccept(student)) {
            return false;
        }
        students.add(student);
        return true;
    }

    public boolean removeStudent(Student student) {
        return students.remove(student);
    }

    public boolean containsStudent(Student student) {
        return students.contains(student);
    }

    public boolean isFull() {
        return students.size() >= capacity;
    }

    public int getStudentCount() {
        return students.size();
    }

    public boolean addSubject(Subject subject) {
        if (subject == null || subjects.contains(subject)) {
            return false;
        }
        subjects.add(subject);
        return true;
    }

    public boolean removeSubject(Subject subject) {
        return subjects.remove(subject);
    }

    public boolean hasSameClassName(SchoolClass other) {

    if (other == null) {
        return false;
    }

    return className.equalsIgnoreCase(other.getClassName());
}

 

    public String getDisplayText() {
    return className;
}
}
