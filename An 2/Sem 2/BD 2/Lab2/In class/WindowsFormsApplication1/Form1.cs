using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;

namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        SqlConnection dbConn;
        DataSet dataSet;
        SqlDataAdapter dataAdapter;
        BindingSource bindingSource = new BindingSource();

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.dbConn = new SqlConnection("Data Source=(local);Initial Catalog=Blogging Platform;Integrated Security=SSPI");
            this.dataSet = new DataSet();
            this.dataAdapter = new SqlDataAdapter("Select * from Polls", this.dbConn);

            this.dataAdapter.InsertCommand = new SqlCommand("insert into Polls values(@id, @subject, @text, @authorId, @date)", this.dbConn);
            this.dataAdapter.InsertCommand.Parameters.Add(new SqlParameter("@id", SqlDbType.Int, 4, "id"));
            this.dataAdapter.InsertCommand.Parameters.Add(new SqlParameter("@subject", SqlDbType.VarChar, 100, "subject"));
            this.dataAdapter.InsertCommand.Parameters.Add(new SqlParameter("@text", SqlDbType.VarChar, 200, "text"));
            this.dataAdapter.InsertCommand.Parameters.Add(new SqlParameter("@authorId", SqlDbType.Int));
            this.dataAdapter.InsertCommand.Parameters["@authorId"].SourceColumn = "authorId";
            this.dataAdapter.InsertCommand.Parameters.Add(new SqlParameter("@date", SqlDbType.DateTime));
            this.dataAdapter.InsertCommand.Parameters["@date"].SourceColumn = "date";

            this.dataAdapter.DeleteCommand = new SqlCommand("delete from Polls where id = @id", this.dbConn);
            this.dataAdapter.DeleteCommand.Parameters.Add(new SqlParameter("@id", SqlDbType.Int));
            this.dataAdapter.DeleteCommand.Parameters["@id"].SourceColumn = "id";
            this.dataAdapter.DeleteCommand.Parameters["@id"].SourceVersion = DataRowVersion.Original;

            this.dataAdapter.UpdateCommand = new SqlCommand("update Polls set subject=@subject, text=@text, authorId=@authorId, date=@date");
            this.dataAdapter.UpdateCommand.Parameters.Add(new SqlParameter("@subject", SqlDbType.VarChar, 100, "subject"));
            this.dataAdapter.UpdateCommand.Parameters.Add(new SqlParameter("@text", SqlDbType.VarChar, 200, "text"));
            this.dataAdapter.UpdateCommand.Parameters.Add(new SqlParameter("@date", SqlDbType.DateTime));
            this.dataAdapter.UpdateCommand.Parameters["@date"].SourceColumn = "date";

            this.dataSet.Clear();
            this.dataAdapter.Fill(this.dataSet, "Polls");

            // This approach is not synchronized with other controls. The textBox will not automagically get the data synchronized with the gridView
            //this.dataGridView1.DataSource = this.dataSet;
            //this.dataGridView1.DataMember = "Polls";

            //this.textBox1.DataBindings.Add(new Binding("text", this.dataSet.Tables["Polls"], "subject"));

            this.bindingSource.DataSource = this.dataSet;
            this.bindingSource.DataMember = "Polls";

            this.dataGridView1.DataSource = this.bindingSource;

            this.textBox1.DataBindings.Add(new Binding("text", this.bindingSource, "subject"));
        }

        private void button1_Click(object sender, EventArgs e)
        {
            DataTable dataTable = this.dataSet.Tables["Polls"];
            foreach (DataRow row in dataTable.Rows) {
                if (row.RowState != DataRowState.Deleted)
                {
                    Console.WriteLine(row[0] + " " + row[1] + " " + row[2] + " " + row[3] + " " + row[4] + " " + row.RowState.ToString());
                }
                else
                {
                    Console.WriteLine(row[0, DataRowVersion.Original] + " " + row[1, DataRowVersion.Original] + " " + row[2, DataRowVersion.Original] + " " + row[3, DataRowVersion.Original] + " " + row[4, DataRowVersion.Original] + " " + row.RowState.ToString());
                }
            }
            this.dataAdapter.Update(this.dataSet, "Polls");
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }


        
    }
}
