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
			private Repository<Student> repo;

			/**
		     *
		     * Gets a String representation of all the elements from a given Stack
		     * @param stack The stack to process
		     * @return An ArrayList<String> of all the String representations of the elements from the given Stack
		     */
			public static ArrayList elementsFromStack<T>(Stack<T> stack) {
				Stack<T> copy = stack.copy();
				ArrayList strings = new ArrayList();

				while (!copy.isEmpty()) {
					try {
						T element = copy.pop();
						strings.Add(element.ToString());
					} catch (StackException e) {
						Console.WriteLine(e.Message);
					}
				}
				return strings;
			}
			/**
		     *
		     * Moves all elements from a stack to another stack
		     * @param source The stack to move the elements from
		     * @param destination The stack to move the elements to
		     */
			public static void moveElements<W,T>(Stack<W> source, Stack<T> destination) where W:T {
				Stack<T> temp = new Stack<T>();
				while (!source.isEmpty()) {
					try {
						temp.push(source.pop());
					} catch (StackException e) {
						System.Console.WriteLine(e.Message);
					}
				}
				while (!temp.isEmpty()) {
					try {
						destination.push(temp.pop());
					} catch (StackException e) {
						System.Console.WriteLine(e.Message);
					}
				}
			}

	        /**
	         * 
	         * Constructor.
	         * 
	         * @param repo The repository to be used. Cannot be null.
	         */
			public Controller(Repository<Student> repo)
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
					repo.addElement(student);
				}
				return errorList;
			}

			public ArrayList addStudent(int id, String name, int grade, int grade2){
				Student student = new UndergraduateStudent(id, name, grade, grade2);
				Validator validator = new Validator(this.repo);
				ArrayList errorList = validator.validateStudent(student);

				if (errorList.Count == 0){
					repo.addElement(student);
				}
				return errorList;
			}

			public ArrayList addStudent(int id, String name, int grade, int grade2, int grade3, String supervisor){
				Student student = new GraduateStudent(id, name, grade, supervisor, grade2, grade3);
				Validator validator = new Validator(this.repo);
				ArrayList errorList = validator.validateStudent(student);

				if (errorList.Count == 0){
					repo.addElement(student);
				}
				return errorList;
			}

			public ArrayList addStudent(int id, String name, int grade, int grade2, String supervisor, String thesis){
				Student student = new PhDStudent(id, name, grade, supervisor, thesis, grade2);
				Validator validator = new Validator(this.repo);
				ArrayList errorList = validator.validateStudent(student);

				if (errorList.Count == 0){
					repo.addElement(student);
				}
				return errorList;
			}

	        /**
	         * 
	         * Removes students from the repository until a student with grade == 0 is found.
	         */
	        public void removeStudentsUntilMaxGrade()
	        {
				Student currentStudent = this.repo.getTopElement();
	            while (currentStudent.grade != 10)
	            {
					this.repo.removeElement(currentStudent);
					currentStudent = this.repo.getTopElement();
	            }
	        }

	        /**
	         * 
	         * Retrieves the entire student list from the repository.
	         * 
	         * @return A stack of students from the repository.
	         */
			public ArrayList allStudents() {
				return Controller.elementsFromStack(this.repo.allElements());
			}

	        /**
	         * 
	         * Gets the number of students from the repository.
	         * 
	         * @return Total number of students in the repository.
	         */
	        public int numberOfStudents()
	        {
				return this.repo.numberOfElements();
	        }

			/**
		     *
		     * Computes the total number of Students greater than a given student
		     * @param id The student to compare to.
		     * @return The number of students greater than the given student.
		     */
			public int numberOfStudentsGreaterThan(Student student) {
				Stack<Student> allStudents = this.repo.allElements();
				int no = 0;
				while (!allStudents.isEmpty()) {
					try {
						Comparable<Student> comparableStudent = allStudents.pop();
						if (comparableStudent.isGreaterThan(student)) {
							no++;
						}
					} catch (StackException e) {
						System.Console.WriteLine(e.Message);
					}
				}
				return no;
			}
	    }
	}
}
