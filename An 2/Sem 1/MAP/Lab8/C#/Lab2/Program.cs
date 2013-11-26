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

			controller.addStudent(0,"asfas",6);
			controller.addStudent(1,"galkdj",10);
			controller.addStudent(2,"fkleaj",9,10);
			controller.addStudent(3,"fekljaj",7,10,"alsjkf","alkfja");

//			repo.serializeDataToFile("whateverman.txt");
//	        repo.deserializeDataFromFile("whateverman.txt");

			repo.saveRepoToFile("textfile.txt");
			controller.readRepoFromFile("textfile.txt");

            ui.showMenu();
        }
    }
}
