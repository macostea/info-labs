using System;
using System.Collections.Specialized;
using System.Collections.Generic;

namespace Server
{
	class MainClass
	{

		public static void Main (string[] args)
		{
			Server server = new Server ();
			server.Start ();
		}
	}
}
