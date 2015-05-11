using System;
using System.Collections.Generic;
using Model;

namespace ClientWindows
{
	public class Controller: IObservable<List<Order>>
	{
        private NetworkClient client;
        private List<IObserver<List<Order>>> observers = new List<IObserver<List<Order>>>();

		public Controller (NetworkClient client)
		{
            this.client = client;
            this.client.controller = this;
		}

		public List<Order> GetOrders() {
            this.client.GetOrders();
            return new List<Order>();
		}

        public void SendOrder(Order o)
        {
            this.client.AddOrder(o);
        }

		#region IAgencyObserver
		public void Update(Object o) {
			if (o is List<Order>) {
                foreach (var observer in this.observers)
                {
                    observer.OnNext((List<Order>)o);
                }
			}
		}

		#endregion

        #region IObservable

        public IDisposable Subscribe(IObserver<List<Order>> observer)
        {
            if (!observers.Contains(observer))
            {
                observers.Add(observer);
            }

            return new Unsubscriber(observers, observer);
        }

        private class Unsubscriber : IDisposable
        {
            private List<IObserver<List<Order>>> _observers;
            private IObserver<List<Order>> _observer;

            public Unsubscriber(List<IObserver<List<Order>>> observers, IObserver<List<Order>> observer)
            {
                this._observers = observers;
                this._observer = observer;
            }

            public void Dispose()
            {
                if (_observer != null && _observers.Contains(_observer)) {
                    _observers.Remove(_observer);
                }
            }
        }

        #endregion
    }
}

