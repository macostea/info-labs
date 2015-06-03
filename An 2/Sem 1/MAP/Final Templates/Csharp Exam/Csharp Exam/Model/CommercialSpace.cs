using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_Exam.Model
{
    class CommercialSpace : Property, IReadable
    {
        void IReadable.readAttributesFromString(String aString)
        {
            String[] tokens = aString.Split(';');
            this.surface = Convert.ToInt16(tokens[1]);
            this.setZone(tokens[2]);
        }
    }
}
