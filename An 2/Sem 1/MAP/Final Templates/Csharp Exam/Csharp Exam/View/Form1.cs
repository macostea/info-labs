using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Csharp_Exam.Model;

namespace Csharp_Exam.View
{
    partial class Form1 : Form
    {
        private Controller.Controller controller;
        private ListView listView = new ListView();

        private TextBox yearBox0 = new TextBox();
        private TextBox monthBox0 = new TextBox();
        private TextBox yearBox1 = new TextBox();
        private TextBox monthBox1 = new TextBox();
        private TextBox zoneBox = new TextBox();

        public Form1(Controller.Controller controller)
        {
            this.controller = controller;

            this.Size = new Size(600, 600);

            Label corruptedLinesLabel = new Label();
            corruptedLinesLabel.Location = new Point(10, 10);
            corruptedLinesLabel.Text = String.Format("Number of corrupted lines: {0}", this.controller.corruptedLines);
            corruptedLinesLabel.AutoSize = true;

            this.Controls.Add(corruptedLinesLabel);

            this.addListView();
            this.addFilterSection();
        }

        private void addListView()
        {
            this.listView.Columns.Add("Type");
            this.listView.Columns.Add("Surface");
            this.listView.Columns.Add("Zone");
            this.listView.Columns.Add("Year");
            this.listView.Columns.Add("Month");
            this.listView.Columns.Add("Rooms");
            this.listView.Columns.Add("Floors");

            this.listView.View = System.Windows.Forms.View.Details;

            this.listView.Size = new Size(350, 400);
            this.listView.Location = new Point(10, 40);

            this.Controls.Add(this.listView);
        }

        private void addFilterSection()
        {
            this.yearBox0.Size = new Size(100, 20);
            this.yearBox0.Location = new Point(370, 40);
            this.monthBox0.Size = new Size(100, 20);
            this.monthBox0.Location = new Point(370, 70);

            this.yearBox1.Size = new Size(100, 20);
            this.yearBox1.Location = new Point(480, 40);
            this.monthBox1.Size = new Size(100, 20);
            this.monthBox1.Location = new Point(480, 70);

            this.zoneBox.Size = new Size(100, 20);
            this.zoneBox.Location = new Point(370, 100);

            Button reloadBtn = new Button();
            reloadBtn.Size = new Size(100, 40);
            reloadBtn.Location = new Point(370, 130);
            reloadBtn.Text = "Reload";
            reloadBtn.Click += reloadBtn_Click;

            this.Controls.AddRange(new Control[] { yearBox0, monthBox0, yearBox1, monthBox1, zoneBox, reloadBtn});
        }

        void reloadBtn_Click(object sender, EventArgs e)
        {
            this.fillListView(this.controller.filterTransactions(Convert.ToInt16(yearBox0.Text), Convert.ToInt16(monthBox0.Text), Convert.ToInt16(yearBox1.Text), Convert.ToInt16(monthBox1.Text), zoneBox.Text));
        }

        private void fillListView(List<Transaction> list)
        {
            foreach (Transaction transaction in list)
            {
                Property property = transaction.property;
                ListViewItem item = new ListViewItem();

                if (property.GetType().Equals(new House().GetType()))
                {
                    House house = (House)property;
                    item.Text = "House";
                    item.SubItems.Add(house.surface.ToString());
                    item.SubItems.Add(house.getZone());
                    item.SubItems.Add(transaction.year.ToString());
                    item.SubItems.Add(transaction.month.ToString());
                    item.SubItems.Add(house.numberOfRooms.ToString());
                    item.SubItems.Add(house.numberOfFloors.ToString());
                }
                else if (property.GetType().Equals(new Flat().GetType()))
                {
                    Flat flat = (Flat)property;
                    item.Text = "Flat";
                    item.SubItems.Add(flat.surface.ToString());
                    item.SubItems.Add(flat.getZone());
                    item.SubItems.Add(transaction.year.ToString());
                    item.SubItems.Add(transaction.month.ToString());
                    item.SubItems.Add(flat.numberOfRooms.ToString());
                    item.SubItems.Add("");
                }
                else if (property.GetType().Equals(new CommercialSpace().GetType()))
                {
                    CommercialSpace commSpace = (CommercialSpace)property;
                    item.Text = "Commercial Space";
                    item.SubItems.Add(commSpace.surface.ToString());
                    item.SubItems.Add(commSpace.getZone());
                    item.SubItems.Add(transaction.year.ToString());
                    item.SubItems.Add(transaction.month.ToString());
                    item.SubItems.Add("");
                    item.SubItems.Add("");
                }

                this.listView.Items.Add(item);                              
            }
        }
    }
}
