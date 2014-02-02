using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_Exam.Repository
{
    interface IRepository<T>
    {
        void addElement(T element);
        void removeElement(T element);
        List<T> allElements();
    }
}
