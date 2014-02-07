using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using Csharp_Exam.View;
using Csharp_Exam.Controller;

namespace Csharp_Exam
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Controller.Controller controller = new Controller.Controller();
            controller.readRepoFromFile("Properties.txt");

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1(controller));
        }
    }
}
