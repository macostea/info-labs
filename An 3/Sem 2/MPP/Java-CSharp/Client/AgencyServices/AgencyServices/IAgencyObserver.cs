using System;
using System.Collections.Generic;

namespace AgencyServices
{
	public interface IAgencyObserver
	{
		void Update(Object info);
	}
}

