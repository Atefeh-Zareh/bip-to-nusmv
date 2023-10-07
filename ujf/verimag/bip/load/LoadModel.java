/*    */ package ujf.verimag.bip.load;
/*    */ 
/*    */ import org.eclipse.emf.common.util.URI;
/*    */ import org.eclipse.emf.ecore.resource.Resource;
/*    */ import org.eclipse.emf.ecore.resource.ResourceSet;
/*    */ import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
/*    */ import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
/*    */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*    */ import ujf.verimag.bip.Core.Modules.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoadModel
/*    */ {
/* 19 */   ResourceSet resourceSet = (ResourceSet)new ResourceSetImpl();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Module readModel(String modelName) {
/* 30 */     this.resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
/*    */ 
/*    */ 
/*    */     
/* 34 */     BehaviorsPackage bipPackage = BehaviorsPackage.eINSTANCE;
/*    */ 
/*    */     
/* 37 */     Resource resource = this.resourceSet.getResource(URI.createFileURI(modelName), true);
/*    */     
/* 39 */     return (Module)resource.getContents().get(0);
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\load\LoadModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */