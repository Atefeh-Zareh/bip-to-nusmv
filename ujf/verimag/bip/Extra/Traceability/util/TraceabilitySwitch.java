/*     */ package ujf.verimag.bip.Extra.Traceability.util;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Extra.Traceability.TraceabilityPackage;
/*     */ import ujf.verimag.bip.Extra.Traceability.TraceableElement;
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
/*     */ public class TraceabilitySwitch<T>
/*     */ {
/*     */   protected static TraceabilityPackage modelPackage;
/*     */   
/*     */   public TraceabilitySwitch() {
/*  47 */     if (modelPackage == null)
/*     */     {
/*  49 */       modelPackage = TraceabilityPackage.eINSTANCE;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T doSwitch(EObject theEObject) {
/*  62 */     return doSwitch(theEObject.eClass(), theEObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected T doSwitch(EClass theEClass, EObject theEObject) {
/*  74 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  76 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  80 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  81 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject);
/*     */   }
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
/*     */   protected T doSwitch(int classifierID, EObject theEObject) {
/*     */     TraceableElement traceableElement;
/*     */     T result;
/*  97 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 101 */         traceableElement = (TraceableElement)theEObject;
/* 102 */         result = caseTraceableElement(traceableElement);
/* 103 */         if (result == null) result = defaultCase(theEObject); 
/* 104 */         return result;
/*     */     } 
/* 106 */     return defaultCase(theEObject);
/*     */   }
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
/*     */   public T caseTraceableElement(TraceableElement object) {
/* 123 */     return null;
/*     */   }
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
/*     */   public T defaultCase(EObject object) {
/* 139 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Traceabilit\\util\TraceabilitySwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */