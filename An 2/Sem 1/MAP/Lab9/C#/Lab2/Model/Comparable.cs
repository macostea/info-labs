using System;

namespace Lab2
{
	namespace Lab2_Model {
		interface Comparable<T>
		{
			/**
		     *
		     * Returns whether the implementing object is greater than a given element.
		     * @param element The element to compare to
		     * @return True if implementing object is greater than passed element. False otherwise.
		     */
			bool isGreaterThan(T element);
		}
	}
}

