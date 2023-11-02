package epfl.risd.bip.nusmv.module;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collection;
import ujf.verimag.bip.Core.Interactions.CompoundType;
import BIPTransformation.TransformationFunction;

public class NuGenerate {

	public NuGenerate(String inputFile, String guideFile, File outputFile, NuVisitor v)  {

		try {
			
			CompoundType ct = TransformationFunction.ParseBIPFile(inputFile);

			PrintStream output = new PrintStream(outputFile);

			v.execute(ct, guideFile);

			Collection<NuModule> modules = v.getModules();
			NuModule main = v.getMain();
			
			for(NuModule module : modules)
				output.println(module.toString());
			
			output.println(main.toString(true));
			output.close();
		}
		catch(FileNotFoundException e) {
			throw new Error("Output File Not Found");
		}
	}

}