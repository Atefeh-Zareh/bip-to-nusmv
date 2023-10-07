/*    */ package ujf.verimag.bip.Core.Modules;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Core.Modules.impl.ModulesFactoryImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ModulesFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final ModulesFactory eINSTANCE = ModulesFactoryImpl.init();
/*    */   
/*    */   Package createPackage();
/*    */   
/*    */   System createSystem();
/*    */   
/*    */   Root createRoot();
/*    */   
/*    */   OpaqueElement createOpaqueElement();
/*    */   
/*    */   ModulesPackage getModulesPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\ModulesFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */