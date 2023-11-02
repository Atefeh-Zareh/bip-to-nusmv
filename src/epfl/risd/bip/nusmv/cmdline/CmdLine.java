package epfl.risd.bip.nusmv.cmdline;

import java.io.File;

import epfl.risd.bip.nusmv.module.NuGenerate;
import epfl.risd.bip.nusmv.module.NuVisitor;
import epfl.risd.bip.nusmv.module.NuVisitorImplementation1;

public class CmdLine {

	private static String cmdLineHelp = "HELP: java -jar bip-to-nusmv input.bip output.smv [guide]";
	
	public static void main(String[] args)  {
		
		/**
		 * simple command line arguments
		 */
		if(args.length < 2) {
			System.out.println(cmdLineHelp);
			System.exit(0);
		}
	
		String inputFile = args[0];
		String outputFile = args[1];
		String guideFile = null;
		if(args.length == 3) guideFile = args[2];
		
		File outFile = new File(outputFile);
		
		// Choosing desired implementation
		NuVisitor v = new NuVisitorImplementation1();
		
		new NuGenerate(inputFile, guideFile, outFile, v);
		
		System.out.println("The " + args[1] + " file has been generated!");
	}
}
