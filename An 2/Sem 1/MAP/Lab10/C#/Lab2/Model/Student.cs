using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab2
{
	namespace Lab2_Model {
		[Serializable()]
		public class Student : Comparable<Student>, Readable, HasId
	    {
            public string name { get; set; }
            public int id { get; set; }
            public int grade { get; set; }


			public Student() {
			}

	        public Student(int id, string name, int grade)
	        {
	            this.id = id;
	            this.name = name;
	            this.grade = grade;
	        }

			public virtual float average() {
				return grade;
			}

			bool Comparable<Student>.isGreaterThan(Student student) {
				return (this.grade > student.grade);
			}

			public override String ToString() {
				return String.Format("{0}|{1}|{2}|{3}|", this.GetType(), this.id, this.name, this.grade);
			}

			void Readable.readAttributesFromString(String inputString) {
				string[] tokens = inputString.Split ('|');
				this.id = Convert.ToInt16 (tokens [1]);
				this.name = tokens [2];
				this.grade = Convert.ToInt16 (tokens [3]);
			}

			int HasId.getId() {
				return this.id;
			}
	    }
	}
}
