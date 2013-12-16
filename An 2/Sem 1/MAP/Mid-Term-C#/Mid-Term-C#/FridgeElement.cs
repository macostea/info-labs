using System;

namespace MidTermC
{
	namespace MidTermC_Model {
		public class FridgeElement : HasId
		{
			private int id;
			public DateTime expirationDate;

			public FridgeElement (int id, DateTime expirationDate)
			{
				this.id = id;
				this.expirationDate = expirationDate;
			}

			int HasId.getId() {
				return this.id;
			}

			public override String ToString() {
				return String.Format ("{0} | {1} | {2} |", this.GetType (), this.id, this.expirationDate);
			}
		}
	}
}

