using System;
using Model;
using System.Collections.Generic;

namespace AgencyServices
{
	public interface IServer
	{
		void ConnectClient (IAgencyObserver client);
		List<Order> GetOrders ();
		Boolean AddOrder (Order o);
		Boolean UpdateOrder (Order o);
		Boolean RemoveOrder (Order o);
	}
}
