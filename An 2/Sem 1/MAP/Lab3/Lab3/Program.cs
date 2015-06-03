using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab3
{
    class Program
    {
        static void Main(string[] args)
        {
            Utils Utils = new Utils();

            int argument = Convert.ToInt32(args[0]) + 1;
            while (!Utils.isPrime(argument))
            {
                argument++;
            }

            Console.WriteLine("The first prime number after {0} is {1}", args[0], argument);
            Console.ReadKey();
        }
    }
}
