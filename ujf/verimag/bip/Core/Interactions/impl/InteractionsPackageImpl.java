/*      */ package ujf.verimag.bip.Core.Interactions.impl;
/*      */ 
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.impl.EPackageImpl;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsPackage;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.impl.ActionsPackageImpl;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.impl.ExpressionsPackageImpl;
/*      */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*      */ import ujf.verimag.bip.Core.Behaviors.impl.BehaviorsPackageImpl;
/*      */ import ujf.verimag.bip.Core.Interactions.ActualPortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.Component;
/*      */ import ujf.verimag.bip.Core.Interactions.CompoundType;
/*      */ import ujf.verimag.bip.Core.Interactions.ConditionalActualPortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.Connector;
/*      */ import ujf.verimag.bip.Core.Interactions.ConnectorType;
/*      */ import ujf.verimag.bip.Core.Interactions.ExportBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortReference;
/*      */ import ujf.verimag.bip.Core.Interactions.InnerPortSpecification;
/*      */ import ujf.verimag.bip.Core.Interactions.Interaction;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionSpecification;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionsFactory;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*      */ import ujf.verimag.bip.Core.Interactions.MultiplicityElement;
/*      */ import ujf.verimag.bip.Core.Interactions.MultiplicityPath;
/*      */ import ujf.verimag.bip.Core.Interactions.Part;
/*      */ import ujf.verimag.bip.Core.Interactions.PartElementReference;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameter;
/*      */ import ujf.verimag.bip.Core.Interactions.PortParameterReference;
/*      */ import ujf.verimag.bip.Core.Interactions.VariableExportBinding;
/*      */ import ujf.verimag.bip.Core.Modules.ModulesPackage;
/*      */ import ujf.verimag.bip.Core.Modules.impl.ModulesPackageImpl;
/*      */ import ujf.verimag.bip.Core.PortExpressions.PortExpressionsPackage;
/*      */ import ujf.verimag.bip.Core.PortExpressions.impl.PortExpressionsPackageImpl;
/*      */ import ujf.verimag.bip.Core.Priorities.PrioritiesPackage;
/*      */ import ujf.verimag.bip.Core.Priorities.impl.PrioritiesPackageImpl;
/*      */ import ujf.verimag.bip.Extra.Contracts.ContractsPackage;
/*      */ import ujf.verimag.bip.Extra.Contracts.impl.ContractsPackageImpl;
/*      */ import ujf.verimag.bip.Extra.Time.TimePackage;
/*      */ import ujf.verimag.bip.Extra.Time.impl.TimePackageImpl;
/*      */ import ujf.verimag.bip.Extra.Traceability.TraceabilityPackage;
/*      */ import ujf.verimag.bip.Extra.Traceability.impl.TraceabilityPackageImpl;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class InteractionsPackageImpl
/*      */   extends EPackageImpl
/*      */   implements InteractionsPackage
/*      */ {
/*   85 */   private EClass componentEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   92 */   private EClass partEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   99 */   private EClass multiplicityElementEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  106 */   private EClass compoundTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  113 */   private EClass connectorEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  120 */   private EClass actualPortParameterEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  127 */   private EClass partElementReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   private EClass multiplicityPathEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  141 */   private EClass innerPortSpecificationEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  148 */   private EClass interactionSpecificationEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  155 */   private EClass interactionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  162 */   private EClass portParameterEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  169 */   private EClass exportBindingEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  176 */   private EClass portParameterReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  183 */   private EClass innerPortReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  190 */   private EClass conditionalActualPortParameterEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  197 */   private EClass variableExportBindingEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  204 */   private EClass connectorTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InteractionsPackageImpl() {
/*  223 */     super("http:///ujf/verimag/bip/Core/Interactions.ecore", (EFactory)InteractionsFactory.eINSTANCE);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  843 */     this.isCreated = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  935 */     this.isInitialized = false;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean isInited = false;
/*      */   
/*      */   private boolean isCreated;
/*      */   
/*      */   private boolean isInitialized;
/*      */   
/*      */   public void initializePackageContents() {
/*  946 */     if (this.isInitialized)
/*  947 */       return;  this.isInitialized = true;
/*      */ 
/*      */     
/*  950 */     setName("Interactions");
/*  951 */     setNsPrefix("ujf.verimag.bip.Core.Interactions");
/*  952 */     setNsURI("http:///ujf/verimag/bip/Core/Interactions.ecore");
/*      */ 
/*      */     
/*  955 */     BehaviorsPackage theBehaviorsPackage = (BehaviorsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/*  956 */     PrioritiesPackage thePrioritiesPackage = (PrioritiesPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore");
/*  957 */     PortExpressionsPackage thePortExpressionsPackage = (PortExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  964 */     this.componentEClass.getESuperTypes().add(getPart());
/*  965 */     this.partEClass.getESuperTypes().add(getMultiplicityElement());
/*  966 */     this.multiplicityElementEClass.getESuperTypes().add(theBehaviorsPackage.getNamedElement());
/*  967 */     this.compoundTypeEClass.getESuperTypes().add(theBehaviorsPackage.getComponentType());
/*  968 */     this.connectorEClass.getESuperTypes().add(getPart());
/*  969 */     this.partElementReferenceEClass.getESuperTypes().add(getMultiplicityPath());
/*  970 */     this.interactionEClass.getESuperTypes().add(thePrioritiesPackage.getPriorityElement());
/*  971 */     this.portParameterEClass.getESuperTypes().add(theBehaviorsPackage.getNamedElement());
/*  972 */     this.exportBindingEClass.getESuperTypes().add(getInnerPortSpecification());
/*  973 */     this.exportBindingEClass.getESuperTypes().add(theBehaviorsPackage.getBinding());
/*  974 */     this.portParameterReferenceEClass.getESuperTypes().add(thePortExpressionsPackage.getPortReference());
/*  975 */     this.innerPortReferenceEClass.getESuperTypes().add(getInnerPortSpecification());
/*  976 */     this.innerPortReferenceEClass.getESuperTypes().add(getActualPortParameter());
/*  977 */     this.innerPortReferenceEClass.getESuperTypes().add(thePortExpressionsPackage.getPortReference());
/*  978 */     this.conditionalActualPortParameterEClass.getESuperTypes().add(getActualPortParameter());
/*  979 */     this.variableExportBindingEClass.getESuperTypes().add(theBehaviorsPackage.getVariableBinding());
/*  980 */     this.connectorTypeEClass.getESuperTypes().add(theBehaviorsPackage.getPartType());
/*      */ 
/*      */     
/*  983 */     initEClass(this.componentEClass, Component.class, "Component", false, false, true);
/*  984 */     initEReference(getComponent_CompoundType(), (EClassifier)getCompoundType(), getCompoundType_Subcomponent(), "compoundType", null, 1, 1, Component.class, false, false, true, false, false, false, false, false, false);
/*  985 */     initEReference(getComponent_Type(), (EClassifier)theBehaviorsPackage.getComponentType(), null, "type", null, 1, 1, Component.class, false, false, true, false, true, false, false, false, false);
/*      */     
/*  987 */     initEClass(this.partEClass, Part.class, "Part", true, false, true);
/*  988 */     initEReference(getPart_ActualData(), (EClassifier)theBehaviorsPackage.getExpression(), null, "actualData", null, 0, -1, Part.class, false, false, true, true, false, false, true, false, true);
/*      */     
/*  990 */     initEClass(this.multiplicityElementEClass, MultiplicityElement.class, "MultiplicityElement", true, false, true);
/*  991 */     initEReference(getMultiplicityElement_MultiplicitySpecification(), (EClassifier)theBehaviorsPackage.getExpression(), null, "multiplicitySpecification", null, 0, -1, MultiplicityElement.class, false, false, true, true, false, false, true, false, true);
/*      */     
/*  993 */     initEClass(this.compoundTypeEClass, CompoundType.class, "CompoundType", false, false, true);
/*  994 */     initEReference(getCompoundType_Connector(), (EClassifier)getConnector(), getConnector_CompoundType(), "connector", null, 0, -1, CompoundType.class, false, false, true, true, false, false, true, false, false);
/*  995 */     initEReference(getCompoundType_Subcomponent(), (EClassifier)getComponent(), getComponent_CompoundType(), "subcomponent", null, 1, -1, CompoundType.class, false, false, true, true, false, false, true, false, false);
/*      */     
/*  997 */     initEClass(this.connectorEClass, Connector.class, "Connector", false, false, true);
/*  998 */     initEReference(getConnector_ActualPort(), (EClassifier)getActualPortParameter(), null, "actualPort", null, 1, -1, Connector.class, false, false, true, true, false, false, true, false, true);
/*  999 */     initEReference(getConnector_Type(), (EClassifier)getConnectorType(), null, "type", null, 1, 1, Connector.class, false, false, true, false, true, false, false, false, false);
/* 1000 */     initEReference(getConnector_CompoundType(), (EClassifier)getCompoundType(), getCompoundType_Connector(), "compoundType", null, 1, 1, Connector.class, false, false, true, false, false, false, false, false, false);
/*      */     
/* 1002 */     initEClass(this.actualPortParameterEClass, ActualPortParameter.class, "ActualPortParameter", true, false, true);
/*      */     
/* 1004 */     initEClass(this.partElementReferenceEClass, PartElementReference.class, "PartElementReference", false, false, true);
/* 1005 */     initEReference(getPartElementReference_ExportBinding(), (EClassifier)getInnerPortSpecification(), getInnerPortSpecification_TargetInstance(), "exportBinding", null, 0, 1, PartElementReference.class, false, false, true, false, false, false, false, false, false);
/* 1006 */     initEReference(getPartElementReference_TargetPart(), (EClassifier)getPart(), null, "targetPart", null, 1, 1, PartElementReference.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1008 */     initEClass(this.multiplicityPathEClass, MultiplicityPath.class, "MultiplicityPath", true, false, true);
/* 1009 */     initEReference(getMultiplicityPath_Index(), (EClassifier)theBehaviorsPackage.getExpression(), null, "index", null, 0, -1, MultiplicityPath.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1011 */     initEClass(this.innerPortSpecificationEClass, InnerPortSpecification.class, "InnerPortSpecification", true, false, true);
/* 1012 */     initEReference(getInnerPortSpecification_TargetPort(), (EClassifier)theBehaviorsPackage.getPort(), null, "targetPort", null, 1, 1, InnerPortSpecification.class, false, false, true, false, true, false, false, false, false);
/* 1013 */     initEReference(getInnerPortSpecification_TargetInstance(), (EClassifier)getPartElementReference(), getPartElementReference_ExportBinding(), "targetInstance", null, 1, 1, InnerPortSpecification.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1015 */     initEClass(this.interactionSpecificationEClass, InteractionSpecification.class, "InteractionSpecification", false, false, true);
/* 1016 */     initEReference(getInteractionSpecification_ConnectorType(), (EClassifier)getConnectorType(), getConnectorType_InteractionSpecification(), "connectorType", null, 1, 1, InteractionSpecification.class, false, false, true, false, false, false, false, false, false);
/* 1017 */     initEReference(getInteractionSpecification_Interaction(), (EClassifier)getInteraction(), null, "interaction", null, 1, 1, InteractionSpecification.class, false, false, true, true, false, false, false, false, false);
/* 1018 */     initEReference(getInteractionSpecification_Guard(), (EClassifier)theBehaviorsPackage.getExpression(), null, "guard", null, 0, 1, InteractionSpecification.class, false, false, true, true, false, false, false, false, false);
/* 1019 */     initEReference(getInteractionSpecification_DownAction(), (EClassifier)theBehaviorsPackage.getAction(), null, "downAction", null, 0, 1, InteractionSpecification.class, false, false, true, true, false, false, false, false, false);
/* 1020 */     initEReference(getInteractionSpecification_UpAction(), (EClassifier)theBehaviorsPackage.getAction(), null, "upAction", null, 0, 1, InteractionSpecification.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1022 */     initEClass(this.interactionEClass, Interaction.class, "Interaction", false, false, true);
/* 1023 */     initEReference(getInteraction_Port(), (EClassifier)thePortExpressionsPackage.getPortReference(), null, "port", null, 0, -1, Interaction.class, false, false, true, true, false, false, true, false, false);
/* 1024 */     initEReference(getInteraction_Connector(), (EClassifier)getPartElementReference(), null, "connector", null, 0, 1, Interaction.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1026 */     initEClass(this.portParameterEClass, PortParameter.class, "PortParameter", false, false, true);
/* 1027 */     initEReference(getPortParameter_Type(), (EClassifier)theBehaviorsPackage.getPortType(), null, "type", null, 1, 1, PortParameter.class, false, false, true, false, true, false, false, false, false);
/* 1028 */     initEReference(getPortParameter_ConnectorType(), (EClassifier)getConnectorType(), getConnectorType_PortParameter(), "connectorType", null, 0, 1, PortParameter.class, false, false, true, false, false, false, false, false, false);
/*      */     
/* 1030 */     initEClass(this.exportBindingEClass, ExportBinding.class, "ExportBinding", false, false, true);
/*      */     
/* 1032 */     initEClass(this.portParameterReferenceEClass, PortParameterReference.class, "PortParameterReference", false, false, true);
/* 1033 */     initEReference(getPortParameterReference_Target(), (EClassifier)getPortParameter(), null, "target", null, 1, 1, PortParameterReference.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1035 */     initEClass(this.innerPortReferenceEClass, InnerPortReference.class, "InnerPortReference", false, false, true);
/*      */     
/* 1037 */     initEClass(this.conditionalActualPortParameterEClass, ConditionalActualPortParameter.class, "ConditionalActualPortParameter", false, false, true);
/* 1038 */     initEReference(getConditionalActualPortParameter_Expression(), (EClassifier)theBehaviorsPackage.getExpression(), null, "expression", null, 1, 1, ConditionalActualPortParameter.class, false, false, true, true, false, false, false, false, false);
/* 1039 */     initEReference(getConditionalActualPortParameter_TrueCase(), (EClassifier)getActualPortParameter(), null, "trueCase", null, 1, 1, ConditionalActualPortParameter.class, false, false, true, true, false, false, false, false, false);
/* 1040 */     initEReference(getConditionalActualPortParameter_FalseCase(), (EClassifier)getActualPortParameter(), null, "falseCase", null, 1, 1, ConditionalActualPortParameter.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1042 */     initEClass(this.variableExportBindingEClass, VariableExportBinding.class, "VariableExportBinding", false, false, true);
/* 1043 */     initEReference(getVariableExportBinding_TargetInstance(), (EClassifier)getPartElementReference(), null, "targetInstance", null, 1, 1, VariableExportBinding.class, false, false, true, true, false, false, false, false, false);
/* 1044 */     initEReference(getVariableExportBinding_TargetInterfaceVariable(), (EClassifier)theBehaviorsPackage.getInterfaceVariable(), null, "targetInterfaceVariable", null, 1, 1, VariableExportBinding.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1046 */     initEClass(this.connectorTypeEClass, ConnectorType.class, "ConnectorType", false, false, true);
/* 1047 */     initEReference(getConnectorType_Port(), (EClassifier)theBehaviorsPackage.getPort(), theBehaviorsPackage.getPort_ConnectorType(), "port", null, 0, 1, ConnectorType.class, false, false, true, true, false, false, false, false, false);
/* 1048 */     initEReference(getConnectorType_InteractionSpecification(), (EClassifier)getInteractionSpecification(), getInteractionSpecification_ConnectorType(), "interactionSpecification", null, 0, -1, ConnectorType.class, false, false, true, true, false, false, true, false, false);
/* 1049 */     initEReference(getConnectorType_PortParameter(), (EClassifier)getPortParameter(), getPortParameter_ConnectorType(), "portParameter", null, 1, -1, ConnectorType.class, false, false, true, true, false, false, true, false, true);
/* 1050 */     initEReference(getConnectorType_PortDefinition(), (EClassifier)theBehaviorsPackage.getPortDefinition(), theBehaviorsPackage.getPortDefinition_ConnectorType(), "portDefinition", null, 0, 1, ConnectorType.class, false, false, true, true, false, false, false, false, false);
/* 1051 */     initEReference(getConnectorType_Definition(), (EClassifier)thePortExpressionsPackage.getPortExpression(), null, "definition", null, 1, 1, ConnectorType.class, false, false, true, true, false, false, false, false, false);
/* 1052 */     initEReference(getConnectorType_Variable(), (EClassifier)theBehaviorsPackage.getVariable(), theBehaviorsPackage.getVariable_ConnectorType(), "variable", null, 0, -1, ConnectorType.class, false, false, true, true, false, false, true, false, false);
/*      */ 
/*      */     
/* 1055 */     createResource("http:///ujf/verimag/bip/Core/Interactions.ecore");
/*      */   }
/*      */   
/*      */   public static InteractionsPackage init() {
/*      */     if (isInited)
/*      */       return (InteractionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore"); 
/*      */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/Interactions.ecore") : new InteractionsPackageImpl();
/*      */     isInited = true;
/*      */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") : (ModulesPackageImpl)ModulesPackage.eINSTANCE;
/*      */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") : (BehaviorsPackageImpl)BehaviorsPackage.eINSTANCE;
/*      */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") : (PrioritiesPackageImpl)PrioritiesPackage.eINSTANCE;
/*      */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : (PortExpressionsPackageImpl)PortExpressionsPackage.eINSTANCE;
/*      */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : (ActionsPackageImpl)ActionsPackage.eINSTANCE;
/*      */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : (ExpressionsPackageImpl)ExpressionsPackage.eINSTANCE;
/*      */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") : (TimePackageImpl)TimePackage.eINSTANCE;
/*      */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") : (ContractsPackageImpl)ContractsPackage.eINSTANCE;
/*      */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") : (TraceabilityPackageImpl)TraceabilityPackage.eINSTANCE;
/*      */     theInteractionsPackage.createPackageContents();
/*      */     theModulesPackage.createPackageContents();
/*      */     theBehaviorsPackage.createPackageContents();
/*      */     thePrioritiesPackage.createPackageContents();
/*      */     thePortExpressionsPackage.createPackageContents();
/*      */     theActionsPackage.createPackageContents();
/*      */     theExpressionsPackage.createPackageContents();
/*      */     theTimePackage.createPackageContents();
/*      */     theContractsPackage.createPackageContents();
/*      */     theTraceabilityPackage.createPackageContents();
/*      */     theInteractionsPackage.initializePackageContents();
/*      */     theModulesPackage.initializePackageContents();
/*      */     theBehaviorsPackage.initializePackageContents();
/*      */     thePrioritiesPackage.initializePackageContents();
/*      */     thePortExpressionsPackage.initializePackageContents();
/*      */     theActionsPackage.initializePackageContents();
/*      */     theExpressionsPackage.initializePackageContents();
/*      */     theTimePackage.initializePackageContents();
/*      */     theContractsPackage.initializePackageContents();
/*      */     theTraceabilityPackage.initializePackageContents();
/*      */     theInteractionsPackage.freeze();
/*      */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Core/Interactions.ecore", theInteractionsPackage);
/*      */     return theInteractionsPackage;
/*      */   }
/*      */   
/*      */   public EClass getComponent() {
/*      */     return this.componentEClass;
/*      */   }
/*      */   
/*      */   public EReference getComponent_CompoundType() {
/*      */     return (EReference)this.componentEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getComponent_Type() {
/*      */     return (EReference)this.componentEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getPart() {
/*      */     return this.partEClass;
/*      */   }
/*      */   
/*      */   public EReference getPart_ActualData() {
/*      */     return (EReference)this.partEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getMultiplicityElement() {
/*      */     return this.multiplicityElementEClass;
/*      */   }
/*      */   
/*      */   public EReference getMultiplicityElement_MultiplicitySpecification() {
/*      */     return (EReference)this.multiplicityElementEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getCompoundType() {
/*      */     return this.compoundTypeEClass;
/*      */   }
/*      */   
/*      */   public EReference getCompoundType_Connector() {
/*      */     return (EReference)this.compoundTypeEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getCompoundType_Subcomponent() {
/*      */     return (EReference)this.compoundTypeEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getConnector() {
/*      */     return this.connectorEClass;
/*      */   }
/*      */   
/*      */   public EReference getConnector_ActualPort() {
/*      */     return (EReference)this.connectorEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getConnector_Type() {
/*      */     return (EReference)this.connectorEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getConnector_CompoundType() {
/*      */     return (EReference)this.connectorEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EClass getActualPortParameter() {
/*      */     return this.actualPortParameterEClass;
/*      */   }
/*      */   
/*      */   public EClass getPartElementReference() {
/*      */     return this.partElementReferenceEClass;
/*      */   }
/*      */   
/*      */   public EReference getPartElementReference_ExportBinding() {
/*      */     return (EReference)this.partElementReferenceEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getPartElementReference_TargetPart() {
/*      */     return (EReference)this.partElementReferenceEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getMultiplicityPath() {
/*      */     return this.multiplicityPathEClass;
/*      */   }
/*      */   
/*      */   public EReference getMultiplicityPath_Index() {
/*      */     return (EReference)this.multiplicityPathEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getInnerPortSpecification() {
/*      */     return this.innerPortSpecificationEClass;
/*      */   }
/*      */   
/*      */   public EReference getInnerPortSpecification_TargetPort() {
/*      */     return (EReference)this.innerPortSpecificationEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getInnerPortSpecification_TargetInstance() {
/*      */     return (EReference)this.innerPortSpecificationEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getInteractionSpecification() {
/*      */     return this.interactionSpecificationEClass;
/*      */   }
/*      */   
/*      */   public EReference getInteractionSpecification_ConnectorType() {
/*      */     return (EReference)this.interactionSpecificationEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getInteractionSpecification_Interaction() {
/*      */     return (EReference)this.interactionSpecificationEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getInteractionSpecification_Guard() {
/*      */     return (EReference)this.interactionSpecificationEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EReference getInteractionSpecification_DownAction() {
/*      */     return (EReference)this.interactionSpecificationEClass.getEStructuralFeatures().get(3);
/*      */   }
/*      */   
/*      */   public EReference getInteractionSpecification_UpAction() {
/*      */     return (EReference)this.interactionSpecificationEClass.getEStructuralFeatures().get(4);
/*      */   }
/*      */   
/*      */   public EClass getInteraction() {
/*      */     return this.interactionEClass;
/*      */   }
/*      */   
/*      */   public EReference getInteraction_Port() {
/*      */     return (EReference)this.interactionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getInteraction_Connector() {
/*      */     return (EReference)this.interactionEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getPortParameter() {
/*      */     return this.portParameterEClass;
/*      */   }
/*      */   
/*      */   public EReference getPortParameter_Type() {
/*      */     return (EReference)this.portParameterEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getPortParameter_ConnectorType() {
/*      */     return (EReference)this.portParameterEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getExportBinding() {
/*      */     return this.exportBindingEClass;
/*      */   }
/*      */   
/*      */   public EClass getPortParameterReference() {
/*      */     return this.portParameterReferenceEClass;
/*      */   }
/*      */   
/*      */   public EReference getPortParameterReference_Target() {
/*      */     return (EReference)this.portParameterReferenceEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getInnerPortReference() {
/*      */     return this.innerPortReferenceEClass;
/*      */   }
/*      */   
/*      */   public EClass getConditionalActualPortParameter() {
/*      */     return this.conditionalActualPortParameterEClass;
/*      */   }
/*      */   
/*      */   public EReference getConditionalActualPortParameter_Expression() {
/*      */     return (EReference)this.conditionalActualPortParameterEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getConditionalActualPortParameter_TrueCase() {
/*      */     return (EReference)this.conditionalActualPortParameterEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getConditionalActualPortParameter_FalseCase() {
/*      */     return (EReference)this.conditionalActualPortParameterEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EClass getVariableExportBinding() {
/*      */     return this.variableExportBindingEClass;
/*      */   }
/*      */   
/*      */   public EReference getVariableExportBinding_TargetInstance() {
/*      */     return (EReference)this.variableExportBindingEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getVariableExportBinding_TargetInterfaceVariable() {
/*      */     return (EReference)this.variableExportBindingEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getConnectorType() {
/*      */     return this.connectorTypeEClass;
/*      */   }
/*      */   
/*      */   public EReference getConnectorType_Port() {
/*      */     return (EReference)this.connectorTypeEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getConnectorType_InteractionSpecification() {
/*      */     return (EReference)this.connectorTypeEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getConnectorType_PortParameter() {
/*      */     return (EReference)this.connectorTypeEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EReference getConnectorType_PortDefinition() {
/*      */     return (EReference)this.connectorTypeEClass.getEStructuralFeatures().get(3);
/*      */   }
/*      */   
/*      */   public EReference getConnectorType_Definition() {
/*      */     return (EReference)this.connectorTypeEClass.getEStructuralFeatures().get(4);
/*      */   }
/*      */   
/*      */   public EReference getConnectorType_Variable() {
/*      */     return (EReference)this.connectorTypeEClass.getEStructuralFeatures().get(5);
/*      */   }
/*      */   
/*      */   public InteractionsFactory getInteractionsFactory() {
/*      */     return (InteractionsFactory)getEFactoryInstance();
/*      */   }
/*      */   
/*      */   public void createPackageContents() {
/*      */     if (this.isCreated)
/*      */       return; 
/*      */     this.isCreated = true;
/*      */     this.componentEClass = createEClass(0);
/*      */     createEReference(this.componentEClass, 4);
/*      */     createEReference(this.componentEClass, 5);
/*      */     this.partEClass = createEClass(1);
/*      */     createEReference(this.partEClass, 3);
/*      */     this.multiplicityElementEClass = createEClass(2);
/*      */     createEReference(this.multiplicityElementEClass, 2);
/*      */     this.compoundTypeEClass = createEClass(3);
/*      */     createEReference(this.compoundTypeEClass, 12);
/*      */     createEReference(this.compoundTypeEClass, 13);
/*      */     this.connectorEClass = createEClass(4);
/*      */     createEReference(this.connectorEClass, 4);
/*      */     createEReference(this.connectorEClass, 5);
/*      */     createEReference(this.connectorEClass, 6);
/*      */     this.actualPortParameterEClass = createEClass(5);
/*      */     this.partElementReferenceEClass = createEClass(6);
/*      */     createEReference(this.partElementReferenceEClass, 1);
/*      */     createEReference(this.partElementReferenceEClass, 2);
/*      */     this.multiplicityPathEClass = createEClass(7);
/*      */     createEReference(this.multiplicityPathEClass, 0);
/*      */     this.innerPortSpecificationEClass = createEClass(8);
/*      */     createEReference(this.innerPortSpecificationEClass, 0);
/*      */     createEReference(this.innerPortSpecificationEClass, 1);
/*      */     this.interactionSpecificationEClass = createEClass(9);
/*      */     createEReference(this.interactionSpecificationEClass, 0);
/*      */     createEReference(this.interactionSpecificationEClass, 1);
/*      */     createEReference(this.interactionSpecificationEClass, 2);
/*      */     createEReference(this.interactionSpecificationEClass, 3);
/*      */     createEReference(this.interactionSpecificationEClass, 4);
/*      */     this.interactionEClass = createEClass(10);
/*      */     createEReference(this.interactionEClass, 0);
/*      */     createEReference(this.interactionEClass, 1);
/*      */     this.portParameterEClass = createEClass(11);
/*      */     createEReference(this.portParameterEClass, 2);
/*      */     createEReference(this.portParameterEClass, 3);
/*      */     this.exportBindingEClass = createEClass(12);
/*      */     this.portParameterReferenceEClass = createEClass(13);
/*      */     createEReference(this.portParameterReferenceEClass, 0);
/*      */     this.innerPortReferenceEClass = createEClass(14);
/*      */     this.conditionalActualPortParameterEClass = createEClass(15);
/*      */     createEReference(this.conditionalActualPortParameterEClass, 0);
/*      */     createEReference(this.conditionalActualPortParameterEClass, 1);
/*      */     createEReference(this.conditionalActualPortParameterEClass, 2);
/*      */     this.variableExportBindingEClass = createEClass(16);
/*      */     createEReference(this.variableExportBindingEClass, 1);
/*      */     createEReference(this.variableExportBindingEClass, 2);
/*      */     this.connectorTypeEClass = createEClass(17);
/*      */     createEReference(this.connectorTypeEClass, 7);
/*      */     createEReference(this.connectorTypeEClass, 8);
/*      */     createEReference(this.connectorTypeEClass, 9);
/*      */     createEReference(this.connectorTypeEClass, 10);
/*      */     createEReference(this.connectorTypeEClass, 11);
/*      */     createEReference(this.connectorTypeEClass, 12);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Interactions\impl\InteractionsPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */