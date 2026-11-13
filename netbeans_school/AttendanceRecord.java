package netbeans_school;
/* CHANGES 
--> class now has an enum within it ( instead of it being a separate file )
--> improved exception handling
*/
public class AttendanceRecord {
    public enum Status {
        PRESENT,
        ABSENT,
        LATE,
        EXCUSED
    }/*An enum in Java is used when a variable should only be allowed to have one value from a fixed set of choices.
    ( you are allowed to create an enum inside a class )
    */

    private final Student student;
    private final String date;
    private Status status;

    public AttendanceRecord(Student student, String date, Status status) {
        if (student == null) {
            throw new IllegalArgumentException("Student is required.");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date is required.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status is required.");
        }

        this.student = student;
        this.date = date.trim();
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public String getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status is required.");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return student.getName() + " | " + date + " | " + status;
    }
}
