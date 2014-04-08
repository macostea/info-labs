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

using System.Xml;

namespace Home
{
    public partial class Form1 : Form
    {
        DataTable parentTable;
        DataTable childTable;
        SqlDataAdapter parentDataAdapter;
        SqlDataAdapter childDataAdapter;
        DataSet dataSet;
        DataRelation dataRelation;
        SqlCommandBuilder commandBuilder;
        BindingSource parentBindingSource = new BindingSource();
        BindingSource childBindingSource = new BindingSource();
        SqlConnection connection;

        String dataSource, dbName, authentication, parentTableName, childTableName, primaryKey, foreignKey;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.readConfigFile();

            this.parentGridView.DataSource = parentBindingSource;
            this.childGridView.DataSource = childBindingSource;
            this.getData();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.childDataAdapter.Update(this.dataSet, this.childTableName);
        }

        private void getData()
        {
            this.connection = new SqlConnection("Data Source=" + dataSource + ";Initial Catalog=" + dbName + ";" + authentication);
            this.dataSet = new DataSet();
            this.parentDataAdapter = new SqlDataAdapter("SELECT * FROM " + parentTableName, this.connection);
            this.childDataAdapter = new SqlDataAdapter("SELECT * FROM " + childTableName, this.connection);
            this.commandBuilder = new SqlCommandBuilder(this.childDataAdapter);

            this.parentDataAdapter.Fill(this.dataSet, parentTableName);
            this.childDataAdapter.Fill(this.dataSet, childTableName);

            this.dataRelation = new DataRelation("ParentChild", this.dataSet.Tables[parentTableName].Columns[primaryKey], this.dataSet.Tables[childTableName].Columns[foreignKey]);
            this.dataSet.Relations.Add(this.dataRelation);

            this.parentBindingSource.DataSource = this.dataSet;
            this.parentBindingSource.DataMember = parentTableName;

            this.childBindingSource.DataSource = this.parentBindingSource;
            this.childBindingSource.DataMember = "ParentChild";
        }

        private void readConfigFile()
        {
            XmlDocument document = new XmlDocument();
            document.Load("..\\..\\config.xml");
            XmlNode config = document.SelectSingleNode("/config");

            this.dataSource = config.SelectSingleNode("dataSource").InnerText;
            this.dbName = config.SelectSingleNode("dbName").InnerText;
            this.authentication = config.SelectSingleNode("authentication").InnerText;
            this.parentTableName = config.SelectSingleNode("parentTableName").InnerText;
            this.childTableName = config.SelectSingleNode("childTableName").InnerText;
            this.primaryKey = config.SelectSingleNode("primaryKey").InnerText;
            this.foreignKey = config.SelectSingleNode("foreignKey").InnerText;
            
        }
    }
}
