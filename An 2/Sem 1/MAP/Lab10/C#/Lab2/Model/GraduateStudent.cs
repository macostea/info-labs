using System;

namespace Lab2
{
	namespace Lab2_Model {
		[Serializable()]
		class GraduateStudent : Student, Comparable<Student>, Readable
		{
            public String supervisor { get; set; }
            public int grade2 { get; set; }
            public int grade3 { get; set; }

			public GraduateStudent() : base() {
			}

			public GraduateStudent(int id, String name, int grade, String supervisor, int grade2, int grade3) : base(id, name, grade) {		
				this.supervisor = supervisor;
				this.grade2 = grade2;
				this.grade3 = grade3;
			}

			public override float average() {
				return (this.grade + this.grade2 + this.grade3) / 3;
			}

			bool Comparable<Student>.isGreaterThan(Student student) {
				return (this.average() > student.average());
			}

			public override String ToString() {
				return String.Format("{0}|{1}|{2}|{3}|{4}|{5}|{6}|", this.GetType(), this.id, this.name, this.grade, this.grade2, this.grade3, this.supervisor);
			}

			void Readable.readAttributesFromString(String inputString) {
				string[] tokens = inputString.Split ('|');
				this.id = Convert.ToInt16 (tokens [1]);
				this.name = tokens [2];
				this.grade = Convert.ToInt16 (tokens [3]);
				this.grade2 = Convert.ToInt16 (tokens [4]);
				this.grade3 = Convert.ToInt16 (tokens [5]);
				this.supervisor = tokens [6];
			}
		}
	}
}

