using System;

namespace Lab2
{
	namespace Lab2_Model {
		class UndergraduateStudent : Student
		{
			public int grade2;

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
				return String.Format("{0}|{1}|{2}|{3}|", this.id, this.name, this.grade, this.grade2);
		    }
		}
	}
}

