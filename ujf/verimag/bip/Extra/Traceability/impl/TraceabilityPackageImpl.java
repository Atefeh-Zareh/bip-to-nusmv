/*     */ package ujf.verimag.bip.Extra.Traceability.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EAttribute;
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EClassifier;
/*     */ import org.eclipse.emf.ecore.EFactory;
/*     */ import org.eclipse.emf.ecore.EPackage;
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
/*     */ import ujf.verimag.bip.Extra.Time.TimePackage;
/*     */ import ujf.verimag.bip.Extra.Time.impl.TimePackageImpl;
/*     */ import ujf.verimag.bip.Extra.Traceability.TraceabilityFactory;
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
/*     */ public class TraceabilityPackageImpl
/*     */   extends EPackageImpl
/*     */   implements TraceabilityPackage
/*     */ {
/*  68 */   private EClass traceableElementEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TraceabilityPackageImpl() {
/*  87 */     super("http:///ujf/verimag/bip/Extra/Traceability.ecore", (EFactory)TraceabilityFactory.eINSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     this.isCreated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     this.isInitialized = false;
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
/* 232 */     if (this.isInitialized)
/* 233 */       return;  this.isInitialized = true;
/*     */ 
/*     */     
/* 236 */     setName("Traceability");
/* 237 */     setNsPrefix("ujf.verimag.bip.Extra.Traceability");
/* 238 */     setNsURI("http:///ujf/verimag/bip/Extra/Traceability.ecore");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 247 */     initEClass(this.traceableElementEClass, TraceableElement.class, "TraceableElement", true, false, true);
/* 248 */     initEAttribute(getTraceableElement_StartSourceLine(), (EClassifier)this.ecorePackage.getEInt(), "startSourceLine", null, 0, 1, TraceableElement.class, false, false, true, false, false, false, false, false);
/*     */ 
/*     */     
/* 251 */     createResource("http:///ujf/verimag/bip/Extra/Traceability.ecore");
/*     */   }
/*     */   
/*     */   private boolean isInitialized;
/*     */   
/*     */   public static TraceabilityPackage init() {
/*     */     if (isInited)
/*     */       return (TraceabilityPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore"); 
/*     */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Extra/Traceability.ecore") : new TraceabilityPackageImpl();
/*     */     isInited = true;
/*     */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") : (ModulesPackageImpl)ModulesPackage.eINSTANCE;
/*     */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") : (BehaviorsPackageImpl)BehaviorsPackage.eINSTANCE;
/*     */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") : (InteractionsPackageImpl)InteractionsPackage.eINSTANCE;
/*     */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") : (PrioritiesPackageImpl)PrioritiesPackage.eINSTANCE;
/*     */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : (PortExpressionsPackageImpl)PortExpressionsPackage.eINSTANCE;
/*     */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : (ActionsPackageImpl)ActionsPackage.eINSTANCE;
/*     */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : (ExpressionsPackageImpl)ExpressionsPackage.eINSTANCE;
/*     */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") : (TimePackageImpl)TimePackage.eINSTANCE;
/*     */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") : (ContractsPackageImpl)ContractsPackage.eINSTANCE;
/*     */     theTraceabilityPackage.createPackageContents();
/*     */     theModulesPackage.createPackageContents();
/*     */     theBehaviorsPackage.createPackageContents();
/*     */     theInteractionsPackage.createPackageContents();
/*     */     thePrioritiesPackage.createPackageContents();
/*     */     thePortExpressionsPackage.createPackageContents();
/*     */     theActionsPackage.createPackageContents();
/*     */     theExpressionsPackage.createPackageContents();
/*     */     theTimePackage.createPackageContents();
/*     */     theContractsPackage.createPackageContents();
/*     */     theTraceabilityPackage.initializePackageContents();
/*     */     theModulesPackage.initializePackageContents();
/*     */     theBehaviorsPackage.initializePackageContents();
/*     */     theInteractionsPackage.initializePackageContents();
/*     */     thePrioritiesPackage.initializePackageContents();
/*     */     thePortExpressionsPackage.initializePackageContents();
/*     */     theActionsPackage.initializePackageContents();
/*     */     theExpressionsPackage.initializePackageContents();
/*     */     theTimePackage.initializePackageContents();
/*     */     theContractsPackage.initializePackageContents();
/*     */     theTraceabilityPackage.freeze();
/*     */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Extra/Traceability.ecore", theTraceabilityPackage);
/*     */     return theTraceabilityPackage;
/*     */   }
/*     */   
/*     */   public EClass getTraceableElement() {
/*     */     return this.traceableElementEClass;
/*     */   }
/*     */   
/*     */   public EAttribute getTraceableElement_StartSourceLine() {
/*     */     return (EAttribute)this.traceableElementEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public TraceabilityFactory getTraceabilityFactory() {
/*     */     return (TraceabilityFactory)getEFactoryInstance();
/*     */   }
/*     */   
/*     */   public void createPackageContents() {
/*     */     if (this.isCreated)
/*     */       return; 
/*     */     this.isCreated = true;
/*     */     this.traceableElementEClass = createEClass(0);
/*     */     createEAttribute(this.traceableElementEClass, 0);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Extra\Traceability\impl\TraceabilityPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */