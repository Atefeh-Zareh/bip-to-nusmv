/*    */ package ujf.verimag.bip.Core.ActionLanguage.Expressions;
/*    */ 
/*    */ import org.eclipse.emf.ecore.EFactory;
/*    */ import ujf.verimag.bip.Core.ActionLanguage.Expressions.impl.ExpressionsFactoryImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ExpressionsFactory
/*    */   extends EFactory
/*    */ {
/* 27 */   public static final ExpressionsFactory eINSTANCE = ExpressionsFactoryImpl.init();
/*    */   
/*    */   InnerDataParameterReference createInnerDataParameterReference();
/*    */   
/*    */   DataParameterReference createDataParameterReference();
/*    */   
/*    */   BooleanLiteral createBooleanLiteral();
/*    */   
/*    */   IntegerLiteral createIntegerLiteral();
/*    */   
/*    */   RealLiteral createRealLiteral();
/*    */   
/*    */   StringLiteral createStringLiteral();
/*    */   
/*    */   UnaryExpression createUnaryExpression();
/*    */   
/*    */   BinaryExpression createBinaryExpression();
/*    */   
/*    */   IndexLiteral createIndexLiteral();
/*    */   
/*    */   FunctionCallExpression createFunctionCallExpression();
/*    */   
/*    */   FieldNavigationExpression createFieldNavigationExpression();
/*    */   
/*    */   ArrayNavigationExpression createArrayNavigationExpression();
/*    */   
/*    */   RequiredDataParameterReference createRequiredDataParameterReference();
/*    */   
/*    */   StateReference createStateReference();
/*    */   
/*    */   InterfaceVariableReference createInterfaceVariableReference();
/*    */   
/*    */   PointerLiteral createPointerLiteral();
/*    */   
/*    */   InnerInterfaceVariableReference createInnerInterfaceVariableReference();
/*    */   
/*    */   VariableReference createVariableReference();
/*    */   
/*    */   ExpressionsPackage getExpressionsPackage();
/*    */ }


/* Location:              C:\D\Workspace\VeriSolid\My-Changes\smart-contracts-fixes-extensions\verificationTools\bip-to-nusmv.jar\\ujf\verimag\bip\Core\ActionLanguage\Expressions\ExpressionsFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */