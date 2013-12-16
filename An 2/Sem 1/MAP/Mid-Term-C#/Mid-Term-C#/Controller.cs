using System;
using MidTermC.MidTermC_Model;
using MidTermC.MidTermC_Repository;
using System.Collections;
using System.Collections.Generic;
using System.IO;

namespace MidTermC
{
	namespace MidTermC_Controller {
		public class Controller
		{
			private RepositoryInterface<FridgeElement> repo;
			public Controller (RepositoryInterface<FridgeElement> repo)
			{
				this.repo = repo;
			}

			public void addMilk(int id, DateTime expirationDate) {
				FridgeElement milk = new Milk (id, expirationDate);
				this.repo.addElement (milk);
			}

			public void addFood(int id, DateTime expirationDate) {
				FridgeElement food = new Food (id, expirationDate);
				this.repo.addElement (food);
			}

			public void addCan(int id, DateTime expirationDate) {
				FridgeElement can = new Can (id, expirationDate);
				this.repo.addElement (can);
			}

			public void writeExpiredElementsToFile(string filename) {
				IList<FridgeElement> expiredElements = new List<FridgeElement> ();
				IList<FridgeElement> elements = this.repo.allElements ();
				foreach (FridgeElement element in elements) {
					if (element.expirationDate.CompareTo (DateTime.Today) > 0) {
						expiredElements.Add (element);
					}
				}

				Controller.saveListToFile<FridgeElement> (expiredElements, filename);
			}

			public static void saveListToFile<T>(IList<T> list, string filename) {
				StreamWriter writer = new StreamWriter (filename);

				try {
					foreach (T element in list) {
						writer.WriteLine(element.ToString());
					}
					writer.Close();
				} catch (IOException e) {
					System.Console.WriteLine (e.Message);
				}
			}
		}
	}
}

