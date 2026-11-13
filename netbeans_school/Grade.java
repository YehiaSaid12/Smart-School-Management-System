package netbeans_school;
/* CHANGES
--> added datafields maximumScore and date
--> renamed datafield assesment to assessmentName
--> various changes and improvements in almost all methods
*/


public class Grade {
    private final Student student;
    private final Subject subject;
    private final String assessmentName;
    private double score;
    private final double maximumScore;
    private final String date;

    public Grade(
            Student student,
            Subject subject,
            String assessmentName,
            double score,
            double maximumScore,
            String date
    ) {
        if (student == null || subject == null) {
            throw new IllegalArgumentException("Student and subject are required.");
        }
        if (assessmentName == null || assessmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Assessment name is required.");
        }
        if (maximumScore <= 0) {
            throw new IllegalArgumentException("Maximum score must be positive.");
        }

        this.student = student;
        this.subject = subject;
        this.assessmentName = assessmentName.trim();
        this.maximumScore = maximumScore;
        this.date = date == null ? "" : date.trim();

        setScore(score);
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public double getScore() {
        return score;
    }

    public double getMaximumScore() {
        return maximumScore;
    }

    public String getDate() {
        return date;
    }

    public void setScore(double score) {
        if (score < 0 || score > maximumScore) {
            throw new IllegalArgumentException(
                    "Score must be between 0 and " + maximumScore + "."
            );
        }
        this.score = score;
    }

    public double getPercentage() {
        return score / maximumScore * 100.0;
    }

    public boolean isPassing() {
        return getPercentage() >= 50.0;
    }

    
    public String toString() {
        return String.format(
                "%s | %s | %s | %.1f/%.1f (%.1f%%)",
                student.getName(),
                subject.getName(),
                assessmentName,
                score,
                maximumScore,
                getPercentage()
        );
    }
}
