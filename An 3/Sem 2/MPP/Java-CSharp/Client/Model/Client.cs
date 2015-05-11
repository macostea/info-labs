using System;

namespace Model
{
	[Serializable]
	public class Client
	{
		
		public int ClientID { get; set; }
		public String Name { get; set; }
		public String Address { get; set; }

		public Client ()
		{
		}
	}
}

