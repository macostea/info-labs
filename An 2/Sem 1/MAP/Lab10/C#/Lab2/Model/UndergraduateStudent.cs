using System;

namespace Lab2
{
	namespace Lab2_Model {
		[Serializable()]
		class UndergraduateStudent : Student, Comparable<Student>, Readable
		{
            public int grade2 { get; set; }

			public UndergraduateStudent() : base() {
			}

			public UndergraduateStudent(int id, String name, int grade, int grade2) : base(id, name, grade) {
				this.grade2 = grade2;
			}

			public override float average() {
				return (this.grade + this.grade2) / 2;
			}

			bool Comparable<Student>.isGreaterThan(Student student) {
				return (this.average() > student.average());
			}


			public override String ToString() {
				return String.Format("{0}|{1}|{2}|{3}|{4}|", this.GetType(), this.id, this.name, this.grade, this.grade2);
		    }

			void Readable.readAttributesFromString(String inputString) {
				string[] tokens = inputString.Split ('|');
				this.id = Convert.ToInt16 (tokens [1]);
				this.name = tokens [2];
				this.grade = Convert.ToInt16 (tokens [3]);
				this.grade2 = Convert.ToInt16 (tokens [4]);
			}
		}
	}
}

