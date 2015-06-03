using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
