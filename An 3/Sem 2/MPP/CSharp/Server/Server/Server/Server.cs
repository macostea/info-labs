using System;
using System.Collections;
using System.Collections.Generic;
using System.Runtime.Remoting;
using System.Runtime.Remoting.Channels;
using System.Runtime.Remoting.Channels.Tcp;
using System.Xml;
using System.IO;
using System.Text;

namespace Server
{
	public class Server
	{
		public OrdersDAO persistance;

		public Server ()
		{
			this.persistance = new OrdersDAO ();
		}

		public void Start() {
			BinaryServerFormatterSinkProvider serverProv = new BinaryServerFormatterSinkProvider();
			serverProv.TypeFilterLevel = System.Runtime.Serialization.Formatters.TypeFilterLevel.Full;
			BinaryClientFormatterSinkProvider clientProv = new BinaryClientFormatterSinkProvider();
			IDictionary props = new Hashtable();

            String xmlPort = null;

            using (XmlReader reader = XmlReader.Create("config.xml"))
            {
                Boolean foundPort = false;
                while (reader.Read())
                {
                    if (reader.NodeType == XmlNodeType.Element && reader.Name.Equals("port"))
                    {
                        foundPort = true;
                    }

                    if (reader.NodeType == XmlNodeType.Text && foundPort)
                    {
                        xmlPort = reader.Value;
                        break;
                    }
                }
            }

            if (xmlPort != null) {
                props["port"] = int.Parse(xmlPort);
            }
            else
            {
                props["port"] = 4322;
            }
                
			TcpChannel channel = new TcpChannel(props, clientProv, serverProv);
			ChannelServices.RegisterChannel(channel, false);

			var server = new ClientHandler (this);
			RemotingServices.Marshal(server, "Agency");

			// the server will keep running until keypress.
			Console.WriteLine("Server started ...");
			Console.WriteLine("Press <enter> to exit...");
			Console.ReadLine();
		}
	}
}

