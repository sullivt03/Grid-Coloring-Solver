package Grid_Color_SAT;


/**
 * Translates the input Grid Coloring instance into an equivelant SAT instance.
 * Methods:
 *  readInput() = Reads user input values for # of colors, rows, and columns. Calculates total # of variables.
 *  outputCNF() = Outputs the translated SAT instance into a .cnf file called "output".
 *  constraintColor() = Meets constraints 1.A. and 1.B. that each cell has exactly one color.
 *  constraintCorner() = Meets constraint 2 that each 2x2 or greater subgrid cannot have all four
 *                       corners of a single color.
 * 
 * @author Hawiar Hussein 
 * @version 04.09.15
 */

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Grid {
	
	private int numVariables, numClauses, n, m, color;
	
	public Grid()
    {
        n = 0;
        m = 0;
        color = 0;
        numVariables = 0;
        numClauses = 0;
    }
	
	public void readInput() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(color < 1) //Must have at least 1 color
        {
            System.out.print("Please enter the number of colors (>=1): ");
            String colors = br.readLine();
            color = Integer.parseInt(colors);
            System.out.println(color);
        }
        while(n < 2) //Must have at least 2 rows
        {
            System.out.print("Please enter the number of rows (>= 2) : ");
            String rows = br.readLine();
            n = Integer.parseInt(rows);
            System.out.println(n);
        }
        while(m < 2) //Must have at least 2 columns
        {
            System.out.print("Please enter the number of columns (>=2) : ");
            String columns = br.readLine();
            m = Integer.parseInt(columns);
            System.out.println(m);
        }
        numVariables = n * m * color;
    }
	
   /*
    * Output the new SAT instance to a CNF file called 'output' in DIMACS format
    * @author: Hawiar Hussein
    * @version: 04.10.15 - Incomplete
	*/
    public void outputCNF() throws IOException
    {
        FileWriter wr = new FileWriter(new File("output.cnf"));
        wr.write("c Grid Coloring of size " + n + "x" + m + "\n");
        wr.write("c Generated by Convert");
        wr.write("c version 2015 by Hawiar Hussein");
        wr.write("p cnf " + numVariables + " " + numClauses + " ");
        wr.write(constraintColor());
        wr.write(constraintCorner());
        wr.close();
    }
    
    /*
    * The constraint color class adheres to the first constraint of the Grid Coloring problem dealing with colors.
    * String atLeastOne = holds all the clauses regarding constraint 1.A. saying that at least one color is 
    * assigned to a cell (i,j).
    * The first for loop tracks the starting point of each clause of size 'color'.
    * The second for loop adds to the clause incrementing by 1 up to the the size of 'color'-1
    * The third for loop handles constraint 1.B. by adding each negated clause of combinations 
    *    of numVariables.
    *    
    * @author: Hawiar Hussein
    * @version: 04.10.15 - Working
    */
   private String constraintColor()
   {
       String atLeastOne = ""; //Constraint 1A
       String justOne = ""; //Constraint 1B
       String colorString  = ""; 
       for(int i = 1; i <= numVariables;  i += color)
       {
           for(int j = 0; j < color; j++)
           {
               atLeastOne += (i+j) + " ";
               for(int x = 0; x < color; x++)
               {   
                   // Checking (i + j) > (i + x) helps to avoid the same variables ending up in a clause
                   // as well as avoiding repeated clauses by always tracking a larger variable
                   if ( (i + j) > (i+x))
                   {
                       justOne += -(i + x) + " " + -(i + j) + " 0";
                       numClauses++;
                   }
               }
           }
           atLeastOne += " 0"; //Does she want each clause on a new line? If yes, change to " 0\n"
           numClauses++;
       }
       colorString += atLeastOne + justOne;
       return colorString;
   }
   
   /*
    * Constraint 2
    * Check each subgrid starting at size 2x2 and increasing by column first.
    * Each subgrid may not have all four corners being of the same color.
    * 
    * @author: Hawiar Hussein
    * @version: 04.10.15 - Incomplete
    */
   private String constraintCorner()
   {
       String cornerString = "";
       for(int i = 2; i <= n; i++) //Minimum subgrid size 2 rows
       {
           for(int j = 2; j <= m; j++) //Minimum subgrid size 2 columns
           {
               for (int x = 1; x <= color; x++) //Check for each color
               {
            	   //Somehow check each subgrid, working on it
               }
           }
       }                   
       return cornerString;
   }
   
    public int getRows()
    {
        return n;
    }

    public int getColumns()
    {
        return m;
    }

    public int getColors()
    {
        return color;
    }

    public int getNumClauses()
    {
        return numClauses;
    }

    public int getNumVariables()
    {
        return numVariables;   
    }
	
	
}

