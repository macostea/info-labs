using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Model;

namespace ClientWindows
{
    public partial class Form1 : Form, IObserver<List<Order>>
    {
        private Controller controller;

        delegate void SetDataCallback(List<Order> orders);

        public Form1(Controller controller)
        {
            this.controller = controller;
            this.controller.Subscribe(this);
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.AddDataFromList(this.controller.GetOrders());
        }

        private void AddDataFromList(List<Order> orders)
        {
            if (this.dataGridView1.InvokeRequired)
            {
                SetDataCallback dataCallback = new SetDataCallback(AddDataFromList);
                this.dataGridView1.Invoke(dataCallback, new object[] {orders});
            }
            else
            {
                this.dataGridView1.Rows.Clear();
                foreach (Order o in orders)
                {
                    this.dataGridView1.Rows.Add(o.OrderId, o.Client.Name, o.Agent.Name, o.Product.Name, o.Quantity, o.Status);
                }
            }
        }

        #region IObserver

        // Summary:
        //     Notifies the observer that the provider has finished sending push-based notifications.
        public void OnCompleted()
        {

        }
        //
        // Summary:
        //     Notifies the observer that the provider has experienced an error condition.
        //
        // Parameters:
        //   error:
        //     An object that provides additional information about the error.
        public void OnError(Exception error)
        {

        }
        //
        // Summary:
        //     Provides the observer with new data.
        //
        // Parameters:
        //   value:
        //     The current notification information.
        public void OnNext(List<Order> value)
        {
            this.AddDataFromList(value);
        }
        #endregion

        private void button1_Click(object sender, EventArgs e)
        {
            Order o = new Order();
            Product p = new Product();
            p.ProductId = Int32.Parse(this.textBox1.Text);
            o.Product = p;
            Client c = new Client();
            c.ClientID = Int32.Parse(this.textBox3.Text);
            o.Client = c;
            Agent a = new Agent();
            a.AgentID = Int32.Parse(this.textBox2.Text);
            o.Agent = a;

            o.Quantity = Int32.Parse(this.textBox4.Text);
            o.Status = this.textBox5.Text;

            this.controller.SendOrder(o);
        }

        private void backgroundWorker1_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {

        }
    }
}
