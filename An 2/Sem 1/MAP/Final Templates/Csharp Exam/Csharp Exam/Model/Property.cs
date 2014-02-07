using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Csharp_Exam.Controller;

namespace Csharp_Exam.Model
{
    class Property
    {
        static String[] kZones = { "Z1", "Z2", "Z3", "Z4" };

        public int surface { get; set; }
        private String zone;
        public String getZone()
        {
            return zone;
        }
        public void setZone(String zone)
        {
            if (!kZones.Contains<String>(zone))
            {
                throw new ReadingException();
            }
            this.zone = zone;
        }
     }
}
