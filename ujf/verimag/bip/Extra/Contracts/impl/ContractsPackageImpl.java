/*     */ package ujf.verimag.bip.Extra.Contracts.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EAttribute;
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
/*     */ import ujf.verimag.bip.Core.Priorities.PrioritiesPackage;
/*     */ import ujf.verimag.bip.Core.Priorities.impl.PrioritiesPackageImpl;
/*     */ import ujf.verimag.bip.Extra.Contracts.Contract;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractBinding;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractState;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractsFactory;
/*     */ import ujf.verimag.bip.Extra.Contracts.ContractsPackage;
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
/*     */ public class ContractsPackageImpl
/*     */   extends EPackageImpl
/*     */   implements ContractsPackage
/*     */ {
/*  71 */   private EClass contractEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   private EClass contractStateEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private EClass contractBindingEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ContractsPackageImpl() {
/* 104 */     super("http:///ujf/verimag/bip/Extra/Contracts.ecore", (EFactory)ContractsFactory.eINSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     this.isCreated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 317 */     this.isInitialized = false;
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
/* 328 */     if (this.isInitialized)
/* 329 */       return;  this.isInitialized = true;
/*     */ 
/*     */     
/* 332 */     setName("Contracts");
/* 333 */     setNsPrefix("ujf.verimag.bip.Extra.Contracts");
/* 334 */     setNsURI("http:///ujf/verimag/bip/Extra/Contracts.ecore");
/*     */ 
/*     */     
/* 337 */     InteractionsPackage theInteractionsPackage = (InteractionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore");
/* 338 */     BehaviorsPackage theBehaviorsPackage = (BehaviorsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 345 */     this.contractEClass.getESuperTypes().add(theInteractionsPackage.getCompoundType());
/* 346 */     this.contractStateEClass.getESuperTypes().add(theBehaviorsPackage.getState());
/* 347 */     this.contractBindingEClass.getESuperTypes().add(theInteractionsPackage.getExportBinding());
/*     */ 
/*     */     
/* 350 */     initEClass(this.contractEClass, Contract.class, "Contract", false, false, true);
/* 351 */     initEReference(getContract_Promise(), (EClassifier)theInteractionsPackage.getComponent(), null, "promise", null, 1, 1, Contract.class, false, false, true, false, true, false, false, false, false);
/* 352 */     initEReference(getContract_Assume(), (EClassifier)theInteractionsPackage.getComponent(), null, "assume", null, 1, 1, Contract.class, false, false, true, false, true, false, false, false, false);
/* 353 */     initEReference(getContract_Contracted(), (EClassifier)theBehaviorsPackage.getComponentType(), theBehaviorsPackage.getComponentType_Contract(), "contracted", null, 1, 1, Contract.class, false, false, true, false, true, false, false, false, false);
/*     */     
/* 355 */     initEClass(this.contractStateEClass, ContractState.class, "ContractState", false, false, true);
/* 356 */     initEAttribute(getContractState_IsAccepting(), (EClassifier)this.ecorePackage.getEBoolean(), "isAccepting", null, 1, 1, ContractState.class, false, false, true, false, false, false, false, false);
/* 357 */     initEReference(getContractState_Invariant(), (EClassifier)theBehaviorsPackage.getExpression(), null, "invariant", null, 0, 1, ContractState.class, false, false, true, true, false, false, false, false, false);
/*     */     
/* 359 */     initEClass(this.contractBindingEClass, ContractBinding.class, "ContractBinding", false, false, true);
/* 360 */     initEReference(getContractBinding_ContractedPort(), (EClassifier)theBehaviorsPackage.getPort(), null, "contractedPort", null, 1, 1, ContractBinding.class, false, false, true, false, true, false, false, false, false);
/*     */ 
/*     */     
/* 363 */     createResource("http:///ujf/verimag/bip/Extra/Contracts.ecore");
/*     */   }
/*     */   
/*     */   public static ContractsPackage init() {
/*     */     if (isInited)
/*     */       return (ContractsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore"); 
/*     */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Extra/Contracts.ecore") : new ContractsPackageImpl();
/*     */     isInited = true;
/*     */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") : (ModulesPackageImpl)ModulesPackage.eINSTANCE;
/*     */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") : (BehaviorsPackageImpl)BehaviorsPackage.eINSTANCE;
/*     */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") : (InteractionsPackageImpl)InteractionsPackage.eINSTANCE;
/*     */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") : (PrioritiesPackageImpl)PrioritiesPackage.eINSTANCE;
/*     */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : (PortExpressionsPackageImpl)PortExpressionsPackage.eINSTANCE;
/*     */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : (ActionsPackageImpl)ActionsPackage.eINSTANCE;
/*     */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : (ExpressionsPackageImpl)ExpressionsPackage.eINSTANCE;
/*     */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") : (TimePackageImpl)TimePackage.eINSTANCE;
/*     */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") : (TraceabilityPackageImpl)TraceabilityPackage.eINSTANCE;
/*     */     theContractsPackage.createPackageContents();
/*     */     theModulesPackage.createPackageContents();
/*     */     theBehaviorsPackage.createPackageContents();
/*     */     theInteractionsPackage.createPackageContents();
/*     */     thePrioritiesPackage.createPackageContents();
/*     */     thePortExpressionsPackage.createPackageContents();
/*     */     theActionsPackage.createPackageContents();
/*     */     theExpressionsPackage.createPackageContents();
/*     */     theTimePackage.createPackageContents();
/*     */     theTraceabilityPackage.createPackageContents();
/*     */     theContractsPackage.initializePackageContents();
/*     */     theModulesPackage.initializePackageContents();
/*     */     theBehaviorsPackage.initializePackageContents();
/*     */     theInteractionsPackage.initializePackageContents();
/*     */     thePrioritiesPackage.initializePackageContents();
/*     */     thePortExpressionsPackage.initializePackageContents();
/*     */     theActionsPackage.initializePackageContents();
/*     */     theExpressionsPackage.initializePackageContents();
/*     */     theTimePackage.initializePackageContents();
/*     */     theTraceabilityPackage.initializePackageContents();
/*     */     theContractsPackage.freeze();
/*     */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Extra/Contracts.ecore", theContractsPackage);
/*     */     return theContractsPackage;
/*     */   }
/*     */   
/*     */   public EClass getContract() {
/*     */     return this.contractEClass;
/*     */   }
/*     */   
/*     */   public EReference getContract_Promise() {
/*     */     return (EReference)this.contractEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getContract_Assume() {
/*     */     return (EReference)this.contractEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EReference getContract_Contracted() {
/*     */     return (EReference)this.contractEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EClass getContractState() {
/*     */     return this.contractStateEClass;
/*     */   }
/*     */   
/*     */   public EAttribute getContractState_IsAccepting() {
/*     */     return (EAttribute)this.contractStateEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getContractState_Invariant() {
/*     */     return (EReference)this.contractStateEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EClass getContractBinding() {
/*     */     return this.contractBindingEClass;
/*     */   }
/*     */   
/*     */   public EReference getContractBinding_ContractedPort() {
/*     */     return (EReference)this.contractBindingEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public ContractsFactory getContractsFactory() {
/*     */     return (ContractsFactory)getEFactoryInstance();
/*     */   }
/*     */   
/*     */   public void createPackageContents() {
/*     */     if (this.isCreated)
/*     */       return; 
/*     */     this.isCreated = true;
/*     */     this.contractEClass = createEClass(0);
/*     */     createEReference(this.contractEClass, 14);
/*     */     createEReference(this.contractEClass, 15);
/*     */     createEReference(this.contractEClass, 16);
/*     */     this.contractStateEClass = createEClass(1);
/*     */     createEAttribute(this.contractStateEClass, 5);
/*     */     createEReference(this.contractStateEClass, 6);
/*     */     this.contractBindingEClass = createEClass(2);
/*     */     createEReference(this.contractBindingEClass, 3);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Contracts\impl\ContractsPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */