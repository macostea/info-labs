using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class Form3 : Form
    {

        private BindingSource bs = new BindingSource();
        public Form3()
        {
            InitializeComponent();
        }

        private void Form3_Load(object sender, EventArgs e)
        {
            this.bs.DataSource = typeof(Band);
            this.dataGridView1.DataSource = this.bs;
            this.bs.Add(new Band("AAAAAA"));
            this.bs.Add(new Band("BBBBBB"));
            this.bs.Add(new Band("wahflsa"));

            this.textBox1.DataBindings.Add(new Binding("text", this.bs, "name"));
        }
    }
}
