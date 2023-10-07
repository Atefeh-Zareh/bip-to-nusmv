/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.impl;
/*     */ 
/*     */ import org.eclipse.emf.ecore.EClass;
/*     */ import org.eclipse.emf.ecore.EDataType;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import org.eclipse.emf.ecore.EPackage;
/*     */ import org.eclipse.emf.ecore.impl.EFactoryImpl;
/*     */ import org.eclipse.emf.ecore.plugin.EcorePlugin;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryOperator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsFactory;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ExpressionsPackage;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FieldNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.FunctionCallExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IndexLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerDataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InnerInterfaceVariableReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.IntegerLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.InterfaceVariableReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.PointerLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RealLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.RequiredDataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StateReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.StringLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.UnaryOperator;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ 
/*     */ public class ExpressionsFactoryImpl
/*     */   extends EFactoryImpl
/*     */   implements ExpressionsFactory
/*     */ {
/*     */   public static ExpressionsFactory init() {
/*     */     try {
/*  38 */       ExpressionsFactory theExpressionsFactory = (ExpressionsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ujf/verimag/bip/Core/ActionLanguage/Expressions.ecore");
/*  39 */       if (theExpressionsFactory != null)
/*     */       {
/*  41 */         return theExpressionsFactory;
/*     */       }
/*     */     }
/*  44 */     catch (Exception exception) {
/*     */       
/*  46 */       EcorePlugin.INSTANCE.log(exception);
/*     */     } 
/*  48 */     return new ExpressionsFactoryImpl();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EObject create(EClass eClass) {
/*  70 */     switch (eClass.getClassifierID()) {
/*     */       case 2:
/*  72 */         return (EObject)createInnerDataParameterReference();
/*  73 */       case 3: return (EObject)createDataParameterReference();
/*  74 */       case 4: return (EObject)createBooleanLiteral();
/*  75 */       case 5: return (EObject)createIntegerLiteral();
/*  76 */       case 6: return (EObject)createRealLiteral();
/*  77 */       case 7: return (EObject)createStringLiteral();
/*  78 */       case 8: return (EObject)createUnaryExpression();
/*  79 */       case 9: return (EObject)createBinaryExpression();
/*  80 */       case 10: return (EObject)createIndexLiteral();
/*  81 */       case 11: return (EObject)createFunctionCallExpression();
/*  82 */       case 12: return (EObject)createFieldNavigationExpression();
/*  83 */       case 14: return (EObject)createArrayNavigationExpression();
/*  84 */       case 15: return (EObject)createRequiredDataParameterReference();
/*  85 */       case 16: return (EObject)createStateReference();
/*  86 */       case 17: return (EObject)createInterfaceVariableReference();
/*  87 */       case 18: return (EObject)createPointerLiteral();
/*  88 */       case 19: return (EObject)createInnerInterfaceVariableReference();
/*  89 */       case 20: return (EObject)createVariableReference();
/*     */     } 
/*  91 */     throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object createFromString(EDataType eDataType, String initialValue) {
/* 103 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 21:
/* 106 */         return createUnaryOperatorFromString(eDataType, initialValue);
/*     */       case 22:
/* 108 */         return createBinaryOperatorFromString(eDataType, initialValue);
/*     */     } 
/* 110 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertToString(EDataType eDataType, Object instanceValue) {
/* 122 */     switch (eDataType.getClassifierID()) {
/*     */       
/*     */       case 21:
/* 125 */         return convertUnaryOperatorToString(eDataType, instanceValue);
/*     */       case 22:
/* 127 */         return convertBinaryOperatorToString(eDataType, instanceValue);
/*     */     } 
/* 129 */     throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InnerDataParameterReference createInnerDataParameterReference() {
/* 140 */     InnerDataParameterReferenceImpl innerDataParameterReference = new InnerDataParameterReferenceImpl();
/* 141 */     return innerDataParameterReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataParameterReference createDataParameterReference() {
/* 151 */     DataParameterReferenceImpl dataParameterReference = new DataParameterReferenceImpl();
/* 152 */     return dataParameterReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BooleanLiteral createBooleanLiteral() {
/* 162 */     BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
/* 163 */     return booleanLiteral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntegerLiteral createIntegerLiteral() {
/* 173 */     IntegerLiteralImpl integerLiteral = new IntegerLiteralImpl();
/* 174 */     return integerLiteral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RealLiteral createRealLiteral() {
/* 184 */     RealLiteralImpl realLiteral = new RealLiteralImpl();
/* 185 */     return realLiteral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringLiteral createStringLiteral() {
/* 195 */     StringLiteralImpl stringLiteral = new StringLiteralImpl();
/* 196 */     return stringLiteral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UnaryExpression createUnaryExpression() {
/* 206 */     UnaryExpressionImpl unaryExpression = new UnaryExpressionImpl();
/* 207 */     return unaryExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BinaryExpression createBinaryExpression() {
/* 217 */     BinaryExpressionImpl binaryExpression = new BinaryExpressionImpl();
/* 218 */     return binaryExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndexLiteral createIndexLiteral() {
/* 228 */     IndexLiteralImpl indexLiteral = new IndexLiteralImpl();
/* 229 */     return indexLiteral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FunctionCallExpression createFunctionCallExpression() {
/* 239 */     FunctionCallExpressionImpl functionCallExpression = new FunctionCallExpressionImpl();
/* 240 */     return functionCallExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldNavigationExpression createFieldNavigationExpression() {
/* 250 */     FieldNavigationExpressionImpl fieldNavigationExpression = new FieldNavigationExpressionImpl();
/* 251 */     return fieldNavigationExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayNavigationExpression createArrayNavigationExpression() {
/* 261 */     ArrayNavigationExpressionImpl arrayNavigationExpression = new ArrayNavigationExpressionImpl();
/* 262 */     return arrayNavigationExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RequiredDataParameterReference createRequiredDataParameterReference() {
/* 272 */     RequiredDataParameterReferenceImpl requiredDataParameterReference = new RequiredDataParameterReferenceImpl();
/* 273 */     return requiredDataParameterReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StateReference createStateReference() {
/* 283 */     StateReferenceImpl stateReference = new StateReferenceImpl();
/* 284 */     return stateReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InterfaceVariableReference createInterfaceVariableReference() {
/* 294 */     InterfaceVariableReferenceImpl interfaceVariableReference = new InterfaceVariableReferenceImpl();
/* 295 */     return interfaceVariableReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PointerLiteral createPointerLiteral() {
/* 305 */     PointerLiteralImpl pointerLiteral = new PointerLiteralImpl();
/* 306 */     return pointerLiteral;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InnerInterfaceVariableReference createInnerInterfaceVariableReference() {
/* 316 */     InnerInterfaceVariableReferenceImpl innerInterfaceVariableReference = new InnerInterfaceVariableReferenceImpl();
/* 317 */     return innerInterfaceVariableReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VariableReference createVariableReference() {
/* 327 */     VariableReferenceImpl variableReference = new VariableReferenceImpl();
/* 328 */     return variableReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UnaryOperator createUnaryOperatorFromString(EDataType eDataType, String initialValue) {
/* 338 */     UnaryOperator result = UnaryOperator.get(initialValue);
/* 339 */     if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); 
/* 340 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertUnaryOperatorToString(EDataType eDataType, Object instanceValue) {
/* 350 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BinaryOperator createBinaryOperatorFromString(EDataType eDataType, String initialValue) {
/* 360 */     BinaryOperator result = BinaryOperator.get(initialValue);
/* 361 */     if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); 
/* 362 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertBinaryOperatorToString(EDataType eDataType, Object instanceValue) {
/* 372 */     return (instanceValue == null) ? null : instanceValue.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExpressionsPackage getExpressionsPackage() {
/* 382 */     return (ExpressionsPackage)getEPackage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static ExpressionsPackage getPackage() {
/* 394 */     return ExpressionsPackage.eINSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\impl\ExpressionsFactoryImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */