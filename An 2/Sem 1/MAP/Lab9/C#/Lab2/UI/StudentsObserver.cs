using System;
using Lab2.Lab2_Controller;
using System.Collections;
using Lab2.Lab2_Model;
using System.Collections.Generic;

namespace Lab2
{
	namespace Lab2_UI {
		public class StudentsObserver : IObserver<IDictionary<int, Student>>
		{
			private String comparator;

			private IDisposable unsubscriber;
			public StudentsObserver (String comparator)
			{
				this.comparator = comparator;
			}

			public virtual void Subscribe(Controller provider) {
				this.unsubscriber = provider.Subscribe (this);
			}

			public virtual void OnNext(IDictionary<int, Student> arg) {
				foreach (Student student in arg.Values) {
					if ("<".Equals(this.comparator)) {
						if (student.average() < 5) {
							Console.WriteLine (student.ToString() + "< 5");
						}
					} else {
						if (student.average() >= 5) {
							Console.WriteLine (student.ToString() + ">= 5");
						}
					}
				}
			}

			public virtual void OnCompleted() {

			}

			public virtual void OnError(Exception e) {

			}

			public virtual void Unsubscribe() {
				this.unsubscriber.Dispose ();
			}
		}
	}
}

