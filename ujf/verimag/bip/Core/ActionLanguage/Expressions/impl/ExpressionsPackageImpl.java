/*      */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
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
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataNavigationExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterSpecification;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FieldNavigationExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IndexLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerInterfaceVariableReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InterfaceVariableReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.PointerLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RealLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StateReference;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
/*      */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*      */ import ujf.verimag.bip.Core.Behaviors.BehaviorsPackage;
/*      */ import ujf.verimag.bip.Core.Behaviors.impl.BehaviorsPackageImpl;
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
/*      */ public class ExpressionsPackageImpl
/*      */   extends EPackageImpl
/*      */   implements ExpressionsPackage
/*      */ {
/*   92 */   private EClass dataReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   99 */   private EClass dataParameterSpecificationEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  106 */   private EClass innerDataParameterReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  113 */   private EClass dataParameterReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  120 */   private EClass booleanLiteralEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  127 */   private EClass integerLiteralEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   private EClass realLiteralEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  141 */   private EClass stringLiteralEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  148 */   private EClass unaryExpressionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  155 */   private EClass binaryExpressionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  162 */   private EClass indexLiteralEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  169 */   private EClass functionCallExpressionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  176 */   private EClass fieldNavigationExpressionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  183 */   private EClass dataNavigationExpressionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  190 */   private EClass arrayNavigationExpressionEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  197 */   private EClass requiredDataParameterReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  204 */   private EClass stateReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  211 */   private EClass interfaceVariableReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  218 */   private EClass pointerLiteralEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  225 */   private EClass innerInterfaceVariableReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  232 */   private EClass variableReferenceEClass = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  239 */   private EEnum unaryOperatorEEnum = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  246 */   private EEnum binaryOperatorEEnum = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ExpressionsPackageImpl() {
/*  265 */     super("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore", (EFactory)ExpressionsFactory.eINSTANCE);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  855 */     this.isCreated = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  949 */     this.isInitialized = false;
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
/*  960 */     if (this.isInitialized)
/*  961 */       return;  this.isInitialized = true;
/*      */ 
/*      */     
/*  964 */     setName("Expressions");
/*  965 */     setNsPrefix("ujf.verimag.bip.Core.ActionLanguage.Expressions");
/*  966 */     setNsURI("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore");
/*      */ 
/*      */     
/*  969 */     BehaviorsPackage theBehaviorsPackage = (BehaviorsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore");
/*  970 */     InteractionsPackage theInteractionsPackage = (InteractionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  977 */     this.dataReferenceEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  978 */     this.dataParameterSpecificationEClass.getESuperTypes().add(getDataReference());
/*  979 */     this.innerDataParameterReferenceEClass.getESuperTypes().add(getDataParameterSpecification());
/*  980 */     this.dataParameterReferenceEClass.getESuperTypes().add(getDataParameterSpecification());
/*  981 */     this.booleanLiteralEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  982 */     this.integerLiteralEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  983 */     this.realLiteralEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  984 */     this.stringLiteralEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  985 */     this.unaryExpressionEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  986 */     this.binaryExpressionEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  987 */     this.indexLiteralEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  988 */     this.functionCallExpressionEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  989 */     this.fieldNavigationExpressionEClass.getESuperTypes().add(getDataNavigationExpression());
/*  990 */     this.dataNavigationExpressionEClass.getESuperTypes().add(getDataReference());
/*  991 */     this.arrayNavigationExpressionEClass.getESuperTypes().add(getDataNavigationExpression());
/*  992 */     this.requiredDataParameterReferenceEClass.getESuperTypes().add(getDataParameterSpecification());
/*  993 */     this.stateReferenceEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  994 */     this.interfaceVariableReferenceEClass.getESuperTypes().add(getDataReference());
/*  995 */     this.pointerLiteralEClass.getESuperTypes().add(theBehaviorsPackage.getExpression());
/*  996 */     this.innerInterfaceVariableReferenceEClass.getESuperTypes().add(getDataReference());
/*  997 */     this.variableReferenceEClass.getESuperTypes().add(getDataReference());
/*      */ 
/*      */     
/* 1000 */     initEClass(this.dataReferenceEClass, DataReference.class, "DataReference", true, false, true);
/*      */     
/* 1002 */     initEClass(this.dataParameterSpecificationEClass, DataParameterSpecification.class, "DataParameterSpecification", true, false, true);
/* 1003 */     initEReference(getDataParameterSpecification_TargetParameter(), (EClassifier)theBehaviorsPackage.getDataParameter(), null, "targetParameter", null, 1, 1, DataParameterSpecification.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1005 */     initEClass(this.innerDataParameterReferenceEClass, InnerDataParameterReference.class, "InnerDataParameterReference", false, false, true);
/* 1006 */     initEReference(getInnerDataParameterReference_PortReference(), (EClassifier)theInteractionsPackage.getInnerPortReference(), null, "portReference", null, 1, 1, InnerDataParameterReference.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1008 */     initEClass(this.dataParameterReferenceEClass, DataParameterReference.class, "DataParameterReference", false, false, true);
/*      */     
/* 1010 */     initEClass(this.booleanLiteralEClass, BooleanLiteral.class, "BooleanLiteral", false, false, true);
/* 1011 */     initEAttribute(getBooleanLiteral_BValue(), (EClassifier)this.ecorePackage.getEBoolean(), "bValue", null, 1, 1, BooleanLiteral.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1013 */     initEClass(this.integerLiteralEClass, IntegerLiteral.class, "IntegerLiteral", false, false, true);
/* 1014 */     initEAttribute(getIntegerLiteral_IValue(), (EClassifier)this.ecorePackage.getEInt(), "iValue", null, 1, 1, IntegerLiteral.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1016 */     initEClass(this.realLiteralEClass, RealLiteral.class, "RealLiteral", false, false, true);
/* 1017 */     initEAttribute(getRealLiteral_RValue(), (EClassifier)this.ecorePackage.getEString(), "rValue", null, 1, 1, RealLiteral.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1019 */     initEClass(this.stringLiteralEClass, StringLiteral.class, "StringLiteral", false, false, true);
/* 1020 */     initEAttribute(getStringLiteral_SValue(), (EClassifier)this.ecorePackage.getEString(), "sValue", null, 1, 1, StringLiteral.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1022 */     initEClass(this.unaryExpressionEClass, UnaryExpression.class, "UnaryExpression", false, false, true);
/* 1023 */     initEAttribute(getUnaryExpression_Operator(), (EClassifier)getUnaryOperator(), "operator", null, 1, 1, UnaryExpression.class, false, false, true, false, false, false, false, false);
/* 1024 */     initEReference(getUnaryExpression_Operand(), (EClassifier)theBehaviorsPackage.getExpression(), null, "operand", null, 1, 1, UnaryExpression.class, false, false, true, true, false, false, false, false, false);
/* 1025 */     initEAttribute(getUnaryExpression_Postfix(), (EClassifier)this.ecorePackage.getEBoolean(), "postfix", "false", 0, 1, UnaryExpression.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1027 */     initEClass(this.binaryExpressionEClass, BinaryExpression.class, "BinaryExpression", false, false, true);
/* 1028 */     initEAttribute(getBinaryExpression_Operator(), (EClassifier)getBinaryOperator(), "operator", null, 1, 1, BinaryExpression.class, false, false, true, false, false, false, false, false);
/* 1029 */     initEReference(getBinaryExpression_LeftOperand(), (EClassifier)theBehaviorsPackage.getExpression(), null, "leftOperand", null, 1, 1, BinaryExpression.class, false, false, true, true, false, false, false, false, false);
/* 1030 */     initEReference(getBinaryExpression_RightOperand(), (EClassifier)theBehaviorsPackage.getExpression(), null, "rightOperand", null, 1, 1, BinaryExpression.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1032 */     initEClass(this.indexLiteralEClass, IndexLiteral.class, "IndexLiteral", false, false, true);
/* 1033 */     initEAttribute(getIndexLiteral_Id(), (EClassifier)this.ecorePackage.getEInt(), "id", null, 1, 1, IndexLiteral.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1035 */     initEClass(this.functionCallExpressionEClass, FunctionCallExpression.class, "FunctionCallExpression", false, false, true);
/* 1036 */     initEAttribute(getFunctionCallExpression_FunctionName(), (EClassifier)this.ecorePackage.getEString(), "functionName", null, 1, 1, FunctionCallExpression.class, false, false, true, false, false, false, false, false);
/* 1037 */     initEAttribute(getFunctionCallExpression_IsOnRef(), (EClassifier)this.ecorePackage.getEBoolean(), "isOnRef", null, 0, 1, FunctionCallExpression.class, false, false, true, false, false, false, false, false);
/* 1038 */     initEReference(getFunctionCallExpression_ActualData(), (EClassifier)theBehaviorsPackage.getExpression(), null, "actualData", null, 0, -1, FunctionCallExpression.class, false, false, true, true, false, false, true, false, true);
/* 1039 */     initEReference(getFunctionCallExpression_Navigated(), (EClassifier)theBehaviorsPackage.getExpression(), null, "navigated", null, 0, 1, FunctionCallExpression.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1041 */     initEClass(this.fieldNavigationExpressionEClass, FieldNavigationExpression.class, "FieldNavigationExpression", false, false, true);
/* 1042 */     initEAttribute(getFieldNavigationExpression_IsOnRef(), (EClassifier)this.ecorePackage.getEBoolean(), "isOnRef", null, 1, 1, FieldNavigationExpression.class, false, false, true, false, false, false, false, false);
/* 1043 */     initEAttribute(getFieldNavigationExpression_FieldName(), (EClassifier)this.ecorePackage.getEString(), "fieldName", null, 1, 1, FieldNavigationExpression.class, false, false, true, false, false, false, false, false);
/*      */     
/* 1045 */     initEClass(this.dataNavigationExpressionEClass, DataNavigationExpression.class, "DataNavigationExpression", true, false, true);
/* 1046 */     initEReference(getDataNavigationExpression_Navigated(), (EClassifier)getDataReference(), null, "navigated", null, 1, 1, DataNavigationExpression.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1048 */     initEClass(this.arrayNavigationExpressionEClass, ArrayNavigationExpression.class, "ArrayNavigationExpression", false, false, true);
/* 1049 */     initEReference(getArrayNavigationExpression_Index(), (EClassifier)theBehaviorsPackage.getExpression(), null, "index", null, 1, 1, ArrayNavigationExpression.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1051 */     initEClass(this.requiredDataParameterReferenceEClass, RequiredDataParameterReference.class, "RequiredDataParameterReference", false, false, true);
/* 1052 */     initEReference(getRequiredDataParameterReference_PortReference(), (EClassifier)theInteractionsPackage.getPortParameterReference(), null, "portReference", null, 1, 1, RequiredDataParameterReference.class, false, false, true, true, false, false, false, false, false);
/*      */     
/* 1054 */     initEClass(this.stateReferenceEClass, StateReference.class, "StateReference", false, false, true);
/* 1055 */     initEReference(getStateReference_TargetState(), (EClassifier)theBehaviorsPackage.getState(), null, "targetState", null, 1, 1, StateReference.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1057 */     initEClass(this.interfaceVariableReferenceEClass, InterfaceVariableReference.class, "InterfaceVariableReference", false, false, true);
/* 1058 */     initEReference(getInterfaceVariableReference_TargetInterfaceVariable(), (EClassifier)theBehaviorsPackage.getInterfaceVariable(), null, "targetInterfaceVariable", null, 1, 1, InterfaceVariableReference.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1060 */     initEClass(this.pointerLiteralEClass, PointerLiteral.class, "PointerLiteral", false, false, true);
/*      */     
/* 1062 */     initEClass(this.innerInterfaceVariableReferenceEClass, InnerInterfaceVariableReference.class, "InnerInterfaceVariableReference", false, false, true);
/* 1063 */     initEReference(getInnerInterfaceVariableReference_PartElementReference(), (EClassifier)theInteractionsPackage.getPartElementReference(), null, "partElementReference", null, 1, 1, InnerInterfaceVariableReference.class, false, false, true, true, false, false, false, false, false);
/* 1064 */     initEReference(getInnerInterfaceVariableReference_TargetInterfaceVariable(), (EClassifier)theBehaviorsPackage.getInterfaceVariable(), null, "targetInterfaceVariable", null, 1, 1, InnerInterfaceVariableReference.class, false, false, true, false, true, false, false, false, false);
/*      */     
/* 1066 */     initEClass(this.variableReferenceEClass, VariableReference.class, "VariableReference", false, false, true);
/* 1067 */     initEReference(getVariableReference_TargetVariable(), (EClassifier)theBehaviorsPackage.getVariable(), null, "targetVariable", null, 1, 1, VariableReference.class, false, false, true, false, true, false, false, false, false);
/*      */ 
/*      */     
/* 1070 */     initEEnum(this.unaryOperatorEEnum, UnaryOperator.class, "UnaryOperator");
/* 1071 */     addEEnumLiteral(this.unaryOperatorEEnum, (Enumerator)UnaryOperator.POSITIVE);
/* 1072 */     addEEnumLiteral(this.unaryOperatorEEnum, (Enumerator)UnaryOperator.NEGATIVE);
/* 1073 */     addEEnumLiteral(this.unaryOperatorEEnum, (Enumerator)UnaryOperator.LOGICAL_NOT);
/* 1074 */     addEEnumLiteral(this.unaryOperatorEEnum, (Enumerator)UnaryOperator.DEREFERENCE);
/* 1075 */     addEEnumLiteral(this.unaryOperatorEEnum, (Enumerator)UnaryOperator.REFERENCE);
/* 1076 */     addEEnumLiteral(this.unaryOperatorEEnum, (Enumerator)UnaryOperator.BITWISE_NOT);
/* 1077 */     addEEnumLiteral(this.unaryOperatorEEnum, (Enumerator)UnaryOperator.INCREMENT);
/* 1078 */     addEEnumLiteral(this.unaryOperatorEEnum, (Enumerator)UnaryOperator.DECREMENT);
/*      */     
/* 1080 */     initEEnum(this.binaryOperatorEEnum, BinaryOperator.class, "BinaryOperator");
/* 1081 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.ADDITION);
/* 1082 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.SUBSTRACTION);
/* 1083 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.MULTIPLICATION);
/* 1084 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.DIVISION);
/* 1085 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.MODULUS);
/* 1086 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.EQUALITY);
/* 1087 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.INEQUALITY);
/* 1088 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.LESS_THAN);
/* 1089 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.GREATER_THAN);
/* 1090 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.LESS_THAN_OR_EQUAL);
/* 1091 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.GREATER_THAN_OR_EQUAL);
/* 1092 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.LOGICAL_OR);
/* 1093 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.LOGICAL_AND);
/* 1094 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.BITWISE_OR);
/* 1095 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.BITWISE_XOR);
/* 1096 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.BITWISE_AND);
/* 1097 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.LEFT_SHIFT);
/* 1098 */     addEEnumLiteral(this.binaryOperatorEEnum, (Enumerator)BinaryOperator.RIGHT_SHIFT);
/*      */ 
/*      */     
/* 1101 */     createResource("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore");
/*      */   }
/*      */   
/*      */   public static ExpressionsPackage init() {
/*      */     if (isInited)
/*      */       return (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore"); 
/*      */     ExpressionsPackageImpl theExpressionsPackage = (EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") instanceof ExpressionsPackageImpl) ? (ExpressionsPackageImpl)EPackage.Registry.INSTANCE.get("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore") : new ExpressionsPackageImpl();
/*      */     isInited = true;
/*      */     ModulesPackageImpl theModulesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") instanceof ModulesPackageImpl) ? (ModulesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Modules.ecore") : (ModulesPackageImpl)ModulesPackage.eINSTANCE;
/*      */     BehaviorsPackageImpl theBehaviorsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") instanceof BehaviorsPackageImpl) ? (BehaviorsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Behaviors.ecore") : (BehaviorsPackageImpl)BehaviorsPackage.eINSTANCE;
/*      */     InteractionsPackageImpl theInteractionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") instanceof InteractionsPackageImpl) ? (InteractionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Interactions.ecore") : (InteractionsPackageImpl)InteractionsPackage.eINSTANCE;
/*      */     PrioritiesPackageImpl thePrioritiesPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") instanceof PrioritiesPackageImpl) ? (PrioritiesPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/Priorities.ecore") : (PrioritiesPackageImpl)PrioritiesPackage.eINSTANCE;
/*      */     PortExpressionsPackageImpl thePortExpressionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") instanceof PortExpressionsPackageImpl) ? (PortExpressionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/PortExpressions.ecore") : (PortExpressionsPackageImpl)PortExpressionsPackage.eINSTANCE;
/*      */     ActionsPackageImpl theActionsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") instanceof ActionsPackageImpl) ? (ActionsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Core/ActionLanguage/Actions.ecore") : (ActionsPackageImpl)ActionsPackage.eINSTANCE;
/*      */     TimePackageImpl theTimePackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") instanceof TimePackageImpl) ? (TimePackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Time.ecore") : (TimePackageImpl)TimePackage.eINSTANCE;
/*      */     ContractsPackageImpl theContractsPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") instanceof ContractsPackageImpl) ? (ContractsPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Contracts.ecore") : (ContractsPackageImpl)ContractsPackage.eINSTANCE;
/*      */     TraceabilityPackageImpl theTraceabilityPackage = (EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") instanceof TraceabilityPackageImpl) ? (TraceabilityPackageImpl)EPackage.Registry.INSTANCE.getEPackage("http:///ujf/verimag/bip/Extra/Traceability.ecore") : (TraceabilityPackageImpl)TraceabilityPackage.eINSTANCE;
/*      */     theExpressionsPackage.createPackageContents();
/*      */     theModulesPackage.createPackageContents();
/*      */     theBehaviorsPackage.createPackageContents();
/*      */     theInteractionsPackage.createPackageContents();
/*      */     thePrioritiesPackage.createPackageContents();
/*      */     thePortExpressionsPackage.createPackageContents();
/*      */     theActionsPackage.createPackageContents();
/*      */     theTimePackage.createPackageContents();
/*      */     theContractsPackage.createPackageContents();
/*      */     theTraceabilityPackage.createPackageContents();
/*      */     theExpressionsPackage.initializePackageContents();
/*      */     theModulesPackage.initializePackageContents();
/*      */     theBehaviorsPackage.initializePackageContents();
/*      */     theInteractionsPackage.initializePackageContents();
/*      */     thePrioritiesPackage.initializePackageContents();
/*      */     thePortExpressionsPackage.initializePackageContents();
/*      */     theActionsPackage.initializePackageContents();
/*      */     theTimePackage.initializePackageContents();
/*      */     theContractsPackage.initializePackageContents();
/*      */     theTraceabilityPackage.initializePackageContents();
/*      */     theExpressionsPackage.freeze();
/*      */     EPackage.Registry.INSTANCE.put("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore", theExpressionsPackage);
/*      */     return theExpressionsPackage;
/*      */   }
/*      */   
/*      */   public EClass getDataReference() {
/*      */     return this.dataReferenceEClass;
/*      */   }
/*      */   
/*      */   public EClass getDataParameterSpecification() {
/*      */     return this.dataParameterSpecificationEClass;
/*      */   }
/*      */   
/*      */   public EReference getDataParameterSpecification_TargetParameter() {
/*      */     return (EReference)this.dataParameterSpecificationEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getInnerDataParameterReference() {
/*      */     return this.innerDataParameterReferenceEClass;
/*      */   }
/*      */   
/*      */   public EReference getInnerDataParameterReference_PortReference() {
/*      */     return (EReference)this.innerDataParameterReferenceEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getDataParameterReference() {
/*      */     return this.dataParameterReferenceEClass;
/*      */   }
/*      */   
/*      */   public EClass getBooleanLiteral() {
/*      */     return this.booleanLiteralEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getBooleanLiteral_BValue() {
/*      */     return (EAttribute)this.booleanLiteralEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getIntegerLiteral() {
/*      */     return this.integerLiteralEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getIntegerLiteral_IValue() {
/*      */     return (EAttribute)this.integerLiteralEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getRealLiteral() {
/*      */     return this.realLiteralEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getRealLiteral_RValue() {
/*      */     return (EAttribute)this.realLiteralEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getStringLiteral() {
/*      */     return this.stringLiteralEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getStringLiteral_SValue() {
/*      */     return (EAttribute)this.stringLiteralEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getUnaryExpression() {
/*      */     return this.unaryExpressionEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getUnaryExpression_Operator() {
/*      */     return (EAttribute)this.unaryExpressionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getUnaryExpression_Operand() {
/*      */     return (EReference)this.unaryExpressionEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EAttribute getUnaryExpression_Postfix() {
/*      */     return (EAttribute)this.unaryExpressionEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EClass getBinaryExpression() {
/*      */     return this.binaryExpressionEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getBinaryExpression_Operator() {
/*      */     return (EAttribute)this.binaryExpressionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getBinaryExpression_LeftOperand() {
/*      */     return (EReference)this.binaryExpressionEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getBinaryExpression_RightOperand() {
/*      */     return (EReference)this.binaryExpressionEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EClass getIndexLiteral() {
/*      */     return this.indexLiteralEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getIndexLiteral_Id() {
/*      */     return (EAttribute)this.indexLiteralEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getFunctionCallExpression() {
/*      */     return this.functionCallExpressionEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getFunctionCallExpression_FunctionName() {
/*      */     return (EAttribute)this.functionCallExpressionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EAttribute getFunctionCallExpression_IsOnRef() {
/*      */     return (EAttribute)this.functionCallExpressionEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EReference getFunctionCallExpression_ActualData() {
/*      */     return (EReference)this.functionCallExpressionEClass.getEStructuralFeatures().get(2);
/*      */   }
/*      */   
/*      */   public EReference getFunctionCallExpression_Navigated() {
/*      */     return (EReference)this.functionCallExpressionEClass.getEStructuralFeatures().get(3);
/*      */   }
/*      */   
/*      */   public EClass getFieldNavigationExpression() {
/*      */     return this.fieldNavigationExpressionEClass;
/*      */   }
/*      */   
/*      */   public EAttribute getFieldNavigationExpression_IsOnRef() {
/*      */     return (EAttribute)this.fieldNavigationExpressionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EAttribute getFieldNavigationExpression_FieldName() {
/*      */     return (EAttribute)this.fieldNavigationExpressionEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getDataNavigationExpression() {
/*      */     return this.dataNavigationExpressionEClass;
/*      */   }
/*      */   
/*      */   public EReference getDataNavigationExpression_Navigated() {
/*      */     return (EReference)this.dataNavigationExpressionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getArrayNavigationExpression() {
/*      */     return this.arrayNavigationExpressionEClass;
/*      */   }
/*      */   
/*      */   public EReference getArrayNavigationExpression_Index() {
/*      */     return (EReference)this.arrayNavigationExpressionEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getRequiredDataParameterReference() {
/*      */     return this.requiredDataParameterReferenceEClass;
/*      */   }
/*      */   
/*      */   public EReference getRequiredDataParameterReference_PortReference() {
/*      */     return (EReference)this.requiredDataParameterReferenceEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getStateReference() {
/*      */     return this.stateReferenceEClass;
/*      */   }
/*      */   
/*      */   public EReference getStateReference_TargetState() {
/*      */     return (EReference)this.stateReferenceEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getInterfaceVariableReference() {
/*      */     return this.interfaceVariableReferenceEClass;
/*      */   }
/*      */   
/*      */   public EReference getInterfaceVariableReference_TargetInterfaceVariable() {
/*      */     return (EReference)this.interfaceVariableReferenceEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EClass getPointerLiteral() {
/*      */     return this.pointerLiteralEClass;
/*      */   }
/*      */   
/*      */   public EClass getInnerInterfaceVariableReference() {
/*      */     return this.innerInterfaceVariableReferenceEClass;
/*      */   }
/*      */   
/*      */   public EReference getInnerInterfaceVariableReference_PartElementReference() {
/*      */     return (EReference)this.innerInterfaceVariableReferenceEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EReference getInnerInterfaceVariableReference_TargetInterfaceVariable() {
/*      */     return (EReference)this.innerInterfaceVariableReferenceEClass.getEStructuralFeatures().get(1);
/*      */   }
/*      */   
/*      */   public EClass getVariableReference() {
/*      */     return this.variableReferenceEClass;
/*      */   }
/*      */   
/*      */   public EReference getVariableReference_TargetVariable() {
/*      */     return (EReference)this.variableReferenceEClass.getEStructuralFeatures().get(0);
/*      */   }
/*      */   
/*      */   public EEnum getUnaryOperator() {
/*      */     return this.unaryOperatorEEnum;
/*      */   }
/*      */   
/*      */   public EEnum getBinaryOperator() {
/*      */     return this.binaryOperatorEEnum;
/*      */   }
/*      */   
/*      */   public ExpressionsFactory getExpressionsFactory() {
/*      */     return (ExpressionsFactory)getEFactoryInstance();
/*      */   }
/*      */   
/*      */   public void createPackageContents() {
/*      */     if (this.isCreated)
/*      */       return; 
/*      */     this.isCreated = true;
/*      */     this.dataReferenceEClass = createEClass(0);
/*      */     this.dataParameterSpecificationEClass = createEClass(1);
/*      */     createEReference(this.dataParameterSpecificationEClass, 0);
/*      */     this.innerDataParameterReferenceEClass = createEClass(2);
/*      */     createEReference(this.innerDataParameterReferenceEClass, 1);
/*      */     this.dataParameterReferenceEClass = createEClass(3);
/*      */     this.booleanLiteralEClass = createEClass(4);
/*      */     createEAttribute(this.booleanLiteralEClass, 0);
/*      */     this.integerLiteralEClass = createEClass(5);
/*      */     createEAttribute(this.integerLiteralEClass, 0);
/*      */     this.realLiteralEClass = createEClass(6);
/*      */     createEAttribute(this.realLiteralEClass, 0);
/*      */     this.stringLiteralEClass = createEClass(7);
/*      */     createEAttribute(this.stringLiteralEClass, 0);
/*      */     this.unaryExpressionEClass = createEClass(8);
/*      */     createEAttribute(this.unaryExpressionEClass, 0);
/*      */     createEReference(this.unaryExpressionEClass, 1);
/*      */     createEAttribute(this.unaryExpressionEClass, 2);
/*      */     this.binaryExpressionEClass = createEClass(9);
/*      */     createEAttribute(this.binaryExpressionEClass, 0);
/*      */     createEReference(this.binaryExpressionEClass, 1);
/*      */     createEReference(this.binaryExpressionEClass, 2);
/*      */     this.indexLiteralEClass = createEClass(10);
/*      */     createEAttribute(this.indexLiteralEClass, 0);
/*      */     this.functionCallExpressionEClass = createEClass(11);
/*      */     createEAttribute(this.functionCallExpressionEClass, 0);
/*      */     createEAttribute(this.functionCallExpressionEClass, 1);
/*      */     createEReference(this.functionCallExpressionEClass, 2);
/*      */     createEReference(this.functionCallExpressionEClass, 3);
/*      */     this.fieldNavigationExpressionEClass = createEClass(12);
/*      */     createEAttribute(this.fieldNavigationExpressionEClass, 1);
/*      */     createEAttribute(this.fieldNavigationExpressionEClass, 2);
/*      */     this.dataNavigationExpressionEClass = createEClass(13);
/*      */     createEReference(this.dataNavigationExpressionEClass, 0);
/*      */     this.arrayNavigationExpressionEClass = createEClass(14);
/*      */     createEReference(this.arrayNavigationExpressionEClass, 1);
/*      */     this.requiredDataParameterReferenceEClass = createEClass(15);
/*      */     createEReference(this.requiredDataParameterReferenceEClass, 1);
/*      */     this.stateReferenceEClass = createEClass(16);
/*      */     createEReference(this.stateReferenceEClass, 0);
/*      */     this.interfaceVariableReferenceEClass = createEClass(17);
/*      */     createEReference(this.interfaceVariableReferenceEClass, 0);
/*      */     this.pointerLiteralEClass = createEClass(18);
/*      */     this.innerInterfaceVariableReferenceEClass = createEClass(19);
/*      */     createEReference(this.innerInterfaceVariableReferenceEClass, 0);
/*      */     createEReference(this.innerInterfaceVariableReferenceEClass, 1);
/*      */     this.variableReferenceEClass = createEClass(20);
/*      */     createEReference(this.variableReferenceEClass, 0);
/*      */     this.unaryOperatorEEnum = createEEnum(21);
/*      */     this.binaryOperatorEEnum = createEEnum(22);
/*      */   }
/*      */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\ExpressionsPackageImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */