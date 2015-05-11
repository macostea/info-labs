using System;
using System.Net;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using System.Collections.Generic;
using AgencyServices;
using Model;

namespace Server
{
	public class ClientHandler: MarshalByRefObject, IServer
	{

		private Server server;
        private HashSet<IAgencyObserver> clients = new HashSet<IAgencyObserver>();

		public void ConnectClient (IAgencyObserver client) {
			this.clients.Add (client);
		}

		public ClientHandler (Server server)
		{
			this.server = server;
		}

		public List<Order> GetOrders() {
			return this.server.persistance.GetOrders ();
		}

		public Boolean AddOrder (Order o) {
			if (this.server.persistance.AddOrder (o)) {
                ThreadPool.QueueUserWorkItem(new WaitCallback(this.SendNotification));
				return true;
			}
			return false;
		}

		public Boolean RemoveOrder (Order o) {
			if (this.server.persistance.RemoveOrder (o)) {
                ThreadPool.QueueUserWorkItem(new WaitCallback(this.SendNotification));

				return true;
			}
			return false;
		}

		public Boolean UpdateOrder (Order o) {
			if (this.server.persistance.UpdateOrder (o)) {
                ThreadPool.QueueUserWorkItem(new WaitCallback(this.SendNotification));


				return true;
			}
			return false;
		}

        public void SendNotification(object state)
        {
            foreach (IAgencyObserver c in this.clients)
            {
                c.Update(this.server.persistance.GetOrders());
            }
        }
	}
}

