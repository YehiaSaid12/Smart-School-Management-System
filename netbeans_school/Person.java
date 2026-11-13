package netbeans_school;
/* CHANGES
--> added new datafield (variable) "phone" alongside(مع) its getters/setters
--> id datafield became final
--> toString method changed
--> added  if/throw block
--> setName/setEmail methods changed to become more strict (بقى ليها شروط معينا)
*/






// person is an abstract class,i dont want it instantiated (تاخد منه اوبجيكت) because its far too general
public abstract class Person {
    private final int id; // final because it cannot be changed no matter what
    private String name;
    private String email;
    private String phone;

    protected Person(int id, String name, String email, String phone) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        } /* exceptions are objects, this code is the exact same thing as whats written below
        
        IllegalArgumentException error = new IllegalArgumentException("Invalid age");
        throw error; */

        this.id = id;
        setName(name);
        setEmail(email);
        setPhone(phone);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) { // trim --> removes blank spaces from beginning and the end of a string
            throw new IllegalArgumentException("Name cannot be blank."); // isEmpty --> check whether a string has 0 characters
        }
        this.name = name.trim();
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Enter a valid email.");
        }
        this.email = email.trim();
    }

    public void setPhone(String phone) {

    if (phone == null) {
        this.phone = "";
    } else {
        this.phone = phone.trim();
    }
}

    public abstract String getRole();

    public String getDisplayInfo() {
        return getRole() + " #" + id + " - " + name + " <" + email + ">";
    }

    
}
