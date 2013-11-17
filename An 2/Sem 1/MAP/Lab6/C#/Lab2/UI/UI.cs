using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab2.Lab2_Controller;
using Lab2.Lab2_Model;
using Lab2.Lab2_Utils;

namespace Lab2
{
	namespace Lab2_UI {
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
					+ "(5) Number of students greater than a given student\n"
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
					} else if ("5".Equals(userInput)) {
						this.numberOfStudentGreaterThan();
					}
	            }
	        }
	    
	        private void addStudent() {
				int id, grade, grade2, grade3, studentType;
				String name, supervisor, thesis;
				ArrayList errorList;
	                
	            try{
	                System.Console.Write("Student ID: ");
	                id = Convert.ToInt16(System.Console.ReadLine());
	                System.Console.Write("Student Name: ");
	                name = System.Console.ReadLine();
	                System.Console.Write("Student Grade: ");
	                grade = Convert.ToInt16(System.Console.ReadLine());

					Console.Write("Student Type:\n(1)Regular\n(2)Graduate\n(3)Undergraduate\n(4)PhD");
					studentType = Convert.ToInt16(System.Console.ReadLine());

					switch (studentType) {
					case 1:
						errorList = this.controller.addStudent(id, name, grade);
						break;
					case 2:
						System.Console.Write("Grade2: ");
						grade2 = Convert.ToInt16(System.Console.ReadLine());
						System.Console.Write("Grade3: ");
						grade3 = Convert.ToInt16(System.Console.ReadLine());
						System.Console.Write("Supervisor: ");
						supervisor = System.Console.ReadLine();
						errorList = this.controller.addStudent(id, name, grade, grade2, grade3, supervisor);
						break;
					case 3:
						System.Console.Write("Grade2: ");
						grade2 = Convert.ToInt16(System.Console.ReadLine());
						errorList = this.controller.addStudent(id, name, grade, grade2);
						break;
					case 4:
						System.Console.Write("Grade2: ");
						grade2 = Convert.ToInt16(System.Console.ReadLine());
						System.Console.Write("Supervisor: ");
						supervisor = System.Console.ReadLine();
						System.Console.Write("Thesis: ");
						thesis = System.Console.ReadLine();

						errorList = this.controller.addStudent(id, name, grade, grade2, supervisor, thesis);
						break;
					default:
						System.Console.Write("Invalid choice!");
						return;
					}
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
				try {
	            	this.controller.removeStudentsUntilMaxGrade();
	            	System.Console.WriteLine(kStudentsRemovedMessage);
				}
				catch (StackException e) {
					System.Console.WriteLine (e.exceptionMessage);
				}
	        }
	    
	        private void allStudents() {
				ArrayList allStudents = this.controller.allStudents();
				foreach (String student in allStudents) {
					System.Console.WriteLine(student);
				}
	        }
	    
	        private void numberOfStudents() {
	            System.Console.Write("Total number of students: ");
	            System.Console.Write(this.controller.numberOfStudents());
	            System.Console.WriteLine();
	        }

		private void numberOfStudentGreaterThan() {
			System.Console.Write("Student id: ");
			int id = Convert.ToInt16 (System.Console.ReadLine ());
			int no = this.controller.numberOfStudentGreaterThan(id);
			System.Console.WriteLine("Number of students greater than {0}: {1}\n", id, no);
		}
	    }
	}
}
