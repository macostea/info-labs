package lab2.Model;

public interface ComparableStudent {
    /**
     *
     * Returns whether the implementing student is greater than a given student.
     * @param student The student to compare to
     * @return True if implementing student is greater than passed student. False otherwise.
     */
    public boolean isGreaterThan(Student student);
}
