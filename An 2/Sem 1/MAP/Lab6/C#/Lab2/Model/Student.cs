using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2
{
	namespace Lab2_Model {
	    class Student : ComparableStudent
	    {
	        public string name;
	        public int id;
	        public int grade;

	        public Student(int id, string name, int grade)
	        {
	            this.id = id;
	            this.name = name;
	            this.grade = grade;
	        }

			public virtual float average() {
				return grade;
			}

			bool ComparableStudent.isGreaterThan(Student student) {
				return (this.grade > student.grade);
			}
	    }
	}
}
