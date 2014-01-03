using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab2.Lab2_Controller;
using Lab2.Lab2_Model;
using Lab2.Lab2_Repository;
using Lab2.Lab2_Utils;
using System.IO;

namespace Lab2
{
	namespace Lab2_Controller {
	    class Controller
	    {
			private Repository<Student> repo;

			/**
		     *
		     * Gets a String representation of all the elements from a given Dict
		     * @param dict The dict to process
		     * @return An ArrayList<String> of all the String representations of the elements from the given dict
		     */
			public static ArrayList elementsFromDict<T>(IDictionary<int, T> dict) {
				ArrayList strings = new ArrayList();

				foreach (T element in dict.Values) {
					strings.Add (element.ToString ());
				}
				return strings;
			}
			/**
		     *
		     * Moves all elements from a dict to another dict
		     * @param source The dict to move the elements from
		     * @param destination The dict to move the elements to
		     */
			public static void moveElements<W, T>(IDictionary<int, W> source, IDictionary<int, T> destination) where W:T
																											   where T : HasId {
				foreach (T element in source.Values) {
					destination.Add (element.getId (), element);
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
				while (currentStudent != null && currentStudent.grade != 10)
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
				return Controller.elementsFromDict(this.repo.allElements());
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
				IDictionary<int, Student> allStudents = this.repo.allElements();
				int no = 0;
				foreach (Comparable<Student> comparableStudent in allStudents.Values) {
					if (comparableStudent.isGreaterThan (student)) {
						no++;
					}
				}
				return no;
			}

			public void readRepoFromFile(string filename) {
				StreamReader reader = new StreamReader (filename);
				IDictionary<int, Student> dict = new Dictionary<int, Student> ();
				try {
					string line;
					while ((line = reader.ReadLine ()) != null) {
						string[] tokens = line.Split ('|');
						Readable element;
						if (tokens [0].Contains ("GraduateStudent")) {
							element = new GraduateStudent ();
						} else if (tokens [0].Contains ("PhDStudent")) {
							element = new PhDStudent ();
						} else if (tokens [0].Contains ("UndergraduateStudent")) {
							element = new UndergraduateStudent ();
						} else if (tokens [0].Contains ("Student")) {
							element = new Student ();
						} else {
							return;
						}

						element.readAttributesFromString (line);
						Student student = (Student) element;
						dict.Add(student.id, student);
					}
					this.repo = new Repository<Student>(dict);
				} catch (IOException e) {
					System.Console.WriteLine (e.Message);
				}
			}

			public void saveStudentsToFile(string filename) {
				this.repo.saveRepoToFile (filename);
			}
	    }
	}
}
