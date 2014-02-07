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

        List<T> IRepository<T>.allElements()
        {
            return new List<T>(this.elements);
        }
    }
}
