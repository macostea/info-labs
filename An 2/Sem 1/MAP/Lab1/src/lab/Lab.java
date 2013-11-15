/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

/**
 *
 * @author mihai
 */
public class Lab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Utils utils = new Utils();
        int nr = Integer.parseInt(args[0]);
        int i = nr+1;
        while (!utils.isPrime(i)){
            i++;
        }
        System.out.println(i);
    }
}
