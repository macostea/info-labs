using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab2.Lab2_Model;
using Lab2.Lab2_Utils;

namespace Lab2
{
	namespace Lab2_Repository {
	    class Repository
	    {
	        private Stack students = new Stack(100);

	        /**
	         * 
	         * Adds a student in the repository.
	         * 
	         * @param student The student to be added.
	         */
	        public void addStudent(Student student)
	        {
	            this.students.push(student);
	        }

	        /**
	         * 
	         * Removes a student from the repository.
	         * 
	         * @param student The student to be removed.
	         */
	        public void removeStudent(Student student)
	        {
	            Stack temp = new Stack(this.students.getSize());
	            while (true)
	            {
	                Student element = this.students.pop();
	                if (element.Equals(student))
	                {
	                    break;
	                }
	                temp.push(element);
	            }
	            while (temp.getSize() != 0)
	            {
	                this.students.push(temp.pop());
	            }
	        }

	        /**
	         * 
	         * Returns the student from the top of the stack.
	         * 
	         * @return The student from the top of the stack.
	         */
	        public Student getTopStudent()
	        {
	            Student temp = this.students.pop();
	            this.students.push(temp);
	            return temp;
	        }

	        /**
	         * 
	         * Returns the number of students in the repository.
	         * 
	         * @return The number of students in the repository. Positive int.
	         */
	        public int numberOfStudents()
	        {
	            return this.students.getSize();
	        }

	        /**
	         * 
	         * Returns a copy of the students stack.
	         * 
	         * @return A copy of the students stack.
	         */
	        public Stack allStudents()
	        {
	            return this.students.copy();
	        }
	    }
	}
}
