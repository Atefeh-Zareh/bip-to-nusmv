/*    */ package ujf.verimag.bip.Extra.Traceability.impl;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EClass;
/*    */ import org.eclipse.emf.ecore.EObject;
/*    */ import org.eclipse.emf.ecore.EPackage;
/*    */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*    */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*    */ import ujf.verimag.bip.Extra.Traceability.TraceabilityFactory;
/*    */ import ujf.verimag.bip.Extra.Traceability.TraceabilityPackage;
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
/*    */ 
/*    */ 
/*    */ public class TraceabilityFactoryImpl
/*    */   extends EFactoryImpl
/*    */   implements TraceabilityFactory
/*    */ {
/*    */   public static TraceabilityFactory init() {
/*    */     try {
/* 37 */       TraceabilityFactory theTraceabilityFactory = (TraceabilityFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Extra/Traceability.ecore");
/* 38 */       if (theTraceabilityFactory != null)
/*    */       {
/* 40 */         return theTraceabilityFactory;
/*    */       }
/*    */     }
/* 43 */     catch (Exception exception) {
/*    */       
/* 45 */       EcorePlugin.INSTANCE.log(exception);
/*    */     } 
/* 47 */     return new TraceabilityFactoryImpl();
/*    */   }
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
/*    */   public EObject create(EClass eClass) {
/* 69 */     switch (eClass.getClassifierID()) {
/*    */     
/*    */     } 
/* 72 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TraceabilityPackage getTraceabilityPackage() {
/* 83 */     return (TraceabilityPackage)getEPackage();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public static TraceabilityPackage getPackage() {
/* 95 */     return TraceabilityPackage.eINSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Traceability\impl\TraceabilityFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */