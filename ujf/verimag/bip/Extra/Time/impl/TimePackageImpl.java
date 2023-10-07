/*     */ package ujf.verimag.bip.Extra.Time.impl;
/*     */ 
/*     */ import org.eclipse.emf.common.util.Enumerator;
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EEnum;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.EReference;
/*     */ import org.eclipse.emf.ecore.impl.EPackageImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.impl.ActionsPackageImpl;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.impl.ExpressionsPackageImpl;
/*     */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*     */ import ujf.verimag.bip.Core.Behaviors.impl.BehaviorsPackageImpl;
/*     */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*     */ import ujf.verimag.bip.Core.Interactions.impl.InteractionsPackageImpl;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*     */ import ujf.verimag.bip.Core.Modules.impl.ModulesPackageImpl;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.PortExpressions.impl.PortExpressionsPackageImpl;
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesPackage;
/*     */ import ujf.verimag.bip.Core.Priorities.impl.PrioritiesPackageImpl;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractsPackage;
/*     */ import ujf.verimag.bip.Extra.Contracts.impl.ContractsPackageImpl;
/*     */ import ujf.verimag.bip.Extra.Time.TimeFactory;
/*     */ import ujf.verimag.bip.Extra.Time.TimePackage;
/*     */ import ujf.verimag.bip.Extra.Time.TimeReset;
/*     */ import ujf.verimag.bip.Extra.Time.TimeSpecification;
/*     */ import ujf.verimag.bip.Extra.Time.TimedConstraint;
/*     */ import ujf.verimag.bip.Extra.Time.TimedVariable;
/*     */ import ujf.verimag.bip.Extra.Time.UrgencyKind;
/*     */ import ujf.verimag.bip.Extra.Traceability.TraceabilityPackage;
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
/*     */ public class TimePackageImpl
/*     */   extends EPackageImpl
/*     */   implements TimePackage
/*     */ {
/*  74 */   private EClass timedVariableEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private EClass timeSpecificationEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private EClass timeResetEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   private EClass timedConstraintEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   private EEnum urgencyKindEEnum = null;
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
/*     */   private TimePackageImpl() {
/* 121 */     super("http:///ujf/verimag/bip/Extra/Time.ecore", (EFactory)TimeFactory.eINSTANCE);
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
/* 341 */     this.isCreated = false;
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
/* 381 */     this.isInitialized = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isInited = false;
/*     */   
/*     */   private boolean isCreated;
/*     */ 
/*     */   
/*     */   public void initializePackageContents() {
/* 392 */     if (this.isInitialized)
/* 393 */       return;  this.isInitialized = true;
/*     */ 
/*     */     
/* 396 */     setName("Time");
/* 397 */     setNsPrefix("ujf.verimag.bip.Extra.Time");
/* 398 */     setNsURI("http:///ujf/verimag/bip/Extra/Time.ecore");
/*     */ 
/*     */     
/* 401 */     BehaviorsPackage theBehaviorsPackage = (BehaviorsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/* 402 */     ExpressionsPackage theExpressionsPackage = (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 409 */     this.timedVariableEClass.getESuperTypes().add(theBehaviorsPackage.getVariable());
/*     */ 
/*     */     
/* 412 */     initEClass(this.timedVariableEClass, TimedVariable.class, "TimedVariable", false, false, true);
/*     */     
/* 414 */     initEClass(this.timeSpecificationEClass, TimeSpecification.class, "TimeSpecification", false, false, true);
/* 415 */     initEAttribute(getTimeSpecification_Urgency(), (EClassifier)getUrgencyKind(), "urgency", null, 1, 1, TimeSpecification.class, false, false, true, false, false, false, false, false);
/* 416 */     initEReference(getTimeSpecification_Transition(), (EClassifier)theBehaviorsPackage.getAbstractTransition(), theBehaviorsPackage.getAbstractTransition_TimeSpecification(), "transition", null, 1, 1, TimeSpecification.class, false, false, true, false, false, false, false, false, false);
/* 417 */     initEReference(getTimeSpecification_TimedConstraint(), (EClassifier)getTimedConstraint(), getTimedConstraint_TimeSpecification(), "timedConstraint", null, 0, -1, TimeSpecification.class, false, false, true, true, false, false, true, false, false);
/*     */     
/* 419 */     initEClass(this.timeResetEClass, TimeReset.class, "TimeReset", false, false, true);
/* 420 */     initEReference(getTimeReset_Clock(), (EClassifier)theExpressionsPackage.getVariableReference(), null, "clock", null, 0, -1, TimeReset.class, false, false, true, true, false, false, true, false, false);
/*     */     
/* 422 */     initEClass(this.timedConstraintEClass, TimedConstraint.class, "TimedConstraint", false, false, true);
/* 423 */     initEReference(getTimedConstraint_Clock(), (EClassifier)theExpressionsPackage.getVariableReference(), null, "clock", null, 1, 1, TimedConstraint.class, false, false, true, true, false, false, false, false, false);
/* 424 */     initEReference(getTimedConstraint_LowBound(), (EClassifier)theBehaviorsPackage.getExpression(), null, "lowBound", null, 0, 1, TimedConstraint.class, false, false, true, true, false, false, false, false, false);
/* 425 */     initEReference(getTimedConstraint_HighBound(), (EClassifier)theBehaviorsPackage.getExpression(), null, "highBound", null, 0, 1, TimedConstraint.class, false, false, true, true, false, false, false, false, false);
/* 426 */     initEReference(getTimedConstraint_TimeSpecification(), (EClassifier)getTimeSpecification(), getTimeSpecification_TimedConstraint(), "timeSpecification", null, 1, 1, TimedConstraint.class, false, false, true, false, false, false, false, false, false);
/*     */ 
/*     */     
/* 429 */     initEEnum(this.urgencyKindEEnum, UrgencyKind.class, "UrgencyKind");
/* 430 */     addEEnumLiteral(this.urgencyKindEEnum, (Enumerator)UrgencyKind.EAGER);
/* 431 */     addEEnumLiteral(this.urgencyKindEEnum, (Enumerator)UrgencyKind.DELAYABLE);
/* 432 */     addEEnumLiteral(this.urgencyKindEEnum, (Enumerator)UrgencyKind.LAZY);
/*     */ 
/*     */     
/* 435 */     createResource("http:///ujf/verimag/bip/Extra/Time.ecore");
/*     */   }
/*     */   
/*     */   private boolean isInitialized;
/*     */   
/*     */   public static TimePackage init() {
/*     */     if (isInited)
/*     */       return (TimePackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore"); 
/*     */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Extra/Time.ecore") : new TimePackageImpl();
/*     */     isInited = true;
/*     */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") : (ModulesPackageImpl)ModulesPackage.eINSTANCE;
/*     */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") : (BehaviorsPackageImpl)BehaviorsPackage.eINSTANCE;
/*     */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") : (InteractionsPackageImpl)InteractionsPackage.eINSTANCE;
/*     */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") : (PrioritiesPackageImpl)PrioritiesPackage.eINSTANCE;
/*     */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : (PortExpressionsPackageImpl)PortExpressionsPackage.eINSTANCE;
/*     */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : (ActionsPackageImpl)ActionsPackage.eINSTANCE;
/*     */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : (ExpressionsPackageImpl)ExpressionsPackage.eINSTANCE;
/*     */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") : (ContractsPackageImpl)ContractsPackage.eINSTANCE;
/*     */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") : (TraceabilityPackageImpl)TraceabilityPackage.eINSTANCE;
/*     */     theTimePackage.createPackageContents();
/*     */     theModulesPackage.createPackageContents();
/*     */     theBehaviorsPackage.createPackageContents();
/*     */     theInteractionsPackage.createPackageContents();
/*     */     thePrioritiesPackage.createPackageContents();
/*     */     thePortExpressionsPackage.createPackageContents();
/*     */     theActionsPackage.createPackageContents();
/*     */     theExpressionsPackage.createPackageContents();
/*     */     theContractsPackage.createPackageContents();
/*     */     theTraceabilityPackage.createPackageContents();
/*     */     theTimePackage.initializePackageContents();
/*     */     theModulesPackage.initializePackageContents();
/*     */     theBehaviorsPackage.initializePackageContents();
/*     */     theInteractionsPackage.initializePackageContents();
/*     */     thePrioritiesPackage.initializePackageContents();
/*     */     thePortExpressionsPackage.initializePackageContents();
/*     */     theActionsPackage.initializePackageContents();
/*     */     theExpressionsPackage.initializePackageContents();
/*     */     theContractsPackage.initializePackageContents();
/*     */     theTraceabilityPackage.initializePackageContents();
/*     */     theTimePackage.freeze();
/*     */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Extra/Time.ecore", theTimePackage);
/*     */     return theTimePackage;
/*     */   }
/*     */   
/*     */   public EClass getTimedVariable() {
/*     */     return this.timedVariableEClass;
/*     */   }
/*     */   
/*     */   public EClass getTimeSpecification() {
/*     */     return this.timeSpecificationEClass;
/*     */   }
/*     */   
/*     */   public EAttribute getTimeSpecification_Urgency() {
/*     */     return (EAttribute)this.timeSpecificationEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getTimeSpecification_Transition() {
/*     */     return (EReference)this.timeSpecificationEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EReference getTimeSpecification_TimedConstraint() {
/*     */     return (EReference)this.timeSpecificationEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EClass getTimeReset() {
/*     */     return this.timeResetEClass;
/*     */   }
/*     */   
/*     */   public EReference getTimeReset_Clock() {
/*     */     return (EReference)this.timeResetEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EClass getTimedConstraint() {
/*     */     return this.timedConstraintEClass;
/*     */   }
/*     */   
/*     */   public EReference getTimedConstraint_Clock() {
/*     */     return (EReference)this.timedConstraintEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getTimedConstraint_LowBound() {
/*     */     return (EReference)this.timedConstraintEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EReference getTimedConstraint_HighBound() {
/*     */     return (EReference)this.timedConstraintEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EReference getTimedConstraint_TimeSpecification() {
/*     */     return (EReference)this.timedConstraintEClass.getEStructuralFeatures().get(3);
/*     */   }
/*     */   
/*     */   public EEnum getUrgencyKind() {
/*     */     return this.urgencyKindEEnum;
/*     */   }
/*     */   
/*     */   public TimeFactory getTimeFactory() {
/*     */     return (TimeFactory)getEFactoryInstance();
/*     */   }
/*     */   
/*     */   public void createPackageContents() {
/*     */     if (this.isCreated)
/*     */       return; 
/*     */     this.isCreated = true;
/*     */     this.timedVariableEClass = createEClass(0);
/*     */     this.timeSpecificationEClass = createEClass(1);
/*     */     createEAttribute(this.timeSpecificationEClass, 0);
/*     */     createEReference(this.timeSpecificationEClass, 1);
/*     */     createEReference(this.timeSpecificationEClass, 2);
/*     */     this.timeResetEClass = createEClass(2);
/*     */     createEReference(this.timeResetEClass, 0);
/*     */     this.timedConstraintEClass = createEClass(3);
/*     */     createEReference(this.timedConstraintEClass, 0);
/*     */     createEReference(this.timedConstraintEClass, 1);
/*     */     createEReference(this.timedConstraintEClass, 2);
/*     */     createEReference(this.timedConstraintEClass, 3);
/*     */     this.urgencyKindEEnum = createEEnum(4);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Time\impl\TimePackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */