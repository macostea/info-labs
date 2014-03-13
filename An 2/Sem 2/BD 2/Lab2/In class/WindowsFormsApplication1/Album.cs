using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApplication1
{
    class Album
    {
        private String title;
        private int bandId;

        public int BandId
        {
            get { return bandId; }
            set { bandId = value; }
        }

        public String Title
        {
            get { return title; }
            set { title = value; }
        }
    }
}
