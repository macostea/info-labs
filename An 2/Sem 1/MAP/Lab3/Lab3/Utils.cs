using System;

namespace Lab3
{

    public class Utils
    {
        public bool isPrime(int x)
        {
            if (x == 0 || x == 1) return false;
            for (int it = 2; it <= x / 2; it++)
            {
                if (x % it == 0) return false;
            }
            return true;
        }
    }
}
