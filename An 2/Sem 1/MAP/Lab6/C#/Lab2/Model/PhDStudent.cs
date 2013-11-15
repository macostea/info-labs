using System;

namespace Lab2
{
	namespace Lab2_Model {
		class PhDStudent : Student, ComparableStudent
		{
			public String supervisor;
			public String thesis;
			public int grade2;

			public PhDStudent(int id, String name, int grade, String supervisor, String thesis, int grade2) : base (id, name, grade) {
				this.supervisor = supervisor;
				this.thesis = thesis;
				this.grade2 = grade2;
			}

			public override float average() {
				return (this.grade + this.grade2) / 2;
			}

			bool ComparableStudent.isGreaterThan(Student student) {
				return (this.average() > this.average());
			}
		}
	}
}

