/*     */ package ujf.verimag.bip.Core.ActionLanguage.Actions.impl;
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
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignType;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.AssignmentAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.CompositeAction;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Actions.IfAction;
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
/*     */ import ujf.verimag.bip.Extra.Time.TimePackage;
/*     */ import ujf.verimag.bip.Extra.Time.impl.TimePackageImpl;
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
/*     */ public class ActionsPackageImpl
/*     */   extends EPackageImpl
/*     */   implements ActionsPackage
/*     */ {
/*  73 */   private EClass compositeActionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   private EClass ifActionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private EClass assignmentActionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   private EEnum assignTypeEEnum = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ActionsPackageImpl() {
/* 113 */     super("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore", (EFactory)ActionsFactory.eINSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 313 */     this.isCreated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 350 */     this.isInitialized = false;
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
/* 361 */     if (this.isInitialized)
/* 362 */       return;  this.isInitialized = true;
/*     */ 
/*     */     
/* 365 */     setName("Actions");
/* 366 */     setNsPrefix("ujf.verimag.bip.Core.ActionLanguage.Actions");
/* 367 */     setNsURI("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore");
/*     */ 
/*     */     
/* 370 */     BehaviorsPackage theBehaviorsPackage = (BehaviorsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/* 371 */     ExpressionsPackage theExpressionsPackage = (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 378 */     this.compositeActionEClass.getESuperTypes().add(theBehaviorsPackage.getAction());
/* 379 */     this.ifActionEClass.getESuperTypes().add(theBehaviorsPackage.getAction());
/* 380 */     this.assignmentActionEClass.getESuperTypes().add(theBehaviorsPackage.getAction());
/*     */ 
/*     */     
/* 383 */     initEClass(this.compositeActionEClass, CompositeAction.class, "CompositeAction", false, false, true);
/* 384 */     initEReference(getCompositeAction_Content(), (EClassifier)theBehaviorsPackage.getAction(), null, "content", null, 0, -1, CompositeAction.class, false, false, true, true, false, false, true, false, true);
/*     */     
/* 386 */     initEClass(this.ifActionEClass, IfAction.class, "IfAction", false, false, true);
/* 387 */     initEReference(getIfAction_IfCase(), (EClassifier)theBehaviorsPackage.getAction(), null, "ifCase", null, 1, 1, IfAction.class, false, false, true, true, false, false, false, false, false);
/* 388 */     initEReference(getIfAction_ElseCase(), (EClassifier)theBehaviorsPackage.getAction(), null, "elseCase", null, 0, 1, IfAction.class, false, false, true, true, false, false, false, false, false);
/* 389 */     initEReference(getIfAction_Condition(), (EClassifier)theBehaviorsPackage.getExpression(), null, "condition", null, 1, 1, IfAction.class, false, false, true, true, false, false, false, false, false);
/*     */     
/* 391 */     initEClass(this.assignmentActionEClass, AssignmentAction.class, "AssignmentAction", false, false, true);
/* 392 */     initEReference(getAssignmentAction_AssignedTarget(), (EClassifier)theExpressionsPackage.getDataReference(), null, "assignedTarget", null, 1, 1, AssignmentAction.class, false, false, true, true, false, false, false, false, false);
/* 393 */     initEReference(getAssignmentAction_AssignedValue(), (EClassifier)theBehaviorsPackage.getExpression(), null, "assignedValue", null, 1, 1, AssignmentAction.class, false, false, true, true, false, false, false, false, false);
/* 394 */     initEAttribute(getAssignmentAction_Type(), (EClassifier)getAssignType(), "type", null, 1, 1, AssignmentAction.class, false, false, true, false, false, false, false, false);
/*     */ 
/*     */     
/* 397 */     initEEnum(this.assignTypeEEnum, AssignType.class, "AssignType");
/* 398 */     addEEnumLiteral(this.assignTypeEEnum, (Enumerator)AssignType.ASSIGN);
/* 399 */     addEEnumLiteral(this.assignTypeEEnum, (Enumerator)AssignType.PLUS_ASSIGN);
/* 400 */     addEEnumLiteral(this.assignTypeEEnum, (Enumerator)AssignType.MINUS_ASSIGN);
/* 401 */     addEEnumLiteral(this.assignTypeEEnum, (Enumerator)AssignType.MULT_ASSIGN);
/* 402 */     addEEnumLiteral(this.assignTypeEEnum, (Enumerator)AssignType.DIV_ASSIGN);
/* 403 */     addEEnumLiteral(this.assignTypeEEnum, (Enumerator)AssignType.MOD_ASSIGN);
/*     */ 
/*     */     
/* 406 */     createResource("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore");
/*     */   }
/*     */   
/*     */   private boolean isInitialized;
/*     */   
/*     */   public static ActionsPackage init() {
/*     */     if (isInited)
/*     */       return (ActionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore"); 
/*     */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : new ActionsPackageImpl();
/*     */     isInited = true;
/*     */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") : (ModulesPackageImpl)ModulesPackage.eINSTANCE;
/*     */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") : (BehaviorsPackageImpl)BehaviorsPackage.eINSTANCE;
/*     */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") : (InteractionsPackageImpl)InteractionsPackage.eINSTANCE;
/*     */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") : (PrioritiesPackageImpl)PrioritiesPackage.eINSTANCE;
/*     */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : (PortExpressionsPackageImpl)PortExpressionsPackage.eINSTANCE;
/*     */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : (ExpressionsPackageImpl)ExpressionsPackage.eINSTANCE;
/*     */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") : (TimePackageImpl)TimePackage.eINSTANCE;
/*     */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") : (ContractsPackageImpl)ContractsPackage.eINSTANCE;
/*     */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") : (TraceabilityPackageImpl)TraceabilityPackage.eINSTANCE;
/*     */     theActionsPackage.createPackageContents();
/*     */     theModulesPackage.createPackageContents();
/*     */     theBehaviorsPackage.createPackageContents();
/*     */     theInteractionsPackage.createPackageContents();
/*     */     thePrioritiesPackage.createPackageContents();
/*     */     thePortExpressionsPackage.createPackageContents();
/*     */     theExpressionsPackage.createPackageContents();
/*     */     theTimePackage.createPackageContents();
/*     */     theContractsPackage.createPackageContents();
/*     */     theTraceabilityPackage.createPackageContents();
/*     */     theActionsPackage.initializePackageContents();
/*     */     theModulesPackage.initializePackageContents();
/*     */     theBehaviorsPackage.initializePackageContents();
/*     */     theInteractionsPackage.initializePackageContents();
/*     */     thePrioritiesPackage.initializePackageContents();
/*     */     thePortExpressionsPackage.initializePackageContents();
/*     */     theExpressionsPackage.initializePackageContents();
/*     */     theTimePackage.initializePackageContents();
/*     */     theContractsPackage.initializePackageContents();
/*     */     theTraceabilityPackage.initializePackageContents();
/*     */     theActionsPackage.freeze();
/*     */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore", theActionsPackage);
/*     */     return theActionsPackage;
/*     */   }
/*     */   
/*     */   public EClass getCompositeAction() {
/*     */     return this.compositeActionEClass;
/*     */   }
/*     */   
/*     */   public EReference getCompositeAction_Content() {
/*     */     return (EReference)this.compositeActionEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EClass getIfAction() {
/*     */     return this.ifActionEClass;
/*     */   }
/*     */   
/*     */   public EReference getIfAction_IfCase() {
/*     */     return (EReference)this.ifActionEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getIfAction_ElseCase() {
/*     */     return (EReference)this.ifActionEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EReference getIfAction_Condition() {
/*     */     return (EReference)this.ifActionEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EClass getAssignmentAction() {
/*     */     return this.assignmentActionEClass;
/*     */   }
/*     */   
/*     */   public EReference getAssignmentAction_AssignedTarget() {
/*     */     return (EReference)this.assignmentActionEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getAssignmentAction_AssignedValue() {
/*     */     return (EReference)this.assignmentActionEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EAttribute getAssignmentAction_Type() {
/*     */     return (EAttribute)this.assignmentActionEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EEnum getAssignType() {
/*     */     return this.assignTypeEEnum;
/*     */   }
/*     */   
/*     */   public ActionsFactory getActionsFactory() {
/*     */     return (ActionsFactory)getEFactoryInstance();
/*     */   }
/*     */   
/*     */   public void createPackageContents() {
/*     */     if (this.isCreated)
/*     */       return; 
/*     */     this.isCreated = true;
/*     */     this.compositeActionEClass = createEClass(0);
/*     */     createEReference(this.compositeActionEClass, 0);
/*     */     this.ifActionEClass = createEClass(1);
/*     */     createEReference(this.ifActionEClass, 0);
/*     */     createEReference(this.ifActionEClass, 1);
/*     */     createEReference(this.ifActionEClass, 2);
/*     */     this.assignmentActionEClass = createEClass(2);
/*     */     createEReference(this.assignmentActionEClass, 0);
/*     */     createEReference(this.assignmentActionEClass, 1);
/*     */     createEAttribute(this.assignmentActionEClass, 2);
/*     */     this.assignTypeEEnum = createEEnum(3);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Actions\impl\ActionsPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */