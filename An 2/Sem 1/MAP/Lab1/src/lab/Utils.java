/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

/**
 *
 * @author mihai
 */
public class Utils {
    public boolean isPrime(int n) {
        for (int i=2; i<n/2; i++) {
            if (n%i == 0) return false;
        }
        return true;
    }
}
