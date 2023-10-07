/*     */ package ujf.verimag.bip.Core.PortExpressions.impl;
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
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACFusionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACNaryExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTyping;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACTypingKind;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACUnion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.ACUnionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AINaryExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AISynchro;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AISynchroNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIUnion;
/*     */ import ujf.verimag.bip.Core.PortExpressions.AIUnionNeutral;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpression;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.PortExpressions.PortReference;
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
/*     */ public class PortExpressionsPackageImpl
/*     */   extends EPackageImpl
/*     */   implements PortExpressionsPackage
/*     */ {
/*  85 */   private EClass acNaryExpressionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   private EClass acExpressionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   private EClass portExpressionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   private EClass acFusionNeutralEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   private EClass acUnionNeutralEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   private EClass aiNaryExpressionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   private EClass aiExpressionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   private EClass acFusionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   private EClass aiUnionNeutralEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   private EClass aiSynchroNeutralEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   private EClass aiUnionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   private EClass acTypingEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   private EClass aiSynchroEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   private EClass acUnionEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   private EClass portReferenceEClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   private EEnum acTypingKindEEnum = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PortExpressionsPackageImpl() {
/* 209 */     super("http:///ujf/verimag/bip/Core/PortExpressions.ecore", (EFactory)PortExpressionsFactory.eINSTANCE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 499 */     this.isCreated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 557 */     this.isInitialized = false;
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
/* 568 */     if (this.isInitialized)
/* 569 */       return;  this.isInitialized = true;
/*     */ 
/*     */     
/* 572 */     setName("PortExpressions");
/* 573 */     setNsPrefix("ujf.verimag.bip.Core.PortExpressions");
/* 574 */     setNsURI("http:///ujf/verimag/bip/Core/PortExpressions.ecore");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 581 */     this.acNaryExpressionEClass.getESuperTypes().add(getACExpression());
/* 582 */     this.acExpressionEClass.getESuperTypes().add(getPortExpression());
/* 583 */     this.acFusionNeutralEClass.getESuperTypes().add(getACExpression());
/* 584 */     this.acUnionNeutralEClass.getESuperTypes().add(getACExpression());
/* 585 */     this.aiNaryExpressionEClass.getESuperTypes().add(getAIExpression());
/* 586 */     this.aiExpressionEClass.getESuperTypes().add(getPortExpression());
/* 587 */     this.acFusionEClass.getESuperTypes().add(getACNaryExpression());
/* 588 */     this.aiUnionNeutralEClass.getESuperTypes().add(getAIExpression());
/* 589 */     this.aiSynchroNeutralEClass.getESuperTypes().add(getAIExpression());
/* 590 */     this.aiUnionEClass.getESuperTypes().add(getAINaryExpression());
/* 591 */     this.acTypingEClass.getESuperTypes().add(getACExpression());
/* 592 */     this.aiSynchroEClass.getESuperTypes().add(getAINaryExpression());
/* 593 */     this.acUnionEClass.getESuperTypes().add(getACNaryExpression());
/* 594 */     this.portReferenceEClass.getESuperTypes().add(getACExpression());
/* 595 */     this.portReferenceEClass.getESuperTypes().add(getAIExpression());
/*     */ 
/*     */     
/* 598 */     initEClass(this.acNaryExpressionEClass, ACNaryExpression.class, "ACNaryExpression", true, false, true);
/* 599 */     initEReference(getACNaryExpression_Operand(), (EClassifier)getACExpression(), null, "operand", null, 1, -1, ACNaryExpression.class, false, false, true, true, false, false, true, false, true);
/*     */     
/* 601 */     initEClass(this.acExpressionEClass, ACExpression.class, "ACExpression", true, false, true);
/*     */     
/* 603 */     initEClass(this.portExpressionEClass, PortExpression.class, "PortExpression", true, false, true);
/*     */     
/* 605 */     initEClass(this.acFusionNeutralEClass, ACFusionNeutral.class, "ACFusionNeutral", false, false, true);
/*     */     
/* 607 */     initEClass(this.acUnionNeutralEClass, ACUnionNeutral.class, "ACUnionNeutral", false, false, true);
/*     */     
/* 609 */     initEClass(this.aiNaryExpressionEClass, AINaryExpression.class, "AINaryExpression", true, false, true);
/* 610 */     initEReference(getAINaryExpression_Operand(), (EClassifier)getAIExpression(), null, "operand", null, 1, -1, AINaryExpression.class, false, false, true, true, false, false, true, false, true);
/*     */     
/* 612 */     initEClass(this.aiExpressionEClass, AIExpression.class, "AIExpression", true, false, true);
/*     */     
/* 614 */     initEClass(this.acFusionEClass, ACFusion.class, "ACFusion", false, false, true);
/*     */     
/* 616 */     initEClass(this.aiUnionNeutralEClass, AIUnionNeutral.class, "AIUnionNeutral", false, false, true);
/*     */     
/* 618 */     initEClass(this.aiSynchroNeutralEClass, AISynchroNeutral.class, "AISynchroNeutral", false, false, true);
/*     */     
/* 620 */     initEClass(this.aiUnionEClass, AIUnion.class, "AIUnion", false, false, true);
/*     */     
/* 622 */     initEClass(this.acTypingEClass, ACTyping.class, "ACTyping", false, false, true);
/* 623 */     initEAttribute(getACTyping_Type(), (EClassifier)getACTypingKind(), "type", null, 1, 1, ACTyping.class, false, false, true, false, false, false, false, false);
/* 624 */     initEReference(getACTyping_Operand(), (EClassifier)getACExpression(), null, "operand", null, 1, 1, ACTyping.class, false, false, true, true, false, false, false, false, false);
/*     */     
/* 626 */     initEClass(this.aiSynchroEClass, AISynchro.class, "AISynchro", false, false, true);
/*     */     
/* 628 */     initEClass(this.acUnionEClass, ACUnion.class, "ACUnion", false, false, true);
/*     */     
/* 630 */     initEClass(this.portReferenceEClass, PortReference.class, "PortReference", true, false, true);
/*     */ 
/*     */     
/* 633 */     initEEnum(this.acTypingKindEEnum, ACTypingKind.class, "ACTypingKind");
/* 634 */     addEEnumLiteral(this.acTypingKindEEnum, (Enumerator)ACTypingKind.SYNC);
/* 635 */     addEEnumLiteral(this.acTypingKindEEnum, (Enumerator)ACTypingKind.TRIG);
/*     */ 
/*     */     
/* 638 */     createResource("http:///ujf/verimag/bip/Core/PortExpressions.ecore");
/*     */   }
/*     */   
/*     */   public static PortExpressionsPackage init() {
/*     */     if (isInited)
/*     */       return (PortExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore"); 
/*     */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : new PortExpressionsPackageImpl();
/*     */     isInited = true;
/*     */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") : (ModulesPackageImpl)ModulesPackage.eINSTANCE;
/*     */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") : (BehaviorsPackageImpl)BehaviorsPackage.eINSTANCE;
/*     */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") : (InteractionsPackageImpl)InteractionsPackage.eINSTANCE;
/*     */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") : (PrioritiesPackageImpl)PrioritiesPackage.eINSTANCE;
/*     */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : (ActionsPackageImpl)ActionsPackage.eINSTANCE;
/*     */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : (ExpressionsPackageImpl)ExpressionsPackage.eINSTANCE;
/*     */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") : (TimePackageImpl)TimePackage.eINSTANCE;
/*     */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") : (ContractsPackageImpl)ContractsPackage.eINSTANCE;
/*     */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") : (TraceabilityPackageImpl)TraceabilityPackage.eINSTANCE;
/*     */     thePortExpressionsPackage.createPackageContents();
/*     */     theModulesPackage.createPackageContents();
/*     */     theBehaviorsPackage.createPackageContents();
/*     */     theInteractionsPackage.createPackageContents();
/*     */     thePrioritiesPackage.createPackageContents();
/*     */     theActionsPackage.createPackageContents();
/*     */     theExpressionsPackage.createPackageContents();
/*     */     theTimePackage.createPackageContents();
/*     */     theContractsPackage.createPackageContents();
/*     */     theTraceabilityPackage.createPackageContents();
/*     */     thePortExpressionsPackage.initializePackageContents();
/*     */     theModulesPackage.initializePackageContents();
/*     */     theBehaviorsPackage.initializePackageContents();
/*     */     theInteractionsPackage.initializePackageContents();
/*     */     thePrioritiesPackage.initializePackageContents();
/*     */     theActionsPackage.initializePackageContents();
/*     */     theExpressionsPackage.initializePackageContents();
/*     */     theTimePackage.initializePackageContents();
/*     */     theContractsPackage.initializePackageContents();
/*     */     theTraceabilityPackage.initializePackageContents();
/*     */     thePortExpressionsPackage.freeze();
/*     */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Core/PortExpressions.ecore", thePortExpressionsPackage);
/*     */     return thePortExpressionsPackage;
/*     */   }
/*     */   
/*     */   public EClass getACNaryExpression() {
/*     */     return this.acNaryExpressionEClass;
/*     */   }
/*     */   
/*     */   public EReference getACNaryExpression_Operand() {
/*     */     return (EReference)this.acNaryExpressionEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EClass getACExpression() {
/*     */     return this.acExpressionEClass;
/*     */   }
/*     */   
/*     */   public EClass getPortExpression() {
/*     */     return this.portExpressionEClass;
/*     */   }
/*     */   
/*     */   public EClass getACFusionNeutral() {
/*     */     return this.acFusionNeutralEClass;
/*     */   }
/*     */   
/*     */   public EClass getACUnionNeutral() {
/*     */     return this.acUnionNeutralEClass;
/*     */   }
/*     */   
/*     */   public EClass getAINaryExpression() {
/*     */     return this.aiNaryExpressionEClass;
/*     */   }
/*     */   
/*     */   public EReference getAINaryExpression_Operand() {
/*     */     return (EReference)this.aiNaryExpressionEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EClass getAIExpression() {
/*     */     return this.aiExpressionEClass;
/*     */   }
/*     */   
/*     */   public EClass getACFusion() {
/*     */     return this.acFusionEClass;
/*     */   }
/*     */   
/*     */   public EClass getAIUnionNeutral() {
/*     */     return this.aiUnionNeutralEClass;
/*     */   }
/*     */   
/*     */   public EClass getAISynchroNeutral() {
/*     */     return this.aiSynchroNeutralEClass;
/*     */   }
/*     */   
/*     */   public EClass getAIUnion() {
/*     */     return this.aiUnionEClass;
/*     */   }
/*     */   
/*     */   public EClass getACTyping() {
/*     */     return this.acTypingEClass;
/*     */   }
/*     */   
/*     */   public EAttribute getACTyping_Type() {
/*     */     return (EAttribute)this.acTypingEClass.getEStructuralFeatures().get(0);
/*     */   }
/*     */   
/*     */   public EReference getACTyping_Operand() {
/*     */     return (EReference)this.acTypingEClass.getEStructuralFeatures().get(1);
/*     */   }
/*     */   
/*     */   public EClass getAISynchro() {
/*     */     return this.aiSynchroEClass;
/*     */   }
/*     */   
/*     */   public EClass getACUnion() {
/*     */     return this.acUnionEClass;
/*     */   }
/*     */   
/*     */   public EClass getPortReference() {
/*     */     return this.portReferenceEClass;
/*     */   }
/*     */   
/*     */   public EEnum getACTypingKind() {
/*     */     return this.acTypingKindEEnum;
/*     */   }
/*     */   
/*     */   public PortExpressionsFactory getPortExpressionsFactory() {
/*     */     return (PortExpressionsFactory)getEFactoryInstance();
/*     */   }
/*     */   
/*     */   public void createPackageContents() {
/*     */     if (this.isCreated)
/*     */       return; 
/*     */     this.isCreated = true;
/*     */     this.acNaryExpressionEClass = createEClass(0);
/*     */     createEReference(this.acNaryExpressionEClass, 0);
/*     */     this.acExpressionEClass = createEClass(1);
/*     */     this.portExpressionEClass = createEClass(2);
/*     */     this.acFusionNeutralEClass = createEClass(3);
/*     */     this.acUnionNeutralEClass = createEClass(4);
/*     */     this.aiNaryExpressionEClass = createEClass(5);
/*     */     createEReference(this.aiNaryExpressionEClass, 0);
/*     */     this.aiExpressionEClass = createEClass(6);
/*     */     this.acFusionEClass = createEClass(7);
/*     */     this.aiUnionNeutralEClass = createEClass(8);
/*     */     this.aiSynchroNeutralEClass = createEClass(9);
/*     */     this.aiUnionEClass = createEClass(10);
/*     */     this.acTypingEClass = createEClass(11);
/*     */     createEAttribute(this.acTypingEClass, 0);
/*     */     createEReference(this.acTypingEClass, 1);
/*     */     this.aiSynchroEClass = createEClass(12);
/*     */     this.acUnionEClass = createEClass(13);
/*     */     this.portReferenceEClass = createEClass(14);
/*     */     this.acTypingKindEEnum = createEEnum(15);
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\PortExpressions\impl\PortExpressionsPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */