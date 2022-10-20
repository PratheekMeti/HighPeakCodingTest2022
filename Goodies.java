import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Goodies 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner in = new Scanner(System.in);
		int N, M, StartIndex, Difference; // N: Number of goodies, M: Number of Employees
		ArrayList<String> GoodiesAndPrices = new ArrayList<String>();
		ArrayList<String> Goodies = new ArrayList<String>();
		ArrayList<Integer> Prices = new ArrayList<Integer>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("D:/Users/rakeshshivanna/Desktop/Java/HighPeakCodingTest2022/src/input.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       GoodiesAndPrices.add(line);
		    }
		}
		// Remove the first element as it's just the description
        GoodiesAndPrices.remove(0);

        N = GoodiesAndPrices.size();

        for(String s: GoodiesAndPrices)
        {
        	String[] tokens = s.split(": ");
        	Goodies.add(tokens[0]);
        	Prices.add(Integer.parseInt(tokens[1]));
        }
        
        // Sort the goodies according to prices
        for(int i = 0; i < Prices.size(); i++)
        {
        	 for(int j = 1; j < (Prices.size() - i); j++)
        	 {  
                 if(Prices.get(j-1) > Prices.get(j))
                 {  
                        // swap Prices  
                        int tempPrice1 = Prices.get(j-1);
                        int tempPrice2 = Prices.get(j);
                        Prices.set(j, tempPrice1);
                        Prices.set(j-1, tempPrice2);
                        
                        // swap Goodies
                        String tempGoodie1 = Goodies.get(j-1);
                        String tempGoodie2 = Goodies.get(j);
                        Goodies.set(j, tempGoodie1);
                        Goodies.set(j-1, tempGoodie2);
                }
        	 }
        }

		System.out.print("Enter the number of Employee: ");
		M = in.nextInt();
		
		// find the suitable set(Difference in min and max is least)
		StartIndex = 0;
		Difference = Prices.get(M-1) - Prices.get(0); 
		for(int i = 0; i < (N-M+1); i++)
		{
			int currentDifference = Prices.get(i+M-1) - Prices.get(i); 
			if(currentDifference < Difference)
			{
				Difference = currentDifference;
				StartIndex = i;
			}
		}
		
		 try 
		 {
			 FileWriter myWriter = new FileWriter("D:/Users/rakeshshivanna/Desktop/Java/HighPeakCodingTest2022/src/output.txt");
		     myWriter.write("The goodies that are selected for distribution are:\n");
		     for(int i = StartIndex; i < StartIndex + M; i++)
			 {
		    	 myWriter.write(Goodies.get(i) + ": " + Prices.get(i) + "\n");
			 }
		     myWriter.write("\n");
		     myWriter.write("And the difference between the chosen goodie with highest price and the lowest price is " + Difference + "\n");

		     myWriter.close();
		     System.out.println("Successfully wrote to the file.");
		 }
		 catch (IOException e)
		 {
		     System.out.println("An error occurred during creating output file");
		     e.printStackTrace();
		 }
	}
}
