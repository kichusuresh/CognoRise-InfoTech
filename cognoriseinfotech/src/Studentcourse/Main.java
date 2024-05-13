package Studentcourse;
import java.util.ArrayList;
import java.util.List;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    // Getters and setters

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }
}

class Student {
    private int id;
    private String name;
    private List<String> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }
}

class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    // Other methods as needed
}

class StudentDatabase {
    private List<Student> students;

    public StudentDatabase() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    // Other methods as needed
}

public class Main {
    public static void main(String[] args) {
        // Create course database
        CourseDatabase courseDB = new CourseDatabase();
        courseDB.addCourse(new Course("CSE101", "Introduction to Computer Science", "Basic concepts of programming", 50, List.of("Mon", "Wed", "Fri")));
        courseDB.addCourse(new Course("MAT201", "Linear Algebra", "Basic linear algebra concepts", 40, List.of("Tue", "Thu")));

        // Create student database
        StudentDatabase studentDB = new StudentDatabase();
        studentDB.addStudent(new Student(1, "Alice"));
        studentDB.addStudent(new Student(2, "Bob"));

        // Course Listing
        System.out.println("Available Courses:");
        for (Course course : courseDB.getCourses()) {
            System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getDescription() + ") - Schedule: " + course.getSchedule() + " - Available slots: " + (course.getCapacity() - studentDB.getStudents().size()));
        }

        // Student Registration
        Student student = studentDB.getStudents().get(0);
        Course courseToRegister = courseDB.getCourses().get(0);
        if (courseToRegister.getCapacity() > studentDB.getStudents().size()) {
            student.getRegisteredCourses().add(courseToRegister.getCode());
            System.out.println(student.getName() + " registered for " + courseToRegister.getTitle());
        } else {
            System.out.println("Course is full, cannot register " + student.getName() + " for " + courseToRegister.getTitle());
        }

        // Course Removal
        Student studentToRemoveCourse = studentDB.getStudents().get(0);
        Course courseToRemove = courseDB.getCourses().get(0);
        studentToRemoveCourse.getRegisteredCourses().remove(courseToRemove.getCode());
        System.out.println(studentToRemoveCourse.getName() + " dropped " + courseToRemove.getTitle());
    }
}
