using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_Exam.Model
{
    class Flat : Property, IReadable
    {
        public int numberOfRooms { get; set; }

        void IReadable.readAttributesFromString(String aString)
        {
            String[] tokens = aString.Split(';');
            this.numberOfRooms = Convert.ToInt16(tokens[1]);
            this.surface = Convert.ToInt16(tokens[2]);
            this.setZone(tokens[3]);
        }
    }
}
