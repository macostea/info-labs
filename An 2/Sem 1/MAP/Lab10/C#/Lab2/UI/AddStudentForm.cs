using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Lab2.UI
{
    public partial class AddStudentForm : Form
    {
        private Lab2_Controller.Controller controller;

        private enum Type
        {
            Student,
            Graduate,
            PhD,
            Undergraduate
        }

        private TextBox idBox = new TextBox();
        private TextBox nameBox = new TextBox();
        private TextBox gradeBox = new TextBox();
        private TextBox supervisorBox = new TextBox();
        private TextBox thesisBox = new TextBox();
        private TextBox grade2Box = new TextBox();
        private TextBox grade3Box = new TextBox();

        private ComboBox comboBox = new ComboBox();

        public AddStudentForm(Lab2_Controller.Controller controller)
        {
            InitializeComponent();

            this.controller = controller;

            this.addTextBoxes();
            this.addButtons();
            this.addComboBox();
            
        }

        private int boxX()
        {
            return 20;
        }

        void addTextBoxes()
        {
            idBox.Size = new Size(100, 20);
            idBox.Location = new Point(boxX(), 20);
            nameBox.Size = new Size(100, 20);
            nameBox.Location = new Point(boxX(), idBox.Location.Y + idBox.Size.Height + 5);
            gradeBox.Size = new Size(100, 20);
            gradeBox.Location = new Point(boxX(), nameBox.Location.Y + nameBox.Size.Height + 5);
            gradeBox.TextChanged += numbersOnly;
            supervisorBox.Size = new Size(100, 20);
            supervisorBox.Location = new Point(boxX(), gradeBox.Location.Y + gradeBox.Size.Height + 5);
            supervisorBox.Enabled = false;
            thesisBox.Size = new Size(100, 20);
            thesisBox.Location = new Point(boxX(), supervisorBox.Location.Y + supervisorBox.Size.Height + 5);
            thesisBox.Enabled = false;
            grade2Box.Size = new Size(100, 20);
            grade2Box.Location = new Point(boxX(), thesisBox.Location.Y + thesisBox.Size.Height + 5);
            grade2Box.Enabled = false;
            grade2Box.TextChanged += numbersOnly;
            grade3Box.Size = new Size(100, 20);
            grade3Box.Location = new Point(boxX(), grade2Box.Location.Y + grade2Box.Height + 5);
            grade3Box.Enabled = false;
            grade3Box.TextChanged += numbersOnly;

            this.Controls.AddRange(new Control[] { idBox, nameBox, gradeBox, supervisorBox, thesisBox, grade2Box, grade3Box });
        }

        void numbersOnly(object sender, EventArgs e)
        {
            bool enteredLetter = false;
            TextBox box = (TextBox)sender;
            Queue<char> text = new Queue<char>();
            foreach (var ch in box.Text)
            {
                if (char.IsDigit(ch))
                {
                    text.Enqueue(ch);
                }
                else
                {
                    enteredLetter = true;
                }
            }

            if (enteredLetter)
            {
                StringBuilder sb = new StringBuilder();
                while (text.Count > 0)
                {
                    sb.Append(text.Dequeue());
                }                

                box.Text = sb.ToString();
                box.SelectionStart = box.Text.Length;
            }
        }

        void addButtons()
        {
            Button addBtn = new Button();
            addBtn.Text = "Add Student";
            addBtn.Location = new Point(idBox.Size.Width + idBox.Location.X + 5, idBox.Location.Y);
            addBtn.Size = new Size(100, 40);

            addBtn.Click += addBtn_Click;

            this.Controls.Add(addBtn);
        }

        void addComboBox()
        {
            this.comboBox.Size = new Size(100, 20);
            this.comboBox.Location = new Point(boxX(), this.grade3Box.Location.Y + this.grade3Box.Height + 5);

            this.comboBox.DataSource = Enum.GetValues(typeof(Type));
            this.comboBox.Name = "Type";

            this.comboBox.SelectedValueChanged += comboBox_SelectedValueChanged;

            this.Controls.Add(this.comboBox);
        }

        void comboBox_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.comboBox.SelectedValue != null)
            {
                if (this.comboBox.SelectedValue.Equals(Type.Student))
                {
                    supervisorBox.Enabled = false;
                    thesisBox.Enabled = false;
                    grade2Box.Enabled = false;
                    grade3Box.Enabled = false;
                }
                else if (this.comboBox.SelectedValue.Equals(Type.Graduate))
                {
                    supervisorBox.Enabled = true;
                    thesisBox.Enabled = false;
                    grade2Box.Enabled = true;
                    grade3Box.Enabled = true;
                }
                else if (this.comboBox.SelectedValue.Equals(Type.PhD))
                {
                    supervisorBox.Enabled = true;
                    thesisBox.Enabled = true;
                    grade2Box.Enabled = true;
                    grade3Box.Enabled = false;
                }
                else if (this.comboBox.SelectedValue.Equals(Type.Undergraduate))
                {
                    supervisorBox.Enabled = false;
                    thesisBox.Enabled = false;
                    grade2Box.Enabled = true;
                    grade3Box.Enabled = false;
                }
            }
        }

        void addBtn_Click(object sender, EventArgs e)
        {
            if (this.comboBox.SelectedValue != null)
            {
                if (this.comboBox.SelectedValue.Equals(Type.Student))
                {
                    this.controller.addStudent(Convert.ToInt16(idBox.Text), nameBox.Text, Convert.ToInt16(gradeBox.Text));
                }
                else if (this.comboBox.SelectedValue.Equals(Type.Graduate))
                {
                    this.controller.addStudent(Convert.ToInt16(idBox.Text), nameBox.Text, Convert.ToInt16(gradeBox.Text), Convert.ToInt16(grade2Box.Text), Convert.ToInt16(grade3Box.Text), supervisorBox.Text);
                }
                else if (this.comboBox.SelectedValue.Equals(Type.PhD))
                {
                    this.controller.addStudent(Convert.ToInt16(idBox.Text), nameBox.Text, Convert.ToInt16(gradeBox.Text), Convert.ToInt16(grade2Box.Text), supervisorBox.Text, thesisBox.Text);
                }
                else if (this.comboBox.SelectedValue.Equals(Type.Undergraduate))
                {
                    this.controller.addStudent(Convert.ToInt16(idBox.Text), nameBox.Text, Convert.ToInt16(gradeBox.Text), Convert.ToInt16(grade2Box.Text));
                }

                this.DialogResult = DialogResult.Yes;
            }
        }
    }
}
