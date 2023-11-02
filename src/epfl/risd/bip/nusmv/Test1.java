package epfl.risd.bip.nusmv;

import java.io.File;
import epfl.risd.bip.nusmv.module.NuGenerate;
import epfl.risd.bip.nusmv.module.NuVisitor;
import epfl.risd.bip.nusmv.module.NuVisitorImplementation1;

public class Test1 {
	
	public static void main(String[] args)  {
		
		// Choosing input and output files
		String inputFile = "input/test.bip";
		String outputFile = "output/test.smv";
		String guideFile = "input/testGuide.txt";
		
		File outFile = new File(outputFile);
		
		// Choosing desired implementation
		NuVisitor v = new NuVisitorImplementation1();
		
		new NuGenerate(inputFile, guideFile, outFile, v);
	}

}