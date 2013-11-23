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
		class Repository<T>
	    {
			private Stack<T> elements = new Stack<T>();

	        /**
	         * 
	         * Adds an element in the repository.
	         * 
	         * @param element The element to be added.
	         */
			public void addElement(T element)
	        {
				this.elements.push(element);
	        }

	        /**
	         * 
	         * Removes an element from the repository.
	         * 
	         * @param element The element to be removed.
	         */
			public void removeElement(T element)
	        {
				Stack<T> temp = new Stack<T>();
	            while (true)
	            {
					T topElement = this.elements.pop();
					if (topElement.Equals(element))
	                {
	                    break;
	                }
					temp.push(topElement);
	            }
	            while (temp.getSize() != 0)
	            {
					this.elements.push(temp.pop());
	            }
	        }

	        /**
	         * 
	         * Returns the element from the top of the stack.
	         * 
	         * @return The element from the top of the stack.
	         */
			public T getTopElement()
	        {
				T temp = this.elements.pop();
				this.elements.push(temp);
	            return temp;
	        }

	        /**
	         * 
	         * Returns the number of elements in the repository.
	         * 
	         * @return The number of elements in the repository. Positive int.
	         */
			public int numberOfElements()
	        {
				return this.elements.getSize();
	        }

	        /**
	         * 
	         * Returns a copy of the elements stack.
	         * 
	         * @return A copy of the elements stack.
	         */
			public Stack<T> allElements()
	        {
				return this.elements.copy();
	        }

			public void saveRepoToFile(string filename) {
				StreamWriter writer = new StreamWriter (filename);

				try {
					Stack<T> copy = this.allElements();
					while (!copy.isEmpty()) {
						T element = copy.pop();
						writer.WriteLine(element.ToString());
					}
					writer.Close();
				} catch (IOException e) {
					System.Console.WriteLine (e.Message);
				} catch (StackException e) {
					System.Console.WriteLine (e.Message);
				}
			}

			public void readRepoFromFile(string filename) {
				StreamReader reader = new StreamReader (filename);
				Stack<T> stack = new Stack<T> ();
				try {
					string line;
					while ((line = reader.ReadLine ()) != null) {
						string[] tokens = line.Split ('|');
						Readable element;
						if (tokens [0].Contains ("GraduateStudent")) {
							element = new GraduateStudent ();
						} else if (tokens [0].Contains ("PhDStudent")) {
							element = new PhDStudent ();
						} else if (tokens [0].Contains ("UndergraduateStudent")) {
							element = new UndergraduateStudent ();
						} else if (tokens [0].Contains ("Student")) {
							element = new Student ();
						} else {
							return;
						}

						element.readAttributesFromString (line);
						stack.push ((T)element);
					}
					this.elements = stack;
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
					Stack<T> stack = (Stack<T>)deserializer.Deserialize (fileStream);
					this.elements = stack;
					fileStream.Close ();
				}
			}
		}
	}
}
