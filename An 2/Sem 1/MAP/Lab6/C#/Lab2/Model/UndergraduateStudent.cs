using System;

namespace Lab2
{
	namespace Lab2_Model {
		class UndergraduateStudent : Student, ComparableStudent
		{
			public int grade2;

			public UndergraduateStudent(int id, String name, int grade, int grade2) : base(id, name, grade) {
				this.grade2 = grade2;
			}

			public override float average() {
				return (this.grade + this.grade2) / 2;
			}

			bool ComparableStudent.isGreaterThan(Student student) {
				return (this.average() > student.average());
			}
		}
	}
}

