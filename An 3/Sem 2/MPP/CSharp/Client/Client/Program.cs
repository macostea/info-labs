using System;
using Gtk;
using System.Threading;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Collections;
using System.Collections.Generic;
using Model;
using AgencyServices;

namespace Client
{
	class MainClass
	{

		public static void Main (string[] args)
		{
//			BinaryServerFormatterSinkProvider serverProv = new BinaryServerFormatterSinkProvider();
//			serverProv.TypeFilterLevel = System.Runtime.Serialization.Formatters.TypeFilterLevel.Full;
//			BinaryClientFormatterSinkProvider clientProv = new BinaryClientFormatterSinkProvider();
//			IDictionary props = new Hashtable();
//
//			props["port"] = 0;
//			TcpChannel channel = new TcpChannel(props, clientProv, serverProv);
//			ChannelServices.RegisterChannel(channel, false);
//			IServer server =
//				(IServer)Activator.GetObject(typeof(IServer), "tcp://localhost:55555/Agency");
//			
//			List<Order> orders = server.GetOrders ();
//
//			Console.WriteLine ("Got back {0} orders", orders.Count);

			Application.Init ();
			MainWindow win = new MainWindow ();
			win.Show ();
			Application.Run ();


		}
	}
}
