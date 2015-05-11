using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using MySql.Data.MySqlClient;
using Model;

namespace Server
{
	public class OrdersDAO : DAO
	{
		public OrdersDAO ()
		{
		}

		public List<Order> GetOrders() {
			lock (this) {
				try {
					this.Connect();
					String query = "SELECT orders.id, orders.quantity, orders.status, agents.id AS 'agentId', agents.name AS 'agentName', client.id AS 'clientId', client.name AS 'clientName', client.address AS 'clientAddress', products.id AS 'productId', products.name AS 'productName', products.price AS 'productPrice', products.quantity AS 'productQuantity'\n" +
						"FROM orders\n" +
						"JOIN agents ON agents.id=orders.agentId\n" +
						"JOIN client ON client.id=orders.clientId\n" +
						"JOIN products ON products.id=orders.productId";

					MySqlCommand stmt = this.connection.CreateCommand ();
					stmt.CommandText = query;
					MySqlDataReader rs = stmt.ExecuteReader ();

					List<Order> result = new List<Order>();

					while (rs.Read()) {
						Order order = new Order();
						order.OrderId = rs.GetInt32 ("id");
						order.Quantity = rs.GetInt32("quantity");
						order.Status = rs.GetString("status");

						Agent agent = new Agent();
						agent.AgentID = rs.GetInt32("agentId");
						agent.Name = rs.GetString("agentName");

						Client client = new Client();
						client.ClientID = rs.GetInt32("clientId");
						client.Name = rs.GetString("clientName");
						client.Address = rs.GetString("clientAddress");

						Product product = new Product();
						product.ProductId = rs.GetInt32("productId");
						product.Name = rs.GetString("productName");
						product.Quantity = rs.GetInt32("productQuantity");
						product.Price = rs.GetInt32("productPrice");

						order.Agent = agent;
						order.Client = client;
						order.Product = product;

						result.Add (order);
					}

					rs.Close();
					this.Disconnect();
					return result;
				} catch (MySqlException ex) {
					Console.WriteLine (ex.ToString ());
					return null;
				}
			}
		}

		public Boolean UpdateOrder(Order o) {
			lock (this) {
				try {
					this.Connect();
					String query = "UPDATE agency.orders SET productId=" + o.Product.ProductId +
						", agentId=" + o.Agent.AgentID + ", clientId=" + o.Client.ClientID +
						", quantity=" + o.Quantity + ", status=" + o.Status +
						"WHERE id=" + o.OrderId;

					MySqlCommand stmt = this.connection.CreateCommand ();
					stmt.CommandText = query;

					int res = stmt.ExecuteNonQuery();

					Console.WriteLine("Update result = {0}", res);

					this.Disconnect();
					return true;

				} catch (MySqlException ex) {
					Console.WriteLine (ex.ToString ());
					return false;
				}
			}
		}

		public Boolean AddOrder(Order o) {
			lock (this) {
				try {
					this.Connect();
					String query = "INSERT INTO agency.orders VALUES (NULL, " + o.Product.ProductId + ", " + o.Agent.AgentID +
						", " + o.Client.ClientID + ", " + o.Quantity + ", '" + o.Status + "')";

					MySqlCommand stmt = this.connection.CreateCommand();
					stmt.CommandText = query;
					int res = stmt.ExecuteNonQuery();

					Console.WriteLine("Insert result = {0}", res);

					this.Disconnect();
					return true;

				} catch (MySqlException ex) {
					Console.WriteLine (ex.ToString ());
					return false;
				}
			}
		}

		public Boolean RemoveOrder(Order o) {
			lock (this) {
				try {
					this.Connect();
					String query = "DELETE FROM agency.orders WHERE id=" + o.OrderId;

					MySqlCommand stmt = this.connection.CreateCommand();
					stmt.CommandText = query;
					int res = stmt.ExecuteNonQuery();

					Console.WriteLine("Delete result = {0}", res);

					this.Disconnect();
					return true;

				} catch (MySqlException ex) {
					Console.WriteLine (ex.ToString ());
					return false;
				}
			}
		}
	}
}

