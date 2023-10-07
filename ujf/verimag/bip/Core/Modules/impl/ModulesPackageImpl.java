/*     */ package ujf.verimag.bip.Core.Modules.impl;
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
/*     */ import ujf.verimag.bip.Core.Modules.Declaration;
/*     */ import ujf.verimag.bip.Core.Modules.Module;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesFactory;
/*     */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*     */ import ujf.verimag.bip.Core.Modules.OpaqueElement;
/*     */ import ujf.verimag.bip.Core.Modules.Package;
/*     */ import ujf.verimag.bip.Core.Modules.Root;
/*     */ import ujf.verimag.bip.Core.Modules.System;
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
/*     */ public class ModulesPackageImpl
/*     */   extends EPackageImpl
/*     */   implements ModulesPackage
/*     */ {
/*  72 */   private EClass moduleEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   private EClass declarationEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   private EClass packageEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   private EClass systemEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   private EClass rootEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   private EClass opaqueElementEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ModulesPackageImpl() {
/* 126 */     super("http:///ujf/verimag/bip/Core/Modules.ecore", (EFactory)ModulesFactory.eINSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 386 */     this.isCreated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 430 */     this.isInitialized = false;
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
/* 441 */     if (this.isInitialized)
/* 442 */       return;  this.isInitialized = true;
/*     */ 
/*     */     
/* 445 */     setName("Modules");
/* 446 */     setNsPrefix("ujf.verimag.bip.Core.Modules");
/* 447 */     setNsURI("http:///ujf/verimag/bip/Core/Modules.ecore");
/*     */ 
/*     */     
/* 450 */     BehaviorsPackage theBehaviorsPackage = (BehaviorsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/* 451 */     ExpressionsPackage theExpressionsPackage = (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore");
/* 452 */     PortExpressionsPackage thePortExpressionsPackage = (PortExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 459 */     this.moduleEClass.getESuperTypes().add(theBehaviorsPackage.getNamedElement());
/* 460 */     this.packageEClass.getESuperTypes().add(getModule());
/* 461 */     this.systemEClass.getESuperTypes().add(getModule());
/* 462 */     this.rootEClass.getESuperTypes().add(theBehaviorsPackage.getNamedElement());
/* 463 */     this.opaqueElementEClass.getESuperTypes().add(theBehaviorsPackage.getAction());
/* 464 */     this.opaqueElementEClass.getESuperTypes().add(theExpressionsPackage.getDataReference());
/* 465 */     this.opaqueElementEClass.getESuperTypes().add(theBehaviorsPackage.getDataType());
/* 466 */     this.opaqueElementEClass.getESuperTypes().add(getDeclaration());
/* 467 */     this.opaqueElementEClass.getESuperTypes().add(thePortExpressionsPackage.getPortExpression());
/*     */ 
/*     */     
/* 470 */     initEClass(this.moduleEClass, Module.class, "Module", true, false, true);
/* 471 */     initEReference(getModule_BipType(), (EClassifier)theBehaviorsPackage.getBipType(), theBehaviorsPackage.getBipType_Module(), "bipType", null, 0, -1, Module.class, false, false, true, true, false, false, true, false, false);
/* 472 */     initEReference(getModule_UsedPackage(), (EClassifier)getPackage(), null, "usedPackage", null, 0, -1, Module.class, false, false, true, false, true, false, false, false, false);
/* 473 */     initEReference(getModule_Declaration(), (EClassifier)getDeclaration(), null, "declaration", null, 0, -1, Module.class, false, false, true, true, false, false, true, false, false);
/* 474 */     initEReference(getModule_DataType(), (EClassifier)theBehaviorsPackage.getDataType(), null, "dataType", null, 0, -1, Module.class, false, false, true, true, false, false, true, false, false);
/* 475 */     initEAttribute(getModule_SrcFileName(), (EClassifier)this.ecorePackage.getEString(), "srcFileName", null, 1, 1, Module.class, false, false, true, false, false, false, false, false);
/*     */     
/* 477 */     initEClass(this.declarationEClass, Declaration.class, "Declaration", true, false, true);
/*     */     
/* 479 */     initEClass(this.packageEClass, Package.class, "Package", false, false, true);
/*     */     
/* 481 */     initEClass(this.systemEClass, System.class, "System", false, false, true);
/* 482 */     initEReference(getSystem_Root(), (EClassifier)getRoot(), getRoot_System(), "root", null, 1, 1, System.class, false, false, true, true, false, false, false, false, false);
/*     */     
/* 484 */     initEClass(this.rootEClass, Root.class, "Root", false, false, true);
/* 485 */     initEReference(getRoot_Type(), (EClassifier)theBehaviorsPackage.getComponentType(), null, "type", null, 1, 1, Root.class, false, false, true, false, true, false, false, false, false);
/* 486 */     initEReference(getRoot_ActualData(), (EClassifier)theBehaviorsPackage.getExpression(), null, "actualData", null, 0, -1, Root.class, false, false, true, true, false, false, true, false, true);
/* 487 */     initEReference(getRoot_System(), (EClassifier)getSystem(), getSystem_Root(), "system", null, 1, 1, Root.class, false, false, true, false, false, false, false, false, false);
/*     */     
/* 489 */     initEClass(this.opaqueElementEClass, OpaqueElement.class, "OpaqueElement", false, false, true);
/* 490 */     initEAttribute(getOpaqueElement_Body(), (EClassifier)this.ecorePackage.getEString(), "body", null, 1, 1, OpaqueElement.class, false, false, true, false, false, false, false, false);
/* 491 */     initEAttribute(getOpaqueElement_IsHeader(), (EClassifier)this.ecorePackage.getEBoolean(), "isHeader", null, 1, 1, OpaqueElement.class, false, false, true, false, false, false, false, false);
/*     */ 
/*     */     
/* 494 */     createResource("http:///ujf/verimag/bip/Core/Modules.ecore");
/*     */   }
/*     */   
/*     */   private boolean isInitialized;
/*     */   
/*     */   public static ModulesPackage init() {
/*     */     if (isInited)
/*     */       return (ModulesPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore"); 
/*     */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/Modules.ecore") : new ModulesPackageImpl();
/*     */     isInited = true;
/*     */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") : (BehaviorsPackageImpl)BehaviorsPackage.eINSTANCE;
/*     */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") : (InteractionsPackageImpl)InteractionsPackage.eINSTANCE;
/*     */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") : (PrioritiesPackageImpl)PrioritiesPackage.eINSTANCE;
/*     */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : (PortExpressionsPackageImpl)PortExpressionsPackage.eINSTANCE;
/*     */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : (ActionsPackageImpl)ActionsPackage.eINSTANCE;
/*     */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : (ExpressionsPackageImpl)ExpressionsPackage.eINSTANCE;
/*     */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") : (TimePackageImpl)TimePackage.eINSTANCE;
/*     */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") : (ContractsPackageImpl)ContractsPackage.eINSTANCE;
/*     */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") : (TraceabilityPackageImpl)TraceabilityPackage.eINSTANCE;
/*     */     theModulesPackage.createPackageContents();
/*     */     theBehaviorsPackage.createPackageContents();
/*     */     theInteractionsPackage.createPackageContents();
/*     */     thePrioritiesPackage.createPackageContents();
/*     */     thePortExpressionsPackage.createPackageContents();
/*     */     theActionsPackage.createPackageContents();
/*     */     theExpressionsPackage.createPackageContents();
/*     */     theTimePackage.createPackageContents();
/*     */     theContractsPackage.createPackageContents();
/*     */     theTraceabilityPackage.createPackageContents();
/*     */     theModulesPackage.initializePackageContents();
/*     */     theBehaviorsPackage.initializePackageContents();
/*     */     theInteractionsPackage.initializePackageContents();
/*     */     thePrioritiesPackage.initializePackageContents();
/*     */     thePortExpressionsPackage.initializePackageContents();
/*     */     theActionsPackage.initializePackageContents();
/*     */     theExpressionsPackage.initializePackageContents();
/*     */     theTimePackage.initializePackageContents();
/*     */     theContractsPackage.initializePackageContents();
/*     */     theTraceabilityPackage.initializePackageContents();
/*     */     theModulesPackage.freeze();
/*     */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Core/Modules.ecore", theModulesPackage);
/*     */     return theModulesPackage;
/*     */   }
/*     */   
/*     */   public EClass getModule() {
/*     */     return this.moduleEClass;
/*     */   }
/*     */   
/*     */   public EReference getModule_BipType() {
/*     */     return (EReference)this.moduleEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getModule_UsedPackage() {
/*     */     return (EReference)this.moduleEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EReference getModule_Declaration() {
/*     */     return (EReference)this.moduleEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EReference getModule_DataType() {
/*     */     return (EReference)this.moduleEClass.getEStructuralFeatures().get(3);
/*     */   }
/*     */   
/*     */   public EAttribute getModule_SrcFileName() {
/*     */     return (EAttribute)this.moduleEClass.getEStructuralFeatures().get(4);
/*     */   }
/*     */   
/*     */   public EClass getDeclaration() {
/*     */     return this.declarationEClass;
/*     */   }
/*     */   
/*     */   public EClass getPackage() {
/*     */     return this.packageEClass;
/*     */   }
/*     */   
/*     */   public EClass getSystem() {
/*     */     return this.systemEClass;
/*     */   }
/*     */   
/*     */   public EReference getSystem_Root() {
/*     */     return (EReference)this.systemEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EClass getRoot() {
/*     */     return this.rootEClass;
/*     */   }
/*     */   
/*     */   public EReference getRoot_Type() {
/*     */     return (EReference)this.rootEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getRoot_ActualData() {
/*     */     return (EReference)this.rootEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EReference getRoot_System() {
/*     */     return (EReference)this.rootEClass.getEStructuralFeatures().get(2);
/*     */   }
/*     */   
/*     */   public EClass getOpaqueElement() {
/*     */     return this.opaqueElementEClass;
/*     */   }
/*     */   
/*     */   public EAttribute getOpaqueElement_Body() {
/*     */     return (EAttribute)this.opaqueElementEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EAttribute getOpaqueElement_IsHeader() {
/*     */     return (EAttribute)this.opaqueElementEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public ModulesFactory getModulesFactory() {
/*     */     return (ModulesFactory)getEFactoryInstance();
/*     */   }
/*     */   
/*     */   public void createPackageContents() {
/*     */     if (this.isCreated)
/*     */       return; 
/*     */     this.isCreated = true;
/*     */     this.moduleEClass = createEClass(0);
/*     */     createEReference(this.moduleEClass, 2);
/*     */     createEReference(this.moduleEClass, 3);
/*     */     createEReference(this.moduleEClass, 4);
/*     */     createEReference(this.moduleEClass, 5);
/*     */     createEAttribute(this.moduleEClass, 6);
/*     */     this.declarationEClass = createEClass(1);
/*     */     this.packageEClass = createEClass(2);
/*     */     this.systemEClass = createEClass(3);
/*     */     createEReference(this.systemEClass, 7);
/*     */     this.rootEClass = createEClass(4);
/*     */     createEReference(this.rootEClass, 2);
/*     */     createEReference(this.rootEClass, 3);
/*     */     createEReference(this.rootEClass, 4);
/*     */     this.opaqueElementEClass = createEClass(5);
/*     */     createEAttribute(this.opaqueElementEClass, 0);
/*     */     createEAttribute(this.opaqueElementEClass, 1);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Modules\impl\ModulesPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */