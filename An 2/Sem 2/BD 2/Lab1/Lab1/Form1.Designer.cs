namespace Lab1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.dataGridView2 = new System.Windows.Forms.DataGridView();
            this.blogging_PlatformDataSet = new Lab1.Blogging_PlatformDataSet();
            this.bloggingPlatformDataSetBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.optionsBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.optionsTableAdapter = new Lab1.Blogging_PlatformDataSetTableAdapters.OptionsTableAdapter();
            this.pollsBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.pollsTableAdapter = new Lab1.Blogging_PlatformDataSetTableAdapters.PollsTableAdapter();
            this.pollsBindingSource1 = new System.Windows.Forms.BindingSource(this.components);
            this.fKPollsOptionsBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.pollsBindingSource2 = new System.Windows.Forms.BindingSource(this.components);
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.blogging_PlatformDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.bloggingPlatformDataSetBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.optionsBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pollsBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pollsBindingSource1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fKPollsOptionsBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pollsBindingSource2)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(12, 12);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(373, 358);
            this.dataGridView1.TabIndex = 0;
            // 
            // dataGridView2
            // 
            this.dataGridView2.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView2.Location = new System.Drawing.Point(391, 12);
            this.dataGridView2.Name = "dataGridView2";
            this.dataGridView2.Size = new System.Drawing.Size(495, 263);
            this.dataGridView2.TabIndex = 1;
            // 
            // blogging_PlatformDataSet
            // 
            this.blogging_PlatformDataSet.DataSetName = "Blogging_PlatformDataSet";
            this.blogging_PlatformDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // bloggingPlatformDataSetBindingSource
            // 
            this.bloggingPlatformDataSetBindingSource.DataSource = this.blogging_PlatformDataSet;
            this.bloggingPlatformDataSetBindingSource.Position = 0;
            // 
            // optionsBindingSource
            // 
            this.optionsBindingSource.DataMember = "Options";
            this.optionsBindingSource.DataSource = this.bloggingPlatformDataSetBindingSource;
            // 
            // optionsTableAdapter
            // 
            this.optionsTableAdapter.ClearBeforeFill = true;
            // 
            // pollsBindingSource
            // 
            this.pollsBindingSource.DataMember = "Polls";
            this.pollsBindingSource.DataSource = this.bloggingPlatformDataSetBindingSource;
            // 
            // pollsTableAdapter
            // 
            this.pollsTableAdapter.ClearBeforeFill = true;
            // 
            // pollsBindingSource1
            // 
            this.pollsBindingSource1.DataMember = "Polls";
            this.pollsBindingSource1.DataSource = this.bloggingPlatformDataSetBindingSource;
            // 
            // fKPollsOptionsBindingSource
            // 
            this.fKPollsOptionsBindingSource.DataMember = "FK_Polls_Options";
            this.fKPollsOptionsBindingSource.DataSource = this.optionsBindingSource;
            // 
            // pollsBindingSource2
            // 
            this.pollsBindingSource2.DataMember = "Polls";
            this.pollsBindingSource2.DataSource = this.blogging_PlatformDataSet;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(391, 281);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 2;
            this.button1.Text = "Delete child";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(472, 281);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 3;
            this.button2.Text = "Update child";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(899, 382);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.dataGridView2);
            this.Controls.Add(this.dataGridView1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.blogging_PlatformDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.bloggingPlatformDataSetBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.optionsBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pollsBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pollsBindingSource1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fKPollsOptionsBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pollsBindingSource2)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private Blogging_PlatformDataSet blogging_PlatformDataSet;
        private System.Windows.Forms.DataGridView dataGridView2;
        private System.Windows.Forms.BindingSource bloggingPlatformDataSetBindingSource;
        private System.Windows.Forms.BindingSource optionsBindingSource;
        private Blogging_PlatformDataSetTableAdapters.OptionsTableAdapter optionsTableAdapter;
        private System.Windows.Forms.BindingSource pollsBindingSource;
        private Blogging_PlatformDataSetTableAdapters.PollsTableAdapter pollsTableAdapter;
        private System.Windows.Forms.BindingSource pollsBindingSource1;
        private System.Windows.Forms.BindingSource fKPollsOptionsBindingSource;
        private System.Windows.Forms.BindingSource pollsBindingSource2;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
    }
}

