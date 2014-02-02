using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_Exam.Model
{
    class Bread : Product, IReadable
    {
        void IReadable.readAttributesFromString(String aString)
        {
            String[] tokens = aString.Split(',');
        }
    }
}
