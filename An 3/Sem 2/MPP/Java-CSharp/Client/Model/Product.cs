using System;

namespace Model
{
	[Serializable]
	public class Product
	{

		public int ProductId { get; set; }
		public String Name { get; set; }
		public int Price { get; set; }
		public int Quantity { get; set; }

		public Product ()
		{
		}
	}
}

