import java.util.*;

class Person {
    protected String name;
    protected String surname;
    protected int pesel;

    public Person(String name, String surname, int pesel) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("PESEL: " + pesel);
    }
}

class Student extends Person {
    private final int studentId;
    private final List<String> courses;
    private int tuitionBalance;

    public Student(String name, String surname, int pesel, int studentId) {
        super(name, surname, pesel);
        this.studentId = studentId;
        this.courses = new ArrayList<>();
        this.tuitionBalance = 0;
    }

    public void enroll() {
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Enter course to enroll (or 'Q' to quit): ");
            String course = in.nextLine();
            if (!course.equals("Q")) {
                courses.add(course);
                tuitionBalance += 100; // Each course costs 100 PLN
            } else {
                break;
            }
        } while (true);
        System.out.println("Enrolled in the following courses:");
        for (String course : courses) {
            System.out.println(course);
        }
        System.out.println("Tuition balance: " + tuitionBalance + " PLN");
    }

    public void viewBalance() {
        System.out.println("Your balance is: " + tuitionBalance + " PLN");
    }

    public void payTuition() {
        System.out.print("Enter your payment: ");
        Scanner in = new Scanner(System.in);
        int payment = in.nextInt();
        tuitionBalance -= payment;
        System.out.println("Thank you for your payment: " + payment + " PLN");
        viewBalance();
    }

    public void displayCourses() {
        System.out.println("Enrolled courses:");
        for (String course : courses) {
            System.out.println(course);
        }
    }

    @Override
    public void displayInfo() {
        if (studentId != 0) {
            System.out.println("Student");
        }
        super.displayInfo();
        System.out.println("Student ID: " + studentId);
    }
}

class Teacher extends Person {
    private final int studentId;

    public Teacher(String name, String surname, int pesel, int studentId) {
        super(name, surname, pesel);
        this.studentId = studentId;
    }
    @Override
    public void displayInfo() {
        if (studentId == 0) {
            System.out.println("Teacher");
        }
        super.displayInfo();
    }
}

public class SchoolManagementSystem {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("School Management System");
            System.out.println(" 1. Add Person");
            System.out.println(" 2. Find Person by PESEL");
            System.out.println(" 3. Remove Person by PESEL");
            System.out.println(" 4. Display All People");
            System.out.println(" 5. Enroll in Courses (Student only)");
            System.out.println(" 6. View Balance (Student only)");
            System.out.println(" 7. Pay Tuition (Student only)");
            System.out.println(" 8. Display Student's Courses");
            System.out.println(" 0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            System.out.println("---------------------------");

            switch (choice) {
                case 1:
                    // Add Person
                    System.out.print("Enter name: ");
                    String name = input.next();
                    System.out.print("Enter surname: ");
                    String surname = input.next();
                    System.out.print("Enter PESEL: ");
                    int pesel = input.nextInt();
                    int studentId;

                    do {
                        System.out.print("Enter student ID (or 0 if not a student): ");
                        studentId = input.nextInt();
                        if (studentId != 0 && String.valueOf(studentId).length() != 5) {
                            System.out.println("Error: Student ID must have 5 digits.");
                        } else {
                            break;
                        }
                    } while (true);

                    if (studentId != 0) {
                        // Student
                        Student student = new Student(name, surname, pesel, studentId);
                        people.add(student);
                        System.out.println("Student added successfully.");
                    } else {
                        // Teacher
                        Teacher teacher = new Teacher(name, surname, pesel, studentId);
                        people.add(teacher);
                        System.out.println("Teacher added successfully.");
                    }
                    System.out.println("---------------------------");
                    break;
                case 2:
                    // Find Person by PESEL
                    System.out.print("Enter PESEL to find: ");
                    int peselToFind = input.nextInt();
                    boolean found = false;
                    for (Person person : people) {
                        if (person.pesel == peselToFind) {
                            person.displayInfo();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Person with the provided PESEL not found.");
                    }
                    System.out.println("---------------------------");
                    break;
                case 3:
                    // Remove Person by PESEL
                    System.out.print("Enter PESEL to remove: ");
                    int peselToRemove = input.nextInt();
                    found = false;
                    for (Person person : people) {
                        if (person.pesel == peselToRemove) {
                            people.remove(person);
                            found = true;
                            System.out.println("Person removed successfully.");
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Person with the provided PESEL not found.");
                    }
                    System.out.println("---------------------------");
                    break;
                case 4:
                    // Display All People
                    for (Person person : people) {
                        person.displayInfo();
                        System.out.println("---------------------------");
                    }
                    break;
                case 5:
                    // Enroll in Courses
                    System.out.print("Enter student's PESEL: ");
                    int studentPesel = input.nextInt();
                    found = false;
                    for (Person person : people) {
                        if (person instanceof Student && person.pesel == studentPesel) {
                            ((Student) person).enroll();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student with the provided PESEL not found.");
                    }
                    System.out.println("---------------------------");
                    break;
                case 6:
                    // View Balance
                    System.out.print("Enter student's PESEL: ");
                    studentPesel = input.nextInt();
                    found = false;
                    for (Person person : people) {
                        if (person instanceof Student && person.pesel == studentPesel) {
                            ((Student) person).viewBalance();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student with the provided PESEL not found.");
                    }
                    System.out.println("---------------------------");
                    break;
                case 7:
                    // Pay Tuition
                    System.out.print("Enter student's PESEL: ");
                    studentPesel = input.nextInt();
                    found = false;
                    for (Person person : people) {
                        if (person instanceof Student && person.pesel == studentPesel) {
                            ((Student) person).payTuition();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student with the provided PESEL not found.");
                    }
                    System.out.println("---------------------------");
                    break;
                case 8:
                    // Display Student's Courses
                    System.out.print("Enter student's PESEL: ");
                    studentPesel = input.nextInt();
                    found = false;
                    for (Person person : people) {
                        if (person instanceof Student && person.pesel == studentPesel) {
                            ((Student) person).displayCourses();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student with the provided PESEL not found.");
                    }
                    System.out.println("---------------------------");
                    break;
                case 0:
                    // Exit
                    System.out.println("Exiting School Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("---------------------------");
                    break;
            }
        } while (choice != 0);

        input.close();
    }
}