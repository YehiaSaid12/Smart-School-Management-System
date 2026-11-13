package netbeans_school;

import java.util.ArrayList;
import java.util.Scanner;
// class made by yehia, changes not listed
public class NetBeans_School {
    private final Scanner scanner = new Scanner(System.in);

    private final StudentService studentService = new StudentService();
    private final TeacherService teacherService = new TeacherService();
    private final ClassService classService = new ClassService();
    private final GradeService gradeService = new GradeService();
    private final AttendanceService attendanceService = new AttendanceService();
    private final AuthenticationService authenticationService = new AuthenticationService();

    private final ReportGenerator reportGenerator = new ReportGenerator();
    private final SchoolHierarchy hierarchy = new SchoolHierarchy("Smart School");

    public static void main(String[] args) {
        new NetBeans_School().run();
    }

    private void run() {
        seedSampleData();

        System.out.println("================================");
        System.out.println(" SMART SCHOOL MANAGEMENT SYSTEM ");
        System.out.println("================================");

        if (!loginLoop()) {
            System.out.println("Goodbye.");
            return;
        }

        menuLoop();
    }

    private boolean loginLoop() {
        while (true) {
            System.out.println("\nDemo accounts:");
            System.out.println("admin / admin123");
            System.out.println("teacher / teach123");
            System.out.println("student / stud123");
            System.out.println("Type EXIT as username to quit.");

            String username = readLine("Username: ");

            if (username.equalsIgnoreCase("EXIT")) {
                return false;
            }

            String password = readLine("Password: ");

            if (authenticationService.login(username, password)) {
                System.out.println(
                        "\nWelcome, "
                                + authenticationService.getCurrentUser().getName()
                                + "!"
                );
                return true;
            }

            System.out.println("Invalid username or password.");
        }
    }

    private void menuLoop() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = readLine("Choose an option: ");

            try {
                switch (choice) {
                    case "1":
                        listStudents();
                        break;
                    case "2":
                        addStudent();
                        break;
                    case "3":
                        listTeachers();
                        break;
                    case "4":
                        addTeacher();
                        break;
                    case "5":
                        listClasses();
                        break;
                    case "6":
                        assignStudentToClass();
                        break;
                    case "7":
                        addGrade();
                        break;
                    case "8":
                        markAttendance();
                        break;
                    case "9":
                        generateStudentReport();
                        break;
                    case "10":
                        System.out.println("\n" + hierarchy.displayHierarchy());
                        break;
                    case "11":
                        searchStudents();
                        break;
                    case "0":
                        authenticationService.logout();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }

        System.out.println("Logged out. Goodbye.");
    }

    private void printMenu() {
        Person user = authenticationService.getCurrentUser();

        System.out.println("\n--------------------------------");
        System.out.println(user.getName() + " | " + user.getRole());
        System.out.println("--------------------------------");
        System.out.println("1. List students");
        System.out.println("2. Add student");
        System.out.println("3. List teachers");
        System.out.println("4. Add teacher");
        System.out.println("5. List classes");
        System.out.println("6. Assign student to class");
        System.out.println("7. Add grade");
        System.out.println("8. Mark attendance");
        System.out.println("9. Generate student report");
        System.out.println("10. Display school hierarchy");
        System.out.println("11. Search students");
        System.out.println("0. Logout");
    }

    private void listStudents() {
        System.out.println("\nSTUDENTS");

        for (Student student : studentService.getAllStudents()) {
            String className = "Unassigned";
            if (student.getSchoolClass() != null) {
                className = student.getSchoolClass().getClassName();
            }

            System.out.printf(
                    "#%d | %-20s | %-10s | %s%n",
                    student.getId(),
                    student.getName(),
                    student.getGradeLevel(),
                    className
            );
        }
    }

