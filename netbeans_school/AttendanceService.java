package netbeans_school;

import java.util.ArrayList;
// class made by yehia, changes not listed
public class AttendanceService {
    private final ArrayList<AttendanceRecord> records = new ArrayList<AttendanceRecord>();

    public void markAttendance(AttendanceRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Attendance record is required.");
        }

        AttendanceRecord existing = findRecord(record.getStudent(), record.getDate());

        if (existing != null) {
            existing.setStatus(record.getStatus());
        } else {
            records.add(record);
        }
    }

    public AttendanceRecord findRecord(Student student, String date) {
        for (AttendanceRecord record : records) {
            if (record.getStudent() == student
                    && record.getDate().equals(date)) {
                return record;
            }
        }

        return null;
    }

    public ArrayList<AttendanceRecord> getAttendanceForStudent(Student student) {
        ArrayList<AttendanceRecord> results = new ArrayList<AttendanceRecord>();

        for (AttendanceRecord record : records) {
            if (record.getStudent() == student) {
                results.add(record);
            }
        }

        return results;
    }

    public ArrayList<AttendanceRecord> getAttendanceForDate(String date) {
        ArrayList<AttendanceRecord> results = new ArrayList<AttendanceRecord>();

        for (AttendanceRecord record : records) {
            if (record.getDate().equals(date)) {
                results.add(record);
            }
        }

        return results;
    }

    public ArrayList<AttendanceRecord> getAttendanceForClass(
            SchoolClass schoolClass,
            String date
    ) {
        ArrayList<AttendanceRecord> results = new ArrayList<AttendanceRecord>();

        for (AttendanceRecord record : records) {
            if (record.getDate().equals(date)
                    && schoolClass.containsStudent(record.getStudent())) {
                results.add(record);
            }
        }

        return results;
    }

    public double calculateAttendancePercentage(Student student) {
        ArrayList<AttendanceRecord> studentRecords = getAttendanceForStudent(student);

        if (studentRecords.isEmpty()) {
            return 0.0;
        }

        int presentOrLate = 0;

        for (AttendanceRecord record : studentRecords) {
            if (record.getStatus() == AttendanceRecord.Status.PRESENT
                    || record.getStatus() == AttendanceRecord.Status.LATE) {
                presentOrLate++;
            }
        }

        return presentOrLate * 100.0 / studentRecords.size();
    }
}
