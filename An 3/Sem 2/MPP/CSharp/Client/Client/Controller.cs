using System;
using AgencyServices;
using System.Collections.Generic;
using Model;

namespace Client
{
	public class Controller: MarshalByRefObject, IAgencyObserver
	{
		private IServer server;

		public Controller (IServer server)
		{
			this.server = server;
		}

		public List<Order> GetOrders() {
			return this.server.GetOrders ();
		}

		#region IAgencyObserver
		public void Update(Object o) {
			if (o is List<Order>) {
				//TODO: Do amazing things
			}
		}

		#endregion
	}
}

