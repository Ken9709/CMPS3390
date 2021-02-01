package Kwood11.a2;
import java.util.Scanner;

/**
 * Main driver class for a2 calculator
 * @author Kenneth Wood
 * @version 1.0
 */
public class Main {

    /**
     * Main entry point for our application
     * @param args String array holding arguements passed in on the cmd line
     */

    public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);

	    System.out.println("Enter either a math problem or quit");
	    System.out.println("Math problem should be in the form of value mathOperator value");
	    System.out.println("i.e. \n 43.1 *2.2");

	    // Loop until user types quit
	    String line;
	    while(!(line = scan.nextLine()).equalsIgnoreCase("quit")){
	    	System.out.println(Calc.evaluate(line));
		}

    }
}
