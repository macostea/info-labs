using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using Newtonsoft.Json;
using System.IO;

using Model;

namespace ClientWindows
{
    public class NetworkClient
    {
        private string host;
        private int port;

        private NetworkStream stream;
        private StreamWriter formatter;
        private StreamReader reader;
        private TcpClient connection;

        private Queue<Response> responses;
        private volatile bool finished;

        private EventWaitHandle waitHandle;

        public Controller controller { get; set; }

        public NetworkClient(string host, int port)
        {
            this.host = host;
            this.port = port;
            this.responses = new Queue<Response>();

            this.initConnection();
        }

        public void GetOrders()
        {
            Packet packet = new Packet();
            packet.packetType = "getAllOrders";
            JsonSerializerSettings settings = new JsonSerializerSettings();
            settings.NullValueHandling = NullValueHandling.Ignore;
            string serialized = JsonConvert.SerializeObject(packet, settings);
            this.formatter.WriteLine(serialized);
            this.formatter.Flush();
        }

        public void AddOrder(Order o)
        {
            Packet packet = new Packet();
            packet.packetType = "addOrder";
            packet.orderToProcess = o;
            JsonSerializerSettings settings = new JsonSerializerSettings();
            settings.NullValueHandling = NullValueHandling.Ignore;
            string serialized = JsonConvert.SerializeObject(packet, settings);
            this.formatter.WriteLine(serialized);
            this.formatter.Flush();
        }

        private void initConnection()
        {
            try
            {
                this.connection = new TcpClient(host, port);
                this.stream = this.connection.GetStream();
                this.formatter = new StreamWriter(this.connection.GetStream(), Encoding.UTF8);
                this.reader = new StreamReader(this.connection.GetStream(), Encoding.UTF8);
                this.finished = false;
                this.waitHandle = new AutoResetEvent(false);
                this.startReader();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.StackTrace);
            }
        }

        private void startReader()
        {
            Thread worker = new Thread(run);
            worker.Start();
        }

        public void run()
        {
            while (!this.finished)
            {
                try
                {
                    string response = this.reader.ReadLine();
                    dynamic responsePacket = JsonConvert.DeserializeObject<Packet>(response);
                    Console.WriteLine("Got response " + response);
                    if (this.controller != null) {
                        this.controller.Update(((Packet)responsePacket).orders);
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Reading error" + ex);
                }
            }
        }
    }
}
