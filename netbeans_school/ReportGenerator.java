package netbeans_school;

import java.util.ArrayList;
// class made by yehia, changes not listed
public class ReportGenerator {

    public String generateTextReport(AcademicRecord record) {
        StringBuilder output = new StringBuilder();

        output.append("\nSTUDENT PERFORMANCE REPORT\n");
        output.append("==========================\n\n");

        output.append("Student: ").append(record.getStudent().getName()).append('\n');
        output.append("ID: ").append(record.getStudent().getId()).append('\n');
        output.append("Grade: ").append(record.getStudent().getGradeLevel()).append('\n');

        if (record.getStudent().getSchoolClass() == null) {
            output.append("Class: Unassigned\n\n");
        } else {
            output.append("Class: ")
                    .append(record.getStudent().getSchoolClass().getClassName())
                    .append("\n\n");
        }

        output.append("ACADEMIC PERFORMANCE\n");

        ArrayList<Subject> subjects = new ArrayList<Subject>();

        for (Grade grade : record.getGrades()) {
            Subject subject = grade.getSubject();
            if (!subjects.contains(subject)) {
                subjects.add(subject);
            }
        }

        if (subjects.isEmpty()) {
            output.append("No grades recorded.\n");
        } else {
            for (Subject subject : subjects) {
                output.append(String.format(
                        "%-20s %6.1f%%\n",
                        subject.getName(),
                        record.calculateSubjectAverage(subject)
                ));
            }
        }

        output.append(String.format(
                "\nOverall average: %.1f%%\n",
                record.calculateOverallAverage()
        ));

        output.append("Academic status: ")
                .append(record.getAcademicStatus())
                .append("\n\n");

        output.append("ATTENDANCE\n");
        output.append("Present: ").append(record.countPresent()).append('\n');
        output.append("Absent: ").append(record.countAbsent()).append('\n');
        output.append("Late: ").append(record.countLate()).append('\n');
        output.append("Excused: ").append(record.countExcused()).append('\n');

        output.append(String.format(
                "Attendance: %.1f%%\n",
                record.calculateAttendancePercentage()
        ));

        return output.toString();
    }
}
