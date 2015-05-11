using System;

namespace Model
{
	[Serializable]
	public class Order
	{

		public int OrderId { get; set; }
		public int Quantity { get; set; }
		public String Status { get; set; }
		public Product Product { get; set; }
		public Agent Agent { get; set; }
		public Client Client { get; set; }

		public Order ()
		{
		}
	}
}

