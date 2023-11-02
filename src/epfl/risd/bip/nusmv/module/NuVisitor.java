package epfl.risd.bip.nusmv.module;

import java.util.Collection;
import ujf.verimag.bip.Core.Interactions.CompoundType;

public interface NuVisitor {
	
	public void execute(CompoundType ct, String guideFile);
	
	public Collection<NuModule> getModules();
	
	public NuModule getMain();

}