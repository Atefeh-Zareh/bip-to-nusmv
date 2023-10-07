/*     */ package ujf.verimag.bip.Core.Priorities.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
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
/*     */ import ujf.verimag.bip.Core.Priorities.ConnectorTypeReference;
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesFactory;
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesPackage;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityElement;
/*     */ import ujf.verimag.bip.Core.Priorities.PriorityRule;
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
/*     */ 
/*     */ public class PrioritiesPackageImpl
/*     */   extends EPackageImpl
/*     */   implements PrioritiesPackage
/*     */ {
/*  70 */   private EClass priorityRuleEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private EClass connectorTypeReferenceEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   private EClass priorityElementEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PrioritiesPackageImpl() {
/* 103 */     super("http:///ujf/verimag/bip/Core/Priorities.ecore", (EFactory)PrioritiesFactory.eINSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     this.isCreated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 305 */     this.isInitialized = false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isInited = false;
/*     */   
/*     */   private boolean isCreated;
/*     */   
/*     */   private boolean isInitialized;
/*     */   
/*     */   public void initializePackageContents() {
/* 316 */     if (this.isInitialized)
/* 317 */       return;  this.isInitialized = true;
/*     */ 
/*     */     
/* 320 */     setName("Priorities");
/* 321 */     setNsPrefix("ujf.verimag.bip.Core.Priorities");
/* 322 */     setNsURI("http:///ujf/verimag/bip/Core/Priorities.ecore");
/*     */ 
/*     */     
/* 325 */     InteractionsPackage theInteractionsPackage = (InteractionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore");
/* 326 */     BehaviorsPackage theBehaviorsPackage = (BehaviorsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 333 */     this.priorityRuleEClass.getESuperTypes().add(theInteractionsPackage.getMultiplicityElement());
/* 334 */     this.connectorTypeReferenceEClass.getESuperTypes().add(getPriorityElement());
/*     */ 
/*     */     
/* 337 */     initEClass(this.priorityRuleEClass, PriorityRule.class, "PriorityRule", false, false, true);
/* 338 */     initEReference(getPriorityRule_CompoundType(), (EClassifier)theBehaviorsPackage.getComponentType(), theBehaviorsPackage.getComponentType_PriorityRule(), "compoundType", null, 1, 1, PriorityRule.class, false, false, true, false, false, false, false, false, false);
/* 339 */     initEReference(getPriorityRule_Guard(), (EClassifier)theBehaviorsPackage.getExpression(), null, "guard", null, 0, 1, PriorityRule.class, false, false, true, true, false, false, false, false, false);
/* 340 */     initEReference(getPriorityRule_Lower(), (EClassifier)getPriorityElement(), null, "lower", null, 1, 1, PriorityRule.class, false, false, true, true, false, false, false, false, false);
/* 341 */     initEReference(getPriorityRule_Greater(), (EClassifier)getPriorityElement(), null, "greater", null, 0, 1, PriorityRule.class, false, false, true, true, false, false, false, false, false);
/*     */     
/* 343 */     initEClass(this.connectorTypeReferenceEClass, ConnectorTypeReference.class, "ConnectorTypeReference", false, false, true);
/* 344 */     initEReference(getConnectorTypeReference_Target(), (EClassifier)theInteractionsPackage.getConnectorType(), null, "target", null, 1, 1, ConnectorTypeReference.class, false, false, true, false, true, false, false, false, false);
/*     */     
/* 346 */     initEClass(this.priorityElementEClass, PriorityElement.class, "PriorityElement", true, false, true);
/*     */ 
/*     */     
/* 349 */     createResource("http:///ujf/verimag/bip/Core/Priorities.ecore");
/*     */   }
/*     */   
/*     */   public static PrioritiesPackage init() {
/*     */     if (isInited)
/*     */       return (PrioritiesPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore"); 
/*     */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/Priorities.ecore") : new PrioritiesPackageImpl();
/*     */     isInited = true;
/*     */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") : (ModulesPackageImpl)ModulesPackage.eINSTANCE;
/*     */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") : (BehaviorsPackageImpl)BehaviorsPackage.eINSTANCE;
/*     */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") : (InteractionsPackageImpl)InteractionsPackage.eINSTANCE;
/*     */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : (PortExpressionsPackageImpl)PortExpressionsPackage.eINSTANCE;
/*     */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : (ActionsPackageImpl)ActionsPackage.eINSTANCE;
/*     */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : (ExpressionsPackageImpl)ExpressionsPackage.eINSTANCE;
/*     */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") : (TimePackageImpl)TimePackage.eINSTANCE;
/*     */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") : (ContractsPackageImpl)ContractsPackage.eINSTANCE;
/*     */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") : (TraceabilityPackageImpl)TraceabilityPackage.eINSTANCE;
/*     */     thePrioritiesPackage.createPackageContents();
/*     */     theModulesPackage.createPackageContents();
/*     */     theBehaviorsPackage.createPackageContents();
/*     */     theInteractionsPackage.createPackageContents();
/*     */     thePortExpressionsPackage.createPackageContents();
/*     */     theActionsPackage.createPackageContents();
/*     */     theExpressionsPackage.createPackageContents();
/*     */     theTimePackage.createPackageContents();
/*     */     theContractsPackage.createPackageContents();
/*     */     theTraceabilityPackage.createPackageContents();
/*     */     thePrioritiesPackage.initializePackageContents();
/*     */     theModulesPackage.initializePackageContents();
/*     */     theBehaviorsPackage.initializePackageContents();
/*     */     theInteractionsPackage.initializePackageContents();
/*     */     thePortExpressionsPackage.initializePackageContents();
/*     */     theActionsPackage.initializePackageContents();
/*     */     theExpressionsPackage.initializePackageContents();
/*     */     theTimePackage.initializePackageContents();
/*     */     theContractsPackage.initializePackageContents();
/*     */     theTraceabilityPackage.initializePackageContents();
/*     */     thePrioritiesPackage.freeze();
/*     */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Core/Priorities.ecore", thePrioritiesPackage);
/*     */     return thePrioritiesPackage;
/*     */   }
/*     */   
/*     */   public EClass getPriorityRule() {
/*     */     return this.priorityRuleEClass;
/*     */   }
/*     */   
/*     */   public EReference getPriorityRule_CompoundType() {
/*     */     return (EReference)this.priorityRuleEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getPriorityRule_Guard() {
/*     */     return (EReference)this.priorityRuleEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EReference getPriorityRule_Lower() {
/*     */     return (EReference)this.priorityRuleEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EReference getPriorityRule_Greater() {
/*     */     return (EReference)this.priorityRuleEClass.getEStructuralFeatures().get(3);
/*     */   }
/*     */   
/*     */   public EClass getConnectorTypeReference() {
/*     */     return this.connectorTypeReferenceEClass;
/*     */   }
/*     */   
/*     */   public EReference getConnectorTypeReference_Target() {
/*     */     return (EReference)this.connectorTypeReferenceEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EClass getPriorityElement() {
/*     */     return this.priorityElementEClass;
/*     */   }
/*     */   
/*     */   public PrioritiesFactory getPrioritiesFactory() {
/*     */     return (PrioritiesFactory)getEFactoryInstance();
/*     */   }
/*     */   
/*     */   public void createPackageContents() {
/*     */     if (this.isCreated)
/*     */       return; 
/*     */     this.isCreated = true;
/*     */     this.priorityRuleEClass = createEClass(0);
/*     */     createEReference(this.priorityRuleEClass, 3);
/*     */     createEReference(this.priorityRuleEClass, 4);
/*     */     createEReference(this.priorityRuleEClass, 5);
/*     */     createEReference(this.priorityRuleEClass, 6);
/*     */     this.connectorTypeReferenceEClass = createEClass(1);
/*     */     createEReference(this.connectorTypeReferenceEClass, 0);
/*     */     this.priorityElementEClass = createEClass(2);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Priorities\impl\PrioritiesPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */