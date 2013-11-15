using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab2.Lab2_Repository;
using Lab2.Lab2_Controller;
using Lab2.Lab2_UI;

namespace Lab2
{
    class Program
    {
        static void Main(string[] args)
        {
            Repository repo = new Repository();
            Controller controller = new Controller(repo);
            UI ui = new UI(controller);

            ui.showMenu();
        }
    }
}
