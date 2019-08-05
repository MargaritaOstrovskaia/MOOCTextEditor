/** 
 * @author UC San Diego Intermediate Programming MOOC team and Margarita Ostrovskaia
 * date 03/23/2019
 */
package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes */
public class DocumentBenchmarking {	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 100;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";
		
	    // The amount of characters to increment each step
	    // You can play around with this
		int increment = 20000;

		// The number of steps to run.  
		// You can play around with this.
		int numSteps = 20;
		
		// THe number of characters to start with. 
		// You can play around with this.
		int start = 50000;
		
		// TODO: prints out timing results
		for (int numToCheck = start; numToCheck < numSteps*increment + start; numToCheck += increment)
		{  
			System.out.print(numToCheck + "\t");
			String text = getStringFromFile(textfile, numToCheck);
			
			long timeStartBasicDoc = System.nanoTime();
			for(int i=0; i<trials; i++) {
				BasicDocument basicDoc = new BasicDocument(text);
				basicDoc.getFleschScore();
			}
			long timeEndBasicDoc = System.nanoTime();
			double timeBasicDoc = (timeEndBasicDoc - timeStartBasicDoc) / 1000000000.0;
			System.out.print(timeBasicDoc + "\t");
			
			long timeStartEfficientDoc = System.nanoTime();
			for(int i=0; i<trials; i++) {
				EfficientDocument efficientDoc = new EfficientDocument(text);
				efficientDoc.getFleschScore();
			}
			long timeEndEfficientDoc = System.nanoTime();
			double timeEfficientDoc = (timeEndEfficientDoc - timeStartEfficientDoc) / 1000000000.0;
			System.out.print(timeEfficientDoc + "\n");
		}
	}
	
	/** Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters */
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		
		return s.toString();
	}	
}