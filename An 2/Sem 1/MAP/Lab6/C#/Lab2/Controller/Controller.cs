using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab2.Lab2_Controller;
using Lab2.Lab2_Model;
using Lab2.Lab2_Repository;

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
			public static ArrayList<String> studentsFromStack(Stack<Student> stack) {
				Stack<Student> copy = stack.copy();
				ArrayList<String> students = new ArrayList<String>();

				while (!copy.isEmpty()) {
					try {
						Student student = copy.pop();
						String studentString;
						studentString = String.Format("%d|%s|%d|", student.id, student.name, student.grade);
						//TODO: This shit does not work. Fix it. FIX IT NAO!
						if (student.GetType() == GraduateStudent) {
							String concatString;
							GraduateStudent gradStud = (GraduateStudent)student;
							concatString = String.format("%d|%d|%s|", gradStud.grade2, gradStud.grade3, gradStud.supervisor);
							studentString = studentString.concat(concatString);
						} else if (student.getClass() == UndergraduateStudent.class) {
							String concatString;
							UndergraduateStudent gradStud = (UndergraduateStudent)student;
							concatString = String.format("%d|", gradStud.grade2);
							studentString = studentString.concat(concatString);
						} else if (student.getClass() == PhDStudent.class) {
							String concatString;
							PhDStudent phdStudent = (PhDStudent)student;
							concatString = String.format("%d|%s|%s|", phdStudent.grade2, phdStudent.supervisor, phdStudent.thesis);
							studentString = studentString.concat(concatString);
						}
						students.add(studentString);
					} catch (StackException e) {
						e.printStackTrace();
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
	        public ArrayList addStudent(int id, String name, int grade)
	        {
	            Student student = new Student(id, name, grade);
	            Validator validator = new Validator(this.repo);
	            ArrayList errorList = validator.validateStudent(student);

	            if (errorList.Count == 0)
	            {
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
	        public Lab2.Lab2_Repository.Stack<Student> allStudents()
	        {
	            return this.repo.allStudents();
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
	    }
	}
}
