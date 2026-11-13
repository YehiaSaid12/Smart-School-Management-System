package netbeans_school;

import java.util.ArrayList;
// class made by yehia, changes not listed
public class AcademicRecord {
    private final Student student;
    private final ArrayList<Grade> grades;
    private final ArrayList<AttendanceRecord> attendanceRecords;

    public AcademicRecord(
            Student student,
            ArrayList<Grade> grades,
            ArrayList<AttendanceRecord> attendanceRecords
    ) {
        if (student == null) {
            throw new IllegalArgumentException("Student is required.");
        }

        this.student = student;

        if (grades == null) {
            this.grades = new ArrayList<Grade>();
        } else {
            this.grades = new ArrayList<Grade>(grades);
        }

        if (attendanceRecords == null) {
            this.attendanceRecords = new ArrayList<AttendanceRecord>();
        } else {
            this.attendanceRecords = new ArrayList<AttendanceRecord>(attendanceRecords);
        }
    }

    public Student getStudent() {
        return student;
    }

    public ArrayList<Grade> getGrades() {
        return new ArrayList<Grade>(grades);
    }

    public ArrayList<AttendanceRecord> getAttendanceRecords() {
        return new ArrayList<AttendanceRecord>(attendanceRecords);
    }

    public double calculateOverallAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (Grade grade : grades) {
            total += grade.getPercentage();
        }

        return total / grades.size();
    }

    public double calculateSubjectAverage(Subject subject) {
        double total = 0.0;
        int count = 0;

        for (Grade grade : grades) {
            if (grade.getSubject().equals(subject)) {
                total += grade.getPercentage();
                count++;
            }
        }

        if (count == 0) {
            return 0.0;
        }

        return total / count;
    }

    public Grade getHighestGrade() {
        if (grades.isEmpty()) {
            return null;
        }

        Grade highest = grades.get(0);

        for (Grade grade : grades) {
            if (grade.getPercentage() > highest.getPercentage()) {
                highest = grade;
            }
        }

        return highest;
    }

    public Grade getLowestGrade() {
        if (grades.isEmpty()) {
            return null;
        }

        Grade lowest = grades.get(0);

        for (Grade grade : grades) {
            if (grade.getPercentage() < lowest.getPercentage()) {
                lowest = grade;
            }
        }

        return lowest;
    }

    public int countPresent() {
        return countStatus(AttendanceRecord.Status.PRESENT);
    }

    public int countAbsent() {
        return countStatus(AttendanceRecord.Status.ABSENT);
    }

    public int countLate() {
        return countStatus(AttendanceRecord.Status.LATE);
    }

    public int countExcused() {
        return countStatus(AttendanceRecord.Status.EXCUSED);
    }

    private int countStatus(AttendanceRecord.Status status) {
        int count = 0;

        for (AttendanceRecord record : attendanceRecords) {
            if (record.getStatus() == status) {
                count++;
            }
        }

        return count;
    }

    public double calculateAttendancePercentage() {
        if (attendanceRecords.isEmpty()) {
            return 0.0;
        }

        int presentOrLate = 0;

        for (AttendanceRecord record : attendanceRecords) {
            if (record.getStatus() == AttendanceRecord.Status.PRESENT
                    || record.getStatus() == AttendanceRecord.Status.LATE) {
                presentOrLate++;
            }
        }

        return presentOrLate * 100.0 / attendanceRecords.size();
    }

    public String getAcademicStatus() {
        double average = calculateOverallAverage();

        if (average >= 90) {
            return "EXCELLENT";
        }
        if (average >= 80) {
            return "VERY GOOD";
        }
        if (average >= 70) {
            return "GOOD";
        }
        if (average >= 50) {
            return "PASS";
        }

        return "AT RISK";
    }
}
