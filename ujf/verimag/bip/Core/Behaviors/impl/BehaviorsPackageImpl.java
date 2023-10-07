/*      */ package ujf.verimag.bip.Core.Behaviors.impl;
/*      */ 
/*      */ import org.eclipse.emf.common.util.Enumerator;
/*      */ import org.eclipse.emf.ecore.EAttribute;
/*      */ import org.eclipse.emf.ecore.EClass;
/*      */ import org.eclipse.emf.ecore.EClassifier;
/*      */ import org.eclipse.emf.ecore.EEnum;
/*      */ import org.eclipse.emf.ecore.EFactory;
/*      */ import org.eclipse.emf.ecore.EPackage;
/*      */ import org.eclipse.emf.ecore.EReference;
/*      */ import org.eclipse.emf.ecore.impl.EPackageImpl;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.ActionsPackage;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Actions.impl.ActionsPackageImpl;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.impl.ExpressionsPackageImpl;
/*      */ import ujf.verimag.bip.Core.Behaviors.AbstractTransition;
/*      */ import ujf.verimag.bip.Core.Behaviors.Action;
/*      */ import ujf.verimag.bip.Core.Behaviors.AtomType;
/*      */ import ujf.verimag.bip.Core.Behaviors.Behavior;
/*      */ import ujf.verimag.bip.Core.Behaviors.BehaviorsFactory;
/*      */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*      */ import ujf.verimag.bip.Core.Behaviors.Binding;
/*      */ import ujf.verimag.bip.Core.Behaviors.BipType;
/*      */ import ujf.verimag.bip.Core.Behaviors.ComponentType;
/*      */ import ujf.verimag.bip.Core.Behaviors.Constant;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataParameter;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataType;
/*      */ import ujf.verimag.bip.Core.Behaviors.DataTypedElement;
/*      */ import ujf.verimag.bip.Core.Behaviors.DefinitionBinding;
/*      */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*      */ import ujf.verimag.bip.Core.Behaviors.InterfaceVariable;
/*      */ import ujf.verimag.bip.Core.Behaviors.MultiTransition;
/*      */ import ujf.verimag.bip.Core.Behaviors.NamedElement;
/*      */ import ujf.verimag.bip.Core.Behaviors.ParameterDirectionKind;
/*      */ import ujf.verimag.bip.Core.Behaviors.ParameterizedElement;
/*      */ import ujf.verimag.bip.Core.Behaviors.PartType;
/*      */ import ujf.verimag.bip.Core.Behaviors.PetriNet;
/*      */ import ujf.verimag.bip.Core.Behaviors.Port;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortDefinition;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortDefinitionReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.PortType;
/*      */ import ujf.verimag.bip.Core.Behaviors.State;
/*      */ import ujf.verimag.bip.Core.Behaviors.Transition;
/*      */ import ujf.verimag.bip.Core.Behaviors.TransitionAlternative;
/*      */ import ujf.verimag.bip.Core.Behaviors.Variable;
/*      */ import ujf.verimag.bip.Core.Behaviors.VariableBinding;
/*      */ import ujf.verimag.bip.Core.Behaviors.VariableDefinitionBinding;
/*      */ import ujf.verimag.bip.Core.Interactions.InteractionsPackage;
/*      */ import ujf.verimag.bip.Core.Interactions.impl.InteractionsPackageImpl;
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
/*      */ public class BehaviorsPackageImpl
/*      */   extends EPackageImpl
/*      */   implements BehaviorsPackage
/*      */ {
/*   99 */   private EClass portDefinitionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  106 */   private EClass atomTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  113 */   private EClass componentTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  120 */   private EClass partTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  127 */   private EClass bipTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   private EClass parameterizedElementEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  141 */   private EClass dataParameterEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  148 */   private EClass dataTypedElementEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  155 */   private EClass dataTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  162 */   private EClass variableEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  169 */   private EClass expressionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  176 */   private EClass actionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  183 */   private EClass portEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  190 */   private EClass bindingEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  197 */   private EClass portTypeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  204 */   private EClass variableBindingEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  211 */   private EClass interfaceVariableEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  218 */   private EClass abstractTransitionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  225 */   private EClass stateEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  232 */   private EClass transitionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  239 */   private EClass transitionAlternativeEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  246 */   private EClass constantEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  253 */   private EClass behaviorEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  260 */   private EClass petriNetEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  267 */   private EClass definitionBindingEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  274 */   private EClass portDefinitionReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  281 */   private EClass multiTransitionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  288 */   private EClass variableDefinitionBindingEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  295 */   private EClass namedElementEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  302 */   private EEnum parameterDirectionKindEEnum = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private BehaviorsPackageImpl() {
/*  321 */     super("http:///ujf/verimag/bip/Core/Behaviors.ecore", (EFactory)BehaviorsFactory.eINSTANCE);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1261 */     this.isCreated = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1398 */     this.isInitialized = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isInited = false;
/*      */   
/*      */   private boolean isCreated;
/*      */ 
/*      */   
/*      */   public void initializePackageContents() {
/* 1409 */     if (this.isInitialized)
/* 1410 */       return;  this.isInitialized = true;
/*      */ 
/*      */     
/* 1413 */     setName("Behaviors");
/* 1414 */     setNsPrefix("ujf.verimag.bip.Core.Behaviors");
/* 1415 */     setNsURI("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/*      */ 
/*      */     
/* 1418 */     InteractionsPackage theInteractionsPackage = (InteractionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore");
/* 1419 */     PrioritiesPackage thePrioritiesPackage = (PrioritiesPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore");
/* 1420 */     ContractsPackage theContractsPackage = (ContractsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore");
/* 1421 */     ModulesPackage theModulesPackage = (ModulesPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore");
/* 1422 */     TraceabilityPackage theTraceabilityPackage = (TraceabilityPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore");
/* 1423 */     PortExpressionsPackage thePortExpressionsPackage = (PortExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore");
/* 1424 */     TimePackage theTimePackage = (TimePackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1431 */     this.portDefinitionEClass.getESuperTypes().add(getNamedElement());
/* 1432 */     this.atomTypeEClass.getESuperTypes().add(getComponentType());
/* 1433 */     this.componentTypeEClass.getESuperTypes().add(getPartType());
/* 1434 */     this.partTypeEClass.getESuperTypes().add(getBipType());
/* 1435 */     this.bipTypeEClass.getESuperTypes().add(getNamedElement());
/* 1436 */     this.bipTypeEClass.getESuperTypes().add(getParameterizedElement());
/* 1437 */     this.bipTypeEClass.getESuperTypes().add(theTraceabilityPackage.getTraceableElement());
/* 1438 */     this.dataParameterEClass.getESuperTypes().add(getDataTypedElement());
/* 1439 */     this.dataTypedElementEClass.getESuperTypes().add(getNamedElement());
/* 1440 */     this.variableEClass.getESuperTypes().add(getDataTypedElement());
/* 1441 */     this.expressionEClass.getESuperTypes().add(getAction());
/* 1442 */     this.portEClass.getESuperTypes().add(getNamedElement());
/* 1443 */     this.portTypeEClass.getESuperTypes().add(getBipType());
/* 1444 */     this.interfaceVariableEClass.getESuperTypes().add(getDataTypedElement());
/* 1445 */     this.abstractTransitionEClass.getESuperTypes().add(getNamedElement());
/* 1446 */     this.stateEClass.getESuperTypes().add(getNamedElement());
/* 1447 */     this.transitionEClass.getESuperTypes().add(getAbstractTransition());
/* 1448 */     this.constantEClass.getESuperTypes().add(getVariable());
/* 1449 */     this.petriNetEClass.getESuperTypes().add(getBehavior());
/* 1450 */     this.definitionBindingEClass.getESuperTypes().add(getBinding());
/* 1451 */     this.portDefinitionReferenceEClass.getESuperTypes().add(thePortExpressionsPackage.getPortReference());
/* 1452 */     this.multiTransitionEClass.getESuperTypes().add(getAbstractTransition());
/* 1453 */     this.variableDefinitionBindingEClass.getESuperTypes().add(getVariableBinding());
/*      */ 
/*      */     
/* 1456 */     initEClass(this.portDefinitionEClass, PortDefinition.class, "PortDefinition", false, false, true);
/* 1457 */     initEReference(getPortDefinition_AtomType(), (EClassifier)getAtomType(), getAtomType_PortDefinition(), "atomType", null, 0, 1, PortDefinition.class, false, false, true, false, false, false, false, false, false);
/* 1458 */     initEReference(getPortDefinition_ConnectorType(), (EClassifier)theInteractionsPackage.getConnectorType(), theInteractionsPackage.getConnectorType_PortDefinition(), "connectorType", null, 0, 1, PortDefinition.class, false, false, true, false, false, false, false, false, false);
/* 1459 */     initEReference(getPortDefinition_Type(), (EClassifier)getPortType(), null, "type", null, 1, 1, PortDefinition.class, false, false, true, false, true, false, false, false, false);
/* 1460 */     initEReference(getPortDefinition_ExposedVariable(), (EClassifier)getVariable(), null, "exposedVariable", null, 0, -1, PortDefinition.class, false, false, true, false, true, false, false, false, true);
/*      */     
/* 1462 */     initEClass(this.atomTypeEClass, AtomType.class, "AtomType", false, false, true);
/* 1463 */     initEReference(getAtomType_Behavior(), (EClassifier)getBehavior(), getBehavior_AtomType(), "behavior", null, 1, 1, AtomType.class, false, false, true, true, false, false, false, false, false);
/* 1464 */     initEReference(getAtomType_Variable(), (EClassifier)getVariable(), null, "variable", null, 0, -1, AtomType.class, false, false, true, true, false, false, true, false, false);
/* 1465 */     initEReference(getAtomType_PortDefinition(), (EClassifier)getPortDefinition(), getPortDefinition_AtomType(), "portDefinition", null, 0, -1, AtomType.class, false, false, true, true, false, false, true, false, false);
/*      */     
/* 1467 */     initEClass(this.componentTypeEClass, ComponentType.class, "ComponentType", true, false, true);
/* 1468 */     initEReference(getComponentType_Port(), (EClassifier)getPort(), getPort_ComponentType(), "port", null, 0, -1, ComponentType.class, false, false, true, true, false, false, true, false, false);
/* 1469 */     initEReference(getComponentType_PriorityRule(), (EClassifier)thePrioritiesPackage.getPriorityRule(), thePrioritiesPackage.getPriorityRule_CompoundType(), "priorityRule", null, 0, -1, ComponentType.class, false, false, true, true, false, false, true, false, false);
/* 1470 */     initEReference(getComponentType_InterfaceVariable(), (EClassifier)getInterfaceVariable(), getInterfaceVariable_ComponentType(), "interfaceVariable", null, 0, -1, ComponentType.class, false, false, true, true, false, false, true, false, false);
/* 1471 */     initEReference(getComponentType_Contract(), (EClassifier)theContractsPackage.getContract(), theContractsPackage.getContract_Contracted(), "contract", null, 0, -1, ComponentType.class, false, false, true, false, true, false, true, false, false);
/* 1472 */     initEAttribute(getComponentType_IsMultishot(), (EClassifier)this.ecorePackage.getEBoolean(), "isMultishot", null, 1, 1, ComponentType.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1474 */     initEClass(this.partTypeEClass, PartType.class, "PartType", true, false, true);
/* 1475 */     initEReference(getPartType_Constant(), (EClassifier)getConstant(), getConstant_PartType(), "constant", null, 0, -1, PartType.class, false, false, true, true, false, false, true, false, false);
/* 1476 */     initEReference(getPartType_Declaration(), (EClassifier)theModulesPackage.getDeclaration(), null, "declaration", null, 0, -1, PartType.class, false, false, true, true, false, false, true, false, false);
/*      */     
/* 1478 */     initEClass(this.bipTypeEClass, BipType.class, "BipType", true, false, true);
/* 1479 */     initEReference(getBipType_Module(), (EClassifier)theModulesPackage.getModule(), theModulesPackage.getModule_BipType(), "module", null, 1, 1, BipType.class, false, false, true, false, false, false, false, false, false);
/*      */     
/* 1481 */     initEClass(this.parameterizedElementEClass, ParameterizedElement.class, "ParameterizedElement", true, false, true);
/* 1482 */     initEReference(getParameterizedElement_DataParameter(), (EClassifier)getDataParameter(), getDataParameter_ParameterizedElement(), "dataParameter", null, 0, -1, ParameterizedElement.class, false, false, true, true, false, false, true, false, true);
/*      */     
/* 1484 */     initEClass(this.dataParameterEClass, DataParameter.class, "DataParameter", false, false, true);
/* 1485 */     initEAttribute(getDataParameter_Direction(), (EClassifier)getParameterDirectionKind(), "direction", null, 1, 1, DataParameter.class, false, false, true, false, false, false, false, false);
/* 1486 */     initEReference(getDataParameter_ParameterizedElement(), (EClassifier)getParameterizedElement(), getParameterizedElement_DataParameter(), "parameterizedElement", null, 1, 1, DataParameter.class, false, false, true, false, false, false, false, false, false);
/*      */     
/* 1488 */     initEClass(this.dataTypedElementEClass, DataTypedElement.class, "DataTypedElement", true, false, true);
/* 1489 */     initEReference(getDataTypedElement_Type(), (EClassifier)getDataType(), null, "type", null, 0, 1, DataTypedElement.class, false, false, true, false, true, false, false, false, false);
/* 1490 */     initEAttribute(getDataTypedElement_OpaqueTypeName(), (EClassifier)this.ecorePackage.getEString(), "OpaqueTypeName", null, 0, 1, DataTypedElement.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1492 */     initEClass(this.dataTypeEClass, DataType.class, "DataType", true, false, true);
/*      */     
/* 1494 */     initEClass(this.variableEClass, Variable.class, "Variable", false, false, true);
/* 1495 */     initEReference(getVariable_ConnectorType(), (EClassifier)theInteractionsPackage.getConnectorType(), theInteractionsPackage.getConnectorType_Variable(), "connectorType", null, 0, 1, Variable.class, false, false, true, false, false, false, false, false, false);
/* 1496 */     initEReference(getVariable_InitialValue(), (EClassifier)getExpression(), null, "initialValue", null, 0, 1, Variable.class, false, false, true, true, false, false, false, false, false);
/* 1497 */     initEAttribute(getVariable_IsExternal(), (EClassifier)this.ecorePackage.getEBoolean(), "isExternal", null, 1, 1, Variable.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1499 */     initEClass(this.expressionEClass, Expression.class, "Expression", true, false, true);
/*      */     
/* 1501 */     initEClass(this.actionEClass, Action.class, "Action", true, false, true);
/*      */     
/* 1503 */     initEClass(this.portEClass, Port.class, "Port", false, false, true);
/* 1504 */     initEReference(getPort_ComponentType(), (EClassifier)getComponentType(), getComponentType_Port(), "componentType", null, 0, 1, Port.class, false, false, true, false, false, false, false, false, false);
/* 1505 */     initEReference(getPort_Binding(), (EClassifier)getBinding(), getBinding_OuterPort(), "binding", null, 1, 1, Port.class, false, false, true, true, false, false, false, false, false);
/* 1506 */     initEReference(getPort_Type(), (EClassifier)getPortType(), null, "type", null, 0, 1, Port.class, false, false, true, false, true, false, false, false, false);
/* 1507 */     initEReference(getPort_ConnectorType(), (EClassifier)theInteractionsPackage.getConnectorType(), theInteractionsPackage.getConnectorType_Port(), "connectorType", null, 0, 1, Port.class, false, false, true, false, false, false, false, false, false);
/*      */     
/* 1509 */     initEClass(this.bindingEClass, Binding.class, "Binding", true, false, true);
/* 1510 */     initEReference(getBinding_OuterPort(), (EClassifier)getPort(), getPort_Binding(), "outerPort", null, 1, 1, Binding.class, false, false, true, false, false, false, false, false, false);
/*      */     
/* 1512 */     initEClass(this.portTypeEClass, PortType.class, "PortType", false, false, true);
/*      */     
/* 1514 */     initEClass(this.variableBindingEClass, VariableBinding.class, "VariableBinding", true, false, true);
/* 1515 */     initEReference(getVariableBinding_InterfaceVariable(), (EClassifier)getInterfaceVariable(), getInterfaceVariable_VariableBinding(), "interfaceVariable", null, 1, 1, VariableBinding.class, false, false, true, false, false, false, false, false, false);
/*      */     
/* 1517 */     initEClass(this.interfaceVariableEClass, InterfaceVariable.class, "InterfaceVariable", false, false, true);
/* 1518 */     initEReference(getInterfaceVariable_ComponentType(), (EClassifier)getComponentType(), getComponentType_InterfaceVariable(), "componentType", null, 1, 1, InterfaceVariable.class, false, false, true, false, false, false, false, false, false);
/* 1519 */     initEReference(getInterfaceVariable_VariableBinding(), (EClassifier)getVariableBinding(), getVariableBinding_InterfaceVariable(), "variableBinding", null, 0, -1, InterfaceVariable.class, false, false, true, true, false, false, true, false, false);
/*      */     
/* 1521 */     initEClass(this.abstractTransitionEClass, AbstractTransition.class, "AbstractTransition", true, false, true);
/* 1522 */     initEReference(getAbstractTransition_Origin(), (EClassifier)getState(), getState_Outgoing(), "origin", null, 1, -1, AbstractTransition.class, false, false, true, false, true, false, true, false, false);
/* 1523 */     initEReference(getAbstractTransition_Guard(), (EClassifier)getExpression(), null, "guard", null, 0, 1, AbstractTransition.class, false, false, true, true, false, false, false, false, false);
/* 1524 */     initEReference(getAbstractTransition_Action(), (EClassifier)getAction(), null, "action", null, 0, 1, AbstractTransition.class, false, false, true, true, false, false, false, false, false);
/* 1525 */     initEReference(getAbstractTransition_Trigger(), (EClassifier)thePortExpressionsPackage.getPortExpression(), null, "trigger", null, 1, 1, AbstractTransition.class, false, false, true, true, false, false, false, false, false);
/* 1526 */     initEReference(getAbstractTransition_TimeReset(), (EClassifier)theTimePackage.getTimeReset(), null, "timeReset", null, 0, 1, AbstractTransition.class, false, false, true, true, false, false, false, false, false);
/* 1527 */     initEReference(getAbstractTransition_TimeSpecification(), (EClassifier)theTimePackage.getTimeSpecification(), theTimePackage.getTimeSpecification_Transition(), "timeSpecification", null, 0, 1, AbstractTransition.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1529 */     initEClass(this.stateEClass, State.class, "State", false, false, true);
/* 1530 */     initEReference(getState_Incoming(), (EClassifier)getTransition(), getTransition_Destination(), "incoming", null, 0, -1, State.class, false, false, true, false, true, false, true, false, false);
/* 1531 */     initEReference(getState_AlternativeIncoming(), (EClassifier)getTransitionAlternative(), getTransitionAlternative_State(), "alternativeIncoming", null, 0, -1, State.class, false, false, true, false, true, false, true, false, false);
/* 1532 */     initEReference(getState_Outgoing(), (EClassifier)getAbstractTransition(), getAbstractTransition_Origin(), "outgoing", null, 0, -1, State.class, false, false, true, false, true, false, true, false, false);
/*      */     
/* 1534 */     initEClass(this.transitionEClass, Transition.class, "Transition", false, false, true);
/* 1535 */     initEReference(getTransition_Destination(), (EClassifier)getState(), getState_Incoming(), "destination", null, 1, -1, Transition.class, false, false, true, false, true, false, true, false, false);
/*      */     
/* 1537 */     initEClass(this.transitionAlternativeEClass, TransitionAlternative.class, "TransitionAlternative", false, false, true);
/* 1538 */     initEReference(getTransitionAlternative_Condition(), (EClassifier)getExpression(), null, "condition", null, 0, 1, TransitionAlternative.class, false, false, true, true, false, false, false, false, false);
/* 1539 */     initEReference(getTransitionAlternative_State(), (EClassifier)getState(), getState_AlternativeIncoming(), "state", null, 1, -1, TransitionAlternative.class, false, false, true, false, true, false, true, false, false);
/*      */     
/* 1541 */     initEClass(this.constantEClass, Constant.class, "Constant", false, false, true);
/* 1542 */     initEReference(getConstant_PartType(), (EClassifier)getPartType(), getPartType_Constant(), "partType", null, 0, 1, Constant.class, false, false, true, false, false, false, false, false, false);
/*      */     
/* 1544 */     initEClass(this.behaviorEClass, Behavior.class, "Behavior", true, false, true);
/* 1545 */     initEReference(getBehavior_AtomType(), (EClassifier)getAtomType(), getAtomType_Behavior(), "atomType", null, 1, 1, Behavior.class, false, false, true, false, false, false, false, false, false);
/*      */     
/* 1547 */     initEClass(this.petriNetEClass, PetriNet.class, "PetriNet", false, false, true);
/* 1548 */     initEReference(getPetriNet_State(), (EClassifier)getState(), null, "state", null, 1, -1, PetriNet.class, false, false, true, true, false, false, true, false, false);
/* 1549 */     initEReference(getPetriNet_Transition(), (EClassifier)getTransition(), null, "transition", null, 0, -1, PetriNet.class, false, false, true, true, false, false, true, false, false);
/* 1550 */     initEReference(getPetriNet_InitialState(), (EClassifier)getState(), null, "initialState", null, 1, -1, PetriNet.class, false, false, true, false, true, false, false, false, false);
/* 1551 */     initEReference(getPetriNet_Initialization(), (EClassifier)getAction(), null, "initialization", null, 0, 1, PetriNet.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1553 */     initEClass(this.definitionBindingEClass, DefinitionBinding.class, "DefinitionBinding", false, false, true);
/* 1554 */     initEReference(getDefinitionBinding_Definition(), (EClassifier)getPortDefinition(), null, "definition", null, 1, 1, DefinitionBinding.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1556 */     initEClass(this.portDefinitionReferenceEClass, PortDefinitionReference.class, "PortDefinitionReference", false, false, true);
/* 1557 */     initEReference(getPortDefinitionReference_Target(), (EClassifier)getPortDefinition(), null, "target", null, 1, 1, PortDefinitionReference.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1559 */     initEClass(this.multiTransitionEClass, MultiTransition.class, "MultiTransition", false, false, true);
/* 1560 */     initEReference(getMultiTransition_Alternative(), (EClassifier)getTransitionAlternative(), null, "alternative", null, 1, -1, MultiTransition.class, false, false, true, true, false, false, true, false, false);
/*      */     
/* 1562 */     initEClass(this.variableDefinitionBindingEClass, VariableDefinitionBinding.class, "VariableDefinitionBinding", false, false, true);
/* 1563 */     initEReference(getVariableDefinitionBinding_Variable(), (EClassifier)getVariable(), null, "variable", null, 1, 1, VariableDefinitionBinding.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1565 */     initEClass(this.namedElementEClass, NamedElement.class, "NamedElement", true, false, true);
/* 1566 */     initEAttribute(getNamedElement_Name(), (EClassifier)this.ecorePackage.getEString(), "name", null, 1, 1, NamedElement.class, false, false, true, false, false, false, false, false);
/* 1567 */     initEAttribute(getNamedElement_Scope(), (EClassifier)this.ecorePackage.getEString(), "scope", null, 1, 1, NamedElement.class, false, false, true, false, false, false, false, false);
/*      */ 
/*      */     
/* 1570 */     initEEnum(this.parameterDirectionKindEEnum, ParameterDirectionKind.class, "ParameterDirectionKind");
/* 1571 */     addEEnumLiteral(this.parameterDirectionKindEEnum, (Enumerator)ParameterDirectionKind.INOUT);
/* 1572 */     addEEnumLiteral(this.parameterDirectionKindEEnum, (Enumerator)ParameterDirectionKind.IN);
/* 1573 */     addEEnumLiteral(this.parameterDirectionKindEEnum, (Enumerator)ParameterDirectionKind.OUT);
/*      */ 
/*      */     
/* 1576 */     createResource("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/*      */   }
/*      */   
/*      */   private boolean isInitialized;
/*      */   
/*      */   public static BehaviorsPackage init() {
/*      */     if (isInited)
/*      */       return (BehaviorsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore"); 
/*      */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/Behaviors.ecore") : new BehaviorsPackageImpl();
/*      */     isInited = true;
/*      */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") : (ModulesPackageImpl)ModulesPackage.eINSTANCE;
/*      */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") : (InteractionsPackageImpl)InteractionsPackage.eINSTANCE;
/*      */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") : (PrioritiesPackageImpl)PrioritiesPackage.eINSTANCE;
/*      */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : (PortExpressionsPackageImpl)PortExpressionsPackage.eINSTANCE;
/*      */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : (ActionsPackageImpl)ActionsPackage.eINSTANCE;
/*      */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : (ExpressionsPackageImpl)ExpressionsPackage.eINSTANCE;
/*      */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") : (TimePackageImpl)TimePackage.eINSTANCE;
/*      */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") : (ContractsPackageImpl)ContractsPackage.eINSTANCE;
/*      */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") : (TraceabilityPackageImpl)TraceabilityPackage.eINSTANCE;
/*      */     theBehaviorsPackage.createPackageContents();
/*      */     theModulesPackage.createPackageContents();
/*      */     theInteractionsPackage.createPackageContents();
/*      */     thePrioritiesPackage.createPackageContents();
/*      */     thePortExpressionsPackage.createPackageContents();
/*      */     theActionsPackage.createPackageContents();
/*      */     theExpressionsPackage.createPackageContents();
/*      */     theTimePackage.createPackageContents();
/*      */     theContractsPackage.createPackageContents();
/*      */     theTraceabilityPackage.createPackageContents();
/*      */     theBehaviorsPackage.initializePackageContents();
/*      */     theModulesPackage.initializePackageContents();
/*      */     theInteractionsPackage.initializePackageContents();
/*      */     thePrioritiesPackage.initializePackageContents();
/*      */     thePortExpressionsPackage.initializePackageContents();
/*      */     theActionsPackage.initializePackageContents();
/*      */     theExpressionsPackage.initializePackageContents();
/*      */     theTimePackage.initializePackageContents();
/*      */     theContractsPackage.initializePackageContents();
/*      */     theTraceabilityPackage.initializePackageContents();
/*      */     theBehaviorsPackage.freeze();
/*      */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Core/Behaviors.ecore", theBehaviorsPackage);
/*      */     return theBehaviorsPackage;
/*      */   }
/*      */   
/*      */   public EClass getPortDefinition() {
/*      */     return this.portDefinitionEClass;
/*      */   }
/*      */   
/*      */   public EReference getPortDefinition_AtomType() {
/*      */     return (EReference)this.portDefinitionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getPortDefinition_ConnectorType() {
/*      */     return (EReference)this.portDefinitionEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getPortDefinition_Type() {
/*      */     return (EReference)this.portDefinitionEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EReference getPortDefinition_ExposedVariable() {
/*      */     return (EReference)this.portDefinitionEClass.getEStructuralFeatures().get(3);
/*      */   }
/*      */   
/*      */   public EClass getAtomType() {
/*      */     return this.atomTypeEClass;
/*      */   }
/*      */   
/*      */   public EReference getAtomType_Behavior() {
/*      */     return (EReference)this.atomTypeEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getAtomType_Variable() {
/*      */     return (EReference)this.atomTypeEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getAtomType_PortDefinition() {
/*      */     return (EReference)this.atomTypeEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EClass getComponentType() {
/*      */     return this.componentTypeEClass;
/*      */   }
/*      */   
/*      */   public EReference getComponentType_Port() {
/*      */     return (EReference)this.componentTypeEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getComponentType_PriorityRule() {
/*      */     return (EReference)this.componentTypeEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getComponentType_InterfaceVariable() {
/*      */     return (EReference)this.componentTypeEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EReference getComponentType_Contract() {
/*      */     return (EReference)this.componentTypeEClass.getEStructuralFeatures().get(3);
/*      */   }
/*      */   
/*      */   public EAttribute getComponentType_IsMultishot() {
/*      */     return (EAttribute)this.componentTypeEClass.getEStructuralFeatures().get(4);
/*      */   }
/*      */   
/*      */   public EClass getPartType() {
/*      */     return this.partTypeEClass;
/*      */   }
/*      */   
/*      */   public EReference getPartType_Constant() {
/*      */     return (EReference)this.partTypeEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getPartType_Declaration() {
/*      */     return (EReference)this.partTypeEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getBipType() {
/*      */     return this.bipTypeEClass;
/*      */   }
/*      */   
/*      */   public EReference getBipType_Module() {
/*      */     return (EReference)this.bipTypeEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getParameterizedElement() {
/*      */     return this.parameterizedElementEClass;
/*      */   }
/*      */   
/*      */   public EReference getParameterizedElement_DataParameter() {
/*      */     return (EReference)this.parameterizedElementEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getDataParameter() {
/*      */     return this.dataParameterEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getDataParameter_Direction() {
/*      */     return (EAttribute)this.dataParameterEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getDataParameter_ParameterizedElement() {
/*      */     return (EReference)this.dataParameterEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getDataTypedElement() {
/*      */     return this.dataTypedElementEClass;
/*      */   }
/*      */   
/*      */   public EReference getDataTypedElement_Type() {
/*      */     return (EReference)this.dataTypedElementEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EAttribute getDataTypedElement_OpaqueTypeName() {
/*      */     return (EAttribute)this.dataTypedElementEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getDataType() {
/*      */     return this.dataTypeEClass;
/*      */   }
/*      */   
/*      */   public EClass getVariable() {
/*      */     return this.variableEClass;
/*      */   }
/*      */   
/*      */   public EReference getVariable_ConnectorType() {
/*      */     return (EReference)this.variableEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getVariable_InitialValue() {
/*      */     return (EReference)this.variableEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EAttribute getVariable_IsExternal() {
/*      */     return (EAttribute)this.variableEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EClass getExpression() {
/*      */     return this.expressionEClass;
/*      */   }
/*      */   
/*      */   public EClass getAction() {
/*      */     return this.actionEClass;
/*      */   }
/*      */   
/*      */   public EClass getPort() {
/*      */     return this.portEClass;
/*      */   }
/*      */   
/*      */   public EReference getPort_ComponentType() {
/*      */     return (EReference)this.portEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getPort_Binding() {
/*      */     return (EReference)this.portEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getPort_Type() {
/*      */     return (EReference)this.portEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EReference getPort_ConnectorType() {
/*      */     return (EReference)this.portEClass.getEStructuralFeatures().get(3);
/*      */   }
/*      */   
/*      */   public EClass getBinding() {
/*      */     return this.bindingEClass;
/*      */   }
/*      */   
/*      */   public EReference getBinding_OuterPort() {
/*      */     return (EReference)this.bindingEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getPortType() {
/*      */     return this.portTypeEClass;
/*      */   }
/*      */   
/*      */   public EClass getVariableBinding() {
/*      */     return this.variableBindingEClass;
/*      */   }
/*      */   
/*      */   public EReference getVariableBinding_InterfaceVariable() {
/*      */     return (EReference)this.variableBindingEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getInterfaceVariable() {
/*      */     return this.interfaceVariableEClass;
/*      */   }
/*      */   
/*      */   public EReference getInterfaceVariable_ComponentType() {
/*      */     return (EReference)this.interfaceVariableEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getInterfaceVariable_VariableBinding() {
/*      */     return (EReference)this.interfaceVariableEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getAbstractTransition() {
/*      */     return this.abstractTransitionEClass;
/*      */   }
/*      */   
/*      */   public EReference getAbstractTransition_Origin() {
/*      */     return (EReference)this.abstractTransitionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getAbstractTransition_Guard() {
/*      */     return (EReference)this.abstractTransitionEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getAbstractTransition_Action() {
/*      */     return (EReference)this.abstractTransitionEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EReference getAbstractTransition_Trigger() {
/*      */     return (EReference)this.abstractTransitionEClass.getEStructuralFeatures().get(3);
/*      */   }
/*      */   
/*      */   public EReference getAbstractTransition_TimeReset() {
/*      */     return (EReference)this.abstractTransitionEClass.getEStructuralFeatures().get(4);
/*      */   }
/*      */   
/*      */   public EReference getAbstractTransition_TimeSpecification() {
/*      */     return (EReference)this.abstractTransitionEClass.getEStructuralFeatures().get(5);
/*      */   }
/*      */   
/*      */   public EClass getState() {
/*      */     return this.stateEClass;
/*      */   }
/*      */   
/*      */   public EReference getState_Incoming() {
/*      */     return (EReference)this.stateEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getState_AlternativeIncoming() {
/*      */     return (EReference)this.stateEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getState_Outgoing() {
/*      */     return (EReference)this.stateEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EClass getTransition() {
/*      */     return this.transitionEClass;
/*      */   }
/*      */   
/*      */   public EReference getTransition_Destination() {
/*      */     return (EReference)this.transitionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getTransitionAlternative() {
/*      */     return this.transitionAlternativeEClass;
/*      */   }
/*      */   
/*      */   public EReference getTransitionAlternative_Condition() {
/*      */     return (EReference)this.transitionAlternativeEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getTransitionAlternative_State() {
/*      */     return (EReference)this.transitionAlternativeEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getConstant() {
/*      */     return this.constantEClass;
/*      */   }
/*      */   
/*      */   public EReference getConstant_PartType() {
/*      */     return (EReference)this.constantEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getBehavior() {
/*      */     return this.behaviorEClass;
/*      */   }
/*      */   
/*      */   public EReference getBehavior_AtomType() {
/*      */     return (EReference)this.behaviorEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getPetriNet() {
/*      */     return this.petriNetEClass;
/*      */   }
/*      */   
/*      */   public EReference getPetriNet_State() {
/*      */     return (EReference)this.petriNetEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getPetriNet_Transition() {
/*      */     return (EReference)this.petriNetEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getPetriNet_InitialState() {
/*      */     return (EReference)this.petriNetEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EReference getPetriNet_Initialization() {
/*      */     return (EReference)this.petriNetEClass.getEStructuralFeatures().get(3);
/*      */   }
/*      */   
/*      */   public EClass getDefinitionBinding() {
/*      */     return this.definitionBindingEClass;
/*      */   }
/*      */   
/*      */   public EReference getDefinitionBinding_Definition() {
/*      */     return (EReference)this.definitionBindingEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getPortDefinitionReference() {
/*      */     return this.portDefinitionReferenceEClass;
/*      */   }
/*      */   
/*      */   public EReference getPortDefinitionReference_Target() {
/*      */     return (EReference)this.portDefinitionReferenceEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getMultiTransition() {
/*      */     return this.multiTransitionEClass;
/*      */   }
/*      */   
/*      */   public EReference getMultiTransition_Alternative() {
/*      */     return (EReference)this.multiTransitionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getVariableDefinitionBinding() {
/*      */     return this.variableDefinitionBindingEClass;
/*      */   }
/*      */   
/*      */   public EReference getVariableDefinitionBinding_Variable() {
/*      */     return (EReference)this.variableDefinitionBindingEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getNamedElement() {
/*      */     return this.namedElementEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getNamedElement_Name() {
/*      */     return (EAttribute)this.namedElementEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EAttribute getNamedElement_Scope() {
/*      */     return (EAttribute)this.namedElementEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EEnum getParameterDirectionKind() {
/*      */     return this.parameterDirectionKindEEnum;
/*      */   }
/*      */   
/*      */   public BehaviorsFactory getBehaviorsFactory() {
/*      */     return (BehaviorsFactory)getEFactoryInstance();
/*      */   }
/*      */   
/*      */   public void createPackageContents() {
/*      */     if (this.isCreated)
/*      */       return; 
/*      */     this.isCreated = true;
/*      */     this.portDefinitionEClass = createEClass(0);
/*      */     createEReference(this.portDefinitionEClass, 2);
/*      */     createEReference(this.portDefinitionEClass, 3);
/*      */     createEReference(this.portDefinitionEClass, 4);
/*      */     createEReference(this.portDefinitionEClass, 5);
/*      */     this.atomTypeEClass = createEClass(1);
/*      */     createEReference(this.atomTypeEClass, 12);
/*      */     createEReference(this.atomTypeEClass, 13);
/*      */     createEReference(this.atomTypeEClass, 14);
/*      */     this.componentTypeEClass = createEClass(2);
/*      */     createEReference(this.componentTypeEClass, 7);
/*      */     createEReference(this.componentTypeEClass, 8);
/*      */     createEReference(this.componentTypeEClass, 9);
/*      */     createEReference(this.componentTypeEClass, 10);
/*      */     createEAttribute(this.componentTypeEClass, 11);
/*      */     this.partTypeEClass = createEClass(3);
/*      */     createEReference(this.partTypeEClass, 5);
/*      */     createEReference(this.partTypeEClass, 6);
/*      */     this.bipTypeEClass = createEClass(4);
/*      */     createEReference(this.bipTypeEClass, 4);
/*      */     this.parameterizedElementEClass = createEClass(5);
/*      */     createEReference(this.parameterizedElementEClass, 0);
/*      */     this.dataParameterEClass = createEClass(6);
/*      */     createEAttribute(this.dataParameterEClass, 4);
/*      */     createEReference(this.dataParameterEClass, 5);
/*      */     this.dataTypedElementEClass = createEClass(7);
/*      */     createEReference(this.dataTypedElementEClass, 2);
/*      */     createEAttribute(this.dataTypedElementEClass, 3);
/*      */     this.dataTypeEClass = createEClass(8);
/*      */     this.variableEClass = createEClass(9);
/*      */     createEReference(this.variableEClass, 4);
/*      */     createEReference(this.variableEClass, 5);
/*      */     createEAttribute(this.variableEClass, 6);
/*      */     this.expressionEClass = createEClass(10);
/*      */     this.actionEClass = createEClass(11);
/*      */     this.portEClass = createEClass(12);
/*      */     createEReference(this.portEClass, 2);
/*      */     createEReference(this.portEClass, 3);
/*      */     createEReference(this.portEClass, 4);
/*      */     createEReference(this.portEClass, 5);
/*      */     this.bindingEClass = createEClass(13);
/*      */     createEReference(this.bindingEClass, 0);
/*      */     this.portTypeEClass = createEClass(14);
/*      */     this.variableBindingEClass = createEClass(15);
/*      */     createEReference(this.variableBindingEClass, 0);
/*      */     this.interfaceVariableEClass = createEClass(16);
/*      */     createEReference(this.interfaceVariableEClass, 4);
/*      */     createEReference(this.interfaceVariableEClass, 5);
/*      */     this.abstractTransitionEClass = createEClass(17);
/*      */     createEReference(this.abstractTransitionEClass, 2);
/*      */     createEReference(this.abstractTransitionEClass, 3);
/*      */     createEReference(this.abstractTransitionEClass, 4);
/*      */     createEReference(this.abstractTransitionEClass, 5);
/*      */     createEReference(this.abstractTransitionEClass, 6);
/*      */     createEReference(this.abstractTransitionEClass, 7);
/*      */     this.stateEClass = createEClass(18);
/*      */     createEReference(this.stateEClass, 2);
/*      */     createEReference(this.stateEClass, 3);
/*      */     createEReference(this.stateEClass, 4);
/*      */     this.transitionEClass = createEClass(19);
/*      */     createEReference(this.transitionEClass, 8);
/*      */     this.transitionAlternativeEClass = createEClass(20);
/*      */     createEReference(this.transitionAlternativeEClass, 0);
/*      */     createEReference(this.transitionAlternativeEClass, 1);
/*      */     this.constantEClass = createEClass(21);
/*      */     createEReference(this.constantEClass, 7);
/*      */     this.behaviorEClass = createEClass(22);
/*      */     createEReference(this.behaviorEClass, 0);
/*      */     this.petriNetEClass = createEClass(23);
/*      */     createEReference(this.petriNetEClass, 1);
/*      */     createEReference(this.petriNetEClass, 2);
/*      */     createEReference(this.petriNetEClass, 3);
/*      */     createEReference(this.petriNetEClass, 4);
/*      */     this.definitionBindingEClass = createEClass(24);
/*      */     createEReference(this.definitionBindingEClass, 1);
/*      */     this.portDefinitionReferenceEClass = createEClass(25);
/*      */     createEReference(this.portDefinitionReferenceEClass, 0);
/*      */     this.multiTransitionEClass = createEClass(26);
/*      */     createEReference(this.multiTransitionEClass, 8);
/*      */     this.variableDefinitionBindingEClass = createEClass(27);
/*      */     createEReference(this.variableDefinitionBindingEClass, 1);
/*      */     this.namedElementEClass = createEClass(28);
/*      */     createEAttribute(this.namedElementEClass, 0);
/*      */     createEAttribute(this.namedElementEClass, 1);
/*      */     this.parameterDirectionKindEEnum = createEEnum(29);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\Behaviors\impl\BehaviorsPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */