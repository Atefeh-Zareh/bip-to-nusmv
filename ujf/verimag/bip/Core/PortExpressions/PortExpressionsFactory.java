/*    */ package ujf.verimag.bip.Core.PortExpressions;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Core.PortExpressions.impl.PortExpressionsFactoryImpl;
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
/*    */ public interface PortExpressionsFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final PortExpressionsFactory eINSTANCE = PortExpressionsFactoryImpl.init();
/*    */   
/*    */   ACFusionNeutral createACFusionNeutral();
/*    */   
/*    */   ACUnionNeutral createACUnionNeutral();
/*    */   
/*    */   ACFusion createACFusion();
/*    */   
/*    */   AIUnionNeutral createAIUnionNeutral();
/*    */   
/*    */   AISynchroNeutral createAISynchroNeutral();
/*    */   
/*    */   AIUnion createAIUnion();
/*    */   
/*    */   ACTyping createACTyping();
/*    */   
/*    */   AISynchro createAISynchro();
/*    */   
/*    */   ACUnion createACUnion();
/*    */   
/*    */   PortExpressionsPackage getPortExpressionsPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\PortExpressions\PortExpressionsFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */