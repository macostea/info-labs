using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab2.Lab2_Controller;
using Lab2.Lab2_Model;
using Lab2.Lab2_Repository;
using Lab2.Lab2_Utils;

namespace Lab2
{
	namespace Lab2_Controller {
	    class Controller
	    {
	        private Repository repo;

			/**
		     *
		     * Gets a String representation of all the students from a given Stack
		     * @param stack The stack to process
		     * @return An ArrayList<String> of all the String representations of the elements from the given Stack
		     */
			public static ArrayList studentsFromStack(Stack<Student> stack) {
				Stack<Student> copy = stack.copy();
				ArrayList students = new ArrayList();

				while (!copy.isEmpty()) {
					try {
						Student student = copy.pop();
						String studentString;
						studentString = String.Format("{0}|{1}|{2}|", student.id, student.name, student.grade);
						if (student is GraduateStudent) {
							String concatString;
							GraduateStudent gradStud = (GraduateStudent)student;
							concatString = String.Format("{0}|{1}|{2}|", gradStud.grade2, gradStud.grade3, gradStud.supervisor);
							studentString += concatString;
						} else if (student is UndergraduateStudent) {
							String concatString;
							UndergraduateStudent gradStud = (UndergraduateStudent)student;
							concatString = String.Format("{0}|", gradStud.grade2);
							studentString += concatString;
						} else if (student is PhDStudent) {
							String concatString;
							PhDStudent phdStudent = (PhDStudent)student;
							concatString = String.Format("{0}|{1}|{2}|", phdStudent.grade2, phdStudent.supervisor, phdStudent.thesis);
							studentString += concatString;
						}
						students.Add(studentString);
					} catch (StackException e) {
						Console.WriteLine(e.Message);
					}
				}
				return students;
			}

	        /**
	         * 
	         * Constructor.
	         * 
	         * @param repo The repository to be used. Cannot be null.
	         */
	        public Controller(Repository repo)
	        {
	            this.repo = repo;
	        }

			/**
		     * 
		     * Controller method to add a new student to the repository.
		     * 
		     * @param id Student id. Must be a unique positive int.
		     * @param name Student name. Cannot be an empty string.
		     * @param grade Student grade. Must be an int between 1 and 10.
		     * @return A list of error messages from the Validator.
		     */
			public ArrayList addStudent(int id, String name, int grade){
				Student student = new Student(id, name, grade);
				Validator validator = new Validator(this.repo);
				ArrayList errorList = validator.validateStudent(student);

				if (errorList.Count == 0){
					repo.addStudent(student);
				}
				return errorList;
			}

			public ArrayList addStudent(int id, String name, int grade, int grade2){
				Student student = new UndergraduateStudent(id, name, grade, grade2);
				Validator validator = new Validator(this.repo);
				ArrayList errorList = validator.validateStudent(student);

				if (errorList.Count == 0){
					repo.addStudent(student);
				}
				return errorList;
			}

			public ArrayList addStudent(int id, String name, int grade, int grade2, int grade3, String supervisor){
				Student student = new GraduateStudent(id, name, grade, supervisor, grade2, grade3);
				Validator validator = new Validator(this.repo);
				ArrayList errorList = validator.validateStudent(student);

				if (errorList.Count == 0){
					repo.addStudent(student);
				}
				return errorList;
			}

			public ArrayList addStudent(int id, String name, int grade, int grade2, String supervisor, String thesis){
				Student student = new PhDStudent(id, name, grade, supervisor, thesis, grade2);
				Validator validator = new Validator(this.repo);
				ArrayList errorList = validator.validateStudent(student);

				if (errorList.Count == 0){
					repo.addStudent(student);
				}
				return errorList;
			}

	        /**
	         * 
	         * Removes students from the repository until a student with grade == 0 is found.
	         */
	        public void removeStudentsUntilMaxGrade()
	        {
	            Student currentStudent = this.repo.getTopStudent();
	            while (currentStudent.grade != 10)
	            {
	                this.repo.removeStudent(currentStudent);
	                currentStudent = this.repo.getTopStudent();
	            }
	        }

	        /**
	         * 
	         * Retrieves the entire student list from the repository.
	         * 
	         * @return A stack of students from the repository.
	         */
			public ArrayList allStudents() {
				return Controller.studentsFromStack(this.repo.allStudents());
			}

	        /**
	         * 
	         * Gets the number of students from the repository.
	         * 
	         * @return Total number of students in the repository.
	         */
	        public int numberOfStudents()
	        {
	            return this.repo.numberOfStudents();
	        }

			/**
		     *
		     * Computes the total number of Students greater than a given student
		     * @param id The id of the student to compare to.
		     * @return The number of students greater than the given student.
		     */
			public int numberOfStudentGreaterThan(int id) {
				Stack<Student> allStudents = this.repo.allStudents();
				Student student = null;
				int no = -1;
				while (!allStudents.isEmpty()) {
					try {
						student = allStudents.pop();
						if (student.id == id) {
							break;
						}
					} catch (StackException e) {
						System.Console.WriteLine(e.Message);
					}
				}
				if (student != null) {
					no = this.repo.numberOfStudentsGreaterThan(student);
				}
				return no;
			}
	    }
	}
}
