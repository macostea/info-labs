using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Lab2.Lab2_Model;

namespace Lab2.UI
{
    public partial class Form1 : Form
    {
        private Lab2_Controller.Controller controller;

        private ListView listView;
        private TextBox idBox = new TextBox();
        private TextBox nameBox = new TextBox();
        private TextBox gradeBox = new TextBox();
        private TextBox supervisorBox = new TextBox();
        private TextBox thesisBox = new TextBox();
        private TextBox grade2Box = new TextBox();
        private TextBox grade3Box = new TextBox();

        private ComboBox filterComboBox = new ComboBox();

        public Form1(Lab2_Controller.Controller controller)
        {
            this.Text = "Student Manager";
            this.Size = new Size(600, 600);
            this.controller = controller;
            InitializeComponent();

            this.addListView();
            this.addTextBoxes();
            this.addButtons();
            this.addFilterSection();
            this.addReloadButton();
        }

        void addListView() {
            this.listView = new ListView();

            this.listView.View = View.Details;

            this.listView.Columns.Add("ID", -2);
            this.listView.Columns.Add("Name", -2);

            this.listView.FullRowSelect = true;
            this.listView.GridLines = true;

            this.fillListView();

            this.listView.Size = new Size(350, 400);
            this.listView.Location = new Point(20, 20);
            this.Controls.Add(this.listView);

            this.listView.SelectedIndexChanged += new EventHandler(indexChanged);
        }

        void fillListView(IList<Student> list = null)
        {
            if (list == null)
            {
                list = this.controller.allStudentObjects();
            }

            foreach (Student student in list)
            {
                ListViewItem item = new ListViewItem();
                item.Text = student.id.ToString();
                item.SubItems.Add(student.name);
                this.listView.Items.Add(item);
            }
        }

        private void clearTextBoxes()
        {
            foreach (Control control in this.Controls)
            {
                if (control.GetType().Equals(new TextBox().GetType()))
                {
                    control.Text = "";
                }
            }
        }

        private int boxX()
        {
            return this.listView.Location.X + this.listView.Size.Width + 20;
        }

        void addTextBoxes()
        {
            idBox.Size = new Size(100, 20);
            idBox.Location = new Point(boxX(), 20);
            nameBox.Size = new Size(100, 20);
            nameBox.Location = new Point(boxX(), idBox.Location.Y + idBox.Size.Height + 5);
            gradeBox.Size = new Size(100, 20);
            gradeBox.Location = new Point(boxX(), nameBox.Location.Y + nameBox.Size.Height + 5);
            supervisorBox.Size = new Size(100, 20);
            supervisorBox.Location = new Point(boxX(), gradeBox.Location.Y + gradeBox.Size.Height + 5);
            thesisBox.Size = new Size(100, 20);
            thesisBox.Location = new Point(boxX(), supervisorBox.Location.Y + supervisorBox.Size.Height + 5);
            grade2Box.Size = new Size(100, 20);
            grade2Box.Location = new Point(boxX(), thesisBox.Location.Y + thesisBox.Size.Height + 5);
            grade3Box.Size = new Size(100, 20);
            grade3Box.Location = new Point(boxX(), grade2Box.Location.Y + grade2Box.Height + 5);

            this.Controls.AddRange(new Control[] { idBox, nameBox, gradeBox, supervisorBox, thesisBox, grade2Box, grade3Box });
        }

        void addButtons()
        {
            Button addStudentBtn = new Button();
            addStudentBtn.Text = "Add Student";
            addStudentBtn.Size = new Size(100, 40);
            addStudentBtn.Location = new Point(boxX(), grade3Box.Location.Y + grade3Box.Height + 5);

            addStudentBtn.Click += addStudentBtn_Click;

            this.Controls.Add(addStudentBtn);
        }

