using System;

namespace Lab2
{
	namespace Lab2_Utils {
		public class StackException : Exception
		{
			public string exceptionMessage;
			public StackException ()
			{
			}
			public StackException(string message) {
				this.exceptionMessage = message;
			}

		}
	}
}

