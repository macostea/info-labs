using System;

namespace Lab2
{
	namespace Lab2_Model {
		class GraduateStudent : Student, ComparableStudent
		{
			public String supervisor;
			public int grade2;
			public int grade3;

			public GraduateStudent(int id, String name, int grade, String supervisor, int grade2, int grade3) : base(id, name, grade) {		
				this.supervisor = supervisor;
				this.grade2 = grade2;
				this.grade3 = grade3;
			}

			public override float average() {
				return (this.grade + this.grade2 + this.grade3) / 3;
			}

			bool ComparableStudent.isGreaterThan(Student student) {
				return (this.average() > student.average());
			}
		}
	}
}

