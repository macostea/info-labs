package lab2.Model;

public interface Comparable<T> {
    /**
     *
     * Returns whether the implementing object is greater than a given element.
     * @param element The element to compare to
     * @return True if implementing object is greater than passed element. False otherwise.
     */
    public boolean isGreaterThan(T element);
}
