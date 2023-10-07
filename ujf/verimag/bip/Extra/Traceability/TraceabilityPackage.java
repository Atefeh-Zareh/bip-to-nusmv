/*     */ package ujf.verimag.bip.Extra.Traceability;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import ujf.verimag.bip.Extra.Traceability.impl.TraceabilityPackageImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface TraceabilityPackage
/*     */   extends EPackage
/*     */ {
/*     */   public static final String eNAME = "Traceability";
/*     */   public static final String eNS_URI = "http:///ujf/verimag/bip/Extra/Traceability.ecore";
/*     */   public static final String eNS_PREFIX = "ujf.verimag.bip.Extra.Traceability";
/*  60 */   public static final TraceabilityPackage eINSTANCE = TraceabilityPackageImpl.init();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TRACEABLE_ELEMENT = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TRACEABLE_ELEMENT__START_SOURCE_LINE = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TRACEABLE_ELEMENT_FEATURE_COUNT = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EClass getTraceableElement();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EAttribute getTraceableElement_StartSourceLine();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TraceabilityFactory getTraceabilityFactory();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface Literals
/*     */   {
/* 143 */     public static final EClass TRACEABLE_ELEMENT = TraceabilityPackage.eINSTANCE.getTraceableElement();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     public static final EAttribute TRACEABLE_ELEMENT__START_SOURCE_LINE = TraceabilityPackage.eINSTANCE.getTraceableElement_StartSourceLine();
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Traceability\TraceabilityPackage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */