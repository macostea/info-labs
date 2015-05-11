using System;
using MySql.Data;
using MySql.Data.MySqlClient;
using System.Data;

namespace Server
{
	public class DAO
	{

        protected String dbConnString = "server=localhost;uid=root;pwd=root;database=agency";
		protected volatile MySqlConnection connection;

		public DAO ()
		{
			
		}

		public void Connect() {
			lock (this) {
				try {
					this.connection = new MySql.Data.MySqlClient.MySqlConnection();
					this.connection.ConnectionString = this.dbConnString;
					this.connection.Open();
				} catch (MySqlException ex) {
					Console.WriteLine (ex.ToString ());
				}
			}
		}

		public void Disconnect() {
			lock (this) {
				try {
					this.connection.Close();
				} catch (MySqlException ex) {
					Console.WriteLine (ex.ToString ());
				}
			}
		}
	}
}

