using System;
using MidTermC.MidTermC_Controller;

namespace MidTermC
{
	namespace MidTermC_View {
		public class View
		{
			private Controller controller;
			public View (Controller controller)
			{
				this.controller = controller;
			}

			public void showMenu() {
				Console.WriteLine ("Hello!");
				String userInput = "";
				while (!"x".Equals (userInput)) {
					Console.WriteLine ("(1)Write expired elements to file");
					Console.WriteLine ("(x)Exit");
					userInput = Console.ReadLine ();

					if ("1".Equals (userInput)) {
						this.writeExpiredElementsToFile ();
					}
				}
			}

			private void writeExpiredElementsToFile() {
				Console.WriteLine ("File name: ");
				String filename = Console.ReadLine ();

				this.controller.writeExpiredElementsToFile (filename);

				Console.WriteLine ("Done!");
			}
		}
	}
}

