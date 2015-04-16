package Grid_Color_SAT;

import java.io.*;

import org.sat4j.reader.ParseFormatException;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

public class GridSolver {
	public static void main(String args []) throws IOException, ParseFormatException, ContradictionException, TimeoutException{
		Grid grid = new Grid();
		SATSolver s = new SATSolver();
		grid.readInput(); //Step 1
		grid.outputCNF(); //Step 2
		s.solve("output.cnf"); //Step 3 - Have a SAT solver read our 'output.cnf' file
		//and output a solution (if satisfiable) to a file. 
		//Step 4 - Convert that file back to a solution of Grid Coloring
		//Step 5 - output the solution in row major order.
	}

}