        void addFilterSection()
        {
            this.filterComboBox.Size = new Size(100, 20);
            this.filterComboBox.Location = new Point(boxX(), this.grade3Box.Location.Y + grade3Box.Height + 40 + 20);

            Dictionary<string, string> filterTypes = new Dictionary<string, string>();
            filterTypes.Add("1", "avg >= 5");
            filterTypes.Add("2", "avg < 5");
            filterTypes.Add("3", "avg = 10");

            this.filterComboBox.DataSource = new BindingSource(filterTypes, null);
            this.filterComboBox.DisplayMember = "Value";
            this.filterComboBox.ValueMember = "Key";

            this.filterComboBox.SelectedValueChanged += filterComboBox_SelectedValueChanged;

            this.Controls.Add(this.filterComboBox);
        }

        void addReloadButton()
        {
            Button reloadBtn = new Button();
            reloadBtn.Text = "Reload";
            reloadBtn.Size = new Size(100, 40);
            reloadBtn.Location = new Point(this.filterComboBox.Location.X, this.filterComboBox.Location.Y + this.filterComboBox.Size.Height + 10);
            reloadBtn.Click += reloadBtn_Click;

            this.Controls.Add(reloadBtn);
        }

        void reloadBtn_Click(object sender, EventArgs e)
        {
            this.listView.Items.Clear();
            this.fillListView();
        }

        void filterComboBox_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.filterComboBox.SelectedValue.Equals("1"))
            {
                List<Student> filteredList = new List<Student>();

                foreach (Student student in this.controller.allStudentObjects())
                {
                    if (student.average() >= 5)
                    {
                        filteredList.Add(student);
                    }
                }

                this.listView.Items.Clear();
                this.fillListView(filteredList);
            }
            else if (this.filterComboBox.SelectedValue.Equals("2"))
            {
                List<Student> filteredList = new List<Student>();

                foreach (Student student in this.controller.allStudentObjects())
                {
                    if (student.average() < 5)
                    {
                        filteredList.Add(student);
                    }
                }

                this.listView.Items.Clear();
                this.fillListView(filteredList);
            }
            else if (this.filterComboBox.SelectedValue.Equals("3"))
            {
                List<Student> filteredList = new List<Student>();

                foreach (Student student in this.controller.allStudentObjects())
                {
                    if (student.average() == 10)
                    {
                        filteredList.Add(student);
                    }
                }

                this.listView.Items.Clear();
                this.fillListView(filteredList);
            }
        }

        #region Actions

        private void indexChanged(object sender, EventArgs e)
        {
            ListView.SelectedListViewItemCollection selected = this.listView.SelectedItems;
            if (selected.Count <= 0)
            {
                return;
            }
            Student selectedStudent = null;
            foreach (Student student in this.controller.allStudentObjects())
            {
                if (student.id.ToString().Equals(selected[0].Text))
                {
                    selectedStudent = student;
                    break;
                }
            }
            if (selectedStudent != null)
            {
                clearTextBoxes();
                idBox.Text = selectedStudent.id.ToString();
                nameBox.Text = selectedStudent.name;
                gradeBox.Text = selectedStudent.grade.ToString();

                if (selectedStudent.GetType().Equals(new PhDStudent().GetType()))
                {
                    supervisorBox.Text = ((PhDStudent)selectedStudent).supervisor;
                    thesisBox.Text = ((PhDStudent)selectedStudent).thesis;
                    grade2Box.Text = ((PhDStudent)selectedStudent).grade2.ToString();
                }
                else if (selectedStudent.GetType().Equals(new GraduateStudent().GetType()))
                {
                    supervisorBox.Text = ((GraduateStudent)selectedStudent).supervisor;
                    grade2Box.Text = ((GraduateStudent)selectedStudent).grade2.ToString();
                    grade3Box.Text = ((GraduateStudent)selectedStudent).grade3.ToString();
                }
                else if (selectedStudent.GetType().Equals(new UndergraduateStudent().GetType()))
                {
                    grade2Box.Text = ((UndergraduateStudent)selectedStudent).grade2.ToString();
                }
            }
        }

        void addStudentBtn_Click(object sender, EventArgs e)
        {
            Form addStudentForm = new AddStudentForm(this.controller);
            if (addStudentForm.ShowDialog() == DialogResult.Yes)
            {
                this.listView.Items.Clear();
                this.fillListView();
            }
        }

        #endregion
    }
}
