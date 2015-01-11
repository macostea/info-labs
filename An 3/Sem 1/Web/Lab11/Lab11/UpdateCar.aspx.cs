using System;
using System.Web;
using System.Web.UI;

using MySql.Data.MySqlClient;

namespace Lab11
{
	
	public partial class UpdateCar : System.Web.UI.Page
	{
		private MySql.Data.MySqlClient.MySqlConnection conn;
		private string carID = null;

		public void Page_Load(object sender, EventArgs e)
		{
			if (Session ["loggedIn"] == null || (bool)Session ["loggedIn"] == false) {
				Server.Transfer ("Unauthorized.aspx");
			}

			string connectionString = "server=localhost;uid=root;pwd=;database=dealership;";

			conn = new MySql.Data.MySqlClient.MySqlConnection(connectionString);
			conn.Open();

			carID = Session ["carId"].ToString ();

			if (!IsPostBack) {
				MySqlCommand command = new MySqlCommand();
				command.Connection = conn;
				command.CommandText = String.Format("SELECT * FROM cars WHERE id='{0}'", carID);
				MySqlDataReader myreader = command.ExecuteReader();

				while (myreader.Read ()) {
					Model.Text = myreader.GetString ("model");
					Power.Text = myreader.GetInt32 ("engine_power").ToString ();
					Fuel.Text = myreader.GetString ("fuel");
					Price.Text = myreader.GetFloat ("price").ToString ();
					Color.Text = myreader.GetString ("color");
					Year.Text = myreader.GetInt32 ("manufacturing_year").ToString ();
				}

				myreader.Close ();
			}
		}

		public void UpdateCarButton_Click(object sender, EventArgs e)
		{
			MySqlCommand command = new MySqlCommand ();
			command.Connection = conn;
			command.CommandText = String.Format ("UPDATE `dealership`.`cars` SET `model` = '{0}', `engine_power` = '{1}', `fuel` = '{2}', `price` = '{3}', `color` = '{4}', `manufacturing_year` = '{5}' WHERE `id` = '{6}'", Model.Text, Power.Text, Fuel.Text, Price.Text, Color.Text, Year.Text, carID);
			command.ExecuteNonQuery ();

			Session.Remove ("carId");
			Server.Transfer ("GetCars.aspx");
		}
	}
}

