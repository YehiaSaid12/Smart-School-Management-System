package netbeans_school;
/*  CHANGES
--> improved constructor
--> improved setter methods
--> properly overrode previous abstract methods

*/
public class Student extends Person implements Searchable {
    private String gradeLevel;
    private SchoolClass schoolClass;
    private String dateOfBirth;

    public Student(
            int id,
            String name,
            String email,
            String phone,
            String gradeLevel,
            String dateOfBirth
    ) {
        super(id, name, email, phone);
        setGradeLevel(gradeLevel);
        if (dateOfBirth == null) {
    this.dateOfBirth = "";
} else {
    this.dateOfBirth = dateOfBirth.trim();
}
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setGradeLevel(String gradeLevel) {
        if (gradeLevel == null || gradeLevel.trim().isEmpty()) {
            throw new IllegalArgumentException("Grade level cannot be blank.");
        }
        this.gradeLevel = gradeLevel.trim();
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public void setDateOfBirth(String dateOfBirth) {
if (dateOfBirth == null) {
    this.dateOfBirth = "";
} else {
    this.dateOfBirth = dateOfBirth.trim();
}    }

    @Override
    public String getRole() {
        return "STUDENT";
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
                || gradeLevel.toLowerCase().contains(key)
                || (schoolClass != null
                    && schoolClass.getClassName().toLowerCase().contains(key));
    }
}
