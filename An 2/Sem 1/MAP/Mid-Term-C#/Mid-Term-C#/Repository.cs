using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Collections;
using MidTermC.MidTermC_Model;

namespace MidTermC
{
	namespace MidTermC_Repository {
		class Repository<T> : RepositoryInterface<T> where T : HasId
	    {
			private IDictionary<int, T> elements = new Dictionary<int, T>();

			public Repository() {
			}

			void RepositoryInterface<T>.addElement(T element)
	        {
				this.elements.Add (element.getId(), element);
	        }

			IList<T> RepositoryInterface<T>.allElements()
			{
				IList<T> list = new List<T> ();

				foreach (T element in this.elements.Values) {
					list.Add (element);
				}

				return list;
			}
		}
	}
}
