package netbeans_school;
// no significant changes, same as previous classes;

public class Admin extends Person {
    private String department;

    public Admin(
            int id,
            String name,
            String email,
            String phone,
            String department
    ) {
        super(id, name, email, phone);
        setDepartment(department);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            this.department = "Administration";
        } else {
            this.department = department.trim();
        }
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }
}
