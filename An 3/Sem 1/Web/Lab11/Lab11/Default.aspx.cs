using System;
using System.Data;
using System.Text;
using System.Web;
using System.Web.UI;

using MySql.Data.MySqlClient;
using System.Security.Cryptography;

namespace Lab11
{
	
	public partial class Default : System.Web.UI.Page
	{
		MySql.Data.MySqlClient.MySqlConnection conn;

		public void Page_Load(object sender, EventArgs e)
		{
			string connectionString = "server=localhost;uid=root;pwd=;database=dealership;";

			conn = new MySql.Data.MySqlClient.MySqlConnection(connectionString);
			conn.Open();
		}

		public void LoginButton_Click(object sender, EventArgs e)
		{
			MD5 md5hash = MD5.Create ();
			byte[] data = md5hash.ComputeHash (Encoding.UTF8.GetBytes (Password.Text));
			StringBuilder stringBuilder = new StringBuilder ();
			for (int i = 0; i < data.Length; i++) {
				stringBuilder.Append (data [i].ToString ("x2"));
			}

			MySqlCommand command = new MySqlCommand();
			command.Connection = conn;
			command.CommandText = String.Format("SELECT count(*) FROM users WHERE username='{0}' AND password='{1}'", Username.Text, stringBuilder.ToString());
			MySqlDataReader myreader = command.ExecuteReader();

			while (myreader.Read ()) {
				if (myreader.GetInt32 (0) == 1) {
					Session ["loggedIn"] = true;
					Server.Transfer ("GetCars.aspx");
				} else {
					LoginError.Text = "Login error! Credentials incorrect!";
				}
			}
		}
	}
}