    private void addStudent() {
        requireAdmin();

        int id = readInt("Student ID: ");
        String name = readLine("Name: ");
        String email = readLine("Email: ");
        String phone = readLine("Phone: ");
        String gradeLevel = readLine("Grade level: ");
        String dateOfBirth = readLine("Date of birth (YYYY-MM-DD): ");

        Student student = new Student(
                id,
                name,
                email,
                phone,
                gradeLevel,
                dateOfBirth
        );

        studentService.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private void listTeachers() {
        System.out.println("\nTEACHERS");

        for (Teacher teacher : teacherService.getAllTeachers()) {
            System.out.printf(
                    "#%d | %-20s | %s%n",
                    teacher.getId(),
                    teacher.getName(),
                    teacher.getSpecialization()
            );
        }
    }

    private void addTeacher() {
        requireAdmin();

        int id = readInt("Teacher ID: ");
        String name = readLine("Name: ");
        String email = readLine("Email: ");
        String phone = readLine("Phone: ");
        String specialization = readLine("Specialization: ");

        Teacher teacher = new Teacher(
                id,
                name,
                email,
                phone,
                specialization
        );

        teacherService.addTeacher(teacher);
        System.out.println("Teacher added successfully.");
    }

    private void listClasses() {
        System.out.println("\nCLASSES");

        for (SchoolClass schoolClass : classService.getAllClasses()) {
            String teacherName = "Unassigned";
            if (schoolClass.getClassTeacher() != null) {
                teacherName = schoolClass.getClassTeacher().getName();
            }

            System.out.printf(
                    "%s | %d/%d students | Teacher: %s%n",
                    schoolClass.getClassName(),
                    schoolClass.getStudentCount(),
                    schoolClass.getCapacity(),
                    teacherName
            );
        }
    }

    private void assignStudentToClass() {
        requireAdminOrTeacher();

        int studentId = readInt("Student ID: ");
        String className = readLine("Class name: ");

        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        SchoolClass schoolClass = classService.findClass(className);
        if (schoolClass == null) {
            throw new IllegalArgumentException("Class not found.");
        }

        classService.assignStudent(student, schoolClass);

        System.out.println(
                student.getName()
                        + " assigned to "
                        + schoolClass.getClassName()
                        + "."
        );
    }

    private void addGrade() {
        requireAdminOrTeacher();

        int studentId = readInt("Student ID: ");
        String subjectCode = readLine("Subject code: ");
        String assessment = readLine("Assessment name: ");
        double score = readDouble("Score: ");
        double maximum = readDouble("Maximum score: ");
        String date = readLine("Date (YYYY-MM-DD): ");

        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        Subject subject = classService.findSubject(subjectCode);
        if (subject == null) {
            throw new IllegalArgumentException("Subject not found.");
        }

        Grade grade = new Grade(
                student,
                subject,
                assessment,
                score,
                maximum,
                date
        );

        gradeService.addGrade(grade);

        System.out.printf(
                "Grade saved: %.1f%%%n",
                grade.getPercentage()
        );
    }

    private void markAttendance() {
        requireAdminOrTeacher();

        int studentId = readInt("Student ID: ");
        Student student = studentService.findStudentById(studentId);

        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        String date = readLine("Date (YYYY-MM-DD): ");

        System.out.println("1. PRESENT");
        System.out.println("2. ABSENT");
        System.out.println("3. LATE");
        System.out.println("4. EXCUSED");

        int option = readInt("Status: ");
        AttendanceRecord.Status status;

        switch (option) {
            case 1:
                status = AttendanceRecord.Status.PRESENT;
                break;
            case 2:
                status = AttendanceRecord.Status.ABSENT;
                break;
            case 3:
                status = AttendanceRecord.Status.LATE;
                break;
            case 4:
                status = AttendanceRecord.Status.EXCUSED;
                break;
            default:
                throw new IllegalArgumentException("Invalid status.");
        }

        attendanceService.markAttendance(
                new AttendanceRecord(student, date, status)
        );

        System.out.println("Attendance saved.");
    }

    private void generateStudentReport() {
        Student student;

        if (authenticationService.isStudent()) {
            student = (Student) authenticationService.getCurrentUser();
        } else {
            int studentId = readInt("Student ID: ");
            student = studentService.findStudentById(studentId);

            if (student == null) {
                throw new IllegalArgumentException("Student not found.");
            }
        }

        AcademicRecord record = new AcademicRecord(
                student,
                gradeService.getGradesForStudent(student),
                attendanceService.getAttendanceForStudent(student)
        );

        System.out.println(reportGenerator.generateTextReport(record));
    }

    private void searchStudents() {
        String keyword = readLine("Search keyword: ");

        ArrayList<Student> results = studentService.search(keyword);

        if (results.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : results) {
            System.out.println(student.getDisplayInfo());
        }
    }

    private void requireAdmin() {
        if (!authenticationService.isAdmin()) {
            throw new SecurityException("Admin access required.");
        }
    }

    private void requireAdminOrTeacher() {
        if (!authenticationService.isAdmin() && !authenticationService.isTeacher()) {
            throw new SecurityException("Admin or teacher access required.");
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readLine(prompt));
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private void seedSampleData() {
        Subject english = new Subject("ENG10", "English", 5);
        Subject math = new Subject("MAT10", "Mathematics", 5);
        Subject science = new Subject("SCI10", "Science", 4);

        classService.addSubject(english);
        classService.addSubject(math);
        classService.addSubject(science);

        Student ahmed = new Student(
                101,
                "Ahmed Hassan",
                "ahmed@student.demo",
                "01000000001",
                "Grade 10",
                "2011-03-14"
        );

        Student sara = new Student(
                102,
                "Sara Omar",
                "sara@student.demo",
                "01000000002",
                "Grade 10",
                "2011-07-22"
        );

        Teacher teacher = new Teacher(
                201,
                "Sarah Ali",
                "sarah@teacher.demo",
                "01000000003",
                "English"
        );

        teacher.addSubject(english);

        Admin admin = new Admin(
                1,
                "System Admin",
                "admin@school.demo",
                "01000000000",
                "Academic Affairs"
        );

        studentService.addStudent(ahmed);
        studentService.addStudent(sara);
        teacherService.addTeacher(teacher);

        SchoolClass grade10A = new SchoolClass("Grade 10A", 30);

        classService.addClass(grade10A);
        classService.assignTeacher(teacher, grade10A);
        classService.assignSubject(english, grade10A);
        classService.assignSubject(math, grade10A);
        classService.assignSubject(science, grade10A);
        classService.assignStudent(ahmed, grade10A);
        classService.assignStudent(sara, grade10A);

        gradeService.addGrade(
                new Grade(
                        ahmed,
                        english,
                        "Midterm",
                        44,
                        50,
                        "2026-08-20"
                )
        );

        gradeService.addGrade(
                new Grade(
                        ahmed,
                        math,
                        "Midterm",
                        46,
                        50,
                        "2026-08-21"
                )
        );

        attendanceService.markAttendance(
                new AttendanceRecord(
                        ahmed,
                        "2026-09-07",
                        AttendanceRecord.Status.PRESENT
                )
        );

        attendanceService.markAttendance(
                new AttendanceRecord(
                        ahmed,
                        "2026-09-08",
                        AttendanceRecord.Status.LATE
                )
        );

        authenticationService.addAccount(
                new UserAccount("admin", "admin123", admin)
        );

        authenticationService.addAccount(
                new UserAccount("teacher", "teach123", teacher)
        );

        authenticationService.addAccount(
                new UserAccount("student", "stud123", ahmed)
        );

        hierarchy.addBranch("Smart School", "Primary");
        hierarchy.addBranch("Smart School", "Secondary");
        hierarchy.addBranch("Primary", "Grade 5");
        hierarchy.addBranch("Primary", "Grade 6");
        hierarchy.addBranch("Secondary", "Grade 9");
        hierarchy.addBranch("Secondary", "Grade 10");
        hierarchy.addBranch("Grade 10", "Grade 10A");
    }
}
