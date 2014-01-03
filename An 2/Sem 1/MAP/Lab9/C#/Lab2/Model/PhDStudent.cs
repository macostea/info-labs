using System;

namespace Lab2
{
	namespace Lab2_Model {
		[Serializable()]
		class PhDStudent : Student
		{
			public String supervisor;
			public String thesis;
			public int grade2;

			public PhDStudent() : base() {
			}

			public PhDStudent(int id, String name, int grade, String supervisor, String thesis, int grade2) : base (id, name, grade) {
				this.supervisor = supervisor;
				this.thesis = thesis;
				this.grade2 = grade2;
			}

			public override float average() {
				return (this.grade + this.grade2) / 2;
			}

			bool Comparable<Student>.isGreaterThan(Student student) {
				return (this.average() > student.average());
			}

			public override String ToString() {
				return String.Format("{0}|{1}|{2}|{3}|{4}|{5}|{6}|", this.GetType(), this.id, this.name, this.grade, this.grade2, this.supervisor, this.thesis);
			}

			void Readable.readAttributesFromString(String inputString) {
				string[] tokens = inputString.Split ('|');
				this.id = Convert.ToInt16 (tokens [1]);
				this.name = tokens [2];
				this.grade = Convert.ToInt16 (tokens [3]);
				this.grade2 = Convert.ToInt16 (tokens [4]);
				this.supervisor = tokens [5];
				this.thesis = tokens [6];
			}
		}
	}
}

