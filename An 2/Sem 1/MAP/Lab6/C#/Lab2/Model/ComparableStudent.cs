using System;

namespace Lab2
{
	namespace Lab2_Model {
		interface ComparableStudent
		{
			/**
		     *
		     * Returns whether the implementing student is greater than a given student.
		     * @param student The student to compare to
		     * @return True if implementing student is greater than passed student. False otherwise.
		     */
			bool isGreaterThan(Student student);
		}
	}
}

