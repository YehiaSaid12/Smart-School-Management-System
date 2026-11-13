package netbeans_school;

import java.util.ArrayList;
// no significant changes, same as previous classes
public class Teacher extends Person implements Searchable {
    private String specialization;
    private final ArrayList<Subject> subjects = new ArrayList<Subject>();

    public Teacher(
            int id,
            String name,
            String email,
            String phone,
            String specialization
    ) {
        super(id, name, email, phone);
        setSpecialization(specialization);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be blank.");
        }
        this.specialization = specialization.trim();
    }

    public void addSubject(Subject subject) {
        if (subject != null && !subjects.contains(subject)) {
            subjects.add(subject);
        }
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    public boolean teachesSubject(Subject subject) {
        return subjects.contains(subject);
    }

    public ArrayList<Subject> getSubjects() {
        return new ArrayList<Subject>(subjects);
    }

    @Override
    public String getRole() {
        return "TEACHER";
    }

    @Override
    public boolean matches(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return true;
        }

        String key = keyword.toLowerCase();

        return getName().toLowerCase().contains(key)
                || getEmail().toLowerCase().contains(key)
                || String.valueOf(getId()).contains(key)
                || specialization.toLowerCase().contains(key);
    }
}
