using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab2.Lab2_Model;
using Lab2.Lab2_Utils;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

namespace Lab2
{
	namespace Lab2_Repository {
		class Repository<T> where T : HasId
	    {
			private IDictionary<int, T> elements = new Dictionary<int, T>();

			/**
			 * Constructor
			 */
			public Repository() {
			}

			/**
			 * Constructor
			 * 
			 * @param adt The adt to use for the repository
			 */
			public Repository(IDictionary<int, T> adt) {
				this.elements = adt;
			}

	        /**
	         * 
	         * Adds an element in the repository.
	         * 
	         * @param element The element to be added.
	         */
			public void addElement(T element)
	        {
				this.elements.Add (element.getId(), element);
	        }

	        /**
	         * 
	         * Removes an element from the repository.
	         * 
	         * @param element The element to be removed.
	         */
			public void removeElement(T element)
	        {
				this.elements.Remove (element.getId ());
	        }

	        /**
	         * 
	         * Returns the element from the top of the stack.
	         * 
	         * @return The element from the top of the stack.
	         */
			public T getTopElement()
	        {
				foreach (T element in this.elements.Values) {
					return element;
				}

				return default(T);
	        }

	        /**
	         * 
	         * Returns the number of elements in the repository.
	         * 
	         * @return The number of elements in the repository. Positive int.
	         */
			public int numberOfElements()
	        {
				return this.elements.Count();
	        }

	        /**
	         * 
	         * Returns a copy of the elements.
	         * 
	         * @return A copy of the elements.
	         */
			public IDictionary<int, T> allElements()
	        {
				return new Dictionary<int, T> (this.elements);
	        }

			public void saveRepoToFile(string filename) {
				StreamWriter writer = new StreamWriter (filename);

				try {
					IDictionary<int, T> copy = this.allElements();
					foreach (T element in copy.Values) {
						writer.WriteLine(element.ToString());
					}
					writer.Close();
				} catch (IOException e) {
					System.Console.WriteLine (e.Message);
				}
			}

			public void serializeDataToFile(string filename) {
				Stream fileStream = File.Create (filename);
				BinaryFormatter serializer = new BinaryFormatter ();
				serializer.Serialize (fileStream, this.elements);
				fileStream.Close ();
			}

			public void deserializeDataFromFile(string filename) {
				if (File.Exists (filename)) {
					Stream fileStream = File.OpenRead (filename);
					BinaryFormatter deserializer = new BinaryFormatter ();
					IDictionary<int, T> dict = (IDictionary<int, T>)deserializer.Deserialize (fileStream);
					this.elements = dict;
					fileStream.Close ();
				}
			}
		}
	}
}
