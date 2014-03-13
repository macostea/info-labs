using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApplication1
{
    class Band
    {
        private Int16 id;
        private String name;
        private static Int16 currentId = 0;

        public String Name
        {
            get { return name; }
            set { name = value; }
        }

        public Int16 Id
        {
            get { return id; }
            set { id = value; }
        }

        public Band(String name)
        {
            this.name = name;
            this.Id = currentId;
            currentId++;
        }

    }
}
