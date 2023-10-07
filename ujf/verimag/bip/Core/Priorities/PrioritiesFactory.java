/*    */ package ujf.verimag.bip.Core.Priorities;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Core.Priorities.impl.PrioritiesFactoryImpl;
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
/*    */ public interface PrioritiesFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final PrioritiesFactory eINSTANCE = PrioritiesFactoryImpl.init();
/*    */   
/*    */   PriorityRule createPriorityRule();
/*    */   
/*    */   ConnectorTypeReference createConnectorTypeReference();
/*    */   
/*    */   PrioritiesPackage getPrioritiesPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Priorities\PrioritiesFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */