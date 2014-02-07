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
        private IRepository<Property> propertyRepo;
        private IRepository<Transaction> transactionRepo;
        public int corruptedLines = 0;

        /*
         * Constructor
         */
        public Controller()
        {
        }

        /*
         * Constructor
         * @param propertyRepo Property repository
         * @param transactionRepo Transaction repository
         */
        public Controller(IRepository<Property> propertyRepo, IRepository<Transaction> transactionRepo)
        {
            this.propertyRepo = propertyRepo;
            this.transactionRepo = transactionRepo;
        }

        /*
         * Returns a filtered list of transactions
         * @param year0 the lower bound year (not null, valid year)
         * @param month0 the lower bound month (not null, valid month)
         * @param year1 the upper bound year (not null, valid year)
         * @param month1 the upper bound month (not null, valid month)
         * @param zone the zone to check in
         */
        public List<Transaction> filterTransactions(int year0, int month0, int year1, int month1, String zone)
        {
            List<Transaction> filteredList = new List<Transaction>();
            foreach (Transaction transaction in this.transactionRepo.allElements()) {
                if (transaction.year < year1 && transaction.year > year0 && transaction.month < month1 && transaction.month > month0 && transaction.property.getZone().Equals(zone))
                {
                    filteredList.Add(transaction);
                }
            }
            return filteredList;
        }

        /*
         * Reads a repository from a fil
         * @param filename The name of the file to read from (not null)
         */
        public void readRepoFromFile(string filename)
        {
            StreamReader reader = new StreamReader(filename);
            List<Property> propertyList = new List<Property>();
            List<Transaction> transactionList = new List<Transaction>();
            try
            {
                string line;
                while ((line = reader.ReadLine()) != null)
                {
                    try
                    {
                        string[] tokens = line.Split(';');
                        IReadable element;
                        Transaction transaction = new Transaction();
                        if (tokens[0].Contains("House"))
                        {
                            element = new House();
                            transaction.year = Convert.ToInt16(tokens[5]);
                            transaction.month = Convert.ToInt16(tokens[6]);

                            if (tokens.Length != 7)
                            {
                                throw new ReadingException();
                            }
                        }
                        else if (tokens[0].Contains("Flat"))
                        {
                            element = new Flat();
                            transaction.year = Convert.ToInt16(tokens[4]);
                            transaction.month = Convert.ToInt16(tokens[5]);

                            if (tokens.Length != 6)
                            {
                                throw new ReadingException();
                            }
                        }
                        else if (tokens[0].Contains("CommSp"))
                        {
                            element = new CommercialSpace();
                            transaction.year = Convert.ToInt16(tokens[3]);
                            transaction.month = Convert.ToInt16(tokens[4]);

                            if (tokens.Length != 5)
                            {
                                throw new ReadingException();
                            }
                        }
                        else
                        {
                            throw new ReadingException();
                        }

                        if (transaction.month < 1 || transaction.month > 12 || transaction.year < 1500 || transaction.year > DateTime.Today.Year)
                        {
                            throw new ReadingException();
                        }

                        element.readAttributesFromString(line);
                        Property property = (Property)element;
                        transaction.property = property;
                        transactionList.Add(transaction);
                        propertyList.Add(property);
                    }
                    catch (FormatException)
                    {
                        corruptedLines++;
                    }
                    catch (ReadingException)
                    {
                        corruptedLines++;
                    }
                    catch (IndexOutOfRangeException)
                    {
                        corruptedLines++;
                    }

                }
                this.propertyRepo = new Repository<Property>(propertyList);
                this.transactionRepo = new Repository<Transaction>(transactionList);
            }
            catch (IOException e)
            {
                System.Console.WriteLine(e.Message);
            }
        }
    }
}
