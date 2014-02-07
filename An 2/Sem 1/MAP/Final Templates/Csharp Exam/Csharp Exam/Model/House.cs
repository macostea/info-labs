using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Csharp_Exam.Model
{
    class House : Property, IReadable
    {
        public int numberOfFloors { get; set; }
        public int numberOfRooms { get; set; }

        void IReadable.readAttributesFromString(String aString)
        {
            String[] tokens = aString.Split(';');
            this.numberOfFloors = Convert.ToInt16(tokens[1]);
            this.numberOfRooms = Convert.ToInt16(tokens[2]);
            this.surface = Convert.ToInt16(tokens[3]);
            this.setZone(tokens[4]);
        }
    }
}
