using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Csharp_Exam.Repository;
using Csharp_Exam.Model;
using System.IO;

namespace Csharp_Exam.Controller
{
    class Controller
    {
        private IRepository<Product> repo;

        public Controller(IRepository<Product> repo)
        {
            this.repo = repo;
        }

        public void addProduct()
        {
            this.repo.addElement(new Bread());
        }

        public void readRepoFromFile(string filename)
        {
            StreamReader reader = new StreamReader(filename);
            List<Product> list = new List<Product>();
            try
            {
                string line;
                while ((line = reader.ReadLine()) != null)
                {
                    string[] tokens = line.Split('|');
                    IReadable element;
                    if (tokens[2].Contains("Bread"))
                    {
                        element = new Bread();
                    }
                    else
                    {
                        return;
                    }

                    element.readAttributesFromString(line);
                    Product product = (Product)element;
                    list.Add(product);
                }
                this.repo = new Repository<Product>(list);
            }
            catch (IOException e)
            {
                System.Console.WriteLine(e.Message);
            }
        }
    }
}
