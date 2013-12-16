using System;
using System.Collections.Generic;

namespace MidTermC
{
	namespace MidTermC_Repository {
		public interface RepositoryInterface<T>
		{
			void addElement(T element);
			IList<T> allElements();
		}
	}
}

