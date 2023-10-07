/*     */ package ujf.verimag.bip.Extra.Time.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
/*     */ public class TimeAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static TimePackage modelPackage;
/*     */   
/*     */   public TimeAdapterFactory() {
/*  48 */     if (modelPackage == null)
/*     */     {
/*  50 */       modelPackage = TimePackage.eINSTANCE;
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
/*     */ 
/*     */   
/*     */   public boolean isFactoryForType(Object object) {
/*  65 */     if (object == modelPackage)
/*     */     {
/*  67 */       return true;
/*     */     }
/*  69 */     if (object instanceof EObject)
/*     */     {
/*  71 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   protected TimeSwitch<Adapter> modelSwitch = new TimeSwitch<Adapter>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Adapter caseTimedVariable(TimedVariable object)
/*     */       {
/*  88 */         return TimeAdapterFactory.this.createTimedVariableAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseTimeSpecification(TimeSpecification object) {
/*  93 */         return TimeAdapterFactory.this.createTimeSpecificationAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseTimeReset(TimeReset object) {
/*  98 */         return TimeAdapterFactory.this.createTimeResetAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseTimedConstraint(TimedConstraint object) {
/* 103 */         return TimeAdapterFactory.this.createTimedConstraintAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseNamedElement(NamedElement object) {
/* 108 */         return TimeAdapterFactory.this.createNamedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDataTypedElement(DataTypedElement object) {
/* 113 */         return TimeAdapterFactory.this.createDataTypedElementAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseVariable(Variable object) {
/* 118 */         return TimeAdapterFactory.this.createVariableAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 123 */         return TimeAdapterFactory.this.createEObjectAdapter();
/*     */       }
/*     */     };
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
/*     */   public Adapter createAdapter(Notifier target) {
/* 138 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createTimedVariableAdapter() {
/* 154 */     return null;
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
/*     */   public Adapter createTimeSpecificationAdapter() {
/* 169 */     return null;
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
/*     */   public Adapter createTimeResetAdapter() {
/* 184 */     return null;
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
/*     */   public Adapter createTimedConstraintAdapter() {
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
/*     */   public Adapter createNamedElementAdapter() {
/* 214 */     return null;
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
/*     */   public Adapter createDataTypedElementAdapter() {
/* 229 */     return null;
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
/*     */   public Adapter createVariableAdapter() {
/* 244 */     return null;
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
/*     */   public Adapter createEObjectAdapter() {
/* 257 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Tim\\util\TimeAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */