public class StudentRecordSystem {
    public static void main(String[] args) {
        StudentRecordSystem student = new StudentRecordSystem();
        student.addStudentEnd(4, "Harman", 21, "A+");
        student.addStudentEnd(5, "Jeevan", 22, "B+");
        student.addStudentBeginning(2, "Dhruv", 21, "O+");
        student.addStudentAtPosition(3, 1, "Aaryan", 20, "O+");

        student.display();

        student.deleteStudent(4);
        student.display();

        student.searchStudent(5);

        student.updateGrade(1, "A+");

        student.display();

    }
    class Student{
        int rollNo;
        String name;
        int age;
        String grade;
        Student next;

        public Student(int rollNo,String name, int age, String grade) {
            this.rollNo = rollNo;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.next = null;
        }
    }

    public Student head = null;
    public Student tail = null;

    //To add Student at Ending
    public void addStudentEnd(int rollNo, String name, int age, String grade) {
        Student newStudent = new Student(rollNo, name, age, grade);

        if(head == null) {
            head = newStudent;
            tail = newStudent;
        } else {
            tail.next = newStudent;
            tail = newStudent;
        }
    }

    //To add Student at beginning
    public void addStudentBeginning(int rollNo, String name, int age, String grade) {
        Student newStudent = new Student(rollNo, name, age, grade);

        if(head == null) {
            head = newStudent;
            tail = newStudent;
        } else {
            newStudent.next = head;
            head = newStudent;
        }
    }

    //To add student at a specific position
    public void addStudentAtPosition(int position, int rollNo, String name, int age, String grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        if(position == 1){
            newStudent.next = head;
            head = newStudent;
        }
        Student prev = head;
        for(int i = 1; i < position - 1; i++) {
            prev = prev.next;
        }

        newStudent.next = prev.next;
        prev.next = newStudent;
    }

    //Method to display student details
    public void display() {
        Student current = head;

        if(head == null){
            System.out.println("List is empty");
            return;
        }
        System.out.println("Student Record: ");
        while(current != null) {
            System.out.print("Roll Number: " + current.rollNo);
            System.out.print(", Name: " + current.name);
            System.out.print(", Age: " + current.age);
            System.out.println(", Grade: " + current.grade);
            current = current.next;
        }
        System.out.println();
    }

    //Method to delete student's data
    public void deleteStudent(int rollNumber) {
        Student temp = head, prev = null;

        if(temp != null && temp.rollNo == rollNumber) {
            head = temp.next;
            return;
        }

        while(temp != null && temp.rollNo != rollNumber) {
            prev = temp;
            temp = temp.next;
        }

        if(temp == null)
            return;

        prev.next = temp.next;
    }

    //Method to search student records
    public void searchStudent(int rollNumber) {
        Student current = head;

        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.print("Student Found: ");
        while (current != null) {
            if (current.rollNo == rollNumber) {
                System.out.print("Roll Number: " + current.rollNo);
                System.out.print(", Name: " + current.name);
                System.out.print(", Age: " + current.age);
                System.out.println(", Grade: " + current.grade);
            }
            current = current.next;
        }
        System.out.println();
    }

    //Method to update student's grade
    public void updateGrade(int rollNumber, String newGrade) {
        Student current = head;
        boolean found = false;

        while (current != null) {
            if (current.rollNo == rollNumber) {
                current.grade = newGrade;
                found = true;
                break;
            }
            current = current.next;
        }

        if (found)
            System.out.println("Grade updated for Roll No. " + rollNumber);
        else
            System.out.println("Student with Roll No. " + rollNumber + " not found");
    }
}

//output
// Student Record:
//Roll Number: 2, Name: Dhruv, Age: 21, Grade: O+
//Roll Number: 4, Name: Harman, Age: 21, Grade: A+
//Roll Number: 1, Name: Aaryan, Age: 20, Grade: O+
//Roll Number: 5, Name: Jeevan, Age: 22, Grade: B+
//
//Student Record:
//Roll Number: 2, Name: Dhruv, Age: 21, Grade: O+
//Roll Number: 1, Name: Aaryan, Age: 20, Grade: O+
//Roll Number: 5, Name: Jeevan, Age: 22, Grade: B+
//
//Student Found: Roll Number: 5, Name: Jeevan, Age: 22, Grade: B+
//
//Grade updated for Roll No. 1
//Student Record:
//Roll Number: 2, Name: Dhruv, Age: 21, Grade: O+
//Roll Number: 1, Name: Aaryan, Age: 20, Grade: A+
//Roll Number: 5, Name: Jeevan, Age: 22, Grade: B+