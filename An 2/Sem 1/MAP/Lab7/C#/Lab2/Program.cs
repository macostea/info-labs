using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Lab2.Lab2_Repository;
using Lab2.Lab2_Controller;
using Lab2.Lab2_UI;
using Lab2.Lab2_Model;

namespace Lab2
{
    class Program
    {
        static void Main(string[] args)
        {
			Repository<Student> repo = new Repository<Student>();
            Controller controller = new Controller(repo);
            UI ui = new UI(controller);

            ui.showMenu();
        }
    }
}
