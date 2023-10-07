/*    */ package ujf.verimag.bip.Extra.Traceability;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Extra.Traceability.impl.TraceabilityFactoryImpl;
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
/*    */ public interface TraceabilityFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final TraceabilityFactory eINSTANCE = TraceabilityFactoryImpl.init();
/*    */   
/*    */   TraceabilityPackage getTraceabilityPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Traceability\TraceabilityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */