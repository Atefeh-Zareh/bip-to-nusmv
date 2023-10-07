/*     */ package ujf.verimag.bip.Extra.Time.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import ujf.verimag.bip.Extra.Time.TimeFactory;
/*     */ import ujf.verimag.bip.Extra.Time.TimePackage;
/*     */ import ujf.verimag.bip.Extra.Time.TimeReset;
/*     */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*     */ import ujf.verimag.bip.Extra.Time.TimedConstraint;
/*     */ import ujf.verimag.bip.Extra.Time.TimedVariable;
/*     */ import ujf.verimag.bip.Extra.Time.UrgencyKind;
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
/*     */ public class TimeFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements TimeFactory
/*     */ {
/*     */   public static TimeFactory init() {
/*     */     try {
/*  38 */       TimeFactory theTimeFactory = (TimeFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Extra/Time.ecore");
/*  39 */       if (theTimeFactory != null)
/*     */       {
/*  41 */         return theTimeFactory;
/*     */       }
/*     */     }
/*  44 */     catch (Exception exception) {
/*     */       
/*  46 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  48 */     return new TimeFactoryImpl();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject create(EClass eClass) {
/*  70 */     switch (eClass.getClassifierID()) {
/*     */       case 0:
/*  72 */         return (EObject)createTimedVariable();
/*  73 */       case 1: return (EObject)createTimeSpecification();
/*  74 */       case 2: return (EObject)createTimeReset();
/*  75 */       case 3: return (EObject)createTimedConstraint();
/*     */     } 
/*  77 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
/*     */   public Object createFromString(EDataType eDataType, String initialValue) {
/*  89 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 4:
/*  92 */         return createUrgencyKindFromString(eDataType, initialValue);
/*     */     } 
/*  94 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
/*     */   public String convertToString(EDataType eDataType, Object instanceValue) {
/* 106 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 4:
/* 109 */         return convertUrgencyKindToString(eDataType, instanceValue);
/*     */     } 
/* 111 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimedVariable createTimedVariable() {
/* 122 */     TimedVariableImpl timedVariable = new TimedVariableImpl();
/* 123 */     return timedVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeSpecification createTimeSpecification() {
/* 133 */     TimeSpecificationImpl timeSpecification = new TimeSpecificationImpl();
/* 134 */     return timeSpecification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeReset createTimeReset() {
/* 144 */     TimeResetImpl timeReset = new TimeResetImpl();
/* 145 */     return timeReset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimedConstraint createTimedConstraint() {
/* 155 */     TimedConstraintImpl timedConstraint = new TimedConstraintImpl();
/* 156 */     return timedConstraint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UrgencyKind createUrgencyKindFromString(EDataType eDataType, String initialValue) {
/* 166 */     UrgencyKind result = UrgencyKind.get(initialValue);
/* 167 */     if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); 
/* 168 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertUrgencyKindToString(EDataType eDataType, Object instanceValue) {
/* 178 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimePackage getTimePackage() {
/* 188 */     return (TimePackage)getEPackage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static TimePackage getPackage() {
/* 200 */     return TimePackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Time\impl\TimeFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */