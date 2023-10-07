/*     */ package ujf.verimag.bip.Core.ActionLanguage.Expressions.util;
/*     */ 
/*     */ import org.eclipse.emf.common.notify.Adapter;
/*     */ import org.eclipse.emf.common.notify.Notifier;
/*     */ import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
/*     */ import org.eclipse.emf.ecore.EObject;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.ArrayNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BinaryExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.BooleanLiteral;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataNavigationExpression;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterReference;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataParameterSpecification;
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.DataReference;
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
/*     */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.VariableReference;
/*     */ import ujf.verimag.bip.Core.Behaviors.Action;
/*     */ import ujf.verimag.bip.Core.Behaviors.Expression;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExpressionsAdapterFactory
/*     */   extends AdapterFactoryImpl
/*     */ {
/*     */   protected static ExpressionsPackage modelPackage;
/*     */   
/*     */   public ExpressionsAdapterFactory() {
/*  47 */     if (modelPackage == null)
/*     */     {
/*  49 */       modelPackage = ExpressionsPackage.eINSTANCE;
/*     */     }
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
/*     */   public boolean isFactoryForType(Object object) {
/*  64 */     if (object == modelPackage)
/*     */     {
/*  66 */       return true;
/*     */     }
/*  68 */     if (object instanceof EObject)
/*     */     {
/*  70 */       return (((EObject)object).eClass().getEPackage() == modelPackage);
/*     */     }
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   protected ExpressionsSwitch<Adapter> modelSwitch = new ExpressionsSwitch<Adapter>()
/*     */     {
/*     */ 
/*     */       
/*     */       public Adapter caseDataReference(DataReference object)
/*     */       {
/*  87 */         return ExpressionsAdapterFactory.this.createDataReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDataParameterSpecification(DataParameterSpecification object) {
/*  92 */         return ExpressionsAdapterFactory.this.createDataParameterSpecificationAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseInnerDataParameterReference(InnerDataParameterReference object) {
/*  97 */         return ExpressionsAdapterFactory.this.createInnerDataParameterReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDataParameterReference(DataParameterReference object) {
/* 102 */         return ExpressionsAdapterFactory.this.createDataParameterReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseBooleanLiteral(BooleanLiteral object) {
/* 107 */         return ExpressionsAdapterFactory.this.createBooleanLiteralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseIntegerLiteral(IntegerLiteral object) {
/* 112 */         return ExpressionsAdapterFactory.this.createIntegerLiteralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseRealLiteral(RealLiteral object) {
/* 117 */         return ExpressionsAdapterFactory.this.createRealLiteralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseStringLiteral(StringLiteral object) {
/* 122 */         return ExpressionsAdapterFactory.this.createStringLiteralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseUnaryExpression(UnaryExpression object) {
/* 127 */         return ExpressionsAdapterFactory.this.createUnaryExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseBinaryExpression(BinaryExpression object) {
/* 132 */         return ExpressionsAdapterFactory.this.createBinaryExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseIndexLiteral(IndexLiteral object) {
/* 137 */         return ExpressionsAdapterFactory.this.createIndexLiteralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseFunctionCallExpression(FunctionCallExpression object) {
/* 142 */         return ExpressionsAdapterFactory.this.createFunctionCallExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseFieldNavigationExpression(FieldNavigationExpression object) {
/* 147 */         return ExpressionsAdapterFactory.this.createFieldNavigationExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseDataNavigationExpression(DataNavigationExpression object) {
/* 152 */         return ExpressionsAdapterFactory.this.createDataNavigationExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseArrayNavigationExpression(ArrayNavigationExpression object) {
/* 157 */         return ExpressionsAdapterFactory.this.createArrayNavigationExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseRequiredDataParameterReference(RequiredDataParameterReference object) {
/* 162 */         return ExpressionsAdapterFactory.this.createRequiredDataParameterReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseStateReference(StateReference object) {
/* 167 */         return ExpressionsAdapterFactory.this.createStateReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseInterfaceVariableReference(InterfaceVariableReference object) {
/* 172 */         return ExpressionsAdapterFactory.this.createInterfaceVariableReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter casePointerLiteral(PointerLiteral object) {
/* 177 */         return ExpressionsAdapterFactory.this.createPointerLiteralAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseInnerInterfaceVariableReference(InnerInterfaceVariableReference object) {
/* 182 */         return ExpressionsAdapterFactory.this.createInnerInterfaceVariableReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseVariableReference(VariableReference object) {
/* 187 */         return ExpressionsAdapterFactory.this.createVariableReferenceAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseAction(Action object) {
/* 192 */         return ExpressionsAdapterFactory.this.createActionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter caseExpression(Expression object) {
/* 197 */         return ExpressionsAdapterFactory.this.createExpressionAdapter();
/*     */       }
/*     */ 
/*     */       
/*     */       public Adapter defaultCase(EObject object) {
/* 202 */         return ExpressionsAdapterFactory.this.createEObjectAdapter();
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Adapter createAdapter(Notifier target) {
/* 217 */     return this.modelSwitch.doSwitch((EObject)target);
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
/*     */   public Adapter createDataReferenceAdapter() {
/* 233 */     return null;
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
/*     */   public Adapter createDataParameterSpecificationAdapter() {
/* 248 */     return null;
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
/*     */   public Adapter createInnerDataParameterReferenceAdapter() {
/* 263 */     return null;
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
/*     */   public Adapter createDataParameterReferenceAdapter() {
/* 278 */     return null;
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
/*     */   public Adapter createBooleanLiteralAdapter() {
/* 293 */     return null;
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
/*     */   public Adapter createIntegerLiteralAdapter() {
/* 308 */     return null;
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
/*     */   public Adapter createRealLiteralAdapter() {
/* 323 */     return null;
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
/*     */   public Adapter createStringLiteralAdapter() {
/* 338 */     return null;
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
/*     */   public Adapter createUnaryExpressionAdapter() {
/* 353 */     return null;
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
/*     */   public Adapter createBinaryExpressionAdapter() {
/* 368 */     return null;
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
/*     */   public Adapter createIndexLiteralAdapter() {
/* 383 */     return null;
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
/*     */   public Adapter createFunctionCallExpressionAdapter() {
/* 398 */     return null;
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
/*     */   public Adapter createFieldNavigationExpressionAdapter() {
/* 413 */     return null;
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
/*     */   public Adapter createDataNavigationExpressionAdapter() {
/* 428 */     return null;
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
/*     */   public Adapter createArrayNavigationExpressionAdapter() {
/* 443 */     return null;
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
/*     */   public Adapter createRequiredDataParameterReferenceAdapter() {
/* 458 */     return null;
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
/*     */   public Adapter createStateReferenceAdapter() {
/* 473 */     return null;
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
/*     */   public Adapter createInterfaceVariableReferenceAdapter() {
/* 488 */     return null;
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
/*     */   public Adapter createPointerLiteralAdapter() {
/* 503 */     return null;
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
/*     */   public Adapter createInnerInterfaceVariableReferenceAdapter() {
/* 518 */     return null;
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
/*     */   public Adapter createVariableReferenceAdapter() {
/* 533 */     return null;
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
/*     */   public Adapter createActionAdapter() {
/* 548 */     return null;
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
/*     */   public Adapter createExpressionAdapter() {
/* 563 */     return null;
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
/*     */   public Adapter createEObjectAdapter() {
/* 576 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expression\\util\ExpressionsAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */