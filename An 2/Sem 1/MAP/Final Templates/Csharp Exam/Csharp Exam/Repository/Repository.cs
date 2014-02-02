using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_Exam.Repository
{
    class Repository<T> : IRepository<T>
    {
        private List<T> elements = new List<T>();

        public Repository(List<T> list) {
            this.elements = list;
        }

        void IRepository<T>.addElement(T element)
        {
            this.elements.Add(element);
        }

        void IRepository<T>.removeElement(T element)
        {
            this.elements.Remove(element);
        }

        List<T> IRepository<T>.allElements()
        {
            return new List<T>(this.elements);
        }
    }
}
