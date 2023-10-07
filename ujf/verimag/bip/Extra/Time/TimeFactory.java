/*    */ package ujf.verimag.bip.Extra.Time;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Extra.Time.impl.TimeFactoryImpl;
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
/*    */ public interface TimeFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final TimeFactory eINSTANCE = TimeFactoryImpl.init();
/*    */   
/*    */   TimedVariable createTimedVariable();
/*    */   
/*    */   TimeSpecification createTimeSpecification();
/*    */   
/*    */   TimeReset createTimeReset();
/*    */   
/*    */   TimedConstraint createTimedConstraint();
/*    */   
/*    */   TimePackage getTimePackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Time\TimeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */