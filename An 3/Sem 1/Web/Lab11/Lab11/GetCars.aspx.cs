using System;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

using MySql.Data.MySqlClient;

namespace Lab11
{
	
	public partial class GetCars : System.Web.UI.Page
	{
		MySql.Data.MySqlClient.MySqlConnection conn;

		protected void Page_Load(object sender, EventArgs e)
		{
			if (Session ["loggedIn"] == null || (bool)Session ["loggedIn"] == false) {
				Server.Transfer ("Unauthorized.aspx");
			}

			string connectionString = "server=localhost;uid=root;pwd=;database=dealership;";

			conn = new MySql.Data.MySqlClient.MySqlConnection(connectionString);
			conn.Open();

			this.FillTable ();
		}

		public void AddCarButton_Click(object sender, EventArgs e) 
		{
			MySqlCommand command = new MySqlCommand();
			command.Connection = conn;
			command.CommandText = string.Format ("INSERT INTO `dealership`.`cars` (`id`, `model`, `engine_power`, `fuel`, `price`, `color`, `manufacturing_year`) VALUES(NULL, '{0}', '{1}', '{2}', '{3}', '{4}', '{5}')", 
				Model.Text, Power.Text, Fuel.Text, Price.Text, Color.Text, Year.Text
			);
			command.ExecuteNonQuery ();

			this.FillTable ();
		}

		public void DeleteCarButton_Click(object sender, EventArgs e)
		{
			LinkButton button = (LinkButton)sender;
			if (button.CommandName == "Delete") {
				MySqlCommand command = new MySqlCommand ();
				command.Connection = conn;
				command.CommandText = String.Format ("DELETE FROM `dealership`.`cars` WHERE `cars`.`id` = '{0}'", button.CommandArgument);
				command.ExecuteNonQuery ();
				this.FillTable ();
			}
		}

		public void UpdateCarButton_Click(object sender, EventArgs e)
		{
			LinkButton button = (LinkButton)sender;
			if (button.CommandName == "Update") {
				Session ["carId"] = button.CommandArgument;
				Server.Transfer ("UpdateCar.aspx");
			}
		}

		private void FillTable()
		{
			MySqlCommand command = new MySqlCommand();
			command.Connection = conn;
			command.CommandText = "SELECT * FROM cars";
			MySqlDataReader myreader = command.ExecuteReader();

			for (int it = 1; it < CarsTable.Rows.Count; it++) {
				CarsTable.Rows.RemoveAt (it);
			}

			while (myreader.Read())
			{
				TableRow row = new TableRow();

				TableCell idCell = new TableCell();
				idCell.Text = myreader.GetInt32("id").ToString();
				TableCell modelCell = new TableCell();
				modelCell.Text = myreader.GetString("model");
				TableCell powerCell = new TableCell();
				powerCell.Text = myreader.GetInt32("engine_power").ToString();
				TableCell fuelCell = new TableCell();
				fuelCell.Text = myreader.GetString("fuel");
				TableCell priceCell = new TableCell();
				priceCell.Text = myreader.GetFloat("price").ToString();
				TableCell colorCell = new TableCell();
				colorCell.Text = myreader.GetString("color");
				TableCell yearCell = new TableCell();
				yearCell.Text = myreader.GetInt32("manufacturing_year").ToString();
				TableCell deleteCell = new TableCell ();
				TableCell updateCell = new TableCell ();

				LinkButton button = new LinkButton ();
				button.Text = "Delete";
				button.CommandName = "Delete";
				button.CommandArgument = myreader.GetInt32 ("id").ToString ();
				button.Click += DeleteCarButton_Click;
				deleteCell.Controls.Add (button);

				LinkButton updateButton = new LinkButton();
				updateButton.Text = "Update";
				updateButton.CommandName = "Update";
				updateButton.CommandArgument = myreader.GetInt32 ("id").ToString ();
				updateButton.Click += UpdateCarButton_Click;
				updateCell.Controls.Add (updateButton);

				TableCell[] cells = {idCell, modelCell, powerCell, fuelCell, priceCell, colorCell, yearCell, deleteCell, updateCell};

				row.Cells.AddRange(cells);

				CarsTable.Rows.Add(row);
			}

			myreader.Close ();
		}
	}
}

