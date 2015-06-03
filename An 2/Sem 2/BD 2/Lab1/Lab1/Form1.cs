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

namespace Lab1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private int selectedRow = 0;
        private int selectedChildRow = 0;
        private DataTable polls;
        private DataTable options;
        private SqlDataAdapter daPolls;
        private SqlDataAdapter daOptions;
        private DataSet dataSet;
        private DataRelation dr;
        private SqlCommandBuilder commandBuilder;
        private BindingSource bsPolls = new BindingSource();
        private BindingSource bsOptions = new BindingSource();
        private SqlConnection dbConn;

        private void Form1_Load(object sender, EventArgs e)
        {
            this.dataGridView1.DataSource = bsPolls;
            this.dataGridView2.DataSource = bsOptions;
            this.getData();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (this.dataGridView2.CurrentRow != null)
            {
                this.dataGridView2.Rows.RemoveAt(this.dataGridView2.CurrentRow.Index);
                this.daOptions.Update(this.dataSet, "Options");
            }
        }

        private void getData()
        {
            this.dbConn = new SqlConnection("Data Source=(local);Initial Catalog=Blogging Platform;Integrated Security=SSPI");
            this.dataSet = new DataSet();
            this.daOptions = new SqlDataAdapter("SELECT * FROM options", this.dbConn);
            this.daPolls = new SqlDataAdapter("select * from Polls", dbConn);
            this.commandBuilder = new SqlCommandBuilder(this.daOptions);

            this.daOptions.Fill(this.dataSet, "Options");
            this.daPolls.Fill(this.dataSet, "Polls");

            this.dr = new DataRelation("PollsOptions", this.dataSet.Tables["Polls"].Columns["id"], this.dataSet.Tables["Options"].Columns["poll_id"]);
            this.dataSet.Relations.Add(this.dr);

            this.bsPolls.DataSource = this.dataSet;
            this.bsPolls.DataMember = "Polls";

            this.bsOptions.DataSource = this.bsPolls;
            this.bsOptions.DataMember = "PollsOptions";
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.daOptions.Update(this.dataSet, "Options");
        }
    }
}
