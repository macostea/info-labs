using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Model;

namespace ClientWindows
{
    public class Packet
    {
        public String packetType { get; set; }
        public List<Order> orders { get; set; }
        public String message { get; set; }
        public Order orderToProcess { get; set; }
    }
}
