using System;
using MidTermC.MidTermC_Model;
using MidTermC.MidTermC_Repository;
using MidTermC.MidTermC_Controller;
using MidTermC.MidTermC_View;

namespace MidTermC
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			RepositoryInterface<FridgeElement> repo = new Repository<FridgeElement> ();
			Controller controller = new Controller (repo);
			View view = new View (controller);

			controller.addCan (0, DateTime.Today.AddDays (4));
			controller.addFood (1, DateTime.Today.AddDays (-4));
			controller.addMilk (2, DateTime.Today.AddDays (-4));
			controller.addMilk (3, DateTime.Today.AddMonths (2));

			view.showMenu ();
		}
	}
}
