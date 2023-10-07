/*     */ package ujf.verimag.bip.Extra.Time.util;
/*     */ 
/*     */ import org.eclipse.emf.common.util.EList;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.Behaviors.DataTypedElement;
/*     */ import ujf.verimag.bip.Core.Behaviors.NamedElement;
/*     */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*     */ import ujf.verimag.bip.Extra.Time.TimePackage;
/*     */ import ujf.verimag.bip.Extra.Time.TimeReset;
/*     */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*     */ import ujf.verimag.bip.Extra.Time.TimedConstraint;
/*     */ import ujf.verimag.bip.Extra.Time.TimedVariable;
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
/*     */ public class TimeSwitch<T>
/*     */ {
/*     */   protected static TimePackage modelPackage;
/*     */   
/*     */   public TimeSwitch() {
/*  51 */     if (modelPackage == null)
/*     */     {
/*  53 */       modelPackage = TimePackage.eINSTANCE;
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
/*  66 */     return doSwitch(theEObject.eClass(), theEObject);
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
/*  78 */     if (theEClass.eContainer() == modelPackage)
/*     */     {
/*  80 */       return doSwitch(theEClass.getClassifierID(), theEObject);
/*     */     }
/*     */ 
/*     */     
/*  84 */     EList<EClass> eList = theEClass.getESuperTypes();
/*  85 */     return eList.isEmpty() ? defaultCase(theEObject) : doSwitch(eList.get(0), theEObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected T doSwitch(int classifierID, EObject theEObject) {
/*     */     TimedVariable timedVariable;
/*     */     TimeSpecification timeSpecification;
/*     */     TimeReset timeReset;
/*     */     TimedConstraint timedConstraint;
/*     */     T result;
/* 101 */     switch (classifierID) {
/*     */ 
/*     */       
/*     */       case 0:
/* 105 */         timedVariable = (TimedVariable)theEObject;
/* 106 */         result = caseTimedVariable(timedVariable);
/* 107 */         if (result == null) result = caseVariable((Variable)timedVariable); 
/* 108 */         if (result == null) result = caseDataTypedElement((DataTypedElement)timedVariable); 
/* 109 */         if (result == null) result = caseNamedElement((NamedElement)timedVariable); 
/* 110 */         if (result == null) result = defaultCase(theEObject); 
/* 111 */         return result;
/*     */ 
/*     */       
/*     */       case 1:
/* 115 */         timeSpecification = (TimeSpecification)theEObject;
/* 116 */         result = caseTimeSpecification(timeSpecification);
/* 117 */         if (result == null) result = defaultCase(theEObject); 
/* 118 */         return result;
/*     */ 
/*     */       
/*     */       case 2:
/* 122 */         timeReset = (TimeReset)theEObject;
/* 123 */         result = caseTimeReset(timeReset);
/* 124 */         if (result == null) result = defaultCase(theEObject); 
/* 125 */         return result;
/*     */ 
/*     */       
/*     */       case 3:
/* 129 */         timedConstraint = (TimedConstraint)theEObject;
/* 130 */         result = caseTimedConstraint(timedConstraint);
/* 131 */         if (result == null) result = defaultCase(theEObject); 
/* 132 */         return result;
/*     */     } 
/* 134 */     return defaultCase(theEObject);
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
/*     */   public T caseTimedVariable(TimedVariable object) {
/* 151 */     return null;
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
/*     */   public T caseTimeSpecification(TimeSpecification object) {
/* 167 */     return null;
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
/*     */   public T caseTimeReset(TimeReset object) {
/* 183 */     return null;
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
/*     */   public T caseTimedConstraint(TimedConstraint object) {
/* 199 */     return null;
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
/*     */   public T caseNamedElement(NamedElement object) {
/* 215 */     return null;
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
/*     */   public T caseDataTypedElement(DataTypedElement object) {
/* 231 */     return null;
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
/*     */   public T caseVariable(Variable object) {
/* 247 */     return null;
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
/* 263 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Tim\\util\TimeSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */