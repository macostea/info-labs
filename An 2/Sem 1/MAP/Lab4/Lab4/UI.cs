using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2
{
    class UI
    {
        private Controller controller;

        private const String kGreeting = "Welcome!";
        private const String kExitString = "x";
        private const String kMenuDelimiter = "----------\n";
        private const String kMenuExit = "(x) Exit";
        private const String kMenu = "(1) Add student\n"
                + "(2) Remove students until 10-grade student is found\n"
                + "(3) All students\n"
                + "(4) Number of students\n"
                + kMenuDelimiter
                + kMenuExit;
        private const String kStudentAddedMessage = "Student Added!";
        private const String kStudentsRemovedMessage = "Students Removed!";
    
        public UI(Controller controller) {
            this.controller = controller;
        }
    
        public void showMenu() {
            System.Console.WriteLine(kGreeting);
            String userInput = "";
            while (!kExitString.Equals(userInput)) {
                System.Console.WriteLine(kMenu);
                userInput = System.Console.ReadLine();
                if ("1".Equals(userInput)) {
                    this.addStudent();
                } else if ("2".Equals(userInput)) {
                    this.removeStudents();
                } else if ("3".Equals(userInput)) {
                    this.allStudents();
                } else if ("4".Equals(userInput)) {
                    this.numberOfStudents();
                }
            }
        }
    
        private void addStudent() {
            int id, grade;
            String name;
                
            try{
                System.Console.Write("Student ID: ");
                id = Convert.ToInt16(System.Console.ReadLine());
                System.Console.Write("Student Name: ");
                name = System.Console.ReadLine();
                System.Console.Write("Student Grade: ");
                grade = Convert.ToInt16(System.Console.ReadLine());

                ArrayList errorList = this.controller.addStudent(id, name, grade);
                if (errorList.Count != 0) {
                    foreach (String error in errorList) {
                        System.Console.WriteLine(error);
                    }
                } else {
                    System.Console.WriteLine(kStudentAddedMessage);
                }
            } catch (Exception) {
                System.Console.WriteLine("Invalid input!");
            }
        
        }
    
        private void removeStudents() {
            this.controller.removeStudentsUntilMaxGrade();
            System.Console.WriteLine(kStudentsRemovedMessage);
        }
    
        private void allStudents() {
            Stack allStudents = this.controller.allStudents();
            while (!allStudents.isEmpty()) {
                Student currStudent = allStudents.pop();
                if (currStudent != null)
                {
                    System.Console.Write(currStudent.id);
                    System.Console.Write(" | ");
                    System.Console.Write(currStudent.name);
                    System.Console.Write(" | ");
                    System.Console.Write(currStudent.grade);
                    System.Console.WriteLine();
                }
            }
        }
    
        private void numberOfStudents() {
            System.Console.Write("Total number of students: ");
            System.Console.Write(this.controller.numberOfStudents());
            System.Console.WriteLine();
        }
    }
}